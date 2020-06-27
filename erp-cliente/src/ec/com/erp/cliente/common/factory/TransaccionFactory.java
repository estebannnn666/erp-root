package ec.com.erp.cliente.common.factory;

import org.springframework.context.ApplicationContext;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.transaccion.servicios.ITransaccionServicio;

public class TransaccionFactory extends ERPSpringContextFactory {

	public static String[] contexto = new String[]{		
	};

	public TransaccionFactory(ApplicationContext appCtx) {
		appCTx = appCtx;
	}


	/**
	 * Devuelve los servicios de transacciones
	 * @return
	 * @throws ERPException
	 */
	public ITransaccionServicio getTransaccionServicio() throws ERPException{
		return (ITransaccionServicio)getBean(ERPFactoryConstantes.TRANSACCION_SERVICE);
	}
	
}
