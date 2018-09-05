/**
 * 
 */
package ec.com.erp.inventario.dao;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.InventarioDTO;
import ec.com.erp.cliente.mdl.dto.id.InventarioID;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2017-06-27
 */
public class InventarioDAO implements IInventarioDAO {

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
	 * M\u00e9todo para obtener kardex por codigo de barra y fechas
	 * @param codigoCompania
	 * @param codigoBarras
	 * @param fechaFacturaInicio
	 * @param fechaFacturaFin
	 * @return
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<InventarioDTO> obtenerListaInventarioByArticuloFechas(Integer codigoCompania, String codigoBarras, Timestamp fechaFacturaInicio, Timestamp fechaFacturaFin) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(InventarioDTO.class, "root");
			criteria.createAlias("root.articuloDTO", "articuloDTO", CriteriaSpecification.INNER_JOIN);

			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			criteria.add(Restrictions.eq("articuloDTO.codigoBarras", codigoBarras));
			
			if(fechaFacturaInicio != null && fechaFacturaFin != null){
				criteria.add(Restrictions.between("root.fechaMovimiento", fechaFacturaInicio, fechaFacturaFin));
			}
			
			//proyecciones entidad inventario
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoInventario"), "id_codigoInventario");
			projectionList.add(Projections.property("root.fechaMovimiento"), "fechaMovimiento");
			projectionList.add(Projections.property("root.detalleMoviento"), "detalleMoviento");
			projectionList.add(Projections.property("root.cantidadEntrada"), "cantidadEntrada");
			projectionList.add(Projections.property("root.valorUnidadEntrada"), "valorUnidadEntrada");
			projectionList.add(Projections.property("root.valorTotalEntrada"), "valorTotalEntrada");
			projectionList.add(Projections.property("root.cantidadSalida"), "cantidadSalida");
			projectionList.add(Projections.property("root.valorUnidadSalida"), "valorUnidadSalida");
			projectionList.add(Projections.property("root.valorTotalSalida"), "valorTotalSalida");
			projectionList.add(Projections.property("root.cantidadExistencia"), "cantidadExistencia");
			projectionList.add(Projections.property("root.valorUnidadExistencia"), "valorUnidadExistencia");
			projectionList.add(Projections.property("root.valorTotalExistencia"), "valorTotalExistencia");
			projectionList.add(Projections.property("root.esUltimoRegistro"), "esUltimoRegistro");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			// Proyecciones entidad articulo
			projectionList.add(Projections.property("articuloDTO.id.codigoCompania"), "articuloDTO_id_codigoCompania");
			projectionList.add(Projections.property("articuloDTO.id.codigoArticulo"), "articuloDTO_id_codigoArticulo");
			projectionList.add(Projections.property("articuloDTO.codigoBarras"), "articuloDTO_codigoBarras");
			projectionList.add(Projections.property("articuloDTO.nombreArticulo"), "articuloDTO_nombreArticulo");
			projectionList.add(Projections.property("articuloDTO.precio"), "articuloDTO_precio");
			projectionList.add(Projections.property("articuloDTO.peso"), "articuloDTO_peso");
			
			criteria.addOrder(Order.desc("root.fechaMovimiento"));
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(InventarioDTO.class));
			
			return criteria.list();

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de existencias.").initCause(e);
		} 
	}
	
	/**
	 * M\u00e9todo para obtener kardex por codigo de barra
	 * @param codigoCompania
	 * @param codigoBarras
	 * @return
	 * @throws ERPException
	 */
	@Override
	public InventarioDTO obtenerUltimoInventarioByArticulo(Integer codigoCompania, String codigoBarras) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(InventarioDTO.class, "root");
			criteria.createAlias("root.articuloDTO", "articuloDTO", CriteriaSpecification.INNER_JOIN);

			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			criteria.add(Restrictions.eq("root.esUltimoRegistro", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			criteria.add(Restrictions.eq("articuloDTO.codigoBarras", codigoBarras));
						
			//proyecciones entidad inventario
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoInventario"), "id_codigoInventario");
			projectionList.add(Projections.property("root.codigoArticulo"), "codigoArticulo");
			projectionList.add(Projections.property("root.fechaMovimiento"), "fechaMovimiento");
			projectionList.add(Projections.property("root.detalleMoviento"), "detalleMoviento");
			projectionList.add(Projections.property("root.cantidadEntrada"), "cantidadEntrada");
			projectionList.add(Projections.property("root.valorUnidadEntrada"), "valorUnidadEntrada");
			projectionList.add(Projections.property("root.valorTotalEntrada"), "valorTotalEntrada");
			projectionList.add(Projections.property("root.cantidadSalida"), "cantidadSalida");
			projectionList.add(Projections.property("root.valorUnidadSalida"), "valorUnidadSalida");
			projectionList.add(Projections.property("root.valorTotalSalida"), "valorTotalSalida");
			projectionList.add(Projections.property("root.cantidadExistencia"), "cantidadExistencia");
			projectionList.add(Projections.property("root.valorUnidadExistencia"), "valorUnidadExistencia");
			projectionList.add(Projections.property("root.valorTotalExistencia"), "valorTotalExistencia");
			projectionList.add(Projections.property("root.esUltimoRegistro"), "esUltimoRegistro");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
						
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(InventarioDTO.class));

			return (InventarioDTO)criteria.uniqueResult();

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener ultimo movimiento registrado para el item ingresado.").initCause(e);
		} 
	}

	/**
	 * M\u00e9todo para crear o actualizar 
	 * @param inventarioDTO
	 * @throws ERPException
	 */
	@Override
	public void crearActualizarInventario(InventarioDTO inventarioDTO)throws ERPException{
		try{
			sessionFactory.getCurrentSession().clear();
			if(inventarioDTO.getId().getCodigoInventario() ==  null){
				Integer secuencialInvetario = this.secuenciaDAO.obtenerSecuencialTabla(InventarioID.NOMBRE_SECUENCIA);
				inventarioDTO.getId().setCodigoInventario(Long.parseLong(""+secuencialInvetario));
				inventarioDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				inventarioDTO.setFechaRegistro(new Date());
				sessionFactory.getCurrentSession().save(inventarioDTO);
			}
			else
			{
				inventarioDTO.setFechaModificacion(new Date());
				inventarioDTO.setUsuarioModificacion(inventarioDTO.getUsuarioRegistro());
				sessionFactory.getCurrentSession().update(inventarioDTO);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Ocurrio un error al guardar o actualizar el modulo."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Ocurrio un error al guardar o actualizar el modulo."+e.getMessage());
		} 
	}
}