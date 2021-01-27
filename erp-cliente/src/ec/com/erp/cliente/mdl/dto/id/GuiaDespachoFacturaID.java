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
public class GuiaDespachoFacturaID implements Serializable{

	@Column(name="CODIGOCOMPANIA")
	private Integer codigoCompania;

	@Column(name="CODIGOGUIADESPACHOFACTURA")
	private Long codigoGuiaDespachoFactura;
	
	public static final String NOMBRE_SECUENCIA = "SCVNSECGUIADESPACHOFACTURA";

	public Integer getCodigoCompania() {
		return codigoCompania;
	}

	public void setCodigoCompania(Integer codigoCompania) {
		this.codigoCompania = codigoCompania;
	}

	public Long getCodigoGuiaDespachoFactura() {
		return codigoGuiaDespachoFactura;
	}

	public void setCodigoGuiaDespachoFactura(Long codigoGuiaDespachoFactura) {
		this.codigoGuiaDespachoFactura = codigoGuiaDespachoFactura;
	}
}
