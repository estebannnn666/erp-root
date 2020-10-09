package ec.com.erp.cliente.common.factory;

import org.springframework.context.ApplicationContext;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.firebase.servicios.IFireBaseServicio;

public class FireBaseFactory extends ERPSpringContextFactory {

	public static String[] contexto = new String[]{		
	};

	public FireBaseFactory(ApplicationContext appCtx) {
		appCTx = appCtx;
	}


	/**
	 * Devuelve los servicios de vendedor
	 * @return
	 * @throws ERPException
	 */
	public IFireBaseServicio getFireBaseServicio() throws ERPException{
		return (IFireBaseServicio)getBean(ERPFactoryConstantes.FIRE_BASE_SERVICE);
	}
	
}
