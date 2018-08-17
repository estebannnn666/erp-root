package ec.com.erp.modulos.gestor;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ModuloDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IModuloGestor {
	
	/**
	 * M\u00e9todo para obtener lista de modulos
	 * @param nombreModulo
	 * @return
	 * @throws ERPException
	 */
	Collection<ModuloDTO> obtenerListaModulos(String nombreModulo) throws ERPException;
	
	/**
	 * M\u00e9todo para obtener lista de modulos activos
	 * @param nombreModulo
	 * @return
	 * @throws ERPException
	 */
	Collection<ModuloDTO> obtenerListaModulosActivos(String nombreModulo) throws ERPException;
	
	/**
	 * M\u00e9todo para crear o actualizar 
	 * @param contactoDTO
	 * @throws ERPException
	 */
	void crearActualizarModulo(ModuloDTO moduloDTO)throws ERPException;
	
}
