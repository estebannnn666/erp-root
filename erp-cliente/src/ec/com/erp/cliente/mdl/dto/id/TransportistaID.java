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
public class TransportistaID implements Serializable{

	@Column(name="CODIGOCOMPANIA")
	private Integer codigoCompania;

	@Column(name="CODIGOTRANSPORTISTA")
	private Long codigoTransportista;
	
	public static final String NOMBRE_SECUENCIA = "SCVNSECTRANSPORTISTA";

	public Integer getCodigoCompania() {
		return codigoCompania;
	}

	public void setCodigoCompania(Integer codigoCompania) {
		this.codigoCompania = codigoCompania;
	}

	public Long getCodigoTransportista() {
		return codigoTransportista;
	}

	public void setCodigoTransportista(Long codigoTransportista) {
		this.codigoTransportista = codigoTransportista;
	}
}
