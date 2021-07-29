/**
 * 
 */
package ec.com.erp.factura.dao;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.FacturaDocumentoDTO;
import ec.com.erp.cliente.mdl.dto.id.FacturaDocumentoID;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2017-06-27
 */
public class FacturaDocumentoDAO implements IFacturaDocumentoDAO {

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
	 * M\u00e9todo para obtener xml factura
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @return FacturaDocumentoDTO
	 * @throws ERPException
	 */
	@Override
	public FacturaDocumentoDTO obtenerXmlDocumentoFactura(Integer codigoCompania, Long codigoFactura) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(FacturaDocumentoDTO.class, "root");

			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			criteria.add(Restrictions.eq("root.codigoFactura", codigoFactura));

			// Proyecciones entidad detalle factura
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoDocumento"), "id_codigoDocumento");
			projectionList.add(Projections.property("root.xmlFactura"), "xmlFactura");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(FacturaDocumentoDTO.class));
			FacturaDocumentoDTO facturaDocumentoDTO = (FacturaDocumentoDTO)criteria.uniqueResult();
			return facturaDocumentoDTO;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener documento de factura.").initCause(e);
		} 
	}
	
	/**
	 * M\u00e9todo para guardar y actualizar xml documento
	 * @param facturaDocumentoDTO
	 * @throws ERPException
	 */
	@Override
	public void guardarActualizarDocumentoFactura(FacturaDocumentoDTO facturaDocumentoDTO) throws ERPException{
		try{
			if (facturaDocumentoDTO.getId().getCodigoCompania() == null || facturaDocumentoDTO.getUsuarioRegistro() == null) {
				throw new ERPException("Error", "El c\u00F3digo de compania y el id de usuario registro es requerido");
			}	
			
			sessionFactory.getCurrentSession().clear();
			if(facturaDocumentoDTO.getId().getCodigoDocumento() ==  null){
				Integer secuencialDocumentoFactura  = this.secuenciaDAO.obtenerSecuencialTabla(FacturaDocumentoID.NOMBRE_SECUENCIA);
				facturaDocumentoDTO.getId().setCodigoDocumento(Long.parseLong(""+secuencialDocumentoFactura));
				facturaDocumentoDTO.setFechaRegistro(new Date());
				facturaDocumentoDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				sessionFactory.getCurrentSession().save(facturaDocumentoDTO);
			}
			else
			{
				facturaDocumentoDTO.setFechaModificacion(new Date());
				facturaDocumentoDTO.setUsuarioModificacion(facturaDocumentoDTO.getUsuarioRegistro());
				sessionFactory.getCurrentSession().update(facturaDocumentoDTO);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar el documento de factura."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar el documento de factura."+e.getMessage());
		} 
	}

}
