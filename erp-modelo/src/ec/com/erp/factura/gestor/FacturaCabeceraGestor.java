/**
 * 
 */
package ec.com.erp.factura.gestor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jdom.Document;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.common.resources.ERPMessages;
import ec.com.erp.cliente.mdl.dto.FacturaCabeceraDTO;
import ec.com.erp.cliente.mdl.dto.FacturaDetalleDTO;
import ec.com.erp.cliente.mdl.dto.InventarioDTO;
import ec.com.erp.cliente.mdl.dto.TransaccionDTO;
import ec.com.erp.factura.dao.IFacturaCabeceraDAO;
import ec.com.erp.inventario.gestor.IInventarioGestor;
import ec.com.erp.transaccion.gestor.ITransaccionGestor;
import ec.com.erp.utilitario.commons.util.TransformerUtil;

/**
 * @author Esteban Gudino
 * 2018-05-05
 */
public class FacturaCabeceraGestor implements IFacturaCabeceraGestor {

	private IFacturaCabeceraDAO facturaCabeceraDAO;
	private IFacturaDetalleGestor facturaDetalleGestor;
	private IInventarioGestor inventarioGestor;
	private ITransaccionGestor transaccionGestor;
	
	
	public IFacturaCabeceraDAO getFacturaCabeceraDAO() {
		return facturaCabeceraDAO;
	}

	public void setFacturaCabeceraDAO(IFacturaCabeceraDAO facturaCabeceraDAO) {
		this.facturaCabeceraDAO = facturaCabeceraDAO;
	}

	public IFacturaDetalleGestor getFacturaDetalleGestor() {
		return facturaDetalleGestor;
	}

	public void setFacturaDetalleGestor(IFacturaDetalleGestor facturaDetalleGestor) {
		this.facturaDetalleGestor = facturaDetalleGestor;
	}
	
	public IInventarioGestor getInventarioGestor() {
		return inventarioGestor;
	}

	public void setInventarioGestor(IInventarioGestor inventarioGestor) {
		this.inventarioGestor = inventarioGestor;
	}

	public ITransaccionGestor getTransaccionGestor() {
		return transaccionGestor;
	}

	public void setTransaccionGestor(ITransaccionGestor transaccionGestor) {
		this.transaccionGestor = transaccionGestor;
	}

	/**
	 * M\u00e9todo para obtener lista de facturas por filtros de busqueda
	 * @param codigoCompania
	 * @param numeroFactura
	 * @param fechaFacturaInicio
	 * @param fechaFacturaFin
	 * @param docClienteProveedor
	 * @param nombClienteProveedor
	 * @param pagado
	 * @param tipoDocumento
	 * @return Collection<FacturaCabeceraDTO>
	 * @throws ERPException
	 */
	@Override
	public Collection<FacturaCabeceraDTO> obtenerListaFacturas(Integer codigoCompania, String numeroFactura, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin,  String docClienteProveedor, String nombClienteProveedor, Boolean pagado, String tipoDocumento) throws ERPException{
		return this.facturaCabeceraDAO.obtenerListaFacturas(codigoCompania, numeroFactura, fechaFacturaInicio, fechaFacturaFin, docClienteProveedor, nombClienteProveedor, pagado, tipoDocumento);
	}
	
	/**
	 * M\u00e9todo para obtener la factura del pedido
	 * @param codigoCompania
	 * @param codigoPedido
	 * @return
	 * @throws ERPException
	 */
	@Override
	public FacturaCabeceraDTO obtenerFacturaPedido(Integer codigoCompania, Long codigoPedido) throws ERPException{
		return this.facturaCabeceraDAO.obtenerFacturaPedido(codigoCompania, codigoPedido);
	}
	
