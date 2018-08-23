package ec.com.erp.cliente.mdl.dto.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author Esteban Gudino
 * 2017-07-13
 */

@SuppressWarnings("serial")
@Embeddable
public class InventarioID implements Serializable{

	@Column(name="CODIGOCOMPANIA")
	private Integer codigoCompania;

	@Column(name="CODIGOINVENTARIO")
	private Long codigoInventario;
	
	public static final String NOMBRE_SECUENCIA = "SCVNSECINVETARIO";
	
	public Integer getCodigoCompania() {
		return codigoCompania;
	}

	public void setCodigoCompania(Integer codigoCompania) {
		this.codigoCompania = codigoCompania;
	}

	public Long getCodigoInventario() {
		return codigoInventario;
	}

	public void setCodigoInventario(Long codigoInventario) {
		this.codigoInventario = codigoInventario;
	}
}
