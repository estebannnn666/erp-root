/**
 * 
 */
package ec.com.erp.factura.servicios;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.FacturaCabeceraDTO;
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
	@Override
	public Collection<FacturaCabeceraDTO> findObtenerListaFacturas(Integer codigoCompania, String numeroFactura, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin,  String docClienteProveedor, String nombClienteProveedor, Boolean pagado, Collection<String> tiposDocumentos) throws ERPException{
		return this.facturaCabeceraGestor.obtenerListaFacturas(codigoCompania, numeroFactura, fechaFacturaInicio, fechaFacturaFin, docClienteProveedor, nombClienteProveedor, pagado, tiposDocumentos);
	}
	
	/**
	 * M\u00e9todo para guardar y actualizar factura cabecera
	 * @param facturaCabeceraDTO
	 * @throws ERPException
	 */
	@Override
	public void transGuardarActualizarFacturaCabecera(FacturaCabeceraDTO facturaCabeceraDTO) throws ERPException{
		this.facturaCabeceraGestor.guardarActualizarFacturaCabecera(facturaCabeceraDTO);
	}

	/**
	 * Devuelve html de reporte de facturas
	 * @param facturaCabeceraDTOCols
	 * @return
	 * @throws ERPException
	 */
	public String finObtenerXMLReporteFacturas(Collection<FacturaCabeceraDTO> facturaCabeceraDTOCols) throws ERPException{
		return this.facturaCabeceraGestor.procesarXMLReporteFacturas(facturaCabeceraDTOCols);
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
	 * @param tipoDocumento
	 * @return
	 * @throws ERPException
	 */
	@Override
	public BigDecimal findObtenerComprasVentas(Integer codigoCompania, Timestamp fechaInicio, Timestamp fechaFin, String tipoDocumento, Boolean pagada) throws ERPException{
		return this.facturaCabeceraGestor.obtenerComprasVentas(codigoCompania, fechaInicio, fechaFin, tipoDocumento, pagada);
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
	public Long findObtenerNumeroFacturasComprasVentas(Integer codigoCompania, String tipoDocumento, Boolean pagada) throws ERPException{
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
}
