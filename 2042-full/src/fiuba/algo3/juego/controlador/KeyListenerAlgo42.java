package fiuba.algo3.juego.controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import fiuba.algo3.juego.controlador.operacionesAlgo.BarraControlable;
import fiuba.algo3.juego.controlador.operacionesAlgo.Controlable;
import fiuba.algo3.juego.controlador.operacionesAlgo.FlechaAbajoControlable;
import fiuba.algo3.juego.controlador.operacionesAlgo.FlechaArribaControlable;
import fiuba.algo3.juego.controlador.operacionesAlgo.FlechaDerechaControlable;
import fiuba.algo3.juego.controlador.operacionesAlgo.FlechaIzquierdaControlable;
import fiuba.algo3.juego.modelo.Algo42;

public class KeyListenerAlgo42 implements KeyListener {

	HashMap<Integer, Controlable> hash;
	Algo42 algo;
	
	public KeyListenerAlgo42(Algo42 algo42){
		algo=algo42;
		hash= cargarDiccionarioControles();
	}
	
	private HashMap<Integer, Controlable> cargarDiccionarioControles(){
		HashMap<Integer, Controlable> diccionario= new HashMap<Integer, Controlable>();
		Controlable flechaDerecha= new FlechaDerechaControlable(algo);
		Controlable flechaIzquierda= new FlechaIzquierdaControlable(algo);
		Controlable flechaAbajo= new FlechaAbajoControlable(algo);
		Controlable flechaArriba= new FlechaArribaControlable(algo);
		Controlable barraEspaciadora= new BarraControlable(algo);
		diccionario.put(KeyEvent.VK_DOWN, flechaAbajo);
		diccionario.put(KeyEvent.VK_UP, flechaArriba);
		diccionario.put(KeyEvent.VK_RIGHT, flechaDerecha);
		diccionario.put(KeyEvent.VK_LEFT, flechaIzquierda);
		diccionario.put(KeyEvent.VK_SPACE, barraEspaciadora);
		return diccionario;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		int opcion=arg0.getKeyCode();
		Controlable tecla= hash.get(opcion);
		tecla.activarEfecto();
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
