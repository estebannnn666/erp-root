package ec.com.erp.clientes.gestor;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ClienteDTO;
import ec.com.erp.cliente.mdl.dto.ContactoDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IClientesGestor {
	
	/**
	 * M\u00e9todo para obtener lista de clientes
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @param nombreCliente
	 * @return
	 * @throws ERPException
	 */
	Collection<ClienteDTO> obtenerListaClientes(Integer codigoCompania, String numeroDocumento, String nombreCliente) throws ERPException;
	
	/**
	 * M\u00e9todo para guardar y actualizar cliente
	 * @param clienteDTO
	 * @param contactoDTO
	 * @throws ERPException
	 */
	void guardarActualizarClientes(ClienteDTO clienteDTO, ContactoDTO contactoDTO) throws ERPException;
}
