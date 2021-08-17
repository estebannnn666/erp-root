package ec.com.erp.cliente.mdl.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author Esteban Gudino
 * 2020-09-13
 */
public class ReporteVentasFacturasVO implements Serializable{
	
	private static final long serialVersionUID = 7863262235394607247L;
	
	private String nombreVendedor ;
	
	private String numeroDocumento ;
	
	private String rucDocumentoCliente ;
	
	private String razonSocialCliente ;
	
	private String tipoCliente;
	
	private Boolean pagada;
	
	private Date fechaVenta;
	
	private BigDecimal subTotal ;
	
	private BigDecimal tarifaCero ;
	
	private BigDecimal tarifaImpuesto ;
	
	private BigDecimal totalIva ;
	
	private BigDecimal totalVenta ;
	
	private BigDecimal totalDescuento ;
	
	public String getNombreVendedor() {
		return nombreVendedor;
	}

	public void setNombreVendedor(String nombreVendedor) {
		this.nombreVendedor = nombreVendedor;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getRucDocumentoCliente() {
		return rucDocumentoCliente;
	}

	public void setRucDocumentoCliente(String rucDocumentoCliente) {
		this.rucDocumentoCliente = rucDocumentoCliente;
	}

	public String getRazonSocialCliente() {
		return razonSocialCliente;
	}

	public void setRazonSocialCliente(String razonSocialCliente) {
		this.razonSocialCliente = razonSocialCliente;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public Boolean getPagada() {
		return pagada;
	}

	public void setPagada(Boolean pagada) {
		this.pagada = pagada;
	}

	public Date getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getTarifaCero() {
		return tarifaCero;
	}

	public void setTarifaCero(BigDecimal tarifaCero) {
		this.tarifaCero = tarifaCero;
	}

	public BigDecimal getTarifaImpuesto() {
		return tarifaImpuesto;
	}

	public void setTarifaImpuesto(BigDecimal tarifaImpuesto) {
		this.tarifaImpuesto = tarifaImpuesto;
	}

	public BigDecimal getTotalIva() {
		return totalIva;
	}

	public void setTotalIva(BigDecimal totalIva) {
		this.totalIva = totalIva;
	}

	public BigDecimal getTotalVenta() {
		return totalVenta;
	}

	public void setTotalVenta(BigDecimal totalVenta) {
		this.totalVenta = totalVenta;
	}

	public BigDecimal getTotalDescuento() {
		return totalDescuento;
	}

	public void setTotalDescuento(BigDecimal totalDescuento) {
		this.totalDescuento = totalDescuento;
	}
}
