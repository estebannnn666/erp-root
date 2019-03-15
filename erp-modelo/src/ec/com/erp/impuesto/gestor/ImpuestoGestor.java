package ec.com.erp.impuesto.gestor;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ImpuestoDTO;
import ec.com.erp.impuesto.dao.IImpuestoDAO;

public class ImpuestoGestor implements IImpuestoGestor{

	private IImpuestoDAO impuestoDAO;
	
	public IImpuestoDAO getImpuestoDAO() {
		return impuestoDAO;
	}

	public void setImpuestoDAO(IImpuestoDAO impuestoDAO) {
		this.impuestoDAO = impuestoDAO;
	}

	/**
	 * M\u00e9todo para obtener lista de ImpuestoDTO
	 * @return 
	 * @throws ERPException
	 */
	@Override
	public Collection<ImpuestoDTO> obtenerListaImpuestos(Integer codigoCompania, String nombreImpuesto, String descripcion) throws ERPException{
		return this.impuestoDAO.obtenerListaImpuestos(codigoCompania, nombreImpuesto, descripcion);
	}
	
	/**
	 * Metodo para guardar y actualizar impuesto
	 * @param articuloDTO
	 * @throws ERPException
	 */
	@Override
	public void guardarActualizarImpuesto(ImpuestoDTO impuestoDTO) throws ERPException{
		this.impuestoDAO.guardarActualizarImpuesto(impuestoDTO); 
	}
}
