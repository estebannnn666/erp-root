package ec.com.erp.articulo.gestor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jdom.Document;
import org.jsoup.Jsoup;
import org.xhtmlrenderer.pdf.ITextRenderer;

import ec.com.erp.articulo.dao.IArticuloDAO;
import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ArticuloDTO;
import ec.com.erp.cliente.mdl.dto.ArticuloImpuestoDTO;
import ec.com.erp.cliente.mdl.dto.ArticuloUnidadManejoDTO;
import ec.com.erp.inventario.gestor.IInventarioGestor;
import ec.com.erp.unidadmanejo.dao.IUnidadManejoDAO;
import ec.com.erp.utilitario.commons.util.TransformerUtil;

public class ArticuloGestor implements IArticuloGestor{

	private IArticuloDAO articuloDAO;
	private IUnidadManejoDAO unidadManejoDAO;
	private IInventarioGestor inventarioGestor;
	
	public IArticuloDAO getArticuloDAO() {
		return articuloDAO;
	}

	public void setArticuloDAO(IArticuloDAO articuloDAO) {
		this.articuloDAO = articuloDAO;
	}
	
	public IUnidadManejoDAO getUnidadManejoDAO() {
		return unidadManejoDAO;
	}

	public void setUnidadManejoDAO(IUnidadManejoDAO unidadManejoDAO) {
		this.unidadManejoDAO = unidadManejoDAO;
	}

	public IInventarioGestor getInventarioGestor() {
		return inventarioGestor;
	}

	public void setInventarioGestor(IInventarioGestor inventarioGestor) {
		this.inventarioGestor = inventarioGestor;
	}
	
	/**
	 * M\u00e9todo para obtener lista de articulos
	 * @return 
	 * @throws ERPException
	 */
	public Collection<ArticuloDTO> obtenerListaArticulos(Integer codigoCompania, String codigoBarras, String nombreArticulo) throws ERPException{
		return this.articuloDAO.obtenerListaArticulos(codigoCompania, codigoBarras, nombreArticulo);
	}
	
	/**
	 * M\u00e9todo para obtener articulo por id
	 * @return 
	 * @throws ERPException
	 */
	public ArticuloDTO obtenerListaArticuloById(Integer codigoCompania, Integer codigoArticulo) throws ERPException{
		return this.articuloDAO.obtenerListaArticuloById(codigoCompania, codigoArticulo);
	}
	
	
	/**
	 * Metodo para guardar y actualizar articulos
	 * @param articuloDTO
	 * @param articuloImpuestoDTONewCols
	 * @throws ERPException
	 */
	public void guardarActualizarArticulo(ArticuloDTO articuloDTO, Collection<ArticuloImpuestoDTO> articuloImpuestoDTONewCols, Collection<ArticuloUnidadManejoDTO> articuloUnidadManejoDTONewCols) throws ERPException{
		// Actualizar o crear articulo
		this.articuloDAO.guardarActualizarArticulo(articuloDTO);
		// Guardar o eliminar impuestos del articulo
		if(CollectionUtils.isNotEmpty(articuloImpuestoDTONewCols)){
			this.guardarActualizarArticuloImpuesto(articuloDTO.getId().getCodigoCompania(), articuloDTO.getId().getCodigoArticulo(), articuloImpuestoDTONewCols);
		}
		
		// Guardar o eliminar articulo unidad de manejo
		if(CollectionUtils.isNotEmpty(articuloUnidadManejoDTONewCols)){
			this.guardarActualizarArticuloUnidadManejo(articuloDTO.getId().getCodigoCompania(), articuloDTO.getId().getCodigoArticulo(), articuloUnidadManejoDTONewCols);
		}
	}
	
