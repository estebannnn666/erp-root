/**
 * 
 */
package ec.com.erp.impuesto.dao;

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
import ec.com.erp.cliente.mdl.dto.ImpuestoDTO;
import ec.com.erp.cliente.mdl.dto.id.ImpuestoID;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2017-06-27
 */
public class ImpuestoDAO implements IImpuestoDAO {

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
	 * M\u00e9todo para obtener lista de ImpuestoDTO
	 * @return 
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<ImpuestoDTO> obtenerListaImpuestos(Integer codigoCompania, String nombreImpuesto, String descripcion) throws ERPException{
		try { 
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(ImpuestoDTO.class, "root");

			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			
			if(nombreImpuesto != null && nombreImpuesto !=""){
				nombreImpuesto = nombreImpuesto.toUpperCase();
				criteria.add(Restrictions.eq("root.nombreImpuesto", nombreImpuesto));
			}
			if(descripcion != null && descripcion !=""){
				descripcion = descripcion.toUpperCase();
				criteria.add(Restrictions.like("root.nombreArticulo", descripcion, MatchMode.ANYWHERE));
			}

			// Proyecciones entidad impuesto
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoImpuesto"), "id_codigoImpuesto");
			projectionList.add(Projections.property("root.nombreImpuesto"), "nombreImpuesto");
			projectionList.add(Projections.property("root.descripcion"), "descripcion");
			projectionList.add(Projections.property("root.valorImpuesto"), "valorImpuesto");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(ImpuestoDTO.class));
			Collection<ImpuestoDTO> impuestoDTOCols = new  ArrayList<ImpuestoDTO>();
			impuestoDTOCols =  criteria.list();

			return impuestoDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de impuestos.").initCause(e);
		} 
	}
	
	/**
	 * Metodo para guardar y actualizar impuesto
	 * @param articuloDTO
	 * @throws ERPException
	 */
	@Override
	public void guardarActualizarImpuesto(ImpuestoDTO impuestoDTO) throws ERPException{
		try{
			if (impuestoDTO.getId().getCodigoCompania() == null || impuestoDTO.getUsuarioRegistro() == null) {
				throw new ERPException("Error", "El c\u00F3digo de compania y el id de usuario registro es requerido");
			}	
			
			sessionFactory.getCurrentSession().clear();
			if(impuestoDTO.getId().getCodigoImpuesto() ==  null){
				Integer secuencialArticulo  = this.secuenciaDAO.obtenerSecuencialTabla(ImpuestoID.NOMBRE_SECUENCIA);
				impuestoDTO.getId().setCodigoImpuesto(secuencialArticulo);
				impuestoDTO.setFechaRegistro(new Date());
				impuestoDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				sessionFactory.getCurrentSession().save(impuestoDTO);
			}
			else
			{
				impuestoDTO.setFechaModificacion(new Date());
				impuestoDTO.setUsuarioModificacion(impuestoDTO.getUsuarioRegistro());
				sessionFactory.getCurrentSession().update(impuestoDTO);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar el impuesto."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar el impuesto."+e.getMessage());
		} 
	}

}
