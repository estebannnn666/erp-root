package ec.com.erp.parametro.servicios;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ParametroDTO;
import ec.com.erp.parametro.gestor.IParametroGestor;

public class ParametroServicio implements IParametroServicio{
	
	private IParametroGestor parametroGestor;

	public IParametroGestor getParametroGestor() {
		return parametroGestor;
	}

	public void setParametroGestor(IParametroGestor parametroGestor) {
		this.parametroGestor = parametroGestor;
	}

	/**
	 * M\u00e9todo para obtener lista de parametros.
	 * @return 
	 * @throws ERPException
	 */
	@Override
	public Collection<ParametroDTO> findObtenerParametrosByCodigos(Collection<String> codigosParametros)throws ERPException {
		return this.parametroGestor.obtenerParametrosByCodigos(codigosParametros);
	}

	/**
	 * M\u00e9todo para obtener parametro por codigo.
	 * @return 
	 * @throws ERPException
	 */
	@Override
	public ParametroDTO findObtenerParametroByCodigo(String codigoParametro) throws ERPException {
		return this.parametroGestor.obtenerParametroByCodigo(codigoParametro);
	}
}
