package modelo.entidades.usuario;

import java.util.Iterator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import modelo.dao.UsuarioDAO;

import org.hibernate.HibernateException;

import at.modelo.entidades.ICrud;
import at.modelo.entidades.IEsFiltro;
import at.modelo.entidades.excepciones.CampoRequeridoException;

/**
 * 
 * Esta clase implementa los diferentes tipos de usuarios
 * que pueden existir en la aplicación
 * 
 * @author brullp
 *
 */
@Entity(name="Usuarios")
public class Usuario implements ICrud <Usuario>, Comparable <Usuario>, IEsFiltro {
	
	@Id @GeneratedValue
	private int idUsuarioPermitido;
	@Column(nullable = false)
	private String nombreUsuario;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Permisos permisos;
	
	public Usuario(){}
	
	@Transient
	public void inicializaUsuario(String nombre, Permisos permisos) throws CampoRequeridoException  {
		setNombreUsuario(nombre);
		setPermisos(permisos);
	}
	
	@Override 
	public void save() throws HibernateException {
		new UsuarioDAO().save(this);
	}

	@Override 
	public void delete() throws HibernateException {
		new UsuarioDAO().delete(this);
	}

	@Override 
	public void update(Usuario nuevo) throws HibernateException {
		nuevo.setIdUsuarioPermitido(getIdUsuarioPermitido());
		new UsuarioDAO().update(nuevo);
	}

	@Override 
	public Usuario get(int id) throws HibernateException {
		return new UsuarioDAO().get(id);
	}
	@Transient
	static public boolean autentificar(String nombreUsuario){
		return new UsuarioDAO().validarUsuario(nombreUsuario);
	}
	
	@Override
	public Iterator<Usuario> getAll() throws HibernateException {
		return new UsuarioDAO().getAll().iterator();
	}
	@Transient
	public Iterator<Usuario> getFiltro(String filtro) {
		return (new UsuarioDAO().getFiltro(filtro)).iterator();
	}
	@Override @Transient
	public int compareTo(Usuario arg0) {
		return toString().compareTo(arg0.toString());
	}
		
	@Override @Transient
	public String toString() {
		return "nombreUsuario=" + getNombreUsuario();
	}
	
	@Override
	public String[] toTable() {
		return new String[]{getNombreUsuario(), Permisos.getValues()[getPermisos().ordinal()]};
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nombreUsuario == null) ? 0 : nombreUsuario.hashCode());
		result = prime * result
				+ ((permisos == null) ? 0 : permisos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Usuario))
			return false;
		Usuario other = (Usuario) obj;
		if (nombreUsuario == null) {
			if (other.nombreUsuario != null)
				return false;
		} else if (!nombreUsuario.equals(other.nombreUsuario))
			return false;
		if (permisos != other.permisos)
			return false;
		return true;
	}

	public int getIdUsuarioPermitido() {
		return idUsuarioPermitido;
	}

	public void setIdUsuarioPermitido(int idUsuarioPermitido) {
		this.idUsuarioPermitido = idUsuarioPermitido;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) throws CampoRequeridoException  {
		if(nombreUsuario.isEmpty())
			throw new CampoRequeridoException(
					"El nombre de usuario es un campo requerido");
		else
			this.nombreUsuario = nombreUsuario;
	}

	public Permisos getPermisos() {
		return permisos;
	}

	public void setPermisos(Permisos permisos) {
		this.permisos = permisos;
	}

	

	

}
