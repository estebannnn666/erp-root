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
import ec.com.erp.cliente.mdl.vo.ReporteVentasFacturasVO;
import ec.com.erp.cliente.mdl.vo.ReporteVentasVO;
import ec.com.erp.factura.gestor.IFacturaCabeceraGestor;

/**
 * @author Esteban Gudino
 * 2018-05-05
 */
public class FacturaCabeceraServicio implements IFacturaCabeceraServicio {

	private IFacturaCabeceraGestor facturaCabeceraGestor;
	
	public IFacturaCabeceraGestor getFacturaCabeceraGestor() {
		return facturaCabeceraGestor;
	}

	public void setFacturaCabeceraGestor(IFacturaCabeceraGestor facturaCabeceraGestor) {
		this.facturaCabeceraGestor = facturaCabeceraGestor;
	}
	
	/**
	 * M\u00e9todo para obtener reporte de ventas por articulo vendedor
	 * @param codigoCompania
	 * @param documentoVendedor
	 * @param fechaFacturaInicio
	 * @param fechaFacturaFin
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Collection<ReporteVentasVO> findObtenerReporteVentas(Integer codigoCompania, Boolean pagada, Long codigoVendedor, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin) throws ERPException{
		return this.facturaCabeceraGestor.obtenerReporteVentas(codigoCompania, pagada, codigoVendedor, fechaFacturaInicio, fechaFacturaFin);
	}

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
	@Override
	public Collection<FacturaCabeceraDTO> findObtenerListaFacturas(Integer codigoCompania, String numeroFactura, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin,  String docClienteProveedor, String nombClienteProveedor, Boolean pagado, Collection<String> tiposDocumentos, Long codigoVendedor) throws ERPException{
		return this.facturaCabeceraGestor.obtenerListaFacturas(codigoCompania, numeroFactura, fechaFacturaInicio, fechaFacturaFin, docClienteProveedor, nombClienteProveedor, pagado, tiposDocumentos, codigoVendedor);
	}
	
	/**
	 * M\u00e9todo para obtener lista de facturas electronicas por filtros de busqueda
	 * @param codigoCompania
	 * @param numeroFactura
	 * @param fechaFacturaInicio
	 * @param fechaFacturaFin
	 * @param docClienteProveedor
	 * @param nombClienteProveedor
	 * @return Collection<FacturaCabeceraDTO>
	 * @throws ERPException
	 */
	@Override
	public Collection<FacturaCabeceraDTO> findObtenerListaFacturasElectronicas(Integer codigoCompania, String numeroFactura, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin,  String docClienteProveedor, String nombClienteProveedor) throws ERPException{
		return this.facturaCabeceraGestor.obtenerListaFacturasElectronicas(codigoCompania, numeroFactura, fechaFacturaInicio, fechaFacturaFin, docClienteProveedor, nombClienteProveedor);
	}
	
	/**
	 * M\u00e9todo para obtener lista de facturas sin despachar.
	 * @param codigoCompania
	 * @param numeroFactura
	 * @param docClienteProveedor
	 * @param nombClienteProveedor
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Collection<FacturaCabeceraDTO> findObtenerListaFacturasSinDespachar(Integer codigoCompania, String numeroFactura, String docClienteProveedor, String nombClienteProveedor) throws ERPException{
		return this.facturaCabeceraGestor.obtenerListaFacturasSinDespachar(codigoCompania, numeroFactura, docClienteProveedor, nombClienteProveedor);
	}
	
	/**
	 * M\u00e9todo para guardar y actualizar factura cabecera
	 * @param facturaCabeceraDTO
	 * @throws ERPException
	 */
	@Override
	public void transGuardarActualizarFacturaCabecera(Boolean crearFacturaElectronica, FacturaCabeceraDTO facturaCabeceraDTO) throws ERPException{
		this.facturaCabeceraGestor.guardarActualizarFacturaCabecera(crearFacturaElectronica, facturaCabeceraDTO);
	}
	
	/**
	 * Metodo para firmar enviar y autorizar factura electronica
	 * @param facturaCabeceraDTO
	 */
	@Override
	public void transEnviarFirmarAutorizar(FacturaCabeceraDTO facturaCabeceraDTO){
		this.facturaCabeceraGestor.enviarFirmarAutorizar(facturaCabeceraDTO);
	}

