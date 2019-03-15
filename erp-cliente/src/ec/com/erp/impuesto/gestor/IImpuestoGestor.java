package ec.com.erp.impuesto.gestor;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ImpuestoDTO;

public interface IImpuestoGestor {
	
	/**
	 * M\u00e9todo para obtener lista de ImpuestoDTO
	 * @return 
	 * @throws ERPException
	 */
	Collection<ImpuestoDTO> obtenerListaImpuestos(Integer codigoCompania, String nombreImpuesto, String descripcion) throws ERPException;
	
	/**
	 * Metodo para guardar y actualizar impuesto
	 * @param articuloDTO
	 * @throws ERPException
	 */
	void guardarActualizarImpuesto(ImpuestoDTO impuestoDTO) throws ERPException;
	
}
