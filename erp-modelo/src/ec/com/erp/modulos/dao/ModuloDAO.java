/**
 * 
 */
package ec.com.erp.modulos.dao;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ModuloDTO;
import ec.com.erp.cliente.mdl.dto.ModuloPerfilDTO;
import ec.com.erp.cliente.mdl.dto.id.ModuloID;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2017-06-27
 */
public class ModuloDAO implements IModuloDAO {

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
	 * M\u00e9todo para obtener lista de modulos
	 * @param nombreModulo
	 * @return
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<ModuloDTO> obtenerListaModulos(String nombreModulo) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(ModuloDTO.class, "root");

			//restricciones
			if(StringUtils.isNotEmpty(nombreModulo)){
				nombreModulo = nombreModulo.toUpperCase();
				criteria.add(Restrictions.like("root.nombreModulo", nombreModulo, MatchMode.ANYWHERE));
			}
			
			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoModulo"), "id_codigoModulo");
			projectionList.add(Projections.property("root.codigoReferencia"), "codigoReferencia");
			projectionList.add(Projections.property("root.orden"), "orden");
			projectionList.add(Projections.property("root.nombreModulo"), "nombreModulo");
			projectionList.add(Projections.property("root.descripcion"), "descripcion");
			projectionList.add(Projections.property("root.estilo"), "estilo");
			projectionList.add(Projections.property("root.valorTipo"), "valorTipo");
			projectionList.add(Projections.property("root.url"), "url");
			projectionList.add(Projections.property("root.estado"), "estado");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(ModuloDTO.class));

			return criteria.list();

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de modulos.").initCause(e);
		} 
	}
	
	/**
	 * M\u00e9todo para obtener lista de modulos activos
	 * @param nombreModulo
	 * @return
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<ModuloDTO> obtenerListaModulosActivos(String nombreModulo) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(ModuloDTO.class, "root");

			//restricciones
			if(StringUtils.isNotEmpty(nombreModulo)){
				criteria.add(Restrictions.eq("root.nombreModulo", nombreModulo));
			}
			
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));

			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoModulo"), "id_codigoModulo");
			projectionList.add(Projections.property("root.codigoReferencia"), "codigoReferencia");
			projectionList.add(Projections.property("root.orden"), "orden");
			projectionList.add(Projections.property("root.nombreModulo"), "nombreModulo");
			projectionList.add(Projections.property("root.descripcion"), "descripcion");
			projectionList.add(Projections.property("root.estilo"), "estilo");
			projectionList.add(Projections.property("root.url"), "url");
			projectionList.add(Projections.property("root.estado"), "estado");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(ModuloDTO.class));

			return criteria.list();

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de modulos.").initCause(e);
		} 
	}

	/**
	 * M\u00e9todo para crear o actualizar 
	 * @param contactoDTO
	 * @throws ERPException
	 */
	@Override
	public void crearActualizarModulo(ModuloDTO moduloDTO)throws ERPException{
		try{
			sessionFactory.getCurrentSession().clear();
			if(moduloDTO.getId().getCodigoModulo() ==  null){
				Integer secuencialFactura = this.secuenciaDAO.obtenerSecuencialTabla(ModuloID.NOMBRE_SECUENCIA);
				moduloDTO.getId().setCodigoModulo(Long.parseLong(""+secuencialFactura));
				moduloDTO.setCodigoReferencia("MOD-"+secuencialFactura);
				moduloDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				sessionFactory.getCurrentSession().save(moduloDTO);
			}
			else
			{
				sessionFactory.getCurrentSession().update(moduloDTO);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Ocurrio un error al guardar o actualizar el modulo."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Ocurrio un error al guardar o actualizar el modulo."+e.getMessage());
		} 
	}
	
	/**
	 * M\u00e9todo para crear o actualizar modulo perfil
	 * @param contactoDTO
	 * @throws ERPException
	 */
	@Override
	public void crearActualizarModuloPerfil(ModuloPerfilDTO moduloPerfilDTO)throws ERPException{
		try{
			sessionFactory.getCurrentSession().clear();
			moduloPerfilDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
			sessionFactory.getCurrentSession().saveOrUpdate(moduloPerfilDTO);
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Ocurrio un error al guardar o actualizar el modulo."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Ocurrio un error al guardar o actualizar el modulo."+e.getMessage());
		} 
	}
}
