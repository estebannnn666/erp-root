package ec.com.erp.modulos.gestor;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ModuloDTO;
import ec.com.erp.modulos.dao.IModuloDAO;

public class ModuloGestor implements IModuloGestor{

	private IModuloDAO moduloDAO;
	
	public IModuloDAO getModuloDAO() {
		return moduloDAO;
	}

	public void setModuloDAO(IModuloDAO moduloDAO) {
		this.moduloDAO = moduloDAO;
	}

	/**
	 * M\u00e9todo para obtener lista de modulos
	 * @param nombreModulo
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Collection<ModuloDTO> obtenerListaModulos(String nombreModulo) throws ERPException{
		return this.moduloDAO.obtenerListaModulos(nombreModulo);
	}
	
	/**
	 * M\u00e9todo para obtener lista de modulos activos
	 * @param nombreModulo
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Collection<ModuloDTO> obtenerListaModulosActivos(String nombreModulo) throws ERPException{
		return this.moduloDAO.obtenerListaModulosActivos(nombreModulo);
	}

	/**
	 * M\u00e9todo para crear o actualizar 
	 * @param contactoDTO
	 * @throws ERPException
	 */
	@Override
	public void crearActualizarModulo(ModuloDTO moduloDTO)throws ERPException{
		this.moduloDAO.crearActualizarModulo(moduloDTO);
	}
}
