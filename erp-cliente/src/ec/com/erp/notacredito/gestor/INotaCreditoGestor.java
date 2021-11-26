package ec.com.erp.notacredito.gestor;

import java.sql.Timestamp;
import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.NotaCreditoDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface INotaCreditoGestor {
	
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
	 * M\u00e9todo para guardar y actualizar nota de credito
	 * @param crearNotaCreditoElectronica
	 * @param notaCreditoDTO
	 * @throws ERPException
	 */
	void guardarActualizarNotaCredito(Boolean crearNotaCreditoElectronica, NotaCreditoDTO notaCreditoDTO) throws ERPException;
	
	/**
	 * M\u00e9todo para obtener xml nota de credito
	 * @param codigoCompania
	 * @param codigoFactura
	 * @throws ERPException
	 */
	byte[] obtenerXmlDocumentoNotaCredito(Integer codigoCompania, Long codigoFactura) throws ERPException;
	
	/**
	 * Metodo para firmar enviar y autorizar nota credito electronica
	 * @param notaCreditoDTO
	 */
	void enviarFirmarAutorizarNotaCredito(NotaCreditoDTO notaCreditoDTO);
	
}
