package ec.com.erp.unidadmanejo.dao;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ArticuloUnidadManejoDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IUnidadManejoDAO {
	
	/**
	 * M\u00e9todo para obtener lista de ArticuloUnidadManejoDTO.
	 * @return 
	 * @throws ERPException
	 */
	Collection<ArticuloUnidadManejoDTO> obtenerListaArticulosUnidadManejo(Integer codigoCompania, Integer codigoArticulo) throws ERPException;
	
	/**
	 * Metodo para guardar y actualizar articulo unidad manejo
	 * @param articuloDTO
	 * @throws ERPException
	 */
	void guardarActualizarUnidadManejo(ArticuloUnidadManejoDTO articuloUnidadManejoDTO) throws ERPException;
	
}
