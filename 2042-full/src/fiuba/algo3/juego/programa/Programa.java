package fiuba.algo3.juego.programa;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Programa {

	/**
	 * @param args
	 * Inicializa el juego.
	 */
	public static void main(String[] args) {
		
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				VentanaInicial v= new VentanaInicial();
				v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				v.setVisible(true);
			}
		});
	}
}
