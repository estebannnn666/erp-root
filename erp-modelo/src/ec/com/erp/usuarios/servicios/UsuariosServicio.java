package ec.com.erp.usuarios.servicios;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.UsuariosDTO;
import ec.com.erp.usuarios.gestor.IUsuariosGestor;

public class UsuariosServicio implements IUsuariosServicio {

	private IUsuariosGestor usuariosGestor;
	
	public IUsuariosGestor getUsuariosGestor() {
		return usuariosGestor;
	}

	public void setUsuariosGestor(IUsuariosGestor usuariosGestor) {
		this.usuariosGestor = usuariosGestor;
	}

	/**
	 * M\u00e9todo para obtener lista de usuarios
	 * @param nombreUsuario
	 * @return 
	 * @throws ERPException
	 */
	public Collection<UsuariosDTO> findObtenerListaUsuarios(String nombreUsuario) throws ERPException{		
		return this.usuariosGestor.obtenerListaUsuarios(nombreUsuario);
	}
	
	/**
	 * Metodo para logearse 
	 * @param nombreUsuario
	 * @param password
	 * @return
	 * @throws ERPException
	 */
	public UsuariosDTO findLoginUser(String nombreUsuario, String password) throws ERPException{
		return this.usuariosGestor.loginUser(nombreUsuario, password);
	}
	
	/**
	 * M\u009etodo para guardar y actualizar usuario
	 * @param usuarioDTO
	 * @throws ERPException
	 */
	public void transGuardarActualizarUsuarios(UsuariosDTO usuarioDTO) throws ERPException{
		this.usuariosGestor.guardarActualizarUsuarios(usuarioDTO);
	}
}
