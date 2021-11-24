package ec.com.erp.cliente.mdl.dto.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author Esteban Gudino
 * 2021-11-26
 */

@SuppressWarnings("serial")
@Embeddable
public class NotaCreditoID implements Serializable{

	@Column(name="CODIGOCOMPANIA")
	private Integer codigoCompania;

	@Column(name="CODIGONOTACREDITO")
	private Long codigoNotaCredito;
	
	public static final String NOMBRE_SECUENCIA = "SCVNSECNOTACREDITO";
	public static final String NTC_ELECTRONICA_RUC_UNO = "SCVNSECNTCELECTRONICAUNO";
	public static final String NTC_ELECTRONICA_RUC_DOS = "SCVNSECNTCELECTRONICADOS";
	

	public Integer getCodigoCompania() {
		return codigoCompania;
	}

	public void setCodigoCompania(Integer codigoCompania) {
		this.codigoCompania = codigoCompania;
	}

	public Long getCodigoNotaCredito() {
		return codigoNotaCredito;
	}

	public void setCodigoNotaCredito(Long codigoNotaCredito) {
		this.codigoNotaCredito = codigoNotaCredito;
	}
}
