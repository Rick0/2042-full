package fiuba.algo3.juego.controlador;

import java.awt.event.KeyEvent;

import fiuba.algo3.juego.modelo.Algo42;
import fiuba.algo3.juego.modelo.excepciones.AreaInvalidaError;
import fiuba.algo3.titiritero.KeyPressedObservador;

public class ControladorMovimientosAlgo implements KeyPressedObservador{

	Algo42 algo;
	public ControladorMovimientosAlgo(Algo42 a){
		algo=a;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		if(event.isActionKey()){
			try {
				algo.moverAbajo();
			} catch (AreaInvalidaError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		
}

