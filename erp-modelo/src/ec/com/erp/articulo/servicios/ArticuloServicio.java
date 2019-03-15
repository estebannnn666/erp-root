package ec.com.erp.articulo.servicios;

import java.util.Collection;

import ec.com.erp.articulo.gestor.IArticuloGestor;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ArticuloDTO;
import ec.com.erp.cliente.mdl.dto.ArticuloImpuestoDTO;

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
	public void transGuardarActualizarArticulo(ArticuloDTO articuloDTO, Collection<ArticuloImpuestoDTO> articuloImpuestoDTOCols) throws ERPException{
		this.articuloGestor.guardarActualizarArticulo(articuloDTO, articuloImpuestoDTOCols);
	}
}
