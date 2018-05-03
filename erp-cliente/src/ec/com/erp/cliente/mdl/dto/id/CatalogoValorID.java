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
public class CatalogoValorID implements Serializable{

	@Column(name="CODIGOCATALOGOVALOR")
	private String codigoCatalogoValor;

	@Column(name="CODIGOCATALOGOTIPO")
	private Integer codigoCatalogoTipo;

	public String getCodigoCatalogoValor() {
		return codigoCatalogoValor;
	}

	public void setCodigoCatalogoValor(String codigoCatalogoValor) {
		this.codigoCatalogoValor = codigoCatalogoValor;
	}

	public Integer getCodigoCatalogoTipo() {
		return codigoCatalogoTipo;
	}

	public void setCodigoCatalogoTipo(Integer codigoCatalogoTipo) {
		this.codigoCatalogoTipo = codigoCatalogoTipo;
	}
}
