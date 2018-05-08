package ec.com.erp.empresa.gestor;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.EmpresaDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IEmpresaGestor {
	
	/**
	 * M\u00E9todo para obtener la empresa por codigo
	 * @param codigoCompania
	 * @param numeroRuc
	 * @return
	 * @throws ERPException
	 */
	EmpresaDTO obtenerEmpresaByCodigo(Integer codigoCompania, String numeroRuc) throws ERPException;
	
	/**
	 * M\u00E9todo para crear o actualizar empresas
	 * @param empresaDTO
	 * @throws ERPException
	 */
	void crearActualizarEmpresa(EmpresaDTO empresaDTO) throws ERPException;
}
