package ec.com.erp.cliente.common.factory;

import org.springframework.context.ApplicationContext;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.notificacionmail.servicios.INotificacionMailServicio;

public class NotificacionMailFactory extends ERPSpringContextFactory {

	public static String[] contexto = new String[]{		
	};

	public NotificacionMailFactory(ApplicationContext appCtx) {
		appCTx = appCtx;
	}


	/**
	 * Devuelve los servicios de vendedor
	 * @return
	 * @throws ERPException
	 */
	public INotificacionMailServicio getNotificacionMailServicio() throws ERPException{
		return (INotificacionMailServicio)getBean(ERPFactoryConstantes.EMAIL_SERVICE);
	}
}
