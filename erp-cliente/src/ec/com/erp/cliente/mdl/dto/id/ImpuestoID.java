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
public class ImpuestoID implements Serializable{

	@Column(name="CODIGOCOMPANIA")
	private Integer codigoCompania;

	@Column(name="CODIGOIMPUESTO")
	private Integer codigoImpuesto;
	
	public static final String NOMBRE_SECUENCIA = "SCVNSECIMPUESTO";

	public Integer getCodigoCompania() {
		return codigoCompania;
	}

	public void setCodigoCompania(Integer codigoCompania) {
		this.codigoCompania = codigoCompania;
	}

	public Integer getCodigoImpuesto() {
		return codigoImpuesto;
	}

	public void setCodigoImpuesto(Integer codigoImpuesto) {
		this.codigoImpuesto = codigoImpuesto;
	}
}
