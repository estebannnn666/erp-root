package ec.com.erp.cliente.common.factory;

import org.springframework.context.ApplicationContext;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.notacredito.servicios.INotaCreditoServicio;

public class NotasCreditoFactory extends ERPSpringContextFactory {

	public static String[] contexto = new String[]{		
	};

	public NotasCreditoFactory(ApplicationContext appCtx) {
		appCTx = appCtx;
	}


	/**
	 * Devuelve los servicios de proveedor
	 * @return
	 * @throws ERPException
	 */
	public INotaCreditoServicio getNotaCreditoServicio() throws ERPException{
		return (INotaCreditoServicio)getBean(ERPFactoryConstantes.NOTA_CREDITO_SERVICE);
	}
	
}
