package ec.com.erp.unidadmanejo.servicios;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ArticuloUnidadManejoDTO;
import ec.com.erp.unidadmanejo.gestor.IUnidadManejoGestor;

public class UnidadManejoServicio implements IUnidadManejoServicio{
	
	private IUnidadManejoGestor unidadManejoGestor;

	public IUnidadManejoGestor getUnidadManejoGestor() {
		return unidadManejoGestor;
	}

	public void setUnidadManejoGestor(IUnidadManejoGestor unidadManejoGestor) {
		this.unidadManejoGestor = unidadManejoGestor;
	}

	/**
	 * M\u00e9todo para obtener lista de ArticuloUnidadManejoDTO.
	 * @return 
	 * @throws ERPException
	 */
	public Collection<ArticuloUnidadManejoDTO> findObtenerListaArticulosUnidadManejo(Integer codigoCompania, Integer codigoArticulo) throws ERPException{
		return this.unidadManejoGestor.obtenerListaArticulosUnidadManejo(codigoCompania, codigoArticulo);
	}
	
	/**
	 * M\u00e9todo para guardar y actualizar articulo unidad manejo
	 * @param articuloDTO
	 * @throws ERPException
	 */
	public void transGuardarActualizarUnidadManejo(ArticuloUnidadManejoDTO articuloUnidadManejoDTO) throws ERPException{
		this.unidadManejoGestor.guardarActualizarUnidadManejo(articuloUnidadManejoDTO);
	}
}
