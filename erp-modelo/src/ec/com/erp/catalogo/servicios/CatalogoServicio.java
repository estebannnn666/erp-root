package ec.com.erp.catalogo.servicios;

import java.util.Collection;

import ec.com.erp.catalogo.gestor.ICatalogoGestor;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.CatalogoValorDTO;

public class CatalogoServicio implements ICatalogoServicio{
	
	private ICatalogoGestor catalogoGestor;
	
	public ICatalogoGestor getCatalogoGestor() {
		return catalogoGestor;
	}

	public void setCatalogoGestor(ICatalogoGestor catalogoGestor) {
		this.catalogoGestor = catalogoGestor;
	}

	/**
	 * M\u00e9todo para obtener catalogo por tipo
	 * @return 
	 * @throws ERPException
	 */
	public Collection<CatalogoValorDTO> findObtenerCatalogoByTipo(Integer codigoCatalogoTipo) throws ERPException {
		return this.catalogoGestor.obtenerCatalogoByTipo(codigoCatalogoTipo);
	}

}
