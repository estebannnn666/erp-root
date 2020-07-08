package ec.com.erp.transaccion.gestor;

import java.sql.Timestamp;
import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.PagosFacturaDTO;
import ec.com.erp.cliente.mdl.dto.TransaccionDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface ITransaccionGestor {
	
	/**
	 * M\u00e9todo para obtener lista de transaccciones
	 * @param codigoCompania
	 * @param fechaTransaccionInicio
	 * @param fechaTransaccionFin
	 * @param tipoTransaccion
	 * @return Collection<TransaccionDTO>
	 * @throws ERPException
	 */
	Collection<TransaccionDTO> obtenerListaTransacciones(Integer codigoCompania, Timestamp fechaTransaccionInicio, Timestamp fechaTransaccionFin, String tipoTransaccion) throws ERPException;
	
	/**
	 * M\u00e9todo para guardar transacciones
	 * @param transaccionDTO
	 * @throws ERPException
	 */
	void guardarTransaccion(TransaccionDTO transaccionDTO) throws ERPException;
	
	/**
	 * M\u00e9todo para obtener lista de pagos por factura
	 * @param codigoCompania
	 * @param codigoFactura
	 * @return Collection<PagosFacturaDTO>
	 * @throws ERPException
	 */
	Collection<PagosFacturaDTO> obtenerListaPagosFactura(Integer codigoCompania, Long codigoFactura) throws ERPException;
	
	/**
	 * M\u00e9todo para guardar pagos por factura
	 * @param transaccionDTO
	 * @throws ERPException
	 */
	void guardarPago(String tipoFactura, PagosFacturaDTO pagosFacturaDTO) throws ERPException;
}
