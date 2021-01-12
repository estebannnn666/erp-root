package ec.com.erp.clientes.gestor;

import java.sql.Timestamp;
import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ClienteDTO;
import ec.com.erp.cliente.mdl.dto.ContactoDTO;
import ec.com.erp.cliente.mdl.dto.EmpresaDTO;
import ec.com.erp.cliente.mdl.dto.PersonaDTO;
import ec.com.erp.clientes.dao.IClientesDAO;
import ec.com.erp.contacto.gestor.IContactoGestor;
import ec.com.erp.empresa.gestor.IEmpresaGestor;
import ec.com.erp.persona.gestor.IPersonaGestor;
import ec.com.erp.usuarios.gestor.IUsuariosGestor;

public class ClientesGestor implements IClientesGestor{

	private IClientesDAO clientesDAO;
	private IPersonaGestor personaGestor;
	private IEmpresaGestor empresaGestor;
	private IContactoGestor contactoGestor;
	private IUsuariosGestor usuariosGestor;
	
	public IClientesDAO getClientesDAO() {
		return clientesDAO;
	}

	public void setClientesDAO(IClientesDAO clientesDAO) {
		this.clientesDAO = clientesDAO;
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

	public IUsuariosGestor getUsuariosGestor() {
		return usuariosGestor;
	}

	public void setUsuariosGestor(IUsuariosGestor usuariosGestor) {
		this.usuariosGestor = usuariosGestor;
	}

	/**
	 * M\u00e9todo para obtener lista de clientes
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @param nombreCliente
	 * @return
	 * @throws ERPException
	 */
	public Collection<ClienteDTO> obtenerListaClientes(Integer codigoCompania, String numeroDocumento, String nombreCliente) throws ERPException{	
		return this.clientesDAO.obtenerListaClientes(codigoCompania, numeroDocumento, nombreCliente);
	}
	
	/**
	 * M\u009etodo para guardar y actualizar cliente
	 * @param clienteDTO
	 * @param contactoDTO
	 * @throws ERPException
	 */
	public void guardarActualizarClientes(ClienteDTO clienteDTO, ContactoDTO contactoDTO) throws ERPException{
		try {
//			UsuariosDTO usuarioDTO = clienteDTO.getUsuariosDTO();
			PersonaDTO personaDTO = clienteDTO.getPersonaDTO();
			EmpresaDTO empresaDTO = clienteDTO.getEmpresaDTO();
			// Se valida si el cliente ya existe para actualizar o guardar los datos de usuario
			/*if(clienteDTO.getId().getCodigoCliente() == null){
				usuarioDTO.setCodigoPerfil(ERPConstantes.CODIGO_PERFIL_CLIENTES);
				usuarioDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				this.usuariosGestor.crearUsuario(usuarioDTO);
			}
			else
			{
				this.usuariosGestor.actualizarUsuario(usuarioDTO);
			}*/
			// Creamos o actualizamos la persona o empresa
			if(clienteDTO.getCodigoValorTipoCliente().equals(ERPConstantes.CODIGO_CATALOGO_VALOR_TIPO_CLIENTE_PERSONA)){
				personaDTO.setPrimerApellido(personaDTO.getPrimerApellido().toUpperCase());
				personaDTO.setPrimerNombre(personaDTO.getPrimerNombre().toUpperCase());
				String nombreCompleto = personaDTO.getPrimerApellido();
				if(personaDTO.getSegundoApellido() != null){
					personaDTO.setSegundoApellido(personaDTO.getSegundoApellido().toUpperCase());
					nombreCompleto += " "+personaDTO.getSegundoApellido();
				}
				nombreCompleto += " "+personaDTO.getPrimerNombre();
				if(personaDTO.getSegundoNombre() != null){
					personaDTO.setSegundoNombre(personaDTO.getSegundoNombre().toUpperCase());
					nombreCompleto += " "+personaDTO.getSegundoNombre();
				}
				personaDTO.setNombreCompleto(nombreCompleto.toUpperCase());
				personaDTO.getId().setCodigoCompania(Integer.parseInt(ERPConstantes.ESTADO_ACTIVO_NUMERICO));
				personaDTO.setUsuarioRegistro(clienteDTO.getUserId());
				this.personaGestor.crearActualizarPersona(personaDTO);
				contactoDTO.setCodigoPersona(personaDTO.getId().getCodigoPersona());
				clienteDTO.setCodigoPersona(personaDTO.getId().getCodigoPersona());
			}
			else
			{
				empresaDTO.getId().setCodigoCompania(Integer.parseInt(ERPConstantes.ESTADO_ACTIVO_NUMERICO));
				empresaDTO.setUsuarioRegistro(clienteDTO.getUserId());
				empresaDTO.setRazonSocial(empresaDTO.getRazonSocial().toUpperCase());
				if(empresaDTO.getDescripcionEmpresa() != null){
					empresaDTO.setDescripcionEmpresa(empresaDTO.getDescripcionEmpresa().toUpperCase());
				}
				this.empresaGestor.crearActualizarEmpresa(empresaDTO);
				contactoDTO.setCodigoEmpresa(empresaDTO.getId().getCodigoEmpresa());
				clienteDTO.setCodigoEmpresa(empresaDTO.getId().getCodigoEmpresa());
			}
			
			// Creamos o actualizamos el contacto
			contactoDTO.getId().setCodigoCompania(Integer.parseInt(ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			contactoDTO.setUsuarioRegistro(clienteDTO.getUserId());
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
			clienteDTO.getId().setCodigoCompania(Integer.parseInt(ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			clienteDTO.setCodigoTipoCliente(ERPConstantes.CODIGO_CATALOGO_TIPOS_CLIENTES);
			clienteDTO.setCodigoTipoCompra(ERPConstantes.CODIGO_CATALOGO_TIPOS_COMPRA_CLIENTE);
			clienteDTO.setUsuarioRegistro(clienteDTO.getUserId());
			clienteDTO.setUserId(clienteDTO.getUserId());
			clienteDTO.setEmpresaDTO(null);
			clienteDTO.setUsuariosDTO(null);
			clienteDTO.setPersonaDTO(null);
			this.clientesDAO.guardarActualizarClientes(clienteDTO);
		}
		catch (ERPException e) {
			throw new ERPException("Error", e.getMessage());
		}
		catch (Exception e) {
			throw new ERPException("Error", e.getMessage());
		}
	}
	
	/**
	 * Obtener cliente por documento
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @param codigoValorTipoCliente
	 * @return
	 * @throws ERPException
	 */
	@Override
	public ClienteDTO obtenerClienteByCodigo(Integer codigoCompania, String numeroDocumento, String codigoValorTipoCliente) throws ERPException{
		Collection<ClienteDTO> clienteDTOCols =  this.clientesDAO.obtenerListaClientes(codigoCompania, numeroDocumento, null);
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setCodigoValorTipoCliente(codigoValorTipoCliente);
		if(CollectionUtils.isEmpty(clienteDTOCols)) {
			if(codigoValorTipoCliente != null){
				if(codigoValorTipoCliente.equals(ERPConstantes.CODIGO_CATALOGO_VALOR_TIPO_CLIENTE_PERSONA)) {
					Collection<PersonaDTO> personaDTOCols = this.personaGestor.obtenerListaPersona(codigoCompania, numeroDocumento);
					if(CollectionUtils.isNotEmpty(personaDTOCols)) {
						clienteDTO.setPersonaDTO(personaDTOCols.iterator().next());
					}
				}else if(codigoValorTipoCliente.equals(ERPConstantes.CODIGO_CATALOGO_VALOR_TIPO_CLIENTE_EMPRESA)) {
					EmpresaDTO empresaDTO = this.empresaGestor.obtenerEmpresaByCodigo(codigoCompania, numeroDocumento);
					if(empresaDTO != null) {
						clienteDTO.setEmpresaDTO(empresaDTO);
					}
				}
			}
			// Validar que exista empresa o persona para controlar el mensaje a mostrar
			if(clienteDTO.getPersonaDTO() == null && clienteDTO.getEmpresaDTO() == null) {
				clienteDTO = null;
			}
		}
		else
		{
			clienteDTO = clienteDTOCols.iterator().next();
		}
		return clienteDTO;
	}
	
	/**
	 * Metood para obtener cantidad de clientes todos o por fecha
	 * @param codigoCompania
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Long obtenerClientesTodosOFecha(Integer codigoCompania, Timestamp fechaInicio, Timestamp fechaFin) throws ERPException{
		return this.clientesDAO.obtenerClientesTodosOFecha(codigoCompania, fechaInicio, fechaFin);
	}
}
