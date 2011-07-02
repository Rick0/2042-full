package fiuba.algo3.juego.programa;

import javax.swing.JFrame;

public class EjecutableJuego implements Runnable{

	@Override
	public void run() {
		VentanaInicial v= new VentanaInicial();
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.setVisible(true);
	}

}
