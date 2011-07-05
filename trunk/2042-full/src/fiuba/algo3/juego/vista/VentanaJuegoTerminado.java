package fiuba.algo3.juego.vista;

import java.awt.Button;
import java.awt.Label;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class VentanaJuegoTerminado extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private Label labelPregunta = null;
	private Button buttonSi = null;
	private Button buttonNo = null;
	private String estado= null;


	/**
	 * This is the default constructor
	 */
	public VentanaJuegoTerminado() {
		super();
		initialize();
	}
	
	public VentanaJuegoTerminado(String estadoJuego) {
		super();
		estado= estadoJuego;
		initialize();
		this.setTitle("Usted ha " + estado);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(550, 570);
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			labelPregunta = new Label();
			labelPregunta.setBounds(new java.awt.Rectangle(213,116,137,23));
			labelPregunta.setText("Desea volver a jugar?");
			if(estado=="ganado!!!"){
				jContentPane = new JPanelConFondo("recursos/PantallaPrincipal/ganaste.jpg");
			}
			else{
				jContentPane= new JPanelConFondo("recursos/PantallaPrincipal/perdiste.jpg");
			}
			jContentPane.setLayout(null);
			jContentPane.add(labelPregunta, null);
			jContentPane.add(getButtonSi(), null);
			jContentPane.add(getButtonNo(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes buttonSi	
	 * 	
	 * @return java.awt.Button	
	 */
	private Button getButtonSi() {
		if (buttonSi == null) {
			buttonSi = new Button();
			buttonSi.setBounds(new java.awt.Rectangle(164,174,174,23));
			buttonSi.setLabel("Si, volver al menu principal.");
			buttonSi.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Volver al menu principal()");
					VentanaInicial ventanaInicial = new VentanaInicial();
					ventanaInicial.setVisible(true);
					ventanaInicial.setAlwaysOnTop(true);
					dispose();
				}
			} );
		}
		return buttonSi;
	}

	/**
	 * This method initializes buttonNo	
	 * 	
	 * @return java.awt.Button	
	 */
	private Button getButtonNo() {
		if (buttonNo == null) {
			buttonNo = new Button();
			buttonNo.setBounds(new java.awt.Rectangle(347,174,36,23));
			buttonNo.setLabel("No.");
			buttonNo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("No quiero seguir jugando()");
					System.exit(0);
				}
			} );
		}
		return buttonNo;
	}

}  //  @jve:decl-index=0:visual-constraint="10,23"
