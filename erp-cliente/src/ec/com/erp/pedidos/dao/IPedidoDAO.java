package ec.com.erp.pedidos.dao;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.PedidoDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IPedidoDAO {
	
	/**
	 *  M\u00e9todo para obtener lista de pedidos
	 * @param codigoCompania
	 * @param estadoPedido
	 * @return
	 * @throws ERPException
	 */
	Collection<PedidoDTO> obtenerPedidosRegistrados(Integer codigoCompania, String estadoPedido) throws ERPException;
	
	/**
	 * M\u00e9todo para crear o actualizar personas
	 * @param personaDTO
	 * @throws ERPException
	 */
	void crearActualizarPedido(PedidoDTO pedidoDTO) throws ERPException;
}
