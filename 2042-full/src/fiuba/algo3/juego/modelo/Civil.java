package fiuba.algo3.juego.modelo;

import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class Civil extends NaveNoOperable {

	/* Inicializa una instancia de Civil */
	public Civil(int posicionX, int posicionY, Plano plano) throws SuperposicionNavesError, NaveDestruidaError {

		puntos = -300;
		energia = 1;
		operable = false;
		rectangulo = new Rectangulo(5, 2, posicionX, posicionY);
		destruida = false;
		fueraDeJuego = false;
		this.determinarPlano(plano);
		
		if (this.seSuperponeConOtraNave()) {
			throw new SuperposicionNavesError("La posicion esta ocupada");
		}
		plano.agregarNave(this);
	}

	/* La nave civil se mueve hacia abajo */
	public void mover() throws SuperposicionNavesError { 

		this.determinarPosicion( this.posicionX() , this.posicionY() - 1);
		if ( this.seSuperponeConOtraNave() ) {
			throw new SuperposicionNavesError("La posicion ya esta ocupada.");
		}
		this.estaFueraDeArea();
	}

	/* Movimiento que se debe llevar a cabo si la funcion intentar movimiento comprueba que el movimiento
	 * por defecto provocaria un choque entre naves del mismo tipo. Intenta moverse una
	 * posicion hacia atras. Si no puede, se queda quieto
	 */
	public void moverAlternativo() throws SuperposicionNavesError {

		this.determinarPosicion( this.posicionX(), (this.posicionY() + 1));
		if (this.seSuperponeConOtraNave() ) {
			throw new SuperposicionNavesError("La posicion ya esta ocupada.");
		}
		this.estaFueraDeArea();
	}
	
	@Override
	/* Si la nave esta en la posicion de algo42 lo choca */
	public void intentarAccionSobre(Algo42 algo42) {
		this.intentarChocar(algo42);
	}

}
