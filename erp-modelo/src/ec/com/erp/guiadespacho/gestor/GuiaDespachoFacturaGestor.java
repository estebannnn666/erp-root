/**
 * 
 */
package ec.com.erp.guiadespacho.gestor;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.GuiaDespachoFacturaDTO;
import ec.com.erp.guiadespacho.dao.IGuiaDespachoFacturaDAO;

/**
 * @author Esteban Gudino
 * 2018-05-05
 */
public class GuiaDespachoFacturaGestor implements IGuiaDespachoFacturaGestor {

	private IGuiaDespachoFacturaDAO guiaDespachoFacturaDAO;

	public IGuiaDespachoFacturaDAO getGuiaDespachoFacturaDAO() {
		return guiaDespachoFacturaDAO;
	}

	public void setGuiaDespachoFacturaDAO(IGuiaDespachoFacturaDAO guiaDespachoFacturaDAO) {
		this.guiaDespachoFacturaDAO = guiaDespachoFacturaDAO;
	}

	/**
	 * M\u00e9todo para obtener lista de facturas por guia de despacho
	 * @param codigoCompania
	 * @param numeroGuia
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Collection<GuiaDespachoFacturaDTO> obtenerListaGuiaDespachoFacturasByNumeroGuiaDespacho(Integer codigoCompania, String numeroGuia) throws ERPException{
		return this.guiaDespachoFacturaDAO.obtenerListaGuiaDespachoFacturasByNumeroGuiaDespacho(codigoCompania, numeroGuia);
	}
	
	/**
	 * M\u00e9todo para guardar y actualizar factura guia despacho
	 * @param guiaDespachoFacturaDTO
	 * @throws ERPException
	 */
	@Override
	public void crearActualizarGuiaDespachoFacturas(GuiaDespachoFacturaDTO guiaDespachoFacturaDTO) throws ERPException{
		this.guiaDespachoFacturaDAO.crearActualizarGuiaDespachoFacturas(guiaDespachoFacturaDTO);
	}

}
