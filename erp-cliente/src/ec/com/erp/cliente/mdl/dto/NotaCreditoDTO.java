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

import org.hibernate.annotations.CollectionTypeInfo;

import ec.com.erp.cliente.mdl.dto.id.NotaCreditoID;
import ec.com.erp.utilitario.commons.constantes.CollectionType;

/**
 * 
 * @author Esteban Gudino
 * 2018-04-19
 */
@Entity
@Table(name="SCVNTNOTACREDITO")
public class NotaCreditoDTO implements Serializable{
	private static final long serialVersionUID = 7863262235394607247L;

	@EmbeddedId
	private NotaCreditoID id = new NotaCreditoID();
	
	/**
	 * Especifica el codigo de factura
	 */
	@Column(name="CODIGOFACTURA")
	private Long codigoFactura;
	
	/**
	 * Especifica el numero de nota de credito
	 */
	@Column(name = "NUMERODOCUMENTO")
	private String numeroDocumento ;
	
	/**
	 * Especifica la fecha de nota de credito
	 */
	@Column(name = "FECHADOCUMENTO")
	private Date fechaDocumento ;
	
	/**
	 * Especifica el ruc o documento del cliente
	 */
	@Column(name = "RUCCLIENTE")
	private String rucCliente ;
	
	/**
	 * Especifica el nombre del cliente 
	 */
	@Column(name = "RAZONSOCIAL")
	private String razonSocial ;
	
	/**
	 * Especifica si la factura o gasto esta pagado
	 */
	@Column(name = "PAGADO")
	private Boolean pagado ;
	
	/**
	 * Especifica la direccion email del cliente
	 */
	@Column(name = "EMAIL")
	private String email;
	
	/**
	 * Especifica la fecha de emision de la factura a cancelar
	 */
	@Column(name = "FECHAEMISIONFACTURA")
	private Date fechaEmisionFactura ;
	
	/**
	 * Especifica el numero de comprobante de factura
	 */
	@Column(name = "NUMEROCOMPRABANTE")
	private String numeroComprobante ;
	
	/**
	 * Especifica el motivo de cancelacion
	 */
	@Column(name = "MOTIVO")
	private String motivo ;
	
	/**
	 * Especifica el descuento de la factura
	 */
	@Column(name = "DESCUENTO")
	private BigDecimal descuento ;
	
	/**
	 * Especifica el total de la compra
	 */
	@Column(name = "TOTALSINIMPUESTOS")
	private BigDecimal totalSinImpuestos ;
	
	/**
	 * Especifica el total de la compra
	 */
	@Column(name = "TOTALIMPUESTOS")
	private BigDecimal totalImpuestos ;
	
	/**
	 * Especifica el total de IVA
	 */
	@Column(name = "TOTALIVA")
	private BigDecimal totalIva ;
	
	/**
	 * Especifica el sub total del pedido
	 */
	@Column(name = "SUBTOTAL")
	private BigDecimal subTotal ;
	
	/**
	 * Especifica el total de la compra
	 */
	@Column(name = "TOTALCUENTA")
	private BigDecimal totalCuenta ;
	
	/**
	 * Especifica el tipo de cliente mayorista o minorista
	 */
	@Column(name = "TIPOCLIENTE")
	private String tipoCliente ;
	
	/**
	 * Especifica el tipo de ruc uno o dos
	 */
	@Column(name = "TIPORUC")
	private String tipoRuc ;
	
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
	 * Referencia a detalle de la nota de credito
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "notaCreditoDTO")
	@CollectionTypeInfo(name = CollectionType.LIST_COLLECTION_TYPE)
	private Collection<NotaCreditoDetalleDTO> notaCreditoDetalleDTOCols;
	
	public NotaCreditoID getId() {
		return id;
	}

	public void setId(NotaCreditoID id) {
		this.id = id;
	}

	public Long getCodigoFactura() {
		return codigoFactura;
	}

	public void setCodigoFactura(Long codigoFactura) {
		this.codigoFactura = codigoFactura;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Date getFechaDocumento() {
		return fechaDocumento;
	}

	public void setFechaDocumento(Date fechaDocumento) {
		this.fechaDocumento = fechaDocumento;
	}

	public String getRucCliente() {
		return rucCliente;
	}

	public void setRucCliente(String rucCliente) {
		this.rucCliente = rucCliente;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public Boolean getPagado() {
		return pagado;
	}

	public void setPagado(Boolean pagado) {
		this.pagado = pagado;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaEmisionFactura() {
		return fechaEmisionFactura;
	}

	public void setFechaEmisionFactura(Date fechaEmisionFactura) {
		this.fechaEmisionFactura = fechaEmisionFactura;
	}

	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public BigDecimal getDescuento() {
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public BigDecimal getTotalSinImpuestos() {
		return totalSinImpuestos;
	}

	public void setTotalSinImpuestos(BigDecimal totalSinImpuestos) {
		this.totalSinImpuestos = totalSinImpuestos;
	}

	public BigDecimal getTotalImpuestos() {
		return totalImpuestos;
	}

	public void setTotalImpuestos(BigDecimal totalImpuestos) {
		this.totalImpuestos = totalImpuestos;
	}

	public BigDecimal getTotalIva() {
		return totalIva;
	}

	public void setTotalIva(BigDecimal totalIva) {
		this.totalIva = totalIva;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getTotalCuenta() {
		return totalCuenta;
	}

	public void setTotalCuenta(BigDecimal totalCuenta) {
		this.totalCuenta = totalCuenta;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getTipoRuc() {
		return tipoRuc;
	}

	public void setTipoRuc(String tipoRuc) {
		this.tipoRuc = tipoRuc;
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

	public Collection<NotaCreditoDetalleDTO> getNotaCreditoDetalleDTOCols() {
		return notaCreditoDetalleDTOCols;
	}

	public void setNotaCreditoDetalleDTOCols(Collection<NotaCreditoDetalleDTO> notaCreditoDetalleDTOCols) {
		this.notaCreditoDetalleDTOCols = notaCreditoDetalleDTOCols;
	}
}
