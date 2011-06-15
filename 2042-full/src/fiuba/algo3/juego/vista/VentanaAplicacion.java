package fiuba.algo3.juego.vista;

import java.awt.GridBagLayout;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import fiuba.algo3.juego.controlador.ejemploAlgo;
import fiuba.algo3.titiritero.vista.Panel;

public class VentanaAplicacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jJToolBarBar = null;
	private JMenuItem jMenuItem = null;
	private JMenuItem jMenuItem1 = null;
	private Panel panel = null;
	private ejemploAlgo ejemplo = null;

	/**
	 * This method initializes jJToolBarBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJJToolBarBar() {
		if (jJToolBarBar == null) {
			jJToolBarBar = new JToolBar();
			jJToolBarBar.setBounds(new Rectangle(0, 0, 372, 33));
			jJToolBarBar.add(getJMenuItem());
			jJToolBarBar.add(getJMenuItem1());
		}
		return jJToolBarBar;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem() {
		if (jMenuItem == null) {
			jMenuItem = new JMenuItem();
			jMenuItem.setText("Comenzar");
			jMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
			jMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					comenzar();
				}
			});
		}
		return jMenuItem;
	}

	private void comenzar(){
		System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
		this.ejemplo = new ejemploAlgo(getSuperficieDeDibujo());
		this.ejemplo.comenzar();
	}
	
	private void detener() {
		this.ejemplo.detener();		
	}

	/**
	 * This method initializes jMenuItem1	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem1() {
		if (jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem();
			jMenuItem1.setText("Detener");
			jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					detener();
				}
			});
		}
		return jMenuItem1;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private Panel getSuperficieDeDibujo() {
		if (panel == null) {
			panel = new Panel(400,400);
			panel.setLayout(new GridBagLayout());
			//panel.setBounds(new Rectangle(10, 43, 267, 114));
			panel.setBounds(new Rectangle(38, 38, 400, 400));
		}
		return panel;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
			// TODO Auto-generated method stub
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					VentanaAplicacion thisClass = new VentanaAplicacion();
					thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					thisClass.setVisible(true);
				}
			});
		}


	/**
	 * This is the default constructor
	 */
	public VentanaAplicacion() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(500, 500);
		this.setContentPane(getJContentPane());
		this.setTitle("Ejemplo algo");
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.out.println("windowClosing()"); // TODO Auto-generated Event stub windowClosing()
				System.exit(NORMAL);
			}
		});
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJJToolBarBar(), null);
			jContentPane.add(getSuperficieDeDibujo(), null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
