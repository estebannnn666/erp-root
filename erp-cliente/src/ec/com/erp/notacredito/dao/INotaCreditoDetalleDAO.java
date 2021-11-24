package ec.com.erp.notacredito.dao;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.NotaCreditoDetalleDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface INotaCreditoDetalleDAO {
	
	/**
	 * M\u00e9todo para obtener lista de detalles por nota de credito
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @return Collection<NotaCreditoDetalleDTO>
	 * @throws ERPException
	 */
	Collection<NotaCreditoDetalleDTO> obtenerListaDetalleNotaCreditoByNumeroDocumento(Integer codigoCompania, String numeroDocumento) throws ERPException;
	
	/**
	 * M\u00e9todo para guardar y actualizar detalle de nota de credito
	 * @param notaCreditoDetalleDTO
	 * @throws ERPException
	 */
	void guardarActualizarDetalleNotaCredito(NotaCreditoDetalleDTO notaCreditoDetalleDTO) throws ERPException;
	
}
