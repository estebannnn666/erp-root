/**
 * 
 */
package ec.com.erp.unidadmanejo.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ArticuloUnidadManejoDTO;
import ec.com.erp.cliente.mdl.dto.id.ArticuloUnidadManejoID;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2020-07-23
 */
public class UnidadManejoDAO implements IUnidadManejoDAO {

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
	 * M\u00e9todo para obtener lista de unidades de manejo
	 * @return 
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<ArticuloUnidadManejoDTO> obtenerListaArticulosUnidadManejo(Integer codigoCompania, Integer codigoArticulo) throws ERPException{
		try { 
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(ArticuloUnidadManejoDTO.class, "root");
			criteria.createAlias("root.tipoUnidadManejoCatalogoValorDTO", "tipoUnidadManejoCatalogoValorDTO", CriteriaSpecification.INNER_JOIN);
			

			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.id.codigoArticulo", codigoArticulo));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));

			// Proyecciones entidad impuesto
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoArticulo"), "id_codigoArticulo");
			projectionList.add(Projections.property("root.id.codigoArticuloUnidadManejo"), "id_codigoArticuloUnidadManejo");
			projectionList.add(Projections.property("root.valorUnidadManejo"), "valorUnidadManejo");
			projectionList.add(Projections.property("root.codigoValorUnidadManejo"), "codigoValorUnidadManejo");
			projectionList.add(Projections.property("root.codigoTipoUnidadManejo"), "codigoTipoUnidadManejo");
			projectionList.add(Projections.property("root.esPorDefecto"), "esPorDefecto");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			// Proyecciones catalogos
			projectionList.add(Projections.property("tipoUnidadManejoCatalogoValorDTO.nombreCatalogoValor"), "tipoUnidadManejoCatalogoValorDTO_nombreCatalogoValor");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(ArticuloUnidadManejoDTO.class));
			Collection<ArticuloUnidadManejoDTO> unidadManejoDTOCols = new  ArrayList<>();
			unidadManejoDTOCols =  criteria.list();

			return unidadManejoDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de unidades de manejo.").initCause(e);
		} 
	}
	
	/**
	 * M\u00e9todo para obtener lista de unidades de manejo por codigo de barras
	 * @return 
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<ArticuloUnidadManejoDTO> obtenerListaUnidadManejoByCodigoBarras(Integer codigoCompania, String codigoBarras) throws ERPException{
		try { 
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(ArticuloUnidadManejoDTO.class, "root");
			criteria.createAlias("root.articuloDTO", "articuloDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("root.tipoUnidadManejoCatalogoValorDTO", "tipoUnidadManejoCatalogoValorDTO", CriteriaSpecification.INNER_JOIN);
			

			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("articuloDTO.codigoBarras", codigoBarras));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));

			// Proyecciones entidad impuesto
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoArticulo"), "id_codigoArticulo");
			projectionList.add(Projections.property("root.id.codigoArticuloUnidadManejo"), "id_codigoArticuloUnidadManejo");
			projectionList.add(Projections.property("root.valorUnidadManejo"), "valorUnidadManejo");
			projectionList.add(Projections.property("root.codigoValorUnidadManejo"), "codigoValorUnidadManejo");
			projectionList.add(Projections.property("root.codigoTipoUnidadManejo"), "codigoTipoUnidadManejo");
			projectionList.add(Projections.property("root.esPorDefecto"), "esPorDefecto");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			// Proyecciones catalogos
			projectionList.add(Projections.property("tipoUnidadManejoCatalogoValorDTO.nombreCatalogoValor"), "tipoUnidadManejoCatalogoValorDTO_nombreCatalogoValor");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(ArticuloUnidadManejoDTO.class));
			Collection<ArticuloUnidadManejoDTO> unidadManejoDTOCols = new  ArrayList<>();
			unidadManejoDTOCols =  criteria.list();

			return unidadManejoDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de unidades de manejo.").initCause(e);
		} 
	}
	
	/**
	 * Metodo para guardar y actualizar unidad de manejo de articulo
	 * @param articuloUnidadManejo
	 * @throws ERPException
	 */
	@Override
	public void guardarActualizarUnidadManejo(ArticuloUnidadManejoDTO articuloUnidadManejo) throws ERPException{
		try{
			if (articuloUnidadManejo.getId().getCodigoCompania() == null || articuloUnidadManejo.getUsuarioRegistro() == null) {
				throw new ERPException("Error", "El c\u00F3digo de compania y el id de usuario registro es requerido");
			}	
			
			sessionFactory.getCurrentSession().clear();
			articuloUnidadManejo.setCodigoTipoUnidadManejo(ERPConstantes.CODIGO_CATALOGO_TIPOS_UNIDAD_MANEJO);
			if(articuloUnidadManejo.getId().getCodigoArticuloUnidadManejo() ==  null){
				Integer secuencialUnidadManejo  = this.secuenciaDAO.obtenerSecuencialTabla(ArticuloUnidadManejoID.NOMBRE_SECUENCIA);
				articuloUnidadManejo.getId().setCodigoArticuloUnidadManejo(secuencialUnidadManejo);
				articuloUnidadManejo.setFechaRegistro(new Date());
				articuloUnidadManejo.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				sessionFactory.getCurrentSession().save(articuloUnidadManejo);
			}else{
				articuloUnidadManejo.setFechaModificacion(new Date());
				articuloUnidadManejo.setUsuarioModificacion(articuloUnidadManejo.getUsuarioRegistro());
				sessionFactory.getCurrentSession().update(articuloUnidadManejo);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar unidad de manejo."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar unidad de manejo."+e.getMessage());
		} 
	}

}
