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

import ec.com.erp.cliente.mdl.dto.id.PerfilID;

/**
 * 
 * @author Esteban Gudino
 * 2017-06-06
 */
@Entity
@Table(name="SCVNTPERFIL")
public class PerfilDTO implements Serializable{
	private static final long serialVersionUID = 7863262235394607247L;

	@EmbeddedId
	private PerfilID id = new PerfilID();
	
	/**
	 * Nombre del perfil
	 */
	@Column(name = "NOMBREPERFIL", nullable = false)
	private String nombrePerfil ;
	
	/**
	 * Descripcion del perfil
	 */
	@Column(name = "DESCRIPCION", nullable = false)
	private String descripcion ;
	
	/**
	 * Codigo valor tipo perfil
	 */
	@Column(name = "CODIGOVALORTIPOPERFIL", nullable = false)
	private String codigoValorTipoPerfil ;
	
	/**
	 * Codigo tipo perfil
	 */
	@Column(name = "CODIGOTIPOPERFIL", nullable = false)
	private Integer codigoTipoPerfil ;
	
	/**
	 * Estado del registro del catalogo
	 */
	@Column(name="ESTADO")
	private String estado;
	
	/**
	 * Referencia CatalogoValorDTO periodo de verificacion
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CODIGOVALORTIPOPERFIL", referencedColumnName = "CODIGOCATALOGOVALOR", insertable = false, updatable = false),
		@JoinColumn(name = "CODIGOTIPOPERFIL", referencedColumnName = "CODIGOCATALOGOTIPO", insertable = false, updatable = false)
	})
	private CatalogoValorDTO perfilCatalogoValorDTO;

	public PerfilID getId() {
		return id;
	}

	public void setId(PerfilID id) {
		this.id = id;
	}

	public String getNombrePerfil() {
		return nombrePerfil;
	}

	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigoValorTipoPerfil() {
		return codigoValorTipoPerfil;
	}

	public void setCodigoValorTipoPerfil(String codigoValorTipoPerfil) {
		this.codigoValorTipoPerfil = codigoValorTipoPerfil;
	}

	public Integer getCodigoTipoPerfil() {
		return codigoTipoPerfil;
	}

	public void setCodigoTipoPerfil(Integer codigoTipoPerfil) {
		this.codigoTipoPerfil = codigoTipoPerfil;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public CatalogoValorDTO getPerfilCatalogoValorDTO() {
		return perfilCatalogoValorDTO;
	}

	public void setPerfilCatalogoValorDTO(CatalogoValorDTO perfilCatalogoValorDTO) {
		this.perfilCatalogoValorDTO = perfilCatalogoValorDTO;
	}
	
}
