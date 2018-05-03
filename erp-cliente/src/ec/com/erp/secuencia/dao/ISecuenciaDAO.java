package ec.com.erp.secuencia.dao;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.SecuenciaDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface ISecuenciaDAO {
	
	/**
	 * M\u00e9todo para obtener secuencia por nombre
	 * @param nombreSecuencia
	 * @return
	 * @throws ERPException
	 */
	SecuenciaDTO obtenerSecuenciaByNombre(String nombreSecuencia) throws ERPException;
	
	/**
	 * Metodo para actualizar el valor de la secuencia
	 * @param articuloDTO
	 * @throws ERPException
	 */
	void guardarActualizarSecuencia(SecuenciaDTO secuenciaDTO) throws ERPException;
	
	/**
	 * Metodo para obtenere el secuencial para cualquier tabla
	 * @param nombreSecuencia
	 * @return
	 * @throws ERPException
	 */
	Integer obtenerSecuencialTabla(String nombreSecuencia) throws ERPException;
	
}
