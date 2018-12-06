package ec.com.erp.inventario.gestor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.jdom.Document;

import ec.com.erp.articulo.gestor.IArticuloGestor;
import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ArticuloDTO;
import ec.com.erp.cliente.mdl.dto.InventarioDTO;
import ec.com.erp.inventario.dao.IInventarioDAO;
import ec.com.erp.utilitario.commons.util.TransformerUtil;

public class InventarioGestor implements IInventarioGestor{

	private IInventarioDAO inventarioDAO;
	private IArticuloGestor articuloGestor;
	
	public IInventarioDAO getInventarioDAO() {
		return inventarioDAO;
	}

	public void setInventarioDAO(IInventarioDAO inventarioDAO) {
		this.inventarioDAO = inventarioDAO;
	}

	public IArticuloGestor getArticuloGestor() {
		return articuloGestor;
	}

	public void setArticuloGestor(IArticuloGestor articuloGestor) {
		this.articuloGestor = articuloGestor;
	}

	/**
	 * M\u00e9todo para obtener kardex por codigo de barra y fechas
	 * @param codigoCompania
	 * @param codigoBarras
	 * @param fechaFacturaInicio
	 * @param fechaFacturaFin
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Collection<InventarioDTO> obtenerListaInventarioByArticuloFechas(Integer codigoCompania, String codigoBarras, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin) throws ERPException{
		return this.inventarioDAO.obtenerListaInventarioByArticuloFechas(codigoCompania, codigoBarras, fechaFacturaInicio, fechaFacturaFin);
	}

	/**
	 * M\u00e9todo para obtener existencias por codigo de barra y fechas
	 * @param codigoCompania
	 * @param codigoBarras
	 * @param fechaFacturaInicio
	 * @param fechaFacturaFin
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Collection<InventarioDTO> obtenerListaExistenciasByArticuloFechas(Integer codigoCompania, String codigoBarras, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin) throws ERPException{
		return this.inventarioDAO.obtenerListaExistenciasByArticuloFechas(codigoCompania, codigoBarras, fechaFacturaInicio, fechaFacturaFin);
	}
	
	/**
	 * M\u00e9todo para obtener kardex por codigo de barra
	 * @param codigoCompania
	 * @param codigoBarras
	 * @return
	 * @throws ERPException
	 */
	@Override
	public InventarioDTO obtenerUltimoInventarioByArticulo(Integer codigoCompania, String codigoBarras) throws ERPException{
		return this.inventarioDAO.obtenerUltimoInventarioByArticulo(codigoCompania, codigoBarras);
	}
	
