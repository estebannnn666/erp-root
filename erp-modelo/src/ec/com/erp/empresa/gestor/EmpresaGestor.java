package ec.com.erp.empresa.gestor;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.EmpresaDTO;
import ec.com.erp.empresa.dao.IEmpresaDAO;

public class EmpresaGestor implements IEmpresaGestor{

	private IEmpresaDAO empresaDAO;
	
	public IEmpresaDAO getEmpresaDAO() {
		return empresaDAO;
	}

	public void setEmpresaDAO(IEmpresaDAO empresaDAO) {
		this.empresaDAO = empresaDAO;
	}

	/**
	 * M\u00E9todo para obtener la empresa por codigo
	 * @param codigoCompania
	 * @param numeroRuc
	 * @return
	 * @throws ERPException
	 */
	@Override
	public EmpresaDTO obtenerEmpresaByCodigo(Integer codigoCompania, String numeroRuc) throws ERPException{
		return this.empresaDAO.obtenerEmpresaByCodigo(codigoCompania, numeroRuc);
	}
	
	/**
	 * M\u00E9todo para crear o actualizar empresa
	 * @param empresaDTO
	 * @throws ERPException
	 */
	public void crearActualizarEmpresa(EmpresaDTO empresaDTO) throws ERPException{
		if(empresaDTO.getId().getCodigoEmpresa() == null) {
			EmpresaDTO empresaExistente = this.empresaDAO.obtenerEmpresaByCodigo(empresaDTO.getId().getCodigoCompania(), empresaDTO.getNumeroRuc());
			if(empresaExistente != null) {
				throw new ERPException("Error", "La Empresa con numero de RUC "+empresaDTO.getNumeroRuc()+" ya existe.");
			}
		}
		this.empresaDAO.crearActualizarEmpresa(empresaDTO);
	}
}
