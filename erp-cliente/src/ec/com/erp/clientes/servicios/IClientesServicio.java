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
	 * @param numeroDocumento
	 * @param nombreCliente
	 * @return
	 * @throws ERPException
	 */
	Collection<ClienteDTO> findObtenerListaClientes(Integer codigoCompania, String numeroDocumento, String nombreCliente) throws ERPException;
	
	/**
	 * M\u00e9todo para guardar y actualizar cliente
	 * @param clienteDTO
	 * @param contactoDTO
	 * @throws ERPException
	 */
	void transGuardarActualizarClientes(ClienteDTO clienteDTO, ContactoDTO contactoDTO) throws ERPException;
	
	/**
	 * Obtener cliente por documento
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @param codigoValorTipoCliente
	 * @return
	 * @throws ERPException
	 */
	ClienteDTO findObtenerClienteByCodigo(Integer codigoCompania, String numeroDocumento, String codigoValorTipoCliente) throws ERPException;
}