	/**
	 * M\u00e9todo para crear o actualizar 
	 * @param inventarioDTO
	 * @throws ERPException
	 */
	@Override
	public void crearActualizarInventario(InventarioDTO inventarioDTO)throws ERPException{
		Boolean actualizarArticulo = Boolean.FALSE;
		String codigoBarras = StringUtils.EMPTY;
		InventarioDTO inventarioDTOAux = this.inventarioDAO.obtenerUltimoInventarioByArticulo(inventarioDTO.getId().getCodigoCompania(), inventarioDTO.getArticuloDTO().getCodigoBarras());
		if(inventarioDTOAux != null) {
			inventarioDTOAux.setEsUltimoRegistro(ERPConstantes.ESTADO_INACTIVO_NUMERICO);
			this.inventarioDAO.crearActualizarInventario(inventarioDTOAux);
			actualizarArticulo = Boolean.TRUE;
			codigoBarras = inventarioDTO.getArticuloDTO().getCodigoBarras();
		}
		inventarioDTO.setFechaMovimiento(new Date());
		inventarioDTO.setEsUltimoRegistro(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
		this.inventarioDAO.crearActualizarInventario(inventarioDTO);
		// Se actualiza el stock del articulo
		if(actualizarArticulo){
			Collection<ArticuloDTO> articulosDTOCols = this.articuloGestor.obtenerListaArticulos(inventarioDTO.getId().getCodigoCompania(), codigoBarras, null);
			ArticuloDTO articuloDTO = articulosDTOCols.iterator().next();
			articuloDTO.setCantidadStock(inventarioDTO.getCantidadExistencia());
			articuloDTO.setPrecio(inventarioDTO.getValorUnidadExistencia());
			this.articuloGestor.transGuardarActualizarArticulo(articuloDTO);
		}
	}
	
	/**
	 * Devuelve html de reporte de inventarios
	 * @param inventarioDTOCols
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 * @throws ERPException
	 */
	public String procesarXMLReporteKardex(Collection<InventarioDTO> inventarioDTOCols, Date fechaInicio, Date fechaFin) throws ERPException{
		StringBuilder contenidoXml = new StringBuilder();
		String html = "";
		String urlTipoReporte = "";
		try{
			BigDecimal total = BigDecimal.ZERO;
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			DecimalFormat formatoDecimales = new DecimalFormat("#.##");
			formatoDecimales.setMinimumFractionDigits(2);

			urlTipoReporte = ERPConstantes.PLANTILLA_XSL_REPORTE_KARDEX;
			
			if(CollectionUtils.isNotEmpty(inventarioDTOCols)) {
				ArticuloDTO articulo = inventarioDTOCols.iterator().next().getArticuloDTO();
				contenidoXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
				contenidoXml.append("<kardex>");
				contenidoXml.append("<codigoBarras>").append(StringEscapeUtils.escapeXml(""+articulo.getCodigoBarras())).append("</codigoBarras>");
				contenidoXml.append("<nombreArticulo>").append(StringEscapeUtils.escapeXml(""+articulo.getNombreArticulo())).append("</nombreArticulo>");
				contenidoXml.append("<pesoArticulo>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(articulo.getPeso()))).append("</pesoArticulo>");
				contenidoXml.append("<precioArticulo>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(articulo.getPrecio()))).append("</precioArticulo>");
				contenidoXml.append("<fechaInicio>").append(StringEscapeUtils.escapeXml(""+formatoFecha.format(fechaInicio))).append("</fechaInicio>");
				contenidoXml.append("<fechaFin>").append(StringEscapeUtils.escapeXml(""+formatoFecha.format(fechaFin))).append("</fechaFin>");
				//detalle reposicion
				contenidoXml.append("<listaMovimientos>");
				for(InventarioDTO inventarioDTO : inventarioDTOCols){
					contenidoXml.append("<movimiento>");
					contenidoXml.append("<fechaMovimiento>").append(StringEscapeUtils.escapeXml(StringEscapeUtils.escapeXml(""+formatoFecha.format(inventarioDTO.getFechaMovimiento())))).append("</fechaMovimiento>");
					contenidoXml.append("<detalleMovimiento>").append(StringEscapeUtils.escapeXml(inventarioDTO.getDetalleMoviento())).append("</detalleMovimiento>");
					contenidoXml.append("<cantidadEntrada>").append(StringEscapeUtils.escapeXml(inventarioDTO.getCantidadEntrada() == null ? "-" : ""+inventarioDTO.getCantidadEntrada())).append("</cantidadEntrada>");
					contenidoXml.append("<valorUnidadEntrada>").append(StringEscapeUtils.escapeXml(inventarioDTO.getValorUnidadEntrada() == null ? "-" : ""+formatoDecimales.format(inventarioDTO.getValorUnidadEntrada().doubleValue()))).append("</valorUnidadEntrada>");
					contenidoXml.append("<valorTotalEntrada>").append(StringEscapeUtils.escapeXml(inventarioDTO.getValorTotalEntrada() == null ? "-" : ""+formatoDecimales.format(inventarioDTO.getValorTotalEntrada().doubleValue()))).append("</valorTotalEntrada>");
					contenidoXml.append("<cantidadSalida>").append(StringEscapeUtils.escapeXml(inventarioDTO.getCantidadSalida() == null ? "-" : ""+inventarioDTO.getCantidadSalida())).append("</cantidadSalida>");
					contenidoXml.append("<valorUnidadSalida>").append(StringEscapeUtils.escapeXml(inventarioDTO.getValorUnidadSalida() == null ? "-" : ""+formatoDecimales.format(inventarioDTO.getValorUnidadSalida().doubleValue()))).append("</valorUnidadSalida>");
					contenidoXml.append("<valorTotalSalida>").append(StringEscapeUtils.escapeXml(inventarioDTO.getValorTotalSalida() == null ? "-" : ""+formatoDecimales.format(inventarioDTO.getValorTotalSalida().doubleValue()))).append("</valorTotalSalida>");
					contenidoXml.append("<cantidadExistencia>").append(StringEscapeUtils.escapeXml(inventarioDTO.getCantidadExistencia() == null ? "-" : ""+inventarioDTO.getCantidadExistencia())).append("</cantidadExistencia>");
					contenidoXml.append("<valorUnidadExistencia>").append(StringEscapeUtils.escapeXml(inventarioDTO.getValorUnidadExistencia() == null ? "-" : ""+formatoDecimales.format(inventarioDTO.getValorUnidadExistencia().doubleValue()))).append("</valorUnidadExistencia>");
					contenidoXml.append("<valorTotalExistencia>").append(StringEscapeUtils.escapeXml(inventarioDTO.getValorTotalExistencia() == null ? "-" : ""+formatoDecimales.format(inventarioDTO.getValorTotalExistencia().doubleValue()))).append("</valorTotalExistencia>");
					contenidoXml.append("</movimiento>");
				}
				contenidoXml.append("</listaMovimientos>");			
				contenidoXml.append("<totalPago>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(total.doubleValue()))).append("</totalPago>");
				contenidoXml.append("</kardex>");
			}
			
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
	 * Devuelve html de reporte de existencias
	 * @param inventarioDTOCols
	 * @return
	 * @throws ERPException
	 */
	public String procesarXMLReporteExistencias(Collection<InventarioDTO> inventarioDTOCols) throws ERPException{
		StringBuilder contenidoXml = new StringBuilder();
		String html = "";
		String urlTipoReporte = "";
		try{
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			DecimalFormat formatoDecimales = new DecimalFormat("#.##");
			formatoDecimales.setMinimumFractionDigits(2);

			urlTipoReporte = ERPConstantes.PLANTILLA_XSL_REPORTE_EXISTENCIA;
			
			if(CollectionUtils.isNotEmpty(inventarioDTOCols)) {
				contenidoXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
				contenidoXml.append("<reporte>");
				contenidoXml.append("<fechaReporte>").append(StringEscapeUtils.escapeXml(""+formatoFecha.format(new Date()))).append("</fechaReporte>");
				//detalle reposicion
				contenidoXml.append("<listaMovimientos>");
				int cont = 1;
				for(InventarioDTO inventarioDTO : inventarioDTOCols){
					contenidoXml.append("<movimiento>");
					contenidoXml.append("<numero>").append(StringEscapeUtils.escapeXml(""+cont)).append("</numero>");
					contenidoXml.append("<fechaMovimiento>").append(StringEscapeUtils.escapeXml(StringEscapeUtils.escapeXml(""+formatoFecha.format(inventarioDTO.getFechaMovimiento())))).append("</fechaMovimiento>");
					contenidoXml.append("<codigoBarras>").append(StringEscapeUtils.escapeXml(inventarioDTO.getArticuloDTO().getCodigoBarras())).append("</codigoBarras>");
					contenidoXml.append("<nombreArticulo>").append(StringEscapeUtils.escapeXml(inventarioDTO.getArticuloDTO().getNombreArticulo())).append("</nombreArticulo>");
					contenidoXml.append("<detalleMovimiento>").append(StringEscapeUtils.escapeXml(inventarioDTO.getDetalleMoviento())).append("</detalleMovimiento>");
					contenidoXml.append("<cantidadExistencia>").append(StringEscapeUtils.escapeXml(inventarioDTO.getCantidadExistencia() == null ? "-" : ""+inventarioDTO.getCantidadExistencia())).append("</cantidadExistencia>");
					contenidoXml.append("<valorUnidadExistencia>").append(StringEscapeUtils.escapeXml(inventarioDTO.getValorUnidadExistencia() == null ? "-" : ""+formatoDecimales.format(inventarioDTO.getValorUnidadExistencia().doubleValue()))).append("</valorUnidadExistencia>");
					contenidoXml.append("<valorTotalExistencia>").append(StringEscapeUtils.escapeXml(inventarioDTO.getValorTotalExistencia() == null ? "-" : ""+formatoDecimales.format(inventarioDTO.getValorTotalExistencia().doubleValue()))).append("</valorTotalExistencia>");
					contenidoXml.append("</movimiento>");
					cont++;
				}
				contenidoXml.append("</listaMovimientos>");			
				contenidoXml.append("</reporte>");
			}
			
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
	 * M\u00e9todo para obtener valores estadisticos de inventario
	 * @param codigoCompania
	 * @param existenciaActual
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Long obtenerCantidadTotalEntradas(Integer codigoCompania, Boolean existenciaActual) throws ERPException{
		return this.inventarioDAO.obtenerCantidadTotalEntradas(codigoCompania, existenciaActual);
	}
}
