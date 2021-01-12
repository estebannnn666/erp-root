/**
 * 
 */
package ec.com.erp.transaccion.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
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
import ec.com.erp.cliente.mdl.dto.PagosFacturaDTO;
import ec.com.erp.cliente.mdl.dto.TransaccionDTO;
import ec.com.erp.cliente.mdl.dto.id.PagosFacturaID;
import ec.com.erp.cliente.mdl.dto.id.TransaccionID;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2018-05-05
 */
public class TransaccionDAO implements ITransaccionDAO {

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
	 * M\u00e9todo para obtener lista de transaccciones
	 * @param codigoCompania
	 * @param fechaTransaccionInicio
	 * @param fechaTransaccionFin
	 * @param tipoTransaccion
	 * @return Collection<TransaccionDTO>
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<TransaccionDTO> obtenerListaTransacciones(Integer codigoCompania, Timestamp fechaTransaccionInicio, Timestamp fechaTransaccionFin, String tipoTransaccion) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(TransaccionDTO.class, "root");
			criteria.createAlias("root.tipoTransaccionCatalogoValorDTO", "tipoTransaccionCatalogoValorDTO", CriteriaSpecification.INNER_JOIN);
			
			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			
			if(fechaTransaccionInicio != null && fechaTransaccionFin != null){
				criteria.add(Restrictions.ge("root.fechaTransaccion", fechaTransaccionInicio));
				criteria.add(Restrictions.lt("root.fechaTransaccion", fechaTransaccionFin));
			}
			if(StringUtils.isNotBlank(tipoTransaccion)){
				tipoTransaccion = tipoTransaccion.toUpperCase();
				criteria.add(Restrictions.eq("root.codigoValorTransaccion", tipoTransaccion));
				criteria.add(Restrictions.eq("root.codigoTipoTransaccion", ERPConstantes.CODIGO_CATALOGO_TIPOS_TRANSACCION));
			}

			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoTransaccion"), "id_codigoTransaccion");			
			projectionList.add(Projections.property("root.numeroTransaccion"), "numeroTransaccion");			
			projectionList.add(Projections.property("root.valorTransaccion"), "valorTransaccion");			
			projectionList.add(Projections.property("root.concepto"), "concepto");			
			projectionList.add(Projections.property("root.codigoValorTransaccion"), "codigoValorTransaccion");			
			projectionList.add(Projections.property("root.codigoTipoTransaccion"), "codigoTipoTransaccion");			
			projectionList.add(Projections.property("root.fechaTransaccion"), "fechaTransaccion");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
						
			// Proyecciobes entidad catalogo valor
			projectionList.add(Projections.property("tipoTransaccionCatalogoValorDTO.nombreCatalogoValor"), "tipoTransaccionCatalogoValorDTO_nombreCatalogoValor");
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(TransaccionDTO.class));
			criteria.addOrder(Order.desc("root.id.codigoTransaccion"));
			Collection<TransaccionDTO> transaccionDTOCols = new ArrayList<>();
			transaccionDTOCols = criteria.list();

			return transaccionDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de transacciones.").initCause(e);
		} 
	}
	
	 
	/**
	* M\u00e9todo para guardar transacciones
	* @param transaccionDTO
	* @throws ERPException
	*/
	@Override
    public void guardarTransaccion(TransaccionDTO transaccionDTO) throws ERPException{
		try{
			if (transaccionDTO.getId().getCodigoCompania() == null || transaccionDTO.getUsuarioRegistro() == null) {
				throw new ERPException("Error", "El c\u00F3digo de compania y el id de usuario registro es requerido");
			}	
			
			sessionFactory.getCurrentSession().clear();
			if(transaccionDTO.getId().getCodigoTransaccion() ==  null){
				Integer secuencialTransaccion = this.secuenciaDAO.obtenerSecuencialTabla(TransaccionID.NOMBRE_SECUENCIA);
				transaccionDTO.getId().setCodigoTransaccion(Long.parseLong(""+secuencialTransaccion));
				transaccionDTO.setNumeroTransaccion("TRA"+secuencialTransaccion);
				transaccionDTO.setFechaRegistro(new Date());
				transaccionDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				sessionFactory.getCurrentSession().save(transaccionDTO);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Error", "Ocurrio un error al guardar la transacci\\u00F3n."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Error", "Ocurrio un error al guardar la transacci\\\\u00F3n."+e.getMessage());
		} 
	}

	/**
	 * M\u00e9todo para obtener lista de pagos por factura
	 * @param codigoCompania
	 * @param codigoFactura
	 * @return Collection<PagosFacturaDTO>
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<PagosFacturaDTO> obtenerListaPagosFactura(Integer codigoCompania, Long codigoFactura)	throws ERPException {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(PagosFacturaDTO.class, "root");
			
			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			criteria.add(Restrictions.eq("root.codigoFactura", codigoFactura));
			
			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoPago"), "id_codigoPago");			
			projectionList.add(Projections.property("root.codigoFactura"), "codigoFactura");			
			projectionList.add(Projections.property("root.valorPago"), "valorPago");			
			projectionList.add(Projections.property("root.descripcion"), "descripcion");			
			projectionList.add(Projections.property("root.fechaPago"), "fechaPago");			
			projectionList.add(Projections.property("root.estado"), "estado");			
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
						
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(PagosFacturaDTO.class));
			criteria.addOrder(Order.desc("root.id.codigoPago"));
			Collection<PagosFacturaDTO> pagosDTOCols = new ArrayList<>();
			pagosDTOCols = criteria.list();

			return pagosDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de pagos.").initCause(e);
		} 
	}
	
	/**
	 * M\u00e9todo para obtener total pagos
	 * @param codigoCompania
	 * @param codigoFactura
	 * @return
	 * @throws ERPException
	 */
	@Override
	public BigDecimal obtenerTotalPagos(Integer codigoCompania, Long codigoFactura) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(PagosFacturaDTO.class, "root");

			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.codigoFactura", codigoFactura));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
						
			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.sum("root.valorPago"));
			criteria.setProjection(projectionList);
			BigDecimal resultado = (BigDecimal)criteria.uniqueResult();
			if(resultado == null){
				resultado = BigDecimal.ZERO;
			}
			return resultado;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener ultimo movimiento registrado para el item ingresado.").initCause(e);
		} 
	}

	/**
	 * M\u00e9todo para guardar pagos por factura
	 * @param transaccionDTO
	 * @throws ERPException
	 */
	@Override
	public void guardarPago(PagosFacturaDTO pagosFacturaDTO) throws ERPException {
		try{
			if (pagosFacturaDTO.getId().getCodigoCompania() == null || pagosFacturaDTO.getUsuarioRegistro() == null) {
				throw new ERPException("Error", "El c\u00F3digo de compania y el id de usuario registro es requerido");
			}	
			
			sessionFactory.getCurrentSession().clear();
			if(pagosFacturaDTO.getId().getCodigoPago() ==  null){
				Integer secuencialTransaccion = this.secuenciaDAO.obtenerSecuencialTabla(PagosFacturaID.NOMBRE_SECUENCIA);
				pagosFacturaDTO.getId().setCodigoPago(Long.parseLong(""+secuencialTransaccion));
				pagosFacturaDTO.setFechaRegistro(new Date());
				pagosFacturaDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				sessionFactory.getCurrentSession().save(pagosFacturaDTO);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Error", "Ocurrio un error al guardar el pago."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Error", "Ocurrio un error al guardar el pago."+e.getMessage());
		} 
	}	
}
