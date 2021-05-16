package ec.com.erp.articulo.servicios;

import java.io.IOException;
import java.util.Collection;

import ec.com.erp.articulo.gestor.IArticuloGestor;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ArticuloDTO;
import ec.com.erp.cliente.mdl.dto.ArticuloImpuestoDTO;
import ec.com.erp.cliente.mdl.dto.ArticuloUnidadManejoDTO;

public class ArticuloServicio implements IArticuloServicio{
	
	private IArticuloGestor articuloGestor;

	public IArticuloGestor getArticuloGestor() {
		return articuloGestor;
	}

	public void setArticuloGestor(IArticuloGestor articuloGestor) {
		this.articuloGestor = articuloGestor;
	}
	
	/**
	 * M\u00e9todo para obtener lista de articulos
	 * @return 
	 * @throws ERPException
	 */
	public Collection<ArticuloDTO> findObtenerListaArticulos(Integer codigoCompania, String codigoBarras, String nombreArticulo) throws ERPException{
		return this.articuloGestor.obtenerListaArticulos(codigoCompania, codigoBarras, nombreArticulo);
	}
	
	/**
	 * M\u00e9todo para obtener articulo por id
	 * @return 
	 * @throws ERPException
	 */
	public ArticuloDTO findObtenerListaArticuloById(Integer codigoCompania, Integer codigoArticulo) throws ERPException{
		return this.articuloGestor.obtenerListaArticuloById(codigoCompania, codigoArticulo);
	}
	
	/**
	 * Metodo para guardar y actualizar articulos
	 * @param articuloDTO
	 * @param articuloImpuestoDTOCols
	 * @throws ERPException
	 */
	public void transGuardarActualizarArticulo(ArticuloDTO articuloDTO, Collection<ArticuloImpuestoDTO> articuloImpuestoDTOCols, Collection<ArticuloUnidadManejoDTO> articuloUnidadManejoDTONewCols) throws ERPException{
		this.articuloGestor.guardarActualizarArticulo(articuloDTO, articuloImpuestoDTOCols, articuloUnidadManejoDTONewCols);
	}
	
	/**
	 * Metodo para obtener imagen del articulo
	 * @param codigoCompania
	 * @param codigoArticulo
	 * @return
	 */
	@Override
	public byte[] findObtenerImagen(Integer codigoCompania, Integer codigoArticulo) throws ERPException{
		return this.articuloGestor.obtenerImagen(codigoCompania, codigoArticulo);
	}
	
	/**
	 * Devuelve html de reporte de catalogo de articulos
	 * @param articuloDTOCols
	 * @return
	 * @throws ERPException
	 */
	@Override
	public String findObtenerXMLReporteCatalogo(Collection<ArticuloDTO> articuloDTOCols) throws ERPException{
		return this.articuloGestor.procesarXMLReporteCatalogo(articuloDTOCols);
	}
	
	/**
	 * M\u00e9todo para obtener lista de articulos para catalogos
	 * @return 
	 * @throws ERPException
	 */
	@Override
	public Collection<ArticuloDTO> findObtenerArticulosCatalogos(Integer codigoCompania, String codigoBarras, String nombreArticulo) throws ERPException{
		return this.articuloGestor.obtenerArticulosCatalogos(codigoCompania, codigoBarras, nombreArticulo);
	}
	
	/**
	 * Obtener bytes reporte generado
	 * @param articuloDTOCols
	 * @return
	 * @throws IOException
	 */
	public byte[] findObtenerReporteCatalogo(Collection<ArticuloDTO> articuloDTOCols) throws IOException {
		return this.articuloGestor.generateReportCatalogo(articuloDTOCols);
	}
	
	/**
	 * M\u00e9todo para obtener lista de unidades de manejo por codigo de barras
	 * @return 
	 * @throws ERPException
	 */
	@Override
	public Collection<ArticuloUnidadManejoDTO> findObtenerListaUnidadManejoByCodigoBarras(Integer codigoCompania, String codigoBarras) throws ERPException{
		return this.articuloGestor.obtenerListaUnidadManejoByCodigoBarras(codigoCompania, codigoBarras);
	}
}
