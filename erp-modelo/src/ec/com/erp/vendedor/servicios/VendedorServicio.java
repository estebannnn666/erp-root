package ec.com.erp.vendedor.servicios;

import java.util.Collection;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ContactoDTO;
import ec.com.erp.cliente.mdl.dto.VendedorDTO;
import ec.com.erp.vendedor.gestor.IVendedorGestor;

public class VendedorServicio implements IVendedorServicio{
	
	private IVendedorGestor vendedorGestor;

	public IVendedorGestor getVendedorGestor() {
		return vendedorGestor;
	}

	public void setVendedorGestor(IVendedorGestor vendedorGestor) {
		this.vendedorGestor = vendedorGestor;
	}

	/**
	 * Obtener vendedor por documento
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @return
	 * @throws ERPException
	 */
	@Override
	public VendedorDTO findObtenerVendedor(Integer codigoCompania, String numeroDocumento) throws ERPException{
		return this.vendedorGestor.obtenerVendedor(codigoCompania, numeroDocumento);
	}
	
	/**
	 * M\u00e9todo para obtener lista de vendedores
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @param nombreVendedor
	 * @return 
	 * @throws ERPException
	 */
	@Override
	public Collection<VendedorDTO> findObtenerListaVendedores(Integer codigoCompania, String numeroDocumento, String nombreVendedor) throws ERPException{
		return this.vendedorGestor.obtenerListaVendedores(codigoCompania, numeroDocumento, nombreVendedor);
	}
	
	/**
	 * M\u009etodo para guardar y actualizar proveedor
	 * @param proveedorDTO
	 * @param contactoDTO
	 * @throws ERPException
	 */
	@Override
	public void transGuardarActualizarVendedor(VendedorDTO vendedorDTO, ContactoDTO contactoDTO) throws ERPException{
		this.vendedorGestor.guardarActualizarVendedor(vendedorDTO, contactoDTO);
	}
}
