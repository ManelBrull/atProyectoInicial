package vista.interfaz;


import modelo.HibernateUtil;
import modelo.InicializarBaseDatos;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import at.vista.IMensajes;
import at.vista.interfaz.Recursos;
import controlador.ControladorInicio;
import controlador.ControladorLogin;


public class Inicio implements IMensajes {

		protected Shell shell;
		private ControladorInicio cInicio;
		
		/**
		 * Launch the application.
		 * @param args
		 */
		public static void main(String[] args) {
			try {
				new InicializarBaseDatos();
				Inicio window = new Inicio();
				window.setcInicio(new ControladorInicio(window));
				window.open();
//				window.dispose();
			} catch (Exception e) {
				e.printStackTrace();
			}
			HibernateUtil.closeConnection();
			System.exit(0);
		}
		
		/**
		 * Abre la aplicacion y comprueba si el login es correcto
		 */
		public void open() {
			Display display = Display.getDefault();
			createContents();
			Login myLogin = new Login(shell, Utils.nombreProyecto);
			myLogin.setcLogin(new ControladorLogin(myLogin));
			Boolean goApp  = myLogin.open();
			if(goApp){
				shell.open();
				shell.layout();
				while (!shell.isDisposed()) {
					if (!display.readAndDispatch()) {
						display.sleep();
					}
				}
			}
		}
		
		/** Imagen del boton  de los mantenimientos**/
		private Image buttonImage;
		/** Imagen del botón de salir **/
		private Image buttonSalir;
		/** Imagen del shell **/
		private Image shellImg;
		/** Color azul de los labels y en general de todo el ayuntamiento de torrent **/
		private Color azul;
		/** Fuente nombrada titulo1**/
		private Font titulo1;
		
		
		CLabel lblMantenimientoEjemplo;
		CLabel lblMantenimientoUsuarios;
		private Button btnEjemplo1Mantenimiento;
		private Button btnMantenimientopDeUsuarios;
		
		/**
		 * Crea el contenido de la aplicacion
		 */
		protected void createContents() {
			shell = new Shell(SWT.CLOSE | SWT.MIN | SWT.TITLE);
			shell.addShellListener(new ShellAdapter() {
				@Override
				public void shellClosed(ShellEvent e) {
					cInicio.close();
				}
			});
			shell.setSize(444, 176);
			shellImg = SWTResourceManager.getImage(Inicio.class, Recursos.shellPath);
			shell.setImage(shellImg);
			azul = SWTResourceManager.getColor(Recursos.AT_COLOR_AZUL);
			shell.setText(Utils.nombreProyecto + "- Inicio");
			
			buttonImage = SWTResourceManager.getImage(Inicio.class, Recursos.imgBotonPath);
			buttonSalir = SWTResourceManager.getImage(Inicio.class, Recursos.imgSalirPath);
			titulo1 = SWTResourceManager.getFont(Recursos.nombreFuenteLabelTitulo1,
					Recursos.tamanoFuenteLabelTitulo1,
					Recursos.estiloFuenteLabelTitulo1);
			
			shell.setLayout(null);
			
			Menu menuBar = new Menu(shell, SWT.BAR);
			shell.setMenuBar(menuBar);
			
			MenuItem mntmMantenimiento = new MenuItem(menuBar, SWT.CASCADE);
			mntmMantenimiento.setText("Mantenimiento");
			
			Menu menu_1 = new Menu(mntmMantenimiento);
			mntmMantenimiento.setMenu(menu_1);
			
			MenuItem mntmUsuarios = new MenuItem(menu_1, SWT.NONE);
			mntmUsuarios.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					cInicio.cargarMantenimientoUsuarios();
				}
			});
			mntmUsuarios.setText("EjemploDeMantenimiento\tCtrl+U");
			mntmUsuarios.setAccelerator(SWT.MOD1 + 'U');
			
			new MenuItem(menu_1, SWT.SEPARATOR);
			
			MenuItem mntmSalir = new MenuItem(menu_1, SWT.NONE);
			mntmSalir.setText("Salir");
			mntmSalir.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					cInicio.close();
				}
			});
			
			MenuItem mntmInformes = new MenuItem(menuBar, SWT.CASCADE);
			mntmInformes.setText("Informes");
			
			Menu menu = new Menu(mntmInformes);
			mntmInformes.setMenu(menu);
			
			MenuItem mntmInformeUno = new MenuItem(menu, SWT.NONE);
			mntmInformeUno.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					cInicio.cargarInforme();
				}
			});
			mntmInformeUno.setText("Informe1\tCtrl+F1");
			mntmInformeUno.setAccelerator(SWT.MOD1 + SWT.F1);
			
			btnEjemplo1Mantenimiento = new Button(shell, SWT.CENTER);
			btnEjemplo1Mantenimiento.setBounds(10, 10, 32, 32);
			btnEjemplo1Mantenimiento.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					cInicio.cargarMantenimiento();
				}
			});
			btnEjemplo1Mantenimiento.setImage(buttonImage);
			
			
			lblMantenimientoEjemplo = new CLabel(shell, SWT.NONE);
			lblMantenimientoEjemplo.setBounds(48, 10, 309, 32);
			lblMantenimientoEjemplo.setText("EjemploDeMantenimiento");
			lblMantenimientoEjemplo.setForeground(azul);
			lblMantenimientoEjemplo.setFont(titulo1);
			
			btnMantenimientopDeUsuarios = new Button(shell, SWT.CENTER);
			btnMantenimientopDeUsuarios.setBounds(10, 48, 32, 32);
			btnMantenimientopDeUsuarios.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					cInicio.cargarMantenimientoUsuarios();
				}
			});
			btnMantenimientopDeUsuarios.setImage(buttonImage);
			
			lblMantenimientoUsuarios = new CLabel(shell, SWT.NONE);
			lblMantenimientoUsuarios.setBounds(48, 48, 309, 32);
			lblMantenimientoUsuarios.setText("Mantenimiento de usuarios");
			lblMantenimientoUsuarios.setForeground(azul);
			lblMantenimientoUsuarios.setFont(titulo1);

			final Button btnSalir = new Button(shell, SWT.NONE);
			btnSalir.setBounds(363, 86, 68, 32);
			btnSalir.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					shell.dispose();
				}
			});
			btnSalir.setImage(buttonSalir);
			inicializar();
		}

		/**
		 * Aqui se liberan los recursos que hemos inicializado en inicio
		 * Por norma general solo liberaremos Imagenes, fuentes y colores.
		 */
		public void dispose(){
			buttonImage.dispose();
			buttonSalir.dispose();
			titulo1.dispose();
			shellImg.dispose();
			azul.dispose();
		}
		
		/**
		 * Inicializamos otros contenidos como shortctus
		 * 
		 * En este caso solo el toolTip text de aquello que tenga accesos directos.
		 * En la pantalla de inicio se usan accelerators definidos en los menuItem
		 */
		public void inicializar(){
			lblMantenimientoEjemplo.setToolTipText(Recursos.generarATorrentTooltipTextShortcutLabel("Ctrl + B"));
			btnEjemplo1Mantenimiento.setToolTipText(Recursos.generarATorrentTooltipTextShortcutButton("Ctrl + B"));
		}

		public ControladorInicio getcInicio() {
			return cInicio;
		}

		public void setcInicio(ControladorInicio cInicio) {
			this.cInicio = cInicio;
		}
		public Shell getShell() {
			return shell;
		}

		public void setShell(Shell shell) {
			this.shell = shell;
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

