package ec.com.erp.cliente.common.factory;

import org.springframework.context.ApplicationContext;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.factura.servicios.IFacturaCabeceraServicio;

public class FacturasFactory extends ERPSpringContextFactory {

	public static String[] contexto = new String[]{		
	};

	public FacturasFactory(ApplicationContext appCtx) {
		appCTx = appCtx;
	}


	/**
	 * Devuelve los servicios de facturas
	 * @return
	 * @throws ERPException
	 */
	public IFacturaCabeceraServicio getFacturaCabeceraServicio() throws ERPException{
		return (IFacturaCabeceraServicio)getBean(ERPFactoryConstantes.FACTURAS_SERVICE);
	}
	
}
