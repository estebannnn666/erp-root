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
public class ParametroID implements Serializable{

	@Column(name="CODIGOCOMPANIA")
	private String codigoCompania;

	@Column(name="CODIGODOPARAMETRO")
	private String codigoParametro;

	public String getCodigoCompania() {
		return codigoCompania;
	}

	public void setCodigoCompania(String codigoCompania) {
		this.codigoCompania = codigoCompania;
	}

	public String getCodigoParametro() {
		return codigoParametro;
	}

	public void setCodigoParametro(String codigoParametro) {
		this.codigoParametro = codigoParametro;
	}

}
