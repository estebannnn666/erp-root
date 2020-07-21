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
public class ArticuloUnidadManejoID implements Serializable{

	@Column(name="CODIGOCOMPANIA")
	private Integer codigoCompania;

	@Column(name="CODIGOARTICULOUNIDADMANEJO")
	private Integer codigoArticuloUnidadManejo;
	
	@Column(name="CODIGOARTICULO")
	private Integer codigoArticulo;
	
	public static final String NOMBRE_SECUENCIA = "SCVNSECARTICULOUNIDADMANEJO";

	public Integer getCodigoCompania() {
		return codigoCompania;
	}

	public void setCodigoCompania(Integer codigoCompania) {
		this.codigoCompania = codigoCompania;
	}
	
	public Integer getCodigoArticulo() {
		return codigoArticulo;
	}

	public void setCodigoArticulo(Integer codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}

	public Integer getCodigoArticuloUnidadManejo() {
		return codigoArticuloUnidadManejo;
	}

	public void setCodigoArticuloUnidadManejo(Integer codigoArticuloUnidadManejo) {
		this.codigoArticuloUnidadManejo = codigoArticuloUnidadManejo;
	}
}
