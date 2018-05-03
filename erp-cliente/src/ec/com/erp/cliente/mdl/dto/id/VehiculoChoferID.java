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
public class VehiculoChoferID implements Serializable{

	@Column(name="CODIGOCOMPANIA")
	private Integer codigoCompania;

	@Column(name="CODIGOVEHICULOCHOFER")
	private Long codigoVehiculoChofer;
	
	public static final String NOMBRE_SECUENCIA = "SCVNSECVEHICULOCHOFER";

	public Integer getCodigoCompania() {
		return codigoCompania;
	}

	public void setCodigoCompania(Integer codigoCompania) {
		this.codigoCompania = codigoCompania;
	}

	public Long getCodigoVehiculoChofer() {
		return codigoVehiculoChofer;
	}

	public void setCodigoVehiculoChofer(Long codigoVehiculoChofer) {
		this.codigoVehiculoChofer = codigoVehiculoChofer;
	}

}
