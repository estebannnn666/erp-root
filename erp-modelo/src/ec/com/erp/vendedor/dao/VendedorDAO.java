/**
 * 
 */
package ec.com.erp.vendedor.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.VendedorDTO;
import ec.com.erp.cliente.mdl.dto.id.VendedorID;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2018-04-20
 */
public class VendedorDAO implements IVendedorDAO {

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
	 * M\u00e9todo para obtener lista de vendedores
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @param nombreVendedor
	 * @return 
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<VendedorDTO> obtenerListaVendedores(Integer codigoCompania, String numeroDocumento, String nombreVendedor) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins 
			Criteria criteria  = session.createCriteria(VendedorDTO.class, "root");
			criteria.createAlias("root.personaDTO", "personaDTO", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("personaDTO.contactoDTOCols", "contactoPersonaDTO", CriteriaSpecification.LEFT_JOIN);
			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));

			if(numeroDocumento != null && numeroDocumento.trim() != "") {
				numeroDocumento = numeroDocumento.toUpperCase();
				criteria.add(Restrictions.eq("personaDTO.numeroDocumento", numeroDocumento));
			}
			
			if(nombreVendedor != null && nombreVendedor.trim() != "") {
				nombreVendedor = nombreVendedor.toUpperCase();
				criteria.add(Restrictions.like("personaDTO.nombreCompleto", nombreVendedor, MatchMode.ANYWHERE));
			}
			
			// Proyecciones entidad clientes 
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoVendedor"), "id_codigoVendedor");
			projectionList.add(Projections.property("root.codigoPersona"), "codigoPersona");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
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
			projectionList.add(Projections.property("contactoPersonaDTO.fechaRegistro"), "personaDTO_contactoPersonaDTO_fechaRegistro");
			projectionList.add(Projections.property("contactoPersonaDTO.usuarioRegistro"), "personaDTO_contactoPersonaDTO_usuarioRegistro");
			
			criteria.setProjection(projectionList);
			criteria.addOrder(Order.asc("personaDTO.nombreCompleto"));
			criteria.setResultTransformer(new MultiLevelResultTransformer(VendedorDTO.class));
			Collection<VendedorDTO> vendedoresDTOCols = new  ArrayList<>();
			vendedoresDTOCols =  criteria.list();

			return vendedoresDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de vendedores.").initCause(e);
		} 
	}
	
	
	/**
	 * M\u00e9todo para guardar y actualizar vendedor
	 * @param vendedorDTO
	 * @throws ERPException
	 */
	@Override
	public void guardarActualizarVendedor(VendedorDTO vendedorDTO) throws ERPException{
		try{
			if (vendedorDTO.getId().getCodigoCompania() == null || vendedorDTO.getUsuarioRegistro() == null) {
				throw new ERPException("El c\u00F3digo de compania y el id de usuario registro es requerido");
			}	
			
			sessionFactory.getCurrentSession().clear();
			if(vendedorDTO.getId().getCodigoVendedor() ==  null){
				Integer secuencialVendedor  = this.secuenciaDAO.obtenerSecuencialTabla(VendedorID.NOMBRE_SECUENCIA);
				vendedorDTO.getId().setCodigoVendedor(Long.parseLong(""+secuencialVendedor));
				vendedorDTO.setFechaRegistro(new Date());
				vendedorDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				sessionFactory.getCurrentSession().save(vendedorDTO);
			}else{
				vendedorDTO.setFechaModificacion(new Date());
				vendedorDTO.setUsuarioModificacion(vendedorDTO.getUsuarioRegistro());
				sessionFactory.getCurrentSession().update(vendedorDTO);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Ocurrio un error al guardar o actualizar el vendedor."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Ocurrio un error al guardar o actualizar el vendedor."+e.getMessage());
		} 
	}

}
