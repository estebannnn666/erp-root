package ec.com.erp.cliente.common.factory;

import org.springframework.context.ApplicationContext;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.vendedor.servicios.IVendedorServicio;

public class VendedorFactory extends ERPSpringContextFactory {

	public static String[] contexto = new String[]{		
	};

	public VendedorFactory(ApplicationContext appCtx) {
		appCTx = appCtx;
	}


	/**
	 * Devuelve los servicios de vendedor
	 * @return
	 * @throws ERPException
	 */
	public IVendedorServicio getVendedorServicio() throws ERPException{
		return (IVendedorServicio)getBean(ERPFactoryConstantes.VENDEDOR_SERVICE);
	}
	
}
