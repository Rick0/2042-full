package fiuba.algo3.juego.controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ConcurrentModificationException;

import fiuba.algo3.juego.modelo.Algo42;
import fiuba.algo3.juego.modelo.excepciones.AreaInvalidaError;

public class KeyListenerAlgo42 implements KeyListener {

	Algo42 algo;
	
	public KeyListenerAlgo42(Algo42 algo42){
		algo=algo42;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		int opcion=arg0.getKeyCode();
		if(opcion==KeyEvent.VK_DOWN){
			//Abajo
			try {
				algo.moverAbajo();
			} catch (AreaInvalidaError e) {
				System.out.print("Area invalida");
				// Si hay error de area invalida, que no se mueva.
			}
		}
		if(opcion==KeyEvent.VK_UP){
		//Arriba
			try {
				algo.moverArriba();
			} catch (AreaInvalidaError e) {
				System.out.print("Area invalida");
				// Si hay error de area invalida, que no se mueva.
			}
		}
		if(opcion==KeyEvent.VK_LEFT){
			//izquierda
			try {
				algo.moverIzquierda();
			} catch (AreaInvalidaError e) {
				System.out.print("Area invalida");
				// Si hay error de area invalida, que no se mueva.
			}
		}
		if(opcion==KeyEvent.VK_RIGHT){
			//Derecha
			try {
				algo.moverDerecha();
			} catch (AreaInvalidaError e) {
				System.out.print("Area invalida");
				// Si hay error de area invalida, que no se mueva.
			}
		}
		if(opcion==KeyEvent.VK_SPACE){
			//Disparo
			try{
				algo.dispararLaser();
			}
			catch(ConcurrentModificationException e){
				algo.dispararLaser();
			}
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
