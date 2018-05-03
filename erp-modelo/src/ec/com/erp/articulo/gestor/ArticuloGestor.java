package ec.com.erp.articulo.gestor;

import java.util.Collection;

import ec.com.erp.articulo.dao.IArticuloDAO;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ArticuloDTO;

public class ArticuloGestor implements IArticuloGestor{

	private IArticuloDAO articuloDAO;
	
	public IArticuloDAO getArticuloDAO() {
		return articuloDAO;
	}

	public void setArticuloDAO(IArticuloDAO articuloDAO) {
		this.articuloDAO = articuloDAO;
	}

	/**
	 * M\u00e9todo para obtener lista de articulos
	 * @return 
	 * @throws ERPException
	 */
	public Collection<ArticuloDTO> obtenerListaArticulos(Integer codigoCompania, String codigoBarras, String nombreArticulo) throws ERPException{
		return this.articuloDAO.obtenerListaArticulos(codigoCompania, codigoBarras, nombreArticulo);
	}
	
	/**
	 * Metodo para guardar y actualizar articulos
	 * @param articuloDTO
	 * @throws ERPException
	 */
	public void transGuardarActualizarArticulo(ArticuloDTO articuloDTO) throws ERPException{
		this.articuloDAO.transGuardarActualizarArticulo(articuloDTO);
	}
}
