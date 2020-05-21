/**
 * 
 */
package ec.com.erp.chofer.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
import ec.com.erp.cliente.mdl.dto.ChoferDTO;
import ec.com.erp.cliente.mdl.dto.id.ChoferID;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2018-05-02
 */
public class ChoferDAO implements IChoferDAO {

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
	 *  M\u00e9todo para obtener lista de choferes
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @param nombreChofer
	 * @return
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<ChoferDTO> obtenerListaChoferes(Integer codigoCompania, String numeroDocumento, String nombreChofer) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(ChoferDTO.class, "root");
			criteria.createAlias("root.tipoLicenciaCatalogoValorDTO", "tipoLicenciaCatalogoValorDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("root.personaDTO", "personaDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("personaDTO.contactoDTOCols", "contactoPersonaDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("root.transportistaDTO", "transportistaDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("transportistaDTO.personaDTO", "personaDTOTrans", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("transportistaDTO.empresaDTO", "empresaDTOTrans", CriteriaSpecification.LEFT_JOIN);
			
			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));

			if(StringUtils.isNotBlank(numeroDocumento)) {
				criteria.add(Restrictions.eq("personaDTO.numeroDocumento", numeroDocumento));
			}
			
			if(StringUtils.isNotBlank(nombreChofer)) {
				nombreChofer = nombreChofer.toUpperCase();
				criteria.add(Restrictions.like("personaDTO.nombreCompleto", nombreChofer, MatchMode.ANYWHERE));
			}
			
			// Proyecciones entidad chofer 
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoChofer"), "id_codigoChofer");
			projectionList.add(Projections.property("root.codigoPersona"), "codigoPersona");
			projectionList.add(Projections.property("root.codigoTransportista"), "codigoTransportista");
			projectionList.add(Projections.property("root.codigoValorTipoLicencia"), "codigoValorTipoLicencia");
			projectionList.add(Projections.property("root.codigoTipoLicencia"), "codigoTipoLicencia");
			projectionList.add(Projections.property("root.fechaCaducidadLicencia"), "fechaCaducidadLicencia");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			// Proyecciones catalogos
			projectionList.add(Projections.property("tipoLicenciaCatalogoValorDTO.nombreCatalogoValor"), "tipoLicenciaCatalogoValorDTO_nombreCatalogoValor");
						
			// Proyecciones entidad persona del chofer
			projectionList.add(Projections.property("personaDTO.id.codigoCompania"), "personaDTO_id_codigoCompania");
			projectionList.add(Projections.property("personaDTO.id.codigoPersona"), "personaDTO_id_codigoPersona");
			projectionList.add(Projections.property("personaDTO.numeroDocumento"), "personaDTO_numeroDocumento");
			projectionList.add(Projections.property("personaDTO.primerApellido"), "personaDTO_primerApellido");
			projectionList.add(Projections.property("personaDTO.segundoApellido"), "personaDTO_segundoApellido");
			projectionList.add(Projections.property("personaDTO.primerNombre"), "personaDTO_primerNombre");
			projectionList.add(Projections.property("personaDTO.segundoNombre"), "personaDTO_segundoNombre");
			projectionList.add(Projections.property("personaDTO.nombreCompleto"), "personaDTO_nombreCompleto");
			projectionList.add(Projections.property("personaDTO.fechaNacimiento"), "personaDTO_fechaNacimiento");
			projectionList.add(Projections.property("personaDTO.estado"), "personaDTO_estado");
			projectionList.add(Projections.property("personaDTO.usuarioRegistro"), "personaDTO_usuarioRegistro");
			projectionList.add(Projections.property("personaDTO.fechaRegistro"), "personaDTO_fechaRegistro");
			
			// Proyecciones entidad contacto persona del chofer
			projectionList.add(Projections.property("contactoPersonaDTO.id.codigoCompania"), "personaDTO_contactoPersonaDTO_id_codigoCompania");
			projectionList.add(Projections.property("contactoPersonaDTO.id.codigoContacto"), "personaDTO_contactoPersonaDTO_id_codigoContacto");			
			projectionList.add(Projections.property("contactoPersonaDTO.codigoPersona"), "personaDTO_contactoPersonaDTO_codigoPersona");
			projectionList.add(Projections.property("contactoPersonaDTO.codigoEmpresa"), "personaDTO_contactoPersonaDTO_codigoEmpresa");
			projectionList.add(Projections.property("contactoPersonaDTO.direccionPrincipal"), "personaDTO_contactoPersonaDTO_direccionPrincipal");			
			projectionList.add(Projections.property("contactoPersonaDTO.callePrincipal"), "personaDTO_contactoPersonaDTO_callePrincipal");			
			projectionList.add(Projections.property("contactoPersonaDTO.calleSecundaria"), "personaDTO_contactoPersonaDTO_calleSecundaria");			
			projectionList.add(Projections.property("contactoPersonaDTO.numeroCasa"), "personaDTO_contactoPersonaDTO_numeroCasa");
			projectionList.add(Projections.property("contactoPersonaDTO.referencia"), "personaDTO_contactoPersonaDTO_referencia");
			projectionList.add(Projections.property("contactoPersonaDTO.ciudad"), "personaDTO_contactoPersonaDTO_ciudad");			
			projectionList.add(Projections.property("contactoPersonaDTO.telefonoPrincipal"), "personaDTO_contactoPersonaDTO_telefonoPrincipal");
			projectionList.add(Projections.property("contactoPersonaDTO.telefonoCelular"), "personaDTO_contactoPersonaDTO_telefonoCelular");			
			projectionList.add(Projections.property("contactoPersonaDTO.codigoValarTipoContacto"), "personaDTO_contactoPersonaDTO_codigoValarTipoContacto");
			projectionList.add(Projections.property("contactoPersonaDTO.codigoTipoContacto"), "personaDTO_contactoPersonaDTO_codigoTipoContacto");			
			projectionList.add(Projections.property("contactoPersonaDTO.estado"), "personaDTO_contactoPersonaDTO_estado");
			projectionList.add(Projections.property("contactoPersonaDTO.usuarioRegistro"), "personaDTO_contactoPersonaDTO_usuarioRegistro");
			projectionList.add(Projections.property("contactoPersonaDTO.fechaRegistro"), "personaDTO_contactoPersonaDTO_fechaRegistro");
						
			// Proyecciones entidad transportista
			projectionList.add(Projections.property("transportistaDTO.id.codigoCompania"), "transportistaDTO_id_codigoCompania");
			projectionList.add(Projections.property("transportistaDTO.id.codigoTransportista"), "transportistaDTO_id_codigoTransportista");
			projectionList.add(Projections.property("transportistaDTO.codigoPersona"), "transportistaDTO_codigoPersona");
			projectionList.add(Projections.property("transportistaDTO.codigoEmpresa"), "transportistaDTO_codigoEmpresa");
			projectionList.add(Projections.property("transportistaDTO.codigoValorTipoTransportista"), "transportistaDTO_codigoValorTipoTransportista");
			projectionList.add(Projections.property("transportistaDTO.codigoTipoTransportista"), "transportistaDTO_codigoTipoTransportista");
			projectionList.add(Projections.property("transportistaDTO.estado"), "estado");
			
			// Proyecciones entidad persona del tranpostista
			projectionList.add(Projections.property("personaDTOTrans.id.codigoCompania"), "transportistaDTO_personaDTO_id_codigoCompania");
			projectionList.add(Projections.property("personaDTOTrans.id.codigoPersona"), "transportistaDTO_personaDTO_id_codigoPersona");
			projectionList.add(Projections.property("personaDTOTrans.numeroDocumento"), "transportistaDTO_personaDTO_numeroDocumento");
			projectionList.add(Projections.property("personaDTOTrans.nombreCompleto"), "transportistaDTO_personaDTO_nombreCompleto");
						
			// Proyecciones entidad empresa del tranpostista  
			projectionList.add(Projections.property("empresaDTOTrans.id.codigoCompania"), "transportistaDTO_empresaDTO_id_codigoCompania");
			projectionList.add(Projections.property("empresaDTOTrans.id.codigoEmpresa"), "transportistaDTO_empresaDTO_id_codigoEmpresa");
			projectionList.add(Projections.property("empresaDTOTrans.numeroRuc"), "transportistaDTO_empresaDTO_numeroRuc");
			projectionList.add(Projections.property("empresaDTOTrans.razonSocial"), "transportistaDTO_empresaDTO_razonSocial");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(ChoferDTO.class));
			Collection<ChoferDTO> choferDTOCols = new  ArrayList<ChoferDTO>();
			choferDTOCols =  criteria.list();

