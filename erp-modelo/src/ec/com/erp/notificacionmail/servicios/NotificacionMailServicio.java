package ec.com.erp.notificacionmail.servicios;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.notificacionmail.gestor.INotificacionMailGestor;

public class NotificacionMailServicio implements INotificacionMailServicio{
	
	private INotificacionMailGestor notificacionMailGestor;

	public INotificacionMailGestor getNotificacionMailGestor() {
		return notificacionMailGestor;
	}

	public void setNotificacionMailGestor(INotificacionMailGestor notificacionMailGestor) {
		this.notificacionMailGestor = notificacionMailGestor;
	}

	@Override
	public void findEnviarFacturaMail(String para, byte[] invoice) throws ERPException {
		this.notificacionMailGestor.enviarFacturaMail(para, invoice);
	}
}
