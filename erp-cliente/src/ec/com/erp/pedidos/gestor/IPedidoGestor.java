package ec.com.erp.pedidos.gestor;

import java.sql.Timestamp;
import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.PedidoDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IPedidoGestor {
	
	/**
	 * M\u00e9todo para obtener lista de pedidos por filtros
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @param nombreCliente
	 * @param fechaInicio
	 * @param fechaFin
	 * @param estadoPedido
	 * @return
	 * @throws ERPException
	 */
	Collection<PedidoDTO> obtenerPedidosRegistrados(Integer codigoCompania, String numeroDocumento, String nombreCliente, Timestamp fechaInicio, Timestamp fechaFin, String estadoPedido) throws ERPException;
	
	/**
	 * M\u00e9todo para guardar pedido
	 * @param codigoCompania
	 * @param pedidoDTO
	 * @throws ERPException
	 */
	void guardarPedido(Integer codigoCompania, PedidoDTO pedidoDTO) throws ERPException;
}
