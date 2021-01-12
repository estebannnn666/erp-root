/**
 * 
 */
package ec.com.erp.vehiculo.dao;

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
import ec.com.erp.cliente.mdl.dto.VehiculoDTO;
import ec.com.erp.cliente.mdl.dto.id.VehiculoID;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2018-05-02
 */
public class VehiculoDAO implements IVehiculoDAO {

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
	 * M\u00e9todo para obtener lista de vehiculos
	 * @param codigoCompania
	 * @param placa
	 * @param documentoTransportista
	 * @param nombreTransportista
	 * @return Collection<VehiculoDTO>
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<VehiculoDTO> obtenerListaVehiculos(Integer codigoCompania, String placa, String documentoTransportista, String nombreTransportista) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(VehiculoDTO.class, "root");
			criteria.createAlias("root.tipoVehiculoCatalogoValorDTO", "tipoVehiculoCatalogoValorDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("root.transportistaDTO", "transportistaDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("transportistaDTO.personaDTO", "personaDTOTrans", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("transportistaDTO.empresaDTO", "empresaDTOTrans", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("root.vehiculoChoferDTOCols", "vehiculoChoferDTOCols", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("vehiculoChoferDTOCols.tipoChoferCatalogoValorDTO", "tipoChoferCatalogoValorDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("vehiculoChoferDTOCols.choferDTO", "choferDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("choferDTO.personaDTO", "personaDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("choferDTO.tipoLicenciaCatalogoValorDTO", "tipoLicenciaCatalogoValorDTO", CriteriaSpecification.INNER_JOIN);
			
			
			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			criteria.add(Restrictions.eq("choferDTO.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			criteria.add(Restrictions.eq("vehiculoChoferDTOCols.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));

			if(StringUtils.isNotBlank(placa)) {
				placa = placa.toUpperCase();
				criteria.add(Restrictions.eq("root.placa", placa));
			}
			
			if(StringUtils.isNotBlank(documentoTransportista)) {
				criteria.add(Restrictions.or(Restrictions.eq("personaDTOTrans.numeroDocumento", documentoTransportista), Restrictions.eq("empresaDTOTrans.numeroRuc", documentoTransportista)));
			}
			
			if(StringUtils.isNotBlank(nombreTransportista)) {
				nombreTransportista = nombreTransportista.toUpperCase();
				criteria.add(Restrictions.or(Restrictions.like("personaDTOTrans.nombreCompleto", nombreTransportista, MatchMode.ANYWHERE), Restrictions.like("empresaDTOTrans.razonSocial", nombreTransportista, MatchMode.ANYWHERE)));
			}
			
			// Proyecciones entidad chofer 
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoVehiculo"), "id_codigoVehiculo");			
			projectionList.add(Projections.property("root.codigoTransportista"), "codigoTransportista");
			projectionList.add(Projections.property("root.placa"), "placa");
			projectionList.add(Projections.property("root.marca"), "marca");
			projectionList.add(Projections.property("root.color"), "color");
			projectionList.add(Projections.property("root.modelo"), "modelo");			
			projectionList.add(Projections.property("root.codigoValorTipoVehiculo"), "codigoValorTipoVehiculo");
			projectionList.add(Projections.property("root.codigoTipoVehiculo"), "codigoTipoVehiculo");			
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			// Proyecciones catalogos
			projectionList.add(Projections.property("tipoVehiculoCatalogoValorDTO.nombreCatalogoValor"), "tipoVehiculoCatalogoValorDTO_nombreCatalogoValor");
			
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
			
			// Proyecciones entidad vehiculo chofer
			projectionList.add(Projections.property("vehiculoChoferDTOCols.id.codigoCompania"), "vehiculoChoferDTOCols_id_codigoCompania");
			projectionList.add(Projections.property("vehiculoChoferDTOCols.id.codigoVehiculoChofer"), "vehiculoChoferDTOCols_id_codigoVehiculoChofer");
			projectionList.add(Projections.property("vehiculoChoferDTOCols.codigoVehiculo"), "vehiculoChoferDTOCols_codigoVehiculo");
			projectionList.add(Projections.property("vehiculoChoferDTOCols.codigoChofer"), "vehiculoChoferDTOCols_codigoChofer");
			projectionList.add(Projections.property("vehiculoChoferDTOCols.codigoValorTipoChofer"), "vehiculoChoferDTOCols_codigoValorTipoChofer");
			projectionList.add(Projections.property("vehiculoChoferDTOCols.codigoTipoChofer"), "vehiculoChoferDTOCols_codigoTipoChofer");
			projectionList.add(Projections.property("vehiculoChoferDTOCols.descripcion"), "vehiculoChoferDTOCols_descripcion");
			projectionList.add(Projections.property("vehiculoChoferDTOCols.estado"), "vehiculoChoferDTOCols_estado");
			projectionList.add(Projections.property("vehiculoChoferDTOCols.usuarioRegistro"), "vehiculoChoferDTOCols_usuarioRegistro");
			projectionList.add(Projections.property("vehiculoChoferDTOCols.fechaRegistro"), "vehiculoChoferDTOCols_fechaRegistro");
			
			// Proyecciones catalogo tipo chofer
			projectionList.add(Projections.property("tipoChoferCatalogoValorDTO.nombreCatalogoValor"), "vehiculoChoferDTOCols_tipoChoferCatalogoValorDTO_nombreCatalogoValor");
			
			// Proyecciones entidad chofer
			projectionList.add(Projections.property("choferDTO.id.codigoCompania"), "vehiculoChoferDTOCols_choferDTO_id_codigoCompania");
			projectionList.add(Projections.property("choferDTO.id.codigoChofer"), "vehiculoChoferDTOCols_choferDTO_id_codigoChofer");
			projectionList.add(Projections.property("choferDTO.estado"), "vehiculoChoferDTOCols_choferDTO_estado");
			projectionList.add(Projections.property("choferDTO.usuarioRegistro"), "vehiculoChoferDTOCols_choferDTO_usuarioRegistro");
			
			// Proyecciones catalogo tipo licencia
			projectionList.add(Projections.property("tipoLicenciaCatalogoValorDTO.nombreCatalogoValor"), "vehiculoChoferDTOCols_choferDTO_tipoLicenciaCatalogoValorDTO_nombreCatalogoValor");
						
			// Proyecciones entidad persona del chofer
			projectionList.add(Projections.property("personaDTO.id.codigoCompania"), "vehiculoChoferDTOCols_choferDTO_personaDTO_id_codigoCompania");
			projectionList.add(Projections.property("personaDTO.id.codigoPersona"), "vehiculoChoferDTOCols_choferDTO_personaDTO_id_codigoPersona");
			projectionList.add(Projections.property("personaDTO.numeroDocumento"), "vehiculoChoferDTOCols_choferDTO_personaDTO_numeroDocumento");
			projectionList.add(Projections.property("personaDTO.nombreCompleto"), "vehiculoChoferDTOCols_choferDTO_personaDTO_nombreCompleto");
						
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(VehiculoDTO.class));
			Collection<VehiculoDTO> vehiculoDTOCols = new  ArrayList<VehiculoDTO>();
			vehiculoDTOCols =  criteria.list();

			return vehiculoDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de vehiculos.").initCause(e);
		} 
	}
	
	/**
	 * M\u00e9todo para guardar y actualizar vehiculos
	 * @param vehiculoDTO
	 * @throws ERPException
	 */
	@Override
	public void guardarActualizarVehiculo(VehiculoDTO vehiculoDTO) throws ERPException{
		try{
			if (vehiculoDTO.getId().getCodigoCompania() == null || vehiculoDTO.getUsuarioRegistro() == null) {
				throw new ERPException("Error", "El c\u00F3digo de compania y el id de usuario registro es requerido");
			}	
			
			sessionFactory.getCurrentSession().clear();
			if(vehiculoDTO.getId().getCodigoVehiculo() ==  null){
				Integer secuencialVehiculo  = this.secuenciaDAO.obtenerSecuencialTabla(VehiculoID.NOMBRE_SECUENCIA);
				vehiculoDTO.getId().setCodigoVehiculo(Long.parseLong(""+secuencialVehiculo));
				vehiculoDTO.setFechaRegistro(new Date());
				vehiculoDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				sessionFactory.getCurrentSession().save(vehiculoDTO);
			}
			else
			{
				vehiculoDTO.setFechaModificacion(new Date());
				vehiculoDTO.setUsuarioModificacion(vehiculoDTO.getUsuarioRegistro());
				sessionFactory.getCurrentSession().update(vehiculoDTO);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar el vehiculo."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar el vehiculo."+e.getMessage());
		} 
	}

}
