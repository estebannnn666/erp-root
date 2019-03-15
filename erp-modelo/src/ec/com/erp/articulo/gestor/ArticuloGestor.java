package ec.com.erp.articulo.gestor;

import java.math.BigDecimal;
import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;

import ec.com.erp.articulo.dao.IArticuloDAO;
import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ArticuloDTO;
import ec.com.erp.cliente.mdl.dto.ArticuloImpuestoDTO;
import ec.com.erp.cliente.mdl.dto.InventarioDTO;
import ec.com.erp.inventario.gestor.IInventarioGestor;

public class ArticuloGestor implements IArticuloGestor{

	private IArticuloDAO articuloDAO;
	private IInventarioGestor inventarioGestor;
	
	public IArticuloDAO getArticuloDAO() {
		return articuloDAO;
	}

	public void setArticuloDAO(IArticuloDAO articuloDAO) {
		this.articuloDAO = articuloDAO;
	}
	
	public IInventarioGestor getInventarioGestor() {
		return inventarioGestor;
	}

	public void setInventarioGestor(IInventarioGestor inventarioGestor) {
		this.inventarioGestor = inventarioGestor;
	}
	
	/**
	 * M\u00e9todo para obtener lista de articulos
	 * @return 
	 * @throws ERPException
	 */
	public Collection<ArticuloDTO> obtenerListaArticulos(Integer codigoCompania, String codigoBarras, String nombreArticulo) throws ERPException{
		return this.articuloDAO.obtenerListaArticulos(codigoCompania, codigoBarras, nombreArticulo);
	}
	
	/**
	 * M\u00e9todo para obtener articulo por id
	 * @return 
	 * @throws ERPException
	 */
	public ArticuloDTO obtenerListaArticuloById(Integer codigoCompania, Integer codigoArticulo) throws ERPException{
		return this.articuloDAO.obtenerListaArticuloById(codigoCompania, codigoArticulo);
	}
	
	
	/**
	 * Metodo para guardar y actualizar articulos
	 * @param articuloDTO
	 * @param articuloImpuestoDTONewCols
	 * @throws ERPException
	 */
	public void guardarActualizarArticulo(ArticuloDTO articuloDTO, Collection<ArticuloImpuestoDTO> articuloImpuestoDTONewCols) throws ERPException{
		Boolean afectarInvetario = Boolean.FALSE;
		if(articuloDTO.getId().getCodigoArticulo() ==  null){
			afectarInvetario = Boolean.TRUE;
		}
		// Actualizar o crear articulo
		this.articuloDAO.guardarActualizarArticulo(articuloDTO);
		// Guardar o eliminar impuestos del articulo
		if(CollectionUtils.isNotEmpty(articuloImpuestoDTONewCols)){
			this.guardarActualizarArticuloImpuesto(articuloDTO.getId().getCodigoCompania(), articuloDTO.getId().getCodigoArticulo(), articuloImpuestoDTONewCols);
		}
		//Afectar inventario al crear un articulo
		if(afectarInvetario) {
			InventarioDTO inventarioDTO = new InventarioDTO();
			inventarioDTO.getId().setCodigoCompania(articuloDTO.getId().getCodigoCompania());
			inventarioDTO.setCantidadEntrada(articuloDTO.getCantidadStock());
			inventarioDTO.setValorUnidadEntrada(articuloDTO.getPrecio());
			inventarioDTO.setValorTotalEntrada(BigDecimal.valueOf(Double.valueOf(""+articuloDTO.getCantidadStock())).multiply(articuloDTO.getPrecio()));
			
			inventarioDTO.setCantidadExistencia(inventarioDTO.getCantidadEntrada());
			inventarioDTO.setValorUnidadExistencia(inventarioDTO.getValorUnidadEntrada());
			inventarioDTO.setValorTotalExistencia(inventarioDTO.getValorTotalEntrada());
			inventarioDTO.setArticuloDTO(articuloDTO);
			inventarioDTO.setCodigoArticulo(articuloDTO.getId().getCodigoArticulo());
			inventarioDTO.setDetalleMoviento("INVENTARIO INICIAL");
			inventarioDTO.setUsuarioRegistro(articuloDTO.getUsuarioRegistro());
			this.inventarioGestor.crearActualizarInventario(inventarioDTO);
		}
	}
	
	/**
	 * Metodo para guardar y actualizar articulo impuesto
	 * @param articuloImpuestoDTO
	 * @throws ERPException
	 */
	public void guardarActualizarArticuloImpuesto(Integer codigoCompania, Integer codigoArticulo, Collection<ArticuloImpuestoDTO> articuloImpuestoDTONewCols) throws ERPException{
		
		// Obtener impuestos agregados para verificar 
		Collection<ArticuloImpuestoDTO> articuloImpuestoDTOAntCols = this.articuloDAO.obtenerListaArticuloImpuesto(codigoCompania, codigoArticulo);
		/// Eliminar elementos 
		Boolean eliminar = Boolean.TRUE;
		for(ArticuloImpuestoDTO articuloImpuestoAntDTO: articuloImpuestoDTOAntCols){
			eliminar = Boolean.TRUE;
			for(ArticuloImpuestoDTO articuloImpuestoNewDTO : articuloImpuestoDTONewCols){
				if(articuloImpuestoAntDTO.getId().getCodigoImpuesto().equals(articuloImpuestoNewDTO.getId().getCodigoImpuesto())){
					eliminar = Boolean.FALSE;
					break;
				}
			}
			if(eliminar){
				articuloImpuestoAntDTO.setEstado(ERPConstantes.ESTADO_INACTIVO_NUMERICO);
				this.articuloDAO.guardarActualizarArticuloImpuesto(articuloImpuestoAntDTO);
			}
		}
		
		// Guardar o actualizar elementos
		Boolean esNuevo = Boolean.TRUE;
		for(ArticuloImpuestoDTO articuloImpuestoNewDTO:articuloImpuestoDTONewCols){
			esNuevo = Boolean.TRUE;
			for(ArticuloImpuestoDTO articuloImpuestoAntDTO : articuloImpuestoDTOAntCols){
				if(articuloImpuestoAntDTO.getId().getCodigoImpuesto().equals(articuloImpuestoNewDTO.getId().getCodigoImpuesto())){
					esNuevo = Boolean.FALSE;
					break;
				}
			}
			if(esNuevo){
				articuloImpuestoNewDTO.getId().setCodigoArticulo(codigoArticulo);
				this.articuloDAO.guardarActualizarArticuloImpuesto(articuloImpuestoNewDTO);
			}
		}
	}
}
