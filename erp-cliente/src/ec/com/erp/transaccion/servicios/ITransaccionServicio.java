/**
 * 
 */
package ec.com.erp.transaccion.servicios;

import java.sql.Timestamp;
import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.TransaccionDTO;

/**
 * @author Esteban Gudino
 * 2018-05-05
 */
public interface ITransaccionServicio {

	/**
	 * M\u00e9todo para obtener lista de transaccciones
	 * @param codigoCompania
	 * @param fechaTransaccionInicio
	 * @param fechaTransaccionFin
	 * @param tipoTransaccion
	 * @return Collection<TransaccionDTO>
	 * @throws ERPException
	 */
	Collection<TransaccionDTO> findObtenerListaTransacciones(Integer codigoCompania, Timestamp fechaTransaccionInicio, Timestamp fechaTransaccionFin, String tipoTransaccion) throws ERPException;
	
	/**
	 * M\u00e9todo para guardar transacciones
	 * @param transaccionDTO
	 * @throws ERPException
	 */
	void transGuardarTransaccion(TransaccionDTO transaccionDTO) throws ERPException;
}
