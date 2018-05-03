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
	 * @return 
	 * @throws ERPException
	 */
	@Override
	public Collection<PerfilDTO> findObtenerListaPerfiles() throws ERPException{
		return this.perfilesGestor.obtenerListaPerfiles();
	}

}
