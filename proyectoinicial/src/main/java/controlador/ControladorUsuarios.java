package controlador;

import java.util.Iterator;

import modelo.entidades.usuario.Permisos;
import modelo.entidades.usuario.Usuario;
import vista.interfaz.MantenimientoDeUsuarios;
import at.controlador.ControladorMantenimiento;
import at.modelo.entidades.excepciones.CampoRequeridoException;

public class ControladorUsuarios extends ControladorMantenimiento<Usuario> {

	MantenimientoDeUsuarios mantenimientoUsuarios;
	
	public ControladorUsuarios(MantenimientoDeUsuarios mant) {
		super(mant);
		this.mantenimientoUsuarios = mant;
	}

	@Override
	public Iterator<Usuario> getIteratorFiltro() {
		return new Usuario().getFiltro(
				mantenimientoUsuarios.getTextNombreFiltro());
	}

	@Override
	public void rellenarInterfaz() {
		mantenimientoUsuarios.setTextNombre(entidadSeleccionado.getNombreUsuario());
		mantenimientoUsuarios.selectComboPermisos(entidadSeleccionado.getPermisos().ordinal());
	}

	@Override
	public void borrarInterfaz() {
		mantenimientoUsuarios.setTextNombre("");
		mantenimientoUsuarios.setTextNombreFiltro("");
		mantenimientoUsuarios.selectComboPermisos(0);
	}

	@Override
	public Usuario creaObjeto() {
		Usuario usr = new Usuario();
		try {
			usr.inicializaUsuario(
					mantenimientoUsuarios.getTextNombre(), 
					Permisos.values()[mantenimientoUsuarios.getSelectionIndexComboPermisos()]);
		} catch (CampoRequeridoException e) {
			mantenimientoUsuarios.openError(
					"Error", 
					"No se ha podido inicializar el usuario");
			return null;
		}
		return usr;
	}
	
	public Usuario creaObjetoSilencioso() {
		Usuario usr = new Usuario();
		try {
			usr.inicializaUsuario(
					mantenimientoUsuarios.getTextNombre(), 
					Permisos.values()[mantenimientoUsuarios.getSelectionIndexComboPermisos()]);
		} catch (CampoRequeridoException e) {
			return null;
		}
		return usr;
	}

}
