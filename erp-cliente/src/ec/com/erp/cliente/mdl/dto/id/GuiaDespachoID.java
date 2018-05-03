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
public class GuiaDespachoID implements Serializable{

	@Column(name="CODIGOCOMPANIA")
	private Integer codigoCompania;

	@Column(name="CODIGOGUIADESPACHO")
	private Long codigoGuiaDespacho;
	
	public static final String NOMBRE_SECUENCIA = "SCVNSECGUIADESPACHO";

	public Integer getCodigoCompania() {
		return codigoCompania;
	}

	public void setCodigoCompania(Integer codigoCompania) {
		this.codigoCompania = codigoCompania;
	}

	public Long getCodigoGuiaDespacho() {
		return codigoGuiaDespacho;
	}

	public void setCodigoGuiaDespacho(Long codigoGuiaDespacho) {
		this.codigoGuiaDespacho = codigoGuiaDespacho;
	}
}
