package ec.com.erp.proveedor.servicios;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ContactoDTO;
import ec.com.erp.cliente.mdl.dto.ProveedorDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IProveedorServicio {
	
	/**
	 * Obtener proveedor por documento
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @param codigoValorTipoProveedor
	 * @return
	 * @throws ERPException
	 */
	ProveedorDTO findObtenerProveedor(Integer codigoCompania, String numeroDocumento, String codigoValorTipoProveedor) throws ERPException;
		
	/**
	 * M\u00e9todo para obtener lista de proveedores
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @param razonSocial
	 * @return 
	 * @throws ERPException
	 */
	Collection<ProveedorDTO> findObtenerListaProveedores(Integer codigoCompania, String numeroDocumento, String razonSocial) throws ERPException;
	
	/**
	 * M\u009etodo para guardar y actualizar proveedor
	 * @param proveedorDTO
	 * @param contactoDTO
	 * @throws ERPException
	 */
	void transGuardarActualizarProveedor(ProveedorDTO proveedorDTO, ContactoDTO contactoDTO) throws ERPException;
		
}
