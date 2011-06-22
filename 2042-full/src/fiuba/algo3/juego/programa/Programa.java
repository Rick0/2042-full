package fiuba.algo3.juego.programa;

import fiuba.algo3.juego.modelo.Plano;
//import fiuba.algo3.juego.vista.AsignadorImagenesArmas;
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

	//	GeneradorFlotasEjemplo generadorFlotas = new GeneradorFlotasEjemplo(plano,controlador);
	//	generadorFlotas.generarFlota1();

		GeneradorFlota generadorFlota = new GeneradorFlota(plano);
		controlador.agregarObjetoVivo(generadorFlota);



	//	while (true) {

			/* Si aun hay naves enemigas, no hay necesidad
			 * de crear una nueva flota
			 */
	//		if (!plano.devolverListaNaves().isEmpty()){
				controlador.comenzarJuego();
	//		}
			/* Si ya no hay naves, en cambio,
			 * genero una nueva flota con el generador de naves
			 */
	/*		else {
				generadorFlotas.generarFlota1();
			}
		}
*/	}

}