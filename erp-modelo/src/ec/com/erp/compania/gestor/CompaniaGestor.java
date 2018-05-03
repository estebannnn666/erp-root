package ec.com.erp.compania.gestor;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.CompaniaDTO;
import ec.com.erp.compania.dao.ICompaniaDAO;

public class CompaniaGestor implements ICompaniaGestor{

	private ICompaniaDAO companiaDAO;
	
	public ICompaniaDAO getCompaniaDAO() {
		return companiaDAO;
	}

	public void setCompaniaDAO(ICompaniaDAO companiaDAO) {
		this.companiaDAO = companiaDAO;
	}

	/**
	 * M\u00e9todo para obtener lista de companias
	 * @return 
	 * @throws ERPException
	 */
	public Collection<CompaniaDTO> obtenerListaCompanias() throws ERPException{
		return this.companiaDAO.obtenerListaCompanias();
	}
	
	/**
	 * M\u00e9todo para obtener la compania por codigo enviado como parametro
	 * @param codigoCompania
	 * @return
	 * @throws ERPException
	 */
	public CompaniaDTO obtenerListaCompaniasByCodigo(Integer codigoCompania) throws ERPException{
		return this.companiaDAO.obtenerListaCompaniasByCodigo(codigoCompania);
	}
}
