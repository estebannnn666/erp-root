package ec.com.erp.factura.gestor;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.FacturaDocumentoDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IFacturaDocumentoGestor {
	
	/**
	 * M\u00e9todo para obtener xml factura
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @return FacturaDocumentoDTO
	 * @throws ERPException
	 */
	FacturaDocumentoDTO obtenerXmlDocumentoFactura(Integer codigoCompania, Long codigoFactura) throws ERPException;
	
	/**
	 * M\u00e9todo para guardar y actualizar xml documento
	 * @param facturaDocumentoDTO
	 * @throws ERPException
	 */
	void guardarActualizarDocumentoFactura(FacturaDocumentoDTO facturaDocumentoDTO) throws ERPException;	
}
