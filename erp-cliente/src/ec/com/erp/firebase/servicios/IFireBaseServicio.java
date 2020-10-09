package ec.com.erp.firebase.servicios;

import ec.com.erp.cliente.common.exception.ERPException;


/**
 * @author Esteban Gudino
 *
 */

public interface IFireBaseServicio {
	
	/**
	 * M\u00e9todo para descargar los clientes de fire base
	 * @return 
	 * @throws ERPException
	 */
	void transDescargarClientesFireBase() throws ERPException;	
	
	
	/**
	 * M\u00e9todo para guardar los clientes locales a fire base
	 * @return 
	 * @throws ERPException
	 */
	void findGuardarClientesFireBase() throws ERPException;	
	
	/**
	 * M\u00e9todo para descargar los ARTICULOS de fire base
	 * @return 
	 * @throws ERPException
	 */
	void transDescargarArticulosFireBase() throws ERPException;
	
	/**
	 * M\u00e9todo para guardar los articulos locales a fire base
	 * @return 
	 * @throws ERPException
	 */
	void findGuardarArticulosFireBase() throws ERPException;
}
