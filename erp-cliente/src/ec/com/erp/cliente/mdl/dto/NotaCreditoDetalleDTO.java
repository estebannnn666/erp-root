package ec.com.erp.cliente.mdl.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import ec.com.erp.cliente.mdl.dto.id.NotaCreditoDetalleID;

/**
 * 
 * @author Esteban Gudino
 * 2021-11-26
 */
@Entity
@Table(name="SCVNTNOTACREDITODETALLE")
public class NotaCreditoDetalleDTO implements Serializable{
	private static final long serialVersionUID = 7863262235394607247L;

	@EmbeddedId
	private NotaCreditoDetalleID id = new NotaCreditoDetalleID();
	
	@Transient
	private Long ordenRegistro;
	
	/**
	 * Especifica la cantidad pedida
	 */
	@Column(name = "CODIGOARTICULO")
	private Integer codigoArticulo ;
	
	/**
	 * Especifica la unidad de manejo
	 */
	@Column(name = "CODIGOARTICULOUNIDADMANEJO")
	private Integer codigoArticuloUnidadManejo ;
	
	/**
	 * Especifica la cantidad pedida
	 */
	@Column(name = "CANTIDAD")
	private Integer cantidad ;
	
	@Column(name="CODIGONOTACREDITO")
	private Long codigoNotaCredito;
	
	/**
	 * Especifica la descripcion o detalle de la factura
	 */
	@Column(name = "DESCRIPCION")
	private String descripcion ;
	
	/**
	 * Especifica el valor por unidad
	 */
	@Column(name = "DESCUENTO")
	private BigDecimal descuento ;
	
	/**
	 * Especifica el valor por unidad
	 */
	@Column(name = "VALORUNIDAD")
	private BigDecimal valorUnidad ;
	
	/**
	 * Especifica el subtotal de cada registro
	 */
	@Column(name = "SUBTOTAL")
	private BigDecimal subTotal ;
	
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
	
	@Transient
	private String codigoBarras;
	
	/**
	 * Referencia al entidad factura
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOCOMPANIA", referencedColumnName = "CODIGOCOMPANIA", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGONOTACREDITO", referencedColumnName = "CODIGONOTACREDITO", insertable = false, updatable = false)
	})
	private NotaCreditoDTO notaCreditoDTO;

	/**
	 * Referencia al entidad Articulo
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOCOMPANIA", referencedColumnName = "CODIGOCOMPANIA", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOARTICULO", referencedColumnName = "CODIGOARTICULO", insertable = false, updatable = false)
	})
	private ArticuloDTO articuloDTO;
	
	/**
	 * Referencia al entidad Articulo
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOCOMPANIA", referencedColumnName = "CODIGOCOMPANIA", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOARTICULOUNIDADMANEJO", referencedColumnName = "CODIGOARTICULOUNIDADMANEJO", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOARTICULO", referencedColumnName = "CODIGOARTICULO", insertable = false, updatable = false)
	})
	private ArticuloUnidadManejoDTO articuloUnidadManejoDTO;
	
	
	public NotaCreditoDetalleID getId() {
		return id;
	}

	public void setId(NotaCreditoDetalleID id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Long getCodigoNotaCredito() {
		return codigoNotaCredito;
	}

	public void setCodigoNotaCredito(Long codigoNotaCredito) {
		this.codigoNotaCredito = codigoNotaCredito;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getDescuento() {
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public BigDecimal getValorUnidad() {
		return valorUnidad;
	}

	public void setValorUnidad(BigDecimal valorUnidad) {
		this.valorUnidad = valorUnidad;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
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

	public NotaCreditoDTO getNotaCreditoDTO() {
		return notaCreditoDTO;
	}

	public void setNotaCreditoDTO(NotaCreditoDTO notaCreditoDTO) {
		this.notaCreditoDTO = notaCreditoDTO;
	}

	public Integer getCodigoArticulo() {
		return codigoArticulo;
	}

	public void setCodigoArticulo(Integer codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}

	public ArticuloDTO getArticuloDTO() {
		return articuloDTO;
	}

	public void setArticuloDTO(ArticuloDTO articuloDTO) {
		this.articuloDTO = articuloDTO;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public Integer getCodigoArticuloUnidadManejo() {
		return codigoArticuloUnidadManejo;
	}

	public void setCodigoArticuloUnidadManejo(Integer codigoArticuloUnidadManejo) {
		this.codigoArticuloUnidadManejo = codigoArticuloUnidadManejo;
	}

	public ArticuloUnidadManejoDTO getArticuloUnidadManejoDTO() {
		return articuloUnidadManejoDTO;
	}

	public void setArticuloUnidadManejoDTO(ArticuloUnidadManejoDTO articuloUnidadManejoDTO) {
		this.articuloUnidadManejoDTO = articuloUnidadManejoDTO;
	}

	public Long getOrdenRegistro() {
		this.ordenRegistro = this.id.getCodigoDetalleNotaCredito();
		return this.ordenRegistro;
	}

	public void setOrdenRegistro(Long ordenRegistro) {
		this.ordenRegistro = ordenRegistro;
	}
	
}
