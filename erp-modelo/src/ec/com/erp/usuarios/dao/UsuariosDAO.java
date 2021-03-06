/**
 * 
 */
package ec.com.erp.usuarios.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.UsuariosDTO;
import ec.com.erp.cliente.mdl.dto.id.UsuariosID;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2017-06-27
 */
public class UsuariosDAO implements IUsuariosDAO {

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
	 * M\u00e9todo para obtener lista de usuarios
	 * @param nombreUsuario
	 * @return 
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	public Collection<UsuariosDTO> obtenerListaUsuarios(String nombreUsuario) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(UsuariosDTO.class, "root");
			criteria.createAlias("root.perfilDTO", "perfilDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("perfilDTO.perfilCatalogoValorDTO", "perfilCatalogoValorDTO", CriteriaSpecification.INNER_JOIN);

			//restricciones
			if(StringUtils.isNotEmpty(nombreUsuario)){
				criteria.add(Restrictions.eq("root.nombreUsuario", nombreUsuario));
			}

			//proyecciones entidad usuario
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.userId"), "id_userId");
			projectionList.add(Projections.property("root.codigoPerfil"), "codigoPerfil");
			projectionList.add(Projections.property("root.codigoCompania"), "codigoCompania");
			projectionList.add(Projections.property("root.nombreUsuario"), "nombreUsuario");
			projectionList.add(Projections.property("root.passwordUsuario"), "passwordUsuario");
			projectionList.add(Projections.property("root.estado"), "estado");
			// Proyecciones perfil
			projectionList.add(Projections.property("perfilDTO.id.codigoPerfil"), "perfilDTO_id_codigoPerfil");
			projectionList.add(Projections.property("perfilDTO.nombrePerfil"), "perfilDTO_nombrePerfil");
			projectionList.add(Projections.property("perfilDTO.descripcion"), "perfilDTO_descripcion");
			
			// Proyecciones catalogos
			projectionList.add(Projections.property("perfilCatalogoValorDTO.nombreCatalogoValor"), "perfilDTO_perfilCatalogoValorDTO_nombreCatalogoValor");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(UsuariosDTO.class));
			Collection<UsuariosDTO> conveniosDiseniadoresCols = new  ArrayList<UsuariosDTO>();
			conveniosDiseniadoresCols =  criteria.list();

