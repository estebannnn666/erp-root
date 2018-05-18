package ec.com.erp.clientes.servicios;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ClienteDTO;
import ec.com.erp.cliente.mdl.dto.ContactoDTO;
import ec.com.erp.clientes.gestor.IClientesGestor;

public class ClientesServicio implements IClientesServicio{
	
	private IClientesGestor clientesGestor;
	
	public IClientesGestor getClientesGestor() {
		return clientesGestor;
	}

	public void setClientesGestor(IClientesGestor clientesGestor) {
		this.clientesGestor = clientesGestor;
	}

	/**
	 * M\u00e9todo para obtener lista de clientes
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @param nombreCliente
	 * @return
	 * @throws ERPException
	 */
	public Collection<ClienteDTO> findObtenerListaClientes(Integer codigoCompania, String numeroDocumento, String nombreCliente) throws ERPException{
		return this.clientesGestor.obtenerListaClientes(codigoCompania, numeroDocumento, nombreCliente);
	}

	/**
	 * M\u00e9todo para guardar y actualizar cliente
	 * @param clienteDTO
	 * @param contactoDTO
	 * @throws ERPException
	 */
	public void transGuardarActualizarClientes(ClienteDTO clienteDTO, ContactoDTO contactoDTO) throws ERPException{
		this.clientesGestor.guardarActualizarClientes(clienteDTO, contactoDTO);
	}
}
