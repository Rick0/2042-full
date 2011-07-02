package fiuba.algo3.juego.controlador.operacionesAlgo;

import java.util.ArrayList;

import fiuba.algo3.juego.modelo.Algo42;
import fiuba.algo3.juego.modelo.NaveNoOperable;
import fiuba.algo3.juego.modelo.excepciones.ArmaNoDisponibleError;
import fiuba.algo3.juego.modelo.excepciones.NaveARastrearError;


public class BotonXControlable implements Controlable {

	Algo42 algo;

	
	public BotonXControlable(Algo42 algo42) {
		algo = algo42;
	}

	/**Si el usuario oprime la tecla X, el algo42 lanzara un arma torpedo rastreador
	 * Como elije la primera nave en la lista de naves enemigas,
	 * esto crea la ilusion de que apunta contra la nave mas cercana a Algo42
	 * @see fiuba.algo3.juego.controlador.operacionesAlgo.Controlable#activarEfecto()
	 */
	@Override
	public void activarEfecto() {

		ArrayList<NaveNoOperable> listaNavesEnemigas = this.algo.devolverPlano().devolverListaNaves();

		if (listaNavesEnemigas.size() > 0) {
			try {
				algo.dispararTorpedoRastreadorHacia(listaNavesEnemigas.get(0));
			}
			catch (ArmaNoDisponibleError e) {
				// Si no tiene el arma, que no haga nada.
			}
			catch (NaveARastrearError e) {
				// Problema con la nave a rastrear
			}
		}
	}

}
