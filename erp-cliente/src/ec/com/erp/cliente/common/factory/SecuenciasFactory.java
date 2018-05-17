package ec.com.erp.cliente.common.factory;

import org.springframework.context.ApplicationContext;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.secuencia.servicios.ISecuenciaServicio;

public class SecuenciasFactory extends ERPSpringContextFactory {

	public static String[] contexto = new String[]{		
	};

	public SecuenciasFactory(ApplicationContext appCtx) {
		appCTx = appCtx;
	}


	/**
	 * Devuelve los servicios de secuencias
	 * @return
	 * @throws ERPException
	 */
	public ISecuenciaServicio getSecuenciaServicio() throws ERPException{
		return (ISecuenciaServicio)getBean(ERPFactoryConstantes.SECUENCIAS_SERVICE);
	}
	
}
