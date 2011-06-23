package fiuba.algo3.juego.vista;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import fiuba.algo3.juego.controlador.ControladorJuegoAlgo42full;
import fiuba.algo3.juego.modelo.*;


public class VentanaAplicacion extends JFrame{

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JToolBar jJToolBarBar = null;
    private JMenuItem jMenuItem = null;
    private JMenuItem jMenuItem1 = null;
   // private JMenuItem jMenuItem2 = null;
//    private JMenuItem jMenuItem3 = null;
    private Panel panel = null;
    private Panel inicio = null;
	private Plano planoJuego = null;
    private ControladorJuegoAlgo42full controladorJuego = null;


    /**This method initializes jJToolBarBar  
     * @return javax.swing.JToolBar 
     */
    private JToolBar getJJToolBarBar() {
		if (jJToolBarBar == null) {
			jJToolBarBar = new JToolBar();
			jJToolBarBar.setBounds(new Rectangle(0, 0, 400, 33));
			jJToolBarBar.add(getJMenuItem());
			jJToolBarBar.add(getJMenuItem1());
		//	jJToolBarBar.add(getJMenuItem2());
		//	jJToolBarBar.add(getJMenuItem3());
		}
		return jJToolBarBar;
    }

    /**This method initializes jMenuItem    
     * @return javax.swing.JMenuItem        
     */
    private JMenuItem getJMenuItem() {
		if (jMenuItem == null) {
			jMenuItem = new JMenuItem();
			jMenuItem.setText("Comenzar");
			jMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// System.out.println("actionPerformed()");
					// TODO Auto-generated Event stub actionPerformed()
				}
			} );
			jMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					comenzar();
				}
			} );
		}
		return jMenuItem;
    }

    private void comenzar(){
		System.out.println("actionPerformed()");
		// TODO Auto-generated Event stub actionPerformed()
		jContentPane.remove(inicio);
		this.controladorJuego = new ControladorJuegoAlgo42full(false, this.planoJuego);
		this.controladorJuego.comenzarJuego();
    }

    private void detener() {
            this.controladorJuego.detenerJuego();         
    }
    
 /*   private void guardar() {
        this.controladorJuego.guardar();         
    }
    
    private void cargar() {
    	jContentPane.remove(inicio);
    	this.controladorJuego = new ControladorJuegoAlgo42full(this.getSuperficieDeDibujo());
        this.controladorJuego.cargar(this.getSuperficieDeDibujo());   
    }*/

    /**This method initializes jMenuItem1 
     * @return javax.swing.JMenuItem        
     */
    private JMenuItem getJMenuItem1() {
		if (jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem();
			jMenuItem1.setText("Detener");
			jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// System.out.println("actionPerformed()");
					// TODO Auto-generated Event stub actionPerformed()
					detener();
				}
			});
		}
		return jMenuItem1;
    }

    /**This method initializes jMenuItem2   
     * @return javax.swing.JMenuItem        
     */
 /*   private JMenuItem getJMenuItem2() {
		if (jMenuItem2 == null) {
			jMenuItem2 = new JMenuItem();
			jMenuItem2.setText("Cargar");
			jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// System.out.println("actionPerformed()");
					// TODO Auto-generated Event stub actionPerformed()
					cargar();
				}
			});
		}
		return jMenuItem2;
    }*/

    /**This method initializes jMenuItem3  
     * @return javax.swing.JMenuItem        
     */
/*    private JMenuItem getJMenuItem3() {
		if (jMenuItem3 == null) {
			jMenuItem3 = new JMenuItem();
			jMenuItem3.setText("Guardar");
			jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// System.out.println("actionPerformed()");
					// TODO Auto-generated Event stub actionPerformed()
					guardar();
				}
			});
		}
		return jMenuItem3;
    }*/

    /**This method initializes jPanel
     * @return javax.swing.JPanel   
     */
    private Panel getSuperficieDeDibujo() {
    	if (panel == null) {
    		panel = new Panel(550,570);
    		panel.setLayout(new GridBagLayout());
    		panel.setBounds(new Rectangle(0,0,410,480));
    	}
    	return panel;
    }

    private Panel imagenInicial(){
        if (inicio == null) {
        	inicio = new Panel(550,570);
        	inicio.setLayout(new GridBagLayout());
            inicio.setBounds(new Rectangle(0, 38, 400, 460));
    //        inicio.setImagen("/vista/recursos/PantallaPrincipal/pantallaPrincipal.png");
        }     
        return inicio;
    }

    /**This is the default constructor
     */
    public VentanaAplicacion() {
		super();
		initialize();
    }

    /**This method initializes this
     * @return void
     */
    private void initialize() {
		this.setSize(550, 600);
		this.setContentPane(getJContentPane());
		this.setTitle("Algo42 - Full");

		this.planoJuego = new Plano(550, 570);
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.out.println("windowClosing()");
				// TODO Auto-generated Event stub windowClosing()
				System.exit(NORMAL);
			}
		} );
    }

    /**This method initializes jContentPane
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {

		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJJToolBarBar(), null);
			jContentPane.add(imagenInicial(), null);               
			jContentPane.add(getSuperficieDeDibujo(), null);
		}

		return jContentPane;
    }

}  //  @jve:decl-index=0:visual-constraint="10,10"
