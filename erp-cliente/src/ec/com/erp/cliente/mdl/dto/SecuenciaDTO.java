package ec.com.erp.cliente.mdl.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import ec.com.erp.cliente.mdl.dto.id.SecuenciaID;

/**
 * 
 * @author Esteban Gudino
 * 2017-06-06
 */
@Entity
@Table(name="SCVNTSECUENCIAS")
public class SecuenciaDTO implements Serializable{
	private static final long serialVersionUID = 7863262235394607247L;

	@EmbeddedId
	private SecuenciaID id = new SecuenciaID();
	
	/**
	 * Valor de la secuencia
	 */
	@Column(name = "VALORSECUENCIA", nullable = false)
	private Integer valorSecuencia ;

	public SecuenciaID getId() {
		return id;
	}

	public void setId(SecuenciaID id) {
		this.id = id;
	}

	public Integer getValorSecuencia() {
		return valorSecuencia;
	}

	public void setValorSecuencia(Integer valorSecuencia) {
		this.valorSecuencia = valorSecuencia;
	}
}
