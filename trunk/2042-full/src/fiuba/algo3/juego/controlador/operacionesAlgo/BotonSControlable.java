package fiuba.algo3.juego.controlador.operacionesAlgo;

import java.util.ArrayList;
import fiuba.algo3.juego.modelo.Algo42;
import fiuba.algo3.juego.modelo.NaveNoOperable;
import fiuba.algo3.juego.modelo.excepciones.ArmaNoDisponibleError;


public class BotonSControlable implements Controlable {

	Algo42 algo;

	
	public BotonSControlable(Algo42 algo42) {
		algo = algo42;
	}

	/**Si el usuario oprime la tecla S, el algo42 lanzara muchos torpedos rastreadores
	 * de variadas velocidades. Apuntan a diferentes naves.
	 * @see fiuba.algo3.juego.controlador.operacionesAlgo.Controlable#activarEfecto()
	 */
	@Override
	public void activarEfecto() {

		ArrayList<NaveNoOperable> listaNavesEnemigas = this.algo.devolverPlano().devolverListaNaves();

		if (listaNavesEnemigas.size() > 0) {
			try {
				algo.dispararTorpedoRastreadorV2();
			}
			catch (ArmaNoDisponibleError e) {
			}
		}
	}

}
