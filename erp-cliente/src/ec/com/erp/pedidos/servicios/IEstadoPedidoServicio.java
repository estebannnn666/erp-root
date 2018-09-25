package ec.com.erp.pedidos.servicios;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.EstadoPedidoDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IEstadoPedidoServicio {
	
	/**
	 * M\u00E9todo para obtener el estado del pedido
	 * @param codigoCompania
	 * @param codigoPedido
	 * @return
	 * @throws ERPException
	 */
	Collection<EstadoPedidoDTO> findObtenerEstadoPedido(Integer codigoCompania, Long codigoPedido) throws ERPException;
	
	/**
	 * M\u00e9todo para actualizar el estado del pedido enviando el estado como parametro
	 * @param codigoCompania
	 * @param codigoPedido
	 * @param valorEstado
	 * @param userId
	 * @throws ERPException
	 */
	void transActualizarEstadoPorEstadoyPedido(Integer codigoCompania, Long codigoPedido, String valorEstado, String userId) throws ERPException;
}
