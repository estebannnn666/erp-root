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
public class FacturaDetalleID implements Serializable{

	@Column(name="CODIGOCOMPANIA")
	private Integer codigoCompania;

	@Column(name="CODIGODETALLEFACTURA")
	private Long codigoDetalleFactura;
	
	public static final String NOMBRE_SECUENCIA = "SCVNSECFACTURADETALLE";

	public Integer getCodigoCompania() {
		return codigoCompania;
	}

	public void setCodigoCompania(Integer codigoCompania) {
		this.codigoCompania = codigoCompania;
	}

	public Long getCodigoDetalleFactura() {
		return codigoDetalleFactura;
	}

	public void setCodigoDetalleFactura(Long codigoDetalleFactura) {
		this.codigoDetalleFactura = codigoDetalleFactura;
	}
}
