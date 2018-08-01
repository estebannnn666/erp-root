package ec.com.erp.guiadespacho.dao;

import java.sql.Timestamp;
import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.GuiaDespachoDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IGuiaDespachoDAO {
	
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
	
}
