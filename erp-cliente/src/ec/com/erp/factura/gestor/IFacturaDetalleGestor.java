package ec.com.erp.factura.gestor;

import java.sql.Timestamp;
import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.FacturaDetalleDTO;
import ec.com.erp.cliente.mdl.vo.ReporteVentasVO;


/**
 * @author Esteban Gudino
 *
 */

public interface IFacturaDetalleGestor {
	
	/**
	 * M\u00e9todo para obtener reporte de ventas por articulo vendedor
	 * @param codigoCompania
	 * @param documentoVendedor
	 * @param fechaFacturaInicio
	 * @param fechaFacturaFin
	 * @return
	 * @throws ERPException
	 */
	Collection<ReporteVentasVO> obtenerReporteVentas(Integer codigoCompania, Boolean pagada, Long codigoVendedor, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin) throws ERPException;
	
	/**
	 * M\u00e9todo para obtener lista de detalles por factura
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @return Collection<FacturaDetalleDTO>
	 * @throws ERPException
	 */
	Collection<FacturaDetalleDTO> obtenerListaDetalleFacturaByNumeroFactura(Integer codigoCompania, String numeroDocumento) throws ERPException;
	
	/**
	 * M\u00e9todo para guardar y actualizar detalle factura
	 * @param facturaDetalleDTO
	 * @throws ERPException
	 */
	void guardarActualizarDetalleFactura(FacturaDetalleDTO facturaDetalleDTO) throws ERPException;
	
}
