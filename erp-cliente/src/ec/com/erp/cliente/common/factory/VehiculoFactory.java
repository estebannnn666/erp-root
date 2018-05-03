package ec.com.erp.cliente.common.factory;

import org.springframework.context.ApplicationContext;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.vehiculo.servicios.IVehiculoServicio;

public class VehiculoFactory extends ERPSpringContextFactory {

	public static String[] contexto = new String[]{		
	};

	public VehiculoFactory(ApplicationContext appCtx) {
		appCTx = appCtx;
	}


	/**
	 * Devuelve los servicios de vehiculo
	 * @return
	 * @throws ERPException
	 */
	public IVehiculoServicio getVehiculoServicio() throws ERPException{
		return (IVehiculoServicio)getBean(ERPFactoryConstantes.VEHICULO_SERVICE);
	}
	
}
