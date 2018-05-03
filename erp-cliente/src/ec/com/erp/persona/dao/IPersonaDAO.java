package ec.com.erp.persona.dao;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.PersonaDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IPersonaDAO {
	
	/**
	 * M\u00e9todo para obtener lista de personas
	 * @param codigoCompania
	 * @return
	 * @throws ERPException
	 */
	Collection<PersonaDTO> obtenerListaPersona(Integer codigoCompania) throws ERPException;
	
	/**
	 * M\u00e9todo para crear o actualizar personas
	 * @param personaDTO
	 * @throws ERPException
	 */
	void crearActualizarPersona(PersonaDTO personaDTO) throws ERPException;
}
