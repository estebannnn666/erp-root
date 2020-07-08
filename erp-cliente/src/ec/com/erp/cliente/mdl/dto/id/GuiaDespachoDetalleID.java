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
public class GuiaDespachoDetalleID implements Serializable{

	@Column(name="CODIGOCOMPANIA")
	private Integer codigoCompania;

	@Column(name="CODIGOGUIADESPACHODETALLE")
	private Long codigoGuiaDespachoDetalle;
	
	public static final String NOMBRE_SECUENCIA = "SCVNSECGUIADESPACHODETALLE";

	public Integer getCodigoCompania() {
		return codigoCompania;
	}

	public void setCodigoCompania(Integer codigoCompania) {
		this.codigoCompania = codigoCompania;
	}

	public Long getCodigoGuiaDespachoDetalle() {
		return codigoGuiaDespachoDetalle;
	}

	public void setCodigoGuiaDespachoDetalle(Long codigoGuiaDespachoDetalle) {
		this.codigoGuiaDespachoDetalle = codigoGuiaDespachoDetalle;
	}
}
