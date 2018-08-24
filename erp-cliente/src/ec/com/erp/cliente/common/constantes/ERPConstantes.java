package ec.com.erp.cliente.common.constantes;

import java.math.BigDecimal;

import ec.com.erp.cliente.common.resources.ERPMessages;

public class ERPConstantes {
	
	private static final ERPConstantes INSTANCIA = new ERPConstantes();
	
	public static ERPConstantes getInstancia(){
		return INSTANCIA;
	}
	/************ Plantillas xsl para impresion de documentos *****/
	public final static String PLANTILLA_XSL_FOPRINCIPAL = ERPMessages.getString("ec.com.erp.cliente.common.plantilla.impresion.principal");
	public final static String PLANTILLA_XSL_REPORTE_FACTURAS = ERPMessages.getString("ec.com.erp.cliente.common.plantilla.impresion.reporte.facturas");
	public final static String PLANTILLA_XSL_IMPRIMIR_GUIA_DESPACHO = ERPMessages.getString("ec.com.erp.cliente.common.plantilla.impresion.guia.despacho");
	
	/************ Generales ************************/
	public static final String ESTADO_ACTIVO_NUMERICO = ERPMessages.getString("ec.com.erp.general.estado.numerico.activo");
	public static final String ESTADO_INACTIVO_NUMERICO = ERPMessages.getString("ec.com.erp.general.estado.numerico.inactivo");
	public static final String PREFIJO_SECUENCIAL_USUARIO = ERPMessages.getString("ec.com.erp.general.prefijo.secuencial.usuario");
	
	/************ CODIGOS CATALAGOS VALORES *****************/
	public static final String CODIGO_CATALOGO_VALOR_TIPO_CLIENTE_PERSONA = ERPMessages.getString("ec.com.erp.catalogo.valor.tipo.cliente.persona");
	public static final String CODIGO_CATALOGO_VALOR_TIPO_CLIENTE_EMPRESA = ERPMessages.getString("ec.com.erp.catalogo.valor.tipo.cliente.empresa");
	public static final String CODIGO_CATALOGO_VALOR_TIPO_CONTACTO_PRINCIPAL = ERPMessages.getString("ec.com.erp.catalogo.valor.tipo.contacto.principal");
	public static final String CODIGO_CATALOGO_VALOR_TIPO_PERFIL_ADMINISTRADOR = ERPMessages.getString("ec.com.erp.catalogo.valor.tipo.perfil.administrador");
	public static final String CODIGO_CATALOGO_VALOR_TIPO_PERFIL_CLIENTES = ERPMessages.getString("ec.com.erp.catalogo.valor.tipo.perfil.clientes");	
	public static final String CODIGO_CATALOGO_VALOR_ESTADO_PEDIDO_REGISTRADO = ERPMessages.getString("ec.com.erp.catalogo.valor.tipo.estado.pedido.registrado");
	public static final String CODIGO_CATALOGO_VALOR_ESTADO_PEDIDO_PENDIENTE = ERPMessages.getString("ec.com.erp.catalogo.valor.tipo.estado.pedido.pendiente");
	public static final String CODIGO_CATALOGO_VALOR_ESTADO_PEDIDO_ENTREGADO = ERPMessages.getString("ec.com.erp.catalogo.valor.tipo.estado.pedido.entregado");
	public static final String CODIGO_CATALOGO_VALOR_TIPO_CHOFER_PRINCIPAL = ERPMessages.getString("ec.com.erp.catalogo.valor.tipo.chofer.principal");
	public static final String CODIGO_CATALOGO_VALOR_TIPO_CHOFER_AYUDANTE = ERPMessages.getString("ec.com.erp.catalogo.valor.tipo.chofer.ayudante");
	public static final String CODIGO_CATALOGO_VALOR_DOCUMENTO_VENTAS = ERPMessages.getString("ec.com.erp.catalogo.valor.tipo.documento.ventas");
	public static final String CODIGO_CATALOGO_VALOR_DOCUMENTO_COMPRAS = ERPMessages.getString("ec.com.erp.catalogo.valor.tipo.documento.compras");
	
	/************ CODIGOS CATALAGOS TIPOS *****************/
	public static final Integer CODIGO_CATALOGO_TIPOS_CONTACTOS = ERPMessages.getInteger("ec.com.erp.catalogo.tipo.tipo.contacto");
	public static final Integer CODIGO_CATALOGO_TIPOS_PERFILES = ERPMessages.getInteger("ec.com.erp.catalogo.tipo.tipo.perfil");
	public static final Integer CODIGO_CATALOGO_TIPOS_CLIENTES = ERPMessages.getInteger("ec.com.erp.catalogo.tipo.tipo.cliente");
	public static final Integer CODIGO_CATALOGO_TIPOS_ESTADO_PEDIDO = ERPMessages.getInteger("ec.com.erp.catalogo.tipo.estado.pedido");
	public static final Integer CODIGO_CATALOGO_TIPOS_LICENCIAS = ERPMessages.getInteger("ec.com.erp.catalogo.tipo.tipo.licencia");
	public static final Integer CODIGO_CATALOGO_TIPOS_CHOFERES = ERPMessages.getInteger("ec.com.erp.catalogo.tipo.tipo.chofer");
	public static final Integer CODIGO_CATALOGO_TIPOS_VEHICULOS = ERPMessages.getInteger("ec.com.erp.catalogo.tipo.tipo.vehiculos");
	public static final Integer CODIGO_CATALOGO_TIPOS_DOCUMENTOS = ERPMessages.getInteger("ec.com.erp.catalogo.tipo.tipo.documentos.cuentas");
	
	/*********** PERFILES ********/
	public static final BigDecimal CODIGO_PERFIL_ADMINISTRADOR = ERPMessages.getBigDecimal("ec.com.erp.codigo.secuencial.pefil.administrador");
	public static final BigDecimal CODIGO_PERFIL_CLIENTES = ERPMessages.getBigDecimal("ec.com.erp.codigo.secuencial.pefil.clientes");
}
