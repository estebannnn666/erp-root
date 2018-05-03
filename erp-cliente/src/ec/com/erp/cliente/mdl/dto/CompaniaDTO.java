package ec.com.erp.cliente.mdl.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import ec.com.erp.cliente.mdl.dto.id.CompaniaID;

/**
 * 
 * @author Esteban Gudino
 * 2017-06-06
 */
@Entity
@Table(name="SCVNTCOMPANIA")
public class CompaniaDTO implements Serializable{
	private static final long serialVersionUID = 7863262235394607247L;

	@EmbeddedId
	private CompaniaID id = new CompaniaID();
	
	/**
	 * Nombre de la compania
	 */
	@Column(name = "NOMBRECOMPANIA", nullable = false)
	private String nombreCompania ;
	
	/**
	 * Descripcion de la compania
	 */
	@Column(name = "DESCRIPCIONCOMPANIA", nullable = false)
	private String descripcionCompania ;
	
	/**
	 * Estado del registro del catalogo
	 */
	@Column(name="ESTADO")
	private String estado;

	public CompaniaID getId() {
		return id;
	}

	public void setId(CompaniaID id) {
		this.id = id;
	}

	public String getNombreCompania() {
		return nombreCompania;
	}

	public void setNombreCompania(String nombreCompania) {
		this.nombreCompania = nombreCompania;
	}

	public String getDescripcionCompania() {
		return descripcionCompania;
	}

	public void setDescripcionCompania(String descripcionCompania) {
		this.descripcionCompania = descripcionCompania;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
