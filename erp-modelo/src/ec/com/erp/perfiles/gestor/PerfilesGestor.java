package ec.com.erp.perfiles.gestor;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.PerfilDTO;
import ec.com.erp.perfiles.dao.IPerfilesDAO;

public class PerfilesGestor implements IPerfilesGestor{

	private IPerfilesDAO perfilesDAO;

	public IPerfilesDAO getPerfilesDAO() {
		return perfilesDAO;
	}

	public void setPerfilesDAO(IPerfilesDAO perfilesDAO) {
		this.perfilesDAO = perfilesDAO;
	}

	/**
	 * M\u00e9todo para obtener lista de perfiles
	 * @return 
	 * @throws ERPException
	 */
	@Override
	public Collection<PerfilDTO> obtenerListaPerfiles() throws ERPException{
		return this.perfilesDAO.obtenerListaPerfiles();
	}
}
