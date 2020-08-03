/**
 * 
 */
package ec.com.erp.factura.servicios;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.FacturaCabeceraDTO;

/**
 * @author Esteban Gudino
 * 2018-05-05
 */
public interface IFacturaCabeceraServicio {

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
	Collection<FacturaCabeceraDTO> findObtenerListaFacturas(Integer codigoCompania, String numeroFactura, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin,  String docClienteProveedor, String nombClienteProveedor, Boolean pagado, Collection<String> tiposDocumentos) throws ERPException;
	
	/**
	 * M\u00e9todo para guardar y actualizar factura cabecera
	 * @param facturaCabeceraDTO
	 * @throws ERPException
	 */
	void transGuardarActualizarFacturaCabecera(FacturaCabeceraDTO facturaCabeceraDTO) throws ERPException;
	
	/**
	 * Devuelve html de reporte de facturas
	 * @param facturaCabeceraDTOCols
	 * @return
	 * @throws ERPException
	 */
	String finObtenerXMLReporteFacturas(Collection<FacturaCabeceraDTO> facturaCabeceraDTOCols) throws ERPException;
	
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
	 * @param tipoDocumento
	 * @return
	 * @throws ERPException
	 */
	BigDecimal findObtenerComprasVentas(Integer codigoCompania, Timestamp fechaInicio, Timestamp fechaFin, String tipoDocumento, Boolean pagada) throws ERPException;
	
	/**
	 * Obtener numero de facturas por filtros
	 * @param codigoCompania
	 * @param tipoDocumento
	 * @param pagada
	 * @return
	 * @throws ERPException
	 */
	Long findObtenerNumeroFacturasComprasVentas(Integer codigoCompania, String tipoDocumento, Boolean pagada) throws ERPException;
	
	/**
	 * Devuelve html para la impresion de factura de venta
	 * @param facturaCabeceraDTO
	 * @return
	 * @throws ERPException
	 */
	String finObtenerXMLImprimirFacturaVenta(FacturaCabeceraDTO facturaCabeceraDTO) throws ERPException;
}
