/**
 * 
 */
package ec.com.erp.guiadespacho.gestor;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.GuiaDespachoPedidoDTO;
import ec.com.erp.guiadespacho.dao.IGuiaDespachoPedidoDAO;

/**
 * @author Esteban Gudino
 * 2018-05-05
 */
public class GuiaDespachoPedidoGestor implements IGuiaDespachoPedidoGestor {

	private IGuiaDespachoPedidoDAO guiaDespachoPedidoDAO;

	/**
	 * M\u00e9todo para obtener lista de pedidos por guia de despacho
	 * @param codigoCompania
	 * @param numeroGuia
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Collection<GuiaDespachoPedidoDTO> obtenerListaGuiaDespachoPedidosByNumeroGuiaDespacho(Integer codigoCompania, String numeroGuia) throws ERPException{
		return this.guiaDespachoPedidoDAO.obtenerListaGuiaDespachoPedidosByNumeroGuiaDespacho(codigoCompania, numeroGuia);
	}
	
	/**
	 * M\u00e9todo para guardar y actualizar pedidos guia despacho
	 * @param guiaDespachoPedidoDTO
	 * @throws ERPException
	 */
	@Override
	public void crearActualizarGuiaDespachoPedidos(GuiaDespachoPedidoDTO guiaDespachoPedidoDTO) throws ERPException{
		this.guiaDespachoPedidoDAO.crearActualizarGuiaDespachoPedidos(guiaDespachoPedidoDTO);
	}

}
