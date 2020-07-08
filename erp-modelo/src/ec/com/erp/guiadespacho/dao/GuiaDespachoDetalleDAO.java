/**
 * 
 */
package ec.com.erp.guiadespacho.dao;

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
import ec.com.erp.cliente.mdl.dto.GuiaDespachoDetalleDTO;
import ec.com.erp.cliente.mdl.dto.id.GuiaDespachoDetalleID;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2018-05-05
 */
public class GuiaDespachoDetalleDAO implements IGuiaDespachoDetalleDAO {

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
	 * M\u00e9todo para obtener lista de detalles en guia de despacho
	 * @param codigoCompania
	 * @param numeroGuia
	 * @return Collection<GuiaDespachoDetalleDTO>
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<GuiaDespachoDetalleDTO> obtenerListaGuiaDespachoDetalleByNumeroGuia(Integer codigoCompania, String numeroGuia) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(GuiaDespachoDetalleDTO.class, "root");
			criteria.createAlias("root.guiaDespachoDTO", "guiaDespachoDTO", CriteriaSpecification.INNER_JOIN);
			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			criteria.add(Restrictions.eq("guiaDespachoDTO.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));

			if(numeroGuia != null) {
				criteria.add(Restrictions.eq("guiaDespachoDTO.numeroGuiaDespacho", numeroGuia));
			}
			
			// Proyecciones entidad clientes 
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoGuiaDespachoDetalle"), "id_codigoGuiaDespachoDetalle");
			projectionList.add(Projections.property("root.codigoGuiaDespacho"), "codigoGuiaDespacho");
			projectionList.add(Projections.property("root.codigoArticulo"), "codigoArticulo");
			projectionList.add(Projections.property("root.descripcionProducto"), "descripcionProducto");
			projectionList.add(Projections.property("root.cantidad"), "cantidad");
			projectionList.add(Projections.property("root.observacion"), "observacion");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(GuiaDespachoDetalleDTO.class));
			Collection<GuiaDespachoDetalleDTO> guiaDespachoDetalleDTOCols = new  ArrayList<GuiaDespachoDetalleDTO>();
			guiaDespachoDetalleDTOCols =  criteria.list();

			return guiaDespachoDetalleDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de detalles de la guia despacho.").initCause(e);
		} 
	}
	
	/**
	 * M\u00e9todo para guardar y actualizar detalle de la guia despacho
	 * @param guiaDespachoDetalleDTO
	 * @throws ERPException
	 */
	@Override
	public void crearActualizarDetalleGuiaDespacho(GuiaDespachoDetalleDTO guiaDespachoDetalleDTO) throws ERPException{
		try{
			if (guiaDespachoDetalleDTO.getId().getCodigoCompania() == null || guiaDespachoDetalleDTO.getUsuarioRegistro() == null) {
				throw new ERPException("El c\u00F3digo de compania y el id de usuario registro es requerido");
			}	
			
			sessionFactory.getCurrentSession().clear();
			if(guiaDespachoDetalleDTO.getId().getCodigoGuiaDespachoDetalle() ==  null){
				Integer secuencialGuiaDespachoDetalle  = this.secuenciaDAO.obtenerSecuencialTabla(GuiaDespachoDetalleID.NOMBRE_SECUENCIA);
				guiaDespachoDetalleDTO.getId().setCodigoGuiaDespachoDetalle(Long.parseLong(""+secuencialGuiaDespachoDetalle));
				guiaDespachoDetalleDTO.setFechaRegistro(new Date());
				guiaDespachoDetalleDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				sessionFactory.getCurrentSession().save(guiaDespachoDetalleDTO);
			}
			else
			{
				guiaDespachoDetalleDTO.setFechaModificacion(new Date());
				guiaDespachoDetalleDTO.setUsuarioModificacion(guiaDespachoDetalleDTO.getUsuarioRegistro());
				sessionFactory.getCurrentSession().update(guiaDespachoDetalleDTO);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Ocurrio un error al guardar o actualizar art\u00EDculos detalle en la gu\u00EDa de despacho."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Ocurrio un error al guardar o actualizar art\u00EDculos detalle en la gu\u00EDa de despacho."+e.getMessage());
		} 
	}

}
