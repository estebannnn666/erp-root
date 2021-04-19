package ec.com.erp.cliente.mdl.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
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

import ec.com.erp.cliente.mdl.dto.id.InventarioID;

/**
 * 
 * @author Esteban Gudino
 * 2017-07-13
 */
@Entity
@Table(name="SCVNTINVETARIO")
public class InventarioDTO implements Serializable{
	private static final long serialVersionUID = 7863262235394607247L;

	@EmbeddedId
	private InventarioID id = new InventarioID();
	
	@Column(name = "CODIGOARTICULO")
	private Integer codigoArticulo ;
	
	/**
	 * Especifica la unidad de manejo
	 */
	@Column(name = "CODIGOARTICULOUNIDADMANEJO")
	private Integer codigoArticuloUnidadManejo ;
	
	@Column(name="FECHAMOVIMIENTO")
	private Date fechaMovimiento;
	
	@Column(name="DETALLEMOVIMIENTO")
	private String detalleMoviento;
	
	@Column(name="CANTIDADENTRADA")
	private Integer cantidadEntrada;
	
	@Column(name = "VALORUNIDADENTRADA")
	private BigDecimal valorUnidadEntrada;
	
	@Column(name = "VALORTOTALENTRADA")
	private BigDecimal valorTotalEntrada;
	
	@Column(name="CANTIDADSALIDA")
	private Integer cantidadSalida;
	
	@Column(name = "VALORUNIDADSALIDA")
	private BigDecimal valorUnidadSalida ;
	
	@Column(name = "VALORTOTALSALIDA")
	private BigDecimal valorTotalSalida ;
	
	@Column(name="CANTIDADEXISTENCIA")
	private Integer cantidadExistencia;
	
	@Column(name = "VALORUNIDADEXISTENCIA")
	private BigDecimal valorUnidadExistencia ;
	
	@Column(name = "VALORTOTALEXISTENCIA")
	private BigDecimal valorTotalExistencia ;
	
	@Column(name="ESULTIMOREGISTRO")
	private String esUltimoRegistro;
	
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
	
	@Transient
	private Collection<ArticuloUnidadManejoDTO> unidadesManejoCols;

	public InventarioID getId() {
		return id;
	}

	public void setId(InventarioID id) {
		this.id = id;
	}

	public Integer getCodigoArticulo() {
		return codigoArticulo;
	}

	public void setCodigoArticulo(Integer codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}

	public Date getFechaMovimiento() {
		return fechaMovimiento;
	}

	public void setFechaMovimiento(Date fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}

	public String getDetalleMoviento() {
		return detalleMoviento;
	}

	public void setDetalleMoviento(String detalleMoviento) {
		this.detalleMoviento = detalleMoviento;
	}

	public Integer getCantidadEntrada() {
		return cantidadEntrada;
	}

	public void setCantidadEntrada(Integer cantidadEntrada) {
		this.cantidadEntrada = cantidadEntrada;
	}

	public BigDecimal getValorUnidadEntrada() {
		return valorUnidadEntrada;
	}

	public void setValorUnidadEntrada(BigDecimal valorUnidadEntrada) {
		this.valorUnidadEntrada = valorUnidadEntrada;
	}

	public BigDecimal getValorTotalEntrada() {
		return valorTotalEntrada;
	}

	public void setValorTotalEntrada(BigDecimal valorTotalEntrada) {
		this.valorTotalEntrada = valorTotalEntrada;
	}

	public Integer getCantidadSalida() {
		return cantidadSalida;
	}

	public void setCantidadSalida(Integer cantidadSalida) {
		this.cantidadSalida = cantidadSalida;
	}

	public BigDecimal getValorUnidadSalida() {
		return valorUnidadSalida;
	}

	public void setValorUnidadSalida(BigDecimal valorUnidadSalida) {
		this.valorUnidadSalida = valorUnidadSalida;
	}

	public BigDecimal getValorTotalSalida() {
		return valorTotalSalida;
	}

	public void setValorTotalSalida(BigDecimal valorTotalSalida) {
		this.valorTotalSalida = valorTotalSalida;
	}

	public Integer getCantidadExistencia() {
		return cantidadExistencia;
	}

	public void setCantidadExistencia(Integer cantidadExistencia) {
		this.cantidadExistencia = cantidadExistencia;
	}

	public BigDecimal getValorUnidadExistencia() {
		return valorUnidadExistencia;
	}

	public void setValorUnidadExistencia(BigDecimal valorUnidadExistencia) {
		this.valorUnidadExistencia = valorUnidadExistencia;
	}

	public BigDecimal getValorTotalExistencia() {
		return valorTotalExistencia;
	}

	public void setValorTotalExistencia(BigDecimal valorTotalExistencia) {
		this.valorTotalExistencia = valorTotalExistencia;
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

	public ArticuloDTO getArticuloDTO() {
		return articuloDTO;
	}

	public void setArticuloDTO(ArticuloDTO articuloDTO) {
		this.articuloDTO = articuloDTO;
	}

	public String getEsUltimoRegistro() {
		return esUltimoRegistro;
	}

	public void setEsUltimoRegistro(String esUltimoRegistro) {
		this.esUltimoRegistro = esUltimoRegistro;
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

	public Collection<ArticuloUnidadManejoDTO> getUnidadesManejoCols() {
		return unidadesManejoCols;
	}

	public void setUnidadesManejoCols(Collection<ArticuloUnidadManejoDTO> unidadesManejoCols) {
		this.unidadesManejoCols = unidadesManejoCols;
	}
}
