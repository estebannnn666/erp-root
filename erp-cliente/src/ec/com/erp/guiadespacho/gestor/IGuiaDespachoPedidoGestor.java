package ec.com.erp.guiadespacho.gestor;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.GuiaDespachoPedidoDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IGuiaDespachoPedidoGestor {
	
	/**
	 * M\u00e9todo para obtener lista de pedidos por guia de despacho
	 * @param codigoCompania
	 * @param numeroGuia
	 * @return
	 * @throws ERPException
	 */
	Collection<GuiaDespachoPedidoDTO> obtenerListaGuiaDespachoPedidosByNumeroGuiaDespacho(Integer codigoCompania, String numeroGuia) throws ERPException;
	
	/**
	 * M\u00e9todo para guardar y actualizar pedidos guia despacho
	 * @param guiaDespachoPedidoDTO
	 * @throws ERPException
	 */
	void crearActualizarGuiaDespachoPedidos(GuiaDespachoPedidoDTO guiaDespachoPedidoDTO) throws ERPException;
}