	/**
	 * M\u00e9todo para guardar y actualizar factura cabecera
	 * @param facturaCabeceraDTO
	 * @throws ERPException
	 */
	@Override
	public void guardarActualizarFacturaCabecera(FacturaCabeceraDTO facturaCabeceraDTO) throws ERPException{
		try{
			// Obtenemos la lista de detalle a guardar
			Collection<FacturaDetalleDTO> facturaDetalleDTOCols = facturaCabeceraDTO.getFacturaDetalleDTOCols();
			// Guardamos la cabecera de la factura
			facturaCabeceraDTO.setCodigoTipoDocumento(ERPConstantes.CODIGO_CATALOGO_TIPOS_DOCUMENTOS);
			this.facturaCabeceraDAO.guardarActualizarFacturaCabecera(facturaCabeceraDTO);
			
			// Guardamos los detalle de la factura 
			for (FacturaDetalleDTO facturaDetalleDTO : facturaDetalleDTOCols) {
				if(facturaDetalleDTO.getCodigoArticulo() != null){
					facturaDetalleDTO.getId().setCodigoCompania(Integer.parseInt(ERPConstantes.ESTADO_ACTIVO_NUMERICO));
					facturaDetalleDTO.setCodigoFactura(facturaCabeceraDTO.getId().getCodigoFactura());
					facturaDetalleDTO.setUsuarioRegistro(facturaCabeceraDTO.getUsuarioRegistro());
					if(facturaCabeceraDTO.getCodigoValorTipoDocumento().equals(ERPConstantes.CODIGO_CATALOGO_VALOR_DOCUMENTO_VENTAS)){
						if(facturaDetalleDTO.getId().getCodigoDetalleFactura() == null) {
							this.registrarInventario(facturaDetalleDTO, facturaCabeceraDTO.getCodigoReferenciaFactura());
						}
					}else {
						if(facturaDetalleDTO.getId().getCodigoDetalleFactura() == null) {
							this.registrarInventarioCompra(facturaDetalleDTO, facturaCabeceraDTO.getCodigoReferenciaFactura());
						}
					}
					this.facturaDetalleGestor.guardarActualizarDetalleFactura(facturaDetalleDTO);
				}
			}
			
			// Guardar tipo de transaccion
			if(facturaCabeceraDTO.getPagado()) {
				TransaccionDTO transaccionDTO = new TransaccionDTO();
				transaccionDTO.getId().setCodigoCompania(facturaCabeceraDTO.getId().getCodigoCompania());
				transaccionDTO.setValorTransaccion(facturaCabeceraDTO.getTotalCuenta());
				transaccionDTO.setUsuarioRegistro(facturaCabeceraDTO.getUsuarioRegistro());
				transaccionDTO.setFechaTransaccion(facturaCabeceraDTO.getFechaDocumento());
				if(facturaCabeceraDTO.getCodigoValorTipoDocumento().equals(ERPConstantes.CODIGO_CATALOGO_VALOR_DOCUMENTO_VENTAS)) {
					transaccionDTO.setCodigoValorTransaccion(ERPConstantes.CODIGO_CATALOGO_VALOR_TRANSACCION_INGRESO);
					transaccionDTO.setConcepto("PAGO DE CLIENTE FACTURA NRO"+facturaCabeceraDTO.getNumeroDocumento());
				}else {
					transaccionDTO.setCodigoValorTransaccion(ERPConstantes.CODIGO_CATALOGO_VALOR_TRANSACCION_GASTO);
					transaccionDTO.setConcepto("PAGO A PROVEEDOR FACTURA NRO"+facturaCabeceraDTO.getNumeroDocumento());
				}
				this.transaccionGestor.guardarTransaccion(transaccionDTO);
			}
			
		} catch (ERPException e) {
			throw new ERPException("Error, {0}",e.getMessage()) ;
		} catch (Exception e) {
			throw new ERPException("Error, {0}",e.getMessage());
		} 
	}
	
