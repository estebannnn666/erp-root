package ec.com.erp.vendedor.gestor;

import java.sql.Timestamp;
import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ContactoDTO;
import ec.com.erp.cliente.mdl.dto.FacturaCabeceraDTO;
import ec.com.erp.cliente.mdl.dto.PersonaDTO;
import ec.com.erp.cliente.mdl.dto.VendedorDTO;
import ec.com.erp.contacto.gestor.IContactoGestor;
import ec.com.erp.persona.gestor.IPersonaGestor;
import ec.com.erp.vendedor.dao.IVendedorDAO;

public class VendedorGestor implements IVendedorGestor{

	private IVendedorDAO vendedorDAO;
	private IPersonaGestor personaGestor;
	private IContactoGestor contactoGestor;
	
	public IVendedorDAO getVendedorDAO() {
		return vendedorDAO;
	}

	public void setVendedorDAO(IVendedorDAO vendedorDAO) {
		this.vendedorDAO = vendedorDAO;
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
	 * Obtener vendedor por documento
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @return
	 * @throws ERPException
	 */
	@Override
	public VendedorDTO obtenerVendedor(Integer codigoCompania, String numeroDocumento) throws ERPException{
		Collection<VendedorDTO> vendedorDTOCols =  this.vendedorDAO.obtenerListaVendedores(codigoCompania, numeroDocumento, null);
		VendedorDTO vendedorDTO = new VendedorDTO();
		if(CollectionUtils.isEmpty(vendedorDTOCols)) {
			Collection<PersonaDTO> personaDTOCols = this.personaGestor.obtenerListaPersona(codigoCompania, numeroDocumento);
			if(CollectionUtils.isNotEmpty(personaDTOCols)) {
				vendedorDTO.setPersonaDTO(personaDTOCols.iterator().next());
			}
			// Validar que exista empresa o persona para controlar el mensaje a mostrar
			if(vendedorDTO.getPersonaDTO() == null) {
				vendedorDTO = null;
			}
		}else{
			vendedorDTO = vendedorDTOCols.iterator().next();
		}
		return vendedorDTO;
	}
	
	/**
	 * M\u00e9todo para obtener lista de proveedores
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @param nombreVendedor
	 * @return 
	 * @throws ERPException
	 */
	@Override
	public Collection<VendedorDTO> obtenerListaVendedores(Integer codigoCompania, String numeroDocumento, String nombreVendedor) throws ERPException{
		return this.vendedorDAO.obtenerListaVendedores(codigoCompania, numeroDocumento, nombreVendedor);
	}
	
	/**
	 * Metodo para obtener lista de facturas por fecha y vendedor
	 * @param codigoCompania
	 * @param codigoVendedor
	 * @param fechaFacturaInicio
	 * @param fechaFacturaFin
	 * @return
	 */
	@Override
	public Collection<FacturaCabeceraDTO> listaFacturasPorVendedorFechaVenta(Integer codigoCompania, Long codigoVendedor, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin){
		return this.vendedorDAO.listaFacturasPorVendedorFechaVenta(codigoCompania, codigoVendedor, fechaFacturaInicio, fechaFacturaFin);
	}
	
	/**
	 * M\u009etodo para guardar y actualizar proveedor
	 * @param vendedorDTO
	 * @param contactoDTO
	 * @throws ERPException
	 */
	@Override
	public void guardarActualizarVendedor(VendedorDTO vendedorDTO, ContactoDTO contactoDTO) throws ERPException{
		try {
			PersonaDTO personaDTO = vendedorDTO.getPersonaDTO();
			// Creamos o actualizamos la persona o empresa
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
			personaDTO.setUsuarioRegistro(vendedorDTO.getUsuarioRegistro());
			this.personaGestor.crearActualizarPersona(personaDTO);
			contactoDTO.setCodigoPersona(personaDTO.getId().getCodigoPersona());
			vendedorDTO.setCodigoPersona(personaDTO.getId().getCodigoPersona());
			
			// Creamos o actualizamos el contacto
			contactoDTO.getId().setCodigoCompania(Integer.parseInt(ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			contactoDTO.setUsuarioRegistro(vendedorDTO.getUsuarioRegistro());
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
			vendedorDTO.getId().setCodigoCompania(Integer.parseInt(ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			vendedorDTO.setPersonaDTO(null);
			this.vendedorDAO.guardarActualizarVendedor(vendedorDTO);
		}
		catch (ERPException e) {
			throw new ERPException("Error, ", e.getMessage());
		}
		catch (Exception e) {
			throw new ERPException("Error, ", e.getMessage());
		}
	}
}
