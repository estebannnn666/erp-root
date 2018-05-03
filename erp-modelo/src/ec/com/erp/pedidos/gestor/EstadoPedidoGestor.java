package ec.com.erp.pedidos.gestor;

import java.util.Collection;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.EstadoPedidoDTO;
import ec.com.erp.pedidos.dao.IEstadoPedidoDAO;

public class EstadoPedidoGestor implements IEstadoPedidoGestor{

	private IEstadoPedidoDAO estadoPedidoDAO;
	
	public IEstadoPedidoDAO getEstadoPedidoDAO() {
		return estadoPedidoDAO;
	}

	public void setEstadoPedidoDAO(IEstadoPedidoDAO estadoPedidoDAO) {
		this.estadoPedidoDAO = estadoPedidoDAO;
	}

	/**
	 * M\u00E9todo para obtener el estado del pedido
	 * @param codigoCompania
	 * @param codigoPedido
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Collection<EstadoPedidoDTO> obtenerEstadoPedido(Integer codigoCompania, Long codigoPedido) throws ERPException{
		return this.estadoPedidoDAO.obtenerEstadoPedido(codigoCompania, codigoPedido);
	}
	
	/**
	 * M\u00e9todo para crear o actualizar estado pedido
	 * @param estadoPedidoDTO
	 * @param estado
	 * @throws ERPException
	 */
	@Override
	public void crearActualizarEstadoPedido(EstadoPedidoDTO estadoPedidoDTO, String estado) throws ERPException{ 
		estadoPedidoDTO.setCodigoTipoEstadoPedido(ERPConstantes.CODIGO_CATALOGO_TIPOS_ESTADO_PEDIDO);
		this.estadoPedidoDAO.crearActualizarEstadoPedido(estadoPedidoDTO);
	}
}
