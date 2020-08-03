package ec.com.erp.vendedor.servicios;

import java.sql.Timestamp;
import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ContactoDTO;
import ec.com.erp.cliente.mdl.dto.FacturaCabeceraDTO;
import ec.com.erp.cliente.mdl.dto.VendedorDTO;


/**
 * @author Esteban Gudino
 *
 */

public interface IVendedorServicio {
	
	/**
	 * Obtener vendedor por documento
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @return
	 * @throws ERPException
	 */
	VendedorDTO findObtenerVendedor(Integer codigoCompania, String numeroDocumento) throws ERPException;
		
	/**
	 * M\u00e9todo para obtener lista de vendedor
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @param nombreVendedor
	 * @return 
	 * @throws ERPException
	 */
	Collection<VendedorDTO> findObtenerListaVendedores(Integer codigoCompania, String numeroDocumento, String nombreVendedor) throws ERPException;
	
	/**
	 * Metodo para obtener lista de facturas por fecha y vendedor
	 * @param codigoCompania
	 * @param codigoVendedor
	 * @param fechaFacturaInicio
	 * @param fechaFacturaFin
	 * @return
	 */
	Collection<FacturaCabeceraDTO> findListaFacturasPorVendedorFechaVenta(Integer codigoCompania, Long codigoVendedor, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin);
	
	/**
	 * M\u009etodo para guardar y actualizar vendedor
	 * @param vendedorDTO
	 * @param contactoDTO
	 * @throws ERPException
	 */
	void transGuardarActualizarVendedor(VendedorDTO vendedorDTO, ContactoDTO contactoDTO) throws ERPException;
		
}
