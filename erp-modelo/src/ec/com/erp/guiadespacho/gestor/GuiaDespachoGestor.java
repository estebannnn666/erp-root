/**
 * 
 */
package ec.com.erp.guiadespacho.gestor;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jdom.Document;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.GuiaDespachoDTO;
import ec.com.erp.cliente.mdl.dto.GuiaDespachoExtrasDTO;
import ec.com.erp.cliente.mdl.dto.GuiaDespachoPedidoDTO;
import ec.com.erp.guiadespacho.dao.IGuiaDespachoDAO;
import ec.com.erp.utilitario.commons.util.TransformerUtil;

/**
 * @author Esteban Gudino
 * 2018-05-05
 */
public class GuiaDespachoGestor implements IGuiaDespachoGestor {

	private IGuiaDespachoDAO guiaDespachoDAO;
	private IGuiaDespachoExtrasGestor guiaDespachoExtrasGestor;
	private IGuiaDespachoPedidoGestor guiaDespachoPedidoGestor;
	
	public IGuiaDespachoDAO getGuiaDespachoDAO() {
		return guiaDespachoDAO;
	}

	public void setGuiaDespachoDAO(IGuiaDespachoDAO guiaDespachoDAO) {
		this.guiaDespachoDAO = guiaDespachoDAO;
	}

	public IGuiaDespachoExtrasGestor getGuiaDespachoExtrasGestor() {
		return guiaDespachoExtrasGestor;
	}

	public void setGuiaDespachoExtrasGestor(IGuiaDespachoExtrasGestor guiaDespachoExtrasGestor) {
		this.guiaDespachoExtrasGestor = guiaDespachoExtrasGestor;
	}

	public IGuiaDespachoPedidoGestor getGuiaDespachoPedidoGestor() {
		return guiaDespachoPedidoGestor;
	}

	public void setGuiaDespachoPedidoGestor(IGuiaDespachoPedidoGestor guiaDespachoPedidoGestor) {
		this.guiaDespachoPedidoGestor = guiaDespachoPedidoGestor;
	}

