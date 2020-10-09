package ec.com.erp.firebase.gestor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

import org.apache.commons.collections.CollectionUtils;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ClienteDTO;
import ec.com.erp.cliente.mdl.dto.ContactoDTO;
import ec.com.erp.cliente.mdl.dto.EmpresaDTO;
import ec.com.erp.cliente.mdl.dto.PersonaDTO;
import ec.com.erp.clientes.gestor.IClientesGestor;
import ec.com.erp.contacto.gestor.IContactoGestor;
import ec.com.erp.empresa.gestor.IEmpresaGestor;
import ec.com.erp.firebase.commons.provider.ClientProvider;
import ec.com.erp.firebase.commons.provider.CommonProvider;
import ec.com.erp.firebase.model.Client;
import ec.com.erp.firebase.model.Sequence;
import ec.com.erp.persona.gestor.IPersonaGestor;

public class FireBaseClienteGestor implements IFireBaseClienteGestor {

	private IClientesGestor clientesGestor;
	private IPersonaGestor personaGestor;
	private IEmpresaGestor empresaGestor;
	private IContactoGestor contactoGestor;
	
	public IClientesGestor getClientesGestor() {
		return clientesGestor;
	}

	public void setClientesGestor(IClientesGestor clientesGestor) {
		this.clientesGestor = clientesGestor;
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
	 * M\u00e9todo para descargar los clientes de fire base
	 * @return 
	 * @throws ERPException
	 */
	public void descargarClientesFireBase() throws ERPException{
		try {
			Collection<Client> clientsFireBase = ClientProvider.obtainClientFirebase();
			Collection<ClienteDTO> clienteDTOCols = this.clientesGestor.obtenerListaClientes(ERPConstantes.CODIGO_COMPANIA, null, null);
			clientsFireBase.stream().forEach(clienteFireBase ->{
				ClienteDTO clienteDTOLocal = clienteDTOCols.stream()
						.filter(clienteLocal -> (clienteLocal.getPersonaDTO() != null && clienteLocal.getPersonaDTO().getNumeroDocumento().equals(clienteFireBase.getDocument())) || (clienteLocal.getEmpresaDTO() != null && clienteLocal.getEmpresaDTO().getNumeroRuc().equals(clienteFireBase.getDocument())))
						.findFirst().orElse(null);
				
				ContactoDTO contactoDTO = null;
				if(clienteDTOLocal == null) {
					ClienteDTO clienteDTO = new ClienteDTO();
					clienteDTO.getId().setCodigoCompania(ERPConstantes.CODIGO_COMPANIA);
					clienteDTO.setUserId(ERPConstantes.USUARIO_GENERICO);
					clienteDTO.setUsuarioRegistro(ERPConstantes.USUARIO_GENERICO);
					if(clienteFireBase.getBuyType().equals(ERPConstantes.TIPO_CLIENTE_MINORISTA)) {
						clienteDTO.setCodigoValorTipoCompra(ERPConstantes.CODIGO_CATALOGO_VALOR_CLIENTE_MINORISTA);
					}else {
						clienteDTO.setCodigoValorTipoCompra(ERPConstantes.CODIGO_CATALOGO_VALOR_CLIENTE_MAYORISTA);
					}
					if(clienteFireBase.getType().equals(ERPConstantes.TIPO_CLIENTE_PERSONA)) {
						clienteDTO.setCodigoValorTipoCliente(ERPConstantes.CODIGO_CATALOGO_VALOR_TIPO_CLIENTE_PERSONA);
						Collection<PersonaDTO> personaDTOCols = this.personaGestor.obtenerListaPersona(ERPConstantes.CODIGO_COMPANIA, clienteFireBase.getDocument());
						if(CollectionUtils.isEmpty(personaDTOCols)) {
							clienteDTO.setPersonaDTO(new PersonaDTO());
							clienteDTO.getPersonaDTO().setNombreCompleto(clienteFireBase.getName());
							clienteDTO.getPersonaDTO().setNumeroDocumento(clienteFireBase.getDocument());
							String[] nombres = clienteFireBase.getName().split(" ");
							if(nombres.length > 1) {
								clienteDTO.getPersonaDTO().setPrimerNombre(nombres[0]);
								clienteDTO.getPersonaDTO().setPrimerApellido(nombres[1]);
							}else {
								clienteDTO.getPersonaDTO().setPrimerNombre(nombres[0]);
								clienteDTO.getPersonaDTO().setPrimerApellido(nombres[0]);
							}
						}else {
							clienteDTO.setPersonaDTO(personaDTOCols.iterator().next());
							contactoDTO = this.contactoGestor.obtenerListaContactos(ERPConstantes.CODIGO_COMPANIA, personaDTOCols.iterator().next().getId().getCodigoPersona(), null);
						}
						clienteDTO.getPersonaDTO().getId().setCodigoCompania(ERPConstantes.CODIGO_COMPANIA);
						clienteDTO.getPersonaDTO().setUsuarioRegistro(ERPConstantes.USUARIO_GENERICO);
					}else {
						clienteDTO.setCodigoValorTipoCliente(ERPConstantes.CODIGO_CATALOGO_VALOR_TIPO_CLIENTE_EMPRESA);
						EmpresaDTO empresaDTO = this.empresaGestor.obtenerEmpresaByCodigo(ERPConstantes.CODIGO_COMPANIA, clienteFireBase.getDocument());
						if(empresaDTO == null) {
							clienteDTO.setEmpresaDTO(new EmpresaDTO());
							clienteDTO.getEmpresaDTO().setRazonSocial(clienteFireBase.getName());
							clienteDTO.getEmpresaDTO().setDescripcionEmpresa(clienteFireBase.getName());
							clienteDTO.getEmpresaDTO().setNumeroRuc(clienteFireBase.getDocument());
							clienteDTO.getEmpresaDTO().setUsuarioRegistro(ERPConstantes.USUARIO_GENERICO);
						}else {
							clienteDTO.setEmpresaDTO(empresaDTO);
							contactoDTO = this.contactoGestor.obtenerListaContactos(ERPConstantes.CODIGO_COMPANIA, null, empresaDTO.getId().getCodigoEmpresa());
						}
						clienteDTO.getEmpresaDTO().getId().setCodigoCompania(ERPConstantes.CODIGO_COMPANIA);
						clienteDTO.getEmpresaDTO().setUsuarioRegistro(ERPConstantes.USUARIO_GENERICO);
					}
					
					if(contactoDTO == null) {
						contactoDTO = new ContactoDTO();
						contactoDTO.getId().setCodigoCompania(ERPConstantes.CODIGO_COMPANIA);
						contactoDTO.setUsuarioRegistro(ERPConstantes.USUARIO_GENERICO);
						contactoDTO.setCallePrincipal(clienteFireBase.getAddress());
						contactoDTO.setDireccionPrincipal(clienteFireBase.getAddress());
						contactoDTO.setNumeroCasa("N/D");
						contactoDTO.setCiudad(clienteFireBase.getCity());
						contactoDTO.setTelefonoPrincipal(clienteFireBase.getTelephone());
					}
					this.clientesGestor.guardarActualizarClientes(clienteDTO, contactoDTO);
				}
			});
		} catch (InterruptedException e) {
			throw new ERPException("Error, {0}",e.getMessage()) ;
		} catch (ExecutionException e) {
			throw new ERPException("Error, {0}",e.getMessage()) ;
		}
	}	
	
	
	/**
	 * M\u00e9todo para guardar los clientes locales a fire base
	 * @return 
	 * @throws ERPException
	 */
	public void guardarClientesFireBase() throws ERPException{
		try {
			Sequence secuencial = CommonProvider.obtenerSecuencial();
			Integer[] secuencialCliente = new Integer[]{Integer.parseInt(secuencial.getClient())};
			Collection<Client> clientsFireBase = ClientProvider.obtainClientFirebase();
			Collection<ClienteDTO> clienteDTOCols = this.clientesGestor.obtenerListaClientes(ERPConstantes.CODIGO_COMPANIA, null, null);
			Collection<Client> clientsUpload = new ArrayList<>();
			clienteDTOCols.stream().forEach(clienteLocal ->{
				Client client = clientsFireBase.stream().filter(clienteFireBase -> (clienteLocal.getPersonaDTO() != null && clienteLocal.getPersonaDTO().getNumeroDocumento().equals(clienteFireBase.getDocument())) || (clienteLocal.getEmpresaDTO() != null && clienteLocal.getEmpresaDTO().getNumeroRuc().equals(clienteFireBase.getDocument())))
						.findFirst().orElse(null);
				if(client == null) {
					Client clientSave = new Client();
					clientSave.setId(secuencialCliente[0]);
					if(clienteLocal.getCodigoValorTipoCliente().equals(ERPConstantes.CODIGO_CATALOGO_VALOR_TIPO_CLIENTE_PERSONA)) {
						clientSave.setName(clienteLocal.getPersonaDTO().getNombreCompleto());
						clientSave.setDocument(clienteLocal.getPersonaDTO().getNumeroDocumento());
						clientSave.setType(ERPConstantes.TIPO_CLIENTE_PERSONA);
						clientSave.setAddress(clienteLocal.getPersonaDTO().getContactoPersonaDTO().getDireccionPrincipal());
						clientSave.setCity(clienteLocal.getPersonaDTO().getContactoPersonaDTO().getCiudad());
						clientSave.setTelephone(clienteLocal.getPersonaDTO().getContactoPersonaDTO().getTelefonoPrincipal());
					}else {
						clientSave.setType(ERPConstantes.TIPO_CLIENTE_EMPRESA);
						clientSave.setName(clienteLocal.getEmpresaDTO().getRazonSocial());
						clientSave.setDocument(clienteLocal.getEmpresaDTO().getNumeroRuc());
						clientSave.setAddress(clienteLocal.getEmpresaDTO().getContactoEmpresaDTO().getDireccionPrincipal());
						clientSave.setCity(clienteLocal.getEmpresaDTO().getContactoEmpresaDTO().getCiudad());
						clientSave.setTelephone(clienteLocal.getEmpresaDTO().getContactoEmpresaDTO().getTelefonoPrincipal());
					}
					if(clienteLocal.getCodigoValorTipoCompra().equals(ERPConstantes.CODIGO_CATALOGO_VALOR_CLIENTE_MINORISTA)) {
						clientSave.setBuyType(ERPConstantes.TIPO_CLIENTE_MINORISTA);
					}else {
						clientSave.setBuyType(ERPConstantes.TIPO_CLIENTE_MAYORISTAS);
					}
					clientSave.setEmail("emailpruebas@gmail.com");
					clientsUpload.add(clientSave);
					secuencialCliente[0]++;
				}
			});
			// Save client in fire base
			ClientProvider.createUpdateClient(clientsUpload);
			// Update sequense
			CommonProvider.updateSequence("client", String.valueOf(secuencialCliente[0]));
			
		} catch (IOException | InterruptedException | ExecutionException e1) {
			throw new ERPException("Error, {0}",e1.getMessage()) ;
		}
	}	
}
