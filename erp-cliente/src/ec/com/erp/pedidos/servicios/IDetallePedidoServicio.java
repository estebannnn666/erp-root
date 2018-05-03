package ec.com.erp.pedidos.servicios;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.DetallePedidoDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IDetallePedidoServicio {
	
	/**
	 *  M\u00E9todo para obtener los detalles por pedido enviado
	 * @param codigoCompania
	 * @param codigoPedido
	 * @return
	 * @throws ERPException
	 */
	Collection<DetallePedidoDTO> findObtenerDetallePedidoByPedido(Integer codigoCompania, Long codigoPedido) throws ERPException;
	
}
