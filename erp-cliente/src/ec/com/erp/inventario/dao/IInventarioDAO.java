package ec.com.erp.inventario.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.InventarioDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IInventarioDAO {
	
	/**
	 * M\u00e9todo para obtener kardex por codigo de barra y fechas
	 * @param codigoCompania
	 * @param codigoBarras
	 * @param fechaFacturaInicio
	 * @param fechaFacturaFin
	 * @return
	 * @throws ERPException
	 */
	Collection<InventarioDTO> obtenerListaInventarioByArticuloFechas(Integer codigoCompania, String codigoBarras, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin) throws ERPException;

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
