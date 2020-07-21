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

import ec.com.erp.cliente.mdl.dto.id.ArticuloUnidadManejoID;

/**
 * 
 * @author Esteban Gudino
 * 2017-06-06
 */
@Entity
@Table(name="SCVNTARTICULOUNIDADMANEJO")
public class ArticuloUnidadManejoDTO implements Serializable{
	private static final long serialVersionUID = 7863262235394607247L;

	@EmbeddedId
	private ArticuloUnidadManejoID id = new ArticuloUnidadManejoID();
	
	@Column(name="VALORUNIDADMANEJO")
	private Integer valorUnidadManejo;
	
	@Column(name="CODIGOVALORUNIDADMANEJO")
	private String codigoValorUnidadManejo;
	
	@Column(name="CODIGOTIPOUNIDADMANEJO")
	private Integer codigoTipoUnidadManejo;
	
	@Column(name="ESPORDEFECTO")
	private Boolean esPorDefecto;
		
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
	 * Referencia al entidad impuesto
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOCOMPANIA", referencedColumnName = "CODIGOCOMPANIA", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOIMPUESTO", referencedColumnName = "CODIGOIMPUESTO", insertable = false, updatable = false)
	})
	private ImpuestoDTO impuestoDTO;
	
	/**
	 * Referencia al entidad articulo
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOCOMPANIA", referencedColumnName = "CODIGOCOMPANIA", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOARTICULO", referencedColumnName = "CODIGOARTICULO", insertable = false, updatable = false)
	})
	private ArticuloDTO articuloDTO;
	
	public ArticuloUnidadManejoID getId() {
		return id;
	}

	public void setId(ArticuloUnidadManejoID id) {
		this.id = id;
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

	public ImpuestoDTO getImpuestoDTO() {
		return impuestoDTO;
	}

	public void setImpuestoDTO(ImpuestoDTO impuestoDTO) {
		this.impuestoDTO = impuestoDTO;
	}

	public ArticuloDTO getArticuloDTO() {
		return articuloDTO;
	}

	public void setArticuloDTO(ArticuloDTO articuloDTO) {
		this.articuloDTO = articuloDTO;
	}

	public Integer getValorUnidadManejo() {
		return valorUnidadManejo;
	}

	public void setValorUnidadManejo(Integer valorUnidadManejo) {
		this.valorUnidadManejo = valorUnidadManejo;
	}

	public String getCodigoValorUnidadManejo() {
		return codigoValorUnidadManejo;
	}

	public void setCodigoValorUnidadManejo(String codigoValorUnidadManejo) {
		this.codigoValorUnidadManejo = codigoValorUnidadManejo;
	}

	public Integer getCodigoTipoUnidadManejo() {
		return codigoTipoUnidadManejo;
	}

	public void setCodigoTipoUnidadManejo(Integer codigoTipoUnidadManejo) {
		this.codigoTipoUnidadManejo = codigoTipoUnidadManejo;
	}

	public Boolean getEsPorDefecto() {
		return esPorDefecto;
	}

	public void setEsPorDefecto(Boolean esPorDefecto) {
		this.esPorDefecto = esPorDefecto;
	}
}
