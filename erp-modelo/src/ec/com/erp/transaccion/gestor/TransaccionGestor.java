/**
 * 
 */
package ec.com.erp.transaccion.gestor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jdom.Document;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.FacturaCabeceraDTO;
import ec.com.erp.cliente.mdl.dto.PagosFacturaDTO;
import ec.com.erp.cliente.mdl.dto.TransaccionDTO;
import ec.com.erp.factura.dao.IFacturaCabeceraDAO;
import ec.com.erp.transaccion.dao.ITransaccionDAO;
import ec.com.erp.utilitario.commons.util.TransformerUtil;

/**
 * @author Esteban Gudino
 * 2018-05-05
 */
public class TransaccionGestor implements ITransaccionGestor {

	private ITransaccionDAO transaccionDAO;
	private IFacturaCabeceraDAO facturaCabeceraDAO;
	
	
	public ITransaccionDAO getTransaccionDAO() {
		return transaccionDAO;
	}

	public void setTransaccionDAO(ITransaccionDAO transaccionDAO) {
		this.transaccionDAO = transaccionDAO;
	}

	public IFacturaCabeceraDAO getFacturaCabeceraDAO() {
		return facturaCabeceraDAO;
	}

	public void setFacturaCabeceraDAO(IFacturaCabeceraDAO facturaCabeceraDAO) {
		this.facturaCabeceraDAO = facturaCabeceraDAO;
	}

	/**
	 * M\u00e9todo para obtener lista de transaccciones
	 * @param codigoCompania
	 * @param fechaTransaccionInicio
	 * @param fechaTransaccionFin
	 * @param tipoTransaccion
	 * @return Collection<TransaccionDTO>
	 * @throws ERPException
	 */
	@Override
	public Collection<TransaccionDTO> obtenerListaTransacciones(Integer codigoCompania, Timestamp fechaTransaccionInicio, Timestamp fechaTransaccionFin, String tipoTransaccion) throws ERPException{
		return this.transaccionDAO.obtenerListaTransacciones(codigoCompania, fechaTransaccionInicio, fechaTransaccionFin, tipoTransaccion);
	}
	
	/**
	 * M\u00e9todo para guardar transacciones
	 * @param transaccionDTO
	 * @throws ERPException
	 */
	@Override
	public void guardarTransaccion(TransaccionDTO transaccionDTO) throws ERPException{
		try{
			// Guardamos la cabecera de la factura
			transaccionDTO.setCodigoTipoTransaccion(ERPConstantes.CODIGO_CATALOGO_TIPOS_TRANSACCION);
			this.transaccionDAO.guardarTransaccion(transaccionDTO);
		} catch (ERPException e) {
			transaccionDTO.getId().setCodigoTransaccion(null);
			throw new ERPException("Error, {0}",e.getMessage()) ;
		} catch (Exception e) {
			transaccionDTO.getId().setCodigoTransaccion(null);
			throw new ERPException("Error, {0}",e.getMessage());
		} 
	}
	
	/**
	 * M\u00e9todo para obtener lista de pagos por factura
	 * @param codigoCompania
	 * @param codigoFactura
	 * @return Collection<PagosFacturaDTO>
	 * @throws ERPException
	 */
	public Collection<PagosFacturaDTO> obtenerListaPagosFactura(Integer codigoCompania, Long codigoFactura) throws ERPException{
		return this.transaccionDAO.obtenerListaPagosFactura(codigoCompania, codigoFactura);
	}
	
	/**
	 * M\u00e9todo para guardar pagos por factura
	 * @param transaccionDTO
	 * @throws ERPException
	 */
	public void guardarPago(String tipoFactura, PagosFacturaDTO pagosFacturaDTO) throws ERPException{
		try{
			// Guardamos la cabecera de la factura
			this.transaccionDAO.guardarPago(pagosFacturaDTO);
			
			// Guardamos la transaccion
			TransaccionDTO transaccionDTO = new TransaccionDTO();
			transaccionDTO.getId().setCodigoCompania(pagosFacturaDTO.getId().getCodigoCompania());
			transaccionDTO.setCodigoValorTransaccion(tipoFactura);
			transaccionDTO.setConcepto(pagosFacturaDTO.getDescripcion());
			transaccionDTO.setValorTransaccion(pagosFacturaDTO.getValorPago());
			transaccionDTO.setFechaTransaccion(pagosFacturaDTO.getFechaPago());
			transaccionDTO.setUsuarioRegistro(pagosFacturaDTO.getUsuarioRegistro());
			transaccionDTO.setCodigoTipoTransaccion(ERPConstantes.CODIGO_CATALOGO_TIPOS_TRANSACCION);
			this.transaccionDAO.guardarTransaccion(transaccionDTO);
			BigDecimal totalPagos = this.transaccionDAO.obtenerTotalPagos(pagosFacturaDTO.getId().getCodigoCompania(), pagosFacturaDTO.getCodigoFactura());
			FacturaCabeceraDTO facturaCabeceraDTO = this.facturaCabeceraDAO.obtenerFacturaPorCodigo(pagosFacturaDTO.getId().getCodigoCompania(), pagosFacturaDTO.getCodigoFactura());
			if(totalPagos.doubleValue() >= facturaCabeceraDTO.getTotalCuenta().doubleValue()) {
				facturaCabeceraDTO.setPagado(Boolean.TRUE);
				facturaCabeceraDTO.setUsuarioRegistro(pagosFacturaDTO.getUsuarioRegistro());
				this.facturaCabeceraDAO.guardarActualizarFacturaCabecera(facturaCabeceraDTO);
			}
		} catch (ERPException e) {
			pagosFacturaDTO.getId().setCodigoPago(null);
			throw new ERPException("Error, {0}",e.getMessage()) ;
		} catch (Exception e) {
			pagosFacturaDTO.getId().setCodigoPago(null);
			throw new ERPException("Error, {0}",e.getMessage());
		} 
	}
	
