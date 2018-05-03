package ec.com.erp.clientes.dao;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ClienteDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IClientesDAO {
	
	/**
	 * M\u00e9todo para obtener lista de clientes
	 * @param codigoCompania
	 * @return 
	 * @throws ERPException
	 */
	Collection<ClienteDTO> obtenerListaClientes(Integer codigoCompania) throws ERPException;
	
	/**
	 * M\u00e9todo para guardar y actualizar cliente
	 * @param clienteDTO
	 * @throws ERPException
	 */
	void guardarActualizarClientes(ClienteDTO clienteDTO) throws ERPException;	
}
