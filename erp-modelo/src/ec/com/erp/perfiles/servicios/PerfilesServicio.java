package ec.com.erp.perfiles.servicios;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.PerfilDTO;
import ec.com.erp.perfiles.gestor.IPerfilesGestor;

public class PerfilesServicio implements IPerfilesServicio{
	
	private IPerfilesGestor perfilesGestor;

	public IPerfilesGestor getPerfilesGestor() {
		return perfilesGestor;
	}

	public void setPerfilesGestor(IPerfilesGestor perfilesGestor) {
		this.perfilesGestor = perfilesGestor;
	}

	/**
	 * M\u00e9todo para obtener lista de perfiles
	 * @param parametro para buscar por nombre de perfil
	 * @return 
	 * @throws ERPException
	 */
	@Override
	public Collection<PerfilDTO> findObtenerListaPerfiles(String nombrePerfil) throws ERPException{
		return this.perfilesGestor.obtenerListaPerfiles(nombrePerfil);
	}
	
	/**
	 * M\u00e9todo para crear o actualizar 
	 * @param perfilDTO
	 * @throws ERPException
	 */
	@Override
	public void transCrearActualizarPerfil(PerfilDTO perfilDTO)throws ERPException{
		this.perfilesGestor.crearActualizarPerfil(perfilDTO);
	}

}
