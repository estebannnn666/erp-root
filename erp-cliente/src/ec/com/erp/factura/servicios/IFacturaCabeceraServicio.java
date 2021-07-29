/**
 * 
 */
package ec.com.erp.factura.servicios;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.FacturaCabeceraDTO;
import ec.com.erp.cliente.mdl.vo.ReporteVentasVO;

/**
 * @author Esteban Gudino
 * 2018-05-05
 */
public interface IFacturaCabeceraServicio {

	/**
	 * M\u00e9todo para obtener reporte de ventas por articulo vendedor
	 * @param codigoCompania
	 * @param documentoVendedor
	 * @param fechaFacturaInicio
	 * @param fechaFacturaFin
	 * @return
	 * @throws ERPException
	 */
	Collection<ReporteVentasVO> findObtenerReorteVentas(Integer codigoCompania, String documentoVendedor, String nombreVendedor, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin) throws ERPException;
	
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
	Collection<FacturaCabeceraDTO> findObtenerListaFacturas(Integer codigoCompania, String numeroFactura, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin,  String docClienteProveedor, String nombClienteProveedor, Boolean pagado, Collection<String> tiposDocumentos, Long codigoVendedor) throws ERPException;
	
	/**
	 * M\u00e9todo para obtener lista de facturas sin despachar.
	 * @param codigoCompania
	 * @param numeroFactura
	 * @param docClienteProveedor
	 * @param nombClienteProveedor
	 * @return
	 * @throws ERPException
	 */
	Collection<FacturaCabeceraDTO> findObtenerListaFacturasSinDespachar(Integer codigoCompania, String numeroFactura, String docClienteProveedor, String nombClienteProveedor) throws ERPException;
	
	/**
	 * M\u00e9todo para guardar y actualizar factura cabecera
	 * @param facturaCabeceraDTO
	 * @throws ERPException
	 */
	void transGuardarActualizarFacturaCabecera(String rucFactElectronica, FacturaCabeceraDTO facturaCabeceraDTO) throws ERPException;
	
	/**
	 * Devuelve html de reporte de facturas
	 * @param facturaCabeceraDTOCols
	 * @return
	 * @throws ERPException
	 */
	String finObtenerXMLReporteFacturas(Collection<FacturaCabeceraDTO> facturaCabeceraDTOCols, String titulo, String tipoReporte) throws ERPException;
	
	/**
	 * Devuelve html de reporte de ventas
	 * @param reporteVentasCol
	 * @return
	 * @throws ERPException
	 */
	String findObtenerXMLReporteVentas(Date fechaInicio, Date fechaFin, Collection<ReporteVentasVO> reporteVentasCol) throws ERPException;
	
	/**
	 * Funcionalidad para cancelar factura o inactivar
	 * @param facturaCabeceraDTO
	 * @throws ERPException
	 */
	void transCancelarFacturaInactivar(FacturaCabeceraDTO facturaCabeceraDTO) throws ERPException;
	
	/**
	 * Metodo para obtener el valor de venta por mes y tipo
	 * @param codigoCompania
	 * @param fechaInicio
	 * @param fechaFin
	 * @param tipoDocumentos
	 * @return
	 * @throws ERPException
	 */
	BigDecimal findObtenerComprasVentas(Integer codigoCompania, Timestamp fechaInicio, Timestamp fechaFin, Collection<String> tiposDocumentos, Boolean pagada) throws ERPException;
	
	/**
	 * Obtener numero de facturas por filtros
	 * @param codigoCompania
	 * @param tipoDocumento
	 * @param pagada
	 * @return
	 * @throws ERPException
	 */
	Long findObtenerNumeroFacturasComprasVentas(Integer codigoCompania, Collection<String> tipoDocumento, Boolean pagada) throws ERPException;
	
	/**
	 * Devuelve html para la impresion de factura de venta
	 * @param facturaCabeceraDTO
	 * @return
	 * @throws ERPException
	 */
	String finObtenerXMLImprimirFacturaVenta(FacturaCabeceraDTO facturaCabeceraDTO) throws ERPException;
	
	/**
	 * Obtener bytes nota de venta
	 * @param facturaCabeceraDTO
	 * @return
	 * @throws IOException
	 */
	byte[] findObtenerNotaVenta(FacturaCabeceraDTO facturaCabeceraDTO) throws IOException;
	
	/**
	 * M\u00e9todo para obtener xml factura
	 * @param codigoCompania
	 * @param codigoFactura
	 * @throws ERPException
	 */
	byte[] findObtenerXmlDocumentoFactura(Integer codigoCompania, Long codigoFactura) throws ERPException;
}
