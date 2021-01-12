package ec.com.erp.secuencia.gestor;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.SecuenciaDTO;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;

public class SecuenciaGestor implements ISecuenciaGestor{

	private ISecuenciaDAO secuenciaDAO;

	public ISecuenciaDAO getSecuenciaDAO() {
		return secuenciaDAO;
	}

	public void setSecuenciaDAO(ISecuenciaDAO secuenciaDAO) {
		this.secuenciaDAO = secuenciaDAO;
	}

	/**
	 * M\u00e9todo para obtener secuencia por nombre
	 * @param nombreSecuencia
	 * @return
	 * @throws ERPException
	 */
	@Override
	public SecuenciaDTO obtenerSecuenciaByNombre(String nombreSecuencia) throws ERPException{
		return this.secuenciaDAO.obtenerSecuenciaByNombre(nombreSecuencia);
	}
	
	/**
	 * Metodo para obtenere el secuencial para cualquier tabla
	 * @param nombreSecuencia
	 * @return
	 * @throws ERPException
	 */
	public Integer obtenerSecuencialTabla(String nombreSecuencia) throws ERPException{
		return this.secuenciaDAO.obtenerSecuencialTabla(nombreSecuencia);
	}
}
