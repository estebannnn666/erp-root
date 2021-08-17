package ec.com.erp.factura.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.FacturaCabeceraDTO;
import ec.com.erp.cliente.mdl.vo.ReporteVentasFacturasVO;


/**
 * @author Esteban Gudino
 *
 */

public interface IFacturaCabeceraDAO {
	
	/**
	 * M\u00e9todo para obtener lista de facturas por filtros de busqueda
	 * @param codigoCompania
	 * @param numeroFactura
	 * @param fechaFacturaInicio
	 * @param fechaFacturaFin
	 * @param docClienteProveedor
	 * @param nombClienteProveedor
	 * @param pagado
	 * @param tipoDocumento
	 * @param codigoVendedor
	 * @return Collection<FacturaCabeceraDTO>
	 * @throws ERPException
	 */
	Collection<FacturaCabeceraDTO> obtenerListaFacturas(Integer codigoCompania, String numeroFactura, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin, String docClienteProveedor, String nombClienteProveedor, Boolean pagado, Collection<String> tiposDocumentos, Long codigoVendedor) throws ERPException;
	
	/**
	 * M\u00e9todo para obtener lista de facturas sin despachar.
	 * @param codigoCompania
	 * @param numeroFactura
	 * @param docClienteProveedor
	 * @param nombClienteProveedor
	 * @return
	 * @throws ERPException
	 */
	Collection<FacturaCabeceraDTO> obtenerListaFacturasSinDespachar(Integer codigoCompania, String numeroFactura, String docClienteProveedor, String nombClienteProveedor) throws ERPException;
	
	/**
	 * M\u00e9todo para obtener lista de facturas por filtros de busqueda
	 * @param codigoCompania
	 * @param numeroFactura
	 * @param fechaFacturaInicio
	 * @param fechaFacturaFin
	 * @param docClienteProveedor
	 * @param nombClienteProveedor
	 * @param pagado
	 * @param tipoDocumento
	 * @return Collection<FacturaCabeceraDTO>
	 * @throws ERPException
	 */
	Collection<FacturaCabeceraDTO> obtenerListaFacturasValidarFirebase(Integer codigoCompania, String numeroFactura, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin,  String docClienteProveedor, String nombClienteProveedor, Boolean pagado, Collection<String> tiposDocumentos) throws ERPException;

	/**
	 * M\u00e9todo para obtener la factura del pedido
	 * @param codigoCompania
	 * @param codigoPedido
	 * @return
	 * @throws ERPException
	 */
	FacturaCabeceraDTO obtenerFacturaPedido(Integer codigoCompania, Long codigoPedido) throws ERPException;
	
	/**
	 * M\u00e9todo para obtener la factura por codigo
	 * @param codigoCompania
	 * @param codigoFactura
	 * @return
	 * @throws ERPException
	 */
	FacturaCabeceraDTO obtenerFacturaPorCodigo(Integer codigoCompania, Long codigoFactura) throws ERPException;
	
	/**
	 * M\u00e9todo para guardar y actualizar factura cabecera
	 * @param facturaCabeceraDTO
	 * @throws ERPException
	 */
	void guardarActualizarFacturaCabecera(FacturaCabeceraDTO facturaCabeceraDTO) throws ERPException;
	
	/**
	 * Metodo para obtener el valor de venta por mes y tipo
	 * @param codigoCompania
	 * @param fechaInicio
	 * @param fechaFin
	 * @param tipoDocumentos
	 * @return
	 * @throws ERPException
	 */
	BigDecimal obtenerComprasVentas(Integer codigoCompania, Timestamp fechaInicio, Timestamp fechaFin, Collection<String> tiposDocumentos, Boolean pagada) throws ERPException;
	
	/**
	 * Obtener numero de facturas por filtros
	 * @param codigoCompania
	 * @param fechaInicio
	 * @param fechaFin
	 * @param tipoDocumento
	 * @param pagada
	 * @return
	 * @throws ERPException
	 */
	Long obtenerNumeroFacturasComprasVentas(Integer codigoCompania, Timestamp fechaInicio, Timestamp fechaFin, Collection<String> tipoDocumento, Boolean pagada) throws ERPException;
	
	/**
	 * Actualizar estado de factura.
	 * @param codigoCompania
	 * @param codigoFactura
	 * @param userId
	 * @param codigoValorEstado
	 * @throws ERPException
	 */
	void actualizarFacturaEstadoDespachado(Integer codigoCompania, Long codigoFactura, String userId, String codigoValorEstado)throws ERPException;
	
	/**
	 * Actualizar numero de factura.
	 * @param codigoCompania
	 * @param codigoFactura
	 * @param userId
	 * @param numeroFactura
	 * @throws ERPException
	 */
	void actualizarFacturaNumeroFactura(Integer codigoCompania, Long codigoFactura, String userId, String numeroFactura)throws ERPException;
	
	/**
	 * M\u00e9todo para obtener reporte de ventas por facturas y vendedores.
	 * @param codigoCompania
	 * @param pagada
	 * @param codigoVendedor
	 * @param fechaFacturaInicio
	 * @param fechaFacturaFin
	 * @return
	 * @throws ERPException
	 */
	Collection<ReporteVentasFacturasVO> obtenerReporteVentasFactura(Integer codigoCompania, Boolean pagada, Long codigoVendedor, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin) throws ERPException;
}
