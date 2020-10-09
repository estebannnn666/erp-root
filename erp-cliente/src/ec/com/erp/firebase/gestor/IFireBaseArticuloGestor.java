package ec.com.erp.firebase.gestor;

import ec.com.erp.cliente.common.exception.ERPException;


/**
 * @author Esteban Gudino
 *
 */

public interface IFireBaseArticuloGestor {
	
	/**
	 * M\u00e9todo para descargar los articulos de fire base
	 * @return 
	 * @throws ERPException
	 */
	void descargarArticulosFireBase() throws ERPException;	
	
	
	/**
	 * M\u00e9todo para guardar los articulos locales a fire base
	 * @return 
	 * @throws ERPException
	 */
	void guardarArticulosFireBase() throws ERPException;	
}
