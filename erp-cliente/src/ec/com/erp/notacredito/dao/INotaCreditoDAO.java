package ec.com.erp.notacredito.dao;

import java.sql.Timestamp;
import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.NotaCreditoDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface INotaCreditoDAO {
	
	/**
	 * M\u00e9todo para obtener lista de notas de credito por filtros de busqueda
	 * @param codigoCompania
	 * @param numeroNotaCredito
	 * @param fechaNotaCreditoInicio
	 * @param fechaNotaCreditoFin
	 * @param docCliente
	 * @param nombreCliente
	 * @return
	 * @throws ERPException
	 */
	Collection<NotaCreditoDTO> obtenerListaNotasCredito(Integer codigoCompania, String numeroNotaCredito, Timestamp fechaNotaCreditoInicio, Timestamp fechaNotaCreditoFin, String docCliente, String nombreCliente) throws ERPException;
	
	/**
	 * M\u00e9todo para obtener la nota de credito por codigo
	 * @param codigoCompania
	 * @param codigoNotaCredito
	 * @return
	 * @throws ERPException
	 */
	NotaCreditoDTO obtenerNotaCreditoPorCodigo(Integer codigoCompania, Long codigoNotaCredito) throws ERPException;
	
	/**
	 * M\u00e9todo para guardar y actualizar nota de credito
	 * @param notaCreditoDTO
	 * @throws ERPException
	 */
	void guardarActualizarNotaCredito(NotaCreditoDTO notaCreditoDTO) throws ERPException;
	
	/**
	 * Actualizar numero de nota de credito.
	 * @param codigoCompania
	 * @param codigoNotaCredito
	 * @param userId
	 * @param numeroNotaCredito
	 * @throws ERPException
	 */
	void actualizarNotaCreditoNumeroNotaCredito(Integer codigoCompania, Long codigoNotaCredito, String userId, String numeroNotaCredito)throws ERPException;
	
}
