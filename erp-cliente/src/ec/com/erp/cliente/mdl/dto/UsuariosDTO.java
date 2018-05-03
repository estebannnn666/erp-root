package ec.com.erp.cliente.mdl.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
}
