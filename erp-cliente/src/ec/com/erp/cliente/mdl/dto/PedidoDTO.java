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

import ec.com.erp.cliente.mdl.dto.id.PedidoID;
import ec.com.erp.utilitario.commons.constantes.CollectionType;

/**
 * 
 * @author Esteban Gudino
 * 2017-07-13
 */
@Entity
@Table(name="SCVNTPEDIDO")
public class PedidoDTO implements Serializable{
	private static final long serialVersionUID = 7863262235394607247L;

	@EmbeddedId
	private PedidoID id = new PedidoID();
	
	/**
	 * Especifica el codigo del cliente
	 */
	@Column(name = "CODIGOCLIENTE")
	private Long codigoCliente ;
	
	/**
	 * Especifica el numero del pedido
	 */
	@Column(name = "NUMEROPEDIDO")
	private String numeroPedido ;
	
	/**
	 * Especifica la fecha del pedido
	 */
	@Column(name = "FECHAPEDIDO")
	private Date fechaPedido ;
	
	/**
	 * Especifica la fecha de entrega
	 */
	@Column(name = "FECHAENTREGA")
	private Date fechaEntrega ;
	
	/**
	 * Especifica el total de la compra
	 */
	@Column(name = "TOTALCOMPRA")
	private BigDecimal totalCompra ;
	
	/**
	 * Especifica el total de la compra
	 */
	@Column(name = "FACTURACREADA")
	private Boolean facturaCreada ;
	
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
	 * Referencia al entidad Cliente
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOCOMPANIA", referencedColumnName = "CODIGOCOMPANIA", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOCLIENTE", referencedColumnName = "CODIGOCLIENTE", insertable = false, updatable = false)
	})
	private ClienteDTO clienteDTO;
	
	/**
	 * Referencia a detalle del pedido
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pedidoDTO")
	@CollectionTypeInfo(name = CollectionType.LIST_COLLECTION_TYPE)
	private Collection<DetallePedidoDTO> detallePedidoDTOCols;
	
	/**
	 * Referencia a Estado del pedido
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pedidoDTO")
	@CollectionTypeInfo(name = CollectionType.LIST_COLLECTION_TYPE)
	private Collection<EstadoPedidoDTO> estadoPedidoDTOCols;

	@Transient
	private EstadoPedidoDTO estadoPedidoDTO;
	
	public PedidoID getId() {
		return id;
	}

	public void setId(PedidoID id) {
		this.id = id;
	}

	public Long getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Long codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public BigDecimal getTotalCompra() {
		return totalCompra;
	}

	public void setTotalCompra(BigDecimal totalCompra) {
		this.totalCompra = totalCompra;
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

	public ClienteDTO getClienteDTO() {
		return clienteDTO;
	}

	public void setClienteDTO(ClienteDTO clienteDTO) {
		this.clienteDTO = clienteDTO;
	}

	public Collection<DetallePedidoDTO> getDetallePedidoDTOCols() {
		return detallePedidoDTOCols;
	}

	public void setDetallePedidoDTOCols(Collection<DetallePedidoDTO> detallePedidoDTOCols) {
		this.detallePedidoDTOCols = detallePedidoDTOCols;
	}

	public Collection<EstadoPedidoDTO> getEstadoPedidoDTOCols() {
		return estadoPedidoDTOCols;
	}

	public void setEstadoPedidoDTOCols(Collection<EstadoPedidoDTO> estadoPedidoDTOCols) {
		this.estadoPedidoDTOCols = estadoPedidoDTOCols;
	}

	public EstadoPedidoDTO getEstadoPedidoDTO() {
		return estadoPedidoDTO;
	}

	public void setEstadoPedidoDTO(EstadoPedidoDTO estadoPedidoDTO) {
		this.estadoPedidoDTO = estadoPedidoDTO;
	}

	public Boolean getFacturaCreada() {
		return facturaCreada;
	}

	public void setFacturaCreada(Boolean facturaCreada) {
		this.facturaCreada = facturaCreada;
	}

	public String getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
}
