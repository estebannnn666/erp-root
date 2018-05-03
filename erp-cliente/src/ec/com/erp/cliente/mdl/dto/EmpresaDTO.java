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
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionTypeInfo;

import ec.com.erp.cliente.mdl.dto.id.EmpresaID;
import ec.com.erp.utilitario.commons.constantes.CollectionType;

/**
 * 
 * @author Esteban Gudino
 * 2017-07-13
 */
@Entity
@Table(name="SCVNTEMPRESA")
public class EmpresaDTO implements Serializable{
	private static final long serialVersionUID = 7863262235394607247L;

	@EmbeddedId
	private EmpresaID id = new EmpresaID();
	
	/**
	 * Especifica el ruc de la empresa
	 */
	@Column(name = "NUMERORUC", nullable = false)
	private String numeroRuc ;
	
	/**
	 * Especifica la razon social de la empresa
	 */
	@Column(name = "RAZONSOCIAL")
	private String razonSocial ;
	
	/**
	 * Especifica la descripcion de la emprea
	 */
	@Column(name = "DESCRIPCIONEMPRESA")
	private String descripcionEmpresa ;
	
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
	 * Referencia a Estado de negociacion
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresaDTO")
	@CollectionTypeInfo(name = CollectionType.LIST_COLLECTION_TYPE)
	private Collection<ContactoDTO> contactoDTOCols;
	
	@Transient
	private ContactoDTO contactoEmpresaDTO;
	
	public EmpresaID getId() {
		return id;
	}

	public void setId(EmpresaID id) {
		this.id = id;
	}

	public String getNumeroRuc() {
		return numeroRuc;
	}

	public void setNumeroRuc(String numeroRuc) {
		this.numeroRuc = numeroRuc;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getDescripcionEmpresa() {
		return descripcionEmpresa;
	}

	public void setDescripcionEmpresa(String descripcionEmpresa) {
		this.descripcionEmpresa = descripcionEmpresa;
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

	public Collection<ContactoDTO> getContactoDTOCols() {
		return contactoDTOCols;
	}

	public void setContactoDTOCols(Collection<ContactoDTO> contactoDTOCols) {
		this.contactoDTOCols = contactoDTOCols;
	}

	public ContactoDTO getContactoEmpresaDTO() {
		return contactoEmpresaDTO;
	}

	public void setContactoEmpresaDTO(ContactoDTO contactoEmpresaDTO) {
		this.contactoEmpresaDTO = contactoEmpresaDTO;
	}
	
}
