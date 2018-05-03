package ec.com.erp.cliente.common.factory;

import org.springframework.context.ApplicationContext;

import ec.com.erp.cliente.common.exception.ERPException;

public class ERPSpringContextFactory {

	protected static ApplicationContext appCTx;

	protected static Object getBean(String bean) throws ERPException {
		return appCTx.getBean(bean);
	}
}
