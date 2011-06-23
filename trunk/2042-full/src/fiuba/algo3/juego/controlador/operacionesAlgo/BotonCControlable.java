package fiuba.algo3.juego.controlador.operacionesAlgo;

import fiuba.algo3.juego.modelo.Algo42;


public class BotonCControlable implements Controlable {

	Algo42 algo;

	
	public BotonCControlable(Algo42 algo42) {
		algo = algo42;
	}

	/** Si el usuario oprime la tecla C, el algo42 lanzara un arma laser
	 * @see fiuba.algo3.juego.controlador.operacionesAlgo.Controlable#activarEfecto()
	 */
	@Override
	public void activarEfecto() {
		algo.dispararLaser();
	}

}
