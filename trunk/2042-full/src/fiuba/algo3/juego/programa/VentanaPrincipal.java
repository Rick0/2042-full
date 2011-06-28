package fiuba.algo3.juego.programa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fiuba.algo3.juego.controlador.ControladorJuegoAlgo42full;
import fiuba.algo3.juego.controlador.GeneradorControlador;
import fiuba.algo3.juego.controlador.GeneradorFlota;
import fiuba.algo3.juego.modelo.Plano;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jPanel = null;
	private JButton jButtonJuegoNuevo = null;
	private JButton jButtonRestaurar = null;
	private JButton jButtonMaximos = null;
	private JLabel jLabelTitulo = null;

	/**
	 * This is the default constructor
	 */
	public VentanaPrincipal() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(400, 332);
		this.setContentPane(getJContentPane());
		this.setTitle("Algo 2042");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJPanel(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabelTitulo = new JLabel();
			jLabelTitulo.setBounds(new Rectangle(27, 15, 332, 61));
			jLabelTitulo.setFont(new Font("Arial", 2, 48));
			jLabelTitulo.setText("Algo 2042 Full");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBackground(Color.WHITE);
			jPanel.add(getJButtonJuegoNuevo(), null);
			jPanel.add(getJButtonRestaurar(), null);
			jPanel.add(getJButtonMaximos(), null);
			jPanel.add(jLabelTitulo, null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jButtonJuegoNuevo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonJuegoNuevo() {
		if (jButtonJuegoNuevo == null) {
			jButtonJuegoNuevo = new JButton();
			jButtonJuegoNuevo.setBounds(new Rectangle(112, 105, 153, 26));
			jButtonJuegoNuevo.setText("Juego Nuevo");
			jButtonJuegoNuevo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Comenzar Juego");
					Plano plano = new Plano(550,570);
					GeneradorControlador generador = new GeneradorControlador(plano);
					ControladorJuegoAlgo42full controlador = generador.generarControlador();

					GeneradorFlota generadorFlota = new GeneradorFlota(plano);
					controlador.agregarObjetoVivo(generadorFlota);

					controlador.comenzarJuego();
				}
			});
		}
		return jButtonJuegoNuevo;
	}

	/**
	 * This method initializes jButtonRestaurar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonRestaurar() {
		if (jButtonRestaurar == null) {
			jButtonRestaurar = new JButton();
			jButtonRestaurar.setBounds(new Rectangle(112, 153, 153, 26));
		}
		return jButtonRestaurar;
	}

	/**
	 * This method initializes jButtonMaximos	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMaximos() {
		if (jButtonMaximos == null) {
			jButtonMaximos = new JButton();
			jButtonMaximos.setBounds(new Rectangle(112, 200, 153, 26));
			jButtonMaximos.setText("Salir");
			jButtonMaximos.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Salir");
					System.exit( 0 );
				}
			});
		}
		return jButtonMaximos;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
