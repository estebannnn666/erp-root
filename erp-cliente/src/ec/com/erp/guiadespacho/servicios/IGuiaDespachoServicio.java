package ec.com.erp.guiadespacho.servicios;

import java.sql.Timestamp;
import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.GuiaDespachoDTO;
import ec.com.erp.cliente.mdl.dto.GuiaDespachoExtrasDTO;
import ec.com.erp.cliente.mdl.dto.GuiaDespachoPedidoDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IGuiaDespachoServicio {
	
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
	Collection<GuiaDespachoDTO> findObtenerListaDespachosByFiltrosBusqueda(Integer codigoCompania, String numeroGuia, Timestamp fechaDespachoInicio, Timestamp fechaDespachoFin, String placa, String documentoChofer, String nombreChofer) throws ERPException;
	
	/**
	 * M\u00e9todo para obtener lista de pedidos por guia de despacho
	 * @param codigoCompania
	 * @param numeroGuia
	 * @return
	 * @throws ERPException
	 */
	Collection<GuiaDespachoPedidoDTO> findObtenerListaGuiaDespachoPedidosByNumeroGuiaDespacho(Integer codigoCompania, String numeroGuia) throws ERPException;
	
	/**
	 * M\u00e9todo para obtener lista de extras en guia de despacho
	 * @param codigoCompania
	 * @param numeroGuia
	 * @return Collection<GuiaDespachoExtrasDTO>
	 * @throws ERPException
	 */
	Collection<GuiaDespachoExtrasDTO> findObtenerListaGuiaDespachoExtrasByNumeroGuia(Integer codigoCompania, String numeroGuia) throws ERPException;
	
	/**
	 * M\u00e9todo para guardar y actualizar guia despacho
	 * @param guiaDespachoDTO
	 * @throws ERPException
	 */
	void transCrearActualizarGuiaDespacho(GuiaDespachoDTO guiaDespachoDTO) throws ERPException;
	
	/**
	 * Devuelve html para la impresion de la guia de despacho
	 * @param guiaDespachoDTO
	 * @return
	 * @throws ERPException
	 */
	String finObtenerXMLImprimirGuiaDespacho(GuiaDespachoDTO guiaDespachoDTO) throws ERPException;
	
	/**
	 * Method for update status order and delete order for dispatch
	 * @param guiaDespachoPedidoDTO
	 */
	void transEliminarPedidoDespacho(GuiaDespachoPedidoDTO guiaDespachoPedidoDTO);
	
	/**
	 * Metodo para eliminar articulos extras del despacho
	 * @param guiaDespachoExtrasDTO
	 */	
	void transEliminarPedidosExtras(GuiaDespachoExtrasDTO guiaDespachoExtrasDTO);
}