	/**
	 * M\u00e9todo para obtener lista de despachos
	 * @param codigoCompania
	 * @param numeroGuia
	 * @param fechaDespacho
	 * @param placa
	 * @param documentoChofer
	 * @param nombreChofer
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Collection<GuiaDespachoDTO> obtenerListaDespachosByFiltrosBusqueda(Integer codigoCompania, String numeroGuia, Timestamp fechaDespacho, String placa, String documentoChofer, String nombreChofer) throws ERPException{
		return this.guiaDespachoDAO.obtenerListaDespachosByFiltrosBusqueda(codigoCompania, numeroGuia, fechaDespacho, placa, documentoChofer, nombreChofer);
	}
	
	/**
	 * M\u00e9todo para guardar y actualizar guia despacho
	 * @param guiaDespachoDTO
	 * @throws ERPException
	 */
	@Override
	public void crearActualizarGuiaDespacho(GuiaDespachoDTO guiaDespachoDTO) throws ERPException{
		try{
			// Obtener la lista de despachos extras
			Collection<GuiaDespachoExtrasDTO> guiaDespachoExtrasDTOCols = guiaDespachoDTO.getGuiaDespachoExtrasDTOCols();
			// Obtener la lista de despachos extras
			Collection<GuiaDespachoPedidoDTO> guiaDespachoPedidoDTOCols = guiaDespachoDTO.getGuiaDespachoPedidoDTOCols();
			
			// Guardar guia despacho
			this.guiaDespachoDAO.crearActualizarGuiaDespacho(guiaDespachoDTO);
			
			// Guardar pedidos guia despacho
			for(GuiaDespachoPedidoDTO guiaDespachoPedidoDTO : guiaDespachoPedidoDTOCols) {
				guiaDespachoPedidoDTO.getId().setCodigoCompania(guiaDespachoDTO.getId().getCodigoCompania());
				guiaDespachoPedidoDTO.setUsuarioRegistro(guiaDespachoDTO.getUsuarioRegistro());
				guiaDespachoPedidoDTO.setCodigoGuiaDespacho(guiaDespachoDTO.getId().getCodigoGuiaDespacho());
				if(guiaDespachoPedidoDTO.getObservacion() != null) {
					guiaDespachoPedidoDTO.setObservacion(guiaDespachoPedidoDTO.getObservacion().toUpperCase());
				}
				this.guiaDespachoPedidoGestor.crearActualizarGuiaDespachoPedidos(guiaDespachoPedidoDTO);
			}	
			
			// Guardar pedidos guia despacho
			for(GuiaDespachoExtrasDTO guiaDespachoExtrasDTO : guiaDespachoExtrasDTOCols) {
				if(guiaDespachoExtrasDTO.getDescripcionProducto() != null && guiaDespachoExtrasDTO.getDescripcionProducto().trim() != "" && guiaDespachoExtrasDTO.getCantidad() != null) {
					guiaDespachoExtrasDTO.getId().setCodigoCompania(guiaDespachoDTO.getId().getCodigoCompania());
					guiaDespachoExtrasDTO.setUsuarioRegistro(guiaDespachoDTO.getUsuarioRegistro());
					guiaDespachoExtrasDTO.setCodigoGuiaDespacho(guiaDespachoDTO.getId().getCodigoGuiaDespacho());
					if(guiaDespachoExtrasDTO.getDescripcionProducto() != null) {
						guiaDespachoExtrasDTO.setDescripcionProducto(guiaDespachoExtrasDTO.getDescripcionProducto().toUpperCase());
					}
					if(guiaDespachoExtrasDTO.getObservacion() != null) {
						guiaDespachoExtrasDTO.setObservacion(guiaDespachoExtrasDTO.getObservacion().toUpperCase());
					}
					this.guiaDespachoExtrasGestor.crearActualizarExtrasGuiaDespacho(guiaDespachoExtrasDTO);
				}
			}
		} catch (ERPException e) {
			throw new ERPException("Error, ", e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Error, ", e.getMessage());
		} 
	}

	/**
	 * Devuelve html para la impresion de la guia de despacho
	 * @param guiaDespachoDTO
	 * @return
	 * @throws ERPException
	 */
	@Override
	public String procesarXMLImprimirGuiaDespacho(GuiaDespachoDTO guiaDespachoDTO) throws ERPException{
		StringBuilder contenidoXml = new StringBuilder();
		String html = "";
		String urlTipoReporte = "";
		try{
			Date fechaactual = new Date();
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			String fechaFormateada =  formatoFecha.format(fechaactual);
			DecimalFormat formatoDecimales = new DecimalFormat("#.##");
			formatoDecimales.setMinimumFractionDigits(2);

			urlTipoReporte = ERPConstantes.PLANTILLA_XSL_IMPRIMIR_GUIA_DESPACHO;
			
			contenidoXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
			contenidoXml.append("<guiaDespacho>");

			contenidoXml.append("<numeroGuiaDespacho>").append(StringEscapeUtils.escapeXml(""+guiaDespachoDTO.getNumeroGuiaDespacho())).append("</numeroGuiaDespacho>");
//			int cont = 1;
//			//detalle reposicion
//			contenidoXml.append("<listaDestinos>");
//			for(GuiaDespachoPedidoDTO guiaDespachoPedidoDTO : guiaDespachoDTO.getGuiaDespachoPedidoDTOCols()){
//				contenidoXml.append("<destino>");
//				contenidoXml.append("<numeroFila>").append(StringEscapeUtils.escapeXml(""+cont)).append("</numeroFila>");
//				contenidoXml.append("<cliente>").append(StringEscapeUtils.escapeXml(guiaDespachoPedidoDTO.getPedidoDTO().getClienteDTO().getPersonaDTO().getNombreCompleto())).append("</cliente>");
//				contenidoXml.append("<totalPedido>").append(StringEscapeUtils.escapeXml(""+guiaDespachoPedidoDTO.getPedidoDTO().getTotalCompra())).append("</totalPedido>");
//				contenidoXml.append("<observacion>").append(StringEscapeUtils.escapeXml(guiaDespachoPedidoDTO.getObservacion())).append("</observacion>");
//				contenidoXml.append("</destino>");
//				cont++;
//			}
//			contenidoXml.append("</listaDestinos>");			
			contenidoXml.append("</guiaDespacho>");
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
