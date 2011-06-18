package fiuba.algo3.juego.modelo;

import fiuba.algo3.juego.modelo.excepciones.NaveNoDestruidaError;


/* Clase de la cual heredan todas las naves del juego */
public abstract class Nave extends ObjetoUbicable {

	boolean estaDestruida;
	boolean esOperable;
	int energia;


	/* Devuelve la cantidad de energia actual con la que cuenta la nave */
	public int devolverCantidadEnergia() {
		return energia;
	}

	/* Lleva a cabo las acciones correspondientes si debe destruirse */
	public void destruirse() throws NaveNoDestruidaError {
		
		if ( (this.devolverCantidadEnergia()) > 0 ) {
			throw new NaveNoDestruidaError("La nave aun tiene energia en su tanque");
		}
		else {
			estaDestruida = true;
			plano.agregarNaveEliminada(this);
		}
	}
	
	/* Devuelve True si la nave esta destruida */
	public boolean estadoActualDestruida() {
		return estaDestruida;
	}

	/* Recibe una cierta cantidad de puntos y los suma a la energia de la nave */
	public void modificarEnergia(int energiaAModificar) {

		energia = (energia + energiaAModificar);
		if(energia <= 0) {
			try {
				this.destruirse();
			} catch ( NaveNoDestruidaError excepcion) {
				// La nave aun tiene energia y no debe ser destruida
			}
		}
	}

	/* Devuelve true si se trata de una nave operable; false en caso contrario */
	public boolean esOperable() {
		return esOperable;
	}

	/* La nave choca con un arma */
	public void chocarCon(Arma unArma) {
		this.modificarEnergia(unArma.getDanio());
	}

}
