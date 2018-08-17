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
public class ModuloPerfilID implements Serializable{

	@Column(name="CODIGOMODULO")
	private Long codigoModulo;
	
	@Column(name="CODIGOPERFIL")
	private Long codigoPerfil;

	public Long getCodigoModulo() {
		return codigoModulo;
	}

	public void setCodigoModulo(Long codigoModulo) {
		this.codigoModulo = codigoModulo;
	}

	public Long getCodigoPerfil() {
		return codigoPerfil;
	}

	public void setCodigoPerfil(Long codigoPerfil) {
		this.codigoPerfil = codigoPerfil;
	}
}
