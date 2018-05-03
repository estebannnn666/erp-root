package ec.com.erp.contacto.servicios;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ContactoDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IContactoServicio {
	
	/**
	 * M\u00e9todo para obtener el contacto por persona o empresa
	 * @param codigoCompania
	 * @param codigoPersona
	 * @param codigoEmpresa
	 * @return
	 * @throws ERPException
	 */
	ContactoDTO findObtenerListaContactos(Integer codigoCompania, Long codigoPersona, Long codigoEmpresa) throws ERPException;
	
}
