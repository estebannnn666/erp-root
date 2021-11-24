/**
 * 
 */
package ec.com.erp.notacredito.servicios;

import java.sql.Timestamp;
import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.NotaCreditoDTO;
import ec.com.erp.notacredito.gestor.INotaCreditoGestor;

/**
 * @author Esteban Gudino
 * 2018-05-05
 */
public class NotaCreditoServicio implements INotaCreditoServicio {

	private INotaCreditoGestor notaCreditoGestor;
	
	public INotaCreditoGestor getNotaCreditoGestor() {
		return notaCreditoGestor;
	}

	public void setNotaCreditoGestor(INotaCreditoGestor notaCreditoGestor) {
		this.notaCreditoGestor = notaCreditoGestor;
	}

	/**
	 * M\u00e9todo para obtener lista de notas de credito por filtros de busqueda
	 * @param codigoCompania
	 * @param numeroNotaCredito
	 * @param fechaNotaCreditoInicio
	 * @param fechaNotaCreditoFin
	 * @param docCliente
	 * @param nombreCliente
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Collection<NotaCreditoDTO> findObtenerListaNotasCredito(Integer codigoCompania, String numeroNotaCredito, Timestamp fechaNotaCreditoInicio, Timestamp fechaNotaCreditoFin, String docCliente, String nombreCliente) throws ERPException{
		return this.notaCreditoGestor.obtenerListaNotasCredito(codigoCompania, numeroNotaCredito, fechaNotaCreditoInicio, fechaNotaCreditoFin, docCliente, nombreCliente);
	}
	
	/**
	 * M\u00e9todo para guardar y actualizar nota de credito
	 * @param crearNotaCreditoElectronica
	 * @param notaCreditoDTO
	 * @throws ERPException
	 */
	@Override
	public void transGuardarActualizarNotaCredito(Boolean crearNotaCreditoElectronica, NotaCreditoDTO notaCreditoDTO) throws ERPException{
		this.notaCreditoGestor.guardarActualizarNotaCredito(crearNotaCreditoElectronica, notaCreditoDTO);
	}
	

}
