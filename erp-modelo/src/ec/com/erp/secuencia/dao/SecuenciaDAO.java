/**
 * 
 */
package ec.com.erp.secuencia.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.SecuenciaDTO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2017-06-27
 */
public class SecuenciaDAO implements ISecuenciaDAO {

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
	 * M\u00e9todo para obtener secuencia por nombre
	 * @param nombreSecuencia
	 * @return
	 * @throws ERPException
	 */
	@Override
	public SecuenciaDTO obtenerSecuenciaByNombre(String nombreSecuencia) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(SecuenciaDTO.class, "root");

			//restricciones
			criteria.add(Restrictions.eq("root.id.nombreSecuencia", nombreSecuencia));

			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.nombreSecuencia"), "id_nombreSecuencia");
			projectionList.add(Projections.property("root.valorSecuencia"), "valorSecuencia");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(SecuenciaDTO.class));
			SecuenciaDTO secuenciaDTO = (SecuenciaDTO)criteria.uniqueResult();

			return secuenciaDTO;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de convenios con diseniadores.").initCause(e);
		} 
	}
	
	/**
	 * Metodo para actualizar el valor de la secuencia
	 * @param articuloDTO
	 * @throws ERPException
	 */
	public void guardarActualizarSecuencia(SecuenciaDTO secuenciaDTO) throws ERPException{
		try{
			sessionFactory.getCurrentSession().clear();
			secuenciaDTO.setValorSecuencia(secuenciaDTO.getValorSecuencia()+1);
			sessionFactory.getCurrentSession().update(secuenciaDTO);
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Ocurrio un error al actualizar el valor de la secuencia."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Ocurrio un error al actualizar el valor de la secuencia."+e.getMessage());
		} 
	}
	
	/**
	 * Metodo para obtenere el secuencial para cualquier tabla
	 * @param nombreSecuencia
	 * @return
	 * @throws ERPException
	 */
	public Integer obtenerSecuencialTabla(String nombreSecuencia) throws ERPException{
		try{
			SecuenciaDTO secuenciaDTO = this.obtenerSecuenciaByNombre(nombreSecuencia);
			Integer secuacialTabla = secuenciaDTO.getValorSecuencia();
			this.guardarActualizarSecuencia(secuenciaDTO);
			return secuacialTabla;
		} catch (ERPException e) {
			throw new ERPException("Ocurrio un error al actualizar el valor de la secuencia."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Ocurrio un error al actualizar el valor de la secuencia."+e.getMessage());
		} 
	}

}
