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
public class FacturaCabeceraID implements Serializable{

	@Column(name="CODIGOCOMPANIA")
	private Integer codigoCompania;

	@Column(name="CODIGOFACTURA")
	private Long codigoFactura;
	
	public static final String NOMBRE_SECUENCIA = "SCVNSECFACTURACABECERA";
	public static final String NOMBRE_SECUENCIA_VENTA = "SCVNSECFACTURAVENTAS";
	public static final String NOMBRE_SECUENCIA_NOTA_VENTA = "SCVNSECFACTURANOTAVENTA";
	public static final String NOMBRE_SECUENCIA_COMPRA = "SCVNSECFACTURACOMPRAS";
	public static final String NOMBRE_SECUENCIA_COMPROVANTE_VENTA = "SCVNSECNUMEROFACTURA";
	public static final String NOMBRE_SECUENCIA_FACTURA_RUC_UNO = "SCVNSECFACTURARUCUNO";
	

	public Integer getCodigoCompania() {
		return codigoCompania;
	}

	public void setCodigoCompania(Integer codigoCompania) {
		this.codigoCompania = codigoCompania;
	}

	public Long getCodigoFactura() {
		return codigoFactura;
	}

	public void setCodigoFactura(Long codigoFactura) {
		this.codigoFactura = codigoFactura;
	}
}
