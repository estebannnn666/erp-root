package ec.com.erp.inventario.gestor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.InventarioDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IInventarioGestor {
	
	/**
	 * M\u00e9todo para obtener kardex por codigo de barra y fechas
	 * @param codigoCompania
	 * @param codigoBarras
	 * @param fechaFacturaInicio
	 * @param fechaFacturaFin
	 * @return
	 * @throws ERPException
	 */
	Collection<InventarioDTO> obtenerListaInventarioByArticuloFechas(Integer codigoCompania, Integer codigoArticuloUnidadManejo, String codigoBarras, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin) throws ERPException;

	/**
	 * M\u00e9todo para obtener kardex por codigo de barra
	 * @param codigoCompania
	 * @param codigoBarras
	 * @return
	 * @throws ERPException
	 */
	InventarioDTO obtenerUltimoInventarioByArticulo(Integer codigoCompania, String codigoBarras, Integer codigoArticuloUnidadManejo) throws ERPException;
	
	/**
	 * M\u00e9todo para crear o actualizar 
	 * @param inventarioDTO
	 * @throws ERPException
	 */
	void crearActualizarInventario(InventarioDTO inventarioDTO)throws ERPException;
	
	/**
	 * Devuelve html de reporte de inventarios
	 * @param inventarioDTOCols
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 * @throws ERPException
	 */
	String procesarXMLReporteKardex(Collection<InventarioDTO> inventarioDTOCols, Date fechaInicio, Date fechaFin) throws ERPException;
	
	/**
	 * M\u00e9todo para obtener existencias por codigo de barra y fechas
	 * @param codigoCompania
	 * @param codigoBarras
	 * @param fechaFacturaInicio
	 * @param fechaFacturaFin
	 * @return
	 * @throws ERPException
	 */
	Collection<InventarioDTO> obtenerListaExistenciasByArticuloFechas(Integer codigoCompania, String codigoBarras, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin) throws ERPException;
	
	/**
	 * Devuelve html de reporte de existencias
	 * @param inventarioDTOCols
	 * @return
	 * @throws ERPException
	 */
	String procesarXMLReporteExistencias(Collection<InventarioDTO> inventarioDTOCols) throws ERPException;
	
	/**
	 * M\u00e9todo para obtener valores estadisticos de inventario
	 * @param codigoCompania
	 * @param existenciaActual
	 * @return
	 * @throws ERPException
	 */
	Long obtenerCantidadTotalEntradas(Integer codigoCompania, Boolean existenciaActual) throws ERPException;
	
	/**
	 * M\u00e9todo para obtener valores total en costo de inventario
	 * @param codigoCompania
	 * @return
	 * @throws ERPException
	 */
	BigDecimal obtenerTotalExistencias(Integer codigoCompania) throws ERPException;
}
