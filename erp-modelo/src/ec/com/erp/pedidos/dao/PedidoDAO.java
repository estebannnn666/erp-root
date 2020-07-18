/**
 * 
 */
package ec.com.erp.pedidos.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.PedidoDTO;
import ec.com.erp.cliente.mdl.dto.id.PedidoID;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2017-06-27
 */
public class PedidoDAO implements IPedidoDAO {

	/**
	 * SessionFactory sessionFactory.
	 */
	private SessionFactory sessionFactory;
	
	/**
	 * Dao para obtnener la secuencia
	 */
	private ISecuenciaDAO secuenciaDAO;

	/**
	 *  M\u00E9todo que asigna el valor de sessionFactory del objeto.
	 * @param sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	
	/**
	 * @return the secuenciaDAO
	 */
	public ISecuenciaDAO getSecuenciaDAO() {
		return secuenciaDAO;
	}

	/**
	 * @param secuenciaDAO the secuenciaDAO to set
	 */
	public void setSecuenciaDAO(ISecuenciaDAO secuenciaDAO) {
		this.secuenciaDAO = secuenciaDAO;
	}	

	/**
	 * M\u00e9todo para obtener lista de pedidos por filtros
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @param nombreCliente
	 * @param fechaInicio
	 * @param fechaFin
	 * @param estadoPedido
	 * @return
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<PedidoDTO> obtenerPedidosRegistrados(Integer codigoCompania, String numeroDocumento, String nombreCliente, Timestamp fechaInicio, Timestamp fechaFin, String estadoPedido) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(PedidoDTO.class, "root");
			criteria.createAlias("root.detallePedidoDTOCols", "detallePedidoDTOCols", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("detallePedidoDTOCols.articuloDTO", "articuloDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("root.clienteDTO", "clienteDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("clienteDTO.tipoClienteCatalogoValorDTO", "tipoClienteCatalogoValorDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("clienteDTO.personaDTO", "personaDTO", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("personaDTO.contactoDTOCols", "contactoPersonaDTO", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("clienteDTO.empresaDTO", "empresaDTO", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("empresaDTO.contactoDTOCols", "contactoEmpresaDTO", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("root.estadoPedidoDTOCols", "estadoPedidoDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("estadoPedidoDTO.estadoCatalogoValorDTO", "estadoCatalogoValorDTO", CriteriaSpecification.INNER_JOIN);

			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO)); 
			criteria.add(Restrictions.isNull("estadoPedidoDTO.fechaFin"));
			criteria.add(Restrictions.eq("estadoPedidoDTO.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			criteria.add(Restrictions.eq("detallePedidoDTOCols.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			
			if(fechaInicio != null && fechaFin != null){
				criteria.add(Restrictions.between("root.fechaPedido", fechaInicio, fechaFin));
			}
			if(numeroDocumento != null && numeroDocumento !=""){
				criteria.add(Restrictions.or(Restrictions.eq("personaDTO.numeroDocumento", numeroDocumento), Restrictions.eq("empresaDTO.numeroRuc", numeroDocumento)));
			}
			if(nombreCliente != null && nombreCliente !=""){
				nombreCliente = nombreCliente.toUpperCase();
				criteria.add(Restrictions.or(Restrictions.like("personaDTO.nombreCompleto", nombreCliente, MatchMode.ANYWHERE), Restrictions.like("empresaDTO.razonSocial", nombreCliente, MatchMode.ANYWHERE)));
			}
			if(estadoPedido != null && estadoPedido.trim() != "") {
				criteria.add(Restrictions.eq("estadoPedidoDTO.codigoValorEstadoPedido", estadoPedido));
			}

			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoPedido"), "id_codigoPedido");
			projectionList.add(Projections.property("root.numeroPedido"), "numeroPedido");
			projectionList.add(Projections.property("root.codigoCliente"), "codigoCliente");
			projectionList.add(Projections.property("root.fechaPedido"), "fechaPedido");
			projectionList.add(Projections.property("root.fechaEntrega"), "fechaEntrega");
			projectionList.add(Projections.property("root.subTotal"), "subTotal");
			projectionList.add(Projections.property("root.totalIva"), "totalIva");
			projectionList.add(Projections.property("root.totalCompra"), "totalCompra");
			projectionList.add(Projections.property("root.descuento"), "descuento");
			projectionList.add(Projections.property("root.totalSinImpuestos"), "totalSinImpuestos");
			projectionList.add(Projections.property("root.totalImpuestos"), "totalImpuestos");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			// Proyecciones entidad clientes 
			projectionList.add(Projections.property("clienteDTO.id.codigoCompania"), "clienteDTO_id_codigoCompania");
			projectionList.add(Projections.property("clienteDTO.id.codigoCliente"), "clienteDTO_id_codigoCliente");
			projectionList.add(Projections.property("clienteDTO.codigoPersona"), "clienteDTO_codigoPersona");
			projectionList.add(Projections.property("clienteDTO.codigoEmpresa"), "clienteDTO_codigoEmpresa");
			projectionList.add(Projections.property("clienteDTO.userId"), "clienteDTO_userId");
			projectionList.add(Projections.property("clienteDTO.codigoValorTipoCliente"), "clienteDTO_codigoValorTipoCliente");
			projectionList.add(Projections.property("clienteDTO.codigoTipoCliente"), "clienteDTO_codigoTipoCliente");
			projectionList.add(Projections.property("clienteDTO.estado"), "clienteDTO_estado");
			projectionList.add(Projections.property("clienteDTO.usuarioRegistro"), "clienteDTO_usuarioRegistro");
			projectionList.add(Projections.property("clienteDTO.fechaRegistro"), "clienteDTO_fechaRegistro");
			
			// Proyecciones catalogos
			projectionList.add(Projections.property("tipoClienteCatalogoValorDTO.nombreCatalogoValor"), "clienteDTO_tipoClienteCatalogoValorDTO_nombreCatalogoValor");
			
			// Proyecciones entidad persona 
			projectionList.add(Projections.property("personaDTO.id.codigoCompania"), "clienteDTO_personaDTO_id_codigoCompania");
			projectionList.add(Projections.property("personaDTO.id.codigoPersona"), "clienteDTO_personaDTO_id_codigoPersona");
			projectionList.add(Projections.property("personaDTO.numeroDocumento"), "clienteDTO_personaDTO_numeroDocumento");
			projectionList.add(Projections.property("personaDTO.nombreCompleto"), "clienteDTO_personaDTO_nombreCompleto");
			
			// Proyecciones entidad contacto persona 
			projectionList.add(Projections.property("contactoPersonaDTO.direccionPrincipal"), "clienteDTO_personaDTO_contactoPersonaDTO_direccionPrincipal");
			projectionList.add(Projections.property("contactoPersonaDTO.ciudad"), "clienteDTO_personaDTO_contactoPersonaDTO_ciudad");
			projectionList.add(Projections.property("contactoPersonaDTO.telefonoPrincipal"), "clienteDTO_personaDTO_contactoPersonaDTO_telefonoPrincipal");
			
			// Proyecciones entidad empresa   
			projectionList.add(Projections.property("empresaDTO.id.codigoCompania"), "clienteDTO_empresaDTO_id_codigoCompania");
			projectionList.add(Projections.property("empresaDTO.id.codigoEmpresa"), "clienteDTO_empresaDTO_id_codigoEmpresa");
			projectionList.add(Projections.property("empresaDTO.numeroRuc"), "clienteDTO_empresaDTO_numeroRuc");
			projectionList.add(Projections.property("empresaDTO.razonSocial"), "clienteDTO_empresaDTO_razonSocial");
			
			// Proyecciones entidad contacto empresa
			projectionList.add(Projections.property("contactoEmpresaDTO.direccionPrincipal"), "clienteDTO_empresaDTO_contactoEmpresaDTO_direccionPrincipal");
			projectionList.add(Projections.property("contactoEmpresaDTO.ciudad"), "clienteDTO_empresaDTO_contactoEmpresaDTO_ciudad");
			projectionList.add(Projections.property("contactoEmpresaDTO.telefonoPrincipal"), "clienteDTO_empresaDTO_contactoEmpresaDTO_telefonoPrincipal");
			
			// Proyecciones entidad estado pedido
			projectionList.add(Projections.property("estadoPedidoDTO.id.codigoCompania"), "estadoPedidoDTO_id_codigoCompania");
			projectionList.add(Projections.property("estadoPedidoDTO.id.codigoEstadoPedido"), "estadoPedidoDTO_id_codigoEstadoPedido");
			projectionList.add(Projections.property("estadoPedidoDTO.id.codigoPedido"), "estadoPedidoDTO_id_codigoPedido");
			projectionList.add(Projections.property("estadoPedidoDTO.codigoValorEstadoPedido"), "estadoPedidoDTO_codigoValorEstadoPedido");
			projectionList.add(Projections.property("estadoPedidoDTO.codigoTipoEstadoPedido"), "estadoPedidoDTO_codigoTipoEstadoPedido");
			projectionList.add(Projections.property("estadoPedidoDTO.fechaInicio"), "estadoPedidoDTO_fechaInicio");
			projectionList.add(Projections.property("estadoPedidoDTO.fechaFin"), "estadoPedidoDTO_fechaFin");
			projectionList.add(Projections.property("estadoPedidoDTO.estado"), "estadoPedidoDTO_estado");
			projectionList.add(Projections.property("estadoPedidoDTO.usuarioRegistro"), "estadoPedidoDTO_usuarioRegistro");
			projectionList.add(Projections.property("estadoPedidoDTO.fechaRegistro"), "estadoPedidoDTO_fechaRegistro");
			
			// Proyecciones entidad catalogo valor estado pedido
			projectionList.add(Projections.property("estadoCatalogoValorDTO.id.codigoCatalogoValor"), "estadoPedidoDTO_estadoCatalogoValorDTO_id_codigoCatalogoValor");
			projectionList.add(Projections.property("estadoCatalogoValorDTO.id.codigoCatalogoTipo"), "estadoPedidoDTO_estadoCatalogoValorDTO_id_codigoCatalogoTipo");
			projectionList.add(Projections.property("estadoCatalogoValorDTO.nombreCatalogoValor"), "estadoPedidoDTO_estadoCatalogoValorDTO_nombreCatalogoValor");
			
			// Proyecciones entidad detalle pedido
			projectionList.add(Projections.property("detallePedidoDTOCols.id.codigoCompania"), "detallePedidoDTOCols_id_codigoCompania");
			projectionList.add(Projections.property("detallePedidoDTOCols.id.codigoDetallePedido"), "detallePedidoDTOCols_id_codigoDetallePedido");
			projectionList.add(Projections.property("detallePedidoDTOCols.id.codigoPedido"), "detallePedidoDTOCols_id_codigoPedido");
			projectionList.add(Projections.property("detallePedidoDTOCols.codigoArticulo"), "detallePedidoDTOCols_codigoArticulo");
			projectionList.add(Projections.property("detallePedidoDTOCols.cantidad"), "detallePedidoDTOCols_cantidad");
			projectionList.add(Projections.property("detallePedidoDTOCols.subTotal"), "detallePedidoDTOCols_subTotal");
			projectionList.add(Projections.property("detallePedidoDTOCols.estado"), "detallePedidoDTOCols_estado");
			projectionList.add(Projections.property("detallePedidoDTOCols.usuarioRegistro"), "detallePedidoDTOCols_usuarioRegistro");
			projectionList.add(Projections.property("detallePedidoDTOCols.fechaRegistro"), "detallePedidoDTOCols_fechaRegistro");
			
			// Proyecciones entidad articulo
			projectionList.add(Projections.property("articuloDTO.id.codigoCompania"), "detallePedidoDTOCols_articuloDTO_id_codigoCompania");
			projectionList.add(Projections.property("articuloDTO.id.codigoArticulo"), "detallePedidoDTOCols_articuloDTO_id_codigoArticulo");
			projectionList.add(Projections.property("articuloDTO.codigoBarras"), "detallePedidoDTOCols_articuloDTO_codigoBarras");
			projectionList.add(Projections.property("articuloDTO.nombreArticulo"), "detallePedidoDTOCols_articuloDTO_nombreArticulo");
			projectionList.add(Projections.property("articuloDTO.peso"), "detallePedidoDTOCols_articuloDTO_peso");
			projectionList.add(Projections.property("articuloDTO.precio"), "detallePedidoDTOCols_articuloDTO_precio");
			projectionList.add(Projections.property("articuloDTO.cantidadStock"), "detallePedidoDTOCols_articuloDTO_cantidadStock");
			projectionList.add(Projections.property("articuloDTO.estado"), "detallePedidoDTOCols_articuloDTO_estado");
			
			criteria.setProjection(projectionList);
			criteria.addOrder(Order.desc("root.id.codigoPedido"));
			criteria.setResultTransformer(new MultiLevelResultTransformer(PedidoDTO.class));
			Collection<PedidoDTO> pedidoDTOCols = new ArrayList<PedidoDTO>();
			pedidoDTOCols =  criteria.list();

			return pedidoDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de pedidos.").initCause(e);
		} 
	}

	
	/**
	 * M\u00e9todo para obtener pedido por codigo
	 * @param codigoCompania
	 * @param codigoPedido
	 * @return
	 * @throws ERPException
	 */
	@Override
	public PedidoDTO obtenerPedidoPorCodigo(Integer codigoCompania, Long codigoPedido) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(PedidoDTO.class, "root");

			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.id.codigoPedido", codigoPedido));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO)); 
			
			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoPedido"), "id_codigoPedido");
			projectionList.add(Projections.property("root.numeroPedido"), "numeroPedido");
			projectionList.add(Projections.property("root.codigoCliente"), "codigoCliente");
			projectionList.add(Projections.property("root.fechaPedido"), "fechaPedido");
			projectionList.add(Projections.property("root.fechaEntrega"), "fechaEntrega");
			projectionList.add(Projections.property("root.subTotal"), "subTotal");
			projectionList.add(Projections.property("root.totalIva"), "totalIva");
			projectionList.add(Projections.property("root.totalCompra"), "totalCompra");
			projectionList.add(Projections.property("root.totalSinImpuestos"), "totalSinImpuestos");
			projectionList.add(Projections.property("root.descuento"), "descuento");
			projectionList.add(Projections.property("root.totalImpuestos"), "totalImpuestos");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(PedidoDTO.class));

			return (PedidoDTO)criteria.uniqueResult();

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de pedidos.").initCause(e);
		} 
	}
	/**
	 * M\u00e9todo para crear o actualizar personas
	 * @param personaDTO
	 * @throws ERPException
	 */
	public void crearActualizarPedido(PedidoDTO pedidoDTO) throws ERPException{
		try{
			if (pedidoDTO.getId().getCodigoCompania() == null || pedidoDTO.getUsuarioRegistro() == null) {
				throw new ERPException("El c\u00F3digo de compania y el id de usuario registro es requerido");
			}	
			
			sessionFactory.getCurrentSession().clear();
			if(pedidoDTO.getId().getCodigoPedido() ==  null){
				Integer secuencialPedido  = this.secuenciaDAO.obtenerSecuencialTabla(PedidoID.NOMBRE_SECUENCIA);
				pedidoDTO.getId().setCodigoPedido(Long.parseLong(""+secuencialPedido));
				pedidoDTO.setFechaRegistro(new Date());
				pedidoDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				sessionFactory.getCurrentSession().save(pedidoDTO);
			}
			else
			{
				pedidoDTO.setFechaModificacion(new Date());
				pedidoDTO.setUsuarioModificacion(pedidoDTO.getUsuarioRegistro());
				sessionFactory.getCurrentSession().update(pedidoDTO);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Ocurrio un error al guardar o actualizar el pedido."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Ocurrio un error al guardar o actualizar el pedido."+e.getMessage());
		} 
	}
}
