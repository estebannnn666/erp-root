package ec.com.erp.perfiles.dao;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.PerfilDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IPerfilesDAO {
	
	/**
	 * M\u00e9todo para obtener lista de perfiles
	 * @param parametro para buscar por nombre de perfil
	 * @return 
	 * @throws ERPException
	 */
	Collection<PerfilDTO> obtenerListaPerfiles(String nombrePerfil) throws ERPException;
	
	/**
	 * M\u00e9todo para crear o actualizar 
	 * @param perfilDTO
	 * @throws ERPException
	 */
	void crearActualizarPerfil(PerfilDTO perfilDTO)throws ERPException;
}
