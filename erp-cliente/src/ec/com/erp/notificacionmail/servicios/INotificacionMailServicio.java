package ec.com.erp.notificacionmail.servicios;

import ec.com.erp.cliente.common.exception.ERPException;


/**
 * @author Esteban Gudino
 *
 */

public interface INotificacionMailServicio {
	
	/**
	 * Method for send mail with invoice.
	 */
	void findEnviarFacturaMail(String para, byte[] invoice) throws ERPException;
}
