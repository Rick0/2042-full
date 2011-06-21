package fiuba.algo3.juego.vista;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import fiuba.algo3.juego.controlador.ControladorJuegoAlgo42full;


public class KeyPressedController extends KeyAdapter implements KeyListener {
	
	private ControladorJuegoAlgo42full controlador;
	
	public KeyPressedController(ControladorJuegoAlgo42full controlador){
		this.controlador = controlador;
	}

	public void keyPressed(KeyEvent e) {
		this.controlador.despacharKeyPress(e);
	}

	public void keyReleased(KeyEvent e) {
		this.controlador.despacharKeyPress(e);
	}

}
