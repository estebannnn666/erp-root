package ec.com.erp.unidadmanejo.gestor;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ArticuloUnidadManejoDTO;
import ec.com.erp.unidadmanejo.dao.IUnidadManejoDAO;

public class UnidadManejoGestor implements IUnidadManejoGestor{

	private IUnidadManejoDAO unidadManejoDAO;
	
	public IUnidadManejoDAO getUnidadManejoDAO() {
		return unidadManejoDAO;
	}

	public void setUnidadManejoDAO(IUnidadManejoDAO unidadManejoDAO) {
		this.unidadManejoDAO = unidadManejoDAO;
	}

	/**
	 * M\u00e9todo para obtener lista de unidades de manejo
	 * @return 
	 * @throws ERPException
	 */
	@Override
	public Collection<ArticuloUnidadManejoDTO> obtenerListaArticulosUnidadManejo(Integer codigoCompania, Integer codigoArticulo) throws ERPException{
		return this.unidadManejoDAO.obtenerListaArticulosUnidadManejo(codigoCompania, codigoArticulo);
	}
	
	/**
	 * Metodo para guardar y actualizar unidad de manejo de articulo
	 * @param articuloUnidadManejo
	 * @throws ERPException
	 */
	@Override
	public void guardarActualizarUnidadManejo(ArticuloUnidadManejoDTO articuloUnidadManejo) throws ERPException{
		this.unidadManejoDAO.guardarActualizarUnidadManejo(articuloUnidadManejo); 
	}
}
