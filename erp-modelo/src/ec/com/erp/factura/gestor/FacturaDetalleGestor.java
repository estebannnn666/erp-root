/**
 * 
 */
package ec.com.erp.factura.gestor;

import java.sql.Timestamp;
import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.FacturaDetalleDTO;
import ec.com.erp.cliente.mdl.vo.ReporteVentasVO;
import ec.com.erp.factura.dao.IFacturaDetalleDAO;

/**
 * @author Esteban Gudino
 * 2018-05-05
 */
public class FacturaDetalleGestor implements IFacturaDetalleGestor {

	
	/**
	 * Dao para obtnener dao detalle factura
	 */
	private IFacturaDetalleDAO facturaDetalleDAO;

	public IFacturaDetalleDAO getFacturaDetalleDAO() {
		return facturaDetalleDAO;
	}

	public void setFacturaDetalleDAO(IFacturaDetalleDAO facturaDetalleDAO) {
		this.facturaDetalleDAO = facturaDetalleDAO;
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
	public Collection<ReporteVentasVO> obtenerReorteVentas(Integer codigoCompania, String documentoVendedor, String nombreVendedor, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin) throws ERPException{
		return this.facturaDetalleDAO.obtenerReorteVentas(codigoCompania, documentoVendedor, nombreVendedor, fechaFacturaInicio, fechaFacturaFin);
	}

	/**
	 * M\u00e9todo para obtener lista de detalles por factura
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @return Collection<FacturaDetalleDTO>
	 * @throws ERPException
	 */
	@Override
	public Collection<FacturaDetalleDTO> obtenerListaDetalleFacturaByNumeroFactura(Integer codigoCompania, String numeroDocumento) throws ERPException{
		return this.facturaDetalleDAO.obtenerListaDetalleFacturaByNumeroFactura(codigoCompania, numeroDocumento);
	}
	
	/**
	 * M\u00e9todo para guardar y actualizar detalle factura
	 * @param facturaDetalleDTO
	 * @throws ERPException
	 */
	@Override
	public void guardarActualizarDetalleFactura(FacturaDetalleDTO facturaDetalleDTO) throws ERPException{
		this.facturaDetalleDAO.guardarActualizarDetalleFactura(facturaDetalleDTO);
	}
}
