package ec.com.erp.cliente.common.factory;

import org.springframework.context.ApplicationContext;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.pedidos.servicios.IPedidoServicio;

public class PedidosFactory extends ERPSpringContextFactory {

	public static String[] contexto = new String[]{		
	};

	public PedidosFactory(ApplicationContext appCtx) {
		appCTx = appCtx;
	}

	/**
	 * Devuelve los servicios de pedidos
	 * @return
	 * @throws ERPException
	 */
	public IPedidoServicio getPedidoServicio() throws ERPException{
		return (IPedidoServicio)getBean(ERPFactoryConstantes.PEDIDOS_SERVICE);
	}
	
}
