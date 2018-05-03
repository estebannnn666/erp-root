package ec.com.erp.cliente.mdl.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import ec.com.erp.cliente.mdl.dto.id.CatalogoValorID;

/**
 * 
 * @author Esteban Gudino
 * 2017-06-06
 */
@Entity
@Table(name="SCVNTCATALOGOVALOR")
public class CatalogoValorDTO implements Serializable{
	private static final long serialVersionUID = 7863262235394607247L;

	@EmbeddedId
	private CatalogoValorID id = new CatalogoValorID();
	
	/**
	 * Nombre del catalogo descripcion
	 */
	@Column(name = "NOMBRECATALOGOVALOR", nullable = false)
	private String nombreCatalogoValor ;
	
	/**
	 * Estado del registro del catalogo
	 */
	@Column(name="ESTADO")
	private String estado;

	public CatalogoValorID getId() {
		return id;
	}

	public void setId(CatalogoValorID id) {
		this.id = id;
	}

	public String getNombreCatalogoValor() {
		return nombreCatalogoValor;
	}

	public void setNombreCatalogoValor(String nombreCatalogoValor) {
		this.nombreCatalogoValor = nombreCatalogoValor;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
