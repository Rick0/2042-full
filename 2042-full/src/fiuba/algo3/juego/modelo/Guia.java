package fiuba.algo3.juego.modelo;

import java.util.Iterator;
import java.util.List;
import fiuba.algo3.juego.modelo.excepciones.GuiaNoDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.NaveNoDestruidaError;


public abstract class Guia extends NaveNoOperable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3148574291086531899L;
	List<NaveNoOperable> flota;


	/* La nave guia tiene una caracteristica especial: Cuando sus puntos de energia bajan,
	 * si llegan a ser iguales o menores a cero, llama a la funcion flotaRetroceder. De esa manera,
	 * las naves de la flota que perdieron su guia, huyen hacia atras
	 */
	public void destruirse() throws NaveNoDestruidaError {

		if (this.devolverEnergia() > 0) {
			throw new NaveNoDestruidaError("La nave aun tiene energia en su tanque");
		}
		else {
			estaDestruida = true;
			plano.agregarNaveEliminada(this);
			try {
				this.flotaRetroceder();
			} catch (GuiaNoDestruidaError error) {
				System.out.println("Rogamos que no use cheats");
			}
		}
	}

	/* Todas las naves de la flota actual retroceden. Esta funcion debe ser llamada solamente si
	 * la nave guia fue destruida. En caso contrario, levanta una excepcion
	 */
	public void flotaRetroceder() throws GuiaNoDestruidaError {

		if (estaDestruida) {

			Iterator<NaveNoOperable> iteradorNaves = flota.iterator();
			while (iteradorNaves.hasNext()) {
				NaveNoOperable unaNave = iteradorNaves.next();
				unaNave.huir();
			}
		}
		else {
			throw new GuiaNoDestruidaError("La nave guia no fue destruida. La flota no tiene por que retirarse");
		}
	}

}
