package ec.com.erp.articulo.servicios;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ArticuloDTO;

public interface IArticuloServicio {
	
	/**
	 * M\u00e9todo para obtener lista de articulos
	 * @return 
	 * @throws ERPException
	 */
	Collection<ArticuloDTO> findObtenerListaArticulos(Integer codigoCompania, String codigoBarras, String nombreArticulo) throws ERPException;
	
	/**
	 * Metodo para guardar y actualizar articulos
	 * @param articuloDTO
	 * @throws ERPException
	 */
	void transGuardarActualizarArticulo(ArticuloDTO articuloDTO) throws ERPException;
	
}
