package ec.com.erp.compania.servicios;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.CompaniaDTO;
import ec.com.erp.compania.gestor.ICompaniaGestor;

public class CompaniaServicio implements ICompaniaServicio{
	
	private ICompaniaGestor companiaGestor;
	
	public ICompaniaGestor getCompaniaGestor() {
		return companiaGestor;
	}

	public void setCompaniaGestor(ICompaniaGestor companiaGestor) {
		this.companiaGestor = companiaGestor;
	}

	/**
	 * M\u00e9todo para obtener lista de companias
	 * @return 
	 * @throws ERPException
	 */
	public Collection<CompaniaDTO> findObtenerListaCompanias() throws ERPException{
		return this.companiaGestor.obtenerListaCompanias();
	}
	
	/**
	 * M\u00e9todo para obtener la compania por codigo enviado como parametro
	 * @param codigoCompania
	 * @return
	 * @throws ERPException
	 */
	public CompaniaDTO findObtenerListaCompaniasByCodigo(Integer codigoCompania) throws ERPException{
		return this.companiaGestor.obtenerListaCompaniasByCodigo(codigoCompania);
	}

}
