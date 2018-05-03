package ec.com.erp.perfiles.servicios;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.PerfilDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IPerfilesServicio {
	
	/**
	 * M\u00e9todo para obtener lista de perfiles
	 * @return 
	 * @throws ERPException
	 */
	Collection<PerfilDTO> findObtenerListaPerfiles() throws ERPException;
	
}
