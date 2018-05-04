/**
 * 
 */
package ec.com.erp.guiadespacho.gestor;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.GuiaDespachoExtrasDTO;
import ec.com.erp.guiadespacho.dao.IGuiaDespachoExtrasDAO;

/**
 * @author Esteban Gudino
 * 2018-05-05
 */
public class GuiaDespachoExtrasGestor implements IGuiaDespachoExtrasGestor {

	
	private IGuiaDespachoExtrasDAO guiaDespachoExtrasDAO;

	public IGuiaDespachoExtrasDAO getGuiaDespachoExtrasDAO() {
		return guiaDespachoExtrasDAO;
	}

	public void setGuiaDespachoExtrasDAO(IGuiaDespachoExtrasDAO guiaDespachoExtrasDAO) {
		this.guiaDespachoExtrasDAO = guiaDespachoExtrasDAO;
	}

	/**
	 * M\u00e9todo para obtener lista de extras en guia de despacho
	 * @param codigoCompania
	 * @param numeroGuia
	 * @return Collection<GuiaDespachoExtrasDTO>
	 * @throws ERPException
	 */
	@Override
	public Collection<GuiaDespachoExtrasDTO> obtenerListaGuiaDespachoExtrasByNumeroGuia(Integer codigoCompania, String numeroGuia) throws ERPException{
		return this.guiaDespachoExtrasDAO.obtenerListaGuiaDespachoExtrasByNumeroGuia(codigoCompania, numeroGuia);
	}
	
	/**
	 * M\u00e9todo para guardar y actualizar extras guia despacho
	 * @param guiaDespachoExtrasDTO
	 * @throws ERPException
	 */
	@Override
	public void crearActualizarExtrasGuiaDespacho(GuiaDespachoExtrasDTO guiaDespachoExtrasDTO) throws ERPException{
		this.guiaDespachoExtrasDAO.crearActualizarExtrasGuiaDespacho(guiaDespachoExtrasDTO);
	}

}
