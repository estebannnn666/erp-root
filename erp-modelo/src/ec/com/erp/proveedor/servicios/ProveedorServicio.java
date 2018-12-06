package ec.com.erp.proveedor.servicios;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ContactoDTO;
import ec.com.erp.cliente.mdl.dto.ProveedorDTO;
import ec.com.erp.proveedor.gestor.IProveedorGestor;

public class ProveedorServicio implements IProveedorServicio{
	
	private IProveedorGestor proveedorGestor;

	public IProveedorGestor getProveedorGestor() {
		return proveedorGestor;
	}

	public void setProveedorGestor(IProveedorGestor proveedorGestor) {
		this.proveedorGestor = proveedorGestor;
	}

	/**
	 * Obtener proveedor por documento
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @param codigoValorTipoProveedor
	 * @return
	 * @throws ERPException
	 */
	@Override
	public ProveedorDTO findObtenerProveedor(Integer codigoCompania, String numeroDocumento, String codigoValorTipoProveedor) throws ERPException{
		return this.proveedorGestor.obtenerProveedor(codigoCompania, numeroDocumento, codigoValorTipoProveedor);
	}
	
	/**
	 * M\u00e9todo para obtener lista de proveedores
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @param razonSocial
	 * @return 
	 * @throws ERPException
	 */
	@Override
	public Collection<ProveedorDTO> findObtenerListaProveedores(Integer codigoCompania, String numeroDocumento, String razonSocial) throws ERPException{
		return this.proveedorGestor.obtenerListaProveedores(codigoCompania, numeroDocumento, razonSocial);
	}
	
	/**
	 * M\u009etodo para guardar y actualizar proveedor
	 * @param proveedorDTO
	 * @param contactoDTO
	 * @throws ERPException
	 */
	@Override
	public void transGuardarActualizarProveedor(ProveedorDTO proveedorDTO, ContactoDTO contactoDTO) throws ERPException{
		this.proveedorGestor.guardarActualizarProveedor(proveedorDTO, contactoDTO);
	}
}
