package ec.com.erp.guiadespacho.dao;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.GuiaDespachoExtrasDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IGuiaDespachoExtrasDAO {
	
	/**
	 * M\u00e9todo para obtener lista de extras en guia de despacho
	 * @param codigoCompania
	 * @param numeroGuia
	 * @return Collection<GuiaDespachoExtrasDTO>
	 * @throws ERPException
	 */
	Collection<GuiaDespachoExtrasDTO> obtenerListaGuiaDespachoExtrasByNumeroGuia(Integer codigoCompania, String numeroGuia) throws ERPException;
	
	/**
	 * M\u00e9todo para guardar y actualizar extras guia despacho
	 * @param guiaDespachoExtrasDTO
	 * @throws ERPException
	 */
	void crearActualizarExtrasGuiaDespacho(GuiaDespachoExtrasDTO guiaDespachoExtrasDTO) throws ERPException;
}
