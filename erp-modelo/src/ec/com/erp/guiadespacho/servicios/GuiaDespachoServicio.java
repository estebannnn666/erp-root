package ec.com.erp.guiadespacho.servicios;

import java.sql.Timestamp;
import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.GuiaDespachoDTO;
import ec.com.erp.cliente.mdl.dto.GuiaDespachoDetalleDTO;
import ec.com.erp.cliente.mdl.dto.GuiaDespachoExtrasDTO;
import ec.com.erp.cliente.mdl.dto.GuiaDespachoFacturaDTO;
import ec.com.erp.cliente.mdl.dto.GuiaDespachoPedidoDTO;
import ec.com.erp.guiadespacho.gestor.IGuiaDespachoDetalleGestor;
import ec.com.erp.guiadespacho.gestor.IGuiaDespachoExtrasGestor;
import ec.com.erp.guiadespacho.gestor.IGuiaDespachoFacturaGestor;
import ec.com.erp.guiadespacho.gestor.IGuiaDespachoGestor;
import ec.com.erp.guiadespacho.gestor.IGuiaDespachoPedidoGestor;

public class GuiaDespachoServicio implements IGuiaDespachoServicio{
	
	private IGuiaDespachoGestor guiaDespachoGestor;
	private IGuiaDespachoPedidoGestor guiaDespachoPedidoGestor;
	private IGuiaDespachoFacturaGestor guiaDespachoFacturaGestor;
	private IGuiaDespachoExtrasGestor guiaDespachoExtrasGestor;
	private IGuiaDespachoDetalleGestor guiaDespachoDetalleGestor;

	public IGuiaDespachoGestor getGuiaDespachoGestor() {
		return guiaDespachoGestor;
	}

	public void setGuiaDespachoGestor(IGuiaDespachoGestor guiaDespachoGestor) {
		this.guiaDespachoGestor = guiaDespachoGestor;
	}
	
	public IGuiaDespachoPedidoGestor getGuiaDespachoPedidoGestor() {
		return guiaDespachoPedidoGestor;
	}

	public void setGuiaDespachoPedidoGestor(IGuiaDespachoPedidoGestor guiaDespachoPedidoGestor) {
		this.guiaDespachoPedidoGestor = guiaDespachoPedidoGestor;
	}
	
	public IGuiaDespachoExtrasGestor getGuiaDespachoExtrasGestor() {
		return guiaDespachoExtrasGestor;
	}

	public void setGuiaDespachoExtrasGestor(IGuiaDespachoExtrasGestor guiaDespachoExtrasGestor) {
		this.guiaDespachoExtrasGestor = guiaDespachoExtrasGestor;
	}
	
	public IGuiaDespachoDetalleGestor getGuiaDespachoDetalleGestor() {
		return guiaDespachoDetalleGestor;
	}

	public void setGuiaDespachoDetalleGestor(IGuiaDespachoDetalleGestor guiaDespachoDetalleGestor) {
		this.guiaDespachoDetalleGestor = guiaDespachoDetalleGestor;
	}
	
	public IGuiaDespachoFacturaGestor getGuiaDespachoFacturaGestor() {
		return guiaDespachoFacturaGestor;
	}

	public void setGuiaDespachoFacturaGestor(IGuiaDespachoFacturaGestor guiaDespachoFacturaGestor) {
		this.guiaDespachoFacturaGestor = guiaDespachoFacturaGestor;
	}

	/**
	 * M\u00e9todo para obtener lista de despachos
	 * @param codigoCompania
	 * @param numeroGuia
	 * @param fechaDespachoInicio
	 * @param fechaDespachoFin
	 * @param placa
	 * @param documentoChofer
	 * @param nombreChofer
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Collection<GuiaDespachoDTO> findObtenerListaDespachosByFiltrosBusqueda(Integer codigoCompania, String numeroGuia, Timestamp fechaDespachoInicio, Timestamp fechaDespachoFin, String placa, String documentoChofer, String nombreChofer) throws ERPException{
		return this.guiaDespachoGestor.obtenerListaDespachosByFiltrosBusqueda(codigoCompania, numeroGuia, fechaDespachoInicio, fechaDespachoFin, placa, documentoChofer, nombreChofer);
	}
	
	/**
	 * M\u00e9todo para obtener lista de extras en guia de despacho
	 * @param codigoCompania
	 * @param numeroGuia
	 * @return Collection<GuiaDespachoExtrasDTO>
	 * @throws ERPException
	 */
	@Override
	public Collection<GuiaDespachoExtrasDTO> findObtenerListaGuiaDespachoExtrasByNumeroGuia(Integer codigoCompania, String numeroGuia) throws ERPException{
		return this.guiaDespachoExtrasGestor.obtenerListaGuiaDespachoExtrasByNumeroGuia(codigoCompania, numeroGuia);
	}
	
