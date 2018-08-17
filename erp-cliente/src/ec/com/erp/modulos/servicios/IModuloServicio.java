package ec.com.erp.modulos.servicios;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ModuloDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IModuloServicio {
	
	/**
	 * M\u00e9todo para obtener lista de modulos
	 * @param nombreModulo
	 * @return
	 * @throws ERPException
	 */
	Collection<ModuloDTO> findObtenerListaModulos(String nombreModulo) throws ERPException;
	
	/**
	 * M\u00e9todo para obtener lista de modulos activos
	 * @param nombreModulo
	 * @return
	 * @throws ERPException
	 */
	Collection<ModuloDTO> findObtenerListaModulosActivos(String nombreModulo) throws ERPException;
	
	/**
	 * M\u00e9todo para crear o actualizar 
	 * @param contactoDTO
	 * @throws ERPException
	 */
	void transCrearActualizarModulo(ModuloDTO moduloDTO)throws ERPException;
}
