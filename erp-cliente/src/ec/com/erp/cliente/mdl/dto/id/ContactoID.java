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
public class ContactoID implements Serializable{

	@Column(name="CODIGOCOMPANIA")
	private Integer codigoCompania;

	@Column(name="CODIGOCONTACTO")
	private Long codigoContacto;
	
	public static final String NOMBRE_SECUENCIA = "SCVNSECCONTACTO";

	public Integer getCodigoCompania() {
		return codigoCompania;
	}

	public void setCodigoCompania(Integer codigoCompania) {
		this.codigoCompania = codigoCompania;
	}

	public Long getCodigoContacto() {
		return codigoContacto;
	}

	public void setCodigoContacto(Long codigoContacto) {
		this.codigoContacto = codigoContacto;
	}
}
