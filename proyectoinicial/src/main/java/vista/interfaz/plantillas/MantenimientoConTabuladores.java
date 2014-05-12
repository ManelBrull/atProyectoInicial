package vista.interfaz.plantillas;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import vista.interfaz.Utils;
import at.controlador.IControladorMantenimientoConTabuladores;
import at.vista.interfaz.ATDialog;

public class MantenimientoConTabuladores extends ATDialog {

	protected Object result;
	protected Shell shell;

	protected IControladorMantenimientoConTabuladores controlador;
	
	private TabFolder tabFolder;
	
	/**
	 * Crea el dialog
	 * @param parent
	 */
	public MantenimientoConTabuladores(Shell parent) {
		super(parent,Utils.nombreProyecto + " - Mantenimiento con tabuladores");
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
				controlador.cerrarShell();
			}
		});
		shell.setSize(561, 590);
		super.createContents(shell);
		
		tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setBounds(0, 0, 558, 560);
		
		TabItem tbtmTab = new TabItem(tabFolder, SWT.NONE);
		tbtmTab.setText("Tab1");
		MantenimientoConTabuladoresTab1 tab1 = new MantenimientoConTabuladoresTab1(tabFolder, SWT.NONE);
		tbtmTab.setControl(tab1);
		
		TabItem tbtmTab_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmTab_1.setText("Tab2");
		MantenimientoConTabuladoresTab2 tab2 = new MantenimientoConTabuladoresTab2(tabFolder, SWT.NONE);
		tbtmTab_1.setControl(tab2);
		controlador.inicializar();
	}
	
	public int getTabActiva(){
		return tabFolder.getSelectionIndex();
	}
	
	@Override
	protected void myDispose() {
	}
}