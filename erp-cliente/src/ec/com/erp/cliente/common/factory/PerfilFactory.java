package ec.com.erp.cliente.common.factory;

import org.springframework.context.ApplicationContext;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.perfiles.servicios.IPerfilesServicio;

public class PerfilFactory extends ERPSpringContextFactory {

	public static String[] contexto = new String[]{		
	};

	public PerfilFactory(ApplicationContext appCtx) {
		appCTx = appCtx;
	}


	/**
	 * Devuelve los servicios de perfiles
	 * @return
	 * @throws ERPException
	 */
	public IPerfilesServicio getPerfilesServicio() throws ERPException{
		return (IPerfilesServicio)getBean(ERPFactoryConstantes.PERFILES_SERVICE);
	}
	
}
