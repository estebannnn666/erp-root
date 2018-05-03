package ec.com.erp.articulo.servicios;

import java.util.Collection;

import ec.com.erp.articulo.gestor.IArticuloGestor;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ArticuloDTO;

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
	 * Metodo para guardar y actualizar articulos
	 * @param articuloDTO
	 * @throws ERPException
	 */
	public void transGuardarActualizarArticulo(ArticuloDTO articuloDTO) throws ERPException{
		this.articuloGestor.transGuardarActualizarArticulo(articuloDTO);
	}
}
