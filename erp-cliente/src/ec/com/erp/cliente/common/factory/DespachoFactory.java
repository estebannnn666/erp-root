package ec.com.erp.cliente.common.factory;

import org.springframework.context.ApplicationContext;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.guiadespacho.servicios.IGuiaDespachoServicio;

public class DespachoFactory extends ERPSpringContextFactory {

	public static String[] contexto = new String[]{		
	};

	public DespachoFactory(ApplicationContext appCtx) {
		appCTx = appCtx;
	}


	/**
	 * Devuelve los servicios de guia de despacho
	 * @return
	 * @throws ERPException
	 */
	public IGuiaDespachoServicio getGuiaDespachoServicio() throws ERPException{
		return (IGuiaDespachoServicio)getBean(ERPFactoryConstantes.DESPACHO_SERVICE);
	}
	
}
