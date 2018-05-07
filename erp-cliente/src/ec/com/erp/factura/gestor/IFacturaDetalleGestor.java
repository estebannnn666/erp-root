package ec.com.erp.factura.gestor;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.FacturaDetalleDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IFacturaDetalleGestor {
	
	/**
	 * M\u00e9todo para obtener lista de detalles por factura
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @return Collection<FacturaDetalleDTO>
	 * @throws ERPException
	 */
	Collection<FacturaDetalleDTO> obtenerListaDetalleFacturaByNumeroFactura(Integer codigoCompania, String numeroDocumento) throws ERPException;
	
	/**
	 * M\u00e9todo para guardar y actualizar detalle factura
	 * @param facturaDetalleDTO
	 * @throws ERPException
	 */
	void guardarActualizarDetalleFactura(FacturaDetalleDTO facturaDetalleDTO) throws ERPException;
	
}
