package fiuba.algo3.juego.controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import fiuba.algo3.juego.controlador.operacionesAlgo.BotonCControlable;
import fiuba.algo3.juego.controlador.operacionesAlgo.BotonXControlable;
import fiuba.algo3.juego.controlador.operacionesAlgo.BotonZControlable;
import fiuba.algo3.juego.controlador.operacionesAlgo.BotonSControlable;
import fiuba.algo3.juego.controlador.operacionesAlgo.Controlable;
import fiuba.algo3.juego.modelo.Algo42;


public class KeyListenerAlgo42Armas implements KeyListener {

	HashMap<Integer, Controlable> hash;
	Algo42 algo;


	public KeyListenerAlgo42Armas(Algo42 algo42){
		algo = algo42;
		hash = cargarDiccionarioControles();
	}

	@Override
	public synchronized void keyPressed(KeyEvent arg0) {
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

	private HashMap<Integer, Controlable> cargarDiccionarioControles() {

		HashMap<Integer, Controlable> diccionario = new HashMap<Integer, Controlable>();

		Controlable botonCControlable = new BotonCControlable(algo);
		Controlable botonXControlable = new BotonXControlable(algo);
		Controlable botonZControlable = new BotonZControlable(algo);
		Controlable botonSControlable = new BotonSControlable(algo);
		
		diccionario.put(KeyEvent.VK_C, botonCControlable);
		diccionario.put(KeyEvent.VK_X, botonXControlable);
		diccionario.put(KeyEvent.VK_Z, botonZControlable);
		diccionario.put(KeyEvent.VK_S, botonSControlable);

		return diccionario;
	}

}
