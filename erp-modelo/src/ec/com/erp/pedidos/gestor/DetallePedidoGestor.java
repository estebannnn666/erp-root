package ec.com.erp.pedidos.gestor;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.DetallePedidoDTO;
import ec.com.erp.pedidos.dao.IDetallePedidoDAO;

public class DetallePedidoGestor implements IDetallePedidoGestor{

	private IDetallePedidoDAO detallePedidoDAO;
	
	public IDetallePedidoDAO getDetallePedidoDAO() {
		return detallePedidoDAO;
	}

	public void setDetallePedidoDAO(IDetallePedidoDAO detallePedidoDAO) {
		this.detallePedidoDAO = detallePedidoDAO;
	}

	/**
	 *  M\u00E9todo para obtener los detalles por pedido enviado
	 * @param codigoCompania
	 * @param codigoPedido
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Collection<DetallePedidoDTO> obtenerDetallePedidoByPedido(Integer codigoCompania, Long codigoPedido) throws ERPException{
		return this.detallePedidoDAO.obtenerDetallePedidoByPedido(codigoCompania, codigoPedido);
	}
}
