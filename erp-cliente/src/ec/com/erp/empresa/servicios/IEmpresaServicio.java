package ec.com.erp.empresa.servicios;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.EmpresaDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IEmpresaServicio {
	
	/**
	 * M\u00E9todo para obtener la empresa por codigo
	 * @param codigoCompania
	 * @param codigoEmpresa
	 * @return
	 * @throws ERPException
	 */
	EmpresaDTO findObtenerEmpresaByCodigo(Integer codigoCompania, Long codigoEmpresa) throws ERPException;
	
}
