package ec.com.erp.clientes.servicios;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ClienteDTO;
import ec.com.erp.cliente.mdl.dto.ContactoDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IClientesServicio {
	
	/**
	 * M\u00e9todo para obtener lista de clientes
	 * @param codigoCompania
	 * @return 
	 * @throws ERPException
	 */
	Collection<ClienteDTO> findObtenerListaClientes(Integer codigoCompania) throws ERPException;
	
	/**
	 * M\u00e9todo para guardar y actualizar cliente
	 * @param clienteDTO
	 * @param contactoDTO
	 * @throws ERPException
	 */
	void transGuardarActualizarClientes(ClienteDTO clienteDTO, ContactoDTO contactoDTO) throws ERPException;
}
