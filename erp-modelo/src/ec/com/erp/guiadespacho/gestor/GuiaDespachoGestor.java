/**
 * 
 */
package ec.com.erp.guiadespacho.gestor;

import java.sql.Timestamp;
import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.GuiaDespachoDTO;
import ec.com.erp.cliente.mdl.dto.GuiaDespachoExtrasDTO;
import ec.com.erp.cliente.mdl.dto.GuiaDespachoPedidoDTO;
import ec.com.erp.guiadespacho.dao.IGuiaDespachoDAO;

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
				guiaDespachoPedidoDTO.setCodigoGuiaDespacho(guiaDespachoDTO.getId().getCodigoGuiaDespacho());
				if(guiaDespachoPedidoDTO.getObservacion() != null) {
					guiaDespachoPedidoDTO.setObservacion(guiaDespachoPedidoDTO.getObservacion().toUpperCase());
				}
				this.guiaDespachoPedidoGestor.crearActualizarGuiaDespachoPedidos(guiaDespachoPedidoDTO);
			}	
			
			// Guardar pedidos guia despacho
			for(GuiaDespachoExtrasDTO guiaDespachoExtrasDTO : guiaDespachoExtrasDTOCols) {
				guiaDespachoExtrasDTO.setCodigoGuiaDespacho(guiaDespachoDTO.getId().getCodigoGuiaDespacho());
				if(guiaDespachoExtrasDTO.getObservacion() != null) {
					guiaDespachoExtrasDTO.setObservacion(guiaDespachoExtrasDTO.getObservacion().toUpperCase());
				}
				this.guiaDespachoExtrasGestor.crearActualizarExtrasGuiaDespacho(guiaDespachoExtrasDTO);
			}
		} catch (ERPException e) {
			throw new ERPException("Error, ", e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Error, ", e.getMessage());
		} 
	}

}
