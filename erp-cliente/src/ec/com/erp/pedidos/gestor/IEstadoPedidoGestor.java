package ec.com.erp.pedidos.gestor;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.EstadoPedidoDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IEstadoPedidoGestor {
	
	/**
	 * M\u00E9todo para obtener el estado del pedido
	 * @param codigoCompania
	 * @param codigoPedido
	 * @return
	 * @throws ERPException
	 */
	Collection<EstadoPedidoDTO> obtenerEstadoPedido(Integer codigoCompania, Long codigoPedido) throws ERPException;
	
	
	/**
	 * M\u00e9todo para crear o actualizar estado pedido
	 * @param estadoPedidoDTO
	 * @param estado
	 * @throws ERPException
	 */
	void crearActualizarEstadoPedido(EstadoPedidoDTO estadoPedidoDTO, String estado) throws ERPException;
	
	/**
	 * M\u00e9todo para actualizar el estado del pedido enviando el estado como parametro
	 * @param codigoCompania
	 * @param codigoPedido
	 * @param valorEstado
	 * @param userId
	 * @throws ERPException
	 */
	void actualizarEstadoPorEstadoyPedido(Integer codigoCompania, Long codigoPedido, String valorEstado, String userId) throws ERPException;
	
}
