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
public class NotaCreditoDetalleID implements Serializable{

	@Column(name="CODIGOCOMPANIA")
	private Integer codigoCompania;

	@Column(name="CODIGODETALLENOTACREDITO")
	private Long codigoDetalleNotaCredito;
	
	public static final String NOMBRE_SECUENCIA = "SCVNSECNOTACREDITODETALLE";

	public Integer getCodigoCompania() {
		return codigoCompania;
	}

	public void setCodigoCompania(Integer codigoCompania) {
		this.codigoCompania = codigoCompania;
	}

	public Long getCodigoDetalleNotaCredito() {
		return codigoDetalleNotaCredito;
	}

	public void setCodigoDetalleNotaCredito(Long codigoDetalleNotaCredito) {
		this.codigoDetalleNotaCredito = codigoDetalleNotaCredito;
	}
}
