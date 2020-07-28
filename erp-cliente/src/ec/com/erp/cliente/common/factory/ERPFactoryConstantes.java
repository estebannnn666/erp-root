package ec.com.erp.cliente.common.factory;

import ec.com.erp.cliente.common.resources.ERPMessages;

public class ERPFactoryConstantes {
	// Variables generales para coneccion con bdd
	public final static String AMBIENTE_CONTEXTO = ERPMessages.getString("ec.com.erp.cliente.sprint.conexion.ambiente");
	public final static String TRANSACTION_CONTEXTO = ERPMessages.getString("ec.com.erp.cliente.sprint.conexion.transaccion.beans");
	public final static String SESSION_FACTORY_CONTEXTO = ERPMessages.getString("ec.com.erp.cliente.sprint.conexion.sesion.fact.beans");
	public final static String BEANREF_CONTEXTPATH = ERPMessages.getString("ec.com.erp.cliente.sprint.conexion.sesion.refbeans.contextpath");
		
	// Nombre de servicios
	public final static String USUARIOS_SERVICE = "erpUsuariosServicio";
	public final static String ARTICULO_SERVICE = "erpArticuloServicio";
	public final static String CATALOGO_SERVICE = "erpCatalogoServicio";
	public final static String CLIENTES_SERVICE = "erpClientesServicio";
	public final static String TRANSPORTISTA_SERVICE = "erpTransportistaServicio";
	public final static String CHOFER_SERVICE = "erpChoferServicio";
	public final static String VEHICULO_SERVICE = "erpVehiculoServicio";
	public final static String DESPACHO_SERVICE = "erpGuiaDespachoServicio";
	public final static String COMPANIA_SERVICE = "erpCompaniaServicio";
	public final static String CONTACTO_SERVICE = "erpContactoServicio";
	public final static String EMPRESA_SERVICE = "erpEmpresaServicio";
	public final static String DETALLE_PEDIDO_SERVICE = "erpDetallePedidoServicio";
	public final static String ESTADO_PEDIDO_SERVICE = "erpEstadoPedidoServicio";
	public final static String PEDIDOS_SERVICE = "erpPedidoServicio";
	public final static String PERFILES_SERVICE = "erpPerfilesServicio";
	public final static String PERSONAS_SERVICE = "erpPersonaServicio";
	public final static String SECUENCIAS_SERVICE = "erpSecuenciaServicio";
	public final static String FACTURAS_SERVICE = "erpFacturaCabeceraServicio";
	public final static String MODULOS_SERVICE = "erpModuloServicio";
	public final static String INVENTARIOS_SERVICE = "erpInventarioServicio";
	public final static String PROVEEDOR_SERVICE = "erpProveedorServicio";
	public final static String IMPUESTOS_SERVICE = "erpImpuestoServicio";
	public final static String TRANSACCION_SERVICE = "erpTransaccionServicio";
	public final static String VENDEDOR_SERVICE = "erpVendedorServicio";
}
