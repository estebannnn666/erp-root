package ec.com.erp.catalogo.gestor;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.CatalogoValorDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface ICatalogoGestor {
	
	/**
	 * M\u00e9todo para obtener catalogo por tipo
	 * @return 
	 * @throws ERPException
	 */
	Collection<CatalogoValorDTO> obtenerCatalogoByTipo(Integer codigoCatalogoTipo) throws ERPException;
	
}
