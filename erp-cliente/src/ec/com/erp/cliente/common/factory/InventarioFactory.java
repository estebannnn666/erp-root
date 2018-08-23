package ec.com.erp.cliente.common.factory;

import org.springframework.context.ApplicationContext;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.inventario.servicios.IInventarioServicio;

public class InventarioFactory extends ERPSpringContextFactory {

	public static String[] contexto = new String[]{		
	};

	public InventarioFactory(ApplicationContext appCtx) {
		appCTx = appCtx;
	}


	/**
	 * Devuelve los servicios de inventarios
	 * @return
	 * @throws ERPException
	 */
	public IInventarioServicio getInventarioServicio() throws ERPException{
		return (IInventarioServicio)getBean(ERPFactoryConstantes.INVENTARIOS_SERVICE);
	}
}
