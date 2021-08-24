package ec.com.erp.cliente.common.factory;

import org.springframework.context.ApplicationContext;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.parametro.servicios.IParametroServicio;

public class ParametroFactory extends ERPSpringContextFactory {

	public static String[] contexto = new String[]{		
	};

	public ParametroFactory(ApplicationContext appCtx) {
		appCTx = appCtx;
	}


	/**
	 * Devuelve los servicios de vendedor
	 * @return
	 * @throws ERPException
	 */
	public IParametroServicio getParametroServicio() throws ERPException{
		return (IParametroServicio)getBean(ERPFactoryConstantes.PARAMETRO_SERVICE);
	}
	
}
