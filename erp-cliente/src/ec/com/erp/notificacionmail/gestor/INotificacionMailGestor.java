package ec.com.erp.notificacionmail.gestor;

import ec.com.erp.cliente.common.exception.ERPException;


/**
 * @author Esteban Gudino
 *
 */

public interface INotificacionMailGestor {
	
	/**
	 * M\u00e9todo para enviar mail factura.
	 * @return 
	 * @throws ERPException
	 */
	void enviarFacturaMail(String para, byte[] invoice) throws ERPException;
	
}
