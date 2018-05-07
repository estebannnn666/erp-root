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
	 * Especifica el numero de factura o numero de documento de debito
	 */
	@Column(name = "NUMERODOCUMENTO")
	private String numeroDocumento ;
	
	/**
	 * Especifica la fecha de factura o documento
	 */
	@Column(name = "FECHADOCUMENTO")
	private Date fechaDocumento ;
	
	/**
	 * Especifica la fecha de entrega
	 */
	@Column(name = "FECHAENTREGA")
	private Date fechaEntrega ;
	
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
	 * Especifica el total de la compra
	 */
	@Column(name = "TOTALCUENTA")
	private BigDecimal totalCuenta ;
	
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
	 * Referencia a detalle de la factura
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "facturaCabeceraDTO")
	@CollectionTypeInfo(name = CollectionType.LIST_COLLECTION_TYPE)
	private Collection<FacturaDetalleDTO> facturaDetalleDTOCols;
	
	/**
	 * Referencia CatalogoValorDTO tipo de documento
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOVALORTIPODOCUMENTO", referencedColumnName = "CODIGOCATALOGOVALOR", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOTIPODOCUMENTO", referencedColumnName = "CODIGOCATALOGOTIPO", insertable = false, updatable = false)
	})
	private CatalogoValorDTO tipoDocumentoCatalogoValorDTO;
	
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

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
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
}
