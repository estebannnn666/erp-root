package ec.com.erp.cliente.mdl.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ec.com.erp.cliente.mdl.dto.id.NotaCreditoDocumentoID;

/**
 * 
 * @author Esteban Gudino
 * 2020-06-24
 */
@Entity
@Table(name="SCVNTNOTACREDITODOCUMENTO")
public class NotaCreditoDocumentoDTO implements Serializable{
	private static final long serialVersionUID = 7863262235394607247L;

	@EmbeddedId
	private NotaCreditoDocumentoID id = new NotaCreditoDocumentoID();
	
	/**
	 * Especifica el codigo de nota de credito relacionada
	 */
	@Column(name="CODIGONOTACREDITO")
	private Long codigoNotaCredito;
	
	/**
	 * Especifica en bytes del documento de la factura
	 */
	@Column(name = "XMLNOTACREDITO")
	private byte[] xmlNotaCredito ;
	
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
		@JoinColumn(name = "CODIGONOTACREDITO", referencedColumnName = "CODIGONOTACREDITO", insertable = false, updatable = false)
	})
	private NotaCreditoDTO notaCreditoDTO;
	
	public NotaCreditoDocumentoID getId() {
		return id;
	}

	public void setId(NotaCreditoDocumentoID id) {
		this.id = id;
	}

	public Long getCodigoNotaCredito() {
		return codigoNotaCredito;
	}

	public void setCodigoNotaCredito(Long codigoNotaCredito) {
		this.codigoNotaCredito = codigoNotaCredito;
	}

	public byte[] getXmlNotaCredito() {
		return xmlNotaCredito;
	}

	public void setXmlNotaCredito(byte[] xmlNotaCredito) {
		this.xmlNotaCredito = xmlNotaCredito;
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
}
