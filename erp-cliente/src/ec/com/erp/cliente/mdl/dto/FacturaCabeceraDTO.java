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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionTypeInfo;

import ec.com.erp.cliente.mdl.dto.id.FacturaCabeceraID;
import ec.com.erp.utilitario.commons.constantes.CollectionType;

/**
 * 
 * @author Esteban Gudino
 * 2018-04-19
 */
@Entity
@Table(name="SCVNTFACTURACABECERA")
public class FacturaCabeceraDTO implements Serializable{
	private static final long serialVersionUID = 7863262235394607247L;

	@EmbeddedId
	private FacturaCabeceraID id = new FacturaCabeceraID();
	
	/**
	 * Especifica el codigo de pedido si la factura fue generada desde un pedido
	 */
	@Column(name="CODIGOPEDIDO")
	private Long codigoPedido;
	
	/**
	 * Especifica el codigo de vendedor
	 */
	@Column(name="CODIGOVENDEDOR")
	private Long codigoVendedor;
	
	/**
	 * Especifica el numero de factura o numero de documento de debito
	 */
	@Column(name = "NUMERODOCUMENTO")
	private String numeroDocumento ;
	
	
	/**
	 * Especifica el codigo de referencia de la factura
	 */
	@Column(name = "CODIGOREFERENCIAFACTURA")
	private String codigoReferenciaFactura ;
	
	/**
	 * Especifica la fecha de factura o documento
	 */
	@Column(name = "FECHADOCUMENTO")
	private Date fechaDocumento ;
	
	/**
	 * Especifica el ruc o documento para la factura o gasto
	 */
	@Column(name = "RUC")
	private String rucDocumento ;
	
	/**
	 * Especifica el nombre del cliente o del proveedor
	 */
	@Column(name = "NOMBRECLIENTEPROVEEDOR")
	private String nombreClienteProveedor ;
	
	/**
	 * Especifica la direccion para la factura
	 */
	@Column(name = "DIRECCION")
	private String direccion ;
	
	/**
	 * Especifica el telefono para la factura
	 */
	@Column(name = "TELEFONO")
	private String telefono ;
	
	/**
	 * Especifica si la factura o gasto esta pagado
	 */
	@Column(name = "PAGADO")
	private Boolean pagado ;
	
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
	
	@Transient
	private BigDecimal totalPagos ;
	
	/**
	 * Especifica el tipo de documento venta y compra
	 */
	@Column(name = "CODIGOTIPODOCUMENTO")
	private Integer codigoTipoDocumento ;
	
	/**
	 * Especifica el valor del tipo de documento venta o compra
	 */
	@Column(name = "CODIGOVALORTIPODOCUMENTO")
	private String codigoValorTipoDocumento ;
	
	/**
	 * Especifica el tipo del estado de despacho de la factura
	 */
	@Column(name = "CODIGOTIPOESTADO")
	private Integer codigoTipoEstado ;
	
	/**
	 * Especifica el valor del estado de despacho de la factura
	 */
	@Column(name = "CODIGOVALORESTADO")
	private String codigoValorEstado ;
	
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

	@Transient
	private Boolean seleccionada;
	
