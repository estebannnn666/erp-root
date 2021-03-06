/**
 * 
 */
package ec.com.erp.compania.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.CompaniaDTO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2017-06-27
 */
public class CompaniaDAO implements ICompaniaDAO {

	/**
	 * SessionFactory sessionFactory.
	 */
	private SessionFactory sessionFactory;

	/**
	 *  M\u00E9todo que asigna el valor de sessionFactory del objeto.
	 * @param sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	


	/**
	 * M\u00e9todo para obtener lista de companias
	 * @return 
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<CompaniaDTO> obtenerListaCompanias() throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(CompaniaDTO.class, "root");

			//restricciones
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));

			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.nombreCompania"), "codigoBarras");
			projectionList.add(Projections.property("root.descripcionCompania"), "nombreArticulo");
			projectionList.add(Projections.property("root.estado"), "estado");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(CompaniaDTO.class));
			Collection<CompaniaDTO> companiaDTOCols = new  ArrayList<CompaniaDTO>();
			companiaDTOCols =  criteria.list();

			return companiaDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de companias.").initCause(e);
		} 
	}
	
	/**
	 * M\u00e9todo para obtener la compania por codigo enviado como parametro
	 * @param codigoCompania
	 * @return
	 * @throws ERPException
	 */
	public CompaniaDTO obtenerListaCompaniasByCodigo(Integer codigoCompania) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(CompaniaDTO.class, "root");

			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));

			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.nombreCompania"), "codigoBarras");
			projectionList.add(Projections.property("root.descripcionCompania"), "nombreArticulo");
			projectionList.add(Projections.property("root.estado"), "estado");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(CompaniaDTO.class));
			CompaniaDTO companiaDTO = (CompaniaDTO)criteria.uniqueResult();

			return companiaDTO;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de companias.").initCause(e);
		} 
	}

}