	/**
	 * Devuelve html de reporte de facturas
	 * @param facturaCabeceraDTOCols
	 * @return
	 * @throws ERPException
	 */
	public String finObtenerXMLReporteFacturas(Collection<FacturaCabeceraDTO> facturaCabeceraDTOCols, String titulo, String tipoReporte) throws ERPException{
		return this.facturaCabeceraGestor.procesarXMLReporteFacturas(facturaCabeceraDTOCols, titulo, tipoReporte);
	}
	
	/**
	 * Devuelve html de reporte de ventas
	 * @param reporteVentasCol
	 * @return
	 * @throws ERPException
	 */
	public String findObtenerXMLReporteVentas(Date fechaInicio, Date fechaFin, Collection<ReporteVentasVO> reporteVentasCol) throws ERPException{
		return this.facturaCabeceraGestor.procesarXMLReporteVentas(fechaInicio, fechaFin, reporteVentasCol);
	}
	
	/**
	 * Funcionalidad para cancelar factura o inactivar
	 * @param facturaCabeceraDTO
	 * @throws ERPException
	 */
	@Override
	public void transCancelarFacturaInactivar(FacturaCabeceraDTO facturaCabeceraDTO) throws ERPException{
		this.facturaCabeceraGestor.cancelarFacturaInactivar(facturaCabeceraDTO);
	}
	
	/**
	 * Metodo para obtener el valor de venta por mes y tipo
	 * @param codigoCompania
	 * @param fechaInicio
	 * @param fechaFin
	 * @param tipoDocumentos
	 * @return
	 * @throws ERPException
	 */
	@Override
	public BigDecimal findObtenerComprasVentas(Integer codigoCompania, Timestamp fechaInicio, Timestamp fechaFin, Collection<String> tiposDocumentos, Boolean pagada) throws ERPException{
		return this.facturaCabeceraGestor.obtenerComprasVentas(codigoCompania, fechaInicio, fechaFin, tiposDocumentos, pagada);
	}
	
	/**
	 * Obtener numero de facturas por filtros
	 * @param codigoCompania
	 * @param tipoDocumento
	 * @param pagada
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Long findObtenerNumeroFacturasComprasVentas(Integer codigoCompania, Collection<String> tipoDocumento, Boolean pagada) throws ERPException{
		return this.facturaCabeceraGestor.obtenerNumeroFacturasComprasVentas(codigoCompania, tipoDocumento, pagada);
	}
	
	/**
	 * Devuelve html para la impresion de factura de venta
	 * @param facturaCabeceraDTO
	 * @return
	 * @throws ERPException
	 */
	@Override
	public String finObtenerXMLImprimirFacturaVenta(FacturaCabeceraDTO facturaCabeceraDTO) throws ERPException{
		return this.facturaCabeceraGestor.obtenerXMLImprimirFacturaVenta(facturaCabeceraDTO);
	}
	
	/**
	 * Obtener bytes nota de venta
	 * @param facturaCabeceraDTO
	 * @return
	 * @throws IOException
	 */
	@Override
	public byte[] findObtenerNotaVenta(FacturaCabeceraDTO facturaCabeceraDTO) throws IOException {
		return this.facturaCabeceraGestor.generateNotaVenta(facturaCabeceraDTO);
	}
	
	/**
	 * M\u00e9todo para obtener xml factura
	 * @param codigoCompania
	 * @param codigoFactura
	 * @throws ERPException
	 */
	public byte[] findObtenerXmlDocumentoFactura(Integer codigoCompania, Long codigoFactura) throws ERPException{
		return this.facturaCabeceraGestor.obtenerXmlDocumentoFactura(codigoCompania, codigoFactura);
	}
	
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
	@Override
	public Collection<ReporteVentasFacturasVO> findObtenerReporteVentasFactura(Integer codigoCompania, Boolean pagada, Long codigoVendedor, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin) throws ERPException{
		return this.facturaCabeceraGestor.obtenerReporteVentasFactura(codigoCompania, pagada, codigoVendedor, fechaFacturaInicio, fechaFacturaFin);
	}
}
