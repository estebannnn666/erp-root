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
public class DetallePedidoID implements Serializable{

	@Column(name="CODIGOCOMPANIA")
	private Integer codigoCompania;

	@Column(name="CODIGODETALLEPEDIDO")
	private Long codigoDetallePedido;
	
	@Column(name="CODIGOPEDIDO")
	private Long codigoPedido;

	public static final String NOMBRE_SECUENCIA = "SCVNSECDETALLEPEDIDO";
	
	public Integer getCodigoCompania() {
		return codigoCompania;
	}

	public void setCodigoCompania(Integer codigoCompania) {
		this.codigoCompania = codigoCompania;
	}

	public Long getCodigoDetallePedido() {
		return codigoDetallePedido;
	}

	public void setCodigoDetallePedido(Long codigoDetallePedido) {
		this.codigoDetallePedido = codigoDetallePedido;
	}

	public Long getCodigoPedido() {
		return codigoPedido;
	}

	public void setCodigoPedido(Long codigoPedido) {
		this.codigoPedido = codigoPedido;
	}
}
