package fiuba.algo3.juego.programa;

import java.awt.Button;
import java.awt.Label;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaJuegoTerminado extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private Label labelPregunta = null;
	private Button buttonSi = null;
	private Button buttonNo = null;

	/**
	 * This is the default constructor
	 */
	public VentanaJuegoTerminado() {
		super();
		initialize();
	}
	
	public VentanaJuegoTerminado(String estado) {
		super();
		initialize();
		this.setTitle("Usted ha " + estado);
		
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 187);
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
			labelPregunta.setBounds(new Rectangle(73, 23, 137, 23));
			labelPregunta.setText("¿Desea volver a jugar?");
			jContentPane = new JPanel();
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
			buttonSi.setBounds(new Rectangle(11, 107, 174, 23));
			buttonSi.setLabel("Si, volver al menu principal.");
			buttonSi.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Volver al menu principal()");
					VentanaInicial ventanaInicial = new VentanaInicial();
					ventanaInicial.setVisible(true);
					ventanaInicial.setAlwaysOnTop(true);
					dispose();
				}
			});
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
			buttonNo.setBounds(new Rectangle(215, 107, 36, 23));
			buttonNo.setLabel("No.");
			buttonNo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("No quiero seguir jugando()");
					System.exit(0);
				}
			});
		}
		return buttonNo;
	}

}  //  @jve:decl-index=0:visual-constraint="10,23"
