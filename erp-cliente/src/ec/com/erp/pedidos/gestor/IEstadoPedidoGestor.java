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
	
}
