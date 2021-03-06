package ec.com.erp.vendedor.dao;

import java.sql.Timestamp;
import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.FacturaCabeceraDTO;
import ec.com.erp.cliente.mdl.dto.VendedorDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IVendedorDAO {
	
	/**
	 * M\u00e9todo para obtener lista de vendedor
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @param nombreVendedor
	 * @return 
	 * @throws ERPException
	 */
	Collection<VendedorDTO> obtenerListaVendedores(Integer codigoCompania, String numeroDocumento, String nombreVendedor) throws ERPException;
	
	/**
	 * Metodo para obtener lista de facturas por fecha y vendedor
	 * @param codigoCompania
	 * @param codigoVendedor
	 * @param fechaFacturaInicio
	 * @param fechaFacturaFin
	 * @return
	 */
	Collection<FacturaCabeceraDTO> listaFacturasPorVendedorFechaVenta(Integer codigoCompania, Long codigoVendedor, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin);
	
	/**
	 * M\u009etodo para guardar y actualizar vendedor
	 * @param vendedorDTO
	 * @param contactoDTO
	 * @throws ERPException
	 */
	void guardarActualizarVendedor(VendedorDTO vendedorDTO) throws ERPException;
}
