package controlador;
import vista.interfaz.Inicio;
import vista.interfaz.MantenimientoDeUsuarios;
import at.controlador.GestionError;
import at.vista.informes.ReportManager;

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
		if(inicio.abrirDialogInforme()){
			try {
				String pathReporte = "/es/atorrent/informes/ejemplo.jasper"; 
				ReportManager.lanzarReporte(Inicio.class.getResourceAsStream(pathReporte));
						 
			} catch (Exception e1) {
				e1.printStackTrace();
				new GestionError(inicio.getShell(), e1);
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
