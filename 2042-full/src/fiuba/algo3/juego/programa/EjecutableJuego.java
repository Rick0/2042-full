package fiuba.algo3.juego.programa;

import javax.swing.JFrame;
import fiuba.algo3.juego.vista.VentanaInicial;


public class EjecutableJuego implements Runnable {

	@Override
	public void run() {
		VentanaInicial v = new VentanaInicial();
		v.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		v.setVisible(true);
	}
	
	public static void main(String[] args) {
		EjecutableJuego e = new EjecutableJuego();
		e.run();
	}

}
