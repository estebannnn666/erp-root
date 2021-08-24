package ec.com.erp.cliente.mdl.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import ec.com.erp.cliente.mdl.dto.id.ParametroID;

/**
 * 
 * @author Esteban Gudino
 * 2017-06-06
 */
@Entity
@Table(name="SCVNTPARAMETRO")
public class ParametroDTO implements Serializable{
	private static final long serialVersionUID = 7863262235394607247L;

	@EmbeddedId
	private ParametroID id = new ParametroID();
	
	/**
	 * Nombre del valor del parametro
	 */
	@Column(name = "VALORPARAMETRO", nullable = false)
	private String valorParametro ;
	
	/**
	 * Nombre de la descripcion del parametro
	 */
	@Column(name = "DESCRIPCIONPARAMETRO", nullable = false)
	private String descripcionParametro ;
	
	/**
	 * Estado del parametro
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

	public ParametroID getId() {
		return id;
	}

	public void setId(ParametroID id) {
		this.id = id;
	}

	public String getValorParametro() {
		return valorParametro;
	}

	public void setValorParametro(String valorParametro) {
		this.valorParametro = valorParametro;
	}

	public String getDescripcionParametro() {
		return descripcionParametro;
	}

	public void setDescripcionParametro(String descripcionParametro) {
		this.descripcionParametro = descripcionParametro;
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
}
