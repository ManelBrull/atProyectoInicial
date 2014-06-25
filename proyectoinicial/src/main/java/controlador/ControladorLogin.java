package controlador;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import modelo.entidades.usuario.Usuario;
import vista.interfaz.Login;
import at.modelo.entidades.excepciones.CampoRequeridoException;

public class ControladorLogin {
	Login login;
	
	public ControladorLogin(Login myLogin){
		this.login = myLogin;
		login.setResult(false);
	}

	public void entrar() {
		try {
			iniciarContexto();
			/** Se comprueba que este en la tabla de usuarios que pueden acceder a la aplicacion **/ 
			if(Usuario.autentificar(login.getStringTextUsuari())){
				login.setResult(true);
				login.salir();
			}
			else {
				login.openError("Error","No tiene permisos para acceder a la aplicación");
			}
		} catch (Exception e) {
			login.openError("Error","El usuario es incorrecto, revise si ha escrito correctamente"
					+ " el nombre de usuario y la contraseña");
		}
	}
	/**
	 * Se lanza excepcion si existe algun error al crear el contexto
	 * @throws Exception
	 */
	private void iniciarContexto() throws Exception {
		String username = "atorrent\\" + login.getStringTextUsuari();
		String password = login.getStringTextContrasenya();
		String base = "DC=DINS,DC=ATORRENT,DC=ES";
		String ldapURL = "LDAP://sr1:389/DC=DINS,DC=ATORRENT,DC=ES";

		Hashtable<String, String> environment = new Hashtable<String, String>();
		environment.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		environment.put(Context.PROVIDER_URL, ldapURL);
		environment.put(Context.SECURITY_AUTHENTICATION, "simple");
		environment.put(Context.SECURITY_PRINCIPAL, username);
		environment.put(Context.SECURITY_CREDENTIALS, password);
		DirContext ctx = new InitialDirContext(environment);

	}
	
	public void salir() {
		login.setResult(false);
		login.salir();
	}
	
	
}
