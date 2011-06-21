package fiuba.algo3.juego.controlador.operacionesAlgo;

import fiuba.algo3.juego.modelo.Algo42;
import fiuba.algo3.juego.modelo.excepciones.AreaInvalidaError;


public class FlechaDerechaControlable implements Controlable{

	Algo42 algo42;


	public FlechaDerechaControlable(Algo42 algo) {
		algo42=algo;
	}

	/*El algo42 se mueve a la derecha*/
	@Override
	public void activarEfecto() {
		try {
			algo42.moverDerecha();
		} catch (AreaInvalidaError e) {
			System.out.print("Area invalida\n");
			// Si hay error de area invalida, que no se mueva.
		}		
	}

}