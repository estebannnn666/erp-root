package ec.com.erp.usuarios.gestor;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.UsuariosDTO;
import ec.com.erp.usuarios.dao.IUsuariosDAO;

public class UsuariosGestor implements IUsuariosGestor {

	private IUsuariosDAO usuariosDAO;
	
	public IUsuariosDAO getUsuariosDAO() {
		return usuariosDAO;
	}

	public void setUsuariosDAO(IUsuariosDAO usuariosDAO) {
		this.usuariosDAO = usuariosDAO;
	}

	/**
	 * M\u00e9todo para obtener lista de usuarios
	 * @return 
	 * @throws ERPException
	 */
	public Collection<UsuariosDTO> obtenerListaUsuarios() throws ERPException{
		return this.usuariosDAO.obtenerListaUsuarios();
	}
	
	/**
	 * M\u00e9todo para logearse 
	 * @param nombreUsuario
	 * @param password
	 * @return
	 * @throws ERPException
	 */
	public UsuariosDTO loginUser(String nombreUsuario, String password) throws ERPException{
		return this.usuariosDAO.loginUser(nombreUsuario, password);
	}
	
	/**
	 * M\u00e9todo para crear nuevos usuarios
	 * @param usuarioDTO
	 * @throws ERPException
	 */
	public void crearUsuario(UsuariosDTO usuarioDTO) throws ERPException{
		this.usuariosDAO.crearUsuario(usuarioDTO);
	}
	
	/**
	 * M\u00e9todo para crear actualizar usuarios
	 * @param usuarioDTO
	 * @throws ERPException
	 */
	public void actualizarUsuario(UsuariosDTO usuarioDTO) throws ERPException{
		this.usuariosDAO.actualizarUsuario(usuarioDTO);
	}
}
