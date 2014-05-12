package modelo.dao;

import java.util.List;

import modelo.HibernateUtil;
import modelo.entidades.usuario.Usuario;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import at.modelo.dao.ATDAO;


public class UsuarioDAO extends ATDAO<Usuario> {

	@Override
	protected void iniciaOperacion() {
		this.session = HibernateUtil.getSessionFactory().openSession();
	    tx = session.beginTransaction();
	}
	
	@Override
	protected Usuario sessionGet(int id) {
		return ((Usuario) session.get(Usuario.class, id));
	}

	@Override
	protected Query createQueryGetAll() {
		return session.createQuery("from Usuarios");
	}
	
	public boolean validarUsuario(String name) throws HibernateException{
		iniciaOperacion();
		Long i = ((Long) session.createQuery(
				"Select count(*) from Usuarios where nombreUsuario = '"+name+"'")
				.iterate().next());
		if(i == 1) return true;
		return false;
	}

	public List <Usuario> getFiltro(String filtro) {
		List <Usuario> listUsuario = null;
		try {
		iniciaOperacion();
		listUsuario = session.createQuery(
				"from Usuarios where nombreUsuario like '%"+filtro+"%'").list();
		} catch(HibernateException he){
			throw he;
		} finally{
			session.close();
		}
		
		return listUsuario;
	}

	

}
