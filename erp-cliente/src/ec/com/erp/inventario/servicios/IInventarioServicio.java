package ec.com.erp.inventario.servicios;

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

public interface IInventarioServicio {
	
	/**
	 * M\u00e9todo para obtener kardex por codigo de barra y fechas
	 * @param codigoCompania
	 * @param codigoBarras
	 * @param fechaFacturaInicio
	 * @param fechaFacturaFin
	 * @return
	 * @throws ERPException
	 */
	Collection<InventarioDTO> findObtenerListaInventarioByArticuloFechas(Integer codigoCompania, Integer codigoArticuloUnidadManejo, String codigoBarras, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin) throws ERPException;

	/**
	 * M\u00e9todo para obtener kardex por codigo de barra
	 * @param codigoCompania
	 * @param codigoBarras
	 * @return
	 * @throws ERPException
	 */
	InventarioDTO findObtenerUltimoInventarioByArticulo(Integer codigoCompania, String codigoBarras, Integer codigoArticuloUnidadManejo) throws ERPException;
	
	/**
	 * M\u00e9todo para crear o actualizar 
	 * @param inventarioDTO
	 * @throws ERPException
	 */
	void transCrearActualizarInventario(InventarioDTO inventarioDTO)throws ERPException;
	
	/**
	 * Devuelve html de reporte de inventarios
	 * @param inventarioDTOCols
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 * @throws ERPException
	 */
	String findObtenerXMLReporteKardex(Collection<InventarioDTO> inventarioDTOCols, Date fechaInicio, Date fechaFin) throws ERPException;
	
	/**
	 * M\u00e9todo para obtener existencias por codigo de barra y fechas
	 * @param codigoCompania
	 * @param codigoBarras
	 * @param fechaFacturaInicio
	 * @param fechaFacturaFin
	 * @return
	 * @throws ERPException
	 */
	Collection<InventarioDTO> findObtenerListaExistenciasByArticuloFechas(Integer codigoCompania, String codigoBarras, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin) throws ERPException;
	
	/**
	 * Devuelve html de reporte de existencias
	 * @param inventarioDTOCols
	 * @return
	 * @throws ERPException
	 */
	String findObtenerXMLReporteExistencias(Collection<InventarioDTO> inventarioDTOCols) throws ERPException;
	
	/**
	 * M\u00e9todo para obtener valores estadisticos de inventario
	 * @param codigoCompania
	 * @param existenciaActual
	 * @return
	 * @throws ERPException
	 */
	Long findObtenerCantidadTotalEntradas(Integer codigoCompania, Boolean existenciaActual) throws ERPException;
	
	/**
	 * M\u00e9todo para obtener valores total en costo de inventario
	 * @param codigoCompania
	 * @return
	 * @throws ERPException
	 */
	BigDecimal findObtenerTotalExistencias(Integer codigoCompania) throws ERPException;
}