	/**
	 * M\u00e9todo para obtener lista de detalle de guia de despacho
	 * @param codigoCompania
	 * @param numeroGuia
	 * @return Collection<GuiaDespachoExtrasDTO>
	 * @throws ERPException
	 */
	@Override
	public Collection<GuiaDespachoDetalleDTO> findObtenerListaGuiaDespachoDetalleByNumeroGuia(Integer codigoCompania, String numeroGuia) throws ERPException{
		return this.guiaDespachoDetalleGestor.obtenerListaGuiaDespachoDetalleByNumeroGuia(codigoCompania, numeroGuia);
	}
	
	/**
	 * M\u00e9todo para obtener lista de pedidos por guia de despacho
	 * @param codigoCompania
	 * @param numeroGuia
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Collection<GuiaDespachoPedidoDTO> findObtenerListaGuiaDespachoPedidosByNumeroGuiaDespacho(Integer codigoCompania, String numeroGuia) throws ERPException{
		return this.guiaDespachoPedidoGestor.obtenerListaGuiaDespachoPedidosByNumeroGuiaDespacho(codigoCompania, numeroGuia);
	}
	
	/**
	 * M\u00e9todo para obtener lista de facturas por guia de despacho
	 * @param codigoCompania
	 * @param numeroGuia
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Collection<GuiaDespachoFacturaDTO> findObtenerListaGuiaDespachoFacturasByNumeroGuiaDespacho(Integer codigoCompania, String numeroGuia) throws ERPException{
		return this.guiaDespachoFacturaGestor.obtenerListaGuiaDespachoFacturasByNumeroGuiaDespacho(codigoCompania, numeroGuia);
	}
	
	
	
	/**
	 * M\u00e9todo para guardar y actualizar guia despacho
	 * @param guiaDespachoDTO
	 * @throws ERPException
	 */
	@Override
	public void transCrearActualizarGuiaDespacho(GuiaDespachoDTO guiaDespachoDTO) throws ERPException{
		this.guiaDespachoGestor.crearActualizarGuiaDespacho(guiaDespachoDTO);
	}
	
	/**
	 * Devuelve html para la impresion de la guia de despacho
	 * @param guiaDespachoDTO
	 * @return
	 * @throws ERPException
	 */
	@Override
	public String finObtenerXMLImprimirGuiaDespacho(GuiaDespachoDTO guiaDespachoDTO) throws ERPException{
		return this.guiaDespachoGestor.procesarXMLImprimirGuiaDespacho(guiaDespachoDTO);
	}
	
	/**
	 * Method for update status order and delete order for dispatch
	 * @param guiaDespachoPedidoDTO
	 */
	@Override
	public void transEliminarPedidoDespacho(String numeroGuia, GuiaDespachoPedidoDTO guiaDespachoPedidoDTO) {
		this.guiaDespachoGestor.eliminarPedidoDespacho(numeroGuia, guiaDespachoPedidoDTO);
	}
	
	/**
	 * Method for update status invoice and delete invoice for dispatch
	 * @param guiaDespachoFacturaDTO
	 */
	@Override
	public void transEliminarFacturaDespacho(String numeroGuia, GuiaDespachoFacturaDTO guiaDespachoFacturaDTO) {
		this.guiaDespachoGestor.eliminarFacturaDespacho(numeroGuia, guiaDespachoFacturaDTO);
	}
	
	/**
	 * Metodo para eliminar articulos extras del despacho
	 * @param guiaDespachoExtrasDTO
	 */	
	@Override
	public void transEliminarPedidosExtras(GuiaDespachoExtrasDTO guiaDespachoExtrasDTO) {
		this.guiaDespachoGestor.eliminarPedidosExtras(guiaDespachoExtrasDTO);
	}
}
