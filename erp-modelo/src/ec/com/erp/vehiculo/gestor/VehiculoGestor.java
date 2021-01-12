package ec.com.erp.vehiculo.gestor;

import java.util.Collection;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.VehiculoChoferDTO;
import ec.com.erp.cliente.mdl.dto.VehiculoDTO;
import ec.com.erp.vehiculo.chofer.gestor.IVehiculoChoferGestor;
import ec.com.erp.vehiculo.dao.IVehiculoDAO;

public class VehiculoGestor implements IVehiculoGestor{

	private IVehiculoChoferGestor vehiculoChoferGestor;
	private IVehiculoDAO vehiculoDAO;
	
	public IVehiculoChoferGestor getVehiculoChoferGestor() {
		return vehiculoChoferGestor;
	}

	public void setVehiculoChoferGestor(IVehiculoChoferGestor vehiculoChoferGestor) {
		this.vehiculoChoferGestor = vehiculoChoferGestor;
	}

	public IVehiculoDAO getVehiculoDAO() {
		return vehiculoDAO;
	}

	public void setVehiculoDAO(IVehiculoDAO vehiculoDAO) {
		this.vehiculoDAO = vehiculoDAO;
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
	public Collection<VehiculoDTO> obtenerListaVehiculos(Integer codigoCompania, String placa, String documentoTransportista, String nombreTransportista) throws ERPException{
		return this.vehiculoDAO.obtenerListaVehiculos(codigoCompania, placa, documentoTransportista, nombreTransportista);
	}
	
	/**
	 * M\u00e9todo para guardar y actualizar vehiculos
	 * @param vehiculoDTO
	 * @throws ERPException
	 */
	@Override
	public void guardarActualizarVehiculo(VehiculoDTO vehiculoDTO) throws ERPException{
		try {
			// Obtenemos la coleccion de choferes asignados al vehiculo
			Collection<VehiculoChoferDTO> vehiculoChoferDTOCols = vehiculoDTO.getVehiculoChoferDTOCols();
			if(vehiculoDTO.getId().getCodigoVehiculo() != null) {
				Collection<VehiculoChoferDTO> vehiculoChoferDTOSaveCols = this.vehiculoChoferGestor.obtenerListaChoferesByVehiculo(vehiculoDTO.getId().getCodigoCompania(), vehiculoDTO.getPlaca());
				vehiculoChoferDTOSaveCols.stream().forEach(vehiculoChofer ->{
					VehiculoChoferDTO objectSearch = vehiculoChoferDTOCols.stream()
							.filter(predicate -> predicate.getId().getCodigoVehiculoChofer().intValue() == vehiculoChofer.getId().getCodigoVehiculoChofer())
							.findFirst().orElse(null);
					if(objectSearch == null) {
						vehiculoChofer.setEstado(ERPConstantes.ESTADO_INACTIVO_NUMERICO);
						this.vehiculoChoferGestor.guardarActualizarVehiculoChofer(vehiculoChofer);
					}
				});
			}
			
			//Creamos o actualizamos el cliente
			vehiculoDTO.setPlaca(vehiculoDTO.getPlaca().toUpperCase());
			vehiculoDTO.setMarca(vehiculoDTO.getMarca().toUpperCase());
			vehiculoDTO.setColor(vehiculoDTO.getColor().toUpperCase());
			vehiculoDTO.setModelo(vehiculoDTO.getModelo().toUpperCase());
			vehiculoDTO.setCodigoTipoVehiculo(ERPConstantes.CODIGO_CATALOGO_TIPOS_VEHICULOS);
			this.vehiculoDAO.guardarActualizarVehiculo(vehiculoDTO);
			
			//Creamos o actualizamos los choferes asignados al vehiculo
			for(VehiculoChoferDTO vehiculoChoferDTO : vehiculoChoferDTOCols) {
				vehiculoChoferDTO.setCodigoVehiculo(vehiculoDTO.getId().getCodigoVehiculo());
				vehiculoChoferDTO.getId().setCodigoCompania(vehiculoDTO.getId().getCodigoCompania());
				vehiculoChoferDTO.setUsuarioRegistro(vehiculoDTO.getUsuarioRegistro());
				this.vehiculoChoferGestor.guardarActualizarVehiculoChofer(vehiculoChoferDTO);
			}
		}
		catch (ERPException e) {
			throw new ERPException("Error, ", e.getMessage());
		}
		catch (Exception e) {
			throw new ERPException("Error, ", e.getMessage());
		}
	}
}
