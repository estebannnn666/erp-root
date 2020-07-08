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
public class PagosFacturaID implements Serializable{

	@Column(name="CODIGOCOMPANIA")
	private Integer codigoCompania;

	@Column(name="CODIGOPAGO")
	private Long codigoPago;
	
	public static final String NOMBRE_SECUENCIA = "SCVNSECPAGOS";

	public Integer getCodigoCompania() {
		return codigoCompania;
	}

	public void setCodigoCompania(Integer codigoCompania) {
		this.codigoCompania = codigoCompania;
	}

	public Long getCodigoPago() {
		return codigoPago;
	}

	public void setCodigoPago(Long codigoPago) {
		this.codigoPago = codigoPago;
	}
}
