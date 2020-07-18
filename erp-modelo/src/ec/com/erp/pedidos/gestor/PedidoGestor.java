package ec.com.erp.pedidos.gestor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.DetallePedidoDTO;
import ec.com.erp.cliente.mdl.dto.EstadoPedidoDTO;
import ec.com.erp.cliente.mdl.dto.FacturaCabeceraDTO;
import ec.com.erp.cliente.mdl.dto.FacturaDetalleDTO;
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
		try {
			Collection<DetallePedidoDTO> detallePedidoDTOCols = pedidoDTO.getDetallePedidoDTOCols(); 
			pedidoDTO.getId().setCodigoCompania(codigoCompania);
			this.pedidoDAO.crearActualizarPedido(pedidoDTO);
			Collection<FacturaDetalleDTO> facturaDetalleDTOCol = new ArrayList<>();
			FacturaDetalleDTO facturaDetalleDTO;		
			for(DetallePedidoDTO detallePedidoDTO : detallePedidoDTOCols) {
				if(detallePedidoDTO.getCodigoArticulo() != null && detallePedidoDTO.getCantidad() != null && detallePedidoDTO.getSubTotal() != null) {
					detallePedidoDTO.getId().setCodigoCompania(codigoCompania);
					detallePedidoDTO.getId().setCodigoPedido(pedidoDTO.getId().getCodigoPedido());
					detallePedidoDTO.setUsuarioRegistro(pedidoDTO.getUsuarioRegistro());
					this.detallePedidoDAO.crearActualizarDetallePedido(detallePedidoDTO);
					facturaDetalleDTO = new FacturaDetalleDTO();
					facturaDetalleDTO.getId().setCodigoCompania(codigoCompania);
					facturaDetalleDTO.setArticuloDTO(detallePedidoDTO.getArticuloDTO());
					facturaDetalleDTO.setCodigoArticulo(detallePedidoDTO.getCodigoArticulo());
					facturaDetalleDTO.setDescripcion(detallePedidoDTO.getArticuloDTO().getNombreArticulo());
					facturaDetalleDTO.setCodigoBarras(detallePedidoDTO.getArticuloDTO().getCodigoBarras());
					facturaDetalleDTO.setCantidad(detallePedidoDTO.getCantidad());
					facturaDetalleDTO.setValorUnidad(detallePedidoDTO.getArticuloDTO().getPrecio());
					facturaDetalleDTO.setSubTotal(detallePedidoDTO.getSubTotal());
					facturaDetalleDTO.setFechaRegistro(new Date());
					facturaDetalleDTO.setUsuarioRegistro(pedidoDTO.getUsuarioRegistro());
					facturaDetalleDTOCol.add(facturaDetalleDTO);
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
			facturaCabeceraDTO.getId().setCodigoCompania(codigoCompania);
			facturaCabeceraDTO.setCodigoValorTipoDocumento(ERPConstantes.CODIGO_CATALOGO_VALOR_DOCUMENTO_VENTAS);
			facturaCabeceraDTO.setRucDocumento(pedidoDTO.getClienteDTO().getPersonaDTO() == null ? pedidoDTO.getClienteDTO().getEmpresaDTO().getNumeroRuc() : pedidoDTO.getClienteDTO().getPersonaDTO().getNumeroDocumento());
			facturaCabeceraDTO.setNombreClienteProveedor(pedidoDTO.getClienteDTO().getPersonaDTO() == null ? pedidoDTO.getClienteDTO().getEmpresaDTO().getRazonSocial() : pedidoDTO.getClienteDTO().getPersonaDTO().getNombreCompleto());
			facturaCabeceraDTO.setDireccion(pedidoDTO.getClienteDTO().getPersonaDTO() == null ? pedidoDTO.getClienteDTO().getEmpresaDTO().getContactoEmpresaDTO().getDireccionPrincipal() : pedidoDTO.getClienteDTO().getPersonaDTO().getContactoPersonaDTO().getDireccionPrincipal());
			facturaCabeceraDTO.setTelefono(pedidoDTO.getClienteDTO().getPersonaDTO() == null ? pedidoDTO.getClienteDTO().getEmpresaDTO().getContactoEmpresaDTO().getTelefonoPrincipal() : pedidoDTO.getClienteDTO().getPersonaDTO().getContactoPersonaDTO().getTelefonoPrincipal());
			facturaCabeceraDTO.setPagado(Boolean.FALSE);
			facturaCabeceraDTO.setFechaDocumento(new Date());
			facturaCabeceraDTO.setCodigoPedido(pedidoDTO.getId().getCodigoPedido());
			facturaCabeceraDTO.setUsuarioRegistro(pedidoDTO.getUsuarioRegistro());
			facturaCabeceraDTO.setTotalCuenta(pedidoDTO.getTotalCompra());
			facturaCabeceraDTO.setDescuento(pedidoDTO.getDescuento());
			facturaCabeceraDTO.setTotalImpuestos(pedidoDTO.getTotalImpuestos());
			facturaCabeceraDTO.setTotalSinImpuestos(pedidoDTO.getTotalSinImpuestos());
			facturaCabeceraDTO.setFacturaDetalleDTOCols(facturaDetalleDTOCol);		
			this.facturaCabeceraGestor.guardarActualizarFacturaCabecera(facturaCabeceraDTO);
		} catch (ERPException e) {
			throw new ERPException("Error, {0}",e.getMessage()) ;
		} catch (Exception e) {
			throw new ERPException("Error, {0}",e.getMessage());
		} 
	}
	
	
	
}
