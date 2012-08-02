package fiuba.algo3.juego.controlador.operacionesAlgo;

import fiuba.algo3.juego.modelo.Algo42;
import fiuba.algo3.juego.modelo.excepciones.AreaInvalidaError;


public class FlechaDerechaControlable implements Controlable{

	Algo42 algo;


	public FlechaDerechaControlable(Algo42 algo42) {
		algo = algo42;
	}

	@Override
	public void activarEfecto() {
	
		this.algo.cambiarEfectoAutomaticoMover(3);
		if (!this.algo.estadoModoAutomatico()) {
			try {
				algo.moverDerecha();
			} catch (AreaInvalidaError e) {
				System.out.print("Area invalida\n");
				// Si hay error de area invalida, que no se mueva.
			}
		}
	}

}