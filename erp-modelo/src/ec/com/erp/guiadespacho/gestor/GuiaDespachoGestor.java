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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.jdom.Document;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.EstadoPedidoDTO;
import ec.com.erp.cliente.mdl.dto.GuiaDespachoDTO;
import ec.com.erp.cliente.mdl.dto.GuiaDespachoDetalleDTO;
import ec.com.erp.cliente.mdl.dto.GuiaDespachoExtrasDTO;
import ec.com.erp.cliente.mdl.dto.GuiaDespachoPedidoDTO;
import ec.com.erp.guiadespacho.dao.IGuiaDespachoDAO;
import ec.com.erp.pedidos.dao.IEstadoPedidoDAO;
import ec.com.erp.utilitario.commons.util.TransformerUtil;

/**
 * @author Esteban Gudino
 * 2018-05-05
 */
public class GuiaDespachoGestor implements IGuiaDespachoGestor {

	private IGuiaDespachoDAO guiaDespachoDAO;
	private IGuiaDespachoExtrasGestor guiaDespachoExtrasGestor;
	private IGuiaDespachoPedidoGestor guiaDespachoPedidoGestor;
	private IGuiaDespachoDetalleGestor guiaDespachoDetalleGestor;
	private IEstadoPedidoDAO estadoPedidoDAO;
	
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

	public IEstadoPedidoDAO getEstadoPedidoDAO() {
		return estadoPedidoDAO;
	}

	public void setEstadoPedidoDAO(IEstadoPedidoDAO estadoPedidoDAO) {
		this.estadoPedidoDAO = estadoPedidoDAO;
	}
	
	public IGuiaDespachoDetalleGestor getGuiaDespachoDetalleGestor() {
		return guiaDespachoDetalleGestor;
	}

	public void setGuiaDespachoDetalleGestor(IGuiaDespachoDetalleGestor guiaDespachoDetalleGestor) {
		this.guiaDespachoDetalleGestor = guiaDespachoDetalleGestor;
	}

	/**
	 * M\u00e9todo para obtener lista de despachos
	 * @param codigoCompania
	 * @param numeroGuia
	 * @param fechaDespachoInicio
	 * @param fechaDespachoFin
	 * @param placa
	 * @param documentoChofer
	 * @param nombreChofer
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Collection<GuiaDespachoDTO> obtenerListaDespachosByFiltrosBusqueda(Integer codigoCompania, String numeroGuia, Timestamp fechaDespachoInicio, Timestamp fechaDespachoFin, String placa, String documentoChofer, String nombreChofer) throws ERPException{
		return this.guiaDespachoDAO.obtenerListaDespachosByFiltrosBusqueda(codigoCompania, numeroGuia, fechaDespachoInicio, fechaDespachoFin, placa, documentoChofer, nombreChofer);
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
			// Obtener la lista de detalles
			Collection<GuiaDespachoDetalleDTO> guiaDespachoDetalleDTOCols = guiaDespachoDTO.getGuiaDespachoDetalleDTOCols();
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
				Collection<EstadoPedidoDTO> estadoPedidoActualCol = this.estadoPedidoDAO.obtenerEstadoPedido(guiaDespachoDTO.getId().getCodigoCompania(), guiaDespachoPedidoDTO.getCodigoPedido());
				EstadoPedidoDTO estadoPedidoDTOActual = estadoPedidoActualCol.iterator().next();
				estadoPedidoDTOActual.setFechaFin(new Date());
				if(estadoPedidoDTOActual.getCodigoValorEstadoPedido().equals(ERPConstantes.CODIGO_CATALOGO_VALOR_ESTADO_PEDIDO_REGISTRADO)) {
				this.estadoPedidoDAO.crearActualizarEstadoPedido(estadoPedidoDTOActual);
					EstadoPedidoDTO estadoPedidoDTONuevo = new EstadoPedidoDTO();
					estadoPedidoDTONuevo.getId().setCodigoCompania(guiaDespachoDTO.getId().getCodigoCompania());
					estadoPedidoDTONuevo.getId().setCodigoPedido(guiaDespachoPedidoDTO.getCodigoPedido());
					estadoPedidoDTONuevo.setCodigoTipoEstadoPedido(ERPConstantes.CODIGO_CATALOGO_TIPOS_ESTADO_PEDIDO);
					estadoPedidoDTONuevo.setCodigoValorEstadoPedido(ERPConstantes.CODIGO_CATALOGO_VALOR_ESTADO_PEDIDO_PENDIENTE);
					estadoPedidoDTONuevo.setFechaInicio(new Date());
					estadoPedidoDTONuevo.setFechaFin(null);
					estadoPedidoDTONuevo.setUsuarioRegistro(guiaDespachoDTO.getUsuarioRegistro());
					this.estadoPedidoDAO.crearActualizarEstadoPedido(estadoPedidoDTONuevo);
				}
				
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
			
			// Guardar detalle de guia despacho
			for(GuiaDespachoDetalleDTO guiaDespachoDetalleDTO : guiaDespachoDetalleDTOCols) {
				if(guiaDespachoDetalleDTO.getDescripcionProducto() != null && guiaDespachoDetalleDTO.getDescripcionProducto().trim() != "" && guiaDespachoDetalleDTO.getCantidad() != null) {
					guiaDespachoDetalleDTO.getId().setCodigoCompania(guiaDespachoDTO.getId().getCodigoCompania());
					guiaDespachoDetalleDTO.setUsuarioRegistro(guiaDespachoDTO.getUsuarioRegistro());
					guiaDespachoDetalleDTO.setCodigoGuiaDespacho(guiaDespachoDTO.getId().getCodigoGuiaDespacho());
					if(guiaDespachoDetalleDTO.getDescripcionProducto() != null) {
						guiaDespachoDetalleDTO.setDescripcionProducto(guiaDespachoDetalleDTO.getDescripcionProducto().toUpperCase());
					}
					if(guiaDespachoDetalleDTO.getObservacion() != null) {
						guiaDespachoDetalleDTO.setObservacion(guiaDespachoDetalleDTO.getObservacion().toUpperCase());
					}
					this.guiaDespachoDetalleGestor.crearActualizarDetalleGuiaDespacho(guiaDespachoDetalleDTO);
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
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			DecimalFormat formatoDecimales = new DecimalFormat("#.##");
			formatoDecimales.setMinimumFractionDigits(2);

			urlTipoReporte = ERPConstantes.PLANTILLA_XSL_IMPRIMIR_GUIA_DESPACHO;
			
			contenidoXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
			contenidoXml.append("<guiaDespacho>");

			contenidoXml.append("<numeroGuiaDespacho>").append(StringEscapeUtils.escapeXml(""+guiaDespachoDTO.getNumeroGuiaDespacho())).append("</numeroGuiaDespacho>");
			contenidoXml.append("<despachador>").append(StringEscapeUtils.escapeXml(""+guiaDespachoDTO.getUsuarioRegistro())).append("</despachador>");
			contenidoXml.append("<fechaDespacho>").append(StringEscapeUtils.escapeXml(""+formatoFecha.format(fechaactual))).append("</fechaDespacho>");
			contenidoXml.append("<placaVehiculo>").append(StringEscapeUtils.escapeXml(""+guiaDespachoDTO.getPlaca())).append("</placaVehiculo>");
			contenidoXml.append("<transportista>").append(StringEscapeUtils.escapeXml(""+guiaDespachoDTO.getNombreTransportista())).append("</transportista>");
			contenidoXml.append("<marcaVehiculo>").append(StringEscapeUtils.escapeXml(""+guiaDespachoDTO.getMarca())).append("</marcaVehiculo>");
			contenidoXml.append("<choferPrincipal>").append(StringEscapeUtils.escapeXml(""+guiaDespachoDTO.getNombreChoferA())).append("</choferPrincipal>");
			contenidoXml.append("<choferSecundario>").append(StringEscapeUtils.escapeXml(""+guiaDespachoDTO.getNombreChoferB())).append("</choferSecundario>");
			int cont = 1;
			//detalle reposicion
			contenidoXml.append("<listaDestinos>");
			for(GuiaDespachoDetalleDTO guiaDespachoDetalleDTO : guiaDespachoDTO.getGuiaDespachoDetalleDTOCols()){
				String observacion = guiaDespachoDetalleDTO.getObservacion() == null ? "" : guiaDespachoDetalleDTO.getObservacion();
				contenidoXml.append("<destino>");
				contenidoXml.append("<nroDestino>").append(StringEscapeUtils.escapeXml(""+cont)).append("</nroDestino>");
				contenidoXml.append("<descripcionProducto>").append(StringEscapeUtils.escapeXml(guiaDespachoDetalleDTO.getDescripcionProducto())).append("</descripcionProducto>");
				contenidoXml.append("<cantidadPedida>").append(StringEscapeUtils.escapeXml(""+guiaDespachoDetalleDTO.getCantidad())).append("</cantidadPedida>");
				contenidoXml.append("<observacion>").append(StringEscapeUtils.escapeXml(observacion)).append("</observacion>");
				contenidoXml.append("</destino>");
				cont++;
			}
			contenidoXml.append("</listaDestinos>");
			// Validar si existen extras
			if(CollectionUtils.isNotEmpty(guiaDespachoDTO.getGuiaDespachoExtrasDTOCols())){
				int contExtras = 1;
				//detalle reposicion
				contenidoXml.append("<listaExtras>");
				for(GuiaDespachoExtrasDTO guiaDespachoExtrasDTO : guiaDespachoDTO.getGuiaDespachoExtrasDTOCols()){
					if(StringUtils.isNotEmpty(guiaDespachoExtrasDTO.getDescripcionProducto()) && guiaDespachoExtrasDTO.getCantidad() != null){
						String observacion = guiaDespachoExtrasDTO.getObservacion() == null ? "" : guiaDespachoExtrasDTO.getObservacion();
						contenidoXml.append("<extra>");
						contenidoXml.append("<nroExtra>").append(StringEscapeUtils.escapeXml(""+contExtras)).append("</nroExtra>");
						contenidoXml.append("<descripcionProducto>").append(StringEscapeUtils.escapeXml(guiaDespachoExtrasDTO.getDescripcionProducto())).append("</descripcionProducto>");
						contenidoXml.append("<cantidad>").append(StringEscapeUtils.escapeXml(""+guiaDespachoExtrasDTO.getCantidad())).append("</cantidad>");
						contenidoXml.append("<observacionExtra>").append(StringEscapeUtils.escapeXml(observacion)).append("</observacionExtra>");
						contenidoXml.append("</extra>");
						contExtras++;
					}
				}
				contenidoXml.append("</listaExtras>");
			}
			
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
			throw new ERPException("Error", "Error al procesar plantilla xsl") ;
		}
		return html;
	}
	
	/**
	 * Method for update status order and delete order for dispatch
	 * @param guiaDespachoPedidoDTO
	 */
	@Override
	public void eliminarPedidoDespacho(String numeroGuia, GuiaDespachoPedidoDTO guiaDespachoPedidoDTO) {
		guiaDespachoPedidoDTO.setEstado(ERPConstantes.ESTADO_INACTIVO_NUMERICO);
		this.guiaDespachoPedidoGestor.crearActualizarGuiaDespachoPedidos(guiaDespachoPedidoDTO);
		Collection<EstadoPedidoDTO> estadoPedidoActualCol = this.estadoPedidoDAO.obtenerEstadoPedido(guiaDespachoPedidoDTO.getId().getCodigoCompania(), guiaDespachoPedidoDTO.getCodigoPedido());
		EstadoPedidoDTO estadoPedidoDTOActual = estadoPedidoActualCol.iterator().next();
		estadoPedidoDTOActual.setFechaFin(new Date());
		this.estadoPedidoDAO.crearActualizarEstadoPedido(estadoPedidoDTOActual);
		EstadoPedidoDTO estadoPedidoDTONuevo = new EstadoPedidoDTO();
		estadoPedidoDTONuevo.getId().setCodigoCompania(guiaDespachoPedidoDTO.getId().getCodigoCompania());
		estadoPedidoDTONuevo.getId().setCodigoPedido(guiaDespachoPedidoDTO.getCodigoPedido());
		estadoPedidoDTONuevo.setCodigoTipoEstadoPedido(ERPConstantes.CODIGO_CATALOGO_TIPOS_ESTADO_PEDIDO);
		estadoPedidoDTONuevo.setCodigoValorEstadoPedido(ERPConstantes.CODIGO_CATALOGO_VALOR_ESTADO_PEDIDO_REGISTRADO);
		estadoPedidoDTONuevo.setFechaInicio(new Date());
		estadoPedidoDTONuevo.setFechaFin(null);
		estadoPedidoDTONuevo.setUsuarioRegistro(guiaDespachoPedidoDTO.getUsuarioRegistro());
		this.estadoPedidoDAO.crearActualizarEstadoPedido(estadoPedidoDTONuevo);
		
		Collection<GuiaDespachoDetalleDTO> detallePedidosCosl = this.guiaDespachoDetalleGestor.obtenerListaGuiaDespachoDetalleByNumeroGuia(guiaDespachoPedidoDTO.getId().getCodigoCompania(), numeroGuia);
		guiaDespachoPedidoDTO.getPedidoDTO().getDetallePedidoDTOCols().stream().forEach(detalle ->{
			GuiaDespachoDetalleDTO detalleGuia = detallePedidosCosl.stream()
	        		.filter(guiaDetalle -> guiaDetalle.getCodigoArticulo().intValue() == detalle.getCodigoArticulo().intValue())
	        		.findFirst().orElse(null);
			if(detalleGuia != null) {
				detallePedidosCosl.stream().forEach(guiaDesp -> {
					if(guiaDesp.getCodigoArticulo().intValue() == detalle.getCodigoArticulo().intValue()) {
						guiaDesp.setCantidad(guiaDesp.getCantidad()-detalle.getCantidad());
					}
				});
			}
		});
		
		detallePedidosCosl.stream().forEach(detallePedido -> {
			detallePedido.setUsuarioModificacion(guiaDespachoPedidoDTO.getUsuarioRegistro());
			detallePedido.setFechaModificacion(new Date());
			if(detallePedido.getCantidad().intValue() == 0) {
				detallePedido.setEstado(ERPConstantes.ESTADO_INACTIVO_NUMERICO);
			}
			this.guiaDespachoDetalleGestor.crearActualizarDetalleGuiaDespacho(detallePedido);
		});
	}
	
	/**
	 * Metodo para eliminar articulos extras del despacho
	 * @param guiaDespachoExtrasDTO
	 */	
	@Override
	public void eliminarPedidosExtras(GuiaDespachoExtrasDTO guiaDespachoExtrasDTO) {
		guiaDespachoExtrasDTO.setEstado(ERPConstantes.ESTADO_INACTIVO_NUMERICO);
		this.guiaDespachoExtrasGestor.crearActualizarExtrasGuiaDespacho(guiaDespachoExtrasDTO);
	}
}
