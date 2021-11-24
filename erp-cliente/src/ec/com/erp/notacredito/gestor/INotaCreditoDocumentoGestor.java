package ec.com.erp.notacredito.gestor;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.NotaCreditoDocumentoDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface INotaCreditoDocumentoGestor {
	
	/**
	 * M\u00e9todo para obtener xml de nota de credito
	 * @param codigoCompania
	 * @param codigoNotaCredito
	 * @return NotaCreditoDocumentoDTO
	 * @throws ERPException
	 */
	NotaCreditoDocumentoDTO obtenerXmlDocumentoNotaCredito(Integer codigoCompania, Long codigoNotaCredito) throws ERPException;
	
	/**
	 * M\u00e9todo para guardar y actualizar xml documento
	 * @param notaCreditoDocumentoDTO
	 * @throws ERPException
	 */
	void guardarActualizarDocumentoNotaCredito(NotaCreditoDocumentoDTO notaCreditoDocumentoDTO) throws ERPException;
}
