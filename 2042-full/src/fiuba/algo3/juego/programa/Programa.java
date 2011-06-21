package fiuba.algo3.juego.programa;

import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.vista.AsignadorImagenesArmas;
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
		GeneradorFlotas generadorFlotas = new GeneradorFlotas(plano,controlador);
		AsignadorImagenesArmas asignadorImagenesArmas = new AsignadorImagenesArmas (controlador);

		while (true){
			/*Si aun hay naves enemigas, no hay necesidad
			 * de crear una nueva flota
			 */
			if(!plano.devolverListaNaves().isEmpty()){
				controlador.comenzarJuego(1);
				asignadorImagenesArmas.asignarImagenes(plano.devolverListaArmas());	
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