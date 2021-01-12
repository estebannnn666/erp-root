/**
 * 
 */
package ec.com.erp.vehiculo.chofer.dao;

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
import ec.com.erp.cliente.mdl.dto.VehiculoChoferDTO;
import ec.com.erp.cliente.mdl.dto.id.VehiculoChoferID;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2018-05-02
 */
public class VehiculoChoferDAO implements IVehiculoChoferDAO {

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
	 *  M\u00e9todo para obtener lista de choferes por vehiculo
	 * @param codigoCompania
	 * @param placa
	 * @return
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<VehiculoChoferDTO> obtenerListaChoferesByVehiculo(Integer codigoCompania, String placa) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(VehiculoChoferDTO.class, "root");
			criteria.createAlias("root.tipoChoferCatalogoValorDTO", "tipoChoferCatalogoValorDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("root.vehiculoDTO", "vehiculoDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("root.choferDTO", "choferDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("choferDTO.tipoLicenciaCatalogoValorDTO", "tipoLicenciaCatalogoValorDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("choferDTO.personaDTO", "personaDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("personaDTO.contactoDTOCols", "contactoPersonaDTO", CriteriaSpecification.INNER_JOIN);
			
			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			criteria.add(Restrictions.eq("vehiculoDTO.placa", placa));

			// Proyecciones entidad vehiculo chofer 
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoVehiculoChofer"), "id_codigoVehiculoChofer");
			projectionList.add(Projections.property("root.codigoVehiculo"), "codigoVehiculo");
			projectionList.add(Projections.property("root.codigoChofer"), "codigoChofer");
			projectionList.add(Projections.property("root.codigoVehiculo"), "codigoVehiculo");
			projectionList.add(Projections.property("root.codigoValorTipoChofer"), "codigoValorTipoChofer");
			projectionList.add(Projections.property("root.codigoTipoChofer"), "codigoTipoChofer");
			projectionList.add(Projections.property("root.descripcion"), "descripcion");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			// Proyecciones entidad chofer 
			projectionList.add(Projections.property("choferDTO.id.codigoCompania"), "choferDTO_id_codigoCompania");
			projectionList.add(Projections.property("choferDTO.id.codigoChofer"), "choferDTO_id_codigoChofer");
			projectionList.add(Projections.property("choferDTO.codigoPersona"), "choferDTO_codigoPersona");
			projectionList.add(Projections.property("choferDTO.codigoTransportista"), "choferDTO_codigoTransportista");
			projectionList.add(Projections.property("choferDTO.codigoValorTipoLicencia"), "choferDTO_codigoValorTipoLicencia");
			projectionList.add(Projections.property("choferDTO.codigoTipoLicencia"), "choferDTO_codigoTipoLicencia");
			projectionList.add(Projections.property("choferDTO.fechaCaducidadLicencia"), "choferDTO_fechaCaducidadLicencia");
			projectionList.add(Projections.property("choferDTO.estado"), "choferDTO_estado");
			projectionList.add(Projections.property("choferDTO.usuarioRegistro"), "choferDTO_usuarioRegistro");
			projectionList.add(Projections.property("choferDTO.fechaRegistro"), "choferDTO_fechaRegistro");
			
			// Proyecciones catalogos tipo chofer
			projectionList.add(Projections.property("tipoChoferCatalogoValorDTO.nombreCatalogoValor"), "tipoChoferCatalogoValorDTO_nombreCatalogoValor");
						
			// Proyecciones catalogos tipo licencia
			projectionList.add(Projections.property("tipoLicenciaCatalogoValorDTO.nombreCatalogoValor"), "choferDTO_tipoLicenciaCatalogoValorDTO_nombreCatalogoValor");
						
			// Proyecciones entidad persona del chofer
			projectionList.add(Projections.property("personaDTO.id.codigoCompania"), "choferDTO_personaDTO_id_codigoCompania");
			projectionList.add(Projections.property("personaDTO.id.codigoPersona"), "choferDTO_personaDTO_id_codigoPersona");
			projectionList.add(Projections.property("personaDTO.numeroDocumento"), "choferDTO_personaDTO_numeroDocumento");
			projectionList.add(Projections.property("personaDTO.nombreCompleto"), "choferDTO_personaDTO_nombreCompleto");
			
			// Proyecciones entidad contacto persona del chofer
			projectionList.add(Projections.property("contactoPersonaDTO.direccionPrincipal"), "choferDTO_personaDTO_contactoPersonaDTO_direccionPrincipal");
			projectionList.add(Projections.property("contactoPersonaDTO.ciudad"), "choferDTO_personaDTO_contactoPersonaDTO_ciudad");
			projectionList.add(Projections.property("contactoPersonaDTO.telefonoPrincipal"), "choferDTO_personaDTO_contactoPersonaDTO_telefonoPrincipal");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(VehiculoChoferDTO.class));
			Collection<VehiculoChoferDTO> vehiculoChoferDTOCols = new  ArrayList<VehiculoChoferDTO>();
			vehiculoChoferDTOCols =  criteria.list();

			return vehiculoChoferDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de choferes por vehiculo.").initCause(e);
		} 
	}
	
	
	/**
	 * M\u00e9todo para guardar y actualizar vehiculo chofer
	 * @param vehiculoChoferDTO
	 * @throws ERPException
	 */
	@Override
	public void guardarActualizarVehiculoChofer(VehiculoChoferDTO vehiculoChoferDTO) throws ERPException{
		try{
			if (vehiculoChoferDTO.getId().getCodigoCompania() == null || vehiculoChoferDTO.getUsuarioRegistro() == null) {
				throw new ERPException("Error", "El c\u00F3digo de compania y el id de usuario registro es requerido");
			}	
			
			sessionFactory.getCurrentSession().clear();
			if(vehiculoChoferDTO.getId().getCodigoVehiculoChofer() ==  null){
				Integer secuencialVehiculoChofer  = this.secuenciaDAO.obtenerSecuencialTabla(VehiculoChoferID.NOMBRE_SECUENCIA);
				vehiculoChoferDTO.getId().setCodigoVehiculoChofer(Long.parseLong(""+secuencialVehiculoChofer));
				vehiculoChoferDTO.setFechaRegistro(new Date());
				vehiculoChoferDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				sessionFactory.getCurrentSession().save(vehiculoChoferDTO);
			}
			else
			{
				vehiculoChoferDTO.setFechaModificacion(new Date());
				vehiculoChoferDTO.setUsuarioModificacion(vehiculoChoferDTO.getUsuarioRegistro());
				sessionFactory.getCurrentSession().update(vehiculoChoferDTO);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar el vehiculo chofer."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar el vehiculo chofer."+e.getMessage());
		} 
	}

}
