package ec.com.erp.cliente.common.factory;

import org.springframework.context.ApplicationContext;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.proveedor.servicios.IProveedorServicio;

public class ProveedorFactory extends ERPSpringContextFactory {

	public static String[] contexto = new String[]{		
	};

	public ProveedorFactory(ApplicationContext appCtx) {
		appCTx = appCtx;
	}


	/**
	 * Devuelve los servicios de proveedor
	 * @return
	 * @throws ERPException
	 */
	public IProveedorServicio getProveedorServicio() throws ERPException{
		return (IProveedorServicio)getBean(ERPFactoryConstantes.PROVEEDOR_SERVICE);
	}
	
}
