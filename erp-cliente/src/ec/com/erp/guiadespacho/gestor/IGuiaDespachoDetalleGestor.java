package ec.com.erp.guiadespacho.gestor;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.GuiaDespachoDetalleDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IGuiaDespachoDetalleGestor {
	
	/**
	 * M\u00e9todo para obtener lista de detalle de guia de despacho
	 * @param codigoCompania
	 * @param numeroGuia
	 * @return Collection<GuiaDespachoDetalleDTO>
	 * @throws ERPException
	 */
	Collection<GuiaDespachoDetalleDTO> obtenerListaGuiaDespachoDetalleByNumeroGuia(Integer codigoCompania, String numeroGuia) throws ERPException;
	
	/**
	 * M\u00e9todo para guardar y actualizar detalle guia despacho
	 * @param guiaDespachoDetalleDTO
	 * @throws ERPException
	 */
	void crearActualizarDetalleGuiaDespacho(GuiaDespachoDetalleDTO guiaDespachoDetalleDTO) throws ERPException;
}
