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

import ec.com.erp.cliente.mdl.dto.id.ClienteID;

/**
 * 
 * @author Esteban Gudino
 * 2017-07-13
 */
@Entity
@Table(name="SCVNTCLIENTE")
public class ClienteDTO implements Serializable{
	private static final long serialVersionUID = 7863262235394607247L;

	@EmbeddedId
	private ClienteID id = new ClienteID();
	
	/**
	 * Especifica el codigo de persona relacionada
	 */
	@Column(name = "CODIGOPERSONA")
	private Long codigoPersona ;
	
	/**
	 * Especifica el codigo de empresa relacionada
	 */
	@Column(name = "CODIGOEMPRESA")
	private Long codigoEmpresa ;

	/**
	 * Especifica el codigo de vendedor
	 */
	@Column(name="CODIGOVENDEDOR")
	private Long codigoVendedor;
	
	/**
	 * Especifica el usuario atado al cliente
	 */
	@Column(name = "USERID")
	private String userId ;
	
	/**
	 * Especifica el codigo valor del tipo de contacto
	 */
	@Column(name = "CODIGOVALORTIPOCLIENTE")
	private String codigoValorTipoCliente ;
	
	/**
	 * Especifica el codigo tipo de contacto
	 */
	@Column(name = "CODIGOTIPOCLIENTE")
	private Integer codigoTipoCliente ;
	
	/**
	 * Especifica el codigo valor del tipo de cliente
	 */
	@Column(name = "CODIGOVALORTIPOCOMPRA")
	private String codigoValorTipoCompra ;
	
	/**
	 * Especifica el codigo tipo de cliente
	 */
	@Column(name = "CODIGOTIPOCOMPRA")
	private Integer codigoTipoCompra ;
	
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
		@JoinColumn(name = "CODIGOVALORTIPOCLIENTE", referencedColumnName = "CODIGOCATALOGOVALOR", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOTIPOCLIENTE", referencedColumnName = "CODIGOCATALOGOTIPO", insertable = false, updatable = false)
	})
	private CatalogoValorDTO tipoClienteCatalogoValorDTO;
	
	/**
	 * Referencia CatalogoValorDTO tipo de contacto
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOVALORTIPOCOMPRA", referencedColumnName = "CODIGOCATALOGOVALOR", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOTIPOCOMPRA", referencedColumnName = "CODIGOCATALOGOTIPO", insertable = false, updatable = false)
	})
	private CatalogoValorDTO tipoCompraCatalogoValorDTO;
	
	/**
	 * Referencia CatalogoValorDTO tipo de contacto
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOCOMPANIA", referencedColumnName = "CODIGOCOMPANIA", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOPERSONA", referencedColumnName = "CODIGOPERSONA", insertable = false, updatable = false)
	})
	private PersonaDTO personaDTO;
	
	/**
	 * Referencia CatalogoValorDTO tipo de contacto
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOCOMPANIA", referencedColumnName = "CODIGOCOMPANIA", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOEMPRESA", referencedColumnName = "CODIGOEMPRESA", insertable = false, updatable = false)
	})
	private EmpresaDTO empresaDTO;
	
	/**
	 * Referencia al entidad Vendedor
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOCOMPANIA", referencedColumnName = "CODIGOCOMPANIA", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOVENDEDOR", referencedColumnName = "CODIGOVENDEDOR", insertable = false, updatable = false)
	})
	private VendedorDTO vendedorDTO;

	/**
	 * Referencia CatalogoValorDTO tipo de contacto
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "USERID", referencedColumnName = "USERID", insertable = false, updatable = false)
	})
	private UsuariosDTO usuariosDTO;

	public ClienteID getId() {
		return id;
	}

	public void setId(ClienteID id) {
		this.id = id;
	}

	public Long getCodigoPersona() {
		return codigoPersona;
	}

	public void setCodigoPersona(Long codigoPersona) {
		this.codigoPersona = codigoPersona;
	}

	public Long getCodigoEmpresa() {
		return codigoEmpresa;
	}

	public void setCodigoEmpresa(Long codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCodigoValorTipoCliente() {
		return codigoValorTipoCliente;
	}

	public void setCodigoValorTipoCliente(String codigoValorTipoCliente) {
		this.codigoValorTipoCliente = codigoValorTipoCliente;
	}

	public Integer getCodigoTipoCliente() {
		return codigoTipoCliente;
	}

	public void setCodigoTipoCliente(Integer codigoTipoCliente) {
		this.codigoTipoCliente = codigoTipoCliente;
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

	public CatalogoValorDTO getTipoClienteCatalogoValorDTO() {
		return tipoClienteCatalogoValorDTO;
	}

	public void setTipoClienteCatalogoValorDTO(CatalogoValorDTO tipoClienteCatalogoValorDTO) {
		this.tipoClienteCatalogoValorDTO = tipoClienteCatalogoValorDTO;
	}

	public PersonaDTO getPersonaDTO() {
		return personaDTO;
	}

	public void setPersonaDTO(PersonaDTO personaDTO) {
		this.personaDTO = personaDTO;
	}

	public EmpresaDTO getEmpresaDTO() {
		return empresaDTO;
	}

	public void setEmpresaDTO(EmpresaDTO empresaDTO) {
		this.empresaDTO = empresaDTO;
	}

	public UsuariosDTO getUsuariosDTO() {
		return usuariosDTO;
	}

	public void setUsuariosDTO(UsuariosDTO usuariosDTO) {
		this.usuariosDTO = usuariosDTO;
	}

	public String getCodigoValorTipoCompra() {
		return codigoValorTipoCompra;
	}

	public void setCodigoValorTipoCompra(String codigoValorTipoCompra) {
		this.codigoValorTipoCompra = codigoValorTipoCompra;
	}

	public Integer getCodigoTipoCompra() {
		return codigoTipoCompra;
	}

	public void setCodigoTipoCompra(Integer codigoTipoCompra) {
		this.codigoTipoCompra = codigoTipoCompra;
	}

	public CatalogoValorDTO getTipoCompraCatalogoValorDTO() {
		return tipoCompraCatalogoValorDTO;
	}

	public void setTipoCompraCatalogoValorDTO(CatalogoValorDTO tipoCompraCatalogoValorDTO) {
		this.tipoCompraCatalogoValorDTO = tipoCompraCatalogoValorDTO;
	}

	public Long getCodigoVendedor() {
		return codigoVendedor;
	}

	public void setCodigoVendedor(Long codigoVendedor) {
		this.codigoVendedor = codigoVendedor;
	}

	public VendedorDTO getVendedorDTO() {
		return vendedorDTO;
	}

	public void setVendedorDTO(VendedorDTO vendedorDTO) {
		this.vendedorDTO = vendedorDTO;
	}
}