	/**
	 * Referencia a detalle de la factura
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "facturaCabeceraDTO")
	@CollectionTypeInfo(name = CollectionType.LIST_COLLECTION_TYPE)
	private Collection<FacturaDetalleDTO> facturaDetalleDTOCols;
	
	/**
	 * Referencia a detalle de pagos
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "facturaCabeceraDTO")
	@CollectionTypeInfo(name = CollectionType.LIST_COLLECTION_TYPE)
	private Collection<PagosFacturaDTO> pagosFacturaDTOCols;
	
	/**
	 * Referencia CatalogoValorDTO tipo de documento
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOVALORTIPODOCUMENTO", referencedColumnName = "CODIGOCATALOGOVALOR", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOTIPODOCUMENTO", referencedColumnName = "CODIGOCATALOGOTIPO", insertable = false, updatable = false)
	})
	private CatalogoValorDTO tipoDocumentoCatalogoValorDTO;
	
	/**
	 * Referencia al entidad Pedido
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOCOMPANIA", referencedColumnName = "CODIGOCOMPANIA", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOPEDIDO", referencedColumnName = "CODIGOPEDIDO", insertable = false, updatable = false)
	})
	private PedidoDTO pedidoDTO;
	
	/**
	 * Referencia al entidad Vendedor
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOCOMPANIA", referencedColumnName = "CODIGOCOMPANIA", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOVENDEDOR", referencedColumnName = "CODIGOVENDEDOR", insertable = false, updatable = false)
	})
	private VendedorDTO vendedorDTO;
	
	public FacturaCabeceraID getId() {
		return id;
	}

	public void setId(FacturaCabeceraID id) {
		this.id = id;
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

	public String getRucDocumento() {
		return rucDocumento;
	}

	public void setRucDocumento(String rucDocumento) {
		this.rucDocumento = rucDocumento;
	}

	public String getNombreClienteProveedor() {
		return nombreClienteProveedor;
	}

	public void setNombreClienteProveedor(String nombreClienteProveedor) {
		this.nombreClienteProveedor = nombreClienteProveedor;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Boolean getPagado() {
		return pagado;
	}

	public void setPagado(Boolean pagado) {
		this.pagado = pagado;
	}

	public Integer getCodigoTipoDocumento() {
		return codigoTipoDocumento;
	}

	public void setCodigoTipoDocumento(Integer codigoTipoDocumento) {
		this.codigoTipoDocumento = codigoTipoDocumento;
	}

	public String getCodigoValorTipoDocumento() {
		return codigoValorTipoDocumento;
	}

	public void setCodigoValorTipoDocumento(String codigoValorTipoDocumento) {
		this.codigoValorTipoDocumento = codigoValorTipoDocumento;
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

	public Collection<FacturaDetalleDTO> getFacturaDetalleDTOCols() {
		return facturaDetalleDTOCols;
	}

	public void setFacturaDetalleDTOCols(Collection<FacturaDetalleDTO> facturaDetalleDTOCols) {
		this.facturaDetalleDTOCols = facturaDetalleDTOCols;
	}

	public CatalogoValorDTO getTipoDocumentoCatalogoValorDTO() {
		return tipoDocumentoCatalogoValorDTO;
	}

	public void setTipoDocumentoCatalogoValorDTO(CatalogoValorDTO tipoDocumentoCatalogoValorDTO) {
		this.tipoDocumentoCatalogoValorDTO = tipoDocumentoCatalogoValorDTO;
	}

	public BigDecimal getTotalCuenta() {
		return totalCuenta;
	}

	public void setTotalCuenta(BigDecimal totalCuenta) {
		this.totalCuenta = totalCuenta;
	}

	public String getCodigoReferenciaFactura() {
		return codigoReferenciaFactura;
	}

	public void setCodigoReferenciaFactura(String codigoReferenciaFactura) {
		this.codigoReferenciaFactura = codigoReferenciaFactura;
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

	public Long getCodigoPedido() {
		return codigoPedido;
	}

	public void setCodigoPedido(Long codigoPedido) {
		this.codigoPedido = codigoPedido;
	}

	public PedidoDTO getPedidoDTO() {
		return pedidoDTO;
	}

	public void setPedidoDTO(PedidoDTO pedidoDTO) {
		this.pedidoDTO = pedidoDTO;
	}

	public Collection<PagosFacturaDTO> getPagosFacturaDTOCols() {
		return pagosFacturaDTOCols;
	}

	public void setPagosFacturaDTOCols(Collection<PagosFacturaDTO> pagosFacturaDTOCols) {
		this.pagosFacturaDTOCols = pagosFacturaDTOCols;
	}

	public BigDecimal getDescuento() {
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
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

	public Long getCodigoVendedor() {
		return codigoVendedor;
	}

	public void setCodigoVendedor(Long codigoVendedor) {
		this.codigoVendedor = codigoVendedor;
	}

	public VendedorDTO getVendedorDTO() {
		return vendedorDTO;
	}

	public void setVendedorDTO(VendedorDTO vendedorDTO) {
		this.vendedorDTO = vendedorDTO;
	}

	public BigDecimal getTotalPagos() {
		return totalPagos;
	}

	public void setTotalPagos(BigDecimal totalPagos) {
		this.totalPagos = totalPagos;
	}

	public String getTipoRuc() {
		return tipoRuc;
	}

	public void setTipoRuc(String tipoRuc) {
		this.tipoRuc = tipoRuc;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public Integer getCodigoTipoEstado() {
		return codigoTipoEstado;
	}

	public void setCodigoTipoEstado(Integer codigoTipoEstado) {
		this.codigoTipoEstado = codigoTipoEstado;
	}

	public String getCodigoValorEstado() {
		return codigoValorEstado;
	}

	public void setCodigoValorEstado(String codigoValorEstado) {
		this.codigoValorEstado = codigoValorEstado;
	}

	public Boolean getSeleccionada() {
		return seleccionada;
	}

	public void setSeleccionada(Boolean seleccionada) {
		this.seleccionada = seleccionada;
	}
}
