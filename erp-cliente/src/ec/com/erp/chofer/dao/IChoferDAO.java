package ec.com.erp.chofer.dao;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ChoferDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IChoferDAO {
	
	/**
	 *  M\u00e9todo para obtener lista de choferes
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @param nombreChofer
	 * @return
	 * @throws ERPException
	 */
	Collection<ChoferDTO> obtenerListaChoferes(Integer codigoCompania, String numeroDocumento, String nombreChofer) throws ERPException;
	
	/**
	 * M\u00e9todo para guardar y actualizar chofer
	 * @param choferDTO
	 * @throws ERPException
	 */
	void guardarActualizarChofer(ChoferDTO choferDTO) throws ERPException;
}
