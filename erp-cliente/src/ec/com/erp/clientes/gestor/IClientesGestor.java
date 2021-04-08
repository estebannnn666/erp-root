package ec.com.erp.clientes.gestor;

import java.sql.Timestamp;
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
	Collection<ClienteDTO> obtenerListaClientes(Integer codigoCompania, String numeroDocumento, String nombreCliente, String documentoVendedor) throws ERPException;
	
	/**
	 * M\u00e9todo para obtener cliente por numero de documento
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @return
	 * @throws ERPException
	 */
	ClienteDTO obteneClientePorNumeroDocumento(Integer codigoCompania, String numeroDocumento) throws ERPException;
	
	/**
	 * M\u00e9todo para guardar y actualizar cliente
	 * @param clienteDTO
	 * @param contactoDTO
	 * @throws ERPException
	 */
	void guardarActualizarClientes(ClienteDTO clienteDTO, ContactoDTO contactoDTO) throws ERPException;
	
	/**
	 * Obtener cliente por documento
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @param codigoValorTipoCliente
	 * @return
	 * @throws ERPException
	 */
	ClienteDTO obtenerClienteByCodigo(Integer codigoCompania, String numeroDocumento, String codigoValorTipoCliente) throws ERPException;
	
	/**
	 * Metood para obtener cantidad de clientes todos o por fecha
	 * @param codigoCompania
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 * @throws ERPException
	 */
	Long obtenerClientesTodosOFecha(Integer codigoCompania, Timestamp fechaInicio, Timestamp fechaFin) throws ERPException;
}
