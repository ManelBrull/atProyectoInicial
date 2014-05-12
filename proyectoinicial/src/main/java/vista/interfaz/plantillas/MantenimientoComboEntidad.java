package vista.interfaz.plantillas;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import at.controlador.ControladorMantenimientoComboEntidad;
import at.vista.IMantenimiento;
import at.vista.interfaz.ATDialog;

public class MantenimientoComboEntidad extends ATDialog implements IMantenimiento {

	protected Object result;
	protected Shell shell;
	
	/** Controlador de las acciones de la interfaz **/
	private ControladorMantenimientoComboEntidad controlador;
	
	/** Table que muestra los resultados del filtro **/
	private Table table;
	
	private Button btnGrabar;
	private Button btnEliminar;
	private Button btnBuscar;
	private Button btnNuevo;
	private Button btnSalir;
	
	private Text textNombreFiltro;
	private Text textValor;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public MantenimientoComboEntidad(Shell parent) {
		super(parent, "NombreProyecto - ComboEntidad");
//		controlador = new ControladorConcreto();
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(460, 351);
		super.createContents(shell);
		
		Composite compositeMain = new Composite(shell, SWT.NONE);
		compositeMain.setBounds(0, 0, 457, 324);
		
		
		Group grpClase = new Group(compositeMain, SWT.NONE);
		grpClase.setText("Datos Clase");
		grpClase.setFont(getGroupFont());
		grpClase.setForeground(getAzul());
		grpClase.setBounds(5, 221, 447, 98);
		
		btnNuevo = new Button(grpClase, SWT.NONE);
		btnNuevo.setBounds(151, 67, 68, 23);
		btnNuevo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				controlador.nuevo();
			}
		});
		btnNuevo.setText("Nuevo");
		
		btnGrabar = new Button(grpClase, SWT.NONE);
		btnGrabar.setBounds(225, 67, 68, 23);
		btnGrabar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				controlador.grabar();
			}
		});
		btnGrabar.setEnabled(false);
		btnGrabar.setText("Grabar");
		
		btnEliminar = new Button(grpClase, SWT.NONE);
		btnEliminar.setBounds(299, 67, 68, 23);
		btnEliminar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				controlador.eliminar();
			}
		});
		btnEliminar.setEnabled(false);
		btnEliminar.setText("Eliminar");
		
		btnSalir = new Button(grpClase, SWT.NONE);
		btnSalir.setBounds(371, 67, 68, 23);
		btnSalir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				controlador.salir();
			}
		});
		btnSalir.setText("Salir");
		
		CLabel lblValor = new CLabel(grpClase, SWT.NONE);
		lblValor.setBounds(10, 20, 61, 21);
		lblValor.setText("Valor: ");
		lblValor.setForeground(getAzul());
		lblValor.setFont(getLabelFont());
		
		textValor = new Text(grpClase, SWT.BORDER);
		textValor.setBounds(77, 20, 362, 21);
		
		Button btnSeleccionar = new Button(grpClase, SWT.NONE);
		btnSeleccionar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				controlador.seleccionado();
			}
		});
		btnSeleccionar.setText("Seleccionar");
		btnSeleccionar.setBounds(77, 67, 68, 23);
		
		Group grpFiltro = new Group(compositeMain, SWT.NONE);
		grpFiltro.setLocation(5, 5);
		grpFiltro.setSize(447, 210);
		grpFiltro.setText("Filtro");
		grpFiltro.setFont(getGroupFont());
		grpFiltro.setForeground(getAzul());
		
		
		CLabel lblNombreFiltro = new CLabel(grpFiltro, SWT.NONE);
		lblNombreFiltro.setBounds(10, 20, 54, 21);
		lblNombreFiltro.setFont(getLabelFont());
		lblNombreFiltro.setForeground(getAzul());
		lblNombreFiltro.setText("Valor: ");
		
		btnBuscar = new Button(grpFiltro, SWT.NONE);
		btnBuscar.setBounds(295, 20, 68, 23);
		btnBuscar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				controlador.buscar();
			}
		});
		btnBuscar.setText("Buscar");
		
		Button btnBorrar = new Button(grpFiltro, SWT.NONE);
		btnBorrar.setBounds(369, 20, 68, 23);
		btnBorrar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				controlador.borrar();
			}
		});
		btnBorrar.setText("Borrar");
		
		table = new Table(grpFiltro, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 47, 427, 155);
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				controlador.elementoFiltroSeleccionado();
			}
		});
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnValor = new TableColumn(table, SWT.CENTER);
		tblclmnValor.setWidth(423);
		tblclmnValor.setText("Valor");
		
		textNombreFiltro = new Text(grpFiltro, SWT.BORDER);
		textNombreFiltro.setBounds(70, 20, 219, 21);
		controlador.inicializar();
		

	}

	@Override
	protected void myDispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Button getBtnBuscar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Button getBtnNuevo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Button getBtnGrabar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Button getBtnSalir() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Button getBtnEliminar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table getTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shell getShell() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void openError(String cabecera, String mensaje) {
		MessageDialog.openError(
				getShell(),
				cabecera,
				mensaje);
	}

	@Override
	public void openInformation(String cabecera, String mensaje) {
		MessageDialog.openInformation(
				getShell(),
				cabecera,
				mensaje);
	}

	@Override
	public int openQuestion(String cabecera, String mensaje, String[] opciones) {
		MessageDialog dialog = new MessageDialog(
				getShell(), 
				cabecera, 
				null,
				mensaje,
				MessageDialog.QUESTION,
				opciones,
				0 );
		return dialog.open();
	}
}
