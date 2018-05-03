package ec.com.erp.catalogo.gestor;

import java.util.Collection;

import ec.com.erp.catalogo.dao.ICatalogoDAO;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.CatalogoValorDTO;

public class CatalogoGestor implements ICatalogoGestor{

	private ICatalogoDAO catalogoDAO;
	
	public ICatalogoDAO getCatalogoDAO() {
		return catalogoDAO;
	}

	public void setCatalogoDAO(ICatalogoDAO catalogoDAO) {
		this.catalogoDAO = catalogoDAO;
	}

	/**
	 * M\u00e9todo para obtener catalogo por tipo
	 * @return 
	 * @throws ERPException
	 */
	public Collection<CatalogoValorDTO> obtenerCatalogoByTipo(Integer codigoCatalogoTipo) throws ERPException{
		return this.catalogoDAO.obtenerCatalogoByTipo(codigoCatalogoTipo);
	}
}
