package fiuba.algo3.juego.programa;

import fiuba.algo3.juego.controlador.GeneradorControlador;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.titiritero.ControladorJuego;

public class Programa {

	/**
	 * @param args
	 * Inicializa el juego.
	 */
	public static void main(String[] args) {
		Plano plano= new Plano(500,500);
		GeneradorControlador generador= new GeneradorControlador(plano);
		ControladorJuego controlador = generador.generarControlador();
		GeneradorFlotas generadorFlotas= new GeneradorFlotas(plano,controlador);
		while (true){
			/*Si aun hay naves enemigas, no hay necesidad
			 * de crear una nueva flota
			 */
			if(!plano.devolverListaNaves().isEmpty()){
				controlador.comenzarJuego(1);
			}
			/*Si ya no hay naves, en cambio,
			 * genero una nueva flota con el generador de naves
			 */
			else{
				generadorFlotas.generarFlota1();
			}
		}
		
	}
}