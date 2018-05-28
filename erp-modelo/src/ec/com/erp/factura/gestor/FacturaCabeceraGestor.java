/**
 * 
 */
package ec.com.erp.factura.gestor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jdom.Document;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.FacturaCabeceraDTO;
import ec.com.erp.cliente.mdl.dto.FacturaDetalleDTO;
import ec.com.erp.factura.dao.IFacturaCabeceraDAO;
import ec.com.erp.utilitario.commons.util.TransformerUtil;

/**
 * @author Esteban Gudino
 * 2018-05-05
 */
public class FacturaCabeceraGestor implements IFacturaCabeceraGestor {

	private IFacturaCabeceraDAO facturaCabeceraDAO;
	private IFacturaDetalleGestor facturaDetalleGestor;
	
	
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
				facturaDetalleDTO.getId().setCodigoCompania(Integer.parseInt(ERPConstantes.ESTADO_ACTIVO_NUMERICO));
				facturaDetalleDTO.setCodigoFactura(facturaCabeceraDTO.getId().getCodigoFactura());
				this.facturaDetalleGestor.guardarActualizarDetalleFactura(facturaDetalleDTO);
			}
		} catch (ERPException e) {
			throw new ERPException("Error, {0}",e.getMessage()) ;
		} catch (Exception e) {
			throw new ERPException("Error, {0}",e.getMessage());
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
			Date fechaactual = new Date();
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			String fechaFormateada =  formatoFecha.format(fechaactual);

			urlTipoReporte = ERPConstantes.PLANTILLA_XSL_REPORTE_FACTURAS;
			
			contenidoXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
			contenidoXml.append("<facturas>");
			

			contenidoXml.append("<fechaReporte>").append(StringEscapeUtils.escapeXml(""+fechaFormateada)).append("</fechaReporte>");

			//detalle reposicion
			contenidoXml.append("<listaFacturas>");
			for(FacturaCabeceraDTO facturaCabeceraDTO : facturaCabeceraDTOCols){
				contenidoXml.append("<factura>");
				contenidoXml.append("<numeroDocumento>").append(StringEscapeUtils.escapeXml(facturaCabeceraDTO.getNumeroDocumento())).append("</numeroDocumento>");
				contenidoXml.append("<documentoCliente>").append(StringEscapeUtils.escapeXml(""+facturaCabeceraDTO.getRucDocumento())).append("</documentoCliente>");
				contenidoXml.append("<nombreClienteProveedor>").append(StringEscapeUtils.escapeXml(facturaCabeceraDTO.getNombreClienteProveedor())).append("</nombreClienteProveedor>");
				contenidoXml.append("<direccion>").append(StringEscapeUtils.escapeXml(facturaCabeceraDTO.getDireccion())).append("</direccion>");
				contenidoXml.append("</factura>");
			}

			contenidoXml.append("</listaFacturas>");
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
}
