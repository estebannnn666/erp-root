/**
 * 
 */
package ec.com.erp.factura.gestor;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.FacturaDocumentoDTO;
import ec.com.erp.factura.dao.IFacturaDocumentoDAO;

/**
 * @author Esteban Gudino
 * 2018-05-05
 */
public class FacturaDocumentoGestor implements IFacturaDocumentoGestor {

	
	/**
	 * Dao para obtnener dao documento factura
	 */
	private IFacturaDocumentoDAO facturaDocumentoDAO;

	public IFacturaDocumentoDAO getFacturaDocumentoDAO() {
		return facturaDocumentoDAO;
	}

	public void setFacturaDocumentoDAO(IFacturaDocumentoDAO facturaDocumentoDAO) {
		this.facturaDocumentoDAO = facturaDocumentoDAO;
	}

	/**
	 * M\u00e9todo para obtener xml factura
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @return FacturaDocumentoDTO
	 * @throws ERPException
	 */
	@Override
	public FacturaDocumentoDTO obtenerXmlDocumentoFactura(Integer codigoCompania, Long codigoFactura) throws ERPException{
		return this.facturaDocumentoDAO.obtenerXmlDocumentoFactura(codigoCompania, codigoFactura);
	}
	
	/**
	 * M\u00e9todo para guardar y actualizar xml documento
	 * @param facturaDocumentoDTO
	 * @throws ERPException
	 */
	@Override
	public void guardarActualizarDocumentoFactura(FacturaDocumentoDTO facturaDocumentoDTO) throws ERPException{
		this.facturaDocumentoDAO.guardarActualizarDocumentoFactura(facturaDocumentoDTO);
	}
}
