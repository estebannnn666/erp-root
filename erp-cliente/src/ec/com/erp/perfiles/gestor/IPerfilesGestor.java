package ec.com.erp.perfiles.gestor;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.PerfilDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IPerfilesGestor {
	
	/**
	 * M\u00e9todo para obtener lista de perfiles
	 * @return 
	 * @throws ERPException
	 */
	Collection<PerfilDTO> obtenerListaPerfiles() throws ERPException;
	
}
