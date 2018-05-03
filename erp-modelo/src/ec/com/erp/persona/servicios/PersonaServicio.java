package ec.com.erp.persona.servicios;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.PersonaDTO;
import ec.com.erp.persona.gestor.IPersonaGestor;

public class PersonaServicio implements IPersonaServicio{
	
	private IPersonaGestor personaGestor;

	public IPersonaGestor getPersonaGestor() {
		return personaGestor;
	}

	public void setPersonaGestor(IPersonaGestor personaGestor) {
		this.personaGestor = personaGestor;
	}

	/**
	 * M\u00e9todo para obtener lista de personas
	 * @param codigoCompania
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Collection<PersonaDTO> findObtenerListaPersona(Integer codigoCompania) throws ERPException{
		return this.personaGestor.obtenerListaPersona(codigoCompania);
	}

}
