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

import ec.com.erp.cliente.mdl.dto.id.PagosFacturaID;

/**
 * 
 * @author Esteban Gudino
 * 2020-06-24
 */
@Entity
@Table(name="SCVNTPAGOSFACTURA")
public class PagosFacturaDTO implements Serializable{
	private static final long serialVersionUID = 7863262235394607247L;

	@EmbeddedId
	private PagosFacturaID id = new PagosFacturaID();
	
	/**
	 * Especifica el codigo de factura relacionada
	 */
	@Column(name="CODIGOFACTURA")
	private Long codigoFactura;
	
	/**
	 * Especifica el valor en dolares de la transaccion
	 */
	@Column(name = "VALORPAGO")
	private BigDecimal valorPago ;
	
	/**
	 * Especifica el concepto de la transaccion
	 */
	@Column(name = "DESCRIPCION")
	private String descripcion ;
	
	/**
	 * Especifica la fecha de pago
	 */
	@Column(name="FECHAPAGO")
	private Date fechaPago;
	
	/**
	 * Especifica el codigo valor del tipo pago
	 */
	@Column(name = "CODIGOVALORPAGO")
	private String codigoValorPago ;
	
	/**
	 * Especifica el codigo tipo de pago
	 */
	@Column(name = "CODIGOTIPOPAGO")
	private Integer codigoTipoPago ;
	
	
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
		@JoinColumn(name = "CODIGOCOMPANIA", referencedColumnName = "CODIGOCOMPANIA", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOFACTURA", referencedColumnName = "CODIGOFACTURA", insertable = false, updatable = false)
	})
	private FacturaCabeceraDTO facturaCabeceraDTO;
	
	/**
	 * Referencia CatalogoValorDTO tipo de pago
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOVALORPAGO", referencedColumnName = "CODIGOCATALOGOVALOR", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOTIPOPAGO", referencedColumnName = "CODIGOCATALOGOTIPO", insertable = false, updatable = false)
	})
	private CatalogoValorDTO tipoPagoCatalogoValorDTO;
	
	public PagosFacturaID getId() {
		return id;
	}

	public void setId(PagosFacturaID id) {
		this.id = id;
	}

	public Long getCodigoFactura() {
		return codigoFactura;
	}

	public void setCodigoFactura(Long codigoFactura) {
		this.codigoFactura = codigoFactura;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
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

	public FacturaCabeceraDTO getFacturaCabeceraDTO() {
		return facturaCabeceraDTO;
	}

	public void setFacturaCabeceraDTO(FacturaCabeceraDTO facturaCabeceraDTO) {
		this.facturaCabeceraDTO = facturaCabeceraDTO;
	}

	public String getCodigoValorPago() {
		return codigoValorPago;
	}

	public void setCodigoValorPago(String codigoValorPago) {
		this.codigoValorPago = codigoValorPago;
	}

	public Integer getCodigoTipoPago() {
		return codigoTipoPago;
	}

	public void setCodigoTipoPago(Integer codigoTipoPago) {
		this.codigoTipoPago = codigoTipoPago;
	}

	public CatalogoValorDTO getTipoPagoCatalogoValorDTO() {
		return tipoPagoCatalogoValorDTO;
	}

	public void setTipoPagoCatalogoValorDTO(CatalogoValorDTO tipoPagoCatalogoValorDTO) {
		this.tipoPagoCatalogoValorDTO = tipoPagoCatalogoValorDTO;
	}
}
