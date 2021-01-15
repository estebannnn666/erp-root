package ec.com.erp.cliente.mdl.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author Esteban Gudino
 * 2020-09-13
 */
public class ReporteVentasVO implements Serializable{
	
	private static final long serialVersionUID = 7863262235394607247L;
	
	private String nombreCompleto ;
	
	private String nombreArticulo ;
	
	private Date fechaVenta;
	
	private String codigoValorUnidadManejo ;
	
	private Integer valorUnidadManejo ;
	
	private BigDecimal precioMayorista;
	
	private BigDecimal precioMinorista;

	private BigDecimal porcentajeComision;
	
	private BigDecimal porcentajeComisionMayor;
	
	private String tipoCliente;
	
	private Long cantidadVendida;
	
	private BigDecimal valorVendido;
	
	private BigDecimal valoComisionMenor;
	
	private BigDecimal valoComisionMayor;
	
	private BigDecimal valoComisionTotal;

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getNombreArticulo() {
		return nombreArticulo;
	}

	public void setNombreArticulo(String nombreArticulo) {
		this.nombreArticulo = nombreArticulo;
	}

	public String getCodigoValorUnidadManejo() {
		return codigoValorUnidadManejo;
	}

	public void setCodigoValorUnidadManejo(String codigoValorUnidadManejo) {
		this.codigoValorUnidadManejo = codigoValorUnidadManejo;
	}

	public Integer getValorUnidadManejo() {
		return valorUnidadManejo;
	}

	public void setValorUnidadManejo(Integer valorUnidadManejo) {
		this.valorUnidadManejo = valorUnidadManejo;
	}

	public BigDecimal getPorcentajeComision() {
		return porcentajeComision;
	}

	public void setPorcentajeComision(BigDecimal porcentajeComision) {
		this.porcentajeComision = porcentajeComision;
	}

	public Long getCantidadVendida() {
		return cantidadVendida;
	}

	public void setCantidadVendida(Long cantidadVendida) {
		this.cantidadVendida = cantidadVendida;
	}

	public BigDecimal getValorVendido() {
		return valorVendido;
	}

	public void setValorVendido(BigDecimal valorVendido) {
		this.valorVendido = valorVendido;
	}

	public BigDecimal getValoComisionMenor() {
		return valoComisionMenor;
	}

	public void setValoComisionMenor(BigDecimal valoComisionMenor) {
		this.valoComisionMenor = valoComisionMenor;
	}

	public BigDecimal getValoComisionMayor() {
		return valoComisionMayor;
	}

	public void setValoComisionMayor(BigDecimal valoComisionMayor) {
		this.valoComisionMayor = valoComisionMayor;
	}

	public BigDecimal getValoComisionTotal() {
		return valoComisionTotal;
	}

	public void setValoComisionTotal(BigDecimal valoComisionTotal) {
		this.valoComisionTotal = valoComisionTotal;
	}

	public Date getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public BigDecimal getPrecioMayorista() {
		return precioMayorista;
	}

	public void setPrecioMayorista(BigDecimal precioMayorista) {
		this.precioMayorista = precioMayorista;
	}

	public BigDecimal getPrecioMinorista() {
		return precioMinorista;
	}

	public void setPrecioMinorista(BigDecimal precioMinorista) {
		this.precioMinorista = precioMinorista;
	}

	public BigDecimal getPorcentajeComisionMayor() {
		return porcentajeComisionMayor;
	}

	public void setPorcentajeComisionMayor(BigDecimal porcentajeComisionMayor) {
		this.porcentajeComisionMayor = porcentajeComisionMayor;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}	
}
