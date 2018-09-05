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
import ec.com.erp.cliente.mdl.dto.EstadoPedidoDTO;
import ec.com.erp.cliente.mdl.dto.id.EstadoPedidoID;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2017-06-27
 */
public class EstadoPedidoDAO implements IEstadoPedidoDAO {

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
	 * M\u00E9todo para obtener el estado del pedido
	 * @param codigoCompania
	 * @param codigoPedido
	 * @return
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<EstadoPedidoDTO> obtenerEstadoPedido(Integer codigoCompania, Long codigoPedido) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(EstadoPedidoDTO.class, "root");

			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.id.codigoPedido", codigoPedido));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			criteria.add(Restrictions.isNull("root.fechaFin"));

			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoEstadoPedido"), "id_codigoEstadoPedido");
			projectionList.add(Projections.property("root.id.codigoPedido"), "id_codigoPedido");
			projectionList.add(Projections.property("root.codigoValorEstadoPedido"), "codigoValorEstadoPedido");
			projectionList.add(Projections.property("root.codigoTipoEstadoPedido"), "codigoTipoEstadoPedido");
			projectionList.add(Projections.property("root.fechaInicio"), "fechaInicio");
			projectionList.add(Projections.property("root.fechaFin"), "fechaFin");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(EstadoPedidoDTO.class));
			Collection<EstadoPedidoDTO> estadoPedidoDTOCols = new  ArrayList<EstadoPedidoDTO>();
			estadoPedidoDTOCols =  criteria.list();

			return estadoPedidoDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de convenios con diseniadores.").initCause(e);
		} 
	}

	/**
	 * M\u00e9todo para crear o actualizar estado pedido
	 * @param personaDTO
	 * @throws ERPException
	 */
	@Override
	public void crearActualizarEstadoPedido(EstadoPedidoDTO estadoPedidoDTO) throws ERPException{
		try{
			if (estadoPedidoDTO.getId().getCodigoCompania() == null || estadoPedidoDTO.getUsuarioRegistro() == null) {
				throw new ERPException("El c\u00F3digo de compania y el id de usuario registro es requerido");
			}	
			
			sessionFactory.getCurrentSession().clear();
			if(estadoPedidoDTO.getId().getCodigoEstadoPedido() ==  null){
				Integer secuencialEstadoPedido  = this.secuenciaDAO.obtenerSecuencialTabla(EstadoPedidoID.NOMBRE_SECUENCIA);
				estadoPedidoDTO.getId().setCodigoEstadoPedido(Long.parseLong(""+secuencialEstadoPedido));
				estadoPedidoDTO.setFechaRegistro(new Date());
				estadoPedidoDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				sessionFactory.getCurrentSession().save(estadoPedidoDTO);
			}
			else
			{
				estadoPedidoDTO.setFechaModificacion(new Date());
				estadoPedidoDTO.setUsuarioModificacion(estadoPedidoDTO.getUsuarioRegistro());
				sessionFactory.getCurrentSession().update(estadoPedidoDTO);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Ocurrio un error al guardar o actualizar el estado del pedido.");
		} catch (Exception e) {
			throw new ERPException("Ocurrio un error al guardar o actualizar el estado del pedido.");
		} 
	}
}
