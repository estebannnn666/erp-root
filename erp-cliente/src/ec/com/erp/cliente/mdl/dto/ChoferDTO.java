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

import ec.com.erp.cliente.mdl.dto.id.ChoferID;

/**
 * 
 * @author Esteban Gudino
 * 2018-04-19
 */
@Entity
@Table(name="SCVNTCHOFER")
public class ChoferDTO implements Serializable{
	private static final long serialVersionUID = 7863262235394607247L;

	@EmbeddedId
	private ChoferID id = new ChoferID();
	
	/**
	 * Especifica el codigo de persona relacionada
	 */
	@Column(name = "CODIGOPERSONA")
	private Long codigoPersona ;
	
	/**
	 * Especifica el codigo de empresa relacionada
	 */
	@Column(name = "CODIGOTRANSPORTISTA")
	private Long codigoTransportista ;
	
	/**
	 * Especifica el codigo valor del tipo de licencia
	 */
	@Column(name = "CODIGOVALORTIPOLICENCIA")
	private String codigoValorTipoLicencia ;
	
	/**
	 * Especifica el codigo tipo de licencia
	 */
	@Column(name = "CODIGOTIPOLICENCIA")
	private Integer codigoTipoLicencia ;
	
	@Column(name="FECHACADUCIDADLICENCIA")
	private Date fechaCaducidadLicencia;
	
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
	 * Referencia CatalogoValorDTO tipo de licencia
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOVALORTIPOLICENCIA", referencedColumnName = "CODIGOCATALOGOVALOR", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOTIPOLICENCIA", referencedColumnName = "CODIGOCATALOGOTIPO", insertable = false, updatable = false)
	})
	private CatalogoValorDTO tipoLicenciaCatalogoValorDTO;
	
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
		@JoinColumn(name = "CODIGOTRANSPORTISTA", referencedColumnName = "CODIGOTRANSPORTISTA", insertable = false, updatable = false)
	})
	private TransportistaDTO transportistaDTO;

	public ChoferID getId() {
		return id;
	}

	public void setId(ChoferID id) {
		this.id = id;
	}

	public Long getCodigoPersona() {
		return codigoPersona;
	}

	public void setCodigoPersona(Long codigoPersona) {
		this.codigoPersona = codigoPersona;
	}

	public Long getCodigoTransportista() {
		return codigoTransportista;
	}

	public void setCodigoTransportista(Long codigoTransportista) {
		this.codigoTransportista = codigoTransportista;
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

	public PersonaDTO getPersonaDTO() {
		return personaDTO;
	}

	public void setPersonaDTO(PersonaDTO personaDTO) {
		this.personaDTO = personaDTO;
	}

	public TransportistaDTO getTransportistaDTO() {
		return transportistaDTO;
	}

	public void setTransportistaDTO(TransportistaDTO transportistaDTO) {
		this.transportistaDTO = transportistaDTO;
	}

	public String getCodigoValorTipoLicencia() {
		return codigoValorTipoLicencia;
	}

	public void setCodigoValorTipoLicencia(String codigoValorTipoLicencia) {
		this.codigoValorTipoLicencia = codigoValorTipoLicencia;
	}

	public Integer getCodigoTipoLicencia() {
		return codigoTipoLicencia;
	}

	public void setCodigoTipoLicencia(Integer codigoTipoLicencia) {
		this.codigoTipoLicencia = codigoTipoLicencia;
	}

	public Date getFechaCaducidadLicencia() {
		return fechaCaducidadLicencia;
	}

	public void setFechaCaducidadLicencia(Date fechaCaducidadLicencia) {
		this.fechaCaducidadLicencia = fechaCaducidadLicencia;
	}

	public CatalogoValorDTO getTipoLicenciaCatalogoValorDTO() {
		return tipoLicenciaCatalogoValorDTO;
	}

	public void setTipoLicenciaCatalogoValorDTO(CatalogoValorDTO tipoLicenciaCatalogoValorDTO) {
		this.tipoLicenciaCatalogoValorDTO = tipoLicenciaCatalogoValorDTO;
	}
	
}
