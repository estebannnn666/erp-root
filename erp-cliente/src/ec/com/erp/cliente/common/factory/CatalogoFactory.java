package ec.com.erp.cliente.common.factory;

import org.springframework.context.ApplicationContext;

import ec.com.erp.catalogo.servicios.ICatalogoServicio;
import ec.com.erp.cliente.common.exception.ERPException;

public class CatalogoFactory extends ERPSpringContextFactory {

	public static String[] contexto = new String[]{		
	};

	public CatalogoFactory(ApplicationContext appCtx) {
		appCTx = appCtx;
	}


	/**
	 * Devuelve los servicios de empleado
	 * @return
	 * @throws ERPException
	 */
	public ICatalogoServicio getCatalogoServicio() throws ERPException{
		return (ICatalogoServicio)getBean(ERPFactoryConstantes.CATALOGO_SERVICE);
	}
	
}
