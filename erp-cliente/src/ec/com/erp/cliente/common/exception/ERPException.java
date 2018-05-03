package ec.com.erp.cliente.common.exception;

import java.util.Collection;

/**
 * @author Esteban Gudino
 * 2017-06-26
 */
@SuppressWarnings("serial")
public class ERPException extends RuntimeException{
	
	private String codigoError;
	private String descripcionError;
	private Boolean showErrorMessage;
	private Collection<String> descripcionesErrores;
	private Object entidadError;

	
	/**
	 * Constructor por defecto. 
	 */
	public ERPException(){
		super();
	}
	
	public ERPException(String message){
		super(message);
	}

	/**
	 * @param message Mensaje de error.
	 */
	public ERPException(String message, Boolean showErrorMessage){
		this(message);
		this.descripcionError = message;
		this.showErrorMessage = showErrorMessage;
	}
	
	public ERPException(Collection<String> descripcionesErrores, Boolean showErrorMessage){
		super();
		this.descripcionesErrores = descripcionesErrores;
		this.showErrorMessage = showErrorMessage;
	}

	/**
	 * @param message Mensaje de error.
	 * @param cause Causa del error.
	 */
	public ERPException(String message, Throwable cause){
		super(message, cause);
	}

	/**
	 * @param cause Causa del Error.
	 */
	public ERPException(Throwable cause){
		super(cause);
	}
	/**
	 * @param codigoError  					codigo del error
	 * @param descripcionError 			descripcion del error
	 */
	public ERPException(String codigoError, String descripcionError){
		super(descripcionError, null);
		this.codigoError = codigoError;
		this.descripcionError = descripcionError;
	}
	/**
	 * @param codigoError  					codigo del error
	 * @param descripcionError 			descripcion del error
	 */
	public ERPException(String codigoError, String mensaje, String descripcionError){
		super(mensaje);
		this.codigoError = codigoError;
		this.descripcionError = descripcionError;
	}
	
	/**
	 * @param codigoError Codigo del error.
	 * @param message Mensaje de error.
	 * @param cause Causa del error.
	 */
	public ERPException(String codigoError, String message, Throwable cause){
		super(message, cause);
		this.codigoError = codigoError;
	}

	/**
	 * @return the codigoError
	 */
	public String getCodigoError() {
		return codigoError;
	}

	/**
	 * @param codigoError the codigoError to set
	 */
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	/**
	 * @return the descripcionError
	 */
	public String getDescripcionError() {
		return descripcionError;
	}

	/**
	 * @param descripcionError the descripcionError to set
	 */
	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}

	/**
	 * @return the showErrorMessage
	 */
	public Boolean getShowErrorMessage() {
		return showErrorMessage;
	}

	/**
	 * @param showErrorMessage the showErrorMessage to set
	 */
	public void setShowErrorMessage(Boolean showErrorMessage) {
		this.showErrorMessage = showErrorMessage;
	}

	/**
	 * @return the descripcionesErrores
	 */
	public Collection<String> getDescripcionesErrores() {
		return descripcionesErrores;
	}

	/**
	 * @param descripcionesErrores the descripcionesErrores to set
	 */
	public void setDescripcionesErrores(Collection<String> descripcionesErrores) {
		this.descripcionesErrores = descripcionesErrores;
	}

	public Object getEntidadError() {
		return entidadError;
	}

	public void setEntidadError(Object entidadError) {
		this.entidadError = entidadError;
	}

	
}

