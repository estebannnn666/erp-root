/**
 * 
 */
package ec.com.erp.factura.gestor;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jdom.Document;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.common.resources.ERPMessages;
import ec.com.erp.cliente.mdl.dto.FacturaCabeceraDTO;
import ec.com.erp.cliente.mdl.dto.FacturaDetalleDTO;
import ec.com.erp.cliente.mdl.dto.InventarioDTO;
import ec.com.erp.cliente.mdl.dto.TransaccionDTO;
import ec.com.erp.cliente.mdl.dto.id.FacturaCabeceraID;
import ec.com.erp.cliente.mdl.vo.ReporteVentasVO;
import ec.com.erp.commons.util.ReportesUtil;
import ec.com.erp.factura.dao.IFacturaCabeceraDAO;
import ec.com.erp.inventario.gestor.IInventarioGestor;
import ec.com.erp.secuencia.gestor.ISecuenciaGestor;
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
	private ISecuenciaGestor secuenciaGestor;
	
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
	
	public ISecuenciaGestor getSecuenciaGestor() {
		return secuenciaGestor;
	}

	public void setSecuenciaGestor(ISecuenciaGestor secuenciaGestor) {
		this.secuenciaGestor = secuenciaGestor;
	}
	
	/**
	 * M\u00e9todo para obtener reporte de ventas por articulo vendedor
	 * @param codigoCompania
	 * @param documentoVendedor
	 * @param fechaFacturaInicio
	 * @param fechaFacturaFin
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Collection<ReporteVentasVO> obtenerReorteVentas(Integer codigoCompania, String documentoVendedor, String nombreVendedor, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin) throws ERPException{
		return this.facturaDetalleGestor.obtenerReorteVentas(codigoCompania, documentoVendedor, nombreVendedor, fechaFacturaInicio, fechaFacturaFin);
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
	 * @param codigoVendedor
	 * @return Collection<FacturaCabeceraDTO>
	 * @throws ERPException
	 */
	@Override
	public Collection<FacturaCabeceraDTO> obtenerListaFacturas(Integer codigoCompania, String numeroFactura, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin,  String docClienteProveedor, String nombClienteProveedor, Boolean pagado, Collection<String> tiposDocumentos, Long codigoVendedor) throws ERPException{
		Collection<FacturaCabeceraDTO> listaFacturas = this.facturaCabeceraDAO.obtenerListaFacturas(codigoCompania, numeroFactura, fechaFacturaInicio, fechaFacturaFin, docClienteProveedor, nombClienteProveedor, pagado, tiposDocumentos, codigoVendedor);
		listaFacturas.stream().forEach(factura ->{
			if(CollectionUtils.isEmpty(factura.getPagosFacturaDTOCols())){
				factura.setTotalPagos(BigDecimal.ZERO);
			}else {
				Double totalPago = factura.getPagosFacturaDTOCols().stream().filter(pago -> pago.getValorPago() != null).mapToDouble(pago-> pago.getValorPago().doubleValue()).sum();
				factura.setTotalPagos(BigDecimal.valueOf(totalPago));
			}
		});
		return listaFacturas;
	}
	
	/**
	 * M\u00e9todo para obtener lista de facturas sin despachar.
	 * @param codigoCompania
	 * @param numeroFactura
	 * @param docClienteProveedor
	 * @param nombClienteProveedor
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Collection<FacturaCabeceraDTO> obtenerListaFacturasSinDespachar(Integer codigoCompania, String numeroFactura, String docClienteProveedor, String nombClienteProveedor) throws ERPException{
		return this.facturaCabeceraDAO.obtenerListaFacturasSinDespachar(codigoCompania, numeroFactura, docClienteProveedor, nombClienteProveedor);
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
	public Collection<FacturaCabeceraDTO> obtenerListaFacturasValidarFirebase(Integer codigoCompania, String numeroFactura, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin,  String docClienteProveedor, String nombClienteProveedor, Boolean pagado, Collection<String> tiposDocumentos) throws ERPException{
		return this.facturaCabeceraDAO.obtenerListaFacturasValidarFirebase(codigoCompania, numeroFactura, fechaFacturaInicio, fechaFacturaFin, docClienteProveedor, nombClienteProveedor, pagado, tiposDocumentos);
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
			
			if(facturaCabeceraDTO.getTipoRuc().equals(ERPConstantes.TIPO_RUC_DOS)) {
				this.secuenciaGestor.obtenerSecuencialTabla(FacturaCabeceraID.NOMBRE_SECUENCIA_FACTURA_RUC_UNO);
			}
			
			// Guardamos los detalle de la factura 
			for (FacturaDetalleDTO facturaDetalleDTO : facturaDetalleDTOCols) {
				if(facturaDetalleDTO != null && facturaDetalleDTO.getCodigoArticulo() != null){
					facturaDetalleDTO.getId().setCodigoCompania(Integer.parseInt(ERPConstantes.ESTADO_ACTIVO_NUMERICO));
					facturaDetalleDTO.setCodigoFactura(facturaCabeceraDTO.getId().getCodigoFactura());
					facturaDetalleDTO.setUsuarioRegistro(facturaCabeceraDTO.getUsuarioRegistro());
					if(facturaCabeceraDTO.getCodigoValorTipoDocumento().equals(ERPConstantes.CODIGO_CATALOGO_VALOR_DOCUMENTO_VENTAS) || facturaCabeceraDTO.getCodigoValorTipoDocumento().equals(ERPConstantes.CODIGO_CATALOGO_VALOR_DOCUMENTO_NOTA_VENTA)){
						if(facturaDetalleDTO.getId().getCodigoDetalleFactura() == null) {
							this.registrarInventario(facturaDetalleDTO, facturaCabeceraDTO.getNumeroDocumento(), facturaCabeceraDTO.getNombreClienteProveedor());
						}
					}else {
						if(facturaDetalleDTO.getId().getCodigoDetalleFactura() == null) {
							this.registrarInventarioCompra(facturaDetalleDTO, facturaCabeceraDTO.getNumeroDocumento(), facturaCabeceraDTO.getNombreClienteProveedor());
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
				if(facturaCabeceraDTO.getCodigoValorTipoDocumento().equals(ERPConstantes.CODIGO_CATALOGO_VALOR_DOCUMENTO_VENTAS) || facturaCabeceraDTO.getCodigoValorTipoDocumento().equals(ERPConstantes.CODIGO_CATALOGO_VALOR_DOCUMENTO_NOTA_VENTA)) {
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
			throw new ERPException("Error, {0}",e.getCause());
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
				if(facturaCabeceraDTO.getCodigoValorTipoDocumento().equals(ERPConstantes.CODIGO_CATALOGO_VALOR_DOCUMENTO_VENTAS) || facturaCabeceraDTO.getCodigoValorTipoDocumento().equals(ERPConstantes.CODIGO_CATALOGO_VALOR_DOCUMENTO_NOTA_VENTA)){
					this.reversarInventario(facturaDetalleDTO, facturaCabeceraDTO.getNumeroDocumento(), facturaCabeceraDTO.getNombreClienteProveedor());
				}else {
					this.reversarInventarioCompra(facturaDetalleDTO, facturaCabeceraDTO.getNumeroDocumento(), facturaCabeceraDTO.getNombreClienteProveedor());
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
	public void registrarInventario(FacturaDetalleDTO facturaDetalleDTO, String numeroFactura, String razonSocial) {
		InventarioDTO inventarioDTOAux = this.inventarioGestor.obtenerUltimoInventarioByArticulo(facturaDetalleDTO.getId().getCodigoCompania(), facturaDetalleDTO.getArticuloDTO().getCodigoBarras(), facturaDetalleDTO.getCodigoArticuloUnidadManejo());
		InventarioDTO inventarioDTO = new InventarioDTO();
		if(inventarioDTOAux != null) {
			if(facturaDetalleDTO.getCantidad().intValue() > inventarioDTOAux.getCantidadExistencia().intValue()) {
				throw new ERPException("Error", "No se puede registrar la venta por que no hay existencias suficientes para el articulo "+facturaDetalleDTO.getArticuloDTO().getNombreArticulo()+", unidad de manejo "+facturaDetalleDTO.getArticuloUnidadManejoDTO().getTipoUnidadManejoCatalogoValorDTO().getNombreCatalogoValor()+" X "+facturaDetalleDTO.getArticuloUnidadManejoDTO().getValorUnidadManejo());
			}
			inventarioDTO.getId().setCodigoCompania(facturaDetalleDTO.getId().getCodigoCompania());
			inventarioDTO.setDetalleMoviento(ERPMessages.getString("ec.com.erp.cliente.mensaje.controlado.descripcion.invetarios.venta")+" "+numeroFactura+" Cliente: "+razonSocial);
			inventarioDTO.setArticuloDTO(facturaDetalleDTO.getArticuloDTO());
			inventarioDTO.setCodigoArticulo(facturaDetalleDTO.getCodigoArticulo());
			inventarioDTO.setCodigoArticuloUnidadManejo(facturaDetalleDTO.getCodigoArticuloUnidadManejo());
			inventarioDTO.setCantidadSalida(facturaDetalleDTO.getCantidad());
			inventarioDTO.setValorUnidadSalida(facturaDetalleDTO.getArticuloDTO().getCosto());
			inventarioDTO.setValorTotalSalida(BigDecimal.valueOf(facturaDetalleDTO.getArticuloDTO().getCosto().doubleValue() * facturaDetalleDTO.getCantidad()));
			inventarioDTO.setCantidadExistencia(inventarioDTOAux.getCantidadExistencia().intValue() - facturaDetalleDTO.getCantidad());
			inventarioDTO.setValorUnidadExistencia(facturaDetalleDTO.getArticuloDTO().getCosto());
			Integer totalUnidades = inventarioDTO.getCantidadExistencia() * facturaDetalleDTO.getArticuloUnidadManejoDTO().getValorUnidadManejo();
			inventarioDTO.setValorTotalExistencia(BigDecimal.valueOf(totalUnidades.intValue() * facturaDetalleDTO.getArticuloDTO().getCosto().doubleValue()));
			inventarioDTO.setCantidadEntrada(null);
			inventarioDTO.setValorUnidadEntrada(null);
			inventarioDTO.setValorTotalEntrada(null);
			inventarioDTO.setUsuarioRegistro(facturaDetalleDTO.getUsuarioRegistro());
			this.inventarioGestor.crearActualizarInventario(inventarioDTO);
		}
		else {
			throw new ERPException("Error", "No se puede registrar la venta por que no hay existencias suficientes para el articulo "+facturaDetalleDTO.getArticuloDTO().getNombreArticulo()+", unidad de manejo "+facturaDetalleDTO.getArticuloUnidadManejoDTO().getTipoUnidadManejoCatalogoValorDTO().getNombreCatalogoValor()+" X "+facturaDetalleDTO.getArticuloUnidadManejoDTO().getValorUnidadManejo());
		}
	}
	
	/**
	 * Metodo para registrar movimiento de mercaderia salida
	 * @param facturaDetalleDTO
	 */
	public void registrarInventarioCompra(FacturaDetalleDTO facturaDetalleDTO, String numeroFactura, String razonSocial) {
		try {
			InventarioDTO inventarioDTOAux = this.inventarioGestor.obtenerUltimoInventarioByArticulo(facturaDetalleDTO.getId().getCodigoCompania(), facturaDetalleDTO.getArticuloDTO().getCodigoBarras(),facturaDetalleDTO.getCodigoArticuloUnidadManejo());
			InventarioDTO inventarioDTO = new InventarioDTO();
			inventarioDTO.getId().setCodigoCompania(facturaDetalleDTO.getId().getCodigoCompania());
			inventarioDTO.setDetalleMoviento(ERPMessages.getString("ec.com.erp.cliente.mensaje.controlado.descripcion.invetarios.compra")+" "+numeroFactura+" Proveedor: "+razonSocial);
			inventarioDTO.setArticuloDTO(facturaDetalleDTO.getArticuloDTO());
			inventarioDTO.setCodigoArticulo(facturaDetalleDTO.getCodigoArticulo());
			inventarioDTO.setCodigoArticuloUnidadManejo(facturaDetalleDTO.getCodigoArticuloUnidadManejo());
			inventarioDTO.setCantidadEntrada(facturaDetalleDTO.getCantidad());
			inventarioDTO.setValorUnidadEntrada(facturaDetalleDTO.getValorUnidad());
			inventarioDTO.setValorTotalEntrada(facturaDetalleDTO.getSubTotal());
			if(inventarioDTOAux != null) {
				inventarioDTO.setCantidadExistencia(inventarioDTOAux.getCantidadExistencia().intValue() + facturaDetalleDTO.getCantidad());
			}else {
				inventarioDTO.setCantidadExistencia(facturaDetalleDTO.getCantidad());
			}
			inventarioDTO.setValorUnidadExistencia(facturaDetalleDTO.getArticuloDTO().getCosto());
			Integer totalUnidades = inventarioDTO.getCantidadExistencia() * facturaDetalleDTO.getArticuloUnidadManejoDTO().getValorUnidadManejo();
			inventarioDTO.setValorTotalExistencia(BigDecimal.valueOf(totalUnidades.intValue() * facturaDetalleDTO.getArticuloDTO().getCosto().doubleValue()));
			inventarioDTO.setCantidadSalida(null);
			inventarioDTO.setValorUnidadSalida(null);
			inventarioDTO.setValorTotalSalida(null);
			inventarioDTO.setUsuarioRegistro(facturaDetalleDTO.getUsuarioRegistro());
			this.inventarioGestor.crearActualizarInventario(inventarioDTO);
		}catch (Exception e) {
			throw new ERPException("Error", "Error al afectar inventario");
		}
	}

	/**
	 * Metodo para registrar movimiento de mercaderia reversa
	 * @param facturaDetalleDTO
	 */
	public void reversarInventario(FacturaDetalleDTO facturaDetalleDTO, String numeroFactura, String razonSocial) {
		InventarioDTO inventarioDTOAux = this.inventarioGestor.obtenerUltimoInventarioByArticulo(facturaDetalleDTO.getId().getCodigoCompania(), facturaDetalleDTO.getArticuloDTO().getCodigoBarras(), facturaDetalleDTO.getCodigoArticuloUnidadManejo());
		InventarioDTO inventarioDTO = new InventarioDTO();
		if(inventarioDTOAux != null) {
			inventarioDTO.getId().setCodigoCompania(facturaDetalleDTO.getId().getCodigoCompania());
			inventarioDTO.setDetalleMoviento(ERPMessages.getString("ec.com.erp.cliente.mensaje.controlado.descripcion.invetarios.reversa")+" "+numeroFactura+" Cliente: "+razonSocial);
			inventarioDTO.setArticuloDTO(facturaDetalleDTO.getArticuloDTO());
			inventarioDTO.setCodigoArticulo(facturaDetalleDTO.getCodigoArticulo());
			inventarioDTO.setCodigoArticuloUnidadManejo(facturaDetalleDTO.getCodigoArticuloUnidadManejo());
			inventarioDTO.setCantidadEntrada(facturaDetalleDTO.getCantidad());
			inventarioDTO.setValorUnidadEntrada(facturaDetalleDTO.getArticuloDTO().getCosto());
			inventarioDTO.setValorTotalEntrada(BigDecimal.valueOf(facturaDetalleDTO.getArticuloDTO().getCosto().doubleValue() * facturaDetalleDTO.getCantidad()));
			inventarioDTO.setCantidadExistencia(inventarioDTOAux.getCantidadExistencia().intValue() + facturaDetalleDTO.getCantidad());
			inventarioDTO.setValorUnidadExistencia(facturaDetalleDTO.getArticuloDTO().getCosto());
			Integer totalUnidades = inventarioDTO.getCantidadExistencia() * facturaDetalleDTO.getArticuloUnidadManejoDTO().getValorUnidadManejo();
			inventarioDTO.setValorTotalExistencia(BigDecimal.valueOf(totalUnidades.intValue() * facturaDetalleDTO.getArticuloDTO().getCosto().doubleValue()));
			inventarioDTO.setCantidadSalida(null);
			inventarioDTO.setValorUnidadSalida(null);
			inventarioDTO.setValorTotalSalida(null);
			inventarioDTO.setUsuarioRegistro(facturaDetalleDTO.getUsuarioRegistro());
			this.inventarioGestor.crearActualizarInventario(inventarioDTO);
		}
		else {
			throw new ERPException("Error", "No se puede registrar la resersa por que no hay existencias para el articulo "+facturaDetalleDTO.getArticuloDTO().getNombreArticulo());
		}
	}
	
	/**
	 * Metodo para registrar movimiento de mercaderia reversa
	 * @param facturaDetalleDTO
	 */
	public void reversarInventarioCompra(FacturaDetalleDTO facturaDetalleDTO, String numeroFactura, String razonSocial) {
		InventarioDTO inventarioDTOAux = this.inventarioGestor.obtenerUltimoInventarioByArticulo(facturaDetalleDTO.getId().getCodigoCompania(), facturaDetalleDTO.getArticuloDTO().getCodigoBarras(), facturaDetalleDTO.getCodigoArticuloUnidadManejo());
		InventarioDTO inventarioDTO = new InventarioDTO();
		if(inventarioDTOAux != null) {
			inventarioDTO.getId().setCodigoCompania(facturaDetalleDTO.getId().getCodigoCompania());
			inventarioDTO.setDetalleMoviento(ERPMessages.getString("ec.com.erp.cliente.mensaje.controlado.descripcion.invetarios.reversa.compra")+" "+numeroFactura+" Proveedor: "+razonSocial);
			inventarioDTO.setArticuloDTO(facturaDetalleDTO.getArticuloDTO());
			inventarioDTO.setCodigoArticulo(facturaDetalleDTO.getCodigoArticulo());
			inventarioDTO.setCodigoArticuloUnidadManejo(facturaDetalleDTO.getCodigoArticuloUnidadManejo());
			inventarioDTO.setCantidadSalida(facturaDetalleDTO.getCantidad());
			inventarioDTO.setValorUnidadSalida(facturaDetalleDTO.getValorUnidad());
			inventarioDTO.setValorTotalSalida(facturaDetalleDTO.getSubTotal());
			inventarioDTO.setCantidadExistencia(inventarioDTOAux.getCantidadExistencia().intValue() - facturaDetalleDTO.getCantidad());
			inventarioDTO.setValorUnidadExistencia(facturaDetalleDTO.getArticuloDTO().getCosto());
			Integer totalUnidades = inventarioDTO.getCantidadExistencia() * facturaDetalleDTO.getArticuloUnidadManejoDTO().getValorUnidadManejo();
			inventarioDTO.setValorTotalExistencia(BigDecimal.valueOf(totalUnidades.intValue() * facturaDetalleDTO.getArticuloDTO().getCosto().doubleValue()));
			inventarioDTO.setCantidadEntrada(null);
			inventarioDTO.setValorUnidadEntrada(null);
			inventarioDTO.setValorTotalEntrada(null);
			inventarioDTO.setUsuarioRegistro(facturaDetalleDTO.getUsuarioRegistro());
			this.inventarioGestor.crearActualizarInventario(inventarioDTO);
		}
		else {
			throw new ERPException("Error", "No se puede registrar la resersa por que no hay existencias para el articulo "+facturaDetalleDTO.getArticuloDTO().getNombreArticulo());
		}
	}

	/**
	 * Devuelve html de reporte de facturas
	 * @param facturaCabeceraDTOCols
	 * @return
	 * @throws ERPException
	 */
	public String procesarXMLReporteFacturas(Collection<FacturaCabeceraDTO> facturaCabeceraDTOCols, String titulo, String tipoReporte) throws ERPException{
		StringBuilder contenidoXml = new StringBuilder();
		String html = "";
		String urlTipoReporte = "";
		try{
			BigDecimal total = BigDecimal.ZERO;
			BigDecimal totalAbono = BigDecimal.ZERO;
			BigDecimal totalSaldo = BigDecimal.ZERO;
			Date fechaactual = new Date();
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			String fechaFormateada =  formatoFecha.format(fechaactual);
			DecimalFormat formatoDecimales = new DecimalFormat("#.##");
			formatoDecimales.setMinimumFractionDigits(2);

			if(tipoReporte.equals(ERPConstantes.CODIGO_CATALOGO_VALOR_DOCUMENTO_COMPRAS)) {
				urlTipoReporte = ERPConstantes.PLANTILLA_XSL_REPORTE_COMPRAS;
			}else {
				urlTipoReporte = ERPConstantes.PLANTILLA_XSL_REPORTE_FACTURAS;
			}
			
			contenidoXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
			contenidoXml.append("<facturas>");

			contenidoXml.append("<fechaReporte>").append(StringEscapeUtils.escapeXml(""+fechaFormateada)).append("</fechaReporte>");
			contenidoXml.append("<titulo>").append(StringEscapeUtils.escapeXml(titulo)).append("</titulo>");
			int cont = 1;
			//detalle reposicion
			contenidoXml.append("<listaFacturas>");
			for(FacturaCabeceraDTO facturaCabeceraDTO : facturaCabeceraDTOCols){
				contenidoXml.append("<factura>");
				contenidoXml.append("<numeroFila>").append(StringEscapeUtils.escapeXml(""+cont)).append("</numeroFila>");
				contenidoXml.append("<numeroDocumento>").append(StringEscapeUtils.escapeXml(facturaCabeceraDTO.getNumeroDocumento())).append("</numeroDocumento>");
				contenidoXml.append("<documentoCliente>").append(StringEscapeUtils.escapeXml(""+facturaCabeceraDTO.getRucDocumento())).append("</documentoCliente>");
				contenidoXml.append("<nombreClienteProveedor>").append(StringEscapeUtils.escapeXml(facturaCabeceraDTO.getNombreClienteProveedor())).append("</nombreClienteProveedor>");
				if(!tipoReporte.equals(ERPConstantes.CODIGO_CATALOGO_VALOR_DOCUMENTO_COMPRAS)) {
					contenidoXml.append("<nombreVendedor>").append(StringEscapeUtils.escapeXml(facturaCabeceraDTO.getVendedorDTO() != null ? facturaCabeceraDTO.getVendedorDTO().getPersonaDTO().getPrimerNombre() +" "+facturaCabeceraDTO.getVendedorDTO().getPersonaDTO().getPrimerApellido() : "N/D")).append("</nombreVendedor>");
				}
				contenidoXml.append("<fechaEmision>").append(StringEscapeUtils.escapeXml(formatoFecha.format(facturaCabeceraDTO.getFechaDocumento()))).append("</fechaEmision>");
				contenidoXml.append("<valorTotal>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(facturaCabeceraDTO.getTotalCuenta().doubleValue()))).append("</valorTotal>");
				contenidoXml.append("<valorAbono>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(facturaCabeceraDTO.getTotalPagos().doubleValue()))).append("</valorAbono>");
				contenidoXml.append("<valorSaldo>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(facturaCabeceraDTO.getTotalCuenta().doubleValue() - facturaCabeceraDTO.getTotalPagos().doubleValue()))).append("</valorSaldo>");
				contenidoXml.append("<estado>").append(StringEscapeUtils.escapeXml(facturaCabeceraDTO.getPagado() ? "SI":"NO")).append("</estado>");
				contenidoXml.append("</factura>");
				cont++;
				total = total.add(facturaCabeceraDTO.getTotalCuenta());
				totalAbono = totalAbono.add(facturaCabeceraDTO.getTotalPagos());
				totalSaldo = totalSaldo.add(facturaCabeceraDTO.getTotalCuenta().subtract(facturaCabeceraDTO.getTotalPagos()));
						
			}
			contenidoXml.append("</listaFacturas>");			
			contenidoXml.append("<totalPago>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(total.doubleValue()))).append("</totalPago>");
			contenidoXml.append("<totalAbono>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(totalAbono.doubleValue()))).append("</totalAbono>");
			contenidoXml.append("<totalSaldo>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(totalSaldo.doubleValue()))).append("</totalSaldo>");
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
			throw new ERPException("Error", "Error al procesar plantilla xsl") ;
		}
		return html;
	}
	
	/**
	 * Devuelve html de reporte de ventas
	 * @param reporteVentasCol
	 * @return
	 * @throws ERPException
	 */
	public String procesarXMLReporteVentas(Date fechaInicio, Date fechaFin, Collection<ReporteVentasVO> reporteVentasCol) throws ERPException{
		StringBuilder contenidoXml = new StringBuilder();
		String html = "";
		String urlTipoReporte = "";
		try{
			Long totalVenta = 0L;
			BigDecimal totalVendido = BigDecimal.ZERO;
			BigDecimal totalComision = BigDecimal.ZERO;
			
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			String fechaFormateadaInicio =  formatoFecha.format(fechaInicio);
			String fechaFormateadaFin =  formatoFecha.format(fechaFin);
			
			DecimalFormat formatoDecimales = new DecimalFormat("#.##");
			formatoDecimales.setMinimumFractionDigits(2);

			urlTipoReporte = ERPConstantes.PLANTILLA_XSL_REPORTE_VENTAS;
			
			contenidoXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
			contenidoXml.append("<facturas>");

			contenidoXml.append("<fechaInicio>").append(StringEscapeUtils.escapeXml(""+fechaFormateadaInicio)).append("</fechaInicio>");
			contenidoXml.append("<fechaFin>").append(StringEscapeUtils.escapeXml(""+fechaFormateadaFin)).append("</fechaFin>");
			int cont = 1;
			//detalle reposicion
			contenidoXml.append("<listaFacturas>");
			for(ReporteVentasVO reporteVentas : reporteVentasCol){
				contenidoXml.append("<factura>");
				contenidoXml.append("<numeroFila>").append(StringEscapeUtils.escapeXml(""+cont)).append("</numeroFila>");
				contenidoXml.append("<nombreVendodor>").append(StringEscapeUtils.escapeXml(reporteVentas.getNombreCompleto())).append("</nombreVendodor>");
				contenidoXml.append("<nombreArticulo>").append(StringEscapeUtils.escapeXml(""+reporteVentas.getNombreArticulo())).append("</nombreArticulo>");
				contenidoXml.append("<unidadManejo>").append(StringEscapeUtils.escapeXml(reporteVentas.getCodigoValorUnidadManejo()+" x "+reporteVentas.getValorUnidadManejo())).append("</unidadManejo>");
				contenidoXml.append("<precioMayorista>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(reporteVentas.getPrecioMayorista().doubleValue()))).append("</precioMayorista>");
				contenidoXml.append("<precioMinorista>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(reporteVentas.getPrecioMinorista().doubleValue()))).append("</precioMinorista>");
				contenidoXml.append("<porcentajeComision>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(reporteVentas.getPorcentajeComision().doubleValue()))).append("</porcentajeComision>");
				contenidoXml.append("<porcentajeComisionMayor>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(reporteVentas.getPorcentajeComisionMayor().doubleValue()))).append("</porcentajeComisionMayor>");
				contenidoXml.append("<tipoCliente>").append(StringEscapeUtils.escapeXml(reporteVentas.getTipoCliente())).append("</tipoCliente>");
				contenidoXml.append("<cantidadVendida>").append(StringEscapeUtils.escapeXml(""+reporteVentas.getCantidadVendida())).append("</cantidadVendida>");
				contenidoXml.append("<valorVendido>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(reporteVentas.getValorVendido().doubleValue()))).append("</valorVendido>");
				contenidoXml.append("<comision>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(reporteVentas.getValoComisionTotal().doubleValue()))).append("</comision>");
				contenidoXml.append("</factura>");
				cont++;
				totalVenta = totalVenta + reporteVentas.getCantidadVendida();
				totalVendido = totalVendido.add(reporteVentas.getValorVendido());
				totalComision = totalComision.add(reporteVentas.getValoComisionTotal());
			}
			contenidoXml.append("</listaFacturas>");			
			contenidoXml.append("<totalVenta>").append(StringEscapeUtils.escapeXml(""+totalVenta)).append("</totalVenta>");
			contenidoXml.append("<totalVendido>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(totalVendido.doubleValue()))).append("</totalVendido>");
			contenidoXml.append("<totalComision>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(totalComision.doubleValue()))).append("</totalComision>");
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
			throw new ERPException("Error", "Error al procesar plantilla xsl") ;
		}
		return html;
	}
	
	/**
	 * Metodo para obtener el valor de venta por mes y tipo
	 * @param codigoCompania
	 * @param fechaInicio
	 * @param fechaFin
	 * @param tipoDocumentos
	 * @return
	 * @throws ERPException
	 */
	@Override
	public BigDecimal obtenerComprasVentas(Integer codigoCompania, Timestamp fechaInicio, Timestamp fechaFin, Collection<String> tiposDocumentos, Boolean pagada) throws ERPException{
		return this.facturaCabeceraDAO.obtenerComprasVentas(codigoCompania, fechaInicio, fechaFin, tiposDocumentos, pagada);
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
	public Long obtenerNumeroFacturasComprasVentas(Integer codigoCompania, Collection<String> tipoDocumento, Boolean pagada) throws ERPException{
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
	 * Obtener bytes nota de venta
	 * @param facturaCabeceraDTO
	 * @return
	 * @throws IOException
	 */
	public byte[] generateNotaVenta(FacturaCabeceraDTO facturaCabeceraDTO) throws IOException {
		String html = this.obtenerXMLImprimirFacturaVenta(facturaCabeceraDTO);
		String xhtml = ReportesUtil.htmlToXhtml(html);
		byte[] contenido = ReportesUtil.xhtmlToPdf(xhtml, "notaventa.pdf");
		return contenido;
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
			String base64String = "data:image/jpeg;base64,"+ERPConstantes.LOGO_NOTAS_VENTA;
			urlTipoReporte = ERPConstantes.PLANTILLA_XSL_IMPRIMIR_NOTA_VETA;
			contenidoXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
			contenidoXml.append("<factura>");
			contenidoXml.append("<imagen>").append(StringEscapeUtils.escapeXml(base64String)).append("</imagen>");
			contenidoXml.append("<codigoFactura>").append(StringEscapeUtils.escapeXml(""+facturaCabeceraDTO.getCodigoReferenciaFactura())).append("</codigoFactura>");
			contenidoXml.append("<numeroFactura>").append(StringEscapeUtils.escapeXml(""+facturaCabeceraDTO.getNumeroDocumento())).append("</numeroFactura>");
			contenidoXml.append("<fechaVenta>").append(StringEscapeUtils.escapeXml(""+formatoFecha.format(facturaCabeceraDTO.getFechaDocumento()))).append("</fechaVenta>");
			contenidoXml.append("<documentoCliente>").append(StringEscapeUtils.escapeXml(""+facturaCabeceraDTO.getRucDocumento())).append("</documentoCliente>");
			contenidoXml.append("<nombreCliente>").append(StringEscapeUtils.escapeXml(""+facturaCabeceraDTO.getNombreClienteProveedor())).append("</nombreCliente>");
			contenidoXml.append("<direccion>").append(StringEscapeUtils.escapeXml(""+facturaCabeceraDTO.getDireccion())).append("</direccion>");
			contenidoXml.append("<telefono>").append(StringEscapeUtils.escapeXml(""+facturaCabeceraDTO.getTelefono())).append("</telefono>");
			contenidoXml.append("<subtotal>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(facturaCabeceraDTO.getSubTotal() == null ? 0 : facturaCabeceraDTO.getSubTotal().doubleValue()))).append("</subtotal>");
			contenidoXml.append("<descuento>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(facturaCabeceraDTO.getDescuento() == null ? 0 : facturaCabeceraDTO.getDescuento().doubleValue()))).append("</descuento>");
			contenidoXml.append("<tarifaCero>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(facturaCabeceraDTO.getTotalSinImpuestos() == null ? 0 : facturaCabeceraDTO.getTotalSinImpuestos().doubleValue()))).append("</tarifaCero>");
			contenidoXml.append("<tarifaDoce>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(facturaCabeceraDTO.getTotalImpuestos() == null ? 0 : facturaCabeceraDTO.getTotalImpuestos().doubleValue()))).append("</tarifaDoce>");
			contenidoXml.append("<iva>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(facturaCabeceraDTO.getTotalIva() == null ? 0 : facturaCabeceraDTO.getTotalIva().doubleValue()))).append("</iva>");
			contenidoXml.append("<total>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(facturaCabeceraDTO.getTotalCuenta().doubleValue()))).append("</total>");
			int cont = 1;
			//detalle reposicion
			contenidoXml.append("<detallesFactura>");
			for(FacturaDetalleDTO facturaDetalleDTO : facturaCabeceraDTO.getFacturaDetalleDTOCols()){
				if(facturaDetalleDTO.getId().getCodigoDetalleFactura() != null) {
					String unidadManejo = facturaDetalleDTO.getArticuloUnidadManejoDTO().getTipoUnidadManejoCatalogoValorDTO().getNombreCatalogoValor().equals("UNIDAD") ? "UNIDADES" : facturaDetalleDTO.getArticuloUnidadManejoDTO().getTipoUnidadManejoCatalogoValorDTO().getNombreCatalogoValor().concat(" X ").concat(""+facturaDetalleDTO.getArticuloUnidadManejoDTO().getValorUnidadManejo());
					contenidoXml.append("<detalle>");
					contenidoXml.append("<nroDetalle>").append(StringEscapeUtils.escapeXml(""+cont)).append("</nroDetalle>");
					contenidoXml.append("<cantidad>").append(StringEscapeUtils.escapeXml(""+facturaDetalleDTO.getCantidad())).append("</cantidad>");
					contenidoXml.append("<unidadManejo>").append(StringEscapeUtils.escapeXml(unidadManejo)).append("</unidadManejo>");
					contenidoXml.append("<descripcion>").append(StringEscapeUtils.escapeXml(facturaDetalleDTO.getDescripcion())).append("</descripcion>");
					contenidoXml.append("<valorUnitario>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(facturaDetalleDTO.getValorUnidad().doubleValue()))).append("</valorUnitario>");
					contenidoXml.append("<subTotal>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(facturaDetalleDTO.getSubTotal().doubleValue()))).append("</subTotal>");
					contenidoXml.append("</detalle>");
					cont++;
				}
			}
			while(cont < 20) {
				contenidoXml.append("<detalle>");
				contenidoXml.append("<nroDetalle>").append(StringEscapeUtils.escapeXml(""+cont)).append("</nroDetalle>");
				contenidoXml.append("<cantidad>").append(StringEscapeUtils.escapeXml("")).append("</cantidad>");
				contenidoXml.append("<unidadManejo>").append(StringEscapeUtils.escapeXml("")).append("</unidadManejo>");
				contenidoXml.append("<descripcion>").append(StringEscapeUtils.escapeXml("")).append("</descripcion>");
				contenidoXml.append("<valorUnitario>").append(StringEscapeUtils.escapeXml("")).append("</valorUnitario>");
				contenidoXml.append("<subTotal>").append(StringEscapeUtils.escapeXml("")).append("</subTotal>");
				contenidoXml.append("</detalle>");
				cont++;
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
			throw new ERPException("Error", "Error al procesar plantilla xsl") ;
		}
		return html;
	}
	
	/**
	 * Actualizar estado de factura.
	 * @param codigoCompania
	 * @param codigoFactura
	 * @param userId
	 * @param codigoValorEstado
	 * @throws ERPException
	 */
	@Override
	public void actualizarFacturaEstadoDespachado(Integer codigoCompania, Long codigoFactura, String userId, String codigoValorEstado)throws ERPException{
		this.facturaCabeceraDAO.actualizarFacturaEstadoDespachado(codigoCompania, codigoFactura, userId, codigoValorEstado);
	}
}
