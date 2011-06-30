package fiuba.algo3.juego.controlador.operacionesJuego;

import fiuba.algo3.juego.controlador.ControladorJuegoAlgo42full;
import fiuba.algo3.juego.controlador.operacionesAlgo.Controlable;

public class BotonPControlable implements Controlable {
	ControladorJuegoAlgo42full controlador;
	
	public BotonPControlable(ControladorJuegoAlgo42full ctrl) {
		controlador = ctrl;
	}
	
	@Override
	public void activarEfecto() {
		if ( controlador.estaEnEjecucion() ) {
			controlador.detenerJuego();
		} else
			controlador.comenzarJuegoAsyn();
		
	}

}
