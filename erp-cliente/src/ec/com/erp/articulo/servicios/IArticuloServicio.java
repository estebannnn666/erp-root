package ec.com.erp.articulo.servicios;

import java.io.IOException;
import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ArticuloDTO;
import ec.com.erp.cliente.mdl.dto.ArticuloImpuestoDTO;
import ec.com.erp.cliente.mdl.dto.ArticuloUnidadManejoDTO;

public interface IArticuloServicio {
	
	/**
	 * M\u00e9todo para obtener lista de articulos
	 * @return 
	 * @throws ERPException
	 */
	Collection<ArticuloDTO> findObtenerListaArticulos(Integer codigoCompania, String codigoBarras, String nombreArticulo) throws ERPException;
	
	/**
	 * M\u00e9todo para obtener articulo por id
	 * @return 
	 * @throws ERPException
	 */
	ArticuloDTO findObtenerListaArticuloById(Integer codigoCompania, Integer codigoArticulo) throws ERPException;
	
	/**
	 * Metodo para guardar y actualizar articulos
	 * @param articuloDTO
	 * @param articuloImpuestoDTOCols
	 * @throws ERPException
	 */
	void transGuardarActualizarArticulo(ArticuloDTO articuloDTO, Collection<ArticuloImpuestoDTO> articuloImpuestoDTOCols, Collection<ArticuloUnidadManejoDTO> articuloUnidadManejoDTONewCols) throws ERPException;
	
	/**
	 * Metodo para obtener imagen del articulo
	 * @param codigoCompania
	 * @param codigoArticulo
	 * @return
	 */
	byte[] findObtenerImagen(Integer codigoCompania, Integer codigoArticulo) throws ERPException;
	
	/**
	 * Devuelve html de reporte de catalogo de articulos
	 * @param articuloDTOCols
	 * @return
	 * @throws ERPException
	 */
	String findObtenerXMLReporteCatalogo(Collection<ArticuloDTO> articuloDTOCols) throws ERPException;
	
	/**
	 * M\u00e9todo para obtener lista de articulos para catalogos
	 * @return 
	 * @throws ERPException
	 */
	Collection<ArticuloDTO> findObtenerArticulosCatalogos(Integer codigoCompania, String codigoBarras, String nombreArticulo) throws ERPException;
	
	/**
	 * Obtener bytes reporte generado
	 * @param articuloDTOCols
	 * @return
	 * @throws IOException
	 */
	byte[] findObtenerReporteCatalogo(Collection<ArticuloDTO> articuloDTOCols) throws IOException;
	
	/**
	 * M\u00e9todo para obtener lista de unidades de manejo por codigo de barras
	 * @return 
	 * @throws ERPException
	 */
	Collection<ArticuloUnidadManejoDTO> findObtenerListaUnidadManejoByCodigoBarras(Integer codigoCompania, String codigoBarras) throws ERPException;
}
