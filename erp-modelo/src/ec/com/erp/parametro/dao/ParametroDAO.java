/**
 * 
 */
package ec.com.erp.parametro.dao;

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
import ec.com.erp.cliente.mdl.dto.ParametroDTO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2017-06-27
 */
public class ParametroDAO implements IParametroDAO {

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
	 * M\u00e9todo para obtener lista de parametros.
	 * @return 
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<ParametroDTO> obtenerParametrosByCodigos(Collection<String> codigosParametros) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(ParametroDTO.class, "root");

			//restricciones
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			criteria.add(Restrictions.in("root.id.codigoParametro", codigosParametros));

			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoParametro"), "id_codigoParametro");
			projectionList.add(Projections.property("root.valorParametro"), "valorParametro");
			projectionList.add(Projections.property("root.descripcionParametro"), "descripcionParametro");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(ParametroDTO.class));
			Collection<ParametroDTO> catalogoValorDTOCols = new  ArrayList<>();
			catalogoValorDTOCols =  criteria.list();
			return catalogoValorDTOCols;
		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de parametros.").initCause(e);
		} 
	}
	
	/**
	 * M\u00e9todo para obtener parametro por codigo.
	 * @return 
	 * @throws ERPException
	 */
	@Override
	public ParametroDTO obtenerParametroByCodigo(String codigoParametro) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(ParametroDTO.class, "root");

			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", 1));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			criteria.add(Restrictions.eq("root.id.codigoParametro", codigoParametro));

			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoParametro"), "id_codigoParametro");
			projectionList.add(Projections.property("root.valorParametro"), "valorParametro");
			projectionList.add(Projections.property("root.descripcionParametro"), "descripcionParametro");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(ParametroDTO.class));
			return (ParametroDTO)criteria.uniqueResult();
		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de parametros.").initCause(e);
		} 
	}

}
