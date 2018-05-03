package ec.com.erp.cliente.mdl.dto;

import java.io.Serializable;
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

import ec.com.erp.cliente.mdl.dto.id.VehiculoID;
import ec.com.erp.utilitario.commons.constantes.CollectionType;

/**
 * 
 * @author Esteban Gudino
 * 2018-04-19
 */
@Entity
@Table(name="SCVNTVEHICULO")
public class VehiculoDTO implements Serializable{
	private static final long serialVersionUID = 7863262235394607247L;

	@EmbeddedId
	private VehiculoID id = new VehiculoID();
	
	/**
	 * Especifica el codigo de transportista relacionado
	 */
	@Column(name = "CODIGOTRANSPORTISTA")
	private Long codigoTransportista ;
	
	/**
	 * Especifica la placa del vehiculo
	 */
	@Column(name = "PLACA")
	private String placa ;
	
	/**
	 * Especifica la marca del vehiculo
	 */
	@Column(name = "MARCA")
	private String marca ;
	
	/**
	 * Especifica el color del vehiculo
	 */
	@Column(name = "COLOR")
	private String color ;
	
	/**
	 * Especifica el modelo anio fabricacion
	 */
	@Column(name = "MODELO")
	private String  modelo ;
	
	/**
	 * Especifica el codigo valor del tipo de vehiculo
	 */
	@Column(name = "CODIGOVALORTIPOVEHICULO")
	private String codigoValorTipoVehiculo ;
	
	/**
	 * Especifica el codigo tipo de vehiculo
	 */
	@Column(name = "CODIGOTIPOVEHICULO")
	private Integer codigoTipoVehiculo ;
	
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
		@JoinColumn(name = "CODIGOVALORTIPOVEHICULO", referencedColumnName = "CODIGOCATALOGOVALOR", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOTIPOVEHICULO", referencedColumnName = "CODIGOCATALOGOTIPO", insertable = false, updatable = false)
	})
	private CatalogoValorDTO tipoVehiculoCatalogoValorDTO;
	
	/**
	 * Referencia TransportistaDTO del vehiculo
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOCOMPANIA", referencedColumnName = "CODIGOCOMPANIA", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOTRANSPORTISTA", referencedColumnName = "CODIGOTRANSPORTISTA", insertable = false, updatable = false)
	})
	private TransportistaDTO transportistaDTO;

	/**
	 * Referencia a detalle o lista de choferes asugnados al vehiculo
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "vehiculoDTO")
	@CollectionTypeInfo(name = CollectionType.LIST_COLLECTION_TYPE)
	private Collection<VehiculoChoferDTO> vehiculoChoferDTOCols;
	
	public VehiculoID getId() {
		return id;
	}

	public void setId(VehiculoID id) {
		this.id = id;
	}

	public Long getCodigoTransportista() {
		return codigoTransportista;
	}

	public void setCodigoTransportista(Long codigoTransportista) {
		this.codigoTransportista = codigoTransportista;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getCodigoValorTipoVehiculo() {
		return codigoValorTipoVehiculo;
	}

	public void setCodigoValorTipoVehiculo(String codigoValorTipoVehiculo) {
		this.codigoValorTipoVehiculo = codigoValorTipoVehiculo;
	}

	public Integer getCodigoTipoVehiculo() {
		return codigoTipoVehiculo;
	}

	public void setCodigoTipoVehiculo(Integer codigoTipoVehiculo) {
		this.codigoTipoVehiculo = codigoTipoVehiculo;
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

	public CatalogoValorDTO getTipoVehiculoCatalogoValorDTO() {
		return tipoVehiculoCatalogoValorDTO;
	}

	public void setTipoVehiculoCatalogoValorDTO(CatalogoValorDTO tipoVehiculoCatalogoValorDTO) {
		this.tipoVehiculoCatalogoValorDTO = tipoVehiculoCatalogoValorDTO;
	}

	public TransportistaDTO getTransportistaDTO() {
		return transportistaDTO;
	}

	public void setTransportistaDTO(TransportistaDTO transportistaDTO) {
		this.transportistaDTO = transportistaDTO;
	}

	public Collection<VehiculoChoferDTO> getVehiculoChoferDTOCols() {
		return vehiculoChoferDTOCols;
	}

	public void setVehiculoChoferDTOCols(Collection<VehiculoChoferDTO> vehiculoChoferDTOCols) {
		this.vehiculoChoferDTOCols = vehiculoChoferDTOCols;
	}
}
