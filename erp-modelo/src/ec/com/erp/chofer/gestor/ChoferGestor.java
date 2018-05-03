package ec.com.erp.chofer.gestor;

import java.util.Collection;

import ec.com.erp.chofer.dao.IChoferDAO;
import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ChoferDTO;
import ec.com.erp.cliente.mdl.dto.ContactoDTO;
import ec.com.erp.cliente.mdl.dto.PersonaDTO;
import ec.com.erp.contacto.gestor.IContactoGestor;
import ec.com.erp.persona.gestor.IPersonaGestor;

public class ChoferGestor implements IChoferGestor{

	private IChoferDAO choferDAO;
	private IPersonaGestor personaGestor;
	private IContactoGestor contactoGestor;
	
	public IChoferDAO getChoferDAO() {
		return choferDAO;
	}

	public void setChoferDAO(IChoferDAO choferDAO) {
		this.choferDAO = choferDAO;
	}

	public IPersonaGestor getPersonaGestor() {
		return personaGestor;
	}

	public void setPersonaGestor(IPersonaGestor personaGestor) {
		this.personaGestor = personaGestor;
	}

	public IContactoGestor getContactoGestor() {
		return contactoGestor;
	}

	public void setContactoGestor(IContactoGestor contactoGestor) {
		this.contactoGestor = contactoGestor;
	}

	/**
	 * M\u00e9todo para obtener lista de choferes
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @param nombreChofer
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Collection<ChoferDTO> obtenerListaChoferes(Integer codigoCompania, String numeroDocumento, String nombreChofer) throws ERPException{
		return this.choferDAO.obtenerListaChoferes(codigoCompania, numeroDocumento, nombreChofer);
	}
	
	/**
	 * M\u009etodo para guardar y actualizar chofer
	 * @param choferDTO
	 * @param contactoDTO
	 * @throws ERPException
	 */
	@Override
	public void guardarActualizarChofer(ChoferDTO choferDTO, ContactoDTO contactoDTO) throws ERPException{
		try {
			// Creamos o actualizamos la persona
			PersonaDTO personaDTO = choferDTO.getPersonaDTO();
			personaDTO.setPrimerApellido(personaDTO.getPrimerApellido().toUpperCase());
			personaDTO.setPrimerNombre(personaDTO.getPrimerNombre().toUpperCase());
			String nombreCompleto = personaDTO.getPrimerApellido();
			if(personaDTO.getSegundoApellido() != null){
				personaDTO.setSegundoApellido(personaDTO.getSegundoApellido().toUpperCase());
				nombreCompleto += " "+personaDTO.getSegundoApellido();
			}
			nombreCompleto += " "+personaDTO.getPrimerNombre();
			if(personaDTO.getSegundoNombre() != null){
				personaDTO.setSegundoNombre(personaDTO.getSegundoNombre());
				nombreCompleto += " "+personaDTO.getSegundoNombre();
			}
			personaDTO.setNombreCompleto(nombreCompleto);
			personaDTO.getId().setCodigoCompania(Integer.parseInt(ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			personaDTO.setUsuarioRegistro(choferDTO.getUsuarioRegistro());
			this.personaGestor.crearActualizarPersona(personaDTO);
			contactoDTO.setCodigoPersona(personaDTO.getId().getCodigoPersona());
			choferDTO.setCodigoPersona(personaDTO.getId().getCodigoPersona());
			
			// Creamos o actualizamos el contacto
			contactoDTO.getId().setCodigoCompania(Integer.parseInt(ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			contactoDTO.setUsuarioRegistro(choferDTO.getUsuarioRegistro());
			contactoDTO.setCallePrincipal(contactoDTO.getCallePrincipal().toUpperCase());
			String direccion = contactoDTO.getCallePrincipal();
			if(contactoDTO.getNumeroCasa() != null){
				contactoDTO.setNumeroCasa(contactoDTO.getNumeroCasa().toUpperCase());
				direccion += " "+contactoDTO.getNumeroCasa();
			}
			if(contactoDTO.getCalleSecundaria() != null){
				contactoDTO.setCalleSecundaria(contactoDTO.getCalleSecundaria().toUpperCase());
				direccion += " "+contactoDTO.getCalleSecundaria();
			}
			contactoDTO.setDireccionPrincipal(direccion);
			if(contactoDTO.getReferencia() != null){
				contactoDTO.setReferencia(contactoDTO.getReferencia().toUpperCase());
			}
			contactoDTO.setCiudad(contactoDTO.getCiudad().toUpperCase());
			
			contactoDTO.setCodigoValarTipoContacto(ERPConstantes.CODIGO_CATALOGO_VALOR_TIPO_CONTACTO_PRINCIPAL);
			contactoDTO.setCodigoTipoContacto(ERPConstantes.CODIGO_CATALOGO_TIPOS_CONTACTOS);
			this.contactoGestor.crearActualizarContacto(contactoDTO);
			
			//Creamos o actualizamos el cliente
			choferDTO.getId().setCodigoCompania(Integer.parseInt(ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			choferDTO.setPersonaDTO(null);
			this.choferDAO.guardarActualizarChofer(choferDTO);
		}
		catch (ERPException e) {
			throw new ERPException("Error, "+e.getMessage());
		}
		catch (Exception e) {
			throw new ERPException("Error, "+e.getMessage());
		}
	}
}
