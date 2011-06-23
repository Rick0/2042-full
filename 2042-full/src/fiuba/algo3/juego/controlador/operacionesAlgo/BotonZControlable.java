package fiuba.algo3.juego.controlador.operacionesAlgo;

import fiuba.algo3.juego.modelo.Algo42;
import fiuba.algo3.juego.modelo.excepciones.ArmaNoDisponibleError;


public class BotonZControlable implements Controlable {

	Algo42 algo;

	
	public BotonZControlable(Algo42 algo42) {
		algo = algo42;
	}

	/** Si el usuario oprime la tecla C, el algo42 lanzara un arma cohete
	 * @see fiuba.algo3.juego.controlador.operacionesAlgo.Controlable#activarEfecto()
	 */
	@Override
	public void activarEfecto() {
		try {
			algo.dispararCohete();
		} catch (ArmaNoDisponibleError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
