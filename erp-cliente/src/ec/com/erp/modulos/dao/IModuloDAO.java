package ec.com.erp.modulos.dao;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ModuloDTO;
import ec.com.erp.cliente.mdl.dto.ModuloPerfilDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IModuloDAO {
	
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
	
	/**
	 * M\u00e9todo para crear o actualizar modulo perfil
	 * @param contactoDTO
	 * @throws ERPException
	 */
	void crearActualizarModuloPerfil(ModuloPerfilDTO moduloPerfilDTO)throws ERPException;
}
