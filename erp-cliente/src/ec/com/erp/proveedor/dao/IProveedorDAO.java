package ec.com.erp.proveedor.dao;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ProveedorDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IProveedorDAO {
	
	/**
	 * M\u00e9todo para obtener lista de proveedores
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @param razonSocial
	 * @return 
	 * @throws ERPException
	 */
	Collection<ProveedorDTO> obtenerListaProveedores(Integer codigoCompania, String numeroDocumento, String razonSocial) throws ERPException;
	
	/**
	 * M\u00e9todo para guardar y actualizar proveedor
	 * @param transportistaDTO
	 * @throws ERPException
	 */
	void guardarActualizarProveedor(ProveedorDTO proveedorDTO) throws ERPException;
}