	/**
	 * Metodo para guardar y actualizar articulo impuesto
	 * @param articuloImpuestoDTO
	 * @throws ERPException
	 */
	public void guardarActualizarArticuloImpuesto(Integer codigoCompania, Integer codigoArticulo, Collection<ArticuloImpuestoDTO> articuloImpuestoDTONewCols) throws ERPException{
		
		// Obtener impuestos agregados para verificar 
		Collection<ArticuloImpuestoDTO> articuloImpuestoDTOAntCols = this.articuloDAO.obtenerListaArticuloImpuesto(codigoCompania, codigoArticulo);
		/// Eliminar elementos 
		Boolean eliminar = Boolean.TRUE;
		for(ArticuloImpuestoDTO articuloImpuestoAntDTO: articuloImpuestoDTOAntCols){
			eliminar = Boolean.TRUE;
			for(ArticuloImpuestoDTO articuloImpuestoNewDTO : articuloImpuestoDTONewCols){
				if(articuloImpuestoAntDTO.getId().getCodigoImpuesto().equals(articuloImpuestoNewDTO.getId().getCodigoImpuesto())){
					eliminar = Boolean.FALSE;
					break;
				}
			}
			if(eliminar){
				articuloImpuestoAntDTO.setEstado(ERPConstantes.ESTADO_INACTIVO_NUMERICO);
				this.articuloDAO.guardarActualizarArticuloImpuesto(articuloImpuestoAntDTO);
			}
		}
		
		// Guardar o actualizar elementos
		Boolean esNuevo = Boolean.TRUE;
		for(ArticuloImpuestoDTO articuloImpuestoNewDTO:articuloImpuestoDTONewCols){
			esNuevo = Boolean.TRUE;
			for(ArticuloImpuestoDTO articuloImpuestoAntDTO : articuloImpuestoDTOAntCols){
				if(articuloImpuestoNewDTO.getId().getCodigoImpuesto() != null && articuloImpuestoAntDTO.getId().getCodigoImpuesto().equals(articuloImpuestoNewDTO.getId().getCodigoImpuesto())){
					esNuevo = Boolean.FALSE;
					break;
				}
			}
			if(esNuevo){
				articuloImpuestoNewDTO.getId().setCodigoArticulo(codigoArticulo);
				this.articuloDAO.guardarActualizarArticuloImpuesto(articuloImpuestoNewDTO);
			}
		}
	}
	
	/**
	 * Metodo para guardar y actualizar articulo impuesto
	 * @param articuloImpuestoDTO
	 * @throws ERPException
	 */
	public void guardarActualizarArticuloUnidadManejo(Integer codigoCompania, Integer codigoArticulo, Collection<ArticuloUnidadManejoDTO> articuloUnidadManejoDTONewCols) throws ERPException{
		
		// Obtener impuestos agregados para verificar 
		Collection<ArticuloUnidadManejoDTO> articuloUnidadManejoDTOAntCols = this.unidadManejoDAO.obtenerListaArticulosUnidadManejo(codigoCompania, codigoArticulo);
		/// Eliminar elementos 
		Boolean eliminar = Boolean.TRUE;
		for(ArticuloUnidadManejoDTO articuloUnidadManejoAntDTO: articuloUnidadManejoDTOAntCols){
			eliminar = Boolean.TRUE;
			for(ArticuloUnidadManejoDTO articuloImpuestoNewDTO : articuloUnidadManejoDTONewCols){
				if(articuloImpuestoNewDTO.getId().getCodigoArticuloUnidadManejo() != null && articuloUnidadManejoAntDTO.getId().getCodigoArticuloUnidadManejo().intValue() == articuloImpuestoNewDTO.getId().getCodigoArticuloUnidadManejo().intValue()){
					eliminar = Boolean.FALSE;
					break;
				}
			}
			if(eliminar){
				articuloUnidadManejoAntDTO.setEstado(ERPConstantes.ESTADO_INACTIVO_NUMERICO);
				this.unidadManejoDAO.guardarActualizarUnidadManejo(articuloUnidadManejoAntDTO);
			}
		}
		
		// Guardar o actualizar elementos
		Boolean esNuevo = Boolean.TRUE;
		for(ArticuloUnidadManejoDTO articuloUnidadManejoNewDTO:articuloUnidadManejoDTONewCols){
			esNuevo = Boolean.TRUE;
			for(ArticuloUnidadManejoDTO articuloUnidadManejoAntDTO : articuloUnidadManejoDTOAntCols){
				if(articuloUnidadManejoNewDTO.getId().getCodigoArticuloUnidadManejo() != null && articuloUnidadManejoAntDTO.getId().getCodigoArticuloUnidadManejo().intValue() == articuloUnidadManejoNewDTO.getId().getCodigoArticuloUnidadManejo().intValue()){
					esNuevo = Boolean.FALSE;
					break;
				}
			}
			if(esNuevo){
				articuloUnidadManejoNewDTO.getId().setCodigoArticulo(codigoArticulo);
				this.unidadManejoDAO.guardarActualizarUnidadManejo(articuloUnidadManejoNewDTO);
			}
		}
	}
	
