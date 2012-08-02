package fiuba.algo3.juego.controlador.operacionesAlgo;

import fiuba.algo3.juego.modelo.Algo42;


public class BotonBarraControlable implements Controlable {

	Algo42 algo42;

	
	public BotonBarraControlable(Algo42 naveJugador) {
		algo42 = naveJugador;
	}

	/**Si el usuario oprime la tecla X, el algo42 lanzara un arma torpedo rastreador
	 * Como elije la primera nave en la lista de naves enemigas,
	 * esto crea la ilusion de que apunta contra la nave mas cercana a Algo42.
	 * @see fiuba.algo3.juego.controlador.operacionesAlgo.Controlable#activarEfecto()
	 */
	@Override
	public void activarEfecto() {

		this.algo42.cambiarModoAutomatico();
	}

}
