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
public class UsuariosID implements Serializable{

	@Column(name="USERID")
	private String userId;
	
	public static final String NOMBRE_SECUENCIA = "SCVNSECUSUARIO";

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
