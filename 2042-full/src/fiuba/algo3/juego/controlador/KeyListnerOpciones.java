package fiuba.algo3.juego.controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import fiuba.algo3.juego.controlador.operacionesAlgo.Controlable;
import fiuba.algo3.juego.controlador.operacionesJuego.BotonGControlable;
import fiuba.algo3.juego.controlador.operacionesJuego.BotonPControlable;


public class KeyListnerOpciones implements KeyListener {

	private HashMap<Integer, Controlable> hash;
	private ControladorJuegoAlgo42full controlador;


	public KeyListnerOpciones(ControladorJuegoAlgo42full ctrl) {
		controlador = ctrl;
		hash = this.cargarDiccionarioControles();
	}
	
	private HashMap<Integer, Controlable> cargarDiccionarioControles() {

		HashMap<Integer, Controlable> diccionario = new HashMap<Integer, Controlable>();
		Controlable botonGControlable = new BotonGControlable(controlador);
		Controlable botonPControlable = new BotonPControlable(controlador);
		
		diccionario.put(KeyEvent.VK_G, botonGControlable);
		diccionario.put(KeyEvent.VK_P, botonPControlable);
		return diccionario;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		int opcion = arg0.getKeyCode();
		if (hash.containsKey(opcion)) {
			Controlable tecla = hash.get(opcion);
			tecla.activarEfecto();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

}
