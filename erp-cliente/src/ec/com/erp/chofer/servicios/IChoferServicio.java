package ec.com.erp.chofer.servicios;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ChoferDTO;
import ec.com.erp.cliente.mdl.dto.ContactoDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IChoferServicio {
	
	/**
	 * Obtener chofer por documento
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @return
	 * @throws ERPException
	 */
	ChoferDTO findObtenerChoferByDocumento(Integer codigoCompania, String numeroDocumento) throws ERPException;
	
	/**
	 * M\u00e9todo para obtener lista de choferes
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @param nombreChofer
	 * @return Collection<TransportistaDTO>
	 * @throws ERPException
	 */
	Collection<ChoferDTO> findObtenerListaChoferes(Integer codigoCompania, String numeroDocumento, String nombreChofer) throws ERPException;
	
	/**
	 * M\u009etodo para guardar y actualizar chofer
	 * @param choferDTO
	 * @param contactoDTO
	 * @throws ERPException
	 */
	void transGuardarActualizarChofer(ChoferDTO choferDTO, ContactoDTO contactoDTO) throws ERPException;
	
}
