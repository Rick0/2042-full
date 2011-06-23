package fiuba.algo3.juego.programa;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import fiuba.algo3.juego.vista.VentanaAplicacion;


public class ProgramaPrueba {


	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				VentanaAplicacion unaVentana = new VentanaAplicacion();
				unaVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				unaVentana.setVisible(true);
			}
		} );
	}

}
