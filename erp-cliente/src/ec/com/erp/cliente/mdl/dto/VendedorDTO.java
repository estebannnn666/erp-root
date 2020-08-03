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

import ec.com.erp.cliente.mdl.dto.id.VendedorID;
import ec.com.erp.utilitario.commons.constantes.CollectionType;

/**
 * 
 * @author Esteban Gudino
 * 2018-04-19
 */
@Entity
@Table(name="SCVNTVENDEDOR")
public class VendedorDTO implements Serializable{
	private static final long serialVersionUID = 7863262235394607247L;

	@EmbeddedId
	private VendedorID id = new VendedorID();
	
	/**
	 * Especifica el codigo de persona relacionada
	 */
	@Column(name = "CODIGOPERSONA")
	private Long codigoPersona ;
	
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
		@JoinColumn(name = "CODIGOPERSONA", referencedColumnName = "CODIGOPERSONA", insertable = false, updatable = false)
	})
	private PersonaDTO personaDTO;
	
	/**
	 * Referencia a detalle de pagos
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "vendedorDTO")
	@CollectionTypeInfo(name = CollectionType.LIST_COLLECTION_TYPE)
	private Collection<FacturaCabeceraDTO> facturaCabeceraDTOCols;
	
	public VendedorID getId() {
		return id;
	}

	public void setId(VendedorID id) {
		this.id = id;
	}

	public Long getCodigoPersona() {
		return codigoPersona;
	}

	public void setCodigoPersona(Long codigoPersona) {
		this.codigoPersona = codigoPersona;
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

	public PersonaDTO getPersonaDTO() {
		return personaDTO;
	}

	public void setPersonaDTO(PersonaDTO personaDTO) {
		this.personaDTO = personaDTO;
	}

	public Collection<FacturaCabeceraDTO> getFacturaCabeceraDTOCols() {
		return facturaCabeceraDTOCols;
	}

	public void setFacturaCabeceraDTOCols(Collection<FacturaCabeceraDTO> facturaCabeceraDTOCols) {
		this.facturaCabeceraDTOCols = facturaCabeceraDTOCols;
	}
}
