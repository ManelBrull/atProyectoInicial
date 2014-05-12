package vista.interfaz;

import modelo.entidades.usuario.Permisos;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import at.vista.IMantenimiento;
import at.vista.interfaz.ATDialog;
import controlador.ControladorUsuarios;


public class MantenimientoDeUsuarios extends ATDialog implements IMantenimiento {

	protected Object result;
	protected Shell shell;
	
	private ControladorUsuarios cUsuarios;
	
	/** Table que muestra los resultados del filtro **/
	private Table table;
	
	private Button btnGrabar;
	private Button btnEliminar;
	private Button btnBuscar;
	private Button btnNuevo;
	private Button btnSalir;
	
	private Text textNombreFiltro;
	private TableColumn tblclmnNombre;
	private TableColumn tblclmnPermisos;
	private CLabel labelNombre;
	private Text textNombre;
	private CLabel labelPermisos;
	private Combo combo;
	private Combo comboPermisos;

	/**
	 * Crea el dialog
	 * @param parent
	 */
	public MantenimientoDeUsuarios(Shell parent) {
		super(parent,"Nombre del proyecto - Usuaris");
		cUsuarios = new ControladorUsuarios(this);
	};

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
		shell.addShellListener(new ShellAdapter() {
			@Override
			public void shellClosed(ShellEvent e) {
				cUsuarios.cerrarShell();
			}
		});
		shell.setSize(560, 335);
		super.createContents(shell);
		
		Composite compositeMain = new Composite(shell, SWT.NONE);
		compositeMain.setBounds(0, 0, 555, 304);
		
		
		Group grpClase = new Group(compositeMain, SWT.NONE);
		grpClase.setText("Datos Clase");
		grpClase.setFont(getGroupFont());
		grpClase.setForeground(getAzul());
		grpClase.setBounds(5, 221, 545, 73);
		
		btnNuevo = new Button(grpClase, SWT.NONE);
		btnNuevo.setBounds(247, 47, 68, 23);
		btnNuevo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cUsuarios.nuevo();
			}
		});
		btnNuevo.setText("Nuevo");
		
		btnGrabar = new Button(grpClase, SWT.NONE);
		btnGrabar.setBounds(321, 47, 68, 23);
		btnGrabar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cUsuarios.grabar();
			}
		});
		btnGrabar.setEnabled(false);
		btnGrabar.setText("Grabar");
		
		btnEliminar = new Button(grpClase, SWT.NONE);
		btnEliminar.setBounds(395, 47, 68, 23);
		btnEliminar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cUsuarios.eliminar();
			}
		});
		btnEliminar.setEnabled(false);
		btnEliminar.setText("Eliminar");
		
		btnSalir = new Button(grpClase, SWT.NONE);
		btnSalir.setBounds(467, 47, 68, 23);
		btnSalir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cUsuarios.salir();
			}
		});
		btnSalir.setText("Salir");
		
		labelNombre = new CLabel(grpClase, SWT.NONE);
		labelNombre.setText("Nombre:");
		labelNombre.setForeground(SWTResourceManager.getColor(23, 77, 147));
		labelNombre.setFont(SWTResourceManager.getFont("MS Sans Serif", 1, SWT.BOLD));
		labelNombre.setBounds(10, 20, 54, 21);
		
		textNombre = new Text(grpClase, SWT.BORDER);
		textNombre.setBounds(70, 20, 142, 21);
		
		labelPermisos = new CLabel(grpClase, SWT.NONE);
		labelPermisos.setText("Permisos:");
		labelPermisos.setForeground(SWTResourceManager.getColor(23, 77, 147));
		labelPermisos.setFont(SWTResourceManager.getFont("MS Sans Serif", 1, SWT.BOLD));
		labelPermisos.setBounds(218, 20, 68, 21);
		
		comboPermisos = new Combo(grpClase, SWT.READ_ONLY);
		comboPermisos.setBounds(292, 20, 243, 21);
		comboPermisos.setItems(Permisos.getValues());
		comboPermisos.select(0);
		
		Group grpFiltro = new Group(compositeMain, SWT.NONE);
		grpFiltro.setLocation(5, 5);
		grpFiltro.setSize(545, 210);
		grpFiltro.setText("Filtro");
		grpFiltro.setFont(getGroupFont());
		grpFiltro.setForeground(getAzul());
		
		
		CLabel lblNombreFiltro = new CLabel(grpFiltro, SWT.NONE);
		lblNombreFiltro.setBounds(10, 20, 54, 21);
		lblNombreFiltro.setFont(getLabelFont());
		lblNombreFiltro.setForeground(getAzul());
		lblNombreFiltro.setText("Nombre:");
		
		CLabel lblPermisosFiltro = new CLabel(grpFiltro, SWT.NONE);
		lblPermisosFiltro.setBounds(218, 20, 68, 21);
		lblPermisosFiltro.setFont(getLabelFont());
		lblPermisosFiltro.setForeground(getAzul());
		lblPermisosFiltro.setText("Permisos:");
		
		btnBuscar = new Button(grpFiltro, SWT.NONE);
		btnBuscar.setBounds(391, 18, 68, 23);
		btnBuscar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cUsuarios.buscar();
			}
		});
		btnBuscar.setText("Buscar");
		
		Button btnBorrar = new Button(grpFiltro, SWT.NONE);
		btnBorrar.setBounds(465, 18, 68, 23);
		btnBorrar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cUsuarios.borrar();
			}
		});
		btnBorrar.setText("Borrar");
		
		table = new Table(grpFiltro, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 47, 523, 155);
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cUsuarios.elementoFiltroSeleccionado();
			}
		});
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		tblclmnNombre = new TableColumn(table, SWT.NONE);
		tblclmnNombre.setWidth(198);
		tblclmnNombre.setText("Nombre");
		
		tblclmnPermisos = new TableColumn(table, SWT.NONE);
		tblclmnPermisos.setWidth(197);
		tblclmnPermisos.setText("Permisos");
		
		textNombreFiltro = new Text(grpFiltro, SWT.BORDER);
		textNombreFiltro.setBounds(70, 20, 142, 21);
		
		combo = new Combo(grpFiltro, SWT.NONE);
		combo.setItems(new String[] {"Todos", "Lectura", "Escritura", "Administrador"});
		combo.setBounds(292, 20, 93, 21);
		combo.select(2);
		cUsuarios.inicializar();
	}
	
	@Override
	protected void myDispose() {
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
	
	public Shell getShell() {
		return shell;
	}

	public void setShell(Shell shell) {
		this.shell = shell;
	}

	public Button getBtnGrabar() {
		return btnGrabar;
	}

	public void setBtnGrabar(Button btnGrabar) {
		this.btnGrabar = btnGrabar;
	}

	public Button getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(Button btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public Button getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(Button btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public Button getBtnNuevo() {
		return btnNuevo;
	}

	public void setBtnNuevo(Button btnNuevo) {
		this.btnNuevo = btnNuevo;
	}

	public Button getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(Button btnSalir) {
		this.btnSalir = btnSalir;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Text getTextNombreFiltro() {
		return textNombreFiltro;
	}

	public void setTextNombreFiltro(Text textNombreFiltro) {
		this.textNombreFiltro = textNombreFiltro;
	}

	public Text getTextNombre() {
		return textNombre;
	}

	public void setTextNombre(Text textNombre) {
		this.textNombre = textNombre;
	}

	public Combo getComboPermisos() {
		return comboPermisos;
	}

	public void setComboPermisos(Combo comboPermisos) {
		this.comboPermisos = comboPermisos;
	}

}