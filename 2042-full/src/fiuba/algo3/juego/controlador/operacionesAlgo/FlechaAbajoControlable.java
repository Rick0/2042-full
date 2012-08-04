package fiuba.algo3.juego.controlador.operacionesAlgo;

import fiuba.algo3.juego.modelo.Algo42;


public class FlechaAbajoControlable implements Controlable {

	Algo42 algo;


	public FlechaAbajoControlable(Algo42 algo42) {
		algo = algo42;
	}

	@Override
	public void activarEfecto() {
	
		this.algo.cambiarEfectoAutomaticoMover(1);
		if (!this.algo.estadoModoAutomatico())
			algo.moverAbajo();
	}

}
