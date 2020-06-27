/**
 * 
 */
package ec.com.erp.transaccion.gestor;

import java.sql.Timestamp;
import java.util.Collection;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.TransaccionDTO;
import ec.com.erp.transaccion.dao.ITransaccionDAO;

/**
 * @author Esteban Gudino
 * 2018-05-05
 */
public class TransaccionGestor implements ITransaccionGestor {

	private ITransaccionDAO transaccionDAO;
	
	
	public ITransaccionDAO getTransaccionDAO() {
		return transaccionDAO;
	}

	public void setTransaccionDAO(ITransaccionDAO transaccionDAO) {
		this.transaccionDAO = transaccionDAO;
	}


	/**
	 * M\u00e9todo para obtener lista de transaccciones
	 * @param codigoCompania
	 * @param fechaTransaccionInicio
	 * @param fechaTransaccionFin
	 * @param tipoTransaccion
	 * @return Collection<TransaccionDTO>
	 * @throws ERPException
	 */
	@Override
	public Collection<TransaccionDTO> obtenerListaTransacciones(Integer codigoCompania, Timestamp fechaTransaccionInicio, Timestamp fechaTransaccionFin, String tipoTransaccion) throws ERPException{
		return this.transaccionDAO.obtenerListaTransacciones(codigoCompania, fechaTransaccionInicio, fechaTransaccionFin, tipoTransaccion);
	}
	
	/**
	 * M\u00e9todo para guardar transacciones
	 * @param transaccionDTO
	 * @throws ERPException
	 */
	@Override
	public void guardarTransaccion(TransaccionDTO transaccionDTO) throws ERPException{
		try{
			// Guardamos la cabecera de la factura
			transaccionDTO.setCodigoTipoTransaccion(ERPConstantes.CODIGO_CATALOGO_TIPOS_TRANSACCION);
			this.transaccionDAO.guardarTransaccion(transaccionDTO);
		} catch (ERPException e) {
			transaccionDTO.getId().setCodigoTransaccion(null);
			throw new ERPException("Error, {0}",e.getMessage()) ;
		} catch (Exception e) {
			transaccionDTO.getId().setCodigoTransaccion(null);
			throw new ERPException("Error, {0}",e.getMessage());
		} 
	}
	
}
