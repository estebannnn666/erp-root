package ec.com.erp.pedidos.servicios;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.EstadoPedidoDTO;
import ec.com.erp.pedidos.gestor.IEstadoPedidoGestor;

public class EstadoPedidoServicio implements IEstadoPedidoServicio{
	
	private IEstadoPedidoGestor estadoPedidoGestor;

	public IEstadoPedidoGestor getEstadoPedidoGestor() {
		return estadoPedidoGestor;
	}

	public void setEstadoPedidoGestor(IEstadoPedidoGestor estadoPedidoGestor) {
		this.estadoPedidoGestor = estadoPedidoGestor;
	}

	/**
	 * M\u00E9todo para obtener el estado del pedido
	 * @param codigoCompania
	 * @param codigoPedido
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Collection<EstadoPedidoDTO> findObtenerEstadoPedido(Integer codigoCompania, Long codigoPedido) throws ERPException{
		return this.estadoPedidoGestor.obtenerEstadoPedido(codigoCompania, codigoPedido);
	}

}
