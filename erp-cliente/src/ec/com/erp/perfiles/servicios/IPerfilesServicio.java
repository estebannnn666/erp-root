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
	 * @param parametro para buscar por nombre de perfil
	 * @return 
	 * @throws ERPException
	 */
	Collection<PerfilDTO> findObtenerListaPerfiles(String nombrePerfil) throws ERPException;
	
	/**
	 * M\u00e9todo para crear o actualizar 
	 * @param perfilDTO
	 * @throws ERPException
	 */
	void transCrearActualizarPerfil(PerfilDTO perfilDTO)throws ERPException;
}
