/**
 * 
 */
package ec.com.erp.articulo.dao;

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
import ec.com.erp.cliente.mdl.dto.ArticuloDTO;
import ec.com.erp.cliente.mdl.dto.ArticuloImpuestoDTO;
import ec.com.erp.cliente.mdl.dto.id.ArticuloID;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2017-06-27
 */
public class ArticuloDAO implements IArticuloDAO {

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
	 * M\u00e9todo para obtener lista de articulos
	 * @return 
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<ArticuloDTO> obtenerListaArticulos(Integer codigoCompania, String codigoBarras, String nombreArticulo) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(ArticuloDTO.class, "root");
			criteria.createAlias("root.articuloImpuestoDTOCols", "articuloImpuestoDTOCols", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("root.articuloUnidadManejoDTOCols", "articuloUnidadManejoDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("articuloUnidadManejoDTO.tipoUnidadManejoCatalogoValorDTO", "tipoUnidadManejoCatalogoValorDTO", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("articuloImpuestoDTOCols.impuestoDTO", "impuestoDTO", CriteriaSpecification.LEFT_JOIN);

			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			criteria.add(Restrictions.eq("articuloUnidadManejoDTO.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			
			if(codigoBarras != null && codigoBarras !=""){
				codigoBarras = codigoBarras.toUpperCase();
				criteria.add(Restrictions.eq("root.codigoBarras", codigoBarras));
			}
			if(nombreArticulo != null && nombreArticulo !=""){
				nombreArticulo = nombreArticulo.toUpperCase();
				criteria.add(Restrictions.like("root.nombreArticulo", nombreArticulo, MatchMode.ANYWHERE));
			}
			

			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoArticulo"), "id_codigoArticulo");
			projectionList.add(Projections.property("root.codigoBarras"), "codigoBarras");
			projectionList.add(Projections.property("root.nombreArticulo"), "nombreArticulo");
			projectionList.add(Projections.property("root.peso"), "peso");
			projectionList.add(Projections.property("root.costo"), "costo");
			projectionList.add(Projections.property("root.precio"), "precio");
			projectionList.add(Projections.property("root.precioMinorista"), "precioMinorista");
			projectionList.add(Projections.property("root.cantidadStock"), "cantidadStock");
			projectionList.add(Projections.property("root.porcentajeComision"), "porcentajeComision");
			projectionList.add(Projections.property("root.porcentajeComisionMayor"), "porcentajeComisionMayor");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			// Proyecciones entidad articulo impuesto
			projectionList.add(Projections.property("articuloImpuestoDTOCols.id.codigoCompania"), "articuloImpuestoDTOCols_id_codigoCompania");
			projectionList.add(Projections.property("articuloImpuestoDTOCols.id.codigoImpuesto"), "articuloImpuestoDTOCols_id_codigoImpuesto");
			projectionList.add(Projections.property("articuloImpuestoDTOCols.id.codigoArticulo"), "articuloImpuestoDTOCols_id_codigoArticulo");
			projectionList.add(Projections.property("articuloImpuestoDTOCols.estado"), "articuloImpuestoDTOCols_estado");
			projectionList.add(Projections.property("articuloImpuestoDTOCols.usuarioRegistro"), "articuloImpuestoDTOCols_usuarioRegistro");
			projectionList.add(Projections.property("articuloImpuestoDTOCols.fechaRegistro"), "articuloImpuestoDTOCols_fechaRegistro");
			
			// Proyecciones entidad impuesto
			projectionList.add(Projections.property("impuestoDTO.id.codigoCompania"), "articuloImpuestoDTOCols_impuestoDTO_id_codigoCompania");
			projectionList.add(Projections.property("impuestoDTO.id.codigoImpuesto"), "articuloImpuestoDTOCols_impuestoDTO_id_codigoImpuesto");
			projectionList.add(Projections.property("impuestoDTO.nombreImpuesto"), "impuestoDTO_nombreImpuesto");
			projectionList.add(Projections.property("impuestoDTO.descripcion"), "impuestoDTO_descripcion");
			projectionList.add(Projections.property("impuestoDTO.valorImpuesto"), "impuestoDTO_valorImpuesto");
			projectionList.add(Projections.property("impuestoDTO.estado"), "impuestoDTO_estado");
			projectionList.add(Projections.property("impuestoDTO.usuarioRegistro"), "impuestoDTO_usuarioRegistro");
			projectionList.add(Projections.property("impuestoDTO.fechaRegistro"), "impuestoDTO_fechaRegistro");
			
			projectionList.add(Projections.property("articuloUnidadManejoDTO.id.codigoCompania"), "articuloUnidadManejoDTOCols_id_codigoCompania");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.id.codigoArticulo"), "articuloUnidadManejoDTOCols_id_codigoArticulo");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.id.codigoArticuloUnidadManejo"), "articuloUnidadManejoDTOCols_id_codigoArticuloUnidadManejo");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.valorUnidadManejo"), "articuloUnidadManejoDTOCols_valorUnidadManejo");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.codigoValorUnidadManejo"), "articuloUnidadManejoDTOCols_codigoValorUnidadManejo");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.codigoTipoUnidadManejo"), "articuloUnidadManejoDTOCols_codigoTipoUnidadManejo");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.esPorDefecto"), "articuloUnidadManejoDTOCols_esPorDefecto");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.estado"), "articuloUnidadManejoDTOCols_estado");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.usuarioRegistro"), "articuloUnidadManejoDTOCols_usuarioRegistro");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.fechaRegistro"), "articuloUnidadManejoDTOCols_fechaRegistro");
			
			// Proyecciones catalogos
			projectionList.add(Projections.property("tipoUnidadManejoCatalogoValorDTO.nombreCatalogoValor"), "articuloUnidadManejoDTOCols_tipoUnidadManejoCatalogoValorDTO_nombreCatalogoValor");
			
			criteria.setProjection(projectionList);
			criteria.addOrder(Order.asc("root.nombreArticulo"));
			criteria.setResultTransformer(new MultiLevelResultTransformer(ArticuloDTO.class));
			Collection<ArticuloDTO> articuloDTOCols = new  ArrayList<ArticuloDTO>();
			articuloDTOCols =  criteria.list();

			return articuloDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de art\u00EDculos.").initCause(e);
		} 
	}
	
	/**
	 * M\u00e9todo para obtener articulo por id
	 * @return 
	 * @throws ERPException
	 */
	public ArticuloDTO obtenerListaArticuloById(Integer codigoCompania, Integer codigoArticulo) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(ArticuloDTO.class, "root");
			criteria.createAlias("root.articuloImpuestoDTOCols", "articuloImpuestoDTOCols", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("articuloImpuestoDTOCols.impuestoDTO", "impuestoDTO", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("root.articuloUnidadManejoDTOCols", "articuloUnidadManejoDTO", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("articuloUnidadManejoDTO.tipoUnidadManejoCatalogoValorDTO", "tipoUnidadManejoCatalogoValorDTO", CriteriaSpecification.LEFT_JOIN);

			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.id.codigoArticulo", codigoArticulo));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			

			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoArticulo"), "id_codigoArticulo");
			projectionList.add(Projections.property("root.codigoBarras"), "codigoBarras");
			projectionList.add(Projections.property("root.nombreArticulo"), "nombreArticulo");
			projectionList.add(Projections.property("root.peso"), "peso");
			projectionList.add(Projections.property("root.costo"), "costo");
			projectionList.add(Projections.property("root.precio"), "precio");
			projectionList.add(Projections.property("root.precioMinorista"), "precioMinorista");
			projectionList.add(Projections.property("root.cantidadStock"), "cantidadStock");
			projectionList.add(Projections.property("root.porcentajeComision"), "porcentajeComision");
			projectionList.add(Projections.property("root.porcentajeComisionMayor"), "porcentajeComisionMayor");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			// Proyecciones entidad articulo impuesto
			projectionList.add(Projections.property("articuloImpuestoDTOCols.id.codigoCompania"), "articuloImpuestoDTOCols_id_codigoCompania");
			projectionList.add(Projections.property("articuloImpuestoDTOCols.id.codigoImpuesto"), "articuloImpuestoDTOCols_id_codigoImpuesto");
			projectionList.add(Projections.property("articuloImpuestoDTOCols.id.codigoArticulo"), "articuloImpuestoDTOCols_id_codigoArticulo");
			projectionList.add(Projections.property("articuloImpuestoDTOCols.estado"), "articuloImpuestoDTOCols_estado");
			projectionList.add(Projections.property("articuloImpuestoDTOCols.usuarioRegistro"), "articuloImpuestoDTOCols_usuarioRegistro");
			projectionList.add(Projections.property("articuloImpuestoDTOCols.fechaRegistro"), "articuloImpuestoDTOCols_fechaRegistro");
			
			// Proyecciones entidad impuesto
			projectionList.add(Projections.property("impuestoDTO.id.codigoCompania"), "articuloImpuestoDTOCols_impuestoDTO_id_codigoCompania");
			projectionList.add(Projections.property("impuestoDTO.id.codigoImpuesto"), "articuloImpuestoDTOCols_impuestoDTO_id_codigoImpuesto");
			projectionList.add(Projections.property("impuestoDTO.nombreImpuesto"), "impuestoDTO_nombreImpuesto");
			projectionList.add(Projections.property("impuestoDTO.descripcion"), "impuestoDTO_descripcion");
			projectionList.add(Projections.property("impuestoDTO.valorImpuesto"), "impuestoDTO_valorImpuesto");
			projectionList.add(Projections.property("impuestoDTO.estado"), "impuestoDTO_estado");
			projectionList.add(Projections.property("impuestoDTO.usuarioRegistro"), "impuestoDTO_usuarioRegistro");
			projectionList.add(Projections.property("impuestoDTO.fechaRegistro"), "impuestoDTO_fechaRegistro");
			
			projectionList.add(Projections.property("articuloUnidadManejoDTO.id.codigoCompania"), "articuloUnidadManejoDTOCols_id_codigoCompania");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.id.codigoArticulo"), "articuloUnidadManejoDTOCols_id_codigoArticulo");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.id.codigoArticuloUnidadManejo"), "articuloUnidadManejoDTOCols_id_codigoArticuloUnidadManejo");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.valorUnidadManejo"), "articuloUnidadManejoDTOCols_valorUnidadManejo");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.codigoValorUnidadManejo"), "articuloUnidadManejoDTOCols_codigoValorUnidadManejo");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.codigoTipoUnidadManejo"), "articuloUnidadManejoDTOCols_codigoTipoUnidadManejo");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.esPorDefecto"), "articuloUnidadManejoDTOCols_esPorDefecto");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.estado"), "articuloUnidadManejoDTOCols_estado");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.usuarioRegistro"), "articuloUnidadManejoDTOCols_usuarioRegistro");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.fechaRegistro"), "articuloUnidadManejoDTOCols_fechaRegistro");
			
			// Proyecciones catalogos
			projectionList.add(Projections.property("tipoUnidadManejoCatalogoValorDTO.nombreCatalogoValor"), "articuloUnidadManejoDTOCols_tipoUnidadManejoCatalogoValorDTO_nombreCatalogoValor");
						
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(ArticuloDTO.class));
			ArticuloDTO resultado = (ArticuloDTO) criteria.uniqueResult();
			return resultado;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de art\u00EDculos.").initCause(e);
		} 
	}
	
	/**
	 * Metodo para guardar y actualizar articulos
	 * @param articuloDTO
	 * @throws ERPException
	 */
	public void guardarActualizarArticulo(ArticuloDTO articuloDTO) throws ERPException{
		try{
			if (articuloDTO.getId().getCodigoCompania() == null || articuloDTO.getUsuarioRegistro() == null) {
				throw new ERPException("Error", "El c\u00F3digo de compania y el id de usuario registro es requerido");
			}	
			
			sessionFactory.getCurrentSession().clear();
			if(articuloDTO.getId().getCodigoArticulo() ==  null){
				Integer secuencialArticulo  = this.secuenciaDAO.obtenerSecuencialTabla(ArticuloID.NOMBRE_SECUENCIA);
				articuloDTO.getId().setCodigoArticulo(secuencialArticulo);
				articuloDTO.setFechaRegistro(new Date());
				articuloDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				sessionFactory.getCurrentSession().save(articuloDTO);
			}
			else
			{
				articuloDTO.setFechaModificacion(new Date());
				articuloDTO.setUsuarioModificacion(articuloDTO.getUsuarioRegistro());
				sessionFactory.getCurrentSession().update(articuloDTO);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar el art\u00EDculo."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar el art\u00EDculo."+e.getMessage());
		} 
	}
	
	/**
	 * M\u00e9todo para obtener lista de impuestos por articulo
	 * @return 
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<ArticuloImpuestoDTO> obtenerListaArticuloImpuesto(Integer codigoCompania, Integer codigoArticulo) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(ArticuloImpuestoDTO.class, "root");

			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.id.codigoArticulo", codigoArticulo));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			

			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			// Proyecciones entidad articulo impuesto
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoImpuesto"), "id_codigoImpuesto");
			projectionList.add(Projections.property("root.id.codigoArticulo"), "id_codigoArticulo");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(ArticuloImpuestoDTO.class));
			Collection<ArticuloImpuestoDTO> articuloImpuestoDTOCols = new  ArrayList<ArticuloImpuestoDTO>();
			articuloImpuestoDTOCols =  criteria.list();

			return articuloImpuestoDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de art\u00EDculos.").initCause(e);
		} 
	}
	
	/**
	 * Metodo para guardar y actualizar articulo impuesto
	 * @param articuloImpuestoDTO
	 * @throws ERPException
	 */
	@Override
	public void guardarActualizarArticuloImpuesto(ArticuloImpuestoDTO articuloImpuestoDTO) throws ERPException{
		try{
			if (articuloImpuestoDTO.getId().getCodigoCompania() == null || articuloImpuestoDTO.getUsuarioRegistro() == null) {
				throw new ERPException("Error", "El c\u00F3digo de compania y el id de usuario registro es requerido");
			}	
			
			sessionFactory.getCurrentSession().clear();
			if(articuloImpuestoDTO.getFechaRegistro() ==  null){
				articuloImpuestoDTO.setFechaRegistro(new Date());
				articuloImpuestoDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				sessionFactory.getCurrentSession().save(articuloImpuestoDTO);
			}else{
				articuloImpuestoDTO.setFechaModificacion(new Date());
				articuloImpuestoDTO.setUsuarioModificacion(articuloImpuestoDTO.getUsuarioRegistro());
				sessionFactory.getCurrentSession().update(articuloImpuestoDTO);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar el art\u00EDculo."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar el art\u00EDculo."+e.getMessage());
		} 
	}
	
	/**
	 * Metodo para obtener imagen del articulo
	 * @param codigoCompania
	 * @param codigoArticulo
	 * @return
	 */
	@Override
	public byte[] obtenerImagen(Integer codigoCompania, Integer codigoArticulo) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(ArticuloDTO.class, "root");

			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.id.codigoArticulo", codigoArticulo));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));

			//proyecciones
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.imagen"), "imagen");
			criteria.setProjection(projectionList);
			return (byte[])criteria.uniqueResult();

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener imagen.").initCause(e);
		} 
	}
	
	
	/**
	 * M\u00e9todo para obtener lista de articulos para catalogos
	 * @return 
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<ArticuloDTO> obtenerArticulosCatalogos(Integer codigoCompania, String codigoBarras, String nombreArticulo) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(ArticuloDTO.class, "root");

			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			
			if(codigoBarras != null && codigoBarras !=""){
				codigoBarras = codigoBarras.toUpperCase();
				criteria.add(Restrictions.eq("root.codigoBarras", codigoBarras));
			}
			if(nombreArticulo != null && nombreArticulo !=""){
				nombreArticulo = nombreArticulo.toUpperCase();
				criteria.add(Restrictions.like("root.nombreArticulo", nombreArticulo, MatchMode.ANYWHERE));
			}
			
			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoArticulo"), "id_codigoArticulo");
			projectionList.add(Projections.property("root.codigoBarras"), "codigoBarras");
			projectionList.add(Projections.property("root.nombreArticulo"), "nombreArticulo");
			projectionList.add(Projections.property("root.peso"), "peso");
			projectionList.add(Projections.property("root.costo"), "costo");
			projectionList.add(Projections.property("root.precio"), "precio");
			projectionList.add(Projections.property("root.imagen"), "imagen");
			projectionList.add(Projections.property("root.precioMinorista"), "precioMinorista");
			projectionList.add(Projections.property("root.cantidadStock"), "cantidadStock");
			projectionList.add(Projections.property("root.porcentajeComision"), "porcentajeComision");
			projectionList.add(Projections.property("root.porcentajeComisionMayor"), "porcentajeComisionMayor");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			criteria.setProjection(projectionList);
			criteria.addOrder(Order.asc("root.nombreArticulo"));
			criteria.setResultTransformer(new MultiLevelResultTransformer(ArticuloDTO.class));
			Collection<ArticuloDTO> articuloDTOCols = new  ArrayList<ArticuloDTO>();
			articuloDTOCols =  criteria.list();

			return articuloDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de art\u00EDculos.").initCause(e);
		} 
	}
	
	/**
	 * M\u00e9todo para obtener lista de articulos con imagen
	 * @return 
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<ArticuloDTO> obtenerArticulosImagen(Integer codigoCompania) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(ArticuloDTO.class, "root");

			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			criteria.add(Restrictions.isNotNull("root.imagen"));
			
			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoArticulo"), "id_codigoArticulo");
			projectionList.add(Projections.property("root.codigoBarras"), "codigoBarras");
			projectionList.add(Projections.property("root.imagen"), "imagen");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(ArticuloDTO.class));
			Collection<ArticuloDTO> articuloDTOCols = new  ArrayList<ArticuloDTO>();
			articuloDTOCols =  criteria.list();

			return articuloDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de art\u00EDculos.").initCause(e);
		} 
	}
	
}
