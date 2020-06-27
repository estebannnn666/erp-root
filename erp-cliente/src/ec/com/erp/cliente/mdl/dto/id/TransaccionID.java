package ec.com.erp.cliente.mdl.dto.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author Esteban Gudino
 * 2020-06-24
 */

@SuppressWarnings("serial")
@Embeddable
public class TransaccionID implements Serializable{

	@Column(name="CODIGOCOMPANIA")
	private Integer codigoCompania;

	@Column(name="CODIGOTRANSACCION")
	private Long codigoTransaccion;
	
	public static final String NOMBRE_SECUENCIA = "SCVNSECTRANSACCION";

	public Integer getCodigoCompania() {
		return codigoCompania;
	}

	public void setCodigoCompania(Integer codigoCompania) {
		this.codigoCompania = codigoCompania;
	}

	public Long getCodigoTransaccion() {
		return codigoTransaccion;
	}

	public void setCodigoTransaccion(Long codigoTransaccion) {
		this.codigoTransaccion = codigoTransaccion;
	}
}
