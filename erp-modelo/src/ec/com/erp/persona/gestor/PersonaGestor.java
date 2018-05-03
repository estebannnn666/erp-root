package ec.com.erp.persona.gestor;

import java.util.Collection;

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
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Collection<PersonaDTO> obtenerListaPersona(Integer codigoCompania) throws ERPException{
		return this.personaDAO.obtenerListaPersona(codigoCompania);
	}
	
	/**
	 * M\u00e9todo para crear o actualizar personas
	 * @param personaDTO
	 * @throws ERPException
	 */
	public void crearActualizarPersona(PersonaDTO personaDTO) throws ERPException{
		this.personaDAO.crearActualizarPersona(personaDTO);
	}
}
