package ec.com.erp.cliente.common.factory;

import org.springframework.context.ApplicationContext;

import ec.com.erp.articulo.servicios.IArticuloServicio;
import ec.com.erp.cliente.common.exception.ERPException;

public class ArticulosFactory extends ERPSpringContextFactory {

	public static String[] contexto = new String[]{		
	};

	public ArticulosFactory(ApplicationContext appCtx) {
		appCTx = appCtx;
	}


	/**
	 * Devuelve los servicios de empleado
	 * @return
	 * @throws ERPException
	 */
	public IArticuloServicio getArticuloServicio() throws ERPException{
		return (IArticuloServicio)getBean(ERPFactoryConstantes.ARTICULO_SERVICE);
	}
	
}
