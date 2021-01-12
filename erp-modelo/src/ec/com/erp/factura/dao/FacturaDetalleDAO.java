/**
 * 
 */
package ec.com.erp.factura.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
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
import ec.com.erp.cliente.mdl.dto.FacturaDetalleDTO;
import ec.com.erp.cliente.mdl.dto.id.FacturaDetalleID;
import ec.com.erp.cliente.mdl.vo.ReporteVentasVO;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2017-06-27
 */
public class FacturaDetalleDAO implements IFacturaDetalleDAO {

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
	 * M\u00e9todo para obtener reporte de ventas por articulo vendedor
	 * @param codigoCompania
	 * @param documentoVendedor
	 * @param fechaFacturaInicio
	 * @param fechaFacturaFin
	 * @return
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<ReporteVentasVO> obtenerReorteVentas(Integer codigoCompania, String documentoVendedor, String nombreVendedor, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(FacturaDetalleDTO.class, "root");
			criteria.createAlias("root.articuloDTO", "articuloDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("root.articuloUnidadManejoDTO", "articuloUnidadManejoDTO", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("root.facturaCabeceraDTO", "facturaCabeceraDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("facturaCabeceraDTO.vendedorDTO", "vendedorDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("vendedorDTO.personaDTO", "personaDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("articuloUnidadManejoDTO.tipoUnidadManejoCatalogoValorDTO", "tipoUnidadManejoCatalogoValorDTO", CriteriaSpecification.INNER_JOIN);
			
			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			criteria.add(Restrictions.eq("facturaCabeceraDTO.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			
			if(fechaFacturaInicio != null && fechaFacturaFin != null){
				criteria.add(Restrictions.between("facturaCabeceraDTO.fechaDocumento", fechaFacturaInicio, fechaFacturaFin));
			}
			if(StringUtils.isNotBlank(documentoVendedor)){
				criteria.add(Restrictions.like("personaDTO.numeroDocumento", documentoVendedor, MatchMode.ANYWHERE));
			}
			if(StringUtils.isNotBlank(nombreVendedor)){
				nombreVendedor = nombreVendedor.toUpperCase();
				criteria.add(Restrictions.like("personaDTO.nombreCompleto", nombreVendedor, MatchMode.ANYWHERE));
			}
			

			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.groupProperty("personaDTO.nombreCompleto"), "nombreCompleto");
			projectionList.add(Projections.groupProperty("articuloDTO.nombreArticulo"), "nombreArticulo");
			projectionList.add(Projections.groupProperty("articuloDTO.porcentajeComision"), "porcentajeComision");
			projectionList.add(Projections.groupProperty("articuloDTO.precio"), "precioMayorista");
			projectionList.add(Projections.groupProperty("articuloDTO.precioMinorista"), "precioMinorista");
			projectionList.add(Projections.groupProperty("articuloUnidadManejoDTO.codigoValorUnidadManejo"), "codigoValorUnidadManejo");
			projectionList.add(Projections.groupProperty("articuloUnidadManejoDTO.valorUnidadManejo"), "valorUnidadManejo");
			projectionList.add(Projections.sum("root.cantidad").as("cantidadVendida"));
			projectionList.add(Projections.sum("root.subTotal").as("valorVendido"));
			criteria.setProjection(projectionList);
			criteria.addOrder(Order.asc("personaDTO.nombreCompleto"));
			criteria.addOrder(Order.asc("articuloDTO.nombreArticulo"));
			criteria.setResultTransformer(new MultiLevelResultTransformer(ReporteVentasVO.class));
			Collection<ReporteVentasVO> facturaCabeceraDTOCols = new ArrayList<ReporteVentasVO>();
			facturaCabeceraDTOCols = criteria.list();

			return facturaCabeceraDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de facturas.").initCause(e);
		} 
	}
	
	/**
	 * M\u00e9todo para obtener lista de detalles por factura
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @return Collection<FacturaDetalleDTO>
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<FacturaDetalleDTO> obtenerListaDetalleFacturaByNumeroFactura(Integer codigoCompania, String numeroDocumento) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(FacturaDetalleDTO.class, "root");
			criteria.createAlias("root.facturaCabeceraDTO", "facturaCabeceraDTO", CriteriaSpecification.INNER_JOIN);

			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			if(numeroDocumento != null && numeroDocumento !=""){
				numeroDocumento = numeroDocumento.toUpperCase();
				criteria.add(Restrictions.eq("facturaCabeceraDTO.numeroDocumento", numeroDocumento));
			}

			// Proyecciones entidad detalle factura
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoDetalleFactura"), "id_codigoDetalleFactura");
			projectionList.add(Projections.property("root.codigoArticulo"), "codigoArticulo");
			projectionList.add(Projections.property("root.codigoArticuloUnidadManejo"), "codigoArticuloUnidadManejo");
			projectionList.add(Projections.property("root.cantidad"), "cantidad");
			projectionList.add(Projections.property("root.codigoFactura"), "codigoFactura");
			projectionList.add(Projections.property("root.descripcion"), "descripcion");
			projectionList.add(Projections.property("root.valorUnidad"), "valorUnidad");
			projectionList.add(Projections.property("root.subTotal"), "subTotal");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(FacturaDetalleDTO.class));
			Collection<FacturaDetalleDTO> facturaDetalleDTOCols = new  ArrayList<FacturaDetalleDTO>();
			facturaDetalleDTOCols =  criteria.list();

			return facturaDetalleDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de detalle factura.").initCause(e);
		} 
	}
	
	/**
	 * M\u00e9todo para guardar y actualizar detalle factura
	 * @param facturaDetalleDTO
	 * @throws ERPException
	 */
	@Override
	public void guardarActualizarDetalleFactura(FacturaDetalleDTO facturaDetalleDTO) throws ERPException{
		try{
			if (facturaDetalleDTO.getId().getCodigoCompania() == null || facturaDetalleDTO.getUsuarioRegistro() == null) {
				throw new ERPException("Error", "El c\u00F3digo de compania y el id de usuario registro es requerido");
			}	
			
			sessionFactory.getCurrentSession().clear();
			if(facturaDetalleDTO.getId().getCodigoDetalleFactura() ==  null){
				Integer secuencialDetalleFactura  = this.secuenciaDAO.obtenerSecuencialTabla(FacturaDetalleID.NOMBRE_SECUENCIA);
				facturaDetalleDTO.getId().setCodigoDetalleFactura(Long.parseLong(""+secuencialDetalleFactura));
				facturaDetalleDTO.setFechaRegistro(new Date());
				facturaDetalleDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				sessionFactory.getCurrentSession().save(facturaDetalleDTO);
			}
			else
			{
				facturaDetalleDTO.setFechaModificacion(new Date());
				facturaDetalleDTO.setUsuarioModificacion(facturaDetalleDTO.getUsuarioRegistro());
				sessionFactory.getCurrentSession().update(facturaDetalleDTO);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar el detalle de factura."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar el detalle de factura."+e.getMessage());
		} 
	}

}
