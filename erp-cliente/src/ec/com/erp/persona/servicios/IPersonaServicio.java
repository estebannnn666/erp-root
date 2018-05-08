package ec.com.erp.persona.servicios;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.PersonaDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IPersonaServicio {
	
	/**
	 * M\u00e9todo para obtener lista de personas
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @return
	 * @throws ERPException
	 */
	Collection<PersonaDTO> findObtenerListaPersona(Integer codigoCompania, String numeroDocumento) throws ERPException;
	
}
