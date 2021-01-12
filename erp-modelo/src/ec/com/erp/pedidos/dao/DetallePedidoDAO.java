/**
 * 
 */
package ec.com.erp.pedidos.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.DetallePedidoDTO;
import ec.com.erp.cliente.mdl.dto.id.DetallePedidoID;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2017-06-27
 */
public class DetallePedidoDAO implements IDetallePedidoDAO {

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
	 *  M\u00E9todo para obtener los detalles por pedido enviado
	 * @param codigoCompania
	 * @param codigoPedido
	 * @return
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<DetallePedidoDTO> obtenerDetallePedidoByPedido(Integer codigoCompania, Long codigoPedido) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(DetallePedidoDTO.class, "root");

			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.id.codigoPedido", codigoPedido));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));

			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoDetallePedido"), "id_codigoDetallePedido");
			projectionList.add(Projections.property("root.id.codigoPedido"), "id_codigoPedido");
			projectionList.add(Projections.property("root.codigoArticulo"), "codigoArticulo");
			projectionList.add(Projections.property("root.codigoArticuloUnidadManejo"), "codigoArticuloUnidadManejo");
			projectionList.add(Projections.property("root.cantidad"), "cantidad");
			projectionList.add(Projections.property("root.subTotal"), "subTotal");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(DetallePedidoDTO.class));
			Collection<DetallePedidoDTO> detallePedidoDTOCols = new  ArrayList<DetallePedidoDTO>();
			detallePedidoDTOCols =  criteria.list();

			return detallePedidoDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de convenios con diseniadores.").initCause(e);
		} 
	}

	/**
	 * M\u00e9todo para crear o actualizar detalle Pedido
	 * @param detallePedidoDTO
	 * @throws ERPException
	 */
	public void crearActualizarDetallePedido(DetallePedidoDTO detallePedidoDTO) throws ERPException{
		try{
			if (detallePedidoDTO.getId().getCodigoCompania() == null || detallePedidoDTO.getUsuarioRegistro() == null) {
				throw new ERPException("Error", "El c\u00F3digo de compania y el id de usuario registro es requerido");
			}	
			
			sessionFactory.getCurrentSession().clear();
			if(detallePedidoDTO.getId().getCodigoDetallePedido() ==  null){
				Integer secuencialPedido  = this.secuenciaDAO.obtenerSecuencialTabla(DetallePedidoID.NOMBRE_SECUENCIA);
				detallePedidoDTO.getId().setCodigoDetallePedido(Long.parseLong(""+secuencialPedido));
				detallePedidoDTO.setFechaRegistro(new Date());
				detallePedidoDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				sessionFactory.getCurrentSession().save(detallePedidoDTO);
			}
			else
			{
				detallePedidoDTO.setFechaModificacion(new Date());
				detallePedidoDTO.setUsuarioModificacion(detallePedidoDTO.getUsuarioRegistro());
				sessionFactory.getCurrentSession().update(detallePedidoDTO);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar el detalle del pedido.");
		} catch (Exception e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar el detalle del pedido.");
		} 
	}
}
