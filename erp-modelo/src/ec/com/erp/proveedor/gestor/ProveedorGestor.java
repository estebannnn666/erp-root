package ec.com.erp.proveedor.gestor;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ContactoDTO;
import ec.com.erp.cliente.mdl.dto.EmpresaDTO;
import ec.com.erp.cliente.mdl.dto.PersonaDTO;
import ec.com.erp.cliente.mdl.dto.ProveedorDTO;
import ec.com.erp.contacto.gestor.IContactoGestor;
import ec.com.erp.empresa.gestor.IEmpresaGestor;
import ec.com.erp.persona.gestor.IPersonaGestor;
import ec.com.erp.proveedor.dao.IProveedorDAO;

public class ProveedorGestor implements IProveedorGestor{

	private IProveedorDAO proveedorDAO;
	private IPersonaGestor personaGestor;
	private IEmpresaGestor empresaGestor;
	private IContactoGestor contactoGestor;
	
	public IProveedorDAO getProveedorDAO() {
		return proveedorDAO;
	}

	public void setProveedorDAO(IProveedorDAO proveedorDAO) {
		this.proveedorDAO = proveedorDAO;
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
	 * Obtener proveedor por documento
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @param codigoValorTipoProveedor
	 * @return
	 * @throws ERPException
	 */
	@Override
	public ProveedorDTO obtenerProveedor(Integer codigoCompania, String numeroDocumento, String codigoValorTipoProveedor) throws ERPException{
		Collection<ProveedorDTO> proveedorDTOCols =  this.proveedorDAO.obtenerListaProveedores(codigoCompania, numeroDocumento, null);
		ProveedorDTO proveedorDTO = new ProveedorDTO();
		proveedorDTO.setCodigoValorTipoProveedor(codigoValorTipoProveedor);
		if(CollectionUtils.isEmpty(proveedorDTOCols)) {
			if(codigoValorTipoProveedor != null){
				if(codigoValorTipoProveedor.equals(ERPConstantes.CODIGO_CATALOGO_VALOR_TIPO_CLIENTE_PERSONA)) {
					Collection<PersonaDTO> personaDTOCols = this.personaGestor.obtenerListaPersona(codigoCompania, numeroDocumento);
					if(CollectionUtils.isNotEmpty(personaDTOCols)) {
						proveedorDTO.setPersonaDTO(personaDTOCols.iterator().next());
					}
				}else if(codigoValorTipoProveedor.equals(ERPConstantes.CODIGO_CATALOGO_VALOR_TIPO_CLIENTE_EMPRESA)) {
					EmpresaDTO empresaDTO = this.empresaGestor.obtenerEmpresaByCodigo(codigoCompania, numeroDocumento);
					if(empresaDTO != null) {
						proveedorDTO.setEmpresaDTO(empresaDTO);
					}
				}
			}
			// Validar que exista empresa o persona para controlar el mensaje a mostrar
			if(proveedorDTO.getPersonaDTO() == null && proveedorDTO.getEmpresaDTO() == null) {
				proveedorDTO = null;
			}
		}
		else
		{
			proveedorDTO = proveedorDTOCols.iterator().next();
		}
		return proveedorDTO;
	}
	
	/**
	 * M\u00e9todo para obtener lista de proveedores
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @param razonSocial
	 * @return 
	 * @throws ERPException
	 */
	@Override
	public Collection<ProveedorDTO> obtenerListaProveedores(Integer codigoCompania, String numeroDocumento, String razonSocial) throws ERPException{
		return this.proveedorDAO.obtenerListaProveedores(codigoCompania, numeroDocumento, razonSocial);
	}
	
	/**
	 * M\u009etodo para guardar y actualizar proveedor
	 * @param proveedorDTO
	 * @param contactoDTO
	 * @throws ERPException
	 */
	@Override
	public void guardarActualizarProveedor(ProveedorDTO proveedorDTO, ContactoDTO contactoDTO) throws ERPException{
		try {
			PersonaDTO personaDTO = proveedorDTO.getPersonaDTO();
			EmpresaDTO empresaDTO = proveedorDTO.getEmpresaDTO();
			// Creamos o actualizamos la persona o empresa
			if(proveedorDTO.getCodigoValorTipoProveedor().equals(ERPConstantes.CODIGO_CATALOGO_VALOR_TIPO_CLIENTE_PERSONA)){
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
				personaDTO.setUsuarioRegistro(proveedorDTO.getUsuarioRegistro());
				this.personaGestor.crearActualizarPersona(personaDTO);
				contactoDTO.setCodigoPersona(personaDTO.getId().getCodigoPersona());
				proveedorDTO.setCodigoPersona(personaDTO.getId().getCodigoPersona());
			}
			else
			{
				empresaDTO.getId().setCodigoCompania(Integer.parseInt(ERPConstantes.ESTADO_ACTIVO_NUMERICO));
				empresaDTO.setUsuarioRegistro(proveedorDTO.getUsuarioRegistro());
				empresaDTO.setRazonSocial(empresaDTO.getRazonSocial().toUpperCase());
				if(empresaDTO.getDescripcionEmpresa() != null){
					empresaDTO.setDescripcionEmpresa(empresaDTO.getDescripcionEmpresa().toUpperCase());
				}
				this.empresaGestor.crearActualizarEmpresa(empresaDTO);
				contactoDTO.setCodigoEmpresa(empresaDTO.getId().getCodigoEmpresa());
				proveedorDTO.setCodigoEmpresa(empresaDTO.getId().getCodigoEmpresa());
			}
			
			// Creamos o actualizamos el contacto
			contactoDTO.getId().setCodigoCompania(Integer.parseInt(ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			contactoDTO.setUsuarioRegistro(proveedorDTO.getUsuarioRegistro());
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
			proveedorDTO.getId().setCodigoCompania(Integer.parseInt(ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			proveedorDTO.setCodigoTipoProveedor(ERPConstantes.CODIGO_CATALOGO_TIPOS_CLIENTES);
			proveedorDTO.setEmpresaDTO(null);
			proveedorDTO.setPersonaDTO(null);
			this.proveedorDAO.guardarActualizarProveedor(proveedorDTO);
		}
		catch (ERPException e) {
			throw new ERPException("Error ", e.getMessage());
		}
		catch (Exception e) {
			throw new ERPException("Error ", e.getMessage());
		}
	}
}
