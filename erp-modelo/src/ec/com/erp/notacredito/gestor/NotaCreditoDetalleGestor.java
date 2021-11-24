/**
 * 
 */
package ec.com.erp.notacredito.gestor;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.NotaCreditoDetalleDTO;
import ec.com.erp.notacredito.dao.INotaCreditoDetalleDAO;

/**
 * @author Esteban Gudino
 * 2021-11-26
 */
public class NotaCreditoDetalleGestor implements INotaCreditoDetalleGestor {

	
	/**
	 * Dao para obtnener dao detalle nota de credito
	 */
	private INotaCreditoDetalleDAO notaCreditoDetalleDAO;

	public INotaCreditoDetalleDAO getNotaCreditoDetalleDAO() {
		return notaCreditoDetalleDAO;
	}

	public void setNotaCreditoDetalleDAO(INotaCreditoDetalleDAO notaCreditoDetalleDAO) {
		this.notaCreditoDetalleDAO = notaCreditoDetalleDAO;
	}

	/**
	 * M\u00e9todo para obtener lista de detalles por nota de credito
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @return Collection<NotaCreditoDetalleDTO>
	 * @throws ERPException
	 */
	@Override
	public Collection<NotaCreditoDetalleDTO> obtenerListaDetalleNotaCreditoByNumeroDocumento(Integer codigoCompania, String numeroDocumento) throws ERPException{
		return this.notaCreditoDetalleDAO.obtenerListaDetalleNotaCreditoByNumeroDocumento(codigoCompania, numeroDocumento);
	}
	
	/**
	 * M\u00e9todo para guardar y actualizar detalle de nota de credito
	 * @param notaCreditoDetalleDTO
	 * @throws ERPException
	 */
	@Override
	public void guardarActualizarDetalleNotaCredito(NotaCreditoDetalleDTO notaCreditoDetalleDTO) throws ERPException{
		this.notaCreditoDetalleDAO.guardarActualizarDetalleNotaCredito(notaCreditoDetalleDTO);
	}
}
