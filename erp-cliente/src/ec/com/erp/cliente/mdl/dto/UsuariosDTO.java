package ec.com.erp.cliente.mdl.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import ec.com.erp.cliente.mdl.dto.id.UsuariosID;


/**
 * 
 * @author Esteban Gudino
 * 2017-06-06
 */
@Entity
@Table(name="SCVNTUSUARIO")
public class UsuariosDTO implements Serializable{
	private static final long serialVersionUID = 7863262235394607247L;

	@EmbeddedId
	private UsuariosID id = new UsuariosID();
	
	/**
	 * Nombre de usuario
	 */
	@Column(name = "CODIGOPERFIL", nullable = false)
	private BigDecimal codigoPerfil ;
	
	/**
	 * Codigo de compania
	 */
	@Column(name = "CODIGOCOMPANIA", nullable = false)
	private Integer codigoCompania ;
	
	/**
	 * Nombre de usuario
	 */
	@Column(name = "NOMBREUSUARIO", nullable = false)
	private String nombreUsuario ;
	
	/**
	 * Email del usaurio
	 */
	@Column(name="PASSWORDUSUARIO")
	private String passwordUsuario;
	
	/**
	 * Email del usaurio
	 */
	@Transient
	private Boolean logeado;
	
	/**
	 * Referencia a la relacion con la entidad perfilDTO
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOPERFIL", referencedColumnName = "CODIGOPERFIL", insertable = false, updatable = false)
	})
	private PerfilDTO perfilDTO;
	
	/**
	 * Referencia a la relacion con la entidad companiaDTO
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOCOMPANIA", referencedColumnName = "CODIGOCOMPANIA", insertable = false, updatable = false)
	})
	private CompaniaDTO companiaDTO;
	
	/**
	 * Estado del registro usuario
	 */
	@Column(name="ESTADO")
	private String estado;

	public UsuariosID getId() {
		return id;
	}

	public void setId(UsuariosID id) {
		this.id = id;
	}

	public BigDecimal getCodigoPerfil() {
		return codigoPerfil;
	}

	public void setCodigoPerfil(BigDecimal codigoPerfil) {
		this.codigoPerfil = codigoPerfil;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPasswordUsuario() {
		return passwordUsuario;
	}

	public void setPasswordUsuario(String passwordUsuario) {
		this.passwordUsuario = passwordUsuario;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Boolean getLogeado() {
		return logeado;
	}

	public void setLogeado(Boolean logeado) {
		this.logeado = logeado;
	}

	public PerfilDTO getPerfilDTO() {
		return perfilDTO;
	}

	public void setPerfilDTO(PerfilDTO perfilDTO) {
		this.perfilDTO = perfilDTO;
	}

	public Integer getCodigoCompania() {
		return codigoCompania;
	}

	public void setCodigoCompania(Integer codigoCompania) {
		this.codigoCompania = codigoCompania;
	}

	public CompaniaDTO getCompaniaDTO() {
		return companiaDTO;
	}

	public void setCompaniaDTO(CompaniaDTO companiaDTO) {
		this.companiaDTO = companiaDTO;
	}
}
