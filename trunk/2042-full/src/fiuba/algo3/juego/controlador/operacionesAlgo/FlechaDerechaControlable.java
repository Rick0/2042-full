package fiuba.algo3.juego.controlador.operacionesAlgo;

import fiuba.algo3.juego.modelo.Algo42;


public class FlechaDerechaControlable implements Controlable{

	Algo42 algo;


	public FlechaDerechaControlable(Algo42 algo42) {
		algo = algo42;
	}

	@Override
	public void activarEfecto() {
	
		this.algo.cambiarEfectoAutomaticoMover(3);
		if (!this.algo.estadoModoAutomatico())
			algo.moverDerecha();
	}

}