package ec.com.erp.perfiles.gestor;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ModuloPerfilDTO;
import ec.com.erp.cliente.mdl.dto.PerfilDTO;
import ec.com.erp.modulos.dao.IModuloDAO;
import ec.com.erp.perfiles.dao.IPerfilesDAO;

public class PerfilesGestor implements IPerfilesGestor{

	private IPerfilesDAO perfilesDAO;
	private IModuloDAO moduloDAO;

	public IPerfilesDAO getPerfilesDAO() {
		return perfilesDAO;
	}

	public void setPerfilesDAO(IPerfilesDAO perfilesDAO) {
		this.perfilesDAO = perfilesDAO;
	}

	public IModuloDAO getModuloDAO() {
		return moduloDAO;
	}

	public void setModuloDAO(IModuloDAO moduloDAO) {
		this.moduloDAO = moduloDAO;
	}

	/**
	 * M\u00e9todo para obtener lista de perfiles
	 * @param parametro para buscar por nombre de perfil
	 * @return 
	 * @throws ERPException
	 */
	@Override
	public Collection<PerfilDTO> obtenerListaPerfiles(String nombrePerfil) throws ERPException{
		return this.perfilesDAO.obtenerListaPerfiles(nombrePerfil);
	}
	
	/**
	 * M\u00e9todo para crear o actualizar 
	 * @param perfilDTO
	 * @throws ERPException
	 */
	@Override
	public void crearActualizarPerfil(PerfilDTO perfilDTO)throws ERPException{
		try {
			Collection<ModuloPerfilDTO> moduloPerfilDTOCols = perfilDTO.getModuloPerfilDTOCols();
			this.perfilesDAO.crearActualizarPerfil(perfilDTO);
			for(ModuloPerfilDTO moduloPerfilDTO : moduloPerfilDTOCols) {
				moduloPerfilDTO.getId().setCodigoPerfil(perfilDTO.getId().getCodigoPerfil());
				this.moduloDAO.crearActualizarModuloPerfil(moduloPerfilDTO);
			}
		} catch (Exception e) {
			throw new ERPException("Error,"+e.getMessage());
		}
	}
}
