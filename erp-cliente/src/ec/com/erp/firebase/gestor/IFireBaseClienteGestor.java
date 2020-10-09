package ec.com.erp.firebase.gestor;

import ec.com.erp.cliente.common.exception.ERPException;


/**
 * @author Esteban Gudino
 *
 */

public interface IFireBaseClienteGestor {
	
	/**
	 * M\u00e9todo para descargar los clientes de fire base
	 * @return 
	 * @throws ERPException
	 */
	void descargarClientesFireBase() throws ERPException;	
	
	
	/**
	 * M\u00e9todo para guardar los clientes locales a fire base
	 * @return 
	 * @throws ERPException
	 */
	void guardarClientesFireBase() throws ERPException;	
}
