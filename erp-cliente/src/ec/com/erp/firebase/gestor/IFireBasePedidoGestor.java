package ec.com.erp.firebase.gestor;

import ec.com.erp.cliente.common.exception.ERPException;


/**
 * @author Esteban Gudino
 *
 */

public interface IFireBasePedidoGestor {
	
	/**
	 * M\u00e9todo para descargar las facturas de fire base
	 * @return 
	 * @throws ERPException
	 */
	void descargarPedidosFireBase() throws ERPException;	
	
}