	/**
	 * Funcionalidad para cancelar factura o inactivar
	 * @param facturaCabeceraDTO
	 * @throws ERPException
	 */
	@Override
	public void cancelarFacturaInactivar(FacturaCabeceraDTO facturaCabeceraDTO) throws ERPException{
		try{
			// Obtenemos la lista de detalle a guardar
			Collection<FacturaDetalleDTO> facturaDetalleDTOCols = facturaCabeceraDTO.getFacturaDetalleDTOCols();
			// Guardamos la cabecera de la factura
			facturaCabeceraDTO.setEstado(ERPConstantes.ESTADO_INACTIVO_NUMERICO);
			this.facturaCabeceraDAO.guardarActualizarFacturaCabecera(facturaCabeceraDTO);
			
			// Guardamos los detalle de la factura 
			for (FacturaDetalleDTO facturaDetalleDTO : facturaDetalleDTOCols) {
				facturaDetalleDTO.getId().setCodigoCompania(Integer.parseInt(ERPConstantes.ESTADO_ACTIVO_NUMERICO));
				facturaDetalleDTO.setCodigoFactura(facturaCabeceraDTO.getId().getCodigoFactura());
				facturaDetalleDTO.setEstado(ERPConstantes.ESTADO_INACTIVO_NUMERICO);
				if(facturaCabeceraDTO.getCodigoValorTipoDocumento().equals(ERPConstantes.CODIGO_CATALOGO_VALOR_DOCUMENTO_VENTAS)){
					this.reversarInventario(facturaDetalleDTO, facturaCabeceraDTO.getCodigoReferenciaFactura());
				}else {
					this.reversarInventarioCompra(facturaDetalleDTO, facturaCabeceraDTO.getCodigoReferenciaFactura());
				}
				this.facturaDetalleGestor.guardarActualizarDetalleFactura(facturaDetalleDTO);
			}
		} catch (ERPException e) {
			throw new ERPException("Error, {0}",e.getMessage()) ;
		} catch (Exception e) {
			throw new ERPException("Error, {0}",e.getMessage());
		} 
	}
	
	/**
	 * Metodo para registrar movimiento de mercaderia salida
	 * @param facturaDetalleDTO
	 */
	public void registrarInventario(FacturaDetalleDTO facturaDetalleDTO, String numeroFactura) {
		InventarioDTO inventarioDTOAux = this.inventarioGestor.obtenerUltimoInventarioByArticulo(facturaDetalleDTO.getId().getCodigoCompania(), facturaDetalleDTO.getArticuloDTO().getCodigoBarras());
		InventarioDTO inventarioDTO = new InventarioDTO();
		if(inventarioDTOAux != null) {
			if(facturaDetalleDTO.getCantidad().intValue() > inventarioDTOAux.getCantidadExistencia().intValue()) {
				throw new ERPException("No se puede registrar la venta por que no hay existencias suficientes para el articulo "+facturaDetalleDTO.getArticuloDTO().getNombreArticulo());
			}
			inventarioDTO.getId().setCodigoCompania(facturaDetalleDTO.getId().getCodigoCompania());
			inventarioDTO.setDetalleMoviento(ERPMessages.getString("ec.com.erp.cliente.mensaje.controlado.descripcion.invetarios.venta")+" "+numeroFactura);
			inventarioDTO.setArticuloDTO(facturaDetalleDTO.getArticuloDTO());
			inventarioDTO.setCodigoArticulo(facturaDetalleDTO.getCodigoArticulo());
			inventarioDTO.setCantidadSalida(facturaDetalleDTO.getCantidad());
			inventarioDTO.setValorUnidadSalida(facturaDetalleDTO.getValorUnidad());
			inventarioDTO.setValorTotalSalida(facturaDetalleDTO.getSubTotal());
			inventarioDTO.setCantidadExistencia(inventarioDTOAux.getCantidadExistencia().intValue() - facturaDetalleDTO.getCantidad());
			inventarioDTO.setValorUnidadExistencia(facturaDetalleDTO.getValorUnidad());
			inventarioDTO.setValorTotalExistencia(inventarioDTOAux.getValorTotalExistencia().subtract(facturaDetalleDTO.getSubTotal()));
			inventarioDTO.setCantidadEntrada(null);
			inventarioDTO.setValorUnidadEntrada(null);
			inventarioDTO.setValorTotalEntrada(null);
			inventarioDTO.setUsuarioRegistro(facturaDetalleDTO.getUsuarioRegistro());
			this.inventarioGestor.crearActualizarInventario(inventarioDTO);
		}
		else {
			throw new ERPException("No se puede registrar la venta por que no hay existencias suficientes para el articulo "+facturaDetalleDTO.getArticuloDTO().getNombreArticulo());
		}
	}
	
