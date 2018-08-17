package ec.com.erp.modulos.servicios;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ModuloDTO;
import ec.com.erp.modulos.gestor.IModuloGestor;

public class ModuloServicio implements IModuloServicio{
	
	private IModuloGestor moduloGestor;

	public IModuloGestor getModuloGestor() {
		return moduloGestor;
	}

	public void setModuloGestor(IModuloGestor moduloGestor) {
		this.moduloGestor = moduloGestor;
	}

	/**
	 * M\u00e9todo para obtener lista de modulos
	 * @param nombreModulo
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Collection<ModuloDTO> findObtenerListaModulos(String nombreModulo) throws ERPException{
		return this.moduloGestor.obtenerListaModulos(nombreModulo);
	}
	
	/**
	 * M\u00e9todo para obtener lista de modulos activos
	 * @param nombreModulo
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Collection<ModuloDTO> findObtenerListaModulosActivos(String nombreModulo) throws ERPException{
		return this.moduloGestor.obtenerListaModulosActivos(nombreModulo);
	}

	/**
	 * M\u00e9todo para crear o actualizar 
	 * @param contactoDTO
	 * @throws ERPException
	 */
	@Override
	public void transCrearActualizarModulo(ModuloDTO moduloDTO)throws ERPException{
		this.moduloGestor.crearActualizarModulo(moduloDTO);
	}
}
