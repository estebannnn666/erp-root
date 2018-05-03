package ec.com.erp.transportistas.gestor;

import java.util.Collection;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ContactoDTO;
import ec.com.erp.cliente.mdl.dto.EmpresaDTO;
import ec.com.erp.cliente.mdl.dto.PersonaDTO;
import ec.com.erp.cliente.mdl.dto.TransportistaDTO;
import ec.com.erp.contacto.gestor.IContactoGestor;
import ec.com.erp.empresa.gestor.IEmpresaGestor;
import ec.com.erp.persona.gestor.IPersonaGestor;
import ec.com.erp.transportistas.dao.ITransportistasDAO;

public class TransportistasGestor implements ITransportistasGestor{

	private ITransportistasDAO transportistasDAO;
	private IPersonaGestor personaGestor;
	private IEmpresaGestor empresaGestor;
	private IContactoGestor contactoGestor;
	
	public ITransportistasDAO getTransportistasDAO() {
		return transportistasDAO;
	}

	public void setTransportistasDAO(ITransportistasDAO transportistasDAO) {
		this.transportistasDAO = transportistasDAO;
	}

	public IPersonaGestor getPersonaGestor() {
		return personaGestor;
	}

	public void setPersonaGestor(IPersonaGestor personaGestor) {
		this.personaGestor = personaGestor;
	}

	public IEmpresaGestor getEmpresaGestor() {
		return empresaGestor;
	}

	public void setEmpresaGestor(IEmpresaGestor empresaGestor) {
		this.empresaGestor = empresaGestor;
	}

	public IContactoGestor getContactoGestor() {
		return contactoGestor;
	}

	public void setContactoGestor(IContactoGestor contactoGestor) {
		this.contactoGestor = contactoGestor;
	}

	/**
	 * M\u00e9todo para obtener lista de transportista
	 * @param codigoCompania
	 * @return Collection<TransportistaDTO>
	 * @throws ERPException
	 */
	@Override
	public Collection<TransportistaDTO> obtenerListaTransportistas(Integer codigoCompania, String numeroDocumento, String razonSocial) throws ERPException{
		return this.transportistasDAO.obtenerListaTransportistas(codigoCompania, numeroDocumento, razonSocial);
	}
	
	/**
	 * M\u009etodo para guardar y actualizar transportista
	 * @param transportistaDTO
	 * @param contactoDTO
	 * @throws ERPException
	 */
	@Override
	public void guardarActualizarTransportista(TransportistaDTO transportistaDTO, ContactoDTO contactoDTO) throws ERPException{
		try {
			PersonaDTO personaDTO = transportistaDTO.getPersonaDTO();
			EmpresaDTO empresaDTO = transportistaDTO.getEmpresaDTO();
			// Creamos o actualizamos la persona o empresa
			if(transportistaDTO.getCodigoValorTipoTransportista().equals(ERPConstantes.CODIGO_CATALOGO_VALOR_TIPO_CLIENTE_PERSONA)){
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
				personaDTO.setUsuarioRegistro(transportistaDTO.getUsuarioRegistro());
				this.personaGestor.crearActualizarPersona(personaDTO);
				contactoDTO.setCodigoPersona(personaDTO.getId().getCodigoPersona());
				transportistaDTO.setCodigoPersona(personaDTO.getId().getCodigoPersona());
			}
			else
			{
				empresaDTO.getId().setCodigoCompania(Integer.parseInt(ERPConstantes.ESTADO_ACTIVO_NUMERICO));
				empresaDTO.setUsuarioRegistro(transportistaDTO.getUsuarioRegistro());
				empresaDTO.setRazonSocial(empresaDTO.getRazonSocial().toUpperCase());
				if(empresaDTO.getDescripcionEmpresa() != null){
					empresaDTO.setDescripcionEmpresa(empresaDTO.getDescripcionEmpresa().toUpperCase());
				}
				this.empresaGestor.crearActualizarEmpresa(empresaDTO);
				contactoDTO.setCodigoEmpresa(empresaDTO.getId().getCodigoEmpresa());
				transportistaDTO.setCodigoEmpresa(empresaDTO.getId().getCodigoEmpresa());
			}
			
			// Creamos o actualizamos el contacto
			contactoDTO.getId().setCodigoCompania(Integer.parseInt(ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			contactoDTO.setUsuarioRegistro(transportistaDTO.getUsuarioRegistro());
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
			transportistaDTO.getId().setCodigoCompania(Integer.parseInt(ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			transportistaDTO.setEmpresaDTO(null);
			transportistaDTO.setPersonaDTO(null);
			this.transportistasDAO.guardarActualizarTransportista(transportistaDTO);
		}
		catch (ERPException e) {
			throw new ERPException("Error, "+e.getMessage());
		}
		catch (Exception e) {
			throw new ERPException("Error, "+e.getMessage());
		}
	}
}
