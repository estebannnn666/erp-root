package ec.com.erp.notificacionmail.gestor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.collections.CollectionUtils;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.common.resources.ERPMessages;
import ec.com.erp.cliente.mdl.dto.ParametroDTO;
import ec.com.erp.parametro.gestor.IParametroGestor;

public class NotificacionMailGestor implements INotificacionMailGestor{

	private IParametroGestor parametroGestor;
	
	public IParametroGestor getParametroGestor() {
		return parametroGestor;
	}

	public void setParametroGestor(IParametroGestor parametroGestor) {
		this.parametroGestor = parametroGestor;
	}

	/**
	 * Method for send mail with invoice.
	 */
	@Override
	public void enviarFacturaMail(String para, byte[] invoice) throws ERPException {
		try{
			Collection<String> codigoParametros = new ArrayList<>();
			codigoParametros.add(ERPConstantes.PARAMETRO_EMAIL_REMITENTE);
			codigoParametros.add(ERPConstantes.PARAMETRO_EMAIL_CONTRASENIA);
			codigoParametros.add(ERPConstantes.PARAMETRO_EMAIL_CUERPO);
			Collection<ParametroDTO> parametroDTOCols = this.parametroGestor.obtenerParametrosByCodigos(codigoParametros);
			String asunto = ERPMessages.getString("ec.com.erp.cliente.plantilla.factura.electronica.mail.asunto");
			enviarConGMail(para, asunto, parametroDTOCols, invoice);
		} catch (ERPException e) {
			throw new ERPException("Error, {0}",e.getMessage()) ;
		} catch (Exception e) {
			throw new ERPException("Error, {0}",e.getCause());
		} 
	}
	
	private void enviarConGMail(String destinatario, String asunto, Collection<ParametroDTO> parametroDTOCols, byte[] invoice) {
		try {
			// Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
			if(CollectionUtils.isEmpty(parametroDTOCols)){
				throw new ERPException("Error", "Error por favor configure los parametros del remitente para las notificaciones.");
			}
			ParametroDTO parametroDTO = parametroDTOCols.stream().filter(param -> param.getId().getCodigoParametro().equals(ERPConstantes.PARAMETRO_EMAIL_REMITENTE)).findFirst().orElse(null);
			ParametroDTO parmContrDTO = parametroDTOCols.stream().filter(param -> param.getId().getCodigoParametro().equals(ERPConstantes.PARAMETRO_EMAIL_CONTRASENIA)).findFirst().orElse(null);
			ParametroDTO parmCuerpoDTO = parametroDTOCols.stream().filter(param -> param.getId().getCodigoParametro().equals(ERPConstantes.PARAMETRO_EMAIL_CUERPO)).findFirst().orElse(null);
		    Properties props = System.getProperties();
		    props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
		    props.put("mail.smtp.user", parametroDTO.getValorParametro());
		    props.put("mail.smtp.clave", parmContrDTO.getValorParametro());    //La clave de la cuenta
		    props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
		    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
		    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
		    
		    Session session = Session.getDefaultInstance(props);
		    MimeMessage message = new MimeMessage(session);
	    
	    	 // Se compone la parte del texto
	        BodyPart texto = new MimeBodyPart();
	        texto.setText(parmCuerpoDTO.getValorParametro());
	        
	        //Create archivo adjunto
	        ByteArrayDataSource dataSource = new ByteArrayDataSource(invoice, "application/pdf");
	        DataHandler dataHandler = new DataHandler(dataSource);
	        
	        // Se compone el adjunto con la imagen
	        BodyPart adjunto = new MimeBodyPart();
	        adjunto.setDataHandler(dataHandler);
	        adjunto.setFileName("documento_electronico.pdf");

	        // Una MultiParte para agrupar texto e imagen.
	        MimeMultipart multiParte = new MimeMultipart();
	        multiParte.addBodyPart(texto);
	        multiParte.addBodyPart(adjunto);
	        
	        message.setFrom(new InternetAddress(parametroDTO.getValorParametro()));
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));   //Se podrían añadir varios de la misma manera
	        message.setSubject(asunto);
	        message.setContent(multiParte);
	        
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", parametroDTO.getValorParametro(), parmContrDTO.getValorParametro());
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }catch (MessagingException me) {
	    	throw new ERPException("Error", "Error al enviar correo, "+me.getMessage());
	    }catch (Exception e) {
			throw new ERPException("Error,"+e.getMessage());
		} 
	}

	
}
