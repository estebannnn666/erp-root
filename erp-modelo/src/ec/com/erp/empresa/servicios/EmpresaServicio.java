package ec.com.erp.empresa.servicios;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.EmpresaDTO;
import ec.com.erp.empresa.gestor.IEmpresaGestor;

public class EmpresaServicio implements IEmpresaServicio{
	
	private IEmpresaGestor empresaGestor;

	public IEmpresaGestor getEmpresaGestor() {
		return empresaGestor;
	}

	public void setEmpresaGestor(IEmpresaGestor empresaGestor) {
		this.empresaGestor = empresaGestor;
	}

	/**
	 * M\u00E9todo para obtener la empresa por codigo
	 * @param codigoCompania
	 * @param codigoEmpresa
	 * @return
	 * @throws ERPException
	 */
	@Override
	public EmpresaDTO findObtenerEmpresaByCodigo(Integer codigoCompania, Long codigoEmpresa) throws ERPException{
		return this.empresaGestor.obtenerEmpresaByCodigo(codigoCompania, codigoEmpresa);
	}

}
