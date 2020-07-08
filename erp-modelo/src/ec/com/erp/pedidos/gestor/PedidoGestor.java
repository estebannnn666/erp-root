package ec.com.erp.pedidos.gestor;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.DetallePedidoDTO;
import ec.com.erp.cliente.mdl.dto.EstadoPedidoDTO;
import ec.com.erp.cliente.mdl.dto.FacturaCabeceraDTO;
import ec.com.erp.cliente.mdl.dto.PedidoDTO;
import ec.com.erp.factura.gestor.IFacturaCabeceraGestor;
import ec.com.erp.pedidos.dao.IDetallePedidoDAO;
import ec.com.erp.pedidos.dao.IEstadoPedidoDAO;
import ec.com.erp.pedidos.dao.IPedidoDAO;

public class PedidoGestor implements IPedidoGestor{

	private IPedidoDAO pedidoDAO;
	private IDetallePedidoDAO detallePedidoDAO;
	private IEstadoPedidoDAO estadoPedidoDAO;
	private IFacturaCabeceraGestor facturaCabeceraGestor;
	
	public IPedidoDAO getPedidoDAO() {
		return pedidoDAO;
	}

	public void setPedidoDAO(IPedidoDAO pedidoDAO) {
		this.pedidoDAO = pedidoDAO;
	}

	public IDetallePedidoDAO getDetallePedidoDAO() {
		return detallePedidoDAO;
	}

	public void setDetallePedidoDAO(IDetallePedidoDAO detallePedidoDAO) {
		this.detallePedidoDAO = detallePedidoDAO;
	}

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
	public Collection<PedidoDTO> obtenerPedidosRegistrados(Integer codigoCompania, String numeroDocumento, String nombreCliente, Timestamp fechaInicio, Timestamp fechaFin, String estadoPedido) throws ERPException{
		return this.pedidoDAO.obtenerPedidosRegistrados(codigoCompania, numeroDocumento, nombreCliente, fechaInicio, fechaFin, estadoPedido);
	}
	
	/**
	 * M\u00e9todo para guardar pedido
	 * @param codigoCompania
	 * @param pedidoDTO
	 * @throws ERPException
	 */
	@Override
	public void guardarPedido(Integer codigoCompania, PedidoDTO pedidoDTO) throws ERPException{
		Collection<DetallePedidoDTO> detallePedidoDTOCols = pedidoDTO.getDetallePedidoDTOCols(); 
		pedidoDTO.getId().setCodigoCompania(codigoCompania);
		this.pedidoDAO.crearActualizarPedido(pedidoDTO);
		for(DetallePedidoDTO detallePedidoDTO : detallePedidoDTOCols) {
			if(detallePedidoDTO.getCodigoArticulo() != null && detallePedidoDTO.getCantidad() != null && detallePedidoDTO.getSubTotal() != null) {
				detallePedidoDTO.getId().setCodigoCompania(codigoCompania);
				detallePedidoDTO.getId().setCodigoPedido(pedidoDTO.getId().getCodigoPedido());
				detallePedidoDTO.setArticuloDTO(null);
				detallePedidoDTO.setUsuarioRegistro(pedidoDTO.getUsuarioRegistro());
				this.detallePedidoDAO.crearActualizarDetallePedido(detallePedidoDTO);
			}
		}
		EstadoPedidoDTO estadoPedidoDTO = null;
		if(pedidoDTO.getEstadoPedidoDTO() == null) {
			estadoPedidoDTO = new EstadoPedidoDTO();
		}
		else {
			estadoPedidoDTO = pedidoDTO.getEstadoPedidoDTO();
		}
		estadoPedidoDTO.getId().setCodigoCompania(codigoCompania);
		estadoPedidoDTO.getId().setCodigoPedido(pedidoDTO.getId().getCodigoPedido());
		estadoPedidoDTO.setCodigoTipoEstadoPedido(ERPConstantes.CODIGO_CATALOGO_TIPOS_ESTADO_PEDIDO);
		estadoPedidoDTO.setCodigoValorEstadoPedido(ERPConstantes.CODIGO_CATALOGO_VALOR_ESTADO_PEDIDO_REGISTRADO);
		estadoPedidoDTO.setFechaInicio(new Date());
		estadoPedidoDTO.setFechaFin(null);
		estadoPedidoDTO.setUsuarioRegistro(pedidoDTO.getUsuarioRegistro());
		this.estadoPedidoDAO.crearActualizarEstadoPedido(estadoPedidoDTO);
		
		// Crear y guardar factura
		FacturaCabeceraDTO facturaCabeceraDTO = new FacturaCabeceraDTO();
		facturaCabeceraDTO.setCodigoValorTipoDocumento(ERPConstantes.CODIGO_CATALOGO_VALOR_DOCUMENTO_VENTAS);
		facturaCabeceraDTO.setNumeroDocumento(pedidoDTO.getClienteDTO().getPersonaDTO() == null ? pedidoDTO.getClienteDTO().getEmpresaDTO().getNumeroRuc() : pedidoDTO.getClienteDTO().getPersonaDTO().getNumeroDocumento());
		this.facturaCabeceraGestor.guardarActualizarFacturaCabecera(facturaCabeceraDTO);
	}
	
	
}
