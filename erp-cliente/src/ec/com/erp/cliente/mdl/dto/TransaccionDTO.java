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

import ec.com.erp.cliente.mdl.dto.id.TransaccionID;

/**
 * 
 * @author Esteban Gudino
 * 2020-06-24
 */
@Entity
@Table(name="SCVNTTTRANSACCION")
public class TransaccionDTO implements Serializable{
	private static final long serialVersionUID = 7863262235394607247L;

	@EmbeddedId
	private TransaccionID id = new TransaccionID();
	
	/**
	 * Especifica el numero de transaccion
	 */
	@Column(name = "NUMEROTRANSACCION")
	private String numeroTransaccion ;
	
	/**
	 * Especifica el valor en dolares de la transaccion
	 */
	@Column(name = "VALORTRANSACCION")
	private BigDecimal valorTransaccion ;
	
	/**
	 * Especifica el concepto de la transaccion
	 */
	@Column(name = "CONCEPTO")
	private String concepto ;
	
	/**
	 * Especifica el codigo valor del tipo de transaccion
	 */
	@Column(name = "CODIGOVALORTRANSACCION")
	private String codigoValorTransaccion ;
	
	/**
	 * Especifica el codigo tipo de transaccion
	 */
	@Column(name = "CODIGOTIPOTRANSACCION")
	private Integer codigoTipoTransaccion ;
	
	/**
	 * Especifica la fecha de transaccion
	 */
	@Column(name="FECHATRANSACCION")
	private Date fechaTransaccion;
	
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
		@JoinColumn(name = "CODIGOVALORTRANSACCION", referencedColumnName = "CODIGOCATALOGOVALOR", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOTIPOTRANSACCION", referencedColumnName = "CODIGOCATALOGOTIPO", insertable = false, updatable = false)
	})
	private CatalogoValorDTO tipoTransaccionCatalogoValorDTO;
	
	public TransaccionID getId() {
		return id;
	}

	public void setId(TransaccionID id) {
		this.id = id;
	}

	public String getNumeroTransaccion() {
		return numeroTransaccion;
	}

	public void setNumeroTransaccion(String numeroTransaccion) {
		this.numeroTransaccion = numeroTransaccion;
	}

	public BigDecimal getValorTransaccion() {
		return valorTransaccion;
	}

	public void setValorTransaccion(BigDecimal valorTransaccion) {
		this.valorTransaccion = valorTransaccion;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getCodigoValorTransaccion() {
		return codigoValorTransaccion;
	}

	public void setCodigoValorTransaccion(String codigoValorTransaccion) {
		this.codigoValorTransaccion = codigoValorTransaccion;
	}

	public Integer getCodigoTipoTransaccion() {
		return codigoTipoTransaccion;
	}

	public void setCodigoTipoTransaccion(Integer codigoTipoTransaccion) {
		this.codigoTipoTransaccion = codigoTipoTransaccion;
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

	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public CatalogoValorDTO getTipoTransaccionCatalogoValorDTO() {
		return tipoTransaccionCatalogoValorDTO;
	}

	public void setTipoTransaccionCatalogoValorDTO(CatalogoValorDTO tipoTransaccionCatalogoValorDTO) {
		this.tipoTransaccionCatalogoValorDTO = tipoTransaccionCatalogoValorDTO;
	}

}
