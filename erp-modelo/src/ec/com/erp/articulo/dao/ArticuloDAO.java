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
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ArticuloDTO;
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
			projectionList.add(Projections.property("root.precio"), "precio");
			projectionList.add(Projections.property("root.cantidadStock"), "cantidadStock");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(ArticuloDTO.class));
			Collection<ArticuloDTO> articuloDTOCols = new  ArrayList<ArticuloDTO>();
			articuloDTOCols =  criteria.list();

			return articuloDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de convenios con diseniadores.").initCause(e);
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
			projectionList.add(Projections.property("root.precio"), "precio");
			projectionList.add(Projections.property("root.cantidadStock"), "cantidadStock");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(ArticuloDTO.class));
			ArticuloDTO resultado = (ArticuloDTO) criteria.uniqueResult();
			return resultado;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de convenios con diseniadores.").initCause(e);
		} 
	}
	
	/**
	 * Metodo para guardar y actualizar articulos
	 * @param articuloDTO
	 * @throws ERPException
	 */
	public void transGuardarActualizarArticulo(ArticuloDTO articuloDTO) throws ERPException{
		try{
			if (articuloDTO.getId().getCodigoCompania() == null || articuloDTO.getUsuarioRegistro() == null) {
				throw new ERPException("El c\u00F3digo de compania y el id de usuario registro es requerido");
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
			throw new ERPException("Ocurrio un error al guardar o actualizar el art\u00EDculo."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Ocurrio un error al guardar o actualizar el art\u00EDculo."+e.getMessage());
		} 
	}

}
