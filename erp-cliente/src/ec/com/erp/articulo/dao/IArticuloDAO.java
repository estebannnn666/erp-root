package ec.com.erp.articulo.dao;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ArticuloDTO;
import ec.com.erp.cliente.mdl.dto.ArticuloImpuestoDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IArticuloDAO {
	
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
	 * @throws ERPException
	 */
	void guardarActualizarArticulo(ArticuloDTO articuloDTO) throws ERPException;
	
	/**
	 * M\u00e9todo para obtener lista de impuestos por articulo
	 * @return 
	 * @throws ERPException
	 */
	Collection<ArticuloImpuestoDTO> obtenerListaArticuloImpuesto(Integer codigoCompania, Integer codigoArticulo) throws ERPException;
	
	/**
	 * Metodo para guardar y actualizar articulo impuesto
	 * @param articuloImpuestoDTO
	 * @throws ERPException
	 */
	void guardarActualizarArticuloImpuesto(ArticuloImpuestoDTO articuloImpuestoDTO) throws ERPException;
	
}
