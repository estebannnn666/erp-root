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
public class GuiaDespachoPedidoID implements Serializable{

	@Column(name="CODIGOCOMPANIA")
	private Integer codigoCompania;

	@Column(name="CODIGOGUIADESPACHOPEDIDO")
	private Long codigoGuiaDespachoPedido;
	
	public static final String NOMBRE_SECUENCIA = "SCVNSECGUIADESPACHOPEDIDO";

	public Integer getCodigoCompania() {
		return codigoCompania;
	}

	public void setCodigoCompania(Integer codigoCompania) {
		this.codigoCompania = codigoCompania;
	}

	public Long getCodigoGuiaDespachoPedido() {
		return codigoGuiaDespachoPedido;
	}

	public void setCodigoGuiaDespachoPedido(Long codigoGuiaDespachoPedido) {
		this.codigoGuiaDespachoPedido = codigoGuiaDespachoPedido;
	}
}