	/**
	 * Metodo para registrar movimiento de mercaderia salida
	 * @param facturaDetalleDTO
	 */
	public void registrarInventarioCompra(FacturaDetalleDTO facturaDetalleDTO, String numeroFactura) {
		InventarioDTO inventarioDTOAux = this.inventarioGestor.obtenerUltimoInventarioByArticulo(facturaDetalleDTO.getId().getCodigoCompania(), facturaDetalleDTO.getArticuloDTO().getCodigoBarras());
		InventarioDTO inventarioDTO = new InventarioDTO();
		if(inventarioDTOAux != null) {
			inventarioDTO.getId().setCodigoCompania(facturaDetalleDTO.getId().getCodigoCompania());
			inventarioDTO.setDetalleMoviento(ERPMessages.getString("ec.com.erp.cliente.mensaje.controlado.descripcion.invetarios.compra")+" "+numeroFactura);
			inventarioDTO.setArticuloDTO(facturaDetalleDTO.getArticuloDTO());
			inventarioDTO.setCodigoArticulo(facturaDetalleDTO.getCodigoArticulo());
			inventarioDTO.setCantidadEntrada(facturaDetalleDTO.getCantidad());
			inventarioDTO.setValorUnidadEntrada(facturaDetalleDTO.getValorUnidad());
			inventarioDTO.setValorTotalEntrada(facturaDetalleDTO.getSubTotal());
			inventarioDTO.setCantidadExistencia(inventarioDTOAux.getCantidadExistencia().intValue() + facturaDetalleDTO.getCantidad());
			inventarioDTO.setValorUnidadExistencia(facturaDetalleDTO.getValorUnidad());
			inventarioDTO.setValorTotalExistencia(inventarioDTOAux.getValorTotalExistencia().add(facturaDetalleDTO.getSubTotal()));
			inventarioDTO.setCantidadSalida(null);
			inventarioDTO.setValorUnidadSalida(null);
			inventarioDTO.setValorTotalSalida(null);
			inventarioDTO.setUsuarioRegistro(facturaDetalleDTO.getUsuarioRegistro());
			this.inventarioGestor.crearActualizarInventario(inventarioDTO);
		}
		else {
			throw new ERPException("No se puede registrar la venta por que no hay existencias suficientes para el articulo "+facturaDetalleDTO.getArticuloDTO().getNombreArticulo());
		}
	}

	/**
	 * Metodo para registrar movimiento de mercaderia reversa
	 * @param facturaDetalleDTO
	 */
	public void reversarInventario(FacturaDetalleDTO facturaDetalleDTO, String numeroFactura) {
		InventarioDTO inventarioDTOAux = this.inventarioGestor.obtenerUltimoInventarioByArticulo(facturaDetalleDTO.getId().getCodigoCompania(), facturaDetalleDTO.getArticuloDTO().getCodigoBarras());
		InventarioDTO inventarioDTO = new InventarioDTO();
		if(inventarioDTOAux != null) {
			inventarioDTO.getId().setCodigoCompania(facturaDetalleDTO.getId().getCodigoCompania());
			inventarioDTO.setDetalleMoviento(ERPMessages.getString("ec.com.erp.cliente.mensaje.controlado.descripcion.invetarios.reversa")+" "+numeroFactura);
			inventarioDTO.setArticuloDTO(facturaDetalleDTO.getArticuloDTO());
			inventarioDTO.setCodigoArticulo(facturaDetalleDTO.getCodigoArticulo());
			inventarioDTO.setCantidadEntrada(facturaDetalleDTO.getCantidad());
			inventarioDTO.setValorUnidadEntrada(facturaDetalleDTO.getValorUnidad());
			inventarioDTO.setValorTotalEntrada(facturaDetalleDTO.getSubTotal());
			inventarioDTO.setCantidadExistencia(inventarioDTOAux.getCantidadExistencia().intValue() + facturaDetalleDTO.getCantidad());
			inventarioDTO.setValorUnidadExistencia(facturaDetalleDTO.getValorUnidad());
			inventarioDTO.setValorTotalExistencia(inventarioDTOAux.getValorTotalExistencia().add(facturaDetalleDTO.getSubTotal()));
			inventarioDTO.setCantidadSalida(null);
			inventarioDTO.setValorUnidadSalida(null);
			inventarioDTO.setValorTotalSalida(null);
			inventarioDTO.setUsuarioRegistro(facturaDetalleDTO.getUsuarioRegistro());
			this.inventarioGestor.crearActualizarInventario(inventarioDTO);
		}
		else {
			throw new ERPException("No se puede registrar la resersa por que no hay existencias para el articulo "+facturaDetalleDTO.getArticuloDTO().getNombreArticulo());
		}
	}
	
