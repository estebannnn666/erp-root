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
import ec.com.erp.cliente.mdl.dto.GuiaDespachoPedidoDTO;
import ec.com.erp.cliente.mdl.dto.id.GuiaDespachoPedidoID;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2018-05-05
 */
public class GuiaDespachoPedidoDAO implements IGuiaDespachoPedidoDAO {

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
	 * M\u00e9todo para obtener lista de pedidos por guia de despacho
	 * @param codigoCompania
	 * @param numeroGuia
	 * @return
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<GuiaDespachoPedidoDTO> obtenerListaGuiaDespachoPedidosByNumeroGuiaDespacho(Integer codigoCompania, String numeroGuia) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(GuiaDespachoPedidoDTO.class, "root");
			criteria.createAlias("root.guiaDespachoDTO", "guiaDespachoDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("root.pedidoDTO", "pedidoDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("pedidoDTO.clienteDTO", "clienteDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("clienteDTO.tipoClienteCatalogoValorDTO", "tipoClienteCatalogoValorDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("clienteDTO.personaDTO", "personaDTO", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("personaDTO.contactoDTOCols", "contactoPersonaDTO", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("clienteDTO.empresaDTO", "empresaDTO", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("empresaDTO.contactoDTOCols", "contactoEmpresaDTO", CriteriaSpecification.LEFT_JOIN);
			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));

			if(numeroGuia != null) {
				criteria.add(Restrictions.eq("guiaDespachoDTO.numeroGuiaDespacho", numeroGuia));
			}
			
			// Proyecciones entidad clientes 
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoGuiaDespachoPedido"), "id_codigoGuiaDespachoPedido");
			projectionList.add(Projections.property("root.codigoGuiaDespacho"), "codigoGuiaDespacho");
			projectionList.add(Projections.property("root.codigoPedido"), "codigoPedido");
			projectionList.add(Projections.property("root.orden"), "orden");
			projectionList.add(Projections.property("root.observacion"), "observacion");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			projectionList.add(Projections.property("pedidoDTO.id.codigoCompania"), "pedidoDTO_id_codigoCompania");
			projectionList.add(Projections.property("pedidoDTO.id.codigoPedido"), "pedidoDTO_id_codigoPedido");
			projectionList.add(Projections.property("pedidoDTO.numeroPedido"), "pedidoDTO_numeroPedido");
			projectionList.add(Projections.property("pedidoDTO.codigoCliente"), "pedidoDTO_codigoCliente");
			projectionList.add(Projections.property("pedidoDTO.fechaPedido"), "pedidoDTO_fechaPedido");
			projectionList.add(Projections.property("pedidoDTO.fechaEntrega"), "pedidoDTO_fechaEntrega");
			projectionList.add(Projections.property("pedidoDTO.totalCompra"), "pedidoDTO_totalCompra");
			projectionList.add(Projections.property("pedidoDTO.estado"), "pedidoDTO_estado");
			projectionList.add(Projections.property("pedidoDTO.usuarioRegistro"), "pedidoDTO_usuarioRegistro");
			projectionList.add(Projections.property("pedidoDTO.fechaRegistro"), "pedidoDTO_fechaRegistro");
			
			// Proyecciones entidad clientes 
			projectionList.add(Projections.property("clienteDTO.id.codigoCompania"), "pedidoDTO_clienteDTO_id_codigoCompania");
			projectionList.add(Projections.property("clienteDTO.id.codigoCliente"), "pedidoDTO_clienteDTO_id_codigoCliente");
			projectionList.add(Projections.property("clienteDTO.codigoPersona"), "pedidoDTO_clienteDTO_codigoPersona");
			projectionList.add(Projections.property("clienteDTO.codigoEmpresa"), "pedidoDTO_clienteDTO_codigoEmpresa");
			projectionList.add(Projections.property("clienteDTO.userId"), "pedidoDTO_clienteDTO_userId");
			projectionList.add(Projections.property("clienteDTO.codigoValorTipoCliente"), "pedidoDTO_clienteDTO_codigoValorTipoCliente");
			projectionList.add(Projections.property("clienteDTO.codigoTipoCliente"), "pedidoDTO_clienteDTO_codigoTipoCliente");
			projectionList.add(Projections.property("clienteDTO.estado"), "pedidoDTO_clienteDTO_estado");
			projectionList.add(Projections.property("clienteDTO.usuarioRegistro"), "pedidoDTO_clienteDTO_usuarioRegistro");
			projectionList.add(Projections.property("clienteDTO.fechaRegistro"), "pedidoDTO_clienteDTO_fechaRegistro");
			
			// Proyecciones catalogos
			projectionList.add(Projections.property("tipoClienteCatalogoValorDTO.nombreCatalogoValor"), "pedidoDTO_clienteDTO_tipoClienteCatalogoValorDTO_nombreCatalogoValor");
			
			// Proyecciones entidad persona 
			projectionList.add(Projections.property("personaDTO.id.codigoCompania"), "pedidoDTO_clienteDTO_personaDTO_id_codigoCompania");
			projectionList.add(Projections.property("personaDTO.id.codigoPersona"), "pedidoDTO_clienteDTO_personaDTO_id_codigoPersona");
			projectionList.add(Projections.property("personaDTO.numeroDocumento"), "pedidoDTO_clienteDTO_personaDTO_numeroDocumento");
			projectionList.add(Projections.property("personaDTO.nombreCompleto"), "pedidoDTO_clienteDTO_personaDTO_nombreCompleto");
			
			// Proyecciones entidad contacto persona 
			projectionList.add(Projections.property("contactoPersonaDTO.direccionPrincipal"), "pedidoDTO_clienteDTO_personaDTO_contactoPersonaDTO_direccionPrincipal");
			projectionList.add(Projections.property("contactoPersonaDTO.ciudad"), "pedidoDTO_clienteDTO_personaDTO_contactoPersonaDTO_ciudad");
			projectionList.add(Projections.property("contactoPersonaDTO.telefonoPrincipal"), "pedidoDTO_clienteDTO_personaDTO_contactoPersonaDTO_telefonoPrincipal");
			
			// Proyecciones entidad empresa   
			projectionList.add(Projections.property("empresaDTO.id.codigoCompania"), "pedidoDTO_clienteDTO_empresaDTO_id_codigoCompania");
			projectionList.add(Projections.property("empresaDTO.id.codigoEmpresa"), "pedidoDTO_clienteDTO_empresaDTO_id_codigoEmpresa");
			projectionList.add(Projections.property("empresaDTO.numeroRuc"), "pedidoDTO_clienteDTO_empresaDTO_numeroRuc");
			projectionList.add(Projections.property("empresaDTO.razonSocial"), "pedidoDTO_clienteDTO_empresaDTO_razonSocial");
			
			// Proyecciones entidad contacto empresa
			projectionList.add(Projections.property("contactoEmpresaDTO.direccionPrincipal"), "pedidoDTO_clienteDTO_empresaDTO_contactoEmpresaDTO_direccionPrincipal");
			projectionList.add(Projections.property("contactoEmpresaDTO.ciudad"), "pedidoDTO_clienteDTO_empresaDTO_contactoEmpresaDTO_ciudad");
			projectionList.add(Projections.property("contactoEmpresaDTO.telefonoPrincipal"), "pedidoDTO_clienteDTO_empresaDTO_contactoEmpresaDTO_telefonoPrincipal");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(GuiaDespachoPedidoDTO.class));
			Collection<GuiaDespachoPedidoDTO> guiaDespachoPedidoDTOCols = new  ArrayList<GuiaDespachoPedidoDTO>();
			guiaDespachoPedidoDTOCols =  criteria.list();

			return guiaDespachoPedidoDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de pedidos por guia de despacho.").initCause(e);
		} 
	}
	
