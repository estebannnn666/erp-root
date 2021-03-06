package ec.com.erp.articulo.gestor;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ArticuloDTO;
import ec.com.erp.cliente.mdl.dto.ArticuloImpuestoDTO;
import ec.com.erp.cliente.mdl.dto.ArticuloUnidadManejoDTO;

public interface IArticuloGestor {
	
	/**
	 * M\u00e9todo para obtener lista de articulos
	 * @return 
	 * @throws ERPException
	 */
	Collection<ArticuloDTO> obtenerListaArticulos(Integer codigoCompania, String codigoBarras, String nombreArticulo) throws ERPException;
	
	/**
	 * M\u00e9todo para obtener articulo por id
	 * @return 
	 * @throws ERPException
	 */
	ArticuloDTO obtenerListaArticuloById(Integer codigoCompania, Integer codigoArticulo) throws ERPException;
	
	/**
	 * Metodo para guardar y actualizar articulos
	 * @param articuloDTO
	 * @param articuloImpuestoDTONewCols
	 * @throws ERPException
	 */
	void guardarActualizarArticulo(ArticuloDTO articuloDTO, Collection<ArticuloImpuestoDTO> articuloImpuestoDTONewCols, Collection<ArticuloUnidadManejoDTO> articuloUnidadManejoDTONewCols) throws ERPException;
}