	/**
	 * Metodo para registrar movimiento de mercaderia reversa
	 * @param facturaDetalleDTO
	 */
	public void reversarInventarioCompra(FacturaDetalleDTO facturaDetalleDTO, String numeroFactura) {
		InventarioDTO inventarioDTOAux = this.inventarioGestor.obtenerUltimoInventarioByArticulo(facturaDetalleDTO.getId().getCodigoCompania(), facturaDetalleDTO.getArticuloDTO().getCodigoBarras());
		InventarioDTO inventarioDTO = new InventarioDTO();
		if(inventarioDTOAux != null) {
			inventarioDTO.getId().setCodigoCompania(facturaDetalleDTO.getId().getCodigoCompania());
			inventarioDTO.setDetalleMoviento(ERPMessages.getString("ec.com.erp.cliente.mensaje.controlado.descripcion.invetarios.reversa.compra")+" "+numeroFactura);
			inventarioDTO.setArticuloDTO(facturaDetalleDTO.getArticuloDTO());
			inventarioDTO.setCodigoArticulo(facturaDetalleDTO.getCodigoArticulo());
			inventarioDTO.setCantidadSalida(facturaDetalleDTO.getCantidad());
			inventarioDTO.setValorUnidadSalida(facturaDetalleDTO.getValorUnidad());
			inventarioDTO.setValorTotalSalida(facturaDetalleDTO.getSubTotal());
			inventarioDTO.setCantidadExistencia(inventarioDTOAux.getCantidadExistencia().intValue() - facturaDetalleDTO.getCantidad());
			inventarioDTO.setValorUnidadExistencia(facturaDetalleDTO.getValorUnidad());
			inventarioDTO.setValorTotalExistencia(inventarioDTOAux.getValorTotalExistencia().subtract(facturaDetalleDTO.getSubTotal()));
			inventarioDTO.setCantidadEntrada(null);
			inventarioDTO.setValorUnidadEntrada(null);
			inventarioDTO.setValorTotalEntrada(null);
			inventarioDTO.setUsuarioRegistro(facturaDetalleDTO.getUsuarioRegistro());
			this.inventarioGestor.crearActualizarInventario(inventarioDTO);
		}
		else {
			throw new ERPException("No se puede registrar la resersa por que no hay existencias para el articulo "+facturaDetalleDTO.getArticuloDTO().getNombreArticulo());
		}
	}

