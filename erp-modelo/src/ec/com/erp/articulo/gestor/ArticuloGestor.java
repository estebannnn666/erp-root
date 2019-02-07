package ec.com.erp.articulo.gestor;

import java.math.BigDecimal;
import java.util.Collection;

import ec.com.erp.articulo.dao.IArticuloDAO;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ArticuloDTO;
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
	 * @throws ERPException
	 */
	public void transGuardarActualizarArticulo(ArticuloDTO articuloDTO) throws ERPException{
		Boolean afectarInvetario = Boolean.FALSE;
		if(articuloDTO.getId().getCodigoArticulo() ==  null){
			afectarInvetario = Boolean.TRUE;
		}
		this.articuloDAO.transGuardarActualizarArticulo(articuloDTO);
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
}
