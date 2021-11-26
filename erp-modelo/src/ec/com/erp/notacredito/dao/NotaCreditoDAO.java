/**
 * 
 */
package ec.com.erp.notacredito.dao;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
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
import ec.com.erp.cliente.mdl.dto.NotaCreditoDTO;
import ec.com.erp.cliente.mdl.dto.id.NotaCreditoID;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2018-05-05
 */
public class NotaCreditoDAO implements INotaCreditoDAO {

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
	 * M\u00e9todo para obtener lista de notas de credito por filtros de busqueda
	 * @param codigoCompania
	 * @param numeroNotaCredito
	 * @param fechaNotaCreditoInicio
	 * @param fechaNotaCreditoFin
	 * @param docCliente
	 * @param nombreCliente
	 * @return
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<NotaCreditoDTO> obtenerListaNotasCredito(Integer codigoCompania, String numeroNotaCredito, Timestamp fechaNotaCreditoInicio, Timestamp fechaNotaCreditoFin, String docCliente, String nombreCliente) throws ERPException {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(NotaCreditoDTO.class, "root");
			criteria.createAlias("root.notaCreditoDetalleDTOCols", "notaCreditoDetalleDTOCols", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("notaCreditoDetalleDTOCols.articuloDTO", "articuloDTO", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("articuloDTO.articuloImpuestoDTOCols", "articuloImpuestoDTOCols", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("notaCreditoDetalleDTOCols.articuloUnidadManejoDTO", "articuloUnidadManejoDTO", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("articuloUnidadManejoDTO.tipoUnidadManejoCatalogoValorDTO", "tipoUnidadManejoCatalogoValorDTO", CriteriaSpecification.LEFT_JOIN);
			
			
			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			
			if(numeroNotaCredito != null && numeroNotaCredito !=""){
				numeroNotaCredito = numeroNotaCredito.toUpperCase();
				criteria.add(Restrictions.eq("root.numeroDocumento", numeroNotaCredito));
			}
			if(fechaNotaCreditoInicio != null && fechaNotaCreditoFin != null){
				criteria.add(Restrictions.between("root.fechaDocumento", fechaNotaCreditoInicio, fechaNotaCreditoFin));
			}
			if(docCliente != null && docCliente !=""){
				criteria.add(Restrictions.like("root.rucCliente", docCliente, MatchMode.ANYWHERE));
			}
			if(nombreCliente != null && nombreCliente !=""){
				nombreCliente = nombreCliente.toUpperCase();
				criteria.add(Restrictions.like("root.razonSocial", nombreCliente, MatchMode.ANYWHERE));
			}

			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoNotaCredito"), "id_codigoNotaCredito");
			projectionList.add(Projections.property("root.codigoFactura"), "codigoFactura");
			projectionList.add(Projections.property("root.numeroDocumento"), "numeroDocumento");
			projectionList.add(Projections.property("root.fechaDocumento"), "fechaDocumento");
			projectionList.add(Projections.property("root.rucCliente"), "rucCliente");
			projectionList.add(Projections.property("root.razonSocial"), "razonSocial");
			projectionList.add(Projections.property("root.email"), "email");
			projectionList.add(Projections.property("root.fechaEmisionFactura"), "fechaEmisionFactura");
			projectionList.add(Projections.property("root.numeroComprobante"), "numeroComprobante");
			projectionList.add(Projections.property("root.motivo"), "motivo");
			projectionList.add(Projections.property("root.descuento"), "descuento");
			projectionList.add(Projections.property("root.totalSinImpuestos"), "totalSinImpuestos");
			projectionList.add(Projections.property("root.totalImpuestos"), "totalImpuestos");
			projectionList.add(Projections.property("root.totalIva"), "totalIva");
			projectionList.add(Projections.property("root.subTotal"), "subTotal");
			projectionList.add(Projections.property("root.totalCuenta"), "totalCuenta");
			projectionList.add(Projections.property("root.tipoCliente"), "tipoCliente");
			projectionList.add(Projections.property("root.tipoRuc"), "tipoRuc");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
						
			// Proyecciones entidad detalle pedido
			projectionList.add(Projections.property("notaCreditoDetalleDTOCols.id.codigoCompania"), "notaCreditoDetalleDTOCols_id_codigoCompania");
			projectionList.add(Projections.property("notaCreditoDetalleDTOCols.id.codigoDetalleNotaCredito"), "notaCreditoDetalleDTOCols_id_codigoDetalleNotaCredito");
			projectionList.add(Projections.property("notaCreditoDetalleDTOCols.codigoArticulo"), "notaCreditoDetalleDTOCols_codigoArticulo");
			projectionList.add(Projections.property("notaCreditoDetalleDTOCols.codigoArticuloUnidadManejo"), "notaCreditoDetalleDTOCols_codigoArticuloUnidadManejo");
			projectionList.add(Projections.property("notaCreditoDetalleDTOCols.cantidad"), "notaCreditoDetalleDTOCols_cantidad");
			projectionList.add(Projections.property("notaCreditoDetalleDTOCols.codigoNotaCredito"), "notaCreditoDetalleDTOCols_codigoNotaCredito");
			projectionList.add(Projections.property("notaCreditoDetalleDTOCols.descripcion"), "notaCreditoDetalleDTOCols_descripcion");
			projectionList.add(Projections.property("notaCreditoDetalleDTOCols.descuento"), "notaCreditoDetalleDTOCols_descuento");
			projectionList.add(Projections.property("notaCreditoDetalleDTOCols.valorUnidad"), "notaCreditoDetalleDTOCols_valorUnidad");
			projectionList.add(Projections.property("notaCreditoDetalleDTOCols.subTotal"), "notaCreditoDetalleDTOCols_subTotal");
			projectionList.add(Projections.property("notaCreditoDetalleDTOCols.estado"), "notaCreditoDetalleDTOCols_estado");
			projectionList.add(Projections.property("notaCreditoDetalleDTOCols.usuarioRegistro"), "notaCreditoDetalleDTOCols_usuarioRegistro");
			projectionList.add(Projections.property("notaCreditoDetalleDTOCols.fechaRegistro"), "notaCreditoDetalleDTOCols_fechaRegistro");
			
			projectionList.add(Projections.property("articuloDTO.id.codigoCompania"), "notaCreditoDetalleDTOCols_articuloDTO_id_codigoCompania");
			projectionList.add(Projections.property("articuloDTO.id.codigoArticulo"), "notaCreditoDetalleDTOCols_articuloDTO_id_codigoArticulo");
			projectionList.add(Projections.property("articuloDTO.codigoBarras"), "notaCreditoDetalleDTOCols_articuloDTO_codigoBarras");
			projectionList.add(Projections.property("articuloDTO.nombreArticulo"), "notaCreditoDetalleDTOCols_articuloDTO_nombreArticulo");
			projectionList.add(Projections.property("articuloDTO.costo"), "notaCreditoDetalleDTOCols_articuloDTO_costo");
			projectionList.add(Projections.property("articuloDTO.peso"), "notaCreditoDetalleDTOCols_articuloDTO_peso");
			projectionList.add(Projections.property("articuloDTO.precio"), "notaCreditoDetalleDTOCols_articuloDTO_precio");
			projectionList.add(Projections.property("articuloDTO.precioMinorista"), "notaCreditoDetalleDTOCols_articuloDTO_precioMinorista");
			projectionList.add(Projections.property("articuloDTO.porcentajeComision"), "notaCreditoDetalleDTOCols_articuloDTO_porcentajeComision");
			projectionList.add(Projections.property("articuloDTO.cantidadStock"), "notaCreditoDetalleDTOCols_articuloDTO_cantidadStock");
			projectionList.add(Projections.property("articuloDTO.estado"), "notaCreditoDetalleDTOCols_articuloDTO_estado");
			
			// Proyecciones entidad articulo impuesto
			projectionList.add(Projections.property("articuloImpuestoDTOCols.id.codigoCompania"), "notaCreditoDetalleDTOCols_articuloDTO_articuloImpuestoDTOCols_id_codigoCompania");
			projectionList.add(Projections.property("articuloImpuestoDTOCols.id.codigoImpuesto"), "notaCreditoDetalleDTOCols_articuloDTO_articuloImpuestoDTOCols_id_codigoImpuesto");
			projectionList.add(Projections.property("articuloImpuestoDTOCols.id.codigoArticulo"), "notaCreditoDetalleDTOCols_articuloDTO_articuloImpuestoDTOCols_id_codigoArticulo");
			projectionList.add(Projections.property("articuloImpuestoDTOCols.estado"), "notaCreditoDetalleDTOCols_articuloDTO_articuloImpuestoDTOCols_estado");
			
			projectionList.add(Projections.property("articuloUnidadManejoDTO.id.codigoCompania"), "notaCreditoDetalleDTOCols_articuloUnidadManejoDTO_id_codigoCompania");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.id.codigoArticulo"), "notaCreditoDetalleDTOCols_articuloUnidadManejoDTO_id_codigoArticulo");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.id.codigoArticuloUnidadManejo"), "notaCreditoDetalleDTOCols_articuloUnidadManejoDTO_id_codigoArticuloUnidadManejo");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.valorUnidadManejo"), "notaCreditoDetalleDTOCols_articuloUnidadManejoDTO_valorUnidadManejo");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.codigoValorUnidadManejo"), "notaCreditoDetalleDTOCols_articuloUnidadManejoDTO_codigoValorUnidadManejo");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.codigoTipoUnidadManejo"), "notaCreditoDetalleDTOCols_articuloUnidadManejoDTO_codigoTipoUnidadManejo");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.esPorDefecto"), "notaCreditoDetalleDTOCols_articuloUnidadManejoDTO_esPorDefecto");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.estado"), "notaCreditoDetalleDTOCols_articuloUnidadManejoDTO_estado");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.usuarioRegistro"), "notaCreditoDetalleDTOCols_articuloUnidadManejoDTO_usuarioRegistro");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.fechaRegistro"), "notaCreditoDetalleDTOCols_articuloUnidadManejoDTO_fechaRegistro");
			
			// Proyecciones catalogos
			projectionList.add(Projections.property("tipoUnidadManejoCatalogoValorDTO.nombreCatalogoValor"), "notaCreditoDetalleDTOCols_articuloUnidadManejoDTO_tipoUnidadManejoCatalogoValorDTO_nombreCatalogoValor");
			
			criteria.setProjection(projectionList);
			criteria.addOrder(Order.desc("root.id.codigoNotaCredito"));
			criteria.setResultTransformer(new MultiLevelResultTransformer(NotaCreditoDTO.class));
			Collection<NotaCreditoDTO> facturaCabeceraDTOCols = criteria.list();
			return facturaCabeceraDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de facturas.").initCause(e);
		} 
	}

	/**
	 * M\u00e9todo para obtener la nota de credito por codigo
	 * @param codigoCompania
	 * @param codigoNotaCredito
	 * @return
	 * @throws ERPException
	 */
	@Override
	public NotaCreditoDTO obtenerNotaCreditoPorCodigo(Integer codigoCompania, Long codigoNotaCredito) throws ERPException {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(NotaCreditoDTO.class, "root");
			
			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.id.codigoNotaCredito", codigoNotaCredito));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			
			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoNotaCredito"), "id_codigoNotaCredito");
			projectionList.add(Projections.property("root.codigoFactura"), "codigoFactura");
			projectionList.add(Projections.property("root.numeroDocumento"), "numeroDocumento");
			projectionList.add(Projections.property("root.fechaDocumento"), "fechaDocumento");
			projectionList.add(Projections.property("root.rucCliente"), "rucCliente");
			projectionList.add(Projections.property("root.razonSocial"), "razonSocial");
			projectionList.add(Projections.property("root.email"), "email");
			projectionList.add(Projections.property("root.fechaEmisionFactura"), "fechaEmisionFactura");
			projectionList.add(Projections.property("root.numeroComprobante"), "numeroComprobante");
			projectionList.add(Projections.property("root.motivo"), "motivo");
			projectionList.add(Projections.property("root.descuento"), "descuento");
			projectionList.add(Projections.property("root.totalSinImpuestos"), "totalSinImpuestos");
			projectionList.add(Projections.property("root.totalImpuestos"), "totalImpuestos");
			projectionList.add(Projections.property("root.totalIva"), "totalIva");
			projectionList.add(Projections.property("root.subTotal"), "subTotal");
			projectionList.add(Projections.property("root.totalCuenta"), "totalCuenta");
			projectionList.add(Projections.property("root.tipoCliente"), "tipoCliente");
			projectionList.add(Projections.property("root.tipoRuc"), "tipoRuc");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(NotaCreditoDTO.class));

			return (NotaCreditoDTO)criteria.uniqueResult();

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener nota de credito.").initCause(e);
		} 
	}

	/**
	 * M\u00e9todo para guardar y actualizar nota de credito
	 * @param notaCreditoDTO
	 * @throws ERPException
	 */
	@Override
	public void guardarActualizarNotaCredito(NotaCreditoDTO notaCreditoDTO) throws ERPException {
		try{
			if (notaCreditoDTO.getId().getCodigoCompania() == null || notaCreditoDTO.getUsuarioRegistro() == null) {
				throw new ERPException("Error", "El c\u00F3digo de compania y el id de usuario registro es requerido");
			}	
			
			sessionFactory.getCurrentSession().clear();
			if(notaCreditoDTO.getId().getCodigoNotaCredito() ==  null){
				Integer secuencialFactura = this.secuenciaDAO.obtenerSecuencialTabla(NotaCreditoID.NOMBRE_SECUENCIA);
				notaCreditoDTO.getId().setCodigoNotaCredito(Long.parseLong(""+secuencialFactura));
				notaCreditoDTO.setFechaRegistro(new Date());
				notaCreditoDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				sessionFactory.getCurrentSession().save(notaCreditoDTO);
			} else {
				notaCreditoDTO.setFechaModificacion(new Date());
				notaCreditoDTO.setUsuarioModificacion(notaCreditoDTO.getUsuarioRegistro());
				sessionFactory.getCurrentSession().update(notaCreditoDTO);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar el nota de credito."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar el nota de credito."+e.getMessage());
		} 
	}

	/**
	 * Actualizar numero de nota de credito.
	 * @param codigoCompania
	 * @param codigoNotaCredito
	 * @param userId
	 * @param numeroNotaCredito
	 * @throws ERPException
	 */
	@Override
	public void actualizarNotaCreditoNumeroNotaCredito(Integer codigoCompania, Long codigoNotaCredito, String userId, String numeroNotaCredito) throws ERPException {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();
			StringBuilder sqlExecute = new StringBuilder();
			sqlExecute.append("UPDATE SCVNTNOTACREDITO SET NUMERODOCUMENTO = :pNumeroDocumento, USUARIOMODIFICACION = :pUsuarioModificacion, FECHAMODIFICACION = :pFechaModificacion"
					+ " WHERE CODIGOCOMPANIA = :pCodigoCompania AND CODIGONOTACREDITO = :pCodigoNotaCredito ");
			SQLQuery query = session.createSQLQuery(sqlExecute.toString());
			query.setParameter("pNumeroDocumento", numeroNotaCredito);
			query.setParameter("pCodigoCompania", codigoCompania);
			query.setParameter("pCodigoNotaCredito", codigoNotaCredito);
			query.setParameter("pUsuarioModificacion", userId);
			query.setParameter("pFechaModificacion", Calendar.getInstance().getTime());
			query.executeUpdate();
		} catch (ERPException e) {
			throw (ERPException)new ERPException("Error al actualizar el secuencial de la nota de credito.").initCause(e);
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al actualizar el secuencial de la nota de credito.").initCause(e);
		}
	}
}
