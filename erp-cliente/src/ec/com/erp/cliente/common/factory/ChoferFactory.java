package ec.com.erp.cliente.common.factory;

import org.springframework.context.ApplicationContext;

import ec.com.erp.chofer.servicios.IChoferServicio;
import ec.com.erp.cliente.common.exception.ERPException;

public class ChoferFactory extends ERPSpringContextFactory {

	public static String[] contexto = new String[]{		
	};

	public ChoferFactory(ApplicationContext appCtx) {
		appCTx = appCtx;
	}


	/**
	 * Devuelve los servicios de chofer
	 * @return
	 * @throws ERPException
	 */
	public IChoferServicio getChoferServicio() throws ERPException{
		return (IChoferServicio)getBean(ERPFactoryConstantes.CHOFER_SERVICE);
	}
	
}
