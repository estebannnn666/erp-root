package ec.com.erp.usuarios.gestor;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.UsuariosDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IUsuariosGestor {
	
	/**
	 * M\u00e9todo para obtener lista de usuarios
	 * @param nombreUsuario
	 * @return 
	 * @throws ERPException
	 */
	Collection<UsuariosDTO> obtenerListaUsuarios(String nombreUsuario) throws ERPException;
	
	/**
	 * M\u00e9todo para logearse 
	 * @param nombreUsuario
	 * @param password
	 * @return
	 * @throws ERPException
	 */
	UsuariosDTO loginUser(String nombreUsuario, String password) throws ERPException;
	
	/**
	 * M\u00e9todo para crear nuevos usuarios
	 * @param usuarioDTO
	 * @throws ERPException
	 */
	void crearUsuario(UsuariosDTO usuarioDTO) throws ERPException;
	
	/**
	 * M\u00e9todo para crear actualizar usuarios
	 * @param usuarioDTO
	 * @throws ERPException
	 */
	void actualizarUsuario(UsuariosDTO usuarioDTO) throws ERPException;
	
	/**
	 * M\u009etodo para guardar y actualizar usuario
	 * @param usuarioDTO
	 * @throws ERPException
	 */
	void guardarActualizarUsuarios(UsuariosDTO usuarioDTO) throws ERPException;
	
}
