package ec.com.erp.vehiculo.dao;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.VehiculoDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IVehiculoDAO {
	
	/**
	 * M\u00e9todo para obtener lista de vehiculos
	 * @param codigoCompania
	 * @param placa
	 * @param documentoTransportista
	 * @param nombreTransportista
	 * @return Collection<VehiculoDTO>
	 * @throws ERPException
	 */
	Collection<VehiculoDTO> obtenerListaVehiculos(Integer codigoCompania, String placa, String documentoTransportista, String nombreTransportista) throws ERPException;
	
	/**
	 * M\u00e9todo para guardar y actualizar vehiculos
	 * @param vehiculoDTO
	 * @throws ERPException
	 */
	void guardarActualizarVehiculo(VehiculoDTO vehiculoDTO) throws ERPException;
}