	/**
	 * Devuelve html de reporte de facturas
	 * @param facturaCabeceraDTOCols
	 * @return
	 * @throws ERPException
	 */
	public String procesarXMLReporteFacturas(Collection<FacturaCabeceraDTO> facturaCabeceraDTOCols) throws ERPException{
		StringBuilder contenidoXml = new StringBuilder();
		String html = "";
		String urlTipoReporte = "";
		try{
			BigDecimal total = BigDecimal.ZERO;
			Date fechaactual = new Date();
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			String fechaFormateada =  formatoFecha.format(fechaactual);
			DecimalFormat formatoDecimales = new DecimalFormat("#.##");
			formatoDecimales.setMinimumFractionDigits(2);

			urlTipoReporte = ERPConstantes.PLANTILLA_XSL_REPORTE_FACTURAS;
			
			contenidoXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
			contenidoXml.append("<facturas>");

			contenidoXml.append("<fechaReporte>").append(StringEscapeUtils.escapeXml(""+fechaFormateada)).append("</fechaReporte>");
			int cont = 1;
			//detalle reposicion
			contenidoXml.append("<listaFacturas>");
			for(FacturaCabeceraDTO facturaCabeceraDTO : facturaCabeceraDTOCols){
				contenidoXml.append("<factura>");
				contenidoXml.append("<numeroFila>").append(StringEscapeUtils.escapeXml(""+cont)).append("</numeroFila>");
				contenidoXml.append("<numeroDocumento>").append(StringEscapeUtils.escapeXml(facturaCabeceraDTO.getNumeroDocumento())).append("</numeroDocumento>");
				contenidoXml.append("<documentoCliente>").append(StringEscapeUtils.escapeXml(""+facturaCabeceraDTO.getRucDocumento())).append("</documentoCliente>");
				contenidoXml.append("<nombreClienteProveedor>").append(StringEscapeUtils.escapeXml(facturaCabeceraDTO.getNombreClienteProveedor())).append("</nombreClienteProveedor>");
				contenidoXml.append("<fechaEmision>").append(StringEscapeUtils.escapeXml(formatoFecha.format(facturaCabeceraDTO.getFechaDocumento()))).append("</fechaEmision>");
				contenidoXml.append("<valorTotal>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(facturaCabeceraDTO.getTotalCuenta().doubleValue()))).append("</valorTotal>");
				contenidoXml.append("<estado>").append(StringEscapeUtils.escapeXml(facturaCabeceraDTO.getPagado() ? "SI":"NO")).append("</estado>");
				contenidoXml.append("</factura>");
				cont++;
				total = total.add(facturaCabeceraDTO.getTotalCuenta());
			}
			contenidoXml.append("</listaFacturas>");			
			contenidoXml.append("<totalPago>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(total.doubleValue()))).append("</totalPago>");
			contenidoXml.append("</facturas>");
			String contenidoXSL=null;
			contenidoXSL = TransformerUtil.obtenerPlantillaHTML(urlTipoReporte);
			Document docXML = TransformerUtil.stringToXML(contenidoXml.toString());
			Document docXSL = TransformerUtil.stringToXML(contenidoXSL);
			Document result = TransformerUtil.transformar(docXML, docXSL);
			HashMap<String , String> parametros = new HashMap<String, String>();
			result = TransformerUtil.transformar(docXML, docXSL, parametros);
			html = TransformerUtil.xmlToString(result);
		} catch (Exception en) {
			throw new ERPException("Error al procesar plantilla xsl") ;
		}
		return html;
	}
	
	/**
	 * Metodo para obtener el valor de venta por mes y tipo
	 * @param codigoCompania
	 * @param fechaInicio
	 * @param fechaFin
	 * @param tipoDocumento
	 * @return
	 * @throws ERPException
	 */
	@Override
	public BigDecimal obtenerComprasVentas(Integer codigoCompania, Timestamp fechaInicio, Timestamp fechaFin, String tipoDocumento, Boolean pagada) throws ERPException{
		return this.facturaCabeceraDAO.obtenerComprasVentas(codigoCompania, fechaInicio, fechaFin, tipoDocumento, pagada);
	}
	
	/**
	 * Obtener numero de facturas por filtros
	 * @param codigoCompania
	 * @param tipoDocumento
	 * @param pagada
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Long obtenerNumeroFacturasComprasVentas(Integer codigoCompania, String tipoDocumento, Boolean pagada) throws ERPException{
		// Fecha inferior
		Calendar fechaInferior = Calendar.getInstance();
		fechaInferior.set(Calendar.DATE, 1);
		fechaInferior.set(Calendar.HOUR_OF_DAY, 0);
		fechaInferior.set(Calendar.MINUTE, 0);
		fechaInferior.set(Calendar.SECOND, 0);
		fechaInferior.set(Calendar.MILLISECOND, 0);
		fechaInferior.set(Calendar.MONTH,0);
		// Fecha superior
		Calendar fechaSuperior = Calendar.getInstance();
		return this.facturaCabeceraDAO.obtenerNumeroFacturasComprasVentas(codigoCompania, new Timestamp(fechaInferior.getTime().getTime()), new Timestamp(fechaSuperior.getTime().getTime()), tipoDocumento, pagada); 
	}
	
	/**
	 * Devuelve html para la impresion de factura de venta
	 * @param facturaCabeceraDTO
	 * @return
	 * @throws ERPException
	 */
	@Override
	public String obtenerXMLImprimirFacturaVenta(FacturaCabeceraDTO facturaCabeceraDTO) throws ERPException{
		StringBuilder contenidoXml = new StringBuilder();
		String html = "";
		String urlTipoReporte = "";
		try{
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			DecimalFormat formatoDecimales = new DecimalFormat("#.##");
			formatoDecimales.setMinimumFractionDigits(2);

			urlTipoReporte = ERPConstantes.PLANTILLA_XSL_IMPRIMIR_FACTURA_VENTA;
			
			contenidoXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
			contenidoXml.append("<factura>");

			contenidoXml.append("<codigoFactura>").append(StringEscapeUtils.escapeXml(""+facturaCabeceraDTO.getCodigoReferenciaFactura())).append("</codigoFactura>");
			contenidoXml.append("<numeroFactura>").append(StringEscapeUtils.escapeXml(""+facturaCabeceraDTO.getNumeroDocumento())).append("</numeroFactura>");
			contenidoXml.append("<fechaVenta>").append(StringEscapeUtils.escapeXml(""+formatoFecha.format(facturaCabeceraDTO.getFechaDocumento()))).append("</fechaVenta>");
			contenidoXml.append("<documentoCliente>").append(StringEscapeUtils.escapeXml(""+facturaCabeceraDTO.getRucDocumento())).append("</documentoCliente>");
			contenidoXml.append("<nombreCliente>").append(StringEscapeUtils.escapeXml(""+facturaCabeceraDTO.getNombreClienteProveedor())).append("</nombreCliente>");
			contenidoXml.append("<direccion>").append(StringEscapeUtils.escapeXml(""+facturaCabeceraDTO.getDireccion())).append("</direccion>");
			contenidoXml.append("<telefono>").append(StringEscapeUtils.escapeXml(""+facturaCabeceraDTO.getTelefono())).append("</telefono>");
			contenidoXml.append("<subtotal>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(facturaCabeceraDTO.getTotalSinImpuestos() == null ? 0 : facturaCabeceraDTO.getTotalSinImpuestos().doubleValue()))).append("</subtotal>");
			contenidoXml.append("<iva>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(facturaCabeceraDTO.getTotalImpuestos() == null ? 0 : facturaCabeceraDTO.getTotalImpuestos().doubleValue()))).append("</iva>");
			contenidoXml.append("<total>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(facturaCabeceraDTO.getTotalCuenta().doubleValue()))).append("</total>");
			int cont = 1;
			//detalle reposicion
			contenidoXml.append("<detallesFactura>");
			for(FacturaDetalleDTO facturaDetalleDTO : facturaCabeceraDTO.getFacturaDetalleDTOCols()){
				if(facturaDetalleDTO.getId().getCodigoDetalleFactura() != null) {
					contenidoXml.append("<detalle>");
					contenidoXml.append("<nroDetalle>").append(StringEscapeUtils.escapeXml(""+cont)).append("</nroDetalle>");
					contenidoXml.append("<cantidad>").append(StringEscapeUtils.escapeXml(""+facturaDetalleDTO.getCantidad())).append("</cantidad>");
					contenidoXml.append("<descripcion>").append(StringEscapeUtils.escapeXml(facturaDetalleDTO.getDescripcion())).append("</descripcion>");
					contenidoXml.append("<valorUnitario>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(facturaDetalleDTO.getValorUnidad().doubleValue()))).append("</valorUnitario>");
					contenidoXml.append("<subTotal>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(facturaDetalleDTO.getSubTotal().doubleValue()))).append("</subTotal>");
					contenidoXml.append("</detalle>");
					cont++;
				}
			}
			contenidoXml.append("</detallesFactura>");
			
			
			contenidoXml.append("</factura>");
			String contenidoXSL=null;
			contenidoXSL = TransformerUtil.obtenerPlantillaHTML(urlTipoReporte);
			Document docXML = TransformerUtil.stringToXML(contenidoXml.toString());
			Document docXSL = TransformerUtil.stringToXML(contenidoXSL);
			Document result = TransformerUtil.transformar(docXML, docXSL);
			HashMap<String , String> parametros = new HashMap<String, String>();
			result = TransformerUtil.transformar(docXML, docXSL, parametros);
			html = TransformerUtil.xmlToString(result);
		} catch (Exception en) {
			throw new ERPException("Error al procesar plantilla xsl") ;
		}
		return html;
	}
}
