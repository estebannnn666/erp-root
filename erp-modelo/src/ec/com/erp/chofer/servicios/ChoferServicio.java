package ec.com.erp.chofer.servicios;

import java.util.Collection;

import ec.com.erp.chofer.gestor.IChoferGestor;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ChoferDTO;
import ec.com.erp.cliente.mdl.dto.ContactoDTO;

public class ChoferServicio implements IChoferServicio{
	
	private IChoferGestor choferGestor;

	public IChoferGestor getChoferGestor() {
		return choferGestor;
	}

	public void setChoferGestor(IChoferGestor choferGestor) {
		this.choferGestor = choferGestor;
	}

	/**
	 * M\u00e9todo para obtener lista de choferes
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @param nombreChofer
	 * @return Collection<TransportistaDTO>
	 * @throws ERPException
	 */
	@Override
	public Collection<ChoferDTO> findObtenerListaChoferes(Integer codigoCompania, String numeroDocumento, String nombreChofer) throws ERPException{
		return this.choferGestor.obtenerListaChoferes(codigoCompania, numeroDocumento, nombreChofer);
	}
	
	/**
	 * M\u009etodo para guardar y actualizar chofer
	 * @param choferDTO
	 * @param contactoDTO
	 * @throws ERPException
	 */
	@Override
	public void transGuardarActualizarChofer(ChoferDTO choferDTO, ContactoDTO contactoDTO) throws ERPException{
		this.choferGestor.guardarActualizarChofer(choferDTO, contactoDTO);
	}
}
