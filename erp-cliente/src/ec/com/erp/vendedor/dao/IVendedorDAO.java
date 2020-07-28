package ec.com.erp.vendedor.dao;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.VendedorDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IVendedorDAO {
	
	/**
	 * M\u00e9todo para obtener lista de vendedor
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @param nombreVendedor
	 * @return 
	 * @throws ERPException
	 */
	Collection<VendedorDTO> obtenerListaVendedores(Integer codigoCompania, String numeroDocumento, String nombreVendedor) throws ERPException;
	
	/**
	 * M\u009etodo para guardar y actualizar vendedor
	 * @param vendedorDTO
	 * @param contactoDTO
	 * @throws ERPException
	 */
	void guardarActualizarVendedor(VendedorDTO vendedorDTO) throws ERPException;
}
