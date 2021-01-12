package ec.com.erp.persona.gestor;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.PersonaDTO;
import ec.com.erp.persona.dao.IPersonaDAO;

public class PersonaGestor implements IPersonaGestor{

	private IPersonaDAO personaDAO;

	public IPersonaDAO getPersonaDAO() {
		return personaDAO;
	}

	public void setPersonaDAO(IPersonaDAO personaDAO) {
		this.personaDAO = personaDAO;
	}

	/**
	 * M\u00e9todo para obtener lista de personas
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Collection<PersonaDTO> obtenerListaPersona(Integer codigoCompania, String numeroDocumento) throws ERPException{
		return this.personaDAO.obtenerListaPersona(codigoCompania, numeroDocumento);
	}
	
	/**
	 * M\u00e9todo para crear o actualizar personas
	 * @param personaDTO
	 * @throws ERPException
	 */
	public void crearActualizarPersona(PersonaDTO personaDTO) throws ERPException{
		if(personaDTO.getId().getCodigoPersona() == null) {
			Collection<PersonaDTO> personaExistente = this.personaDAO.obtenerListaPersona(personaDTO.getId().getCodigoCompania(), personaDTO.getNumeroDocumento());
			if(CollectionUtils.isNotEmpty(personaExistente)) {
				throw new ERPException("Error", "La persona con numero de documento "+personaDTO.getNumeroDocumento()+" ya existe.");
			}
		}
		this.personaDAO.crearActualizarPersona(personaDTO);
	}
}
