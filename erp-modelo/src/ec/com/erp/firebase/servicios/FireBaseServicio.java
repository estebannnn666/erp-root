package ec.com.erp.firebase.servicios;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.firebase.gestor.IFireBaseArticuloGestor;
import ec.com.erp.firebase.gestor.IFireBaseClienteGestor;
import ec.com.erp.firebase.gestor.IFireBaseFacturaGestor;
import ec.com.erp.firebase.gestor.IFireBasePedidoGestor;

public class FireBaseServicio implements IFireBaseServicio {

	private IFireBaseClienteGestor fireBaseClienteGestor;
	private IFireBaseArticuloGestor fireBaseArticuloGestor;
	private IFireBaseFacturaGestor fireBaseFacturaGestor;
	private IFireBasePedidoGestor fireBasePedidoGestor;
	
	public IFireBaseClienteGestor getFireBaseClienteGestor() {
		return fireBaseClienteGestor;
	}

	public void setFireBaseClienteGestor(IFireBaseClienteGestor fireBaseClienteGestor) {
		this.fireBaseClienteGestor = fireBaseClienteGestor;
	}
	

	public IFireBaseArticuloGestor getFireBaseArticuloGestor() {
		return fireBaseArticuloGestor;
	}

	public void setFireBaseArticuloGestor(IFireBaseArticuloGestor fireBaseArticuloGestor) {
		this.fireBaseArticuloGestor = fireBaseArticuloGestor;
	}
	
	public IFireBaseFacturaGestor getFireBaseFacturaGestor() {
		return fireBaseFacturaGestor;
	}

	public void setFireBaseFacturaGestor(IFireBaseFacturaGestor fireBaseFacturaGestor) {
		this.fireBaseFacturaGestor = fireBaseFacturaGestor;
	}
	
	public IFireBasePedidoGestor getFireBasePedidoGestor() {
		return fireBasePedidoGestor;
	}

	public void setFireBasePedidoGestor(IFireBasePedidoGestor fireBasePedidoGestor) {
		this.fireBasePedidoGestor = fireBasePedidoGestor;
	}

	/**
	 * M\u00e9todo para descargar los clientes de fire base
	 * @return 
	 * @throws ERPException
	 */
	@Override
	public void transDescargarClientesFireBase() throws ERPException{
		this.fireBaseClienteGestor.descargarClientesFireBase();
	}	
	
	
	/**
	 * M\u00e9todo para guardar los clientes locales a fire base
	 * @return 
	 * @throws ERPException
	 */
	@Override
	public void findGuardarClientesFireBase() throws ERPException{
		this.fireBaseClienteGestor.guardarClientesFireBase();
	}	
	
	/**
	 * M\u00e9todo para descargar los ARTICULOS de fire base
	 * @return 
	 * @throws ERPException
	 */
	@Override
	public void transDescargarArticulosFireBase() throws ERPException{
		this.fireBaseArticuloGestor.descargarArticulosFireBase();
	}
	
	/**
	 * M\u00e9todo para guardar los articulos locales a fire base
	 * @return 
	 * @throws ERPException
	 */
	@Override
	public void findGuardarArticulosFireBase() throws ERPException{
		this.fireBaseArticuloGestor.guardarArticulosFireBase();
	}
	
	/**
	 * M\u00e9todo para descargar las facturas de fire base
	 * @return 
	 * @throws ERPException
	 */
	@Override
	public void transDescargarFacturasFireBase() throws ERPException{
		this.fireBaseFacturaGestor.descargarFacturasFireBase();
	}
	
	/**
	 * M\u00e9todo para descargar las pedidos de fire base
	 * @return 
	 * @throws ERPException
	 */
	@Override
	public void transDescargarPedidosFireBase() throws ERPException{
		this.fireBasePedidoGestor.descargarPedidosFireBase();
	}
}
