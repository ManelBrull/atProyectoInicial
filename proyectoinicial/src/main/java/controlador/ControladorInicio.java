package controlador;
import vista.interfaz.Inicio;
import vista.interfaz.MantenimientoDeUsuarios;
import at.vista.informes.JasperReportManager;

public class ControladorInicio {
	
	Inicio inicio;
	JasperReportManager reportManager;
	/**
	 * 
	 * @param mInicio
	 */
	public ControladorInicio(Inicio mInicio){
		this.inicio = mInicio;
		this.reportManager = new JasperReportManager();
	}

	public void cargarMantenimiento() {
		
	}

	public void cargarInforme() {
		int opcion = 
				inicio.openQuestion(
				"Lanzamiento informe", 
				"Va a abrir el informe, ¿Desea continuar?",
				new String[]{"Si", "No"});
		if(opcion == 0)
		{
			try {
				String pathReporte = "/es/atorrent/informes/ejemplo.jasper"; 
				reportManager.lanzarReporte(Inicio.class.getResourceAsStream(pathReporte));
						 
			} catch (Exception e1) {
				this.inicio.openError(
						"lanzamiento de informe", 
						"No se ha podido abrir el informe requerido");
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
