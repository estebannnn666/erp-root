package ec.com.erp.cliente.common.factory;

import org.springframework.context.ApplicationContext;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.pedidos.servicios.IEstadoPedidoServicio;

public class EstadoPedidoFactory extends ERPSpringContextFactory {

	public static String[] contexto = new String[]{		
	};

	public EstadoPedidoFactory(ApplicationContext appCtx) {
		appCTx = appCtx;
	}

	/**
	 * Devuelve los servicios de pedidos
	 * @return
	 * @throws ERPException
	 */
	public IEstadoPedidoServicio getEstadoPedidoServicio() throws ERPException{
		return (IEstadoPedidoServicio)getBean(ERPFactoryConstantes.ESTADO_PEDIDO_SERVICE);
	}
	
}