	/**
	 * Devuelve html de reporte de existencias
	 * @param transaccionDTOCols
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 * @throws ERPException
	 */
	public String procesarXMLReporteTransacciones(Collection<TransaccionDTO> transaccionDTOCols, Date fechaInicio, Date fechaFin) throws ERPException{
		StringBuilder contenidoXml = new StringBuilder();
		String html = "";
		String urlTipoReporte = "";
		Collection<TransaccionDTO> transaccionIngresosDTOCols = new ArrayList<>();
		Collection<TransaccionDTO> transaccionGastosDTOCols = new ArrayList<>();
		try{
			BigDecimal ingresos = BigDecimal.ZERO;
			BigDecimal gastos = BigDecimal.ZERO;
			
			// Obtener ingresos
			transaccionIngresosDTOCols = transaccionDTOCols.stream()
					.filter(transac -> transac.getCodigoValorTransaccion().equals(ERPConstantes.CODIGO_CATALOGO_VALOR_TRANSACCION_INGRESO))
					.collect(Collectors.toList());
			
			
			// Obtener gastos		
			transaccionGastosDTOCols = transaccionDTOCols.stream()
					.filter(transac -> transac.getCodigoValorTransaccion().equals(ERPConstantes.CODIGO_CATALOGO_VALOR_TRANSACCION_GASTO))
					.collect(Collectors.toList());
			
			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			DecimalFormat formatoDecimales = new DecimalFormat("#.##");
			formatoDecimales.setMinimumFractionDigits(2);

			urlTipoReporte = ERPConstantes.PLANTILLA_XSL_REPORTE_CAJA;
			
			contenidoXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
			contenidoXml.append("<reporte>");
			contenidoXml.append("<fechaReporte>").append(StringEscapeUtils.escapeXml(""+formatoFecha.format(new Date()))).append("</fechaReporte>");
			contenidoXml.append("<fechaInicio>").append(StringEscapeUtils.escapeXml(""+formatoFecha.format(fechaInicio))).append("</fechaInicio>");
			contenidoXml.append("<fechaFin>").append(StringEscapeUtils.escapeXml(""+formatoFecha.format(fechaFin))).append("</fechaFin>");
			
			if(CollectionUtils.isNotEmpty(transaccionIngresosDTOCols)) {
				// Total ingresos
				ingresos = transaccionIngresosDTOCols.stream()
						.filter(ingreso ->ingreso != null && ingreso.getValorTransaccion()!=null)
						.map(TransaccionDTO::getValorTransaccion)
						.reduce(BigDecimal.ZERO, BigDecimal::add);
				
				//detalle reposicion
				contenidoXml.append("<listaIngresos>");
				int cont = 1;
				for(TransaccionDTO transaccionIngresos : transaccionIngresosDTOCols){
					contenidoXml.append("<ingreso>");
					contenidoXml.append("<numero>").append(StringEscapeUtils.escapeXml(""+cont)).append("</numero>");
					contenidoXml.append("<numeroTransaccion>").append(StringEscapeUtils.escapeXml(""+transaccionIngresos.getNumeroTransaccion())).append("</numeroTransaccion>");
					contenidoXml.append("<concepto>").append(StringEscapeUtils.escapeXml(transaccionIngresos.getConcepto())).append("</concepto>");
					contenidoXml.append("<fechaTransaccion>").append(StringEscapeUtils.escapeXml(""+formatoFecha.format(transaccionIngresos.getFechaTransaccion()))).append("</fechaTransaccion>");
					contenidoXml.append("<valorTransaccion>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(transaccionIngresos.getValorTransaccion()))).append("</valorTransaccion>");
					contenidoXml.append("</ingreso>");
					cont++;
				}
				contenidoXml.append("</listaIngresos>");	
			}
			
			if(CollectionUtils.isNotEmpty(transaccionGastosDTOCols)) {
				// Total gastos
				gastos = transaccionGastosDTOCols.stream()
						.filter(ingreso ->ingreso != null && ingreso.getValorTransaccion()!=null)
						.map(TransaccionDTO::getValorTransaccion)
						.reduce(BigDecimal.ZERO, BigDecimal::add);
				
				//detalle reposicion
				contenidoXml.append("<listaGastos>");
				int cont = 1;
				for(TransaccionDTO transaccionGastos : transaccionGastosDTOCols){
					contenidoXml.append("<gasto>");
					contenidoXml.append("<numero>").append(StringEscapeUtils.escapeXml(""+cont)).append("</numero>");
					contenidoXml.append("<numeroTransaccion>").append(StringEscapeUtils.escapeXml(""+transaccionGastos.getNumeroTransaccion())).append("</numeroTransaccion>");
					contenidoXml.append("<concepto>").append(StringEscapeUtils.escapeXml(transaccionGastos.getConcepto())).append("</concepto>");
					contenidoXml.append("<fechaTransaccion>").append(StringEscapeUtils.escapeXml(""+formatoFecha.format(transaccionGastos.getFechaTransaccion()))).append("</fechaTransaccion>");
					contenidoXml.append("<valorTransaccion>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(transaccionGastos.getValorTransaccion()))).append("</valorTransaccion>");
					contenidoXml.append("</gasto>");
					cont++;
				}
				contenidoXml.append("</listaGastos>");	
			}
			contenidoXml.append("<tatalIngresos>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(ingresos))).append("</tatalIngresos>");
			contenidoXml.append("<totalGastos>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(gastos))).append("</totalGastos>");
			contenidoXml.append("<ganancias>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(ingresos.subtract(gastos)))).append("</ganancias>");
			contenidoXml.append("</reporte>");
			
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
}
