package ec.com.erp.vehiculo.servicios;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.VehiculoDTO;
import ec.com.erp.vehiculo.gestor.IVehiculoGestor;

public class VehiculoServicio implements IVehiculoServicio{
	
	private IVehiculoGestor vehiculoGestor;

	public IVehiculoGestor getVehiculoGestor() {
		return vehiculoGestor;
	}

	public void setVehiculoGestor(IVehiculoGestor vehiculoGestor) {
		this.vehiculoGestor = vehiculoGestor;
	}

	/**
	 * M\u00e9todo para obtener lista de vehiculos
	 * @param codigoCompania
	 * @param placa
	 * @param documentoTransportista
	 * @param nombreTransportista
	 * @return Collection<VehiculoDTO>
	 * @throws ERPException
	 */
	@Override
	public Collection<VehiculoDTO> findObtenerListaVehiculos(Integer codigoCompania, String placa, String documentoTransportista, String nombreTransportista) throws ERPException{
		return this.vehiculoGestor.obtenerListaVehiculos(codigoCompania, placa, documentoTransportista, nombreTransportista);
	}
	
	/**
	 * M\u00e9todo para guardar y actualizar vehiculos
	 * @param vehiculoDTO
	 * @throws ERPException
	 */
	@Override
	public void transGuardarActualizarVehiculo(VehiculoDTO vehiculoDTO) throws ERPException{
		this.vehiculoGestor.guardarActualizarVehiculo(vehiculoDTO);
	}
}
