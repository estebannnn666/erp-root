package ec.com.erp.guiadespacho.gestor;

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

public interface IGuiaDespachoGestor {
	
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
	Collection<GuiaDespachoDTO> obtenerListaDespachosByFiltrosBusqueda(Integer codigoCompania, String numeroGuia, Timestamp fechaDespachoInicio, Timestamp fechaDespachoFin, String placa, String documentoChofer, String nombreChofer) throws ERPException;
	
	/**
	 * M\u00e9todo para guardar y actualizar guia despacho
	 * @param guiaDespachoDTO
	 * @throws ERPException
	 */
	void crearActualizarGuiaDespacho(GuiaDespachoDTO guiaDespachoDTO) throws ERPException;
	
	/**
	 * Devuelve html para la impresion de la guia de despacho
	 * @param guiaDespachoDTO
	 * @return
	 * @throws ERPException
	 */
	String procesarXMLImprimirGuiaDespacho(GuiaDespachoDTO guiaDespachoDTO) throws ERPException;
	
	/**
	 * Method para eliminar pedidos asignados al despacho
	 * @param guiaDespachoPedidoDTO
	 */
	void eliminarPedidoDespacho(String numeroGuia, GuiaDespachoPedidoDTO guiaDespachoPedidoDTO);
	
	/**
	 * Metodo para eliminar articulos extras del despacho
	 * @param guiaDespachoExtrasDTO
	 */
	void eliminarPedidosExtras(GuiaDespachoExtrasDTO guiaDespachoExtrasDTO);
	
}
