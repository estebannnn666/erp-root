/**
 * 
 */
package ec.com.erp.empresa.dao;

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
import ec.com.erp.cliente.mdl.dto.EmpresaDTO;
import ec.com.erp.cliente.mdl.dto.id.EmpresaID;
import ec.com.erp.secuencia.dao.ISecuenciaDAO;
import ec.com.erp.utilitario.dao.commons.hibernate.transformers.MultiLevelResultTransformer;

/**
 * @author Esteban Gudino
 * 2017-06-27
 */
public class EmpresaDAO implements IEmpresaDAO {

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
	 * M\u00E9todo para obtener la empresa por codigo
	 * @param codigoCompania
	 * @param numeroRuc
	 * @return
	 * @throws ERPException
	 */
	@Override
	public EmpresaDTO obtenerEmpresaByCodigo(Integer codigoCompania, String numeroRuc) throws ERPException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.clear();

			//joins
			Criteria criteria  = session.createCriteria(EmpresaDTO.class, "root");
			criteria.createAlias("root.contactoDTOCols", "contactoEmpresaDTO", CriteriaSpecification.LEFT_JOIN);
			
			//restricciones
			criteria.add(Restrictions.eq("root.id.codigoCompania", codigoCompania));
			criteria.add(Restrictions.eq("root.numeroRuc", numeroRuc));
			criteria.add(Restrictions.eq("root.estado", ERPConstantes.ESTADO_ACTIVO_NUMERICO));

			//proyecciones entidad empresa
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("root.id.codigoCompania"), "id_codigoCompania");
			projectionList.add(Projections.property("root.id.codigoEmpresa"), "id_codigoEmpresa");
			projectionList.add(Projections.property("root.numeroRuc"), "numeroRuc");
			projectionList.add(Projections.property("root.razonSocial"), "razonSocial");
			projectionList.add(Projections.property("root.descripcionEmpresa"), "descripcionEmpresa");
			projectionList.add(Projections.property("root.estado"), "estado");
			projectionList.add(Projections.property("root.usuarioRegistro"), "usuarioRegistro");
			projectionList.add(Projections.property("root.fechaRegistro"), "fechaRegistro");
			
			// Proyecciones entidad contacto empresa 
			projectionList.add(Projections.property("contactoEmpresaDTO.id.codigoCompania"), "contactoEmpresaDTO_id_codigoCompania");
			projectionList.add(Projections.property("contactoEmpresaDTO.id.codigoContacto"), "contactoEmpresaDTO_id_codigoContacto");			
			projectionList.add(Projections.property("contactoEmpresaDTO.codigoPersona"), "contactoEmpresaDTO_codigoPersona");
			projectionList.add(Projections.property("contactoEmpresaDTO.codigoEmpresa"), "contactoEmpresaDTO_codigoEmpresa");
			projectionList.add(Projections.property("contactoEmpresaDTO.direccionPrincipal"), "contactoEmpresaDTO_direccionPrincipal");			
			projectionList.add(Projections.property("contactoEmpresaDTO.callePrincipal"), "contactoEmpresaDTO_callePrincipal");			
			projectionList.add(Projections.property("contactoEmpresaDTO.calleSecundaria"), "contactoEmpresaDTO_calleSecundaria");			
			projectionList.add(Projections.property("contactoEmpresaDTO.numeroCasa"), "contactoEmpresaDTO_numeroCasa");
			projectionList.add(Projections.property("contactoEmpresaDTO.referencia"), "contactoEmpresaDTO_referencia");
			projectionList.add(Projections.property("contactoEmpresaDTO.ciudad"), "contactoEmpresaDTO_ciudad");			
			projectionList.add(Projections.property("contactoEmpresaDTO.telefonoPrincipal"), "contactoEmpresaDTO_telefonoPrincipal");
			projectionList.add(Projections.property("contactoEmpresaDTO.telefonoCelular"), "contactoEmpresaDTO_telefonoCelular");			
			projectionList.add(Projections.property("contactoEmpresaDTO.codigoValarTipoContacto"), "contactoEmpresaDTO_codigoValarTipoContacto");
			projectionList.add(Projections.property("contactoEmpresaDTO.codigoTipoContacto"), "contactoEmpresaDTO_codigoTipoContacto");			
			projectionList.add(Projections.property("contactoEmpresaDTO.estado"), "contactoEmpresaDTO_estado");
			projectionList.add(Projections.property("contactoEmpresaDTO.usuarioRegistro"), "contactoEmpresaDTO_usuarioRegistro");
			projectionList.add(Projections.property("contactoEmpresaDTO.fechaRegistro"), "contactoEmpresaDTO_fechaRegistro");
						
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new MultiLevelResultTransformer(EmpresaDTO.class));
			EmpresaDTO empresaDTO = (EmpresaDTO)criteria.uniqueResult();

			return empresaDTO;

		} catch (ERPException e) {
			throw e;
		} catch (Exception e) {
			throw (ERPException)new ERPException("Error al obtener lista de empresas por numero de ruc.").initCause(e);
		} 
	}

	
	/**
	 * M\u00E9todo para crear o actualizar empresas
	 * @param empresaDTO
	 * @throws ERPException
	 */
	public void crearActualizarEmpresa(EmpresaDTO empresaDTO) throws ERPException{
		try{
			if (empresaDTO.getId().getCodigoCompania() == null || empresaDTO.getUsuarioRegistro() == null) {
				throw new ERPException("Error", "El c\u00F3digo de compania y el id de usuario registro es requerido");
			}	
			
			sessionFactory.getCurrentSession().clear();
			if(empresaDTO.getId().getCodigoEmpresa() ==  null){
				Integer secuencialEmpresa  = this.secuenciaDAO.obtenerSecuencialTabla(EmpresaID.NOMBRE_SECUENCIA);
				empresaDTO.getId().setCodigoEmpresa(Long.parseLong(""+secuencialEmpresa));
				empresaDTO.setFechaRegistro(new Date());
				empresaDTO.setEstado(ERPConstantes.ESTADO_ACTIVO_NUMERICO);
				sessionFactory.getCurrentSession().save(empresaDTO);
			}
			else
			{
				empresaDTO.setFechaModificacion(new Date());
				empresaDTO.setUsuarioModificacion(empresaDTO.getUsuarioRegistro());
				sessionFactory.getCurrentSession().update(empresaDTO);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (ERPException e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar la empresa."+e.getMessage());
		} catch (Exception e) {
			throw new ERPException("Error", "Ocurrio un error al guardar o actualizar la empresa."+e.getMessage());
		} 
	}
}
