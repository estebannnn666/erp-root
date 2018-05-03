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
public class GuiaDespachoExtrasID implements Serializable{

	@Column(name="CODIGOCOMPANIA")
	private Integer codigoCompania;

	@Column(name="CODIGOGUIADESPACHOEXTRA")
	private Long codigoGuiaDespachoExtra;
	
	public static final String NOMBRE_SECUENCIA = "SCVNSECGUIADESPACHOEXTRAS";

	public Integer getCodigoCompania() {
		return codigoCompania;
	}

	public void setCodigoCompania(Integer codigoCompania) {
		this.codigoCompania = codigoCompania;
	}

	public Long getCodigoGuiaDespachoExtra() {
		return codigoGuiaDespachoExtra;
	}

	public void setCodigoGuiaDespachoExtra(Long codigoGuiaDespachoExtra) {
		this.codigoGuiaDespachoExtra = codigoGuiaDespachoExtra;
	}
}
