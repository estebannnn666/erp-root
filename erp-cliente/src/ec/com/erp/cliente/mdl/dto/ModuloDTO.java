package ec.com.erp.cliente.mdl.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import ec.com.erp.cliente.mdl.dto.id.ModuloID;

/**
 * 
 * @author Esteban Gudino
 * 2017-06-06
 */
@Entity
@Table(name="SCVNTMODULOS")
public class ModuloDTO implements Serializable{
	private static final long serialVersionUID = 7863262235394607247L;

	@EmbeddedId
	private ModuloID id = new ModuloID();
	
	/**
	 * codigo de referencia para el modulo
	 */
	@Column(name = "CODIGOREFERENCIA", nullable = false)
	private String codigoReferencia ;
	
	/**
	 * Orden para vizualizar el modulo
	 */
	@Column(name = "ORDEN", nullable = false)
	private Integer orden ;
	
	/**
	 * Nombre del modulo
	 */
	@Column(name = "NOMBREMODULO", nullable = false)
	private String nombreModulo ;
	/**
	 * Descripcion del modulo
	 */
	@Column(name = "DESCRIPCION")
	private String descripcion ;
	
	/**
	 * Estilo de modulo
	 */
	@Column(name = "ESTILO", nullable = false)
	private String estilo ;
	
	/**
	 * Url para accerder al modulo
	 */
	@Column(name = "URL", nullable = false)
	private String url ;
	
	/**
	 * Tipo de modulo
	 */
	@Column(name = "VALORTIPO", nullable = false)
	private String valorTipo ;
	
	/**
	 * Estado del registro
	 */
	@Column(name="ESTADO",  nullable = false)
	private String estado;
	
	@Transient
	private Boolean seleccionado;
	
	@Transient
	private Boolean mostrarPerfil;
	
	@Transient
	private Boolean menuActivo;
	
	public ModuloID getId() {
		return id;
	}

	public void setId(ModuloID id) {
		this.id = id;
	}

	public String getCodigoReferencia() {
		return codigoReferencia;
	}

	public void setCodigoReferencia(String codigoReferencia) {
		this.codigoReferencia = codigoReferencia;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public String getNombreModulo() {
		return nombreModulo;
	}

	public void setNombreModulo(String nombreModulo) {
		this.nombreModulo = nombreModulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Boolean getSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(Boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	public String getValorTipo() {
		return valorTipo;
	}

	public void setValorTipo(String valorTipo) {
		this.valorTipo = valorTipo;
	}

	public Boolean getMostrarPerfil() {
		return mostrarPerfil;
	}

	public void setMostrarPerfil(Boolean mostrarPerfil) {
		this.mostrarPerfil = mostrarPerfil;
	}

	public Boolean getMenuActivo() {
		return menuActivo;
	}

	public void setMenuActivo(Boolean menuActivo) {
		this.menuActivo = menuActivo;
	}
}
