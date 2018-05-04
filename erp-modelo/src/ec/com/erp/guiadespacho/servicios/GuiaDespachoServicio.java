package ec.com.erp.guiadespacho.servicios;

import java.sql.Timestamp;
import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.GuiaDespachoDTO;
import ec.com.erp.guiadespacho.gestor.IGuiaDespachoGestor;

public class GuiaDespachoServicio implements IGuiaDespachoServicio{
	
	private IGuiaDespachoGestor guiaDespachoGestor;

	public IGuiaDespachoGestor getGuiaDespachoGestor() {
		return guiaDespachoGestor;
	}

	public void setGuiaDespachoGestor(IGuiaDespachoGestor guiaDespachoGestor) {
		this.guiaDespachoGestor = guiaDespachoGestor;
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
	public Collection<GuiaDespachoDTO> findObtenerListaDespachosByFiltrosBusqueda(Integer codigoCompania, String numeroGuia, Timestamp fechaDespacho, String placa, String documentoChofer, String nombreChofer) throws ERPException{
		return this.guiaDespachoGestor.obtenerListaDespachosByFiltrosBusqueda(codigoCompania, numeroGuia, fechaDespacho, placa, documentoChofer, nombreChofer);
	}
	
	/**
	 * M\u00e9todo para guardar y actualizar guia despacho
	 * @param guiaDespachoDTO
	 * @throws ERPException
	 */
	@Override
	public void transCrearActualizarGuiaDespacho(GuiaDespachoDTO guiaDespachoDTO) throws ERPException{
		this.guiaDespachoGestor.crearActualizarGuiaDespacho(guiaDespachoDTO);
	}
	
}
