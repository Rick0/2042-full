package fiuba.algo3.juego.controlador.operacionesAlgo;

import fiuba.algo3.juego.modelo.Algo42;
import fiuba.algo3.juego.modelo.excepciones.AreaInvalidaError;

public class FlechaAbajoControlable implements Controlable {

	Algo42 algo;
	public FlechaAbajoControlable(Algo42 algo42) {
		algo=algo42;
	}

	@Override
	public void activarEfecto() {
		try {
			algo.moverAbajo();
		} catch (AreaInvalidaError e) {
			System.out.print("Area invalida");
			// Si hay error de area invalida, que no se mueva.
		}

	}

}