			return choferDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de choferes.").initCause(e);
		} 
	}
	
	
	/**
	 * M\u00e9todo para guardar y actualizar chofer
	 * @param choferDTO
	 * @throws ERPException
	 */
	@Override
	public void guardarActualizarChofer(ChoferDTO choferDTO) throws ERPException{
		try{
			if (choferDTO.getId().getCodigoCompania() == null || choferDTO.getUsuarioRegistro() == null) {
				throw new ERPException("El c\u00F3digo de compania y el id de usuario registro es requerido");
			}	
			
			sessionFactory.getCurrentSession().clear();
			if(choferDTO.getId().getCodigoChofer() ==  null){
				Integer secuencialChofer  = this.secuenciaDAO.obtenerSecuencialTabla(ChoferID.NOMBRE_SECUENCIA);
				choferDTO.getId().setCodigoChofer(Long.parseLong(""+secuencialChofer));
				choferDTO.setFechaRegistro(new Date());
				choferDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				sessionFactory.getCurrentSession().save(choferDTO);
			}
			else
			{
				choferDTO.setFechaModificacion(new Date());
				choferDTO.setUsuarioModificacion(choferDTO.getUsuarioRegistro());
				sessionFactory.getCurrentSession().update(choferDTO);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Ocurrio un error al guardar o actualizar el transportista."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Ocurrio un error al guardar o actualizar el transportista."+e.getMessage());
		} 
	}

}
