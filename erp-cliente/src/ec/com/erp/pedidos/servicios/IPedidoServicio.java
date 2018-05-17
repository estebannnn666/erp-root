package ec.com.erp.pedidos.servicios;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.PedidoDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IPedidoServicio {
	
	/**
	 *  M\u00e9todo para obtener lista de pedidos
	 * @param codigoCompania
	 * @param estadoPedido
	 * @return
	 * @throws ERPException
	 */
	Collection<PedidoDTO> findObtenerPedidosRegistrados(Integer codigoCompania, String estadoPedido) throws ERPException;
	
	/**
	 * M\u00e9todo para guardar pedido
	 * @param codigoCompania
	 * @param pedidoDTO
	 * @throws ERPException
	 */
	void transGuardarPedido(Integer codigoCompania, PedidoDTO pedidoDTO)throws ERPException;
	
}