	/**
	 * M\u00e9todo para guardar y actualizar pedidos guia despacho
	 * @param guiaDespachoPedidoDTO
	 * @throws ERPException
	 */
	@Override
	public void crearActualizarGuiaDespachoPedidos(GuiaDespachoPedidoDTO guiaDespachoPedidoDTO) throws ERPException{
		try{
			if (guiaDespachoPedidoDTO.getId().getCodigoCompania() == null || guiaDespachoPedidoDTO.getUsuarioRegistro() == null) {
				throw new ERPException("El c\u00F3digo de compania y el id de usuario registro es requerido");
			}	
			
			sessionFactory.getCurrentSession().clear();
			if(guiaDespachoPedidoDTO.getId().getCodigoGuiaDespachoPedido() ==  null){
				Integer secuencialGuiaDespachoPedido  = this.secuenciaDAO.obtenerSecuencialTabla(GuiaDespachoPedidoID.NOMBRE_SECUENCIA);
				guiaDespachoPedidoDTO.getId().setCodigoGuiaDespachoPedido(Long.parseLong(""+secuencialGuiaDespachoPedido));
				guiaDespachoPedidoDTO.setFechaRegistro(new Date());
				guiaDespachoPedidoDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				sessionFactory.getCurrentSession().save(guiaDespachoPedidoDTO);
			}
			else
			{
				guiaDespachoPedidoDTO.setFechaModificacion(new Date());
				guiaDespachoPedidoDTO.setUsuarioModificacion(guiaDespachoPedidoDTO.getUsuarioRegistro());
				sessionFactory.getCurrentSession().update(guiaDespachoPedidoDTO);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Ocurrio un error al guardar o actualizar pedido guia despacho."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Ocurrio un error al guardar o actualizar pedido guia despacho."+e.getMessage());
		} 
	}

}
