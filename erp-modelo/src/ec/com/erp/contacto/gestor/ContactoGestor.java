package ec.com.erp.contacto.gestor;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ContactoDTO;
import ec.com.erp.contacto.dao.IContactoDAO;

public class ContactoGestor implements IContactoGestor{

	private IContactoDAO contactoDAO;
	
	public IContactoDAO getContactoDAO() {
		return contactoDAO;
	}

	public void setContactoDAO(IContactoDAO contactoDAO) {
		this.contactoDAO = contactoDAO;
	}

	/**
	 * M\u00e9todo para obtener el contacto por persona o empresa
	 * @param codigoCompania
	 * @param codigoPersona
	 * @param codigoEmpresa
	 * @return
	 * @throws ERPException
	 */
	@Override
	public ContactoDTO obtenerListaContactos(Integer codigoCompania, Long codigoPersona, Long codigoEmpresa) throws ERPException{
		return this.contactoDAO.obtenerListaContactos(codigoCompania, codigoPersona, codigoEmpresa);
	}
	
	/**
	 * M\u00e9todo para crear o actualizar 
	 * @param contactoDTO
	 * @throws ERPException
	 */
	public void crearActualizarContacto(ContactoDTO contactoDTO)throws ERPException{
		this.contactoDAO.crearActualizarContacto(contactoDTO);
	}
}
