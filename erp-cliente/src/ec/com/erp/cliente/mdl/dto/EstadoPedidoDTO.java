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

import ec.com.erp.cliente.mdl.dto.id.EstadoPedidoID;

/**
 * 
 * @author Esteban Gudino
 * 2017-07-13
 */
@Entity
@Table(name="SCVNTESTADOPEDIDO")
public class EstadoPedidoDTO implements Serializable{
	private static final long serialVersionUID = 7863262235394607247L;

	@EmbeddedId
	private EstadoPedidoID id = new EstadoPedidoID();
	
	/**
	 * Especifica el codigo valor del estado del pedido
	 */
	@Column(name = "CODIGOVALORESTADOPEDIDO")
	private String codigoValorEstadoPedido ;
	
	/**
	 * Especifica el codigo tipo del estado del pedido
	 */
	@Column(name = "CODIGOTIPOESTADOPEDIDO")
	private Integer codigoTipoEstadoPedido ;
	
	/**
	 * Especifica la fecha de inicio
	 */
	@Column(name = "FECHAINICIO")
	private Date fechaInicio ;
	
	/**
	 * Especifica la fecha de fin
	 */
	@Column(name = "FECHAFIN")
	private Date fechaFin ;
	
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
	 * Referencia al entidad Pedido
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOCOMPANIA", referencedColumnName = "CODIGOCOMPANIA", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOPEDIDO", referencedColumnName = "CODIGOPEDIDO", insertable = false, updatable = false)
	})
	private PedidoDTO pedidoDTO;
	
	/**
	 * Referencia CatalogoValorDTO tipo de estado
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOVALORESTADOPEDIDO", referencedColumnName = "CODIGOCATALOGOVALOR", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOTIPOESTADOPEDIDO", referencedColumnName = "CODIGOCATALOGOTIPO", insertable = false, updatable = false)
	})
	private CatalogoValorDTO estadoCatalogoValorDTO;
	

	public EstadoPedidoID getId() {
		return id;
	}

	public void setId(EstadoPedidoID id) {
		this.id = id;
	}

	public String getCodigoValorEstadoPedido() {
		return codigoValorEstadoPedido;
	}

	public void setCodigoValorEstadoPedido(String codigoValorEstadoPedido) {
		this.codigoValorEstadoPedido = codigoValorEstadoPedido;
	}

	public Integer getCodigoTipoEstadoPedido() {
		return codigoTipoEstadoPedido;
	}

	public void setCodigoTipoEstadoPedido(Integer codigoTipoEstadoPedido) {
		this.codigoTipoEstadoPedido = codigoTipoEstadoPedido;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
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

	public PedidoDTO getPedidoDTO() {
		return pedidoDTO;
	}

	public void setPedidoDTO(PedidoDTO pedidoDTO) {
		this.pedidoDTO = pedidoDTO;
	}

	public CatalogoValorDTO getEstadoCatalogoValorDTO() {
		return estadoCatalogoValorDTO;
	}

	public void setEstadoCatalogoValorDTO(CatalogoValorDTO estadoCatalogoValorDTO) {
		this.estadoCatalogoValorDTO = estadoCatalogoValorDTO;
	}
	
}
