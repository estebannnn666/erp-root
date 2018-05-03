package ec.com.erp.transportistas.dao;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.TransportistaDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface ITransportistasDAO {
	
	/**
	 * M\u00e9todo para obtener lista de transportista
	 * @param codigoCompania
	 * @return Collection<TransportistaDTO>
	 * @throws ERPException
	 */
	Collection<TransportistaDTO> obtenerListaTransportistas(Integer codigoCompania, String numeroDocumento, String razonSocial) throws ERPException;
	
	/**
	 * M\u00e9todo para guardar y actualizar transportista
	 * @param transportistaDTO
	 * @throws ERPException
	 */
	void guardarActualizarTransportista(TransportistaDTO transportistaDTO) throws ERPException;	
}
