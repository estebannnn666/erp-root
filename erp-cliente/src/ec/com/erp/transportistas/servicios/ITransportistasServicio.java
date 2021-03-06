package ec.com.erp.transportistas.servicios;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ContactoDTO;
import ec.com.erp.cliente.mdl.dto.TransportistaDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface ITransportistasServicio {
	
	/**
	 * Obtener transportista por documento
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @param codigoValorTipoTransportista
	 * @return
	 * @throws ERPException
	 */
	TransportistaDTO findObtenerTransportista(Integer codigoCompania, String numeroDocumento, String codigoValorTipoTransportista) throws ERPException;
	
	/**
	 * M\u00e9todo para obtener lista de transportista
	 * @param codigoCompania
	 * @return Collection<TransportistaDTO>
	 * @throws ERPException
	 */
	Collection<TransportistaDTO> findObtenerListaTransportistas(Integer codigoCompania, String numeroDocumento, String razonSocial) throws ERPException;
	
	/**
	 * M\u009etodo para guardar y actualizar transportista
	 * @param transportistaDTO
	 * @param contactoDTO
	 * @throws ERPException
	 */
	void transGuardarActualizarTransportista(TransportistaDTO transportistaDTO, ContactoDTO contactoDTO) throws ERPException;
	
}
