package vista.interfaz;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import at.controlador.IControlador;
import at.vista.IMensajes;
import at.vista.interfaz.ATDialogEscudo;
import at.vista.interfaz.Recursos;
import controlador.ControladorLogin;

/**
 * Clase que muestra la interfaz de login en el dominio de Atorrent.
 * Para que funcione correctamente hay que hacer un par de cambios.
 * En DBConexión tiene que indicarse la dirección de la base de datos.
 * En dicha base de datos se tiene que crear una tabla de nombre
 * usuariospermitidos y con al menos tres campos: 
 * 	idUsuarioPermitido, nombreUsuario y permisos
 * 
 * @author brullp
 *
 */
public class Login extends ATDialogEscudo implements IMensajes, IControlador {
	/** Controlador que maneja la logica de la interfaz **/
	private ControladorLogin cLogin;

	private Shell shell;
	private Boolean result;
	
	private Text textUsuari;
	private Text textContrasenya;
	
	private Font titulo1;
	private Image buttonSalir;
	private Button btnEntrar;
	
	/**
	 * Crea el dialog para el login
	 * @param parent
	 * @param Application
	 */
	public Login(Shell parent, String title) {
		super(parent, "Login de dominio" , title);
		titulo1 = SWTResourceManager.getFont(
				Recursos.nombreFuenteLabelTitulo1,
				Recursos.tamanoFuenteLabelTitulo1,
				Recursos.estiloFuenteLabelTitulo1);
		buttonSalir = SWTResourceManager.getImage(Login.class, Recursos.imgSalirPath);
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Boolean open() {
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
		shell.setSize(450, 260);
		super.createContents(shell);
		
		Composite compositeMain = new Composite(shell, SWT.NONE);
		compositeMain.setBounds(130, 64, 314, 164);
		
		Label lblUsuari = new Label(compositeMain, SWT.NONE);
		lblUsuari.setBounds(10, 10, 119, 23);
		lblUsuari.setText("Usuari: ");
		lblUsuari.setFont(titulo1);
		lblUsuari.setForeground(getAzul());
		
		Label labelContrasenya = new Label(compositeMain, SWT.NONE);
		labelContrasenya.setText("Contrasenya:");
		labelContrasenya.setBounds(10, 39, 126, 23);
		labelContrasenya.setForeground(getAzul());
		labelContrasenya.setFont(titulo1);
		
		textUsuari = new Text(compositeMain, SWT.BORDER);
		textUsuari.setBounds(142, 10, 162, 23);
		
		textContrasenya = new Text(compositeMain, SWT.BORDER| SWT.PASSWORD);
		textContrasenya.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR){
					cLogin.entrar();
				}
			}
		});
		textContrasenya.setBounds(142, 39, 162, 23);
		
		btnEntrar = new Button(compositeMain, SWT.NONE);
		btnEntrar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cLogin.entrar();
			}
		});
		btnEntrar.setBounds(65, 79, 102, 35);
		btnEntrar.setText("Entrar");
		
		Button btnSalir = new Button(compositeMain, SWT.NONE);
		btnSalir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cLogin.salir();
			}
		});
		btnSalir.setBounds(202, 79, 102, 35);
		btnSalir.setImage(buttonSalir);
		
		shell.setDefaultButton(btnEntrar);
	}
	
	@Override
	protected void myDispose() {
		buttonSalir.dispose();
		titulo1.dispose();
	}
	
	//GETTERS AND SETTERS
	
	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public String getStringTextUsuari() {
		return textUsuari.getText().trim();
	}

	public void setStringTextUsuari(String textUsuari) {
		this.textUsuari.setText(textUsuari);
	}

	public String getStringTextContrasenya() {
		return textContrasenya.getText().trim();
	}

	public void setStringTextContrasenya(Text textContrasenya) {
		this.textContrasenya = textContrasenya;
	}

	public ControladorLogin getCLogin() {
		return cLogin;
	}

	public void setcLogin(ControladorLogin cLogin) {
		this.cLogin = cLogin;
	}

	@Override
	public void openError(String cabecera, String mensaje) {
		MessageDialog.openError(
				shell,
				cabecera,
				mensaje);
	}

	@Override
	public void openInformation(String cabecera, String mensaje) {
		MessageDialog.openInformation(
				shell,
				cabecera,
				mensaje);
	}

	@Override
	public int openQuestion(String cabecera, String mensaje, String[] opciones) {
		MessageDialog dialog = new MessageDialog(
				shell, 
				cabecera, 
				null,
				mensaje,
				MessageDialog.QUESTION,
				opciones,
				0 );
		return dialog.open();
	}

	@Override
	public void inicializar() {
		
	}

	@Override
	public void salir() {
		shell.dispose();
	}

	@Override
	public void visibilidadBtn() {
	}

}
