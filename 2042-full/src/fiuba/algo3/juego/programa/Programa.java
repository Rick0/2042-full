package fiuba.algo3.juego.programa;

import fiuba.algo3.juego.controlador.GeneradorControlador;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.titiritero.ControladorJuego;

public class Programa {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Plano plano= new Plano(500,500);
		GeneradorControlador generador= new GeneradorControlador(plano);
		ControladorJuego controlador = generador.generarControlador();
		GeneradorFlotas generadorFlotas= new GeneradorFlotas(plano,controlador);
		while (true){
			if(!plano.devolverListaNaves().isEmpty()){
				controlador.comenzarJuego(1);
			}
			else{
				generadorFlotas.generarFlota1();
			}
		}
		
	}
}