package ec.com.erp.unidadmanejo.servicios;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ArticuloUnidadManejoDTO;

public interface IUnidadManejoServicio {
	
	/**
	 * M\u00e9todo para obtener lista de ArticuloUnidadManejoDTO.
	 * @return 
	 * @throws ERPException
	 */
	Collection<ArticuloUnidadManejoDTO> findObtenerListaArticulosUnidadManejo(Integer codigoCompania, Integer codigoArticulo) throws ERPException;
	
	/**
	 * Metodo para guardar y actualizar articulo unidad manejo
	 * @param articuloDTO
	 * @throws ERPException
	 */
	void transGuardarActualizarUnidadManejo(ArticuloUnidadManejoDTO articuloUnidadManejoDTO) throws ERPException;
}