	/**
	 * Metodo para obtener imagen del articulo
	 * @param codigoCompania
	 * @param codigoArticulo
	 * @return
	 */
	@Override
	public byte[] obtenerImagen(Integer codigoCompania, Integer codigoArticulo) throws ERPException{
		return this.articuloDAO.obtenerImagen(codigoCompania, codigoArticulo);
	}
	
	/**
	 * Devuelve html de reporte de catalogo de articulos
	 * @param articuloDTOCols
	 * @return
	 * @throws ERPException
	 */
	@Override
	public String procesarXMLReporteCatalogo(Collection<ArticuloDTO> articuloDTOCols) throws ERPException{
		StringBuilder contenidoXml = new StringBuilder();
		String html = "";
		String urlTipoReporte = "";
		try{
			Date fechaactual = new Date();
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			String fechaFormateada =  formatoFecha.format(fechaactual);
			DecimalFormat formatoDecimales = new DecimalFormat("#.##");
			formatoDecimales.setMinimumFractionDigits(2);

			urlTipoReporte = ERPConstantes.PLANTILLA_XSL_REPORTE_CATALOGO;
			
			contenidoXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
			contenidoXml.append("<articulos>");

			contenidoXml.append("<fechaReporte>").append(StringEscapeUtils.escapeXml(""+fechaFormateada)).append("</fechaReporte>");
			//detalle reposicion
			contenidoXml.append("<listaArticulos>");
			for(ArticuloDTO articuloDTO : articuloDTOCols){
				String base64String = "";
				if(articuloDTO.getImagen() != null) {
					base64String = "data:image/jpeg;base64,"+Base64.getEncoder().encodeToString(articuloDTO.getImagen());
				}else {
					base64String = "data:image/jpeg;base64,"+ERPConstantes.IMAGEN_DEFAULT;
				}
				contenidoXml.append("<articulo>");
				contenidoXml.append("<nombre>").append(StringEscapeUtils.escapeXml(articuloDTO.getNombreArticulo())).append("</nombre>");
				contenidoXml.append("<precioMenor>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(articuloDTO.getPrecioMinorista()))).append("</precioMenor>");
				contenidoXml.append("<precioMayor>").append(StringEscapeUtils.escapeXml(""+formatoDecimales.format(articuloDTO.getPrecio()))).append("</precioMayor>");
				contenidoXml.append("<imagen>").append(StringEscapeUtils.escapeXml(base64String)).append("</imagen>");
				contenidoXml.append("</articulo>");
			}
			contenidoXml.append("</listaArticulos>");			
			contenidoXml.append("</articulos>");
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
	 * Obtener bytes reporte generado
	 * @param articuloDTOCols
	 * @return
	 * @throws IOException
	 */
	public byte[] generateReportCatalogo(Collection<ArticuloDTO> articuloDTOCols) throws IOException {
		String html = this.procesarXMLReporteCatalogo(articuloDTOCols);
		String xhtml = htmlToXhtml(html);
		byte[] contenido = xhtmlToPdf(xhtml, "reporteCotalogo.pdf");
		System.out.println("RES:"+contenido);
		return contenido;
	}

	private String htmlToXhtml(String html) {
        org.jsoup.nodes.Document document = Jsoup.parse(html);
        document.outputSettings().syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml);
        return document.html();
    }

	private byte[] xhtmlToPdf(String xhtml, String outFileName) throws IOException {
        File output = new File(outFileName);
        ITextRenderer iTextRenderer = new ITextRenderer();
        iTextRenderer.setDocumentFromString(xhtml);
        iTextRenderer.layout();
        OutputStream os = new FileOutputStream(output);
        iTextRenderer.createPDF(os);
        os.close();
        byte[] bytes = Files.readAllBytes(output.toPath());
        return bytes;
    }
    
	/**
	 * M\u00e9todo para obtener lista de articulos para catalogos
	 * @return 
	 * @throws ERPException
	 */
	@Override
	public Collection<ArticuloDTO> obtenerArticulosCatalogos(Integer codigoCompania, String codigoBarras, String nombreArticulo) throws ERPException{
		return this.articuloDAO.obtenerArticulosCatalogos(codigoCompania, codigoBarras, nombreArticulo);
	}
}
