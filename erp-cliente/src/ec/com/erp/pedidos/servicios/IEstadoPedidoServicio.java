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
	
}
