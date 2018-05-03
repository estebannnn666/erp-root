/**
 * 
 */
package ec.com.erp.persona.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.PersonaDTO;
import ec.com.erp.cliente.mdl.dto.id.PersonaID;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2017-06-27
 */
public class PersonaDAO implements IPersonaDAO {

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
	 * M\u00e9todo para obtener lista de personas
	 * @param codigoCompania
	 * @return
	 * @throws ERPException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<PersonaDTO> obtenerListaPersona(Integer codigoCompania) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(PersonaDTO.class, "root");

			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));

			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoPersona"), "id_codigoPersona");
			projectionList.add(Projections.property("root.numeroDocumento"), "numeroDocumento");
			projectionList.add(Projections.property("root.primerApellido"), "primerApellido");
			projectionList.add(Projections.property("root.segundoApellido"), "segundoApellido");
			projectionList.add(Projections.property("root.primerNombre"), "primerNombre");
			projectionList.add(Projections.property("root.segundoNombre"), "segundoNombre");
			projectionList.add(Projections.property("root.nombreCompleto"), "nombreCompleto");
			projectionList.add(Projections.property("root.fechaNacimiento"), "fechaNacimiento");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(PersonaDTO.class));
			Collection<PersonaDTO> personaDTOCols = new  ArrayList<PersonaDTO>();
			personaDTOCols =  criteria.list();

			return personaDTOCols;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de convenios con diseniadores.").initCause(e);
		} 
	}

	
	/**
	 * M\u00e9todo para crear o actualizar personas
	 * @param personaDTO
	 * @throws ERPException
	 */
	public void crearActualizarPersona(PersonaDTO personaDTO) throws ERPException{
		try{
			if (personaDTO.getId().getCodigoCompania() == null || personaDTO.getUsuarioRegistro() == null) {
				throw new ERPException("El c\u00F3digo de compania y el id de usuario registro es requerido");
			}	
			
			sessionFactory.getCurrentSession().clear();
			if(personaDTO.getId().getCodigoPersona() ==  null){
				Integer secuencialArticulo  = this.secuenciaDAO.obtenerSecuencialTabla(PersonaID.NOMBRE_SECUENCIA);
				personaDTO.getId().setCodigoPersona(Long.parseLong(""+secuencialArticulo));
				personaDTO.setFechaRegistro(new Date());
				personaDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				sessionFactory.getCurrentSession().save(personaDTO);
			}
			else
			{
				personaDTO.setFechaModificacion(new Date());
				personaDTO.setUsuarioModificacion(personaDTO.getUsuarioRegistro());
				sessionFactory.getCurrentSession().update(personaDTO);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Ocurrio un error al guardar o actualizar la persona."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Ocurrio un error al guardar o actualizar la persona."+e.getMessage());
		} 
	}
}
