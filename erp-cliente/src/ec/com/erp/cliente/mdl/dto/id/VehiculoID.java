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
public class VehiculoID implements Serializable{

	@Column(name="CODIGOCOMPANIA")
	private Integer codigoCompania;

	@Column(name="CODIGOVEHICULO")
	private Long codigoVehiculo;
	
	public static final String NOMBRE_SECUENCIA = "SCVNSECVEHICULO";

	public Integer getCodigoCompania() {
		return codigoCompania;
	}

	public void setCodigoCompania(Integer codigoCompania) {
		this.codigoCompania = codigoCompania;
	}

	public Long getCodigoVehiculo() {
		return codigoVehiculo;
	}

	public void setCodigoVehiculo(Long codigoVehiculo) {
		this.codigoVehiculo = codigoVehiculo;
	}
}
