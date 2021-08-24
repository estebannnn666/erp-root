package ec.com.erp.cliente.common.factory;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ec.com.erp.cliente.common.exception.ERPException;

public class ERPFactory {
	public static final String[] CONTEXTO = {
		ERPFactoryConstantes.AMBIENTE_CONTEXTO,
		ERPFactoryConstantes.TRANSACTION_CONTEXTO,
		ERPFactoryConstantes.SESSION_FACTORY_CONTEXTO,
		"/ec/com/erp/administracion/spring/config/ERPServicioBeans.xml",
		"/ec/com/erp/administracion/spring/config/ERPGestorBeans.xml",
		"/ec/com/erp/administracion/spring/config/ERPDaoBeans.xml",
	};
	
	private static final ApplicationContext APP_CTX = activateFactory();
	public static final UsuariosFactory usuarios = new UsuariosFactory(APP_CTX);
	public static final ArticulosFactory articulos = new ArticulosFactory(APP_CTX);
	public static final CatalogoFactory catalogos = new CatalogoFactory(APP_CTX);
	public static final ClientesFactory clientes = new ClientesFactory(APP_CTX);
	public static final PedidosFactory pedidos = new PedidosFactory(APP_CTX);
	public static final TransportistaFactory transportista = new TransportistaFactory(APP_CTX);
	public static final ChoferFactory chofer = new ChoferFactory(APP_CTX);
	public static final VehiculoFactory vehiculo = new VehiculoFactory(APP_CTX);
	public static final DespachoFactory despacho = new DespachoFactory(APP_CTX);
	public static final FacturasFactory facturas = new FacturasFactory(APP_CTX);
	public static final SecuenciasFactory secuencias = new SecuenciasFactory(APP_CTX);
	public static final PerfilFactory perfiles = new PerfilFactory(APP_CTX);
	public static final ModuloFactory modulos = new ModuloFactory(APP_CTX);
	public static final InventarioFactory inventario = new InventarioFactory(APP_CTX);
	public static final EstadoPedidoFactory estadopedido = new EstadoPedidoFactory(APP_CTX);
	public static final ProveedorFactory proveedor = new ProveedorFactory(APP_CTX);
	public static final ImpuestoFactory impuesto = new ImpuestoFactory(APP_CTX);
	public static final TransaccionFactory transaccion = new TransaccionFactory(APP_CTX);
	public static final VendedorFactory vendedor = new VendedorFactory(APP_CTX);
	public static final FireBaseFactory firebase = new FireBaseFactory(APP_CTX);
	public static final ParametroFactory parametro = new ParametroFactory(APP_CTX);
	public static final NotificacionMailFactory notificacion = new NotificacionMailFactory(APP_CTX);
	
	private static ApplicationContext activateFactory(){
		try{
			return new ClassPathXmlApplicationContext(unirContextos());
		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al cargar factory.").initCause(e);
		} 
	}

	private static String[] unirContextos(){
		Set<String> contextSet = new HashSet<String>();
		addContextPath(ERPFactory.CONTEXTO,contextSet);
	
		return contextSet.toArray(new String[contextSet.size()]);
	}
	
	private static void addContextPath(String[] contextos, Set<String> contextSet){
		for (String contextPath : contextos) {
			contextSet.add(contextPath);
		}
	}
	
	/**
	 * 
	 * @param bean
	 * @return
	 * @throws 
	 */
	public static Object getBean(String bean) throws ERPException {
		return APP_CTX.getBean(bean);
	}
}
