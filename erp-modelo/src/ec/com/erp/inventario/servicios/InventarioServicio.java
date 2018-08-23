package ec.com.erp.inventario.servicios;

import java.sql.Timestamp;
import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.InventarioDTO;
import ec.com.erp.inventario.gestor.IInventarioGestor;

public class InventarioServicio implements IInventarioServicio{
	
	private IInventarioGestor inventarioGestor;

	public IInventarioGestor getInventarioGestor() {
		return inventarioGestor;
	}

	public void setInventarioGestor(IInventarioGestor inventarioGestor) {
		this.inventarioGestor = inventarioGestor;
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
	public Collection<InventarioDTO> findObtenerListaInventarioByArticuloFechas(Integer codigoCompania, String codigoBarras, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin) throws ERPException{
		return this.inventarioGestor.obtenerListaInventarioByArticuloFechas(codigoCompania, codigoBarras, fechaFacturaInicio, fechaFacturaFin);
	}

	/**
	 * M\u00e9todo para crear o actualizar 
	 * @param inventarioDTO
	 * @throws ERPException
	 */
	@Override
	public void transCrearActualizarInventario(InventarioDTO inventarioDTO)throws ERPException{
		this.inventarioGestor.crearActualizarInventario(inventarioDTO);
	}
}
