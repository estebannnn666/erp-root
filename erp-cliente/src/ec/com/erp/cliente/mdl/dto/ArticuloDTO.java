package ec.com.erp.cliente.mdl.dto;

import java.io.Serializable;
import java.math.BigDecimal;
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
import org.springframework.util.CollectionUtils;

import ec.com.erp.cliente.mdl.dto.id.ArticuloID;
import ec.com.erp.utilitario.commons.constantes.CollectionType;

/**
 * 
 * @author Esteban Gudino
 * 2017-06-06
 */
@Entity
@Table(name="SCVNTARTICULO")
public class ArticuloDTO implements Serializable{
	private static final long serialVersionUID = 7863262235394607247L;

	@EmbeddedId
	private ArticuloID id = new ArticuloID();
	
	/**
	 * Codigo de barras del articulo
	 */
	@Column(name = "CODIGOBARRAS", nullable = false)
	private String codigoBarras ;
	
	/**
	 * Nombre del articulo
	 */
	@Column(name = "NOMBREARTICULO", nullable = false)
	private String nombreArticulo ;
	
	/**
	 * Especifica el peso del articulo
	 */
	@Column(name="PESO")
	private BigDecimal peso;
	
	/**
	 * Especifica el precio del articulo
	 */
	@Column(name="PRECIO")
	private BigDecimal precio;
	
	/**
	 * Especifica el precio minorista
	 */
	@Column(name="PRECIOMINORISTA")
	private BigDecimal precioMinorista;
	
	/**
	 * Especifica el precio del articulo
	 */
	@Column(name="COSTO")
	private BigDecimal costo;
	
	/**
	 * Especifica la cantidad disponible
	 */
	@Column(name="CANTIDADSTOCK")
	private Integer cantidadStock;
	
	/**
	 * Especifica el porcentaje comision
	 */
	@Column(name="PORCENTAJECOMISION")
	private BigDecimal porcentajeComision;
	
	/**
	 * Especifica el porcentaje comision al por mayor
	 */
	@Column(name="PORCENTAJECOMISIONMAYOR")
	private BigDecimal porcentajeComisionMayor;
	
	/**
	 * Especifica ka imagen del item
	 */
	@Column(name="IMAGEN")
	private byte[] imagen; 
	
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
	private Boolean tieneImpuesto;
	
	/**
	 * Referencia a detalle de la factura
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "articuloDTO")
	@CollectionTypeInfo(name = CollectionType.LIST_COLLECTION_TYPE)
	private Collection<ArticuloImpuestoDTO> articuloImpuestoDTOCols;
	
	/**
	 * Referencia a articulo unidad de manejo
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "articuloDTO")
	@CollectionTypeInfo(name = CollectionType.LIST_COLLECTION_TYPE)
	private Collection<ArticuloUnidadManejoDTO> articuloUnidadManejoDTOCols;
	
	public ArticuloID getId() {
		return id;
	}

	public void setId(ArticuloID id) {
		this.id = id;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getNombreArticulo() {
		return nombreArticulo;
	}

	public void setNombreArticulo(String nombreArticulo) {
		this.nombreArticulo = nombreArticulo;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}
	
	public BigDecimal getCosto() {
		return costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Integer getCantidadStock() {
		return cantidadStock;
	}

	public void setCantidadStock(Integer cantidadStock) {
		this.cantidadStock = cantidadStock;
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

	public Collection<ArticuloImpuestoDTO> getArticuloImpuestoDTOCols() {
		return articuloImpuestoDTOCols;
	}

	public void setArticuloImpuestoDTOCols(Collection<ArticuloImpuestoDTO> articuloImpuestoDTOCols) {
		this.articuloImpuestoDTOCols = articuloImpuestoDTOCols;
	}

	public Boolean getTieneImpuesto() {
		if(CollectionUtils.isEmpty(this.articuloImpuestoDTOCols)){
			tieneImpuesto = Boolean.FALSE;
		}else{
			tieneImpuesto = Boolean.TRUE;
		}
		return tieneImpuesto;
	}

	public void setTieneImpuesto(Boolean tieneImpuesto) {
		this.tieneImpuesto = tieneImpuesto;
	}

	public Collection<ArticuloUnidadManejoDTO> getArticuloUnidadManejoDTOCols() {
		return articuloUnidadManejoDTOCols;
	}

	public void setArticuloUnidadManejoDTOCols(Collection<ArticuloUnidadManejoDTO> articuloUnidadManejoDTOCols) {
		this.articuloUnidadManejoDTOCols = articuloUnidadManejoDTOCols;
	}

	public BigDecimal getPorcentajeComision() {
		return porcentajeComision;
	}

	public void setPorcentajeComision(BigDecimal porcentajeComision) {
		this.porcentajeComision = porcentajeComision;
	}
	
	public BigDecimal getPorcentajeComisionMayor() {
		return porcentajeComisionMayor;
	}

	public void setPorcentajeComisionMayor(BigDecimal porcentajeComisionMayor) {
		this.porcentajeComisionMayor = porcentajeComisionMayor;
	}

	public BigDecimal getPrecioMinorista() {
		return precioMinorista;
	}

	public void setPrecioMinorista(BigDecimal precioMinorista) {
		this.precioMinorista = precioMinorista;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
}
