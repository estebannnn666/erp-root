/**
 * 
 */
package ec.com.erp.perfiles.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.PerfilDTO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2017-06-27
 */
public class PerfilesDAO implements IPerfilesDAO{

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
	 * M\u00e9todo para obtener lista de perfiles
	 * @return 
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<PerfilDTO> obtenerListaPerfiles() throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(PerfilDTO.class, "root");
			criteria.createAlias("root.perfilCatalogoValorDTO", "perfilCatalogoValorDTO", CriteriaSpecification.INNER_JOIN);
			
			//restricciones
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			
			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoPerfil"), "id_codigoPerfil");
			projectionList.add(Projections.property("root.nombrePerfil"), "nombrePerfil");
			projectionList.add(Projections.property("root.descripcion"), "descripcion");
			projectionList.add(Projections.property("root.codigoValorTipoPerfil"), "codigoValorTipoPerfil");
			projectionList.add(Projections.property("root.codigoTipoPerfil"), "codigoTipoPerfil");
			projectionList.add(Projections.property("root.estado"), "estado");
			
			// Proyecciones catalogos
			projectionList.add(Projections.property("perfilCatalogoValorDTO.nombreCatalogoValor"), "perfilCatalogoValorDTO_nombreCatalogoValor");
						
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(PerfilDTO.class));
			Collection<PerfilDTO> perfilDTOCols = new  ArrayList<PerfilDTO>();
			perfilDTOCols =  criteria.list();

			return perfilDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de convenios con diseniadores.").initCause(e);
		} 
	}

}
