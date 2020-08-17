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
import ec.com.erp.cliente.mdl.dto.GuiaDespachoExtrasDTO;
import ec.com.erp.cliente.mdl.dto.id.GuiaDespachoExtrasID;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2018-05-05
 */
public class GuiaDespachoExtrasDAO implements IGuiaDespachoExtrasDAO {

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
	 * M\u00e9todo para obtener lista de extras en guia de despacho
	 * @param codigoCompania
	 * @param numeroGuia
	 * @return Collection<GuiaDespachoExtrasDTO>
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<GuiaDespachoExtrasDTO> obtenerListaGuiaDespachoExtrasByNumeroGuia(Integer codigoCompania, String numeroGuia) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(GuiaDespachoExtrasDTO.class, "root");
			criteria.createAlias("root.guiaDespachoDTO", "guiaDespachoDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("root.articuloDTO", "articuloDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("articuloDTO.articuloUnidadManejoDTOCols", "articuloUnidadManejoDTOCols", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("articuloUnidadManejoDTOCols.tipoUnidadManejoCatalogoValorDTO", "unidadManejoCatalogoValorDTO", CriteriaSpecification.LEFT_JOIN);
			
			criteria.createAlias("root.articuloUnidadManejoDTO", "articuloUnidadManejoDTO", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("articuloUnidadManejoDTO.tipoUnidadManejoCatalogoValorDTO", "tipoUnidadManejoCatalogoValorDTO", CriteriaSpecification.LEFT_JOIN);
			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			criteria.add(Restrictions.eq("guiaDespachoDTO.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));

			if(numeroGuia != null) {
				criteria.add(Restrictions.eq("guiaDespachoDTO.numeroGuiaDespacho", numeroGuia));
			}
			
			// Proyecciones entidad clientes 
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoGuiaDespachoExtra"), "id_codigoGuiaDespachoExtra");
			projectionList.add(Projections.property("root.codigoGuiaDespacho"), "codigoGuiaDespacho");
			projectionList.add(Projections.property("root.codigoArticulo"), "codigoArticulo");
			projectionList.add(Projections.property("root.codigoArticuloUnidadManejo"), "codigoArticuloUnidadManejo");
			projectionList.add(Projections.property("root.descripcionProducto"), "descripcionProducto");
			projectionList.add(Projections.property("root.cantidad"), "cantidad");
			projectionList.add(Projections.property("root.observacion"), "observacion");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			// Proyecciones entidad articulo
			projectionList.add(Projections.property("articuloDTO.id.codigoCompania"), "articuloDTO_id_codigoCompania");
			projectionList.add(Projections.property("articuloDTO.id.codigoArticulo"), "articuloDTO_id_codigoArticulo");
			projectionList.add(Projections.property("articuloDTO.codigoBarras"), "articuloDTO_codigoBarras");
			projectionList.add(Projections.property("articuloDTO.nombreArticulo"), "articuloDTO_nombreArticulo");
			projectionList.add(Projections.property("articuloDTO.precio"), "articuloDTO_precio");
			projectionList.add(Projections.property("articuloDTO.precioMinorista"), "articuloDTO_precioMinorista");
			projectionList.add(Projections.property("articuloDTO.peso"), "articuloDTO_peso");
						
			projectionList.add(Projections.property("articuloUnidadManejoDTOCols.id.codigoCompania"), "articuloDTO_articuloUnidadManejoDTOCols_id_codigoCompania");
			projectionList.add(Projections.property("articuloUnidadManejoDTOCols.id.codigoArticulo"), "articuloDTO_articuloUnidadManejoDTOCols_id_codigoArticulo");
			projectionList.add(Projections.property("articuloUnidadManejoDTOCols.id.codigoArticuloUnidadManejo"), "articuloDTO_articuloUnidadManejoDTOCols_id_codigoArticuloUnidadManejo");
			projectionList.add(Projections.property("articuloUnidadManejoDTOCols.valorUnidadManejo"), "articuloDTO_articuloUnidadManejoDTOCols_valorUnidadManejo");
			projectionList.add(Projections.property("articuloUnidadManejoDTOCols.esPorDefecto"), "articuloDTO_articuloUnidadManejoDTOCols_esPorDefecto");
			
			// Proyecciones catalogos
			projectionList.add(Projections.property("tipoUnidadManejoCatalogoValorDTO.nombreCatalogoValor"), "articuloDTO_articuloUnidadManejoDTOCols_tipoUnidadManejoCatalogoValorDTO_nombreCatalogoValor");
			
			projectionList.add(Projections.property("articuloUnidadManejoDTO.id.codigoCompania"), "articuloUnidadManejoDTO_id_codigoCompania");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.id.codigoArticulo"), "articuloUnidadManejoDTO_id_codigoArticulo");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.id.codigoArticuloUnidadManejo"), "articuloUnidadManejoDTO_id_codigoArticuloUnidadManejo");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.valorUnidadManejo"), "articuloUnidadManejoDTO_valorUnidadManejo");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.codigoValorUnidadManejo"), "articuloUnidadManejoDTO_codigoValorUnidadManejo");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.codigoTipoUnidadManejo"), "articuloUnidadManejoDTO_codigoTipoUnidadManejo");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.esPorDefecto"), "articuloUnidadManejoDTO_esPorDefecto");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.estado"), "articuloUnidadManejoDTO_estado");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.usuarioRegistro"), "articuloUnidadManejoDTO_usuarioRegistro");
			projectionList.add(Projections.property("articuloUnidadManejoDTO.fechaRegistro"), "articuloUnidadManejoDTO_fechaRegistro");
			
			// Proyecciones catalogos
			projectionList.add(Projections.property("tipoUnidadManejoCatalogoValorDTO.nombreCatalogoValor"), "articuloUnidadManejoDTO_tipoUnidadManejoCatalogoValorDTO_nombreCatalogoValor");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(GuiaDespachoExtrasDTO.class));
			Collection<GuiaDespachoExtrasDTO> guiaDespachoExtrasDTOCols = new  ArrayList<GuiaDespachoExtrasDTO>();
			guiaDespachoExtrasDTOCols =  criteria.list();

			return guiaDespachoExtrasDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de extras guia despacho.").initCause(e);
		} 
	}
	
	/**
	 * M\u00e9todo para guardar y actualizar extras guia despacho
	 * @param guiaDespachoExtrasDTO
	 * @throws ERPException
	 */
	@Override
	public void crearActualizarExtrasGuiaDespacho(GuiaDespachoExtrasDTO guiaDespachoExtrasDTO) throws ERPException{
		try{
			if (guiaDespachoExtrasDTO.getId().getCodigoCompania() == null || guiaDespachoExtrasDTO.getUsuarioRegistro() == null) {
				throw new ERPException("El c\u00F3digo de compania y el id de usuario registro es requerido");
			}	
			
			sessionFactory.getCurrentSession().clear();
			if(guiaDespachoExtrasDTO.getId().getCodigoGuiaDespachoExtra() ==  null){
				Integer secuencialGuiaDespachoExtra  = this.secuenciaDAO.obtenerSecuencialTabla(GuiaDespachoExtrasID.NOMBRE_SECUENCIA);
				guiaDespachoExtrasDTO.getId().setCodigoGuiaDespachoExtra(Long.parseLong(""+secuencialGuiaDespachoExtra));
				guiaDespachoExtrasDTO.setFechaRegistro(new Date());
				guiaDespachoExtrasDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				sessionFactory.getCurrentSession().save(guiaDespachoExtrasDTO);
			}
			else
			{
				guiaDespachoExtrasDTO.setFechaModificacion(new Date());
				guiaDespachoExtrasDTO.setUsuarioModificacion(guiaDespachoExtrasDTO.getUsuarioRegistro());
				sessionFactory.getCurrentSession().update(guiaDespachoExtrasDTO);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Ocurrio un error al guardar o actualizar art\u00EDculos extras en la gu\u00EDa de despacho."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Ocurrio un error al guardar o actualizar art\u00EDculos extras en la gu\u00EDa de despacho."+e.getMessage());
		} 
	}

}
