package ec.com.erp.articulo.gestor;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;

import ec.com.erp.articulo.dao.IArticuloDAO;
import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ArticuloDTO;
import ec.com.erp.cliente.mdl.dto.ArticuloImpuestoDTO;
import ec.com.erp.cliente.mdl.dto.ArticuloUnidadManejoDTO;
import ec.com.erp.inventario.gestor.IInventarioGestor;
import ec.com.erp.unidadmanejo.dao.IUnidadManejoDAO;

public class ArticuloGestor implements IArticuloGestor{

	private IArticuloDAO articuloDAO;
	private IUnidadManejoDAO unidadManejoDAO;
	private IInventarioGestor inventarioGestor;
	
	public IArticuloDAO getArticuloDAO() {
		return articuloDAO;
	}

	public void setArticuloDAO(IArticuloDAO articuloDAO) {
		this.articuloDAO = articuloDAO;
	}
	
	public IUnidadManejoDAO getUnidadManejoDAO() {
		return unidadManejoDAO;
	}

	public void setUnidadManejoDAO(IUnidadManejoDAO unidadManejoDAO) {
		this.unidadManejoDAO = unidadManejoDAO;
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
	public void guardarActualizarArticulo(ArticuloDTO articuloDTO, Collection<ArticuloImpuestoDTO> articuloImpuestoDTONewCols, Collection<ArticuloUnidadManejoDTO> articuloUnidadManejoDTONewCols) throws ERPException{
		// Actualizar o crear articulo
		this.articuloDAO.guardarActualizarArticulo(articuloDTO);
		// Guardar o eliminar impuestos del articulo
		if(CollectionUtils.isNotEmpty(articuloImpuestoDTONewCols)){
			this.guardarActualizarArticuloImpuesto(articuloDTO.getId().getCodigoCompania(), articuloDTO.getId().getCodigoArticulo(), articuloImpuestoDTONewCols);
		}
		
		// Guardar o eliminar articulo unidad de manejo
		if(CollectionUtils.isNotEmpty(articuloUnidadManejoDTONewCols)){
			this.guardarActualizarArticuloUnidadManejo(articuloDTO.getId().getCodigoCompania(), articuloDTO.getId().getCodigoArticulo(), articuloUnidadManejoDTONewCols);
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
				if(articuloImpuestoNewDTO.getId().getCodigoImpuesto() != null && articuloImpuestoAntDTO.getId().getCodigoImpuesto().equals(articuloImpuestoNewDTO.getId().getCodigoImpuesto())){
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
	
	/**
	 * Metodo para guardar y actualizar articulo impuesto
	 * @param articuloImpuestoDTO
	 * @throws ERPException
	 */
	public void guardarActualizarArticuloUnidadManejo(Integer codigoCompania, Integer codigoArticulo, Collection<ArticuloUnidadManejoDTO> articuloUnidadManejoDTONewCols) throws ERPException{
		
		// Obtener impuestos agregados para verificar 
		Collection<ArticuloUnidadManejoDTO> articuloUnidadManejoDTOAntCols = this.unidadManejoDAO.obtenerListaArticulosUnidadManejo(codigoCompania, codigoArticulo);
		/// Eliminar elementos 
		Boolean eliminar = Boolean.TRUE;
		for(ArticuloUnidadManejoDTO articuloUnidadManejoAntDTO: articuloUnidadManejoDTOAntCols){
			eliminar = Boolean.TRUE;
			for(ArticuloUnidadManejoDTO articuloImpuestoNewDTO : articuloUnidadManejoDTONewCols){
				if(articuloImpuestoNewDTO.getId().getCodigoArticuloUnidadManejo() != null && articuloUnidadManejoAntDTO.getId().getCodigoArticuloUnidadManejo().intValue() == articuloImpuestoNewDTO.getId().getCodigoArticuloUnidadManejo().intValue()){
					eliminar = Boolean.FALSE;
					break;
				}
			}
			if(eliminar){
				articuloUnidadManejoAntDTO.setEstado(ERPConstantes.ESTADO_INACTIVO_NUMERICO);
				this.unidadManejoDAO.guardarActualizarUnidadManejo(articuloUnidadManejoAntDTO);
			}
		}
		
		// Guardar o actualizar elementos
		Boolean esNuevo = Boolean.TRUE;
		for(ArticuloUnidadManejoDTO articuloUnidadManejoNewDTO:articuloUnidadManejoDTONewCols){
			esNuevo = Boolean.TRUE;
			for(ArticuloUnidadManejoDTO articuloUnidadManejoAntDTO : articuloUnidadManejoDTOAntCols){
				if(articuloUnidadManejoNewDTO.getId().getCodigoArticuloUnidadManejo() != null && articuloUnidadManejoAntDTO.getId().getCodigoArticuloUnidadManejo().intValue() == articuloUnidadManejoNewDTO.getId().getCodigoArticuloUnidadManejo().intValue()){
					esNuevo = Boolean.FALSE;
					break;
				}
			}
			if(esNuevo){
				articuloUnidadManejoNewDTO.getId().setCodigoArticulo(codigoArticulo);
				this.unidadManejoDAO.guardarActualizarUnidadManejo(articuloUnidadManejoNewDTO);
			}
		}
	}
}
