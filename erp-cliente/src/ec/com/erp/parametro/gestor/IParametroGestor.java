package ec.com.erp.parametro.gestor;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ParametroDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IParametroGestor {
	
	/**
	 * M\u00e9todo para obtener lista de parametros.
	 * @return 
	 * @throws ERPException
	 */
	Collection<ParametroDTO> obtenerParametrosByCodigos(Collection<String> codigosParametros) throws ERPException;
	
	/**
	 * M\u00e9todo para obtener parametro por codigo.
	 * @return 
	 * @throws ERPException
	 */
	ParametroDTO obtenerParametroByCodigo(String codigoParametro) throws ERPException;
}
