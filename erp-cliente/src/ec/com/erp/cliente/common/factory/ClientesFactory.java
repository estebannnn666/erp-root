package ec.com.erp.cliente.common.factory;

import org.springframework.context.ApplicationContext;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.clientes.servicios.IClientesServicio;

public class ClientesFactory extends ERPSpringContextFactory {

	public static String[] contexto = new String[]{		
	};

	public ClientesFactory(ApplicationContext appCtx) {
		appCTx = appCtx;
	}


	/**
	 * Devuelve los servicios de empleado
	 * @return
	 * @throws ERPException
	 */
	public IClientesServicio getClientesServicio() throws ERPException{
		return (IClientesServicio)getBean(ERPFactoryConstantes.CLIENTES_SERVICE);
	}
	
}
