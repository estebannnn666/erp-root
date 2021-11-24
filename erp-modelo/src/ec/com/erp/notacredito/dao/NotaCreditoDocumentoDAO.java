/**
 * 
 */
package ec.com.erp.notacredito.dao;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.NotaCreditoDocumentoDTO;
import ec.com.erp.cliente.mdl.dto.id.NotaCreditoDocumentoID;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2021-11-26
 */
public class NotaCreditoDocumentoDAO implements INotaCreditoDocumentoDAO {

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
	 * M\u00e9todo para obtener xml de nota de credito
	 * @param codigoCompania
	 * @param codigoNotaCredito
	 * @return NotaCreditoDocumentoDTO
	 * @throws ERPException
	 */
	@Override
	public NotaCreditoDocumentoDTO obtenerXmlDocumentoNotaCredito(Integer codigoCompania, Long codigoNotaCredito)
			throws ERPException {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(NotaCreditoDocumentoDTO.class, "root");

			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			criteria.add(Restrictions.eq("root.codigoNotaCredito", codigoNotaCredito));

			// Proyecciones entidad detalle factura
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoDocumento"), "id_codigoDocumento");
			projectionList.add(Projections.property("root.codigoNotaCredito"), "codigoNotaCredito");
			projectionList.add(Projections.property("root.xmlNotaCredito"), "xmlNotaCredito");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(NotaCreditoDocumentoDTO.class));
			NotaCreditoDocumentoDTO notaCreditoDocumentoDTO = (NotaCreditoDocumentoDTO)criteria.uniqueResult();
			return notaCreditoDocumentoDTO;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener documento nota de credito.").initCause(e);
		} 
	}

	/**
	 * M\u00e9todo para guardar y actualizar xml documento
	 * @param notaCreditoDocumentoDTO
	 * @throws ERPException
	 */
	@Override
	public void guardarActualizarDocumentoNotaCredito(NotaCreditoDocumentoDTO notaCreditoDocumentoDTO) throws ERPException {
		try{
			if (notaCreditoDocumentoDTO.getId().getCodigoCompania() == null || notaCreditoDocumentoDTO.getUsuarioRegistro() == null) {
				throw new ERPException("Error", "El c\u00F3digo de compania y el id de usuario registro es requerido");
			}	
			
			sessionFactory.getCurrentSession().clear();
			if(notaCreditoDocumentoDTO.getId().getCodigoDocumento() ==  null){
				Integer secuencialDocumentoFactura  = this.secuenciaDAO.obtenerSecuencialTabla(NotaCreditoDocumentoID.NOMBRE_SECUENCIA);
				notaCreditoDocumentoDTO.getId().setCodigoDocumento(Long.parseLong(""+secuencialDocumentoFactura));
				notaCreditoDocumentoDTO.setFechaRegistro(new Date());
				notaCreditoDocumentoDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				sessionFactory.getCurrentSession().save(notaCreditoDocumentoDTO);
			}else{
				notaCreditoDocumentoDTO.setFechaModificacion(new Date());
				notaCreditoDocumentoDTO.setUsuarioModificacion(notaCreditoDocumentoDTO.getUsuarioRegistro());
				sessionFactory.getCurrentSession().update(notaCreditoDocumentoDTO);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar el documento de la nota de credito."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar el documento de la nota de credito."+e.getMessage());
		} 
		
	}

}
