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

import ec.com.erp.cliente.mdl.dto.id.VehiculoChoferID;

/**
 * 
 * @author Esteban Gudino
 * 2018-04-19
 */
@Entity
@Table(name="SCVNTVEHICULOCHOFER")
public class VehiculoChoferDTO implements Serializable{
	private static final long serialVersionUID = 7863262235394607247L;

	@EmbeddedId
	private VehiculoChoferID id = new VehiculoChoferID();
	
	/**
	 * Especifica el codigo del vehiculo relacionado
	 */
	@Column(name = "CODIGOVEHICULO")
	private Long codigoVehiculo ; 
	
	/**
	 * Especifica el codigo del chofer relacionado
	 */
	@Column(name = "CODIGOCHOFER")
	private Long codigoChofer ; 
	
	/**
	 * Especifica el codigo valor del tipo de chofer
	 */
	@Column(name = "CODIGOVALORTIPOCHOFER")
	private String codigoValorTipoChofer ;
	
	/**
	 * Especifica el codigo tipo de chofer
	 */
	@Column(name = "CODIGOTIPOCHOFER")
	private Integer codigoTipoChofer;
	
	/**
	 * Especifica la descripcion u observacion del chofer asignado al vehiculo
	 */
	@Column(name = "DESCRIPCION")
	private String descripcion ;
	
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
	 * Referencia al entidad vehiculo
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOCOMPANIA", referencedColumnName = "CODIGOCOMPANIA", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOVEHICULO", referencedColumnName = "CODIGOVEHICULO", insertable = false, updatable = false)
	})
	private VehiculoDTO vehiculoDTO;
	
	/**
	 * Referencia al entidad chofer
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOCOMPANIA", referencedColumnName = "CODIGOCOMPANIA", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOCHOFER", referencedColumnName = "CODIGOCHOFER", insertable = false, updatable = false)
	})
	private ChoferDTO choferDTO;
	
	/**
	 * Referencia CatalogoValorDTO tipo de chofer(Principal o secundario)
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOVALORTIPOCHOFER", referencedColumnName = "CODIGOCATALOGOVALOR", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOTIPOCHOFER", referencedColumnName = "CODIGOCATALOGOTIPO", insertable = false, updatable = false)
	})
	private CatalogoValorDTO tipoChoferCatalogoValorDTO;

	public VehiculoChoferID getId() {
		return id;
	}

	public void setId(VehiculoChoferID id) {
		this.id = id;
	}

	public Long getCodigoVehiculo() {
		return codigoVehiculo;
	}

	public void setCodigoVehiculo(Long codigoVehiculo) {
		this.codigoVehiculo = codigoVehiculo;
	}

	public Long getCodigoChofer() {
		return codigoChofer;
	}

	public void setCodigoChofer(Long codigoChofer) {
		this.codigoChofer = codigoChofer;
	}

	public String getCodigoValorTipoChofer() {
		return codigoValorTipoChofer;
	}

	public void setCodigoValorTipoChofer(String codigoValorTipoChofer) {
		this.codigoValorTipoChofer = codigoValorTipoChofer;
	}

	public Integer getCodigoTipoChofer() {
		return codigoTipoChofer;
	}

	public void setCodigoTipoChofer(Integer codigoTipoChofer) {
		this.codigoTipoChofer = codigoTipoChofer;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public VehiculoDTO getVehiculoDTO() {
		return vehiculoDTO;
	}

	public void setVehiculoDTO(VehiculoDTO vehiculoDTO) {
		this.vehiculoDTO = vehiculoDTO;
	}

	public ChoferDTO getChoferDTO() {
		return choferDTO;
	}

	public void setChoferDTO(ChoferDTO choferDTO) {
		this.choferDTO = choferDTO;
	}

	public CatalogoValorDTO getTipoChoferCatalogoValorDTO() {
		return tipoChoferCatalogoValorDTO;
	}

	public void setTipoChoferCatalogoValorDTO(CatalogoValorDTO tipoChoferCatalogoValorDTO) {
		this.tipoChoferCatalogoValorDTO = tipoChoferCatalogoValorDTO;
	}
}
