/**
 * 
 */
package ec.com.erp.catalogo.dao;

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
import ec.com.erp.cliente.mdl.dto.CatalogoValorDTO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2017-06-27
 */
public class CatalogoDAO implements ICatalogoDAO {

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
	 * M\u00e9todo para obtener catalogo por tipo
	 * @return 
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<CatalogoValorDTO> obtenerCatalogoByTipo(Integer codigoCatalogoTipo) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(CatalogoValorDTO.class, "root");

			//restricciones
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			criteria.add(Restrictions.eq("root.id.codigoCatalogoTipo", codigoCatalogoTipo));

			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCatalogoValor"), "id_codigoCatalogoValor");
			projectionList.add(Projections.property("root.id.codigoCatalogoTipo"), "id_codigoCatalogoTipo");
			projectionList.add(Projections.property("root.nombreCatalogoValor"), "nombreCatalogoValor");
			projectionList.add(Projections.property("root.estado"), "estado");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(CatalogoValorDTO.class));
			Collection<CatalogoValorDTO> catalogoValorDTOCols = new  ArrayList<CatalogoValorDTO>();
			catalogoValorDTOCols =  criteria.list();

			return catalogoValorDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de catalogos.").initCause(e);
		} 
	}

}
