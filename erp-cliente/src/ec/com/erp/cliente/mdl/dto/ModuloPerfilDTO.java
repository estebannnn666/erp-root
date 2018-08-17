package ec.com.erp.cliente.mdl.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import ec.com.erp.cliente.mdl.dto.id.ModuloPerfilID;

/**
 * 
 * @author Esteban Gudino
 * 2017-06-06
 */
@Entity
@Table(name="SCVNTMODULOPERFIL")
public class ModuloPerfilDTO implements Serializable{
	private static final long serialVersionUID = 7863262235394607247L;

	@EmbeddedId
	private ModuloPerfilID id = new ModuloPerfilID();
	
	/**
	 * Estado del registro
	 */
	@Column(name="ESTADO",  nullable = false)
	private String estado;
	
	/**
	 * Referencia a la emtidad moduloDTO
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOMODULO", referencedColumnName = "CODIGOMODULO", insertable = false, updatable = false)
	})
	private ModuloDTO moduloDTO;
	
	/**
	 * Referencia a la entidad perfilDTO
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOPERFIL", referencedColumnName = "CODIGOPERFIL", insertable = false, updatable = false)
	})
	private PerfilDTO perfilDTO;
	
	@Transient
	private Boolean seleccionado;

	public ModuloPerfilID getId() {
		return id;
	}

	public void setId(ModuloPerfilID id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public ModuloDTO getModuloDTO() {
		return moduloDTO;
	}

	public void setModuloDTO(ModuloDTO moduloDTO) {
		this.moduloDTO = moduloDTO;
	}

	public PerfilDTO getPerfilDTO() {
		return perfilDTO;
	}

	public void setPerfilDTO(PerfilDTO perfilDTO) {
		this.perfilDTO = perfilDTO;
	}

	public Boolean getSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(Boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
}
