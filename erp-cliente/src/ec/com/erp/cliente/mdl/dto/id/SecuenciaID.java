package ec.com.erp.cliente.mdl.dto.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author Esteban Gudino
 * 2017-07-13
 */

@SuppressWarnings("serial")
@Embeddable
public class SecuenciaID implements Serializable{

	@Column(name="NOMBRESECUENCIA")
	private String nombreSecuencia;

	public String getNombreSecuencia() {
		return nombreSecuencia;
	}

	public void setNombreSecuencia(String nombreSecuencia) {
		this.nombreSecuencia = nombreSecuencia;
	}
}
