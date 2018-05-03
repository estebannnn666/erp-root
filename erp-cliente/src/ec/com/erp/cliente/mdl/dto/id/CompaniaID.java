package ec.com.erp.cliente.mdl.dto.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author Esteban Gudino
 * 2017-06-27
 */

@SuppressWarnings("serial")
@Embeddable
public class CompaniaID implements Serializable{

	@Column(name="CODIGOCOMPANIA")
	private Integer codigoCompania;

	public Integer getCodigoCompania() {
		return codigoCompania;
	}

	public void setCodigoCompania(Integer codigoCompania) {
		this.codigoCompania = codigoCompania;
	}
}
