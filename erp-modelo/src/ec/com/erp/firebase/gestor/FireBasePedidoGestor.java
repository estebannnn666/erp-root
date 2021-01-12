package ec.com.erp.firebase.gestor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

import org.apache.commons.collections.CollectionUtils;

import ec.com.erp.articulo.gestor.IArticuloGestor;
import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ArticuloDTO;
import ec.com.erp.cliente.mdl.dto.ArticuloUnidadManejoDTO;
import ec.com.erp.cliente.mdl.dto.ClienteDTO;
import ec.com.erp.cliente.mdl.dto.DetallePedidoDTO;
import ec.com.erp.cliente.mdl.dto.PedidoDTO;
import ec.com.erp.cliente.mdl.dto.VendedorDTO;
import ec.com.erp.clientes.gestor.IClientesGestor;
import ec.com.erp.firebase.commons.provider.OrderProvider;
import ec.com.erp.firebase.model.DetailOrder;
import ec.com.erp.firebase.model.Order;
import ec.com.erp.pedidos.gestor.IPedidoGestor;
import ec.com.erp.vendedor.gestor.IVendedorGestor;

public class FireBasePedidoGestor implements IFireBasePedidoGestor {

	private IPedidoGestor pedidoGestor;
	private IVendedorGestor vendedorGestor;
	private IArticuloGestor articuloGestor;
	private IClientesGestor clientesGestor;
	
	public IPedidoGestor getPedidoGestor() {
		return pedidoGestor;
	}

	public void setPedidoGestor(IPedidoGestor pedidoGestor) {
		this.pedidoGestor = pedidoGestor;
	}

	public IVendedorGestor getVendedorGestor() {
		return vendedorGestor;
	}

	public void setVendedorGestor(IVendedorGestor vendedorGestor) {
		this.vendedorGestor = vendedorGestor;
	}
	
	public IArticuloGestor getArticuloGestor() {
		return articuloGestor;
	}

	public void setArticuloGestor(IArticuloGestor articuloGestor) {
		this.articuloGestor = articuloGestor;
	}
	
	public IClientesGestor getClientesGestor() {
		return clientesGestor;
	}

	public void setClientesGestor(IClientesGestor clientesGestor) {
		this.clientesGestor = clientesGestor;
	}

