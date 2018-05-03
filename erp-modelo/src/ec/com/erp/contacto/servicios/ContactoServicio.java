package ec.com.erp.contacto.servicios;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ContactoDTO;
import ec.com.erp.contacto.gestor.IContactoGestor;

public class ContactoServicio implements IContactoServicio{
	
	private IContactoGestor contactoGestor;

	public IContactoGestor getContactoGestor() {
		return contactoGestor;
	}

	public void setContactoGestor(IContactoGestor contactoGestor) {
		this.contactoGestor = contactoGestor;
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
	public ContactoDTO findObtenerListaContactos(Integer codigoCompania, Long codigoPersona, Long codigoEmpresa) throws ERPException{
		return this.contactoGestor.obtenerListaContactos(codigoCompania, codigoPersona, codigoEmpresa);
	}

}
