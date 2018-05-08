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
			criteria.createAlias("root.tipoTransportistaCatalogoValorDTO", "tipoTransportistaCatalogoValorDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("root.personaDTO", "personaDTO", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("personaDTO.contactoDTOCols", "contactoPersonaDTO", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("root.empresaDTO", "empresaDTO", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("empresaDTO.contactoDTOCols", "contactoEmpresaDTO", CriteriaSpecification.LEFT_JOIN);
			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));

			if(numeroDocumento != null && numeroDocumento.trim() != "") {
				numeroDocumento = numeroDocumento.toUpperCase();
				criteria.add(Restrictions.or(Restrictions.eq("personaDTO.numeroDocumento", numeroDocumento), Restrictions.eq("empresaDTO.numeroRuc", numeroDocumento)));
			}
			
			if(razonSocial != null && razonSocial.trim() != "") {
				razonSocial = razonSocial.toUpperCase();
				criteria.add(Restrictions.or(Restrictions.eq("personaDTO.nombreCompleto", razonSocial), Restrictions.eq("empresaDTO.razonSocial", razonSocial)));
			}
			
			// Proyecciones entidad clientes 
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoTransportista"), "id_codigoTransportista");
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
			projectionList.add(Projections.property("personaDTO.primerApellido"), "personaDTO_primerApellido");
			projectionList.add(Projections.property("personaDTO.segundoApellido"), "personaDTO_segundoApellido");
			projectionList.add(Projections.property("personaDTO.primerNombre"), "personaDTO_primerNombre");
			projectionList.add(Projections.property("personaDTO.segundoNombre"), "personaDTO_segundoNombre");
			projectionList.add(Projections.property("personaDTO.nombreCompleto"), "personaDTO_nombreCompleto");
			projectionList.add(Projections.property("personaDTO.fechaNacimiento"), "personaDTO_fechaNacimiento");
			projectionList.add(Projections.property("personaDTO.estado"), "personaDTO_estado");
			projectionList.add(Projections.property("personaDTO.usuarioRegistro"), "personaDTO_usuarioRegistro");
			projectionList.add(Projections.property("personaDTO.fechaRegistro"), "personaDTO_fechaRegistro");
			
			// Proyecciones entidad contacto persona 
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
			
			//Proyecciones entidad empresa
			projectionList.add(Projections.property("empresaDTO.id.codigoCompania"), "empresaDTO_id_codigoCompania");
			projectionList.add(Projections.property("empresaDTO.id.codigoEmpresa"), "empresaDTO_id_codigoEmpresa");
			projectionList.add(Projections.property("empresaDTO.numeroRuc"), "empresaDTO_numeroRuc");
			projectionList.add(Projections.property("empresaDTO.razonSocial"), "empresaDTO_razonSocial");
			projectionList.add(Projections.property("empresaDTO.descripcionEmpresa"), "empresaDTO_descripcionEmpresa");
			projectionList.add(Projections.property("empresaDTO.estado"), "empresaDTO_estado");
			projectionList.add(Projections.property("empresaDTO.usuarioRegistro"), "empresaDTO_usuarioRegistro");
			projectionList.add(Projections.property("empresaDTO.fechaRegistro"), "empresaDTO_fechaRegistro");
			
			// Proyecciones entidad contacto empresa 
			projectionList.add(Projections.property("contactoEmpresaDTO.id.codigoCompania"), "empresaDTO_contactoEmpresaDTO_id_codigoCompania");
			projectionList.add(Projections.property("contactoEmpresaDTO.id.codigoContacto"), "empresaDTO_contactoEmpresaDTO_id_codigoContacto");			
			projectionList.add(Projections.property("contactoEmpresaDTO.codigoPersona"), "empresaDTO_contactoEmpresaDTO_codigoPersona");
			projectionList.add(Projections.property("contactoEmpresaDTO.codigoEmpresa"), "empresaDTO_contactoEmpresaDTO_codigoEmpresa");
			projectionList.add(Projections.property("contactoEmpresaDTO.direccionPrincipal"), "empresaDTO_contactoEmpresaDTO_direccionPrincipal");			
			projectionList.add(Projections.property("contactoEmpresaDTO.callePrincipal"), "empresaDTO_contactoEmpresaDTO_callePrincipal");			
			projectionList.add(Projections.property("contactoEmpresaDTO.calleSecundaria"), "empresaDTO_contactoEmpresaDTO_calleSecundaria");			
			projectionList.add(Projections.property("contactoEmpresaDTO.numeroCasa"), "empresaDTO_contactoEmpresaDTO_numeroCasa");
			projectionList.add(Projections.property("contactoEmpresaDTO.referencia"), "empresaDTO_contactoEmpresaDTO_referencia");
			projectionList.add(Projections.property("contactoEmpresaDTO.ciudad"), "empresaDTO_contactoEmpresaDTO_ciudad");			
			projectionList.add(Projections.property("contactoEmpresaDTO.telefonoPrincipal"), "empresaDTO_contactoEmpresaDTO_telefonoPrincipal");
			projectionList.add(Projections.property("contactoEmpresaDTO.telefonoCelular"), "empresaDTO_contactoEmpresaDTO_telefonoCelular");			
			projectionList.add(Projections.property("contactoEmpresaDTO.codigoValarTipoContacto"), "empresaDTO_contactoEmpresaDTO_codigoValarTipoContacto");
			projectionList.add(Projections.property("contactoEmpresaDTO.codigoTipoContacto"), "empresaDTO_contactoEmpresaDTO_codigoTipoContacto");			
			projectionList.add(Projections.property("contactoEmpresaDTO.estado"), "empresaDTO_contactoEmpresaDTO_estado");
			projectionList.add(Projections.property("contactoEmpresaDTO.usuarioRegistro"), "empresaDTO_contactoEmpresaDTO_usuarioRegistro");
			projectionList.add(Projections.property("contactoEmpresaDTO.fechaRegistro"), "empresaDTO_contactoEmpresaDTO_fechaRegistro");
			
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
