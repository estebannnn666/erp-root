package ec.com.erp.usuarios.servicios;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.UsuariosDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IUsuariosServicio {
	
	/**
	 * M\u00e9todo para obtener lista de usuarios
	 * @param nombreUsuario
	 * @return 
	 * @throws ERPException
	 */
	Collection<UsuariosDTO> findObtenerListaUsuarios(String nombreUsuario) throws ERPException;	
	
	/**
	 * Metodo para logearse 
	 * @param nombreUsuario
	 * @param password
	 * @return
	 * @throws ERPException
	 */
	UsuariosDTO findLoginUser(String nombreUsuario, String password) throws ERPException;
	
	/**
	 * M\u009etodo para guardar y actualizar usuario
	 * @param usuarioDTO
	 * @throws ERPException
	 */
	void transGuardarActualizarUsuarios(UsuariosDTO usuarioDTO) throws ERPException;
}
