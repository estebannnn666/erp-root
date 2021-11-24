/**
 * 
 */
package ec.com.erp.notacredito.gestor;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.cert.CertificateException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang3.StringUtils;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.common.resources.ERPMessages;
import ec.com.erp.cliente.mdl.dto.InventarioDTO;
import ec.com.erp.cliente.mdl.dto.NotaCreditoDTO;
import ec.com.erp.cliente.mdl.dto.NotaCreditoDetalleDTO;
import ec.com.erp.cliente.mdl.dto.NotaCreditoDocumentoDTO;
import ec.com.erp.cliente.mdl.dto.ParametroDTO;
import ec.com.erp.cliente.mdl.dto.TransaccionDTO;
import ec.com.erp.cliente.mdl.dto.id.NotaCreditoID;
import ec.com.erp.facturacion.electronica.ws.ConstruirFacturaUtil;
import ec.com.erp.facturacion.electronica.ws.FacturaElectronicaUtil;
import ec.com.erp.facturacion.electronica.ws.NotaCreditoElectronicaUtil;
import ec.com.erp.inventario.gestor.IInventarioGestor;
import ec.com.erp.notacredito.dao.INotaCreditoDAO;
import ec.com.erp.notificacionmail.gestor.INotificacionMailGestor;
import ec.com.erp.parametro.gestor.IParametroGestor;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.transaccion.gestor.ITransaccionGestor;

/**
 * @author Esteban Gudino
 * 2018-05-05
 */
public class NotaCreditoGestor implements INotaCreditoGestor {

	private INotaCreditoDAO notaCreditoDAO;
	private INotaCreditoDetalleGestor notaCreditoDetalleGestor;
	private IInventarioGestor inventarioGestor;
	private ITransaccionGestor transaccionGestor;
	private ISecuenciaDAO secuenciaDAO;
	private INotaCreditoDocumentoGestor notaCreditoDocumentoGestor;
	private INotificacionMailGestor notificacionMailGestor;
	private IParametroGestor parametroGestor;

	public INotaCreditoDAO getNotaCreditoDAO() {
		return notaCreditoDAO;
	}

	public void setNotaCreditoDAO(INotaCreditoDAO notaCreditoDAO) {
		this.notaCreditoDAO = notaCreditoDAO;
	}

	public INotaCreditoDetalleGestor getNotaCreditoDetalleGestor() {
		return notaCreditoDetalleGestor;
	}

	public void setNotaCreditoDetalleGestor(INotaCreditoDetalleGestor notaCreditoDetalleGestor) {
		this.notaCreditoDetalleGestor = notaCreditoDetalleGestor;
	}

	public IInventarioGestor getInventarioGestor() {
		return inventarioGestor;
	}

	public void setInventarioGestor(IInventarioGestor inventarioGestor) {
		this.inventarioGestor = inventarioGestor;
	}

	public ITransaccionGestor getTransaccionGestor() {
		return transaccionGestor;
	}

	public void setTransaccionGestor(ITransaccionGestor transaccionGestor) {
		this.transaccionGestor = transaccionGestor;
	}

	public ISecuenciaDAO getSecuenciaDAO() {
		return secuenciaDAO;
	}

	public void setSecuenciaDAO(ISecuenciaDAO secuenciaDAO) {
		this.secuenciaDAO = secuenciaDAO;
	}

	public INotaCreditoDocumentoGestor getNotaCreditoDocumentoGestor() {
		return notaCreditoDocumentoGestor;
	}

	public void setNotaCreditoDocumentoGestor(INotaCreditoDocumentoGestor notaCreditoDocumentoGestor) {
		this.notaCreditoDocumentoGestor = notaCreditoDocumentoGestor;
	}

	public INotificacionMailGestor getNotificacionMailGestor() {
		return notificacionMailGestor;
	}

	public void setNotificacionMailGestor(INotificacionMailGestor notificacionMailGestor) {
		this.notificacionMailGestor = notificacionMailGestor;
	}

	public IParametroGestor getParametroGestor() {
		return parametroGestor;
	}

	public void setParametroGestor(IParametroGestor parametroGestor) {
		this.parametroGestor = parametroGestor;
	}

	/**
	 * M\u00e9todo para obtener lista de notas de credito por filtros de busqueda
	 * @param codigoCompania
	 * @param numeroNotaCredito
	 * @param fechaNotaCreditoInicio
	 * @param fechaNotaCreditoFin
	 * @param docCliente
	 * @param nombreCliente
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Collection<NotaCreditoDTO> obtenerListaNotasCredito(Integer codigoCompania, String numeroNotaCredito, Timestamp fechaNotaCreditoInicio, Timestamp fechaNotaCreditoFin, String docCliente, String nombreCliente) throws ERPException {
		return this.notaCreditoDAO.obtenerListaNotasCredito(codigoCompania, numeroNotaCredito, fechaNotaCreditoInicio, fechaNotaCreditoFin, docCliente, nombreCliente);
	}

	/**
	 * M\u00e9todo para guardar y actualizar nota de credito
	 * @param crearNotaCreditoElectronica
	 * @param notaCreditoDTO
	 * @throws ERPException
	 */
	@Override
	public void guardarActualizarNotaCredito(Boolean crearNotaCreditoElectronica, NotaCreditoDTO notaCreditoDTO) throws ERPException{
		try{
			// Obtenemos la lista de detalle a guardar
			Collection<NotaCreditoDetalleDTO> notaCreditoDetalleDTOCols = notaCreditoDTO.getNotaCreditoDetalleDTOCols();
			// Obtener secuencia segun el ruc
			if(notaCreditoDTO.getTipoRuc().equals(ERPConstantes.TIPO_RUC_UNO)) {
				Integer secuenciaFactura = this.secuenciaDAO.obtenerSecuencialTabla(NotaCreditoID.NTC_ELECTRONICA_RUC_UNO);
				String numeroFactura = ConstruirFacturaUtil.numeroFactura(secuenciaFactura.toString());
				// Asignar numero de secuencia a la factura
				notaCreditoDTO.setNumeroDocumento(numeroFactura);
			}
			if(notaCreditoDTO.getTipoRuc().equals(ERPConstantes.TIPO_RUC_DOS)) {	
				Integer secuenciaFactura = this.secuenciaDAO.obtenerSecuencialTabla(NotaCreditoID.NTC_ELECTRONICA_RUC_UNO);
				String numeroFactura = ConstruirFacturaUtil.numeroFactura(secuenciaFactura.toString());
				// Asignar numero de secuencia a la factura
				notaCreditoDTO.setNumeroDocumento(numeroFactura);
			}
			
			// Guardamos la cabecera de la factura
			this.notaCreditoDAO.guardarActualizarNotaCredito(notaCreditoDTO);
			
			// Guardamos los detalle de la factura 
			for (NotaCreditoDetalleDTO notaCreditoDetalleDTO : notaCreditoDetalleDTOCols) {
				if(notaCreditoDetalleDTO != null && notaCreditoDetalleDTO.getCodigoArticulo() != null){
					notaCreditoDetalleDTO.getId().setCodigoCompania(Integer.parseInt(ERPConstantes.ESTADO_ACTIVO_NUMERICO));
					notaCreditoDetalleDTO.setCodigoNotaCredito(notaCreditoDTO.getId().getCodigoNotaCredito());
					notaCreditoDetalleDTO.setUsuarioRegistro(notaCreditoDTO.getUsuarioRegistro());
					this.reversarInventario(notaCreditoDetalleDTO, notaCreditoDTO.getNumeroDocumento(), notaCreditoDTO.getRazonSocial());
					this.notaCreditoDetalleGestor.guardarActualizarDetalleNotaCredito(notaCreditoDetalleDTO);
				}
			}
			
			// Guardar tipo de transaccion
			if(notaCreditoDTO.getPagado()) {
				TransaccionDTO transaccionDTO = new TransaccionDTO();
				transaccionDTO.getId().setCodigoCompania(notaCreditoDTO.getId().getCodigoCompania());
				transaccionDTO.setValorTransaccion(notaCreditoDTO.getTotalCuenta());
				transaccionDTO.setUsuarioRegistro(notaCreditoDTO.getUsuarioRegistro());
				transaccionDTO.setFechaTransaccion(notaCreditoDTO.getFechaDocumento());
				transaccionDTO.setCodigoValorTransaccion(ERPConstantes.CODIGO_CATALOGO_VALOR_TRANSACCION_GASTO);
				transaccionDTO.setConcepto("CANCELAR FACTURA ELECTRONICA NRO"+notaCreditoDTO.getNumeroComprobante());
				this.transaccionGestor.guardarTransaccion(transaccionDTO);
			}
			// Registrar factura electronica
			if(crearNotaCreditoElectronica) {
				// Generar factura electronica
				Map<String, Object> datosFacturaElectronica = this.generarNotaCreditoElectronica(notaCreditoDTO);
				// Obtener datos para guardar
				byte[] xmlDocument = (byte[])datosFacturaElectronica.get("XMLDOCUMENT");
				NotaCreditoDocumentoDTO notaCreditoDocumentoDTO = new NotaCreditoDocumentoDTO();
				notaCreditoDocumentoDTO.getId().setCodigoCompania(notaCreditoDTO.getId().getCodigoCompania());
				notaCreditoDocumentoDTO.setCodigoNotaCredito(notaCreditoDTO.getId().getCodigoNotaCredito());
				notaCreditoDocumentoDTO.setXmlNotaCredito(xmlDocument);
				notaCreditoDocumentoDTO.setUsuarioRegistro(notaCreditoDTO.getUsuarioRegistro());
				notaCreditoDocumentoDTO.setFechaRegistro(notaCreditoDTO.getFechaRegistro());
				this.notaCreditoDocumentoGestor.guardarActualizarDocumentoNotaCredito(notaCreditoDocumentoDTO);
				// Actualizar el numero de documento
				String numeroNotaCreditoElectronica = (String)datosFacturaElectronica.get("NROFACTURA");	
				this.notaCreditoDAO.actualizarNotaCreditoNumeroNotaCredito(notaCreditoDTO.getId().getCodigoCompania(), notaCreditoDTO.getId().getCodigoNotaCredito(), notaCreditoDTO.getUsuarioRegistro(), numeroNotaCreditoElectronica);
				// Enviar correo con factura
				try{
					if(StringUtils.isNotBlank(notaCreditoDTO.getEmail())){
						byte[] invoice = this.obtenerDocumentoElectronicoElectronica(xmlDocument);
						this.notificacionMailGestor.enviarFacturaMail(notaCreditoDTO.getEmail(), invoice);
					}
				}catch (Exception e) {
					System.out.println("Error al enviar email: "+e);
				}
			}
			
		} catch (ERPException e) {
			throw new ERPException("Error, {0}",e.getMessage()) ;
		} catch (Exception e) {
			throw new ERPException("Error, {0}",e.getCause());
		} 
	}
	
	/**
	 * Metodo para registrar movimiento de mercaderia reversa
	 * @param notaCreditoDetalleDTO
	 */
	public void reversarInventario(NotaCreditoDetalleDTO notaCreditoDetalleDTO, String numeroNotaCredito, String razonSocial) {
		InventarioDTO inventarioDTOAux = this.inventarioGestor.obtenerUltimoInventarioByArticulo(notaCreditoDetalleDTO.getId().getCodigoCompania(), notaCreditoDetalleDTO.getArticuloDTO().getCodigoBarras(), notaCreditoDetalleDTO.getCodigoArticuloUnidadManejo());
		InventarioDTO inventarioDTO = new InventarioDTO();
		if(inventarioDTOAux != null) {
			inventarioDTO.getId().setCodigoCompania(notaCreditoDetalleDTO.getId().getCodigoCompania());
			inventarioDTO.setDetalleMoviento(ERPMessages.getString("ec.com.erp.cliente.mensaje.controlado.descripcion.invetarios.reversa")+" "+numeroNotaCredito+" Cliente: "+razonSocial);
			inventarioDTO.setArticuloDTO(notaCreditoDetalleDTO.getArticuloDTO());
			inventarioDTO.setCodigoArticulo(notaCreditoDetalleDTO.getCodigoArticulo());
			inventarioDTO.setCodigoArticuloUnidadManejo(notaCreditoDetalleDTO.getCodigoArticuloUnidadManejo());
			inventarioDTO.setCantidadEntrada(notaCreditoDetalleDTO.getCantidad());
			inventarioDTO.setValorUnidadEntrada(notaCreditoDetalleDTO.getArticuloDTO().getCosto());
			inventarioDTO.setValorTotalEntrada(BigDecimal.valueOf(notaCreditoDetalleDTO.getArticuloDTO().getCosto().doubleValue() * notaCreditoDetalleDTO.getCantidad()));
			inventarioDTO.setCantidadExistencia(inventarioDTOAux.getCantidadExistencia().intValue() + notaCreditoDetalleDTO.getCantidad());
			inventarioDTO.setValorUnidadExistencia(notaCreditoDetalleDTO.getArticuloDTO().getCosto());
			Integer totalUnidades = inventarioDTO.getCantidadExistencia() * notaCreditoDetalleDTO.getArticuloUnidadManejoDTO().getValorUnidadManejo();
			inventarioDTO.setValorTotalExistencia(BigDecimal.valueOf(totalUnidades.intValue() * notaCreditoDetalleDTO.getArticuloDTO().getCosto().doubleValue()));
			inventarioDTO.setCantidadSalida(null);
			inventarioDTO.setValorUnidadSalida(null);
			inventarioDTO.setValorTotalSalida(null);
			inventarioDTO.setUsuarioRegistro(notaCreditoDetalleDTO.getUsuarioRegistro());
			this.inventarioGestor.crearActualizarInventario(inventarioDTO);
		} else {
			throw new ERPException("Error", "No se puede registrar la resersa por que no hay existencias para el articulo "+notaCreditoDetalleDTO.getArticuloDTO().getNombreArticulo());
		}
	}
	
	private Map<String, Object> generarNotaCreditoElectronica(NotaCreditoDTO facturaCabeceraDTO) {
		try {
			Collection<String> codigosParametros = new ArrayList<>();
			codigosParametros.add(ERPConstantes.PARAMETRO_NOMBRE_ESTABLECIMIENTO);
			codigosParametros.add(ERPConstantes.PARAMETRO_NOMBRE_PUNTO_EMISION);
			Collection<ParametroDTO> parametrosFactura = this.parametroGestor.obtenerParametrosByCodigos(codigosParametros);
			ParametroDTO parametroDTOEsta = parametrosFactura.stream().filter(param -> param.getId().getCodigoParametro().equals(ERPConstantes.PARAMETRO_NOMBRE_ESTABLECIMIENTO)).findFirst().orElse(null);
			ParametroDTO parametroDTOPunt = parametrosFactura.stream().filter(param -> param.getId().getCodigoParametro().equals(ERPConstantes.PARAMETRO_NOMBRE_PUNTO_EMISION)).findFirst().orElse(null);
			return NotaCreditoElectronicaUtil.generarNotaCreditoElectronica(facturaCabeceraDTO, parametroDTOEsta.getValorParametro(), parametroDTOPunt.getValorParametro());
		} catch (SAXParseException e) {
			throw new ERPException("Error", "Error al generar nota credito electronica"+e.getMessage()) ;
		} catch (CertificateException e) {
			throw new ERPException("Error", "Error al generar nota credito electronica"+e.getMessage()) ;
		} catch (SAXException e) {
			throw new ERPException("Error", "Error al generar nota credito electronica"+e.getMessage()) ;
		} catch (IOException e) {
			throw new ERPException("Error", "Error al generar nota credito electronica"+e.getMessage()) ;
		} catch (JAXBException e) {
			throw new ERPException("Error", "Error al generar nota credito electronica"+e.getMessage()) ;
		} catch (InterruptedException e) {
			throw new ERPException("Error", "Error al generar nota credito electronica"+e.getMessage()) ;
		}
	}
	
	private byte[] obtenerDocumentoElectronicoElectronica(byte[] xmlFactura) throws IOException {  
		try {
			return FacturaElectronicaUtil.imprimirRideFactura(xmlFactura);
		} catch (Exception e) {
			throw new ERPException("Error", "Error al obtener nota credito electronica"+e.getMessage()) ;
		} 
	}
}
