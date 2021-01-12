/**
 * 
 */
package ec.com.erp.perfiles.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.PerfilDTO;
import ec.com.erp.cliente.mdl.dto.id.PerfilID;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2017-06-27
 */
public class PerfilesDAO implements IPerfilesDAO{

	/**
	 * SessionFactory sessionFactory.
	 */
	private SessionFactory sessionFactory;

	/**
	 * Dao para obtnener la secuencia
	 */
	private ISecuenciaDAO secuenciaDAO;

	/**
	 *  M\u00E9todo que asigna el valor de sessionFactory del objeto.
	 * @param sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	

	/**
	 * @return the secuenciaDAO
	 */
	public ISecuenciaDAO getSecuenciaDAO() {
		return secuenciaDAO;
	}

	/**
	 * @param secuenciaDAO the secuenciaDAO to set
	 */
	public void setSecuenciaDAO(ISecuenciaDAO secuenciaDAO) {
		this.secuenciaDAO = secuenciaDAO;
	}	

	/**
	 * M\u00e9todo para obtener lista de perfiles
	 * @param parametro para buscar por nombre de perfil
	 * @return 
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<PerfilDTO> obtenerListaPerfiles(String nombrePerfil) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(PerfilDTO.class, "root");
			criteria.createAlias("root.perfilCatalogoValorDTO", "perfilCatalogoValorDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("root.moduloPerfilDTOCols", "moduloPerfilDTOCols", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("moduloPerfilDTOCols.moduloDTO", "moduloDTO", CriteriaSpecification.LEFT_JOIN);
			
			//restricciones
			if(StringUtils.isNotEmpty(nombrePerfil)) {
				nombrePerfil = nombrePerfil.toUpperCase();
				criteria.add(Restrictions.like("root.nombrePerfil", nombrePerfil, MatchMode.ANYWHERE));
			}
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			
			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoPerfil"), "id_codigoPerfil");
			projectionList.add(Projections.property("root.nombrePerfil"), "nombrePerfil");
			projectionList.add(Projections.property("root.descripcion"), "descripcion");
			projectionList.add(Projections.property("root.codigoValorTipoPerfil"), "codigoValorTipoPerfil");
			projectionList.add(Projections.property("root.codigoTipoPerfil"), "codigoTipoPerfil");
			projectionList.add(Projections.property("root.estado"), "estado");
			
			// Proyecciones catalogos
			projectionList.add(Projections.property("perfilCatalogoValorDTO.nombreCatalogoValor"), "perfilCatalogoValorDTO_nombreCatalogoValor");
			
			// Proyecciones entidad modulo perfil
			projectionList.add(Projections.property("moduloPerfilDTOCols.id.codigoModulo"), "moduloPerfilDTOCols_id_codigoModulo");
			projectionList.add(Projections.property("moduloPerfilDTOCols.id.codigoPerfil"), "moduloPerfilDTOCols_id_codigoPerfil");
			projectionList.add(Projections.property("moduloPerfilDTOCols.estado"), "moduloPerfilDTOCols_estado");
			
			// Proyecciones modulos
			projectionList.add(Projections.property("moduloDTO.id.codigoModulo"), "moduloPerfilDTOCols_moduloDTO_id_codigoModulo");
			projectionList.add(Projections.property("moduloDTO.codigoReferencia"), "moduloPerfilDTOCols_moduloDTO_codigoReferencia");
			projectionList.add(Projections.property("moduloDTO.nombreModulo"), "moduloPerfilDTOCols_moduloDTO_nombreModulo");
			projectionList.add(Projections.property("moduloDTO.orden"), "moduloPerfilDTOCols_moduloDTO_orden");
						
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(PerfilDTO.class));
			Collection<PerfilDTO> perfilDTOCols = new  ArrayList<PerfilDTO>();
			perfilDTOCols =  criteria.list();

			return perfilDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de perfiles.").initCause(e);
		} 
	}

	/**
	 * M\u00e9todo para crear o actualizar 
	 * @param perfilDTO
	 * @throws ERPException
	 */
	@Override
	public void crearActualizarPerfil(PerfilDTO perfilDTO)throws ERPException{
		try{
			sessionFactory.getCurrentSession().clear();
			if(perfilDTO.getId().getCodigoPerfil() ==  null){
				Integer secuencialFactura = this.secuenciaDAO.obtenerSecuencialTabla(PerfilID.NOMBRE_SECUENCIA);
				perfilDTO.getId().setCodigoPerfil(Long.parseLong(""+secuencialFactura));
				perfilDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				sessionFactory.getCurrentSession().save(perfilDTO);
			}else{
				sessionFactory.getCurrentSession().update(perfilDTO);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar el perfil."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar el perfil."+e.getMessage());
		} 
	}
}
