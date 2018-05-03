package ec.com.erp.secuencia.servicios;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.SecuenciaDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface ISecuenciaServicio {
	
	/**
	 * M\u00e9todo para obtener secuencia por nombre
	 * @param nombreSecuencia
	 * @return
	 * @throws ERPException
	 */
	SecuenciaDTO findObtenerSecuenciaByNombre(String nombreSecuencia) throws ERPException;
	
}
