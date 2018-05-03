package ec.com.erp.cliente.mdl.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ec.com.erp.cliente.mdl.dto.id.ContactoID;

/**
 * 
 * @author Esteban Gudino
 * 2017-07-13
 */
@Entity
@Table(name="SCVNTCONTACTO")
public class ContactoDTO implements Serializable{
	private static final long serialVersionUID = 7863262235394607247L;

	@EmbeddedId
	private ContactoID id = new ContactoID();
	
	/**
	 * Especifica el codigo de persona relacionada
	 */
	@Column(name = "CODIGOPERSONA")
	private Long codigoPersona ;
	
	/**
	 * Especifica el codigo de empresa relacionada
	 */
	@Column(name = "CODIGOEMPRESA")
	private Long codigoEmpresa ;
	
	/**
	 * Especifica la calle de direccion principal
	 */
	@Column(name = "DIRECCIONPRINCIPAL")
	private String direccionPrincipal ;
	
	/**
	 * Especifica la calle de direccion principal
	 */
	@Column(name = "CALLEPRINCIPAL")
	private String callePrincipal ;
	
	/**
	 * Especifica la calle de direccion secundaria
	 */
	@Column(name = "CALLESECUNDARIA")
	private String calleSecundaria ;
	
	/**
	 * Especifica el numero de casa
	 */
	@Column(name = "NUMEROCASA")
	private String numeroCasa ;
	
	/**
	 * Especificala referencia de la direccion
	 */
	@Column(name = "REFERENCIA")
	private String referencia ;
	
	/**
	 * Especifica la ciudad de la direccion
	 */
	@Column(name = "CIUDAD")
	private String ciudad ;
	
	/**
	 * Especifica el telefono principal
	 */
	@Column(name = "TELEFONOPRINCIPAL")
	private String telefonoPrincipal ;
	
	/**
	 * Especifica el telefono celular
	 */
	@Column(name = "TELEFONOCELULAR")
	private String telefonoCelular ;
	
	/**
	 * Especifica el codigo valor del tipo de contacto
	 */
	@Column(name = "CODIGOVALORTIPOCONTACTO")
	private String codigoValarTipoContacto ;
	
	/**
	 * Especifica el codigo tipo de contacto
	 */
	@Column(name = "CODIGOTIPOCONTACTO")
	private Integer codigoTipoContacto ;
	
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
	 * Referencia CatalogoValorDTO tipo de contacto
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOVALORTIPOCONTACTO", referencedColumnName = "CODIGOCATALOGOVALOR", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOTIPOCONTACTO", referencedColumnName = "CODIGOCATALOGOTIPO", insertable = false, updatable = false)
	})
	private CatalogoValorDTO tipoContactoCatalogoValorDTO;
	
	/**
	 * Referencia CatalogoValorDTO tipo de contacto
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOCOMPANIA", referencedColumnName = "CODIGOCOMPANIA", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOPERSONA", referencedColumnName = "CODIGOPERSONA", insertable = false, updatable = false)
	})
	private PersonaDTO personaDTO;
	
	/**
	 * Referencia CatalogoValorDTO tipo de contacto
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOCOMPANIA", referencedColumnName = "CODIGOCOMPANIA", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOEMPRESA", referencedColumnName = "CODIGOEMPRESA", insertable = false, updatable = false)
	})
	private EmpresaDTO empresaDTO;

	public ContactoID getId() {
		return id;
	}

	public void setId(ContactoID id) {
		this.id = id;
	}

	public Long getCodigoPersona() {
		return codigoPersona;
	}

	public void setCodigoPersona(Long codigoPersona) {
		this.codigoPersona = codigoPersona;
	}

	public Long getCodigoEmpresa() {
		return codigoEmpresa;
	}

	public void setCodigoEmpresa(Long codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	public String getCallePrincipal() {
		return callePrincipal;
	}

	public void setCallePrincipal(String callePrincipal) {
		this.callePrincipal = callePrincipal;
	}

	public String getCalleSecundaria() {
		return calleSecundaria;
	}

	public void setCalleSecundaria(String calleSecundaria) {
		this.calleSecundaria = calleSecundaria;
	}

	public String getNumeroCasa() {
		return numeroCasa;
	}

	public void setNumeroCasa(String numeroCasa) {
		this.numeroCasa = numeroCasa;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getTelefonoPrincipal() {
		return telefonoPrincipal;
	}

	public void setTelefonoPrincipal(String telefonoPrincipal) {
		this.telefonoPrincipal = telefonoPrincipal;
	}

	public String getTelefonoCelular() {
		return telefonoCelular;
	}

	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	public String getCodigoValarTipoContacto() {
		return codigoValarTipoContacto;
	}

	public void setCodigoValarTipoContacto(String codigoValarTipoContacto) {
		this.codigoValarTipoContacto = codigoValarTipoContacto;
	}

	public Integer getCodigoTipoContacto() {
		return codigoTipoContacto;
	}

	public void setCodigoTipoContacto(Integer codigoTipoContacto) {
		this.codigoTipoContacto = codigoTipoContacto;
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

	public CatalogoValorDTO getTipoContactoCatalogoValorDTO() {
		return tipoContactoCatalogoValorDTO;
	}

	public void setTipoContactoCatalogoValorDTO(CatalogoValorDTO tipoContactoCatalogoValorDTO) {
		this.tipoContactoCatalogoValorDTO = tipoContactoCatalogoValorDTO;
	}

	public PersonaDTO getPersonaDTO() {
		return personaDTO;
	}

	public void setPersonaDTO(PersonaDTO personaDTO) {
		this.personaDTO = personaDTO;
	}

	public EmpresaDTO getEmpresaDTO() {
		return empresaDTO;
	}

	public void setEmpresaDTO(EmpresaDTO empresaDTO) {
		this.empresaDTO = empresaDTO;
	}

	public String getDireccionPrincipal() {
		return direccionPrincipal;
	}

	public void setDireccionPrincipal(String direccionPrincipal) {
		this.direccionPrincipal = direccionPrincipal;
	}

}
