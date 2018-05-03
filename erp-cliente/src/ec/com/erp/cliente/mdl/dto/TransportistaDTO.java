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

import ec.com.erp.cliente.mdl.dto.id.TransportistaID;

/**
 * 
 * @author Esteban Gudino
 * 2018-04-19
 */
@Entity
@Table(name="SCVNTTRANSPORTISTA")
public class TransportistaDTO implements Serializable{
	private static final long serialVersionUID = 7863262235394607247L;

	@EmbeddedId
	private TransportistaID id = new TransportistaID();
	
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
	 * Especifica el codigo valor del tipo de contacto
	 */
	@Column(name = "CODIGOVALORTIPOTRANSPORTISTA")
	private String codigoValorTipoTransportista ;
	
	/**
	 * Especifica el codigo tipo de contacto
	 */
	@Column(name = "CODIGOTIPOTRANSPORTISTA")
	private Integer codigoTipoTransportista ;
	
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
		@JoinColumn(name = "CODIGOVALORTIPOTRANSPORTISTA", referencedColumnName = "CODIGOCATALOGOVALOR", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOTIPOTRANSPORTISTA", referencedColumnName = "CODIGOCATALOGOTIPO", insertable = false, updatable = false)
	})
	private CatalogoValorDTO tipoTransportistaCatalogoValorDTO;
	
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

	public TransportistaID getId() {
		return id;
	}

	public void setId(TransportistaID id) {
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

	public String getCodigoValorTipoTransportista() {
		return codigoValorTipoTransportista;
	}

	public void setCodigoValorTipoTransportista(String codigoValorTipoTransportista) {
		this.codigoValorTipoTransportista = codigoValorTipoTransportista;
	}

	public Integer getCodigoTipoTransportista() {
		return codigoTipoTransportista;
	}

	public void setCodigoTipoTransportista(Integer codigoTipoTransportista) {
		this.codigoTipoTransportista = codigoTipoTransportista;
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

	public CatalogoValorDTO getTipoTransportistaCatalogoValorDTO() {
		return tipoTransportistaCatalogoValorDTO;
	}

	public void setTipoTransportistaCatalogoValorDTO(CatalogoValorDTO tipoTransportistaCatalogoValorDTO) {
		this.tipoTransportistaCatalogoValorDTO = tipoTransportistaCatalogoValorDTO;
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

}
