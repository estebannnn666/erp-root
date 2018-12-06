package ec.com.erp.pedidos.servicios;

import java.sql.Timestamp;
import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.PedidoDTO;
import ec.com.erp.pedidos.gestor.IPedidoGestor;

public class PedidoServicio implements IPedidoServicio{
	
	private IPedidoGestor pedidoGestor;

	public IPedidoGestor getPedidoGestor() {
		return pedidoGestor;
	}

	public void setPedidoGestor(IPedidoGestor pedidoGestor) {
		this.pedidoGestor = pedidoGestor;
	}

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
	@Override
	public Collection<PedidoDTO> findObtenerPedidosRegistrados(Integer codigoCompania, String numeroDocumento, String nombreCliente, Timestamp fechaInicio, Timestamp fechaFin, String estadoPedido) throws ERPException{
		return this.pedidoGestor.obtenerPedidosRegistrados(codigoCompania, numeroDocumento, nombreCliente, fechaInicio, fechaFin, estadoPedido);
	}

	/**
	 * M\u00e9todo para guardar pedido
	 * @param codigoCompania
	 * @param pedidoDTO
	 * @throws ERPException
	 */
	@Override
	public void transGuardarPedido(Integer codigoCompania, PedidoDTO pedidoDTO)throws ERPException{
		this.pedidoGestor.guardarPedido(codigoCompania, pedidoDTO);
	}
	
}
