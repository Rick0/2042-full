package fiuba.algo3.juego.programa;

import fiuba.algo3.juego.controlador.GeneradorControlador;
import fiuba.algo3.titiritero.ControladorJuego;

public class Programa {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GeneradorControlador generador= new GeneradorControlador();
		ControladorJuego controlador = generador.generarControlador();
		controlador.comenzarJuego();
	}
}