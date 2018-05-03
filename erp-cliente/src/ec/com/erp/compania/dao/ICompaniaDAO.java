package ec.com.erp.compania.dao;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.CompaniaDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface ICompaniaDAO {
	
	/**
	 * M\u00e9todo para obtener lista de companias
	 * @return 
	 * @throws ERPException
	 */
	Collection<CompaniaDTO> obtenerListaCompanias() throws ERPException;
	
	/**
	 * M\u00e9todo para obtener la compania por codigo enviado como parametro
	 * @param codigoCompania
	 * @return
	 * @throws ERPException
	 */
	public CompaniaDTO obtenerListaCompaniasByCodigo(Integer codigoCompania) throws ERPException;
	
}
