package ec.com.erp.compania.servicios;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.CompaniaDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface ICompaniaServicio {
	
	/**
	 * M\u00e9todo para obtener lista de companias
	 * @return 
	 * @throws ERPException
	 */
	Collection<CompaniaDTO> findObtenerListaCompanias() throws ERPException;
	
	/**
	 * M\u00e9todo para obtener la compania por codigo enviado como parametro
	 * @param codigoCompania
	 * @return
	 * @throws ERPException
	 */
	public CompaniaDTO findObtenerListaCompaniasByCodigo(Integer codigoCompania) throws ERPException;
	
}
