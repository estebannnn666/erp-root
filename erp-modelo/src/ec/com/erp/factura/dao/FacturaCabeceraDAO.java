/**
 * 
 */
package ec.com.erp.factura.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.FacturaCabeceraDTO;
import ec.com.erp.cliente.mdl.dto.id.FacturaCabeceraID;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2018-05-05
 */
public class FacturaCabeceraDAO implements IFacturaCabeceraDAO {

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
	 * M\u00e9todo para obtener lista de facturas por filtros de busqueda
	 * @param codigoCompania
	 * @param numeroFactura
	 * @param fechaFacturaInicio
	 * @param fechaFacturaFin
	 * @param docClienteProveedor
	 * @param nombClienteProveedor
	 * @param pagado
	 * @param tipoDocumento
	 * @return Collection<FacturaCabeceraDTO>
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<FacturaCabeceraDTO> obtenerListaFacturas(Integer codigoCompania, String numeroFactura, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin,  String docClienteProveedor, String nombClienteProveedor, Boolean pagado, String tipoDocumento) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(FacturaCabeceraDTO.class, "root");
			criteria.createAlias("root.facturaDetalleDTOCols", "facturaDetalleDTOCols", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("facturaDetalleDTOCols.articuloDTO", "articuloDTO", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("root.tipoDocumentoCatalogoValorDTO", "tipoDocumentoCatalogoValorDTO", CriteriaSpecification.INNER_JOIN);
			
			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			
			if(numeroFactura != null && numeroFactura !=""){
				numeroFactura = numeroFactura.toUpperCase();
				criteria.add(Restrictions.eq("root.numeroDocumento", numeroFactura));
			}
			if(fechaFacturaInicio != null && fechaFacturaFin != null){
				criteria.add(Restrictions.between("root.fechaDocumento", fechaFacturaInicio, fechaFacturaFin));
			}
			if(docClienteProveedor != null && docClienteProveedor !=""){
				criteria.add(Restrictions.like("root.rucDocumento", docClienteProveedor, MatchMode.ANYWHERE));
			}
			if(nombClienteProveedor != null && nombClienteProveedor !=""){
				nombClienteProveedor = nombClienteProveedor.toUpperCase();
				criteria.add(Restrictions.like("root.nombreClienteProveedor", nombClienteProveedor, MatchMode.ANYWHERE));
			}
			if(pagado != null){
				criteria.add(Restrictions.eq("root.pagado", pagado));
			}
			if(tipoDocumento != null && tipoDocumento !=""){
				tipoDocumento = tipoDocumento.toUpperCase();
				criteria.add(Restrictions.eq("root.codigoValorTipoDocumento", tipoDocumento));
			}

			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoFactura"), "id_codigoFactura");
			projectionList.add(Projections.property("root.codigoReferenciaFactura"), "codigoReferenciaFactura");
			projectionList.add(Projections.property("root.numeroDocumento"), "numeroDocumento");
			projectionList.add(Projections.property("root.fechaDocumento"), "fechaDocumento");
			projectionList.add(Projections.property("root.rucDocumento"), "rucDocumento");
			projectionList.add(Projections.property("root.nombreClienteProveedor"), "nombreClienteProveedor");
			projectionList.add(Projections.property("root.direccion"), "direccion");
			projectionList.add(Projections.property("root.telefono"), "telefono");
			projectionList.add(Projections.property("root.pagado"), "pagado");
			projectionList.add(Projections.property("root.totalCuenta"), "totalCuenta");
			projectionList.add(Projections.property("root.codigoTipoDocumento"), "codigoTipoDocumento");
			projectionList.add(Projections.property("root.codigoValorTipoDocumento"), "codigoValorTipoDocumento");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			// Proyecciones entidad detalle pedido
			projectionList.add(Projections.property("facturaDetalleDTOCols.id.codigoCompania"), "facturaDetalleDTOCols_id_codigoCompania");
			projectionList.add(Projections.property("facturaDetalleDTOCols.id.codigoDetalleFactura"), "facturaDetalleDTOCols_id_codigoDetalleFactura");
			projectionList.add(Projections.property("facturaDetalleDTOCols.codigoArticulo"), "facturaDetalleDTOCols_codigoArticulo");
			projectionList.add(Projections.property("facturaDetalleDTOCols.cantidad"), "facturaDetalleDTOCols_cantidad");
			projectionList.add(Projections.property("facturaDetalleDTOCols.codigoFactura"), "facturaDetalleDTOCols_codigoFactura");
			projectionList.add(Projections.property("facturaDetalleDTOCols.descripcion"), "facturaDetalleDTOCols_descripcion");
			projectionList.add(Projections.property("facturaDetalleDTOCols.valorUnidad"), "facturaDetalleDTOCols_valorUnidad");
			projectionList.add(Projections.property("facturaDetalleDTOCols.subTotal"), "facturaDetalleDTOCols_subTotal");
			projectionList.add(Projections.property("facturaDetalleDTOCols.estado"), "facturaDetalleDTOCols_estado");
			projectionList.add(Projections.property("facturaDetalleDTOCols.usuarioRegistro"), "facturaDetalleDTOCols_usuarioRegistro");
			projectionList.add(Projections.property("facturaDetalleDTOCols.fechaRegistro"), "facturaDetalleDTOCols_fechaRegistro");
			
			projectionList.add(Projections.property("articuloDTO.id.codigoCompania"), "facturaDetalleDTOCols_articuloDTO_id_codigoCompania");
			projectionList.add(Projections.property("articuloDTO.id.codigoArticulo"), "facturaDetalleDTOCols_articuloDTO_id_codigoArticulo");
			projectionList.add(Projections.property("articuloDTO.codigoBarras"), "facturaDetalleDTOCols_articuloDTO_codigoBarras");
			projectionList.add(Projections.property("articuloDTO.nombreArticulo"), "facturaDetalleDTOCols_articuloDTO_nombreArticulo");
			projectionList.add(Projections.property("articuloDTO.peso"), "facturaDetalleDTOCols_articuloDTO_peso");
			projectionList.add(Projections.property("articuloDTO.precio"), "facturaDetalleDTOCols_articuloDTO_precio");
			projectionList.add(Projections.property("articuloDTO.cantidadStock"), "facturaDetalleDTOCols_articuloDTO_cantidadStock");
			projectionList.add(Projections.property("articuloDTO.estado"), "facturaDetalleDTOCols_articuloDTO_estado");
			
			// Proyecciobes entidad catalogo valor
			projectionList.add(Projections.property("tipoDocumentoCatalogoValorDTO.nombreCatalogoValor"), "tipoDocumentoCatalogoValorDTO_nombreCatalogoValor");
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(FacturaCabeceraDTO.class));
			Collection<FacturaCabeceraDTO> facturaCabeceraDTOCols = new ArrayList<FacturaCabeceraDTO>();
			facturaCabeceraDTOCols = criteria.list();

			return facturaCabeceraDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de facturas.").initCause(e);
		} 
	}
	
	/**
	 * M\u00e9todo para guardar y actualizar factura cabecera
	 * @param facturaCabeceraDTO
	 * @throws ERPException
	 */
	@Override
	public void guardarActualizarFacturaCabecera(FacturaCabeceraDTO facturaCabeceraDTO) throws ERPException{
		try{
			if (facturaCabeceraDTO.getId().getCodigoCompania() == null || facturaCabeceraDTO.getUsuarioRegistro() == null) {
				throw new ERPException("El c\u00F3digo de compania y el id de usuario registro es requerido");
			}	
			
			sessionFactory.getCurrentSession().clear();
			if(facturaCabeceraDTO.getId().getCodigoFactura() ==  null){
				Integer secuencialFactura = this.secuenciaDAO.obtenerSecuencialTabla(FacturaCabeceraID.NOMBRE_SECUENCIA);
				facturaCabeceraDTO.getId().setCodigoFactura(Long.parseLong(""+secuencialFactura));
				if(facturaCabeceraDTO.getCodigoValorTipoDocumento().equals(ERPConstantes.CODIGO_CATALOGO_VALOR_DOCUMENTO_VENTAS)) {
					Integer secuencialFacturaVentas = this.secuenciaDAO.obtenerSecuencialTabla(FacturaCabeceraID.NOMBRE_SECUENCIA_VENTA);
					facturaCabeceraDTO.setCodigoReferenciaFactura("FAC-"+secuencialFacturaVentas);
				}
				if(facturaCabeceraDTO.getCodigoValorTipoDocumento().equals(ERPConstantes.CODIGO_CATALOGO_VALOR_DOCUMENTO_COMPRAS)) {
					Integer secuencialFacturaCompras = this.secuenciaDAO.obtenerSecuencialTabla(FacturaCabeceraID.NOMBRE_SECUENCIA_COMPRA);
					facturaCabeceraDTO.setCodigoReferenciaFactura("DOC-"+secuencialFacturaCompras);
				}
				facturaCabeceraDTO.setFechaRegistro(new Date());
				facturaCabeceraDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				sessionFactory.getCurrentSession().save(facturaCabeceraDTO);
			}
			else
			{
				facturaCabeceraDTO.setFechaModificacion(new Date());
				facturaCabeceraDTO.setUsuarioModificacion(facturaCabeceraDTO.getUsuarioRegistro());
				sessionFactory.getCurrentSession().update(facturaCabeceraDTO);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Ocurrio un error al guardar o actualizar el factura cabecera."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Ocurrio un error al guardar o actualizar el factura cabecera."+e.getMessage());
		} 
	}

	/**
	 * Metodo para obtener el valor de venta por mes y tipo
	 * @param codigoCompania
	 * @param fechaInicio
	 * @param fechaFin
	 * @param tipoDocumento
	 * @return
	 * @throws ERPException
	 */
	@Override
	public BigDecimal obtenerComprasVentas(Integer codigoCompania, Timestamp fechaInicio, Timestamp fechaFin, String tipoDocumento, Boolean pagada) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(FacturaCabeceraDTO.class, "root");
			
			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			criteria.add(Restrictions.between("root.fechaDocumento", fechaInicio, fechaFin));
			if(tipoDocumento != null && tipoDocumento !=""){
				tipoDocumento = tipoDocumento.toUpperCase();
				criteria.add(Restrictions.eq("root.codigoValorTipoDocumento", tipoDocumento));
			}
			
			if(pagada != null){
				criteria.add(Restrictions.eq("root.pagado", pagada));
			}
			
			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.sum("root.totalCuenta"));
			criteria.setProjection(projectionList);
			BigDecimal resultado = (BigDecimal)criteria.uniqueResult();

			return resultado;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de facturas.").initCause(e);
		} 
	}
	
	/**
	 * Obtener numero de facturas por filtros
	 * @param codigoCompania
	 * @param fechaInicio
	 * @param fechaFin
	 * @param tipoDocumento
	 * @param pagada
	 * @return
	 * @throws ERPException
	 */
	@Override
	public Long obtenerNumeroFacturasComprasVentas(Integer codigoCompania, Timestamp fechaInicio, Timestamp fechaFin, String tipoDocumento, Boolean pagada) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(FacturaCabeceraDTO.class, "root");
			
			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			criteria.add(Restrictions.between("root.fechaDocumento", fechaInicio, fechaFin));
			if(tipoDocumento != null && tipoDocumento !=""){
				tipoDocumento = tipoDocumento.toUpperCase();
				criteria.add(Restrictions.eq("root.codigoValorTipoDocumento", tipoDocumento));
			}
			
			if(pagada != null){
				criteria.add(Restrictions.eq("root.pagado", pagada));
			}

			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.count("root.numeroDocumento"));
			criteria.setProjection(projectionList);
			Long resultado = (Long)criteria.uniqueResult();
			if(resultado == null){
				resultado = 0L;
			}
			return resultado;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de facturas.").initCause(e);
		} 
	}
}
