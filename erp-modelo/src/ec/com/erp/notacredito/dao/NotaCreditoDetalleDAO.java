/**
 * 
 */
package ec.com.erp.notacredito.dao;

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
import ec.com.erp.cliente.mdl.dto.NotaCreditoDetalleDTO;
import ec.com.erp.cliente.mdl.dto.id.NotaCreditoDetalleID;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2017-06-27
 */
public class NotaCreditoDetalleDAO implements INotaCreditoDetalleDAO {

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
	 * M\u00e9todo para obtener lista de detalles por nota de credito
	 * @param codigoCompania
	 * @param numeroDocumento
	 * @return Collection<NotaCreditoDetalleDTO>
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<NotaCreditoDetalleDTO> obtenerListaDetalleNotaCreditoByNumeroDocumento(Integer codigoCompania, String numeroDocumento) throws ERPException {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(NotaCreditoDetalleDTO.class, "root");
			criteria.createAlias("root.notaCreditoDTO", "notaCreditoDTO", CriteriaSpecification.INNER_JOIN);

			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			if(numeroDocumento != null && numeroDocumento !=""){
				numeroDocumento = numeroDocumento.toUpperCase();
				criteria.add(Restrictions.eq("notaCreditoDTO.numeroDocumento", numeroDocumento));
			}

			// Proyecciones entidad detalle factura
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoDetalleNotaCredito"), "id_codigoDetalleNotaCredito");
			projectionList.add(Projections.property("root.codigoArticulo"), "codigoArticulo");
			projectionList.add(Projections.property("root.codigoArticuloUnidadManejo"), "codigoArticuloUnidadManejo");
			projectionList.add(Projections.property("root.cantidad"), "cantidad");
			projectionList.add(Projections.property("root.codigoNotaCredito"), "codigoNotaCredito");
			projectionList.add(Projections.property("root.descripcion"), "descripcion");
			projectionList.add(Projections.property("root.valorUnidad"), "valorUnidad");
			projectionList.add(Projections.property("root.subTotal"), "subTotal");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(NotaCreditoDetalleDTO.class));
			Collection<NotaCreditoDetalleDTO> facturaDetalleDTOCols = criteria.list();
			return facturaDetalleDTOCols;
		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de detalle nota de credito.").initCause(e);
		} 
	}

	/**
	 * M\u00e9todo para guardar y actualizar detalle de nota de credito
	 * @param notaCreditoDetalleDTO
	 * @throws ERPException
	 */
	@Override
	public void guardarActualizarDetalleNotaCredito(NotaCreditoDetalleDTO notaCreditoDetalleDTO) throws ERPException {
		try{
			if (notaCreditoDetalleDTO.getId().getCodigoCompania() == null || notaCreditoDetalleDTO.getUsuarioRegistro() == null) {
				throw new ERPException("Error", "El c\u00F3digo de compania y el id de usuario registro es requerido");
			}	
			
			sessionFactory.getCurrentSession().clear();
			if(notaCreditoDetalleDTO.getId().getCodigoDetalleNotaCredito() ==  null){
				Integer secuencialDetalleNotaCredito  = this.secuenciaDAO.obtenerSecuencialTabla(NotaCreditoDetalleID.NOMBRE_SECUENCIA);
				notaCreditoDetalleDTO.getId().setCodigoDetalleNotaCredito(Long.parseLong(""+secuencialDetalleNotaCredito));
				notaCreditoDetalleDTO.setFechaRegistro(new Date());
				notaCreditoDetalleDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				sessionFactory.getCurrentSession().save(notaCreditoDetalleDTO);
			}
			else
			{
				notaCreditoDetalleDTO.setFechaModificacion(new Date());
				notaCreditoDetalleDTO.setUsuarioModificacion(notaCreditoDetalleDTO.getUsuarioRegistro());
				sessionFactory.getCurrentSession().update(notaCreditoDetalleDTO);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar el detalle nota de credito."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar el detalle nota de credito."+e.getMessage());
		} 
	}

}
