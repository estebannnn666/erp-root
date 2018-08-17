package ec.com.erp.cliente.common.factory;

import org.springframework.context.ApplicationContext;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.modulos.servicios.IModuloServicio;

public class ModuloFactory extends ERPSpringContextFactory {

	public static String[] contexto = new String[]{		
	};

	public ModuloFactory(ApplicationContext appCtx) {
		appCTx = appCtx;
	}

	/**
	 * Devuelve los servicios de modulos
	 * @return
	 * @throws ERPException
	 */
	public IModuloServicio getModuloServicio() throws ERPException{
		return (IModuloServicio)getBean(ERPFactoryConstantes.MODULOS_SERVICE);
	}
}
