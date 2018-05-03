package ec.com.erp.pedidos.servicios;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.DetallePedidoDTO;
import ec.com.erp.pedidos.gestor.IDetallePedidoGestor;

public class DetallePedidoServicio implements IDetallePedidoServicio{
	
	private IDetallePedidoGestor detallePedidoGestor;

	public IDetallePedidoGestor getDetallePedidoGestor() {
		return detallePedidoGestor;
	}

	public void setDetallePedidoGestor(IDetallePedidoGestor detallePedidoGestor) {
		this.detallePedidoGestor = detallePedidoGestor;
	}

	/**
	 *  M\u00E9todo para obtener los detalles por pedido enviado
	 * @param codigoCompania
	 * @param codigoPedido
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Collection<DetallePedidoDTO> findObtenerDetallePedidoByPedido(Integer codigoCompania, Long codigoPedido) throws ERPException{
		return this.detallePedidoGestor.obtenerDetallePedidoByPedido(codigoCompania, codigoPedido);
	}

}
