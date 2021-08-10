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
import ec.com.erp.cliente.mdl.dto.FacturaCabeceraDTO;
import ec.com.erp.cliente.mdl.dto.FacturaDetalleDTO;
import ec.com.erp.cliente.mdl.dto.VendedorDTO;
import ec.com.erp.clientes.gestor.IClientesGestor;
import ec.com.erp.factura.gestor.IFacturaCabeceraGestor;
import ec.com.erp.firebase.commons.provider.InvoiceProvider;
import ec.com.erp.firebase.model.DetailInvoice;
import ec.com.erp.firebase.model.Invoice;
import ec.com.erp.vendedor.gestor.IVendedorGestor;

public class FireBaseFacturaGestor implements IFireBaseFacturaGestor {

	private IFacturaCabeceraGestor facturaCabeceraGestor;
	private IVendedorGestor vendedorGestor;
	private IArticuloGestor articuloGestor;
	private IClientesGestor clienteGestor;
	
	public IFacturaCabeceraGestor getFacturaCabeceraGestor() {
		return facturaCabeceraGestor;
	}

	public void setFacturaCabeceraGestor(IFacturaCabeceraGestor facturaCabeceraGestor) {
		this.facturaCabeceraGestor = facturaCabeceraGestor;
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
	
	public IClientesGestor getClienteGestor() {
		return clienteGestor;
	}

	public void setClienteGestor(IClientesGestor clienteGestor) {
		this.clienteGestor = clienteGestor;
	}

	/**
	 * M\u00e9todo para descargar los ARTICULOS de fire base
	 * @return 
	 * @throws ERPException
	 */
	public void descargarFacturasFireBase() throws ERPException{
		try {
			Collection<Invoice> facturasFireBase = InvoiceProvider.obtainInvoicesFirebase();
			Collection<String> tiposDocumentos = new ArrayList<>();
			tiposDocumentos.add(ERPConstantes.CODIGO_CATALOGO_VALOR_DOCUMENTO_VENTAS);
			tiposDocumentos.add(ERPConstantes.CODIGO_CATALOGO_VALOR_DOCUMENTO_NOTA_VENTA);
			Collection<FacturaCabeceraDTO> facturasDTOCols = this.facturaCabeceraGestor.obtenerListaFacturasValidarFirebase(ERPConstantes.CODIGO_COMPANIA, null, null, null, null, null, null, tiposDocumentos);
			
			facturasFireBase.stream().forEach(facFireBase ->{
				FacturaCabeceraDTO articuloDTOLocal = facturasDTOCols.stream()
						.filter(facturaDTO -> facturaDTO.getNumeroDocumento().equals(facFireBase.getHeader().getNumberDocument()))
						.findFirst().orElse(null);
				
				if(articuloDTOLocal == null) {
					
					ClienteDTO clienteDTO = this.clienteGestor.obteneClientePorNumeroDocumento(ERPConstantes.CODIGO_COMPANIA, facFireBase.getHeader().getClientDocument());
					if(clienteDTO == null) {
						throw new ERPException("Error", "El cliente con documento "+facFireBase.getHeader().getClientDocument()+" no se encuentra registrado en el sistema.");
					}
					
					FacturaCabeceraDTO facturaCabeceraDTO = new FacturaCabeceraDTO();
					facturaCabeceraDTO.getId().setCodigoCompania(ERPConstantes.CODIGO_COMPANIA);
					facturaCabeceraDTO.setTipoRuc(ERPConstantes.TIPO_RUC_DOS);					
					facturaCabeceraDTO.setUsuarioRegistro(ERPConstantes.USUARIO_GENERICO);
					facturaCabeceraDTO.setNumeroDocumento(facFireBase.getHeader().getNumberDocument());
					facturaCabeceraDTO.setCodigoTipoDocumento(ERPConstantes.CODIGO_CATALOGO_TIPOS_DOCUMENTOS);
					facturaCabeceraDTO.setTipoCliente(clienteDTO.getCodigoValorTipoCompra());
					if(facFireBase.getHeader().getValueDocumentCode().equals(ERPConstantes.TIPO_DOCUMENTO_FACTURA)) {
						facturaCabeceraDTO.setCodigoValorTipoDocumento(ERPConstantes.CODIGO_CATALOGO_VALOR_DOCUMENTO_VENTAS);
					}else {
						facturaCabeceraDTO.setCodigoValorTipoDocumento(ERPConstantes.CODIGO_CATALOGO_VALOR_DOCUMENTO_NOTA_VENTA);
					}
					// Obtener datos del vendedor si existe
					VendedorDTO vendedorDTO = this.vendedorGestor.obtenerVendedor(ERPConstantes.CODIGO_COMPANIA, facFireBase.getHeader().getUserId());
					if(vendedorDTO != null && vendedorDTO.getId().getCodigoVendedor() != null) {
						facturaCabeceraDTO.setCodigoVendedor(vendedorDTO.getId().getCodigoVendedor());
					}
					facturaCabeceraDTO.setDescuento(facFireBase.getHeader().getDiscount());
					facturaCabeceraDTO.setDireccion(facFireBase.getHeader().getClientDirection());
					facturaCabeceraDTO.setFechaDocumento(facFireBase.getHeader().getDateDocument());
					facturaCabeceraDTO.setNombreClienteProveedor(facFireBase.getHeader().getClientName());
					facturaCabeceraDTO.setPagado(facFireBase.getHeader().getPaidOut());
					facturaCabeceraDTO.setRucDocumento(facFireBase.getHeader().getClientDocument());
					facturaCabeceraDTO.setSubTotal(facFireBase.getHeader().getSubTotal());
					facturaCabeceraDTO.setTelefono(facFireBase.getHeader().getClientPhone());
					facturaCabeceraDTO.setTotalCuenta(facFireBase.getHeader().getTotalInvoice());
					facturaCabeceraDTO.setTotalImpuestos(facFireBase.getHeader().getTotalTax());
					facturaCabeceraDTO.setTotalIva(facFireBase.getHeader().getTotalIva());
					facturaCabeceraDTO.setTipoRuc(ERPConstantes.TIPO_RUC_DOS);
					if(facFireBase.getHeader().getPaidOut()) {
						facturaCabeceraDTO.setTotalPagos(facFireBase.getHeader().getTotalInvoice());
					}
					facturaCabeceraDTO.setTotalSinImpuestos(facFireBase.getHeader().getTotalNotTax());
					
					Collection<FacturaDetalleDTO> facturaDetalleDTOCols = new ArrayList<>();
					// Agregar unidades de manejo de articulo
					if(CollectionUtils.isNotEmpty(facFireBase.getDetails())) {
						for(DetailInvoice detailInvoice: facFireBase.getDetails()) {
							 
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
									FacturaDetalleDTO facturaDetalleDTO = new FacturaDetalleDTO();
									facturaDetalleDTO.getId().setCodigoCompania(ERPConstantes.CODIGO_COMPANIA);
									facturaDetalleDTO.setUsuarioRegistro(ERPConstantes.USUARIO_GENERICO);
									facturaDetalleDTO.setCantidad(detailInvoice.getQuantity());
									facturaDetalleDTO.setCodigoArticulo(articuloDTO.getId().getCodigoArticulo());
									facturaDetalleDTO.setArticuloDTO(articuloDTO);
									facturaDetalleDTO.setArticuloUnidadManejoDTO(articuloUnidadManejoDTO);
									facturaDetalleDTO.setCodigoArticuloUnidadManejo(articuloUnidadManejoDTO.getId().getCodigoArticuloUnidadManejo());
									facturaDetalleDTO.setCodigoBarras(detailInvoice.getBarCodeItem());
									facturaDetalleDTO.setDescripcion(detailInvoice.getDescription());
									facturaDetalleDTO.setSubTotal(detailInvoice.getSubTotal());
									facturaDetalleDTO.setValorUnidad(detailInvoice.getUnitValue());
									facturaDetalleDTOCols.add(facturaDetalleDTO);
								}
							}
						}
					}	
					facturaCabeceraDTO.setFacturaDetalleDTOCols(facturaDetalleDTOCols);
					// Guardar la factura
					this.facturaCabeceraGestor.guardarActualizarFacturaCabecera(Boolean.FALSE, facturaCabeceraDTO);
				}
			});
		} catch (InterruptedException e) {
			throw new ERPException("Error, {0}",e.getMessage()) ;
		} catch (ExecutionException e) {
			throw new ERPException("Error, {0}",e.getMessage()) ;
		}
	}	
	
	
}
