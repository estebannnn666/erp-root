/**
 * 
 */
package ec.com.erp.transportistas.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.TransportistaDTO;
import ec.com.erp.cliente.mdl.dto.id.TransportistaID;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2018-04-20
 */
public class TransportistasDAO implements ITransportistasDAO {

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
	 * M\u00e9todo para obtener lista de clientes
	 * @param codigoCompania
	 * @return 
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<TransportistaDTO> obtenerListaTransportistas(Integer codigoCompania, String numeroDocumento, String razonSocial) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(TransportistaDTO.class, "root");
			criteria.createAlias("root.tipoClienteCatalogoValorDTO", "tipoClienteCatalogoValorDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("root.personaDTO", "personaDTO", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("personaDTO.contactoDTOCols", "contactoPersonaDTO", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("root.empresaDTO", "empresaDTO", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("empresaDTO.contactoDTOCols", "contactoEmpresaDTO", CriteriaSpecification.LEFT_JOIN);
			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));

			if(numeroDocumento != null) {
				criteria.add(Restrictions.or(Restrictions.eq("personaDTO.numeroDocumento", numeroDocumento), Restrictions.eq("empresaDTO.numeroRuc", numeroDocumento)));
			}
			
			if(razonSocial != null) {
				criteria.add(Restrictions.or(Restrictions.eq("personaDTO.nombreCompleto", razonSocial), Restrictions.eq("empresaDTO.razonSocial", razonSocial)));
			}
			
			// Proyecciones entidad clientes 
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoCliente"), "id_codigoCliente");
			projectionList.add(Projections.property("root.codigoPersona"), "codigoPersona");
			projectionList.add(Projections.property("root.codigoEmpresa"), "codigoEmpresa");
			projectionList.add(Projections.property("root.codigoValorTipoTransportista"), "codigoValorTipoTransportista");
			projectionList.add(Projections.property("root.codigoTipoTransportista"), "codigoTipoTransportista");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			// Proyecciones catalogos
			projectionList.add(Projections.property("tipoTransportistaCatalogoValorDTO.nombreCatalogoValor"), "tipoTransportistaCatalogoValorDTO_nombreCatalogoValor");
			
			// Proyecciones entidad persona 
			projectionList.add(Projections.property("personaDTO.id.codigoCompania"), "personaDTO_id_codigoCompania");
			projectionList.add(Projections.property("personaDTO.id.codigoPersona"), "personaDTO_id_codigoPersona");
			projectionList.add(Projections.property("personaDTO.numeroDocumento"), "personaDTO_numeroDocumento");
			projectionList.add(Projections.property("personaDTO.nombreCompleto"), "personaDTO_nombreCompleto");
			
			// Proyecciones entidad contacto persona 
			projectionList.add(Projections.property("contactoPersonaDTO.direccionPrincipal"), "personaDTO_contactoPersonaDTO_direccionPrincipal");
			projectionList.add(Projections.property("contactoPersonaDTO.ciudad"), "personaDTO_contactoPersonaDTO_ciudad");
			projectionList.add(Projections.property("contactoPersonaDTO.telefonoPrincipal"), "personaDTO_contactoPersonaDTO_telefonoPrincipal");
			
			// Proyecciones entidad empresa   
			projectionList.add(Projections.property("empresaDTO.id.codigoCompania"), "empresaDTO_id_codigoCompania");
			projectionList.add(Projections.property("empresaDTO.id.codigoEmpresa"), "empresaDTO_id_codigoEmpresa");
			projectionList.add(Projections.property("empresaDTO.numeroRuc"), "empresaDTO_numeroRuc");
			projectionList.add(Projections.property("empresaDTO.razonSocial"), "empresaDTO_razonSocial");
			
			// Proyecciones entidad contacto empresa
			projectionList.add(Projections.property("contactoEmpresaDTO.direccionPrincipal"), "empresaDTO_contactoEmpresaDTO_direccionPrincipal");
			projectionList.add(Projections.property("contactoEmpresaDTO.ciudad"), "empresaDTO_contactoEmpresaDTO_ciudad");
			projectionList.add(Projections.property("contactoEmpresaDTO.telefonoPrincipal"), "empresaDTO_contactoEmpresaDTO_telefonoPrincipal");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(TransportistaDTO.class));
			Collection<TransportistaDTO> transportistaDTOCols = new  ArrayList<TransportistaDTO>();
			transportistaDTOCols =  criteria.list();

			return transportistaDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de transportistas.").initCause(e);
		} 
	}
	
	
	/**
	 * M\u00e9todo para guardar y actualizar trasportista
	 * @param transportistaDTO
	 * @throws ERPException
	 */
	@Override
	public void guardarActualizarTransportista(TransportistaDTO transportistaDTO) throws ERPException{
		try{
			if (transportistaDTO.getId().getCodigoCompania() == null || transportistaDTO.getUsuarioRegistro() == null) {
				throw new ERPException("El c\u00F3digo de compania y el id de usuario registro es requerido");
			}	
			
			sessionFactory.getCurrentSession().clear();
			if(transportistaDTO.getId().getCodigoTransportista() ==  null){
				Integer secuencialCliente  = this.secuenciaDAO.obtenerSecuencialTabla(TransportistaID.NOMBRE_SECUENCIA);
				transportistaDTO.getId().setCodigoTransportista(Long.parseLong(""+secuencialCliente));
				transportistaDTO.setFechaRegistro(new Date());
				transportistaDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				sessionFactory.getCurrentSession().save(transportistaDTO);
			}
			else
			{
				transportistaDTO.setFechaModificacion(new Date());
				transportistaDTO.setUsuarioModificacion(transportistaDTO.getUsuarioRegistro());
				sessionFactory.getCurrentSession().update(transportistaDTO);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Ocurrio un error al guardar o actualizar el transportista."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Ocurrio un error al guardar o actualizar el transportista."+e.getMessage());
		} 
	}

}
