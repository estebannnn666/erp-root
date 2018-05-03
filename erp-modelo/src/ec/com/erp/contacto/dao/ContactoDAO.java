/**
 * 
 */
package ec.com.erp.contacto.dao;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ContactoDTO;
import ec.com.erp.cliente.mdl.dto.id.ContactoID;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2017-06-27
 */
public class ContactoDAO implements IContactoDAO {

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
	 * M\u00e9todo para obtener el contacto por persona o empresa
	 * @param codigoCompania
	 * @param codigoPersona
	 * @param codigoEmpresa
	 * @return
	 * @throws ERPException
	 */
	@Override
	public ContactoDTO obtenerListaContactos(Integer codigoCompania, Long codigoPersona, Long codigoEmpresa) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(ContactoDTO.class, "root");

			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));
			criteria.add(Restrictions.eq("root.codigoPersona", codigoPersona));
			criteria.add(Restrictions.eq("root.codigoEmpresa", codigoEmpresa));

			//proyecciones entidad negociacion proveedor
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoContacto"), "id_codigoContacto");
			projectionList.add(Projections.property("root.codigoPersona"), "codigoPersona");
			projectionList.add(Projections.property("root.codigoEmpresa"), "codigoEmpresa");
			projectionList.add(Projections.property("root.callePrincipal"), "callePrincipal");
			projectionList.add(Projections.property("root.calleSecundaria"), "calleSecundaria");
			projectionList.add(Projections.property("root.numeroCasa"), "numeroCasa");
			projectionList.add(Projections.property("root.referencia"), "referencia");
			projectionList.add(Projections.property("root.ciudad"), "ciudad");
			projectionList.add(Projections.property("root.telefonoPrincipal"), "telefonoPrincipal");
			projectionList.add(Projections.property("root.telefonoCelular"), "telefonoCelular");
			projectionList.add(Projections.property("root.codigoValarTipoContacto"), "codigoValarTipoContacto");
			projectionList.add(Projections.property("root.codigoTipoContacto"), "codigoTipoContacto");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(ContactoDTO.class));
			ContactoDTO contactoDTO = (ContactoDTO)criteria.uniqueResult();

			return contactoDTO;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de convenios con diseniadores.").initCause(e);
		} 
	}

	/**
	 * M\u00e9todo para crear o actualizar 
	 * @param contactoDTO
	 * @throws ERPException
	 */
	public void crearActualizarContacto(ContactoDTO contactoDTO)throws ERPException{
		try{
			if (contactoDTO.getId().getCodigoCompania() == null || contactoDTO.getUsuarioRegistro() == null) {
				throw new ERPException("El c\u00F3digo de compania y el id de usuario registro es requerido");
			}	
			
			sessionFactory.getCurrentSession().clear();
			if(contactoDTO.getId().getCodigoContacto() ==  null){
				Integer secuencialContacto  = this.secuenciaDAO.obtenerSecuencialTabla(ContactoID.NOMBRE_SECUENCIA);
				contactoDTO.getId().setCodigoContacto(Long.parseLong(""+secuencialContacto));
				contactoDTO.setFechaRegistro(new Date());
				contactoDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				sessionFactory.getCurrentSession().save(contactoDTO);
			}
			else
			{
				contactoDTO.setFechaModificacion(new Date());
				contactoDTO.setUsuarioModificacion(contactoDTO.getUsuarioRegistro());
				sessionFactory.getCurrentSession().update(contactoDTO);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Ocurrio un error al guardar o actualizar el contacto."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Ocurrio un error al guardar o actualizar el contacto."+e.getMessage());
		} 
	}
}