			return conveniosDiseniadoresCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de usuarios.").initCause(e);
		} 
	}
	
	/**
	 * M\u00e9todo para logearse 
	 * @param nombreUsuario
	 * @param password
	 * @return
	 * @throws ERPException
	 */
	public UsuariosDTO loginUser(String nombreUsuario, String password) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(UsuariosDTO.class, "root");
			criteria.createAlias("root.perfilDTO", "perfilDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("root.companiaDTO", "companiaDTO", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("perfilDTO.moduloPerfilDTOCols", "moduloPerfilDTOCols", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("moduloPerfilDTOCols.moduloDTO", "moduloDTO", CriteriaSpecification.LEFT_JOIN);
			//restricciones
			criteria.add(Restrictions.eq("root.estado", "1"));
			criteria.add(Restrictions.eq("root.nombreUsuario", nombreUsuario));
			criteria.add(Restrictions.eq("root.passwordUsuario", password));
			criteria.add(Restrictions.eq("moduloDTO.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));

			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.userId"), "id_userId");
			projectionList.add(Projections.property("root.codigoCompania"), "codigoCompania");
			projectionList.add(Projections.property("root.codigoPerfil"), "codigoPerfil");
			projectionList.add(Projections.property("root.nombreUsuario"), "nombreUsuario");
			projectionList.add(Projections.property("root.passwordUsuario"), "passwordUsuario");
			projectionList.add(Projections.property("root.estado"), "estado");
			
			projectionList.add(Projections.property("perfilDTO.id.codigoPerfil"), "perfilDTO_id_codigoPerfil");
			projectionList.add(Projections.property("perfilDTO.nombrePerfil"), "perfilDTO_nombrePerfil");
			projectionList.add(Projections.property("perfilDTO.descripcion"), "perfilDTO_descripcion");
			
			projectionList.add(Projections.property("companiaDTO.id.codigoCompania"), "companiaDTO_id_codigoCompania");
			projectionList.add(Projections.property("companiaDTO.nombreCompania"), "companiaDTO_nombreCompania");
			projectionList.add(Projections.property("companiaDTO.descripcionCompania"), "companiaDTO_descripcionCompania");
			
			// Proyecciones entidad modulo perfil
			projectionList.add(Projections.property("moduloPerfilDTOCols.id.codigoModulo"), "perfilDTO_moduloPerfilDTOCols_id_codigoModulo");
			projectionList.add(Projections.property("moduloPerfilDTOCols.id.codigoPerfil"), "perfilDTO_moduloPerfilDTOCols_id_codigoPerfil");
			projectionList.add(Projections.property("moduloPerfilDTOCols.estado"), "perfilDTO_moduloPerfilDTOCols_estado");
			
			// Proyecciones modulos
			projectionList.add(Projections.property("moduloDTO.id.codigoModulo"), "perfilDTO_moduloPerfilDTOCols_moduloDTO_id_codigoModulo");
			projectionList.add(Projections.property("moduloDTO.codigoReferencia"), "perfilDTO_moduloPerfilDTOCols_moduloDTO_codigoReferencia");
			projectionList.add(Projections.property("moduloDTO.nombreModulo"), "perfilDTO_moduloPerfilDTOCols_moduloDTO_nombreModulo");
			projectionList.add(Projections.property("moduloDTO.orden"), "perfilDTO_moduloPerfilDTOCols_moduloDTO_orden");
			projectionList.add(Projections.property("moduloDTO.estilo"), "perfilDTO_moduloPerfilDTOCols_moduloDTO_estilo");
			projectionList.add(Projections.property("moduloDTO.url"), "perfilDTO_moduloPerfilDTOCols_moduloDTO_url");
			projectionList.add(Projections.property("moduloDTO.valorTipo"), "perfilDTO_moduloPerfilDTOCols_moduloDTO_valorTipo");
			
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(UsuariosDTO.class));
			UsuariosDTO usuariosDTO = new UsuariosDTO();
			usuariosDTO = (UsuariosDTO)criteria.uniqueResult();
			
			if(usuariosDTO == null){
				usuariosDTO = new UsuariosDTO();
				usuariosDTO.setLogeado(Boolean.FALSE);
			}
			else
			{
				usuariosDTO.setLogeado(Boolean.TRUE);
			}

			return usuariosDTO;
		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de usuarios.").initCause(e);
		} 
	}
	
	/**
	 * M\u00e9todo para crear nuevos usuarios
	 * @param usuarioDTO
	 * @throws ERPException
	 */
	public void crearUsuario(UsuariosDTO usuarioDTO) throws ERPException{
		try{
			if (usuarioDTO.getNombreUsuario() == null || usuarioDTO.getPasswordUsuario() == null) {
				throw new ERPException("Error", "Existen campos que no deben ser nulos para crear el usuario");
			}	
			sessionFactory.getCurrentSession().clear();
			Integer secuencialArticulo  = this.secuenciaDAO.obtenerSecuencialTabla(UsuariosID.NOMBRE_SECUENCIA);
			usuarioDTO.getId().setUserId(ERPConstantes.PREFIJO_SECUENCIAL_USUARIO.concat(""+secuencialArticulo));
			sessionFactory.getCurrentSession().save(usuarioDTO);
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Error", "Ocurrio un error al crear el usuario."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Error", "Ocurrio un error al crear el usuario."+e.getMessage());
		} 
	}
	
	/**
	 * M\u00e9todo para crear actualizar usuarios
	 * @param usuarioDTO
	 * @throws ERPException
	 */
	public void actualizarUsuario(UsuariosDTO usuarioDTO) throws ERPException{
		try{
			if (usuarioDTO.getId().getUserId() == null || usuarioDTO.getNombreUsuario() == null || usuarioDTO.getPasswordUsuario() == null) {
				throw new ERPException("Error", "Existen campos que no deben ser nulos para actualizar el usuario");
			}	
			sessionFactory.getCurrentSession().clear();
			sessionFactory.getCurrentSession().update(usuarioDTO);
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Error", "Ocurrio un error al actualizar el usuario."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Error", "Ocurrio un error al actualizar el usuario."+e.getMessage());
		} 
	}
}
