package ec.com.erp.secuencia.servicios;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.SecuenciaDTO;
import ec.com.erp.secuencia.gestor.ISecuenciaGestor;

public class SecuenciaServicio implements ISecuenciaServicio{
	
	private ISecuenciaGestor secuenciaGestor;
	
	public ISecuenciaGestor getSecuenciaGestor() {
		return secuenciaGestor;
	}

	public void setSecuenciaGestor(ISecuenciaGestor secuenciaGestor) {
		this.secuenciaGestor = secuenciaGestor;
	}

	/**
	 * M\u00e9todo para obtener secuencia por nombre
	 * @param nombreSecuencia
	 * @return
	 * @throws ERPException
	 */
	@Override
	public SecuenciaDTO findObtenerSecuenciaByNombre(String nombreSecuencia) throws ERPException{
		return this.secuenciaGestor.obtenerSecuenciaByNombre(nombreSecuencia);
	}

}
