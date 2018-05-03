package ec.com.erp.cliente.mdl.dto.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author Esteban Gudino
 * 2018-04-19
 */

@SuppressWarnings("serial")
@Embeddable
public class ChoferID implements Serializable{

	@Column(name="CODIGOCOMPANIA")
	private Integer codigoCompania;

	@Column(name="CODIGOCHOFER")
	private Long codigoChofer;
	
	public static final String NOMBRE_SECUENCIA = "SCVNSECCHOFER";

	public Integer getCodigoCompania() {
		return codigoCompania;
	}

	public void setCodigoCompania(Integer codigoCompania) {
		this.codigoCompania = codigoCompania;
	}

	public Long getCodigoChofer() {
		return codigoChofer;
	}

	public void setCodigoChofer(Long codigoChofer) {
		this.codigoChofer = codigoChofer;
	}

}
