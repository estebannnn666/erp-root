package ec.com.erp.cliente.common.factory;

import org.springframework.context.ApplicationContext;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.usuarios.servicios.IUsuariosServicio;

public class UsuariosFactory extends ERPSpringContextFactory {

	public static String[] contexto = new String[]{		
	};

	public UsuariosFactory(ApplicationContext appCtx) {
		appCTx = appCtx;
	}


	/**
	 * Devuelve los servicios de empleado
	 * @return
	 * @throws ERPException
	 */
	public IUsuariosServicio getUsuariosServicio() throws ERPException{
		return (IUsuariosServicio)getBean(ERPFactoryConstantes.USUARIOS_SERVICE);
	}
	
}
