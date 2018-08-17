package ec.com.erp.cliente.mdl.dto.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author Esteban Gudino
 * 2017-06-27
 */

@SuppressWarnings("serial")
@Embeddable
public class ModuloID implements Serializable{

	@Column(name="CODIGOMODULO")
	private Long codigoModulo;
	
	public static final String NOMBRE_SECUENCIA = "SCVNSECMODULOS";

	public Long getCodigoModulo() {
		return codigoModulo;
	}

	public void setCodigoModulo(Long codigoModulo) {
		this.codigoModulo = codigoModulo;
	}
}
