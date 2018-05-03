package ec.com.erp.contacto.gestor;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ContactoDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IContactoGestor {
	
	/**
	 * M\u00e9todo para obtener el contacto por persona o empresa
	 * @param codigoCompania
	 * @param codigoPersona
	 * @param codigoEmpresa
	 * @return
	 * @throws ERPException
	 */
	ContactoDTO obtenerListaContactos(Integer codigoCompania, Long codigoPersona, Long codigoEmpresa) throws ERPException;
	
	/**
	 * M\u00e9todo para crear o actualizar 
	 * @param contactoDTO
	 * @throws ERPException
	 */
	void crearActualizarContacto(ContactoDTO contactoDTO)throws ERPException;
	
}
