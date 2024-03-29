package fiuba.algo3.juego.vista;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import fiuba.algo3.juego.controlador.ControladorJuegoAlgo42full;
import fiuba.algo3.juego.controlador.GeneradorControlador;
import fiuba.algo3.juego.modelo.GeneradorFlota;
import fiuba.algo3.juego.modelo.Plano;


public class VentanaInicial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jPanel = null;
	private JButton jButtonJuegoNuevo = null;
	private JButton jButtonRestaurar = null;
	private JButton jButtonSalir = null;


	/**
	 * This is the default constructor
	 */
	public VentanaInicial() {
		super();
		initialize();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("Saliendo del Programa");
				System.exit(0);
			}
		} );
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(550, 570);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Algo 2042 Full");
		this.setIconImage(new ImageIcon("/recursos/Icono.gif").getImage());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanelConFondo("recursos/PantallaPrincipal/pantallaPrincipal.jpg");
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
			jPanel = new JPanelConFondo("recursos/PantallaPrincipal/pantallaPrincipal.jpg");
			jPanel.setLayout(null);
			jPanel.add(getJButtonJuegoNuevo(), null);
			jPanel.add(getJButtonRestaurar(), null);
			jPanel.add(getJButtonSalir(), null);
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
			jButtonJuegoNuevo.setText("Juego Nuevo");
			jButtonJuegoNuevo.setBounds(new java.awt.Rectangle(239,402,113,25));
			jButtonJuegoNuevo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Comenzar Juego");
					Plano plano = new Plano(550,570);
					GeneradorControlador generador = new GeneradorControlador(plano);
					ControladorJuegoAlgo42full controlador = generador.generarControlador();
					GeneradorFlota generadorFlota = new GeneradorFlota(plano);
					controlador.agregarObjetoVivo(generadorFlota);

					controlador.comenzarJuegoAsyn();
					dispose();
				}
			} );
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
			jButtonRestaurar.setText("Restaurar");
			jButtonRestaurar.setBounds(new java.awt.Rectangle(239,442,113,25));
			jButtonRestaurar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Restaurar()");
					
					GeneradorControlador generadorControlador = new GeneradorControlador(Plano.restaurar("savegame.dat"));
					ControladorJuegoAlgo42full controlador = generadorControlador.generarControladorAPartirDePlano();
					GeneradorFlota generadorFlota = new GeneradorFlota(controlador.getPlano());
					controlador.agregarObjetoVivo(generadorFlota);
					controlador.comenzarJuegoAsyn();
					dispose();
				}
			} );
		}
		return jButtonRestaurar;
	}

	/**
	 * This method initializes jButtonSalir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonSalir() {
		if (jButtonSalir == null) {
			jButtonSalir = new JButton();
			jButtonSalir.setText("Salir");
			jButtonSalir.setBounds(new java.awt.Rectangle(239,482,113,25));
			jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Salir");
					System.exit( 0 );
				}
			} );
		}
		return jButtonSalir;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
