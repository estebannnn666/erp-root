package ec.com.erp.pedidos.gestor;

import java.util.Collection;
import java.util.Date;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.EstadoPedidoDTO;
import ec.com.erp.cliente.mdl.dto.FacturaCabeceraDTO;
import ec.com.erp.cliente.mdl.dto.PedidoDTO;
import ec.com.erp.factura.gestor.IFacturaCabeceraGestor;
import ec.com.erp.pedidos.dao.IEstadoPedidoDAO;
import ec.com.erp.pedidos.dao.IPedidoDAO;

public class EstadoPedidoGestor implements IEstadoPedidoGestor{

	private IEstadoPedidoDAO estadoPedidoDAO;
	private IFacturaCabeceraGestor facturaCabeceraGestor;
	private IPedidoDAO pedidoDAO;
	
	public IEstadoPedidoDAO getEstadoPedidoDAO() {
		return estadoPedidoDAO;
	}

	public void setEstadoPedidoDAO(IEstadoPedidoDAO estadoPedidoDAO) {
		this.estadoPedidoDAO = estadoPedidoDAO;
	}
	
	public IFacturaCabeceraGestor getFacturaCabeceraGestor() {
		return facturaCabeceraGestor;
	}

	public void setFacturaCabeceraGestor(IFacturaCabeceraGestor facturaCabeceraGestor) {
		this.facturaCabeceraGestor = facturaCabeceraGestor;
	}

	public IPedidoDAO getPedidoDAO() {
		return pedidoDAO;
	}

	public void setPedidoDAO(IPedidoDAO pedidoDAO) {
		this.pedidoDAO = pedidoDAO;
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
	
	/**
	 * M\u00e9todo para actualizar el estado del pedido enviando el estado como parametro
	 * @param codigoCompania
	 * @param codigoPedido
	 * @param valorEstado
	 * @param userId
	 * @throws ERPException
	 */
	@Override
	public void actualizarEstadoPorEstadoyPedido(Integer codigoCompania, Long codigoPedido, String valorEstado, String userId) throws ERPException{
		try {
			Collection<EstadoPedidoDTO> estadoPedidoActualCol = this.estadoPedidoDAO.obtenerEstadoPedido(codigoCompania, codigoPedido);
			EstadoPedidoDTO estadoPedidoDTOActual = estadoPedidoActualCol.iterator().next();
			estadoPedidoDTOActual.setFechaFin(new Date());
			this.estadoPedidoDAO.crearActualizarEstadoPedido(estadoPedidoDTOActual);
			EstadoPedidoDTO estadoPedidoDTONuevo = new EstadoPedidoDTO();
			estadoPedidoDTONuevo.getId().setCodigoCompania(codigoCompania);
			estadoPedidoDTONuevo.getId().setCodigoPedido(codigoPedido);
			estadoPedidoDTONuevo.setCodigoTipoEstadoPedido(ERPConstantes.CODIGO_CATALOGO_TIPOS_ESTADO_PEDIDO);
			estadoPedidoDTONuevo.setCodigoValorEstadoPedido(valorEstado);
			estadoPedidoDTONuevo.setFechaInicio(new Date());
			estadoPedidoDTONuevo.setFechaFin(null);
			estadoPedidoDTONuevo.setUsuarioRegistro(userId);
			this.estadoPedidoDAO.crearActualizarEstadoPedido(estadoPedidoDTONuevo);
			if(valorEstado.equals(ERPConstantes.CODIGO_CATALOGO_VALOR_ESTADO_PEDIDO_ENTREGADO)) {
				PedidoDTO pedidoDTO = this.pedidoDAO.obtenerPedidoPorCodigo(codigoCompania, codigoPedido);
				pedidoDTO.setFechaEntrega(new Date());
				this.pedidoDAO.crearActualizarPedido(pedidoDTO);
			}
			if(valorEstado.equals(ERPConstantes.CODIGO_CATALOGO_VALOR_ESTADO_PEDIDO_CANCELADO)) {
				FacturaCabeceraDTO facturaCabeceraDTO = this.facturaCabeceraGestor.obtenerFacturaPedido(codigoCompania, codigoPedido);
				this.facturaCabeceraGestor.cancelarFacturaInactivar(facturaCabeceraDTO);
			}
		} catch (Exception e) {
			throw new ERPException("Error", e.getMessage());
		}
	}
}
