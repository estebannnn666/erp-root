package ec.com.erp.cliente.mdl.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionTypeInfo;

import ec.com.erp.cliente.mdl.dto.id.PersonaID;
import ec.com.erp.utilitario.commons.constantes.CollectionType;

/**
 * 
 * @author Esteban Gudino
 * 2017-07-13
 */
@Entity
@Table(name="SCVNTPERSONA")
public class PersonaDTO implements Serializable{
	private static final long serialVersionUID = 7863262235394607247L;

	@EmbeddedId
	private PersonaID id = new PersonaID();
	
	/**
	 * Especifica el numero de documento de la persona
	 */
	@Column(name = "NUMERODOCUMENTO", nullable = false)
	private String numeroDocumento ;
	
	/**
	 * Especifica el primer apellido de la persona
	 */
	@Column(name = "PRIMERAPELLIDO", nullable = false)
	private String primerApellido ;
	
	/**
	 * Especifica el segundo apellido de la persona
	 */
	@Column(name = "SEGUNDOAPELLIDO")
	private String segundoApellido ;
	
	/**
	 * Especifica el primer nombre de la persona
	 */
	@Column(name = "PRIMERNOMBRE", nullable = false)
	private String primerNombre ;
	
	/**
	 * Especifica el segundo nombre de la persona
	 */
	@Column(name = "SEGUNDONOMBRE")
	private String segundoNombre ;
	
	/**
	 *  Especifica el nombre completo de la persona
	 */
	@Column(name = "NOMBRECOMPLETO")
	private String nombreCompleto ;
	
	/**
	 * Especifica la fecha de nacimiento de la persona
	 */
	@Column(name="FECHANACIMIENTO")
	private Date fechaNacimiento;
	
	/**
	 * Estado del registro usuario
	 */
	@Column(name="ESTADO")
	private String estado;

	@Column(name="USUARIOREGISTRO")
	private String usuarioRegistro;
	
	@Column(name="FECHAREGISTRO")
	private Date fechaRegistro;
	
	@Column(name="USUARIOMODIFICACION")
	private String usuarioModificacion;
	
	@Column(name="FECHAMODIFICACION")
	private Date fechaModificacion;
	
	/**
	 * Referencia a Estado de negociacion
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "personaDTO")
	@CollectionTypeInfo(name = CollectionType.LIST_COLLECTION_TYPE)
	private Collection<ContactoDTO> contactoDTOCols;
	
	@Transient
	private ContactoDTO contactoPersonaDTO;

	public PersonaID getId() {
		return id;
	}

	public void setId(PersonaID id) {
		this.id = id;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUsuarioRegistro() {
		return usuarioRegistro;
	}

	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Collection<ContactoDTO> getContactoDTOCols() {
		return contactoDTOCols;
	}

	public void setContactoDTOCols(Collection<ContactoDTO> contactoDTOCols) {
		this.contactoDTOCols = contactoDTOCols;
	}

	public ContactoDTO getContactoPersonaDTO() {
		return contactoPersonaDTO;
	}

	public void setContactoPersonaDTO(ContactoDTO contactoPersonaDTO) {
		this.contactoPersonaDTO = contactoPersonaDTO;
	}
	
}
