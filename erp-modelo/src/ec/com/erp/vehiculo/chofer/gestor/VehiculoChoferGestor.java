package ec.com.erp.vehiculo.chofer.gestor;

import java.util.Collection;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.VehiculoChoferDTO;
import ec.com.erp.vehiculo.chofer.dao.IVehiculoChoferDAO;

public class VehiculoChoferGestor implements IVehiculoChoferGestor{

	private IVehiculoChoferDAO vehiculoChoferDAO;
	
	public IVehiculoChoferDAO getVehiculoChoferDAO() {
		return vehiculoChoferDAO;
	}

	public void setVehiculoChoferDAO(IVehiculoChoferDAO vehiculoChoferDAO) {
		this.vehiculoChoferDAO = vehiculoChoferDAO;
	}

	/**
	 *  M\u00e9todo para obtener lista de choferes por vehiculo
	 * @param codigoCompania
	 * @param placa
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Collection<VehiculoChoferDTO> obtenerListaChoferesByVehiculo(Integer codigoCompania, String placa) throws ERPException{
		return this.vehiculoChoferDAO.obtenerListaChoferesByVehiculo(codigoCompania, placa);
	}
	
	/**
	 * M\u00e9todo para guardar y actualizar vehiculo chofer
	 * @param vehiculoChoferDTO
	 * @throws ERPException
	 */
	@Override
	public void guardarActualizarVehiculoChofer(VehiculoChoferDTO vehiculoChoferDTO) throws ERPException{
		try {
			//Creamos o actualizamos el cliente
			vehiculoChoferDTO.getId().setCodigoCompania(Integer.parseInt(ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			vehiculoChoferDTO.setCodigoTipoChofer(ERPConstantes.CODIGO_CATALOGO_TIPOS_CHOFERES);
			this.vehiculoChoferDAO.guardarActualizarVehiculoChofer(vehiculoChoferDTO);
		}
		catch (ERPException e) {
			throw new ERPException("Error, "+e.getMessage());
		}
		catch (Exception e) {
			throw new ERPException("Error, "+e.getMessage());
		}
	}
}
