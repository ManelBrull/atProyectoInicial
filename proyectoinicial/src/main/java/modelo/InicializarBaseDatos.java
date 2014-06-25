package modelo;

import modelo.entidades.usuario.Permisos;
import modelo.entidades.usuario.Usuario;
import at.modelo.entidades.excepciones.CampoRequeridoException;


public class InicializarBaseDatos {
	public InicializarBaseDatos(){
		inicializarUsuarios();
	}

	private void inicializarUsuarios(){
		Usuario developer = new Usuario();
		try {
			developer.setNombreUsuario("brullp");
			developer.setPermisos(Permisos.Administrador);
			developer.save();
		} catch (CampoRequeridoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
