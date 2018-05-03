package ec.com.erp.vehiculo.chofer.dao;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.VehiculoChoferDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IVehiculoChoferDAO {
	
	/**
	 *  M\u00e9todo para obtener lista de choferes por vehiculo
	 * @param codigoCompania
	 * @param placa
	 * @return
	 * @throws ERPException
	 */
	Collection<VehiculoChoferDTO> obtenerListaChoferesByVehiculo(Integer codigoCompania, String placa) throws ERPException;
	
	/**
	 * M\u00e9todo para guardar y actualizar vehiculo chofer
	 * @param vehiculoChoferDTO
	 * @throws ERPException
	 */
	void guardarActualizarVehiculoChofer(VehiculoChoferDTO vehiculoChoferDTO) throws ERPException;
}
