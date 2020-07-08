/**
 * 
 */
package ec.com.erp.transaccion.servicios;

import java.sql.Timestamp;
import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.PagosFacturaDTO;
import ec.com.erp.cliente.mdl.dto.TransaccionDTO;
import ec.com.erp.transaccion.gestor.ITransaccionGestor;

/**
 * @author Esteban Gudino
 * 2018-05-05
 */
public class TransaccionServicio implements ITransaccionServicio {

	private ITransaccionGestor transaccionGestor;
	
	public ITransaccionGestor getTransaccionGestor() {
		return transaccionGestor;
	}

	public void setTransaccionGestor(ITransaccionGestor transaccionGestor) {
		this.transaccionGestor = transaccionGestor;
	}

	/**
	 * M\u00e9todo para obtener lista de transacciones
	 * @param codigoCompania
	 * @param fechaTransaccionInicio
	 * @param fechaTransaccionFin
	 * @param tipoTransaccion
	 * @return Collection<TransaccionDTO>
	 * @throws ERPException
	 */
	@Override
	public Collection<TransaccionDTO> findObtenerListaTransacciones(Integer codigoCompania, Timestamp fechaTransaccionInicio, Timestamp fechaTransaccionFin, String tipoTransaccion) throws ERPException{
		return this.transaccionGestor.obtenerListaTransacciones(codigoCompania, fechaTransaccionInicio, fechaTransaccionFin, tipoTransaccion);
	}
	
	/**
	 * M\u00e9todo para guardar y actualizar factura cabecera
	 * @param transaccionDTO
	 * @throws ERPException
	 */
	@Override
	public void transGuardarTransaccion(TransaccionDTO transaccionDTO) throws ERPException{
		this.transaccionGestor.guardarTransaccion(transaccionDTO);
	}
	
	/**
	 * M\u00e9todo para obtener lista de pagos por factura
	 * @param codigoCompania
	 * @param codigoFactura
	 * @return Collection<PagosFacturaDTO>
	 * @throws ERPException
	 */
	public Collection<PagosFacturaDTO> findObtenerListaPagosFactura(Integer codigoCompania, Long codigoFactura) throws ERPException{
		return this.transaccionGestor.obtenerListaPagosFactura(codigoCompania, codigoFactura);
	}
	
	/**
	 * M\u00e9todo para guardar pagos por factura
	 * @param transaccionDTO
	 * @throws ERPException
	 */
	public void transGuardarPago(String tipoFactura, PagosFacturaDTO pagosFacturaDTO) throws ERPException{
		this.transaccionGestor.guardarPago(tipoFactura, pagosFacturaDTO);
	}

}
