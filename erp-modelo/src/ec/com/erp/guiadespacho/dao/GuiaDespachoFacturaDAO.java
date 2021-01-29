/**
 * 
 */
package ec.com.erp.guiadespacho.dao;

import java.util.ArrayList;
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
import ec.com.erp.cliente.mdl.dto.GuiaDespachoFacturaDTO;
import ec.com.erp.cliente.mdl.dto.id.GuiaDespachoFacturaID;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2018-05-05
 */
public class GuiaDespachoFacturaDAO implements IGuiaDespachoFacturaDAO {

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
	 * M\u00e9todo para obtener lista de facturas por guia de despacho
	 * @param codigoCompania
	 * @param numeroGuia
	 * @return
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<GuiaDespachoFacturaDTO> obtenerListaGuiaDespachoFacturasByNumeroGuiaDespacho(Integer codigoCompania, String numeroGuia) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(GuiaDespachoFacturaDTO.class, "root");
			criteria.createAlias("root.guiaDespachoDTO", "guiaDespachoDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("root.facturaCabeceraDTO", "facturaCabeceraDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("facturaCabeceraDTO.facturaDetalleDTOCols", "facturaDetalleDTOCols", CriteriaSpecification.INNER_JOIN);
			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			criteria.add(Restrictions.eq("facturaCabeceraDTO.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			criteria.add(Restrictions.eq("facturaDetalleDTOCols.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			criteria.add(Restrictions.eq("guiaDespachoDTO.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));

			if(numeroGuia != null) {
				criteria.add(Restrictions.eq("guiaDespachoDTO.numeroGuiaDespacho", numeroGuia));
			}
			
			// Proyecciones entidad root 
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoGuiaDespachoFactura"), "id_codigoGuiaDespachoFactura");
			projectionList.add(Projections.property("root.codigoGuiaDespacho"), "codigoGuiaDespacho");
			projectionList.add(Projections.property("root.codigoFactura"), "codigoFactura");
			projectionList.add(Projections.property("root.orden"), "orden");
			projectionList.add(Projections.property("root.observacion"), "observacion");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			projectionList.add(Projections.property("facturaCabeceraDTO.id.codigoCompania"), "facturaCabeceraDTO_id_codigoCompania");
			projectionList.add(Projections.property("facturaCabeceraDTO.id.codigoFactura"), "facturaCabeceraDTO_id_codigoFactura");
			projectionList.add(Projections.property("facturaCabeceraDTO.codigoReferenciaFactura"), "facturaCabeceraDTO_codigoReferenciaFactura");
			projectionList.add(Projections.property("facturaCabeceraDTO.codigoPedido"), "facturaCabeceraDTO_codigoPedido");
			projectionList.add(Projections.property("facturaCabeceraDTO.codigoVendedor"), "facturaCabeceraDTO_codigoVendedor");
			projectionList.add(Projections.property("facturaCabeceraDTO.numeroDocumento"), "facturaCabeceraDTO_numeroDocumento");
			projectionList.add(Projections.property("facturaCabeceraDTO.fechaDocumento"), "facturaCabeceraDTO_fechaDocumento");
			projectionList.add(Projections.property("facturaCabeceraDTO.rucDocumento"), "facturaCabeceraDTO_rucDocumento");
			projectionList.add(Projections.property("facturaCabeceraDTO.nombreClienteProveedor"), "facturaCabeceraDTO_nombreClienteProveedor");
			projectionList.add(Projections.property("facturaCabeceraDTO.direccion"), "facturaCabeceraDTO_direccion");
			projectionList.add(Projections.property("facturaCabeceraDTO.telefono"), "facturaCabeceraDTO_telefono");
			projectionList.add(Projections.property("facturaCabeceraDTO.pagado"), "facturaCabeceraDTO_pagado");
			projectionList.add(Projections.property("facturaCabeceraDTO.subTotal"), "facturaCabeceraDTO_subTotal");
			projectionList.add(Projections.property("facturaCabeceraDTO.totalIva"), "facturaCabeceraDTO_totalIva");
			projectionList.add(Projections.property("facturaCabeceraDTO.tipoCliente"), "facturaCabeceraDTO_tipoCliente");
			projectionList.add(Projections.property("facturaCabeceraDTO.totalSinImpuestos"), "facturaCabeceraDTO_totalSinImpuestos");
			projectionList.add(Projections.property("facturaCabeceraDTO.totalImpuestos"), "facturaCabeceraDTO_totalImpuestos");
			projectionList.add(Projections.property("facturaCabeceraDTO.totalCuenta"), "facturaCabeceraDTO_totalCuenta");
			projectionList.add(Projections.property("facturaCabeceraDTO.descuento"), "facturaCabeceraDTO_descuento");
			projectionList.add(Projections.property("facturaCabeceraDTO.codigoTipoDocumento"), "facturaCabeceraDTO_codigoTipoDocumento");
			projectionList.add(Projections.property("facturaCabeceraDTO.codigoValorTipoDocumento"), "facturaCabeceraDTO_codigoValorTipoDocumento");
			projectionList.add(Projections.property("facturaCabeceraDTO.estado"), "facturaCabeceraDTO_estado");
			projectionList.add(Projections.property("facturaCabeceraDTO.usuarioRegistro"), "facturaCabeceraDTO_usuarioRegistro");
			projectionList.add(Projections.property("facturaCabeceraDTO.fechaRegistro"), "facturaCabeceraDTO_fechaRegistro");
			
			// Proyecciones entidad detalle pedido
			projectionList.add(Projections.property("facturaDetalleDTOCols.id.codigoCompania"), "facturaCabeceraDTO_facturaDetalleDTOCols_id_codigoCompania");
			projectionList.add(Projections.property("facturaDetalleDTOCols.id.codigoDetalleFactura"), "facturaCabeceraDTO_facturaDetalleDTOCols_id_codigoDetalleFactura");
			projectionList.add(Projections.property("facturaDetalleDTOCols.codigoArticulo"), "facturaCabeceraDTO_facturaDetalleDTOCols_codigoArticulo");
			projectionList.add(Projections.property("facturaDetalleDTOCols.codigoArticuloUnidadManejo"), "facturaCabeceraDTO_facturaDetalleDTOCols_codigoArticuloUnidadManejo");
			projectionList.add(Projections.property("facturaDetalleDTOCols.cantidad"), "facturaCabeceraDTO_facturaDetalleDTOCols_cantidad");
			projectionList.add(Projections.property("facturaDetalleDTOCols.codigoFactura"), "facturaCabeceraDTO_facturaDetalleDTOCols_codigoFactura");
			projectionList.add(Projections.property("facturaDetalleDTOCols.descripcion"), "facturaCabeceraDTO_facturaDetalleDTOCols_descripcion");
			projectionList.add(Projections.property("facturaDetalleDTOCols.valorUnidad"), "facturaCabeceraDTO_facturaDetalleDTOCols_valorUnidad");
			projectionList.add(Projections.property("facturaDetalleDTOCols.subTotal"), "facturaCabeceraDTO_facturaDetalleDTOCols_subTotal");
			projectionList.add(Projections.property("facturaDetalleDTOCols.estado"), "facturaCabeceraDTO_facturaDetalleDTOCols_estado");
			projectionList.add(Projections.property("facturaDetalleDTOCols.usuarioRegistro"), "facturaCabeceraDTO_facturaDetalleDTOCols_usuarioRegistro");
			projectionList.add(Projections.property("facturaDetalleDTOCols.fechaRegistro"), "facturaCabeceraDTO_facturaDetalleDTOCols_fechaRegistro");
						
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(GuiaDespachoFacturaDTO.class));
			Collection<GuiaDespachoFacturaDTO> guiaDespachoPedidoDTOCols = new  ArrayList<GuiaDespachoFacturaDTO>();
			guiaDespachoPedidoDTOCols =  criteria.list();

			return guiaDespachoPedidoDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de facturas por guia de despacho.").initCause(e);
		} 
	}
	
	/**
	 * M\u00e9todo para guardar y actualizar facturas guia despacho
	 * @param guiaDespachoFacturaDTO
	 * @throws ERPException
	 */
	@Override
	public void crearActualizarGuiaDespachoFacturas(GuiaDespachoFacturaDTO guiaDespachoFacturaDTO) throws ERPException{
		try{
			if (guiaDespachoFacturaDTO.getId().getCodigoCompania() == null || guiaDespachoFacturaDTO.getUsuarioRegistro() == null) {
				throw new ERPException("Error", "El c\u00F3digo de compania y el id de usuario registro es requerido");
			}	
			
			sessionFactory.getCurrentSession().clear();
			if(guiaDespachoFacturaDTO.getId().getCodigoGuiaDespachoFactura() ==  null){
				Integer secuencialGuiaDespachoFactura  = this.secuenciaDAO.obtenerSecuencialTabla(GuiaDespachoFacturaID.NOMBRE_SECUENCIA);
				guiaDespachoFacturaDTO.getId().setCodigoGuiaDespachoFactura(Long.parseLong(""+secuencialGuiaDespachoFactura));
				guiaDespachoFacturaDTO.setFechaRegistro(new Date());
				guiaDespachoFacturaDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				sessionFactory.getCurrentSession().save(guiaDespachoFacturaDTO);
			}else{
				guiaDespachoFacturaDTO.setFechaModificacion(new Date());
				guiaDespachoFacturaDTO.setUsuarioModificacion(guiaDespachoFacturaDTO.getUsuarioRegistro());
				sessionFactory.getCurrentSession().update(guiaDespachoFacturaDTO);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar factura guia despacho."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar factura guia despacho."+e.getMessage());
		} 
	}

}
