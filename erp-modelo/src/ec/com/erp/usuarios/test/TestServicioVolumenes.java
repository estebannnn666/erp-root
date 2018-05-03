package ec.com.erp.usuarios.test;

import java.util.Collection;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;

import ec.com.erp.cliente.common.factory.ERPFactory;
import ec.com.erp.cliente.mdl.dto.ArticuloDTO;
import ec.com.erp.cliente.mdl.dto.CatalogoValorDTO;
import ec.com.erp.cliente.mdl.dto.ClienteDTO;
import ec.com.erp.cliente.mdl.dto.UsuariosDTO;


/**
 * 
 * @author egudino
 *
 */
public class TestServicioVolumenes {

	@BeforeClass
	public static void init() {
		DOMConfigurator.configure("logging.xml");
		//		System.out.println("Factory:"+SICFactory.getInstancia().administracion.getDataService());
	}

	//@Test
	public void testUsuario(){
		String nombreUsuario = "egudino";
		String password = "es13ban";
		UsuariosDTO usuarioDTO = ERPFactory.usuarios.getUsuariosServicio().findLoginUser(nombreUsuario, password);
		System.out.println("Tamanio: "+usuarioDTO.getLogeado());
	}
	
	//@Test
	public void testArticulos(){
		Collection<ArticuloDTO> articuloDTOCols = ERPFactory.articulos.getArticuloServicio().findObtenerListaArticulos(1, null, null);
		System.out.println("Tamanio: "+articuloDTOCols.size());
	}
	
	//@Test
	public void testCatalogos(){
		Integer codigoCatalogoTipo = 1;
		Collection<CatalogoValorDTO> catalogoValorDTOCols = ERPFactory.catalogos.getCatalogoServicio().findObtenerCatalogoByTipo(codigoCatalogoTipo);
		System.out.println("Tamanio: "+catalogoValorDTOCols.size());
	}
	
	@Test
	public void testClientes(){
		Collection<ClienteDTO> clienteDTOCols = ERPFactory.clientes.getClientesServicio().findObtenerListaClientes(1);
		System.out.println("Tamanio: "+clienteDTOCols.size());
	}
}
