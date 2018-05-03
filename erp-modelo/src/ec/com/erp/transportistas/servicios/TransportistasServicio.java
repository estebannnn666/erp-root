package ec.com.erp.transportistas.servicios;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ContactoDTO;
import ec.com.erp.cliente.mdl.dto.TransportistaDTO;
import ec.com.erp.transportistas.gestor.ITransportistasGestor;

public class TransportistasServicio implements ITransportistasServicio{
	
	private ITransportistasGestor transportistasGestor;

	public ITransportistasGestor getTransportistasGestor() {
		return transportistasGestor;
	}

	public void setTransportistasGestor(ITransportistasGestor transportistasGestor) {
		this.transportistasGestor = transportistasGestor;
	}

	/**
	 * M\u00e9todo para obtener lista de transportista
	 * @param codigoCompania
	 * @return Collection<TransportistaDTO>
	 * @throws ERPException
	 */
	@Override
	public Collection<TransportistaDTO> findObtenerListaTransportistas(Integer codigoCompania, String numeroDocumento, String razonSocial) throws ERPException{
		return this.transportistasGestor.obtenerListaTransportistas(codigoCompania, numeroDocumento, razonSocial);
	}
	
	/**
	 * M\u009etodo para guardar y actualizar transportista
	 * @param transportistaDTO
	 * @param contactoDTO
	 * @throws ERPException
	 */
	@Override
	public void transGuardarActualizarTransportista(TransportistaDTO transportistaDTO, ContactoDTO contactoDTO) throws ERPException{
		this.transportistasGestor.guardarActualizarTransportista(transportistaDTO, contactoDTO);
	}
}
