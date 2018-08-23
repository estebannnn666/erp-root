package ec.com.erp.inventario.servicios;

import java.sql.Timestamp;
import java.util.Collection;

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
	Collection<InventarioDTO> findObtenerListaInventarioByArticuloFechas(Integer codigoCompania, String codigoBarras, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin) throws ERPException;

	/**
	 * M\u00e9todo para crear o actualizar 
	 * @param inventarioDTO
	 * @throws ERPException
	 */
	void transCrearActualizarInventario(InventarioDTO inventarioDTO)throws ERPException;
	
}
