package ec.com.erp.cliente.common.factory;

import org.springframework.context.ApplicationContext;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.transportistas.servicios.ITransportistasServicio;

public class TransportistaFactory extends ERPSpringContextFactory {

	public static String[] contexto = new String[]{		
	};

	public TransportistaFactory(ApplicationContext appCtx) {
		appCTx = appCtx;
	}


	/**
	 * Devuelve los servicios de transportista
	 * @return
	 * @throws ERPException
	 */
	public ITransportistasServicio getTransportistasServicio() throws ERPException{
		return (ITransportistasServicio)getBean(ERPFactoryConstantes.TRANSPORTISTA_SERVICE);
	}
	
}
