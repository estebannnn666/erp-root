/**
 * 
 */
package ec.com.erp.guiadespacho.gestor;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.GuiaDespachoDetalleDTO;
import ec.com.erp.guiadespacho.dao.IGuiaDespachoDetalleDAO;

/**
 * @author Esteban Gudino
 * 2018-05-05
 */
public class GuiaDespachoDetalleGestor implements IGuiaDespachoDetalleGestor {

	private IGuiaDespachoDetalleDAO guiaDespachoDetalleDAO;

	public IGuiaDespachoDetalleDAO getGuiaDespachoDetalleDAO() {
		return guiaDespachoDetalleDAO;
	}

	public void setGuiaDespachoDetalleDAO(IGuiaDespachoDetalleDAO guiaDespachoDetalleDAO) {
		this.guiaDespachoDetalleDAO = guiaDespachoDetalleDAO;
	}

	/**
	 * M\u00e9todo para obtener lista de extras en guia de despacho
	 * @param codigoCompania
	 * @param numeroGuia
	 * @return Collection<GuiaDespachoDetalleDTO>
	 * @throws ERPException
	 */
	@Override
	public Collection<GuiaDespachoDetalleDTO> obtenerListaGuiaDespachoDetalleByNumeroGuia(Integer codigoCompania, String numeroGuia) throws ERPException{
		return this.guiaDespachoDetalleDAO.obtenerListaGuiaDespachoDetalleByNumeroGuia(codigoCompania, numeroGuia);
	}
	
	/**
	 * M\u00e9todo para guardar y actualizar extras guia despacho
	 * @param guiaDespachoDetalleDTO
	 * @throws ERPException
	 */
	@Override
	public void crearActualizarDetalleGuiaDespacho(GuiaDespachoDetalleDTO guiaDespachoDetalleDTO) throws ERPException{
		this.guiaDespachoDetalleDAO.crearActualizarDetalleGuiaDespacho(guiaDespachoDetalleDTO);
	}

}
