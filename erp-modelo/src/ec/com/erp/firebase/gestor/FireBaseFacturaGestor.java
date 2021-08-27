package ec.com.erp.firebase.gestor;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
import ec.com.erp.firebase.model.HeadInvoiceUpdate;
import ec.com.erp.firebase.model.Invoice;
import ec.com.erp.firebase.model.InvoiceUpdate;
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
	@Override
	public void descargarFacturasFireBase() throws ERPException{
		try {
			Collection<Invoice> facturasFireBase = InvoiceProvider.obtainInvoicesFirebase();
			Collection<String> tiposDocumentos = new ArrayList<>();
			tiposDocumentos.add(ERPConstantes.CODIGO_CATALOGO_VALOR_DOCUMENTO_VENTAS);
			tiposDocumentos.add(ERPConstantes.CODIGO_CATALOGO_VALOR_DOCUMENTO_NOTA_VENTA);
			Collection<FacturaCabeceraDTO> facturasDTOCols = this.facturaCabeceraGestor.obtenerListaFacturasValidarFirebase(ERPConstantes.CODIGO_COMPANIA, null, null, null, null, null, null, tiposDocumentos);
			
			facturasFireBase.stream().forEach(facFireBase ->{
				FacturaCabeceraDTO articuloDTOLocal = facturasDTOCols.stream()
						.filter(facturaDTO -> facturaDTO.getCodigoReferenciaFactura().equals(facFireBase.getHeader().getNumberDocument()))
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
					facturaCabeceraDTO.setCodigoReferenciaFactura(facFireBase.getHeader().getNumberDocument());
					facturaCabeceraDTO.setCodigoTipoDocumento(ERPConstantes.CODIGO_CATALOGO_TIPOS_DOCUMENTOS);
					facturaCabeceraDTO.setTipoCliente(clienteDTO.getCodigoValorTipoCompra());
					if(facFireBase.getHeader().getValueDocumentCode().equals(ERPConstantes.TIPO_DOCUMENTO_FACTURA)) {
						facturaCabeceraDTO.setCodigoValorTipoDocumento(ERPConstantes.CODIGO_CATALOGO_VALOR_DOCUMENTO_VENTAS);
					}else {
						facturaCabeceraDTO.setCodigoValorTipoDocumento(ERPConstantes.CODIGO_CATALOGO_VALOR_DOCUMENTO_NOTA_VENTA);
						facturaCabeceraDTO.setNumeroDocumento(facFireBase.getHeader().getNumberDocument());
					}
					// Obtener datos del vendedor si existe
					VendedorDTO vendedorDTO = this.vendedorGestor.obtenerVendedor(ERPConstantes.CODIGO_COMPANIA, facFireBase.getHeader().getUserId());
					if(vendedorDTO != null && vendedorDTO.getId().getCodigoVendedor() != null) {
						facturaCabeceraDTO.setCodigoVendedor(vendedorDTO.getId().getCodigoVendedor());
					}
					facturaCabeceraDTO.setDescuento(BigDecimal.valueOf(facFireBase.getHeader().getDiscount()));
					facturaCabeceraDTO.setDireccion(facFireBase.getHeader().getClientDirection());
					facturaCabeceraDTO.setFechaDocumento(facFireBase.getHeader().getDateDocument());
					facturaCabeceraDTO.setNombreClienteProveedor(facFireBase.getHeader().getClientName());
					facturaCabeceraDTO.setPagado(facFireBase.getHeader().getPaidOut());
					facturaCabeceraDTO.setRucDocumento(facFireBase.getHeader().getClientDocument());
					facturaCabeceraDTO.setSubTotal(BigDecimal.valueOf(facFireBase.getHeader().getSubTotal()));
					facturaCabeceraDTO.setTelefono(facFireBase.getHeader().getClientPhone());
					facturaCabeceraDTO.setTotalCuenta(BigDecimal.valueOf(facFireBase.getHeader().getTotalInvoice()));
					facturaCabeceraDTO.setTotalImpuestos(BigDecimal.valueOf(facFireBase.getHeader().getTotalTax()));
					facturaCabeceraDTO.setTotalIva(BigDecimal.valueOf(facFireBase.getHeader().getTotalIva()));
					facturaCabeceraDTO.setTipoRuc(ERPConstantes.TIPO_RUC_DOS);
					if(facFireBase.getHeader().getPaidOut()) {
						facturaCabeceraDTO.setTotalPagos(BigDecimal.valueOf(facFireBase.getHeader().getTotalInvoice()));
					}
					facturaCabeceraDTO.setTotalSinImpuestos(BigDecimal.valueOf(facFireBase.getHeader().getTotalNotTax()));
					
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
									facturaDetalleDTO.setDescuento(detailInvoice.getDiscount() != null ? BigDecimal.valueOf(detailInvoice.getDiscount()): BigDecimal.ZERO);
									facturaDetalleDTO.setSubTotal(BigDecimal.valueOf(detailInvoice.getSubTotal()));
									facturaDetalleDTO.setValorUnidad(BigDecimal.valueOf(detailInvoice.getUnitValue()));
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
	
	
	/**
	 * Metodo para actualizar factura en app
	 */
	@Override
	public void actualizarPagoFacturas(){
		try{
			SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
			Collection<Invoice> facturasFireBase = InvoiceProvider.obtainInvoicesFirebase();
			Collection<String> tiposDocumentos = new ArrayList<>();
			Collection<InvoiceUpdate> facturasActualizar = new ArrayList<>();
			tiposDocumentos.add(ERPConstantes.CODIGO_CATALOGO_VALOR_DOCUMENTO_VENTAS);
			tiposDocumentos.add(ERPConstantes.CODIGO_CATALOGO_VALOR_DOCUMENTO_NOTA_VENTA);
			Collection<FacturaCabeceraDTO> facturasDTOCols = this.facturaCabeceraGestor.obtenerListaFacturasValidarFirebase(ERPConstantes.CODIGO_COMPANIA, null, null, null, null, null, null, tiposDocumentos);
			facturasFireBase.stream().forEach(facturaApp -> {
				FacturaCabeceraDTO facturaCabeceraDTO = facturasDTOCols.stream()
				    .filter(facturaLocal -> facturaLocal.getCodigoReferenciaFactura().equals(facturaApp.getHeader().getNumberDocument()) && facturaLocal.getPagado() && !facturaApp.getHeader().getPaidOut())
				    .findFirst().orElse(null);
				if(facturaCabeceraDTO != null){
					InvoiceUpdate invoiceUpdate = crearFacturaActualizar(facturaApp, formatoFecha.format(facturaCabeceraDTO.getFechaDocumento()));
					facturaApp.getHeader().setPaidOut(Boolean.TRUE);
					facturasActualizar.add(invoiceUpdate);
				}
			});
			// Update invoices
			InvoiceProvider.updateInvoiceAux(facturasActualizar);
		} catch (InterruptedException e) {
			throw new ERPException("Error, {0}",e.getMessage()) ;
		} catch (ExecutionException e) {
			throw new ERPException("Error, {0}",e.getMessage()) ;
		} catch (IOException e) {
			throw new ERPException("Error, {0}",e.getMessage()) ;
		}
	}
	
	public InvoiceUpdate crearFacturaActualizar(Invoice invoice, String dateDocument){
		InvoiceUpdate invoiceUpdate = new InvoiceUpdate();
		invoiceUpdate.setDetails(invoice.getDetails());
		invoiceUpdate.setHeader(new HeadInvoiceUpdate());
		invoiceUpdate.getHeader().setIdInvoice(invoice.getHeader().getIdInvoice());
		invoiceUpdate.getHeader().setClientDirection(invoice.getHeader().getClientDirection());
		invoiceUpdate.getHeader().setClientDocument(invoice.getHeader().getClientDocument()); 
		invoiceUpdate.getHeader().setClientName(invoice.getHeader().getClientName()); 
		invoiceUpdate.getHeader().setClientPhone(invoice.getHeader().getClientPhone()); 
		invoiceUpdate.getHeader().setDateDocument(dateDocument); 
		invoiceUpdate.getHeader().setDiscount(invoice.getHeader().getDiscount()); 
		invoiceUpdate.getHeader().setNumberDocument(invoice.getHeader().getNumberDocument());
		invoiceUpdate.getHeader().setPaidOut(invoice.getHeader().getPaidOut());
		invoiceUpdate.getHeader().setSubTotal(invoice.getHeader().getSubTotal());
		invoiceUpdate.getHeader().setTotalInvoice(invoice.getHeader().getTotalInvoice());
		invoiceUpdate.getHeader().setTotalIva(invoice.getHeader().getTotalIva());
		invoiceUpdate.getHeader().setTotalNotTax(invoice.getHeader().getTotalNotTax());
		invoiceUpdate.getHeader().setTotalTax(invoice.getHeader().getTotalTax());
		invoiceUpdate.getHeader().setTypeDocumentCode(invoice.getHeader().getTypeDocumentCode());
		invoiceUpdate.getHeader().setUserId(invoice.getHeader().getUserId());
		invoiceUpdate.getHeader().setSeller(invoice.getHeader().getSeller());
		invoiceUpdate.getHeader().setValueDocumentCode(invoice.getHeader().getValueDocumentCode());
		return invoiceUpdate;
	}
	
}
