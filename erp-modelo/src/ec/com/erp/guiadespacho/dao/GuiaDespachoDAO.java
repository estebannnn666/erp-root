/**
 * 
 */
package ec.com.erp.guiadespacho.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.GuiaDespachoDTO;
import ec.com.erp.cliente.mdl.dto.id.GuiaDespachoID;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2018-05-05
 */
public class GuiaDespachoDAO implements IGuiaDespachoDAO {

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
	 * M\u00e9todo para obtener lista de despachos
	 * @param codigoCompania
	 * @param numeroGuia
	 * @param fechaDespachoInicio
	 * @param fechaDespachoFin
	 * @param placa
	 * @param documentoChofer
	 * @param nombreChofer
	 * @return
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<GuiaDespachoDTO> obtenerListaDespachosByFiltrosBusqueda(Integer codigoCompania, String numeroGuia, Timestamp fechaDespachoInicio, Timestamp fechaDespachoFin, String placa, String documentoChofer, String nombreChofer) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(GuiaDespachoDTO.class, "root");
			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			if(numeroGuia != null && numeroGuia.trim() !=""){
				criteria.add(Restrictions.eq("root.numeroGuiaDespacho", numeroGuia));
			}
			
			if(fechaDespachoInicio != null && fechaDespachoFin != null){
				criteria.add(Restrictions.between("root.fechaDespacho", fechaDespachoInicio, fechaDespachoFin));
			}
			
			if(placa != null && placa.trim() !=""){
				criteria.add(Restrictions.eq("root.placa", placa));
			}
			if(documentoChofer != null && documentoChofer.trim() !=""){
				criteria.add(Restrictions.eq("root.documentoChoferA", documentoChofer));
			}
			if(nombreChofer != null && nombreChofer.trim() !=""){
				criteria.add(Restrictions.eq("root.nombreChoferA", nombreChofer));
			}
			
			// Proyecciones entidad clientes 
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoGuiaDespacho"), "id_codigoGuiaDespacho");
			projectionList.add(Projections.property("root.numeroGuiaDespacho"), "numeroGuiaDespacho");
			projectionList.add(Projections.property("root.fechaDespacho"), "fechaDespacho");
			projectionList.add(Projections.property("root.placa"), "placa");
			projectionList.add(Projections.property("root.marca"), "marca");
			projectionList.add(Projections.property("root.rucTransportista"), "rucTransportista");
			projectionList.add(Projections.property("root.nombreTransportista"), "nombreTransportista");
			projectionList.add(Projections.property("root.documentoChoferA"), "documentoChoferA");
			projectionList.add(Projections.property("root.nombreChoferA"), "nombreChoferA");
			projectionList.add(Projections.property("root.documentoChoferB"), "documentoChoferB");
			projectionList.add(Projections.property("root.nombreChoferB"), "nombreChoferB");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			criteria.setProjection(projectionList);
			criteria.addOrder(Order.desc("root.id.codigoGuiaDespacho"));
			criteria.setResultTransformer(new MultiLevelResultTransformer(GuiaDespachoDTO.class));
			Collection<GuiaDespachoDTO> guiaDespachoDTOCols = new  ArrayList<GuiaDespachoDTO>();
			guiaDespachoDTOCols =  criteria.list();

			return guiaDespachoDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de guias de despachos.").initCause(e);
		} 
	}
	
	/**
	 * M\u00e9todo para guardar y actualizar guia despacho
	 * @param guiaDespachoDTO
	 * @throws ERPException
	 */
	@Override
	public void crearActualizarGuiaDespacho(GuiaDespachoDTO guiaDespachoDTO) throws ERPException{
		try{
			if (guiaDespachoDTO.getId().getCodigoCompania() == null || guiaDespachoDTO.getUsuarioRegistro() == null) {
				throw new ERPException("Error", "El c\u00F3digo de compania y el id de usuario registro es requerido");
			}	
			
			sessionFactory.getCurrentSession().clear();
			if(guiaDespachoDTO.getId().getCodigoGuiaDespacho() ==  null){
				Integer secuencialGuia  = this.secuenciaDAO.obtenerSecuencialTabla(GuiaDespachoID.NOMBRE_SECUENCIA);
				guiaDespachoDTO.getId().setCodigoGuiaDespacho(Long.parseLong(""+secuencialGuia));
				guiaDespachoDTO.setFechaRegistro(new Date());
				guiaDespachoDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				sessionFactory.getCurrentSession().save(guiaDespachoDTO);
			}
			else
			{
				guiaDespachoDTO.setFechaModificacion(new Date());
				guiaDespachoDTO.setUsuarioModificacion(guiaDespachoDTO.getUsuarioRegistro());
				sessionFactory.getCurrentSession().update(guiaDespachoDTO);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar guia de despacho."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar guia de despacho."+e.getMessage());
		} 
	}

}