	/**
	 * M\u00e9todo para descargar los ARTICULOS de fire base
	 * @return 
	 * @throws ERPException
	 */
	public void descargarPedidosFireBase() throws ERPException{
		try {
			Collection<Order> ordenesFireBase = OrderProvider.obtainOrdersFirebase();
			Collection<PedidoDTO> pedidosDTOCols = this.pedidoGestor.obtenerPedidosRegistrados(ERPConstantes.CODIGO_COMPANIA, null, null, null, null, null);
			
			ordenesFireBase.stream().forEach(facFireBase ->{
				PedidoDTO pedidoDTOLocal = pedidosDTOCols.stream()
						.filter(pedidoDTO -> pedidoDTO.getCodigoReferencia() != null && pedidoDTO.getCodigoReferencia().intValue() == facFireBase.getHeader().getIdOrder().intValue())
						.findFirst().orElse(null);
				
				if(pedidoDTOLocal == null) {
					PedidoDTO pedidoDTO = new PedidoDTO();
					pedidoDTO.getId().setCodigoCompania(ERPConstantes.CODIGO_COMPANIA);
					pedidoDTO.setUsuarioRegistro(ERPConstantes.USUARIO_GENERICO);
					ClienteDTO clienteDTO = this.clientesGestor.obtenerClienteByCodigo(ERPConstantes.CODIGO_COMPANIA, facFireBase.getHeader().getClientDocument(), null);
					if(clienteDTO != null && clienteDTO.getId().getCodigoCliente() != null) {
						pedidoDTO.setCodigoCliente(clienteDTO.getId().getCodigoCliente());
						pedidoDTO.setClienteDTO(clienteDTO);
					}else {
						throw new ERPException("Error", "El cliente con documento "+facFireBase.getHeader().getClientDocument()+" no se encuentra registrado en el sistema.");
					}
					pedidoDTO.setCodigoReferencia(Long.parseLong(""+facFireBase.getHeader().getIdOrder()));
					pedidoDTO.setFechaPedido(facFireBase.getHeader().getOrderDate());
					pedidoDTO.setFechaEntrega(facFireBase.getHeader().getDeliveryDate());
					// Obtener datos del vendedor si existe
					VendedorDTO vendedorDTO = this.vendedorGestor.obtenerVendedor(ERPConstantes.CODIGO_COMPANIA, facFireBase.getHeader().getUserId());
					if(vendedorDTO != null && vendedorDTO.getId().getCodigoVendedor() != null) {
						pedidoDTO.setCodigoVendedor(vendedorDTO.getId().getCodigoVendedor());
					}
					pedidoDTO.setDescuento(facFireBase.getHeader().getDiscount());
					pedidoDTO.setSubTotal(facFireBase.getHeader().getSubTotal());
					pedidoDTO.setTotalCompra(facFireBase.getHeader().getTotalInvoice());
					pedidoDTO.setTotalImpuestos(facFireBase.getHeader().getTotalTax());
					pedidoDTO.setTotalIva(facFireBase.getHeader().getTotalIva());
					pedidoDTO.setTotalSinImpuestos(facFireBase.getHeader().getTotalNotTax());
					
					Collection<DetallePedidoDTO> pedidoDetalleDTOCols = new ArrayList<>();
					// Agregar detalles del pedido
					if(CollectionUtils.isNotEmpty(facFireBase.getDetails())) {
						for(DetailOrder detailInvoice: facFireBase.getDetails()) {
							 
							Collection<ArticuloDTO> articuloDTOCols = this.articuloGestor.obtenerListaArticulos(ERPConstantes.CODIGO_COMPANIA, detailInvoice.getBarCodeItem(), null);
							if(CollectionUtils.isEmpty(articuloDTOCols)) {
								throw new ERPException("Error", "El articulo "+detailInvoice.getBarCodeItem()+" no se encuentra registrado en el sistema.");
							}else {
								ArticuloDTO articuloDTO = articuloDTOCols.iterator().next();
								ArticuloUnidadManejoDTO articuloUnidadManejoDTO = articuloDTO.getArticuloUnidadManejoDTOCols()
										.stream().filter(unidadManejo -> unidadManejo.getCodigoValorUnidadManejo().equals(detailInvoice.getValueCatalogDriverUnit()) && unidadManejo.getValorUnidadManejo().intValue() == detailInvoice.getValueDriverUnit().intValue())
										.findFirst().orElse(null);
								if(articuloUnidadManejoDTO == null) {
									throw new ERPException("Error", "La unidad de manejo "+detailInvoice.getValueCatalogDriverUnit()+" con valor "+detailInvoice.getValueDriverUnit()+" no se encuentra registrada en el sistema.");
								}else {
									DetallePedidoDTO detallePedidoDTO = new DetallePedidoDTO();
									detallePedidoDTO.getId().setCodigoCompania(ERPConstantes.CODIGO_COMPANIA);
									detallePedidoDTO.setUsuarioRegistro(ERPConstantes.USUARIO_GENERICO);
									detallePedidoDTO.setCantidad(detailInvoice.getQuantity());
									detallePedidoDTO.setCodigoArticulo(articuloDTO.getId().getCodigoArticulo());
									detallePedidoDTO.setArticuloDTO(articuloDTO);
									detallePedidoDTO.setArticuloUnidadManejoDTO(articuloUnidadManejoDTO);
									detallePedidoDTO.setCodigoArticuloUnidadManejo(articuloUnidadManejoDTO.getId().getCodigoArticuloUnidadManejo());
									detallePedidoDTO.setNombreArticulo(detailInvoice.getDescription());
									detallePedidoDTO.setSubTotal(detailInvoice.getSubTotal());
									pedidoDetalleDTOCols.add(detallePedidoDTO);
								}
							}
						}
					}	
					pedidoDTO.setDetallePedidoDTOCols(pedidoDetalleDTOCols);
					// Guardar la factura
					this.pedidoGestor.guardarPedido(ERPConstantes.CODIGO_COMPANIA, pedidoDTO);
				}
			});
		} catch (InterruptedException e) {
			throw new ERPException("Error, {0}",e.getMessage()) ;
		} catch (ExecutionException e) {
			throw new ERPException("Error, {0}",e.getMessage()) ;
		}
	}	
	
	
}
