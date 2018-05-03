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

import ec.com.erp.cliente.mdl.dto.id.GuiaDespachoExtrasID;

/**
 * 
 * @author Esteban Gudino
 * 2018-04-19
 */
@Entity
@Table(name="SCVNTGUIADESPACHOEXTRAS")
public class GuiaDespachoExtrasDTO implements Serializable{
	private static final long serialVersionUID = 7863262235394607247L;

	@EmbeddedId
	private GuiaDespachoExtrasID id = new GuiaDespachoExtrasID();
	
	/**
	 * Especifica el codigo de guia de despacho relacionado
	 */
	@Column(name = "CODIGOGUIADESPACHO")
	private Long codigoGuiaDespacho ;
	
	/**
	 * Especifica la descripcion del producto extra 
	 */
	@Column(name = "DESCRIPCIONPRODUCTO")
	private String descripcionProducto ;
	
	/**
	 * Especifica la cantidad de producto extra
	 */
	@Column(name = "CANTIDAD")
	private Integer cantidad ; 
	
	/**
	 * Especifica la observacion del producto extra 
	 */
	@Column(name = "OBSERVACION")
	private String observacion ;
	
	/**
	 * Estado de la factura
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
	 * Referencia al entidad guia despacho
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOCOMPANIA", referencedColumnName = "CODIGOCOMPANIA", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOGUIADESPACHO", referencedColumnName = "CODIGOGUIADESPACHO", insertable = false, updatable = false)
	})
	private GuiaDespachoDTO guiaDespachoDTO;

	public GuiaDespachoExtrasID getId() {
		return id;
	}

	public void setId(GuiaDespachoExtrasID id) {
		this.id = id;
	}

	public Long getCodigoGuiaDespacho() {
		return codigoGuiaDespacho;
	}

	public void setCodigoGuiaDespacho(Long codigoGuiaDespacho) {
		this.codigoGuiaDespacho = codigoGuiaDespacho;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
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

	public GuiaDespachoDTO getGuiaDespachoDTO() {
		return guiaDespachoDTO;
	}

	public void setGuiaDespachoDTO(GuiaDespachoDTO guiaDespachoDTO) {
		this.guiaDespachoDTO = guiaDespachoDTO;
	}
}
