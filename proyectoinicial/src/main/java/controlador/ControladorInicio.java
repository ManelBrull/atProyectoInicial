package controlador;
import org.eclipse.jface.dialogs.MessageDialog;

import vista.interfaz.Inicio;
import vista.interfaz.MantenimientoDeUsuarios;

public class ControladorInicio {
	
	Inicio inicio;
	/**
	 * 
	 * @param mInicio
	 */
	public ControladorInicio(Inicio mInicio){
		this.inicio = mInicio;
	}

	public void cargarMantenimiento() {
		
	}

	public void cargarInforme() {
		MessageDialog dialog = new MessageDialog(inicio.getShell(), 
				"Informe", 
				null,
				"Va a abrir el informe, ¿Desea continuar?",
				MessageDialog.QUESTION,
				new String[]{"Si", "No"},
				0);
		if(dialog.open() == 0){
			try {
				String pathReporte = "/es/atorrent/informes/BecariosActivosHoy.jasper"; 
//				ReportManager.lanzarReporte(Inicio.class.getResourceAsStream(pathReporte));
						 
			} catch (Exception e1) {
				e1.printStackTrace();
				MessageDialog.openError(inicio.getShell(), "Error", "Ha ocurrido un error en el informe");
			}
		}
		
	}
	
	public void close() {
		inicio.dispose();
		inicio.getShell().dispose();
	}

	public void cargarMantenimientoUsuarios() {
		MantenimientoDeUsuarios mdu = new MantenimientoDeUsuarios(inicio.getShell());
		mdu.open();
		
	}
	
}
