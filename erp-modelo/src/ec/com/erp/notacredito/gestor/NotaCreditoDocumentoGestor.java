/**
 * 
 */
package ec.com.erp.notacredito.gestor;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.NotaCreditoDocumentoDTO;
import ec.com.erp.notacredito.dao.INotaCreditoDocumentoDAO;

/**
 * @author Esteban Gudino
 * 2021-11-26
 */
public class NotaCreditoDocumentoGestor implements INotaCreditoDocumentoGestor {

	
	/**
	 * Dao para obtnener dao documento nota de credito
	 */
	private INotaCreditoDocumentoDAO notaCreditoDocumentoDAO;

	public INotaCreditoDocumentoDAO getNotaCreditoDocumentoDAO() {
		return notaCreditoDocumentoDAO;
	}

	public void setNotaCreditoDocumentoDAO(INotaCreditoDocumentoDAO notaCreditoDocumentoDAO) {
		this.notaCreditoDocumentoDAO = notaCreditoDocumentoDAO;
	}

	/**
	 * M\u00e9todo para obtener xml de nota de credito
	 * @param codigoCompania
	 * @param codigoNotaCredito
	 * @return NotaCreditoDocumentoDTO
	 * @throws ERPException
	 */
	@Override
	public NotaCreditoDocumentoDTO obtenerXmlDocumentoNotaCredito(Integer codigoCompania, Long codigoNotaCredito) throws ERPException{
		return this.notaCreditoDocumentoDAO.obtenerXmlDocumentoNotaCredito(codigoCompania, codigoNotaCredito);
	}
	
	/**
	 * M\u00e9todo para guardar y actualizar xml documento
	 * @param notaCreditoDocumentoDTO
	 * @throws ERPException
	 */
	@Override
	public void guardarActualizarDocumentoNotaCredito(NotaCreditoDocumentoDTO notaCreditoDocumentoDTO) throws ERPException{
		this.notaCreditoDocumentoDAO.guardarActualizarDocumentoNotaCredito(notaCreditoDocumentoDTO);
	}
}
