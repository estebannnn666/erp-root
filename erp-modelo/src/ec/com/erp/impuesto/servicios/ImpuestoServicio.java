package ec.com.erp.impuesto.servicios;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ImpuestoDTO;
import ec.com.erp.impuesto.gestor.IImpuestoGestor;

public class ImpuestoServicio implements IImpuestoServicio{
	
	private IImpuestoGestor impuestoGestor;

	public IImpuestoGestor getImpuestoGestor() {
		return impuestoGestor;
	}

	public void setImpuestoGestor(IImpuestoGestor impuestoGestor) {
		this.impuestoGestor = impuestoGestor;
	}

	/**
	 * M\u00e9todo para obtener lista de ImpuestoDTO
	 * @return 
	 * @throws ERPException
	 */
	@Override
	public Collection<ImpuestoDTO> findObtenerListaImpuestos(Integer codigoCompania, String nombreImpuesto, String descripcion) throws ERPException{
		return this.impuestoGestor.obtenerListaImpuestos(codigoCompania, nombreImpuesto, descripcion);
	}
	
	/**
	 * Metodo para guardar y actualizar impuesto
	 * @param articuloDTO
	 * @throws ERPException
	 */
	@Override
	public void transGuardarActualizarImpuesto(ImpuestoDTO impuestoDTO) throws ERPException{
		this.impuestoGestor.guardarActualizarImpuesto(impuestoDTO); 
	}
}
