package ec.com.erp.inventario.gestor;

import java.sql.Timestamp;
import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.InventarioDTO;
import ec.com.erp.inventario.dao.IInventarioDAO;

public class InventarioGestor implements IInventarioGestor{

	private IInventarioDAO inventarioDAO;
	
	public IInventarioDAO getInventarioDAO() {
		return inventarioDAO;
	}

	public void setInventarioDAO(IInventarioDAO inventarioDAO) {
		this.inventarioDAO = inventarioDAO;
	}

	/**
	 * M\u00e9todo para obtener kardex por codigo de barra y fechas
	 * @param codigoCompania
	 * @param codigoBarras
	 * @param fechaFacturaInicio
	 * @param fechaFacturaFin
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Collection<InventarioDTO> obtenerListaInventarioByArticuloFechas(Integer codigoCompania, String codigoBarras, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin) throws ERPException{
		return this.inventarioDAO.obtenerListaInventarioByArticuloFechas(codigoCompania, codigoBarras, fechaFacturaInicio, fechaFacturaFin);
	}

	/**
	 * M\u00e9todo para obtener kardex por codigo de barra
	 * @param codigoCompania
	 * @param codigoBarras
	 * @return
	 * @throws ERPException
	 */
	@Override
	public InventarioDTO obtenerUltimoInventarioByArticulo(Integer codigoCompania, String codigoBarras) throws ERPException{
		return this.obtenerUltimoInventarioByArticulo(codigoCompania, codigoBarras);
	}
	
	/**
	 * M\u00e9todo para crear o actualizar 
	 * @param inventarioDTO
	 * @throws ERPException
	 */
	@Override
	public void crearActualizarInventario(InventarioDTO inventarioDTO)throws ERPException{
		this.inventarioDAO.crearActualizarInventario(inventarioDTO);
	}
	
}
