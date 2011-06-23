package fiuba.algo3.juego.programa;

import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.controlador.ControladorJuegoAlgo42full;
import fiuba.algo3.juego.controlador.*;


public class Programa {

	/**
	 * @param args
	 * Inicializa el juego.
	 */
	public static void main(String[] args) {

		Plano plano = new Plano(550,570);
		GeneradorControlador generador = new GeneradorControlador(plano);
		ControladorJuegoAlgo42full controlador = generador.generarControlador();

		GeneradorFlota generadorFlota = new GeneradorFlota(plano);
		controlador.agregarObjetoVivo(generadorFlota);

		controlador.comenzarJuego();
	}

}
