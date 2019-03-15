package ec.com.erp.cliente.common.factory;

import org.springframework.context.ApplicationContext;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.impuesto.servicios.IImpuestoServicio;

public class ImpuestoFactory extends ERPSpringContextFactory {

	public static String[] contexto = new String[]{		
	};

	public ImpuestoFactory(ApplicationContext appCtx) {
		appCTx = appCtx;
	}


	/**
	 * Devuelve los servicios de proveedor
	 * @return
	 * @throws ERPException
	 */
	public IImpuestoServicio getImpuestoServicio() throws ERPException{
		return (IImpuestoServicio)getBean(ERPFactoryConstantes.IMPUESTOS_SERVICE);
	}
	
}
