package ec.com.erp.guiadespacho.dao;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.GuiaDespachoFacturaDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IGuiaDespachoFacturaDAO {
	
	/**
	 * M\u00e9todo para obtener lista de facturas por guia de despacho
	 * @param codigoCompania
	 * @param numeroGuia
	 * @return
	 * @throws ERPException
	 */
	Collection<GuiaDespachoFacturaDTO> obtenerListaGuiaDespachoFacturasByNumeroGuiaDespacho(Integer codigoCompania, String numeroGuia) throws ERPException;
	
	/**
	 * M\u00e9todo para guardar y actualizar facturas guia despacho
	 * @param guiaDespachoFacturaDTO
	 * @throws ERPException
	 */
	void crearActualizarGuiaDespachoFacturas(GuiaDespachoFacturaDTO guiaDespachoFacturaDTO) throws ERPException;
}
