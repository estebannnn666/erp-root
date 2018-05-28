/**
 * 
 */
package ec.com.erp.factura.servicios;

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
	public Collection<FacturaCabeceraDTO> findObtenerListaFacturas(Integer codigoCompania, String numeroFactura, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin,  String docClienteProveedor, String nombClienteProveedor, Boolean pagado, String tipoDocumento) throws ERPException{
		return this.facturaCabeceraGestor.obtenerListaFacturas(codigoCompania, numeroFactura, fechaFacturaInicio, fechaFacturaFin, docClienteProveedor, nombClienteProveedor, pagado, tipoDocumento);
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
}
