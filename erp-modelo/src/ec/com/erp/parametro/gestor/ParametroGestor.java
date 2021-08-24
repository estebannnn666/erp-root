package ec.com.erp.parametro.gestor;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ParametroDTO;
import ec.com.erp.parametro.dao.IParametroDAO;

public class ParametroGestor implements IParametroGestor{

	private IParametroDAO parametroDAO;

	public IParametroDAO getParametroDAO() {
		return parametroDAO;
	}

	public void setParametroDAO(IParametroDAO parametroDAO) {
		this.parametroDAO = parametroDAO;
	}

	/**
	 * M\u00e9todo para obtener lista de parametros.
	 * @return 
	 * @throws ERPException
	 */
	@Override
	public Collection<ParametroDTO> obtenerParametrosByCodigos(Collection<String> codigosParametros)throws ERPException {
		return this.parametroDAO.obtenerParametrosByCodigos(codigosParametros);
	}

	/**
	 * M\u00e9todo para obtener parametro por codigo.
	 * @return 
	 * @throws ERPException
	 */
	@Override
	public ParametroDTO obtenerParametroByCodigo(String codigoParametro) throws ERPException {
		return this.parametroDAO.obtenerParametroByCodigo(codigoParametro);
	}
}
