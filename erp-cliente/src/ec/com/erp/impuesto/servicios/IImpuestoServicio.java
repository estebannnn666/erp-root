package ec.com.erp.impuesto.servicios;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ImpuestoDTO;

public interface IImpuestoServicio {
	
	/**
	 * M\u00e9todo para obtener lista de ImpuestoDTO
	 * @return 
	 * @throws ERPException
	 */
	Collection<ImpuestoDTO> findObtenerListaImpuestos(Integer codigoCompania, String nombreImpuesto, String descripcion) throws ERPException;
	
	/**
	 * Metodo para guardar y actualizar impuesto
	 * @param articuloDTO
	 * @throws ERPException
	 */
	void transGuardarActualizarImpuesto(ImpuestoDTO impuestoDTO) throws ERPException;
}
