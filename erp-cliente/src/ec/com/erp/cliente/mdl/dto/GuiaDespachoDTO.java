package ec.com.erp.cliente.mdl.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionTypeInfo;

import ec.com.erp.cliente.mdl.dto.id.GuiaDespachoID;
import ec.com.erp.utilitario.commons.constantes.CollectionType;

/**
 * 
 * @author Esteban Gudino
 * 2018-04-19
 */
@Entity
@Table(name="SCVNTGUIADESPACHO")
public class GuiaDespachoDTO implements Serializable{
	private static final long serialVersionUID = 7863262235394607247L;

	@EmbeddedId
	private GuiaDespachoID id = new GuiaDespachoID();
	
	/**
	 * Especifica el numero de guia de despacho
	 */
	@Column(name = "NUMEROGUIADESPACHO")
	private String numeroGuiaDespacho ;
	
	/**
	 * Especifica la fecha de despacho
	 */
	@Column(name = "FECHADESPACHO")
	private Date fechaDespacho ;
	
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
	 * Especifica el ruc o ci del transportista
	 */
	@Column(name = "RUCTRANSPORTISTA")
	private String rucTransportista ;
	
	/**
	 * Especifica la razon social del transportista
	 */
	@Column(name = "NOMBRETRANSPORTISTA")
	private String nombreTransportista ;
	
	/**
	 * Especifica el documento del chofer principal
	 */
	@Column(name = "DOCUMENTOCHOFERA")
	private String documentoChoferA ;
	
	/**
	 * Especifica el nombre del chofer principal
	 */
	@Column(name = "NOMBRECHOFERA")
	private String nombreChoferA ;
	
	/**
	 * Especifica el documento del chofer secundario
	 */
	@Column(name = "DOCUMENTOCHOFERB")
	private String documentoChoferB ;
	
	/**
	 *Especifica el nombre del chofer secundario
	 */
	@Column(name = "NOMBRECHOFERB")
	private String nombreChoferB ;
	
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
	 * Referencia a la lista de pedidos despachados
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "guiaDespachoDTO")
	@CollectionTypeInfo(name = CollectionType.LIST_COLLECTION_TYPE)
	private Collection<GuiaDespachoPedidoDTO> guiaDespachoPedidoDTOCols;
	
	/**
	 * Referencia a la lista de pedidos extras o productos extras
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "guiaDespachoDTO")
	@CollectionTypeInfo(name = CollectionType.LIST_COLLECTION_TYPE)
	private Collection<GuiaDespachoExtrasDTO> guiaDespachoExtrasDTOCols;
	
	/**
	 * Referencia a la lista de detalle de pedidos
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "guiaDespachoDTO")
	@CollectionTypeInfo(name = CollectionType.LIST_COLLECTION_TYPE)
	private Collection<GuiaDespachoDetalleDTO> guiaDespachoDetalleDTOCols;

	public GuiaDespachoID getId() {
		return id;
	}

	public void setId(GuiaDespachoID id) {
		this.id = id;
	}

	public String getNumeroGuiaDespacho() {
		return numeroGuiaDespacho;
	}

	public void setNumeroGuiaDespacho(String numeroGuiaDespacho) {
		this.numeroGuiaDespacho = numeroGuiaDespacho;
	}

	public Date getFechaDespacho() {
		return fechaDespacho;
	}

	public void setFechaDespacho(Date fechaDespacho) {
		this.fechaDespacho = fechaDespacho;
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

	public String getRucTransportista() {
		return rucTransportista;
	}

	public void setRucTransportista(String rucTransportista) {
		this.rucTransportista = rucTransportista;
	}

	public String getNombreTransportista() {
		return nombreTransportista;
	}

	public void setNombreTransportista(String nombreTransportista) {
		this.nombreTransportista = nombreTransportista;
	}

	public String getDocumentoChoferA() {
		return documentoChoferA;
	}

	public void setDocumentoChoferA(String documentoChoferA) {
		this.documentoChoferA = documentoChoferA;
	}

	public String getNombreChoferA() {
		return nombreChoferA;
	}

	public void setNombreChoferA(String nombreChoferA) {
		this.nombreChoferA = nombreChoferA;
	}

	public String getDocumentoChoferB() {
		return documentoChoferB;
	}

	public void setDocumentoChoferB(String documentoChoferB) {
		this.documentoChoferB = documentoChoferB;
	}

	public String getNombreChoferB() {
		return nombreChoferB;
	}

	public void setNombreChoferB(String nombreChoferB) {
		this.nombreChoferB = nombreChoferB;
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

	public Collection<GuiaDespachoPedidoDTO> getGuiaDespachoPedidoDTOCols() {
		return guiaDespachoPedidoDTOCols;
	}

	public void setGuiaDespachoPedidoDTOCols(Collection<GuiaDespachoPedidoDTO> guiaDespachoPedidoDTOCols) {
		this.guiaDespachoPedidoDTOCols = guiaDespachoPedidoDTOCols;
	}

	public Collection<GuiaDespachoExtrasDTO> getGuiaDespachoExtrasDTOCols() {
		return guiaDespachoExtrasDTOCols;
	}

	public void setGuiaDespachoExtrasDTOCols(Collection<GuiaDespachoExtrasDTO> guiaDespachoExtrasDTOCols) {
		this.guiaDespachoExtrasDTOCols = guiaDespachoExtrasDTOCols;
	}

	public Collection<GuiaDespachoDetalleDTO> getGuiaDespachoDetalleDTOCols() {
		return guiaDespachoDetalleDTOCols;
	}

	public void setGuiaDespachoDetalleDTOCols(Collection<GuiaDespachoDetalleDTO> guiaDespachoDetalleDTOCols) {
		this.guiaDespachoDetalleDTOCols = guiaDespachoDetalleDTOCols;
	}
}
