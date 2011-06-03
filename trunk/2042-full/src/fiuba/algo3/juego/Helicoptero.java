package juego;
import juego.excepciones.NaveDestruidaError;
import juego.excepciones.SuperposicionNavesError;


public class Helicoptero extends NaveNoOperable {

	/* Instancia un Helicoptero */
	public Helicoptero(int posicionX, int posicionY, Plano plano) throws SuperposicionNavesError, NaveDestruidaError {
		
		puntos = -200;
		energia = 1;
		operable = false;
		rectangulo = new Rectangulo(4, 4, posicionX, posicionY);
		destruida = false;
		fueraDeJuego = false;
		this.determinarPlano(plano);
		
		if (this.seSuperponeConOtraNave()) {
			throw new SuperposicionNavesError("La posicion esta ocupada");
		}
		plano.agregarNave(this);
	}

	@Override
	/* Si la nave esta en la posicion de algo42 lo choca */
	public void IntentarAccionSobre(Algo42 algo42) {
		this.intentarChocar(algo42);
	}

	/* El helicoptero se mueve hacia abajo */
	public void mover() throws SuperposicionNavesError { 
	
		this.determinarPosicion( this.posicionX(), this.posicionY() -1);
		if ( this.seSuperponeConOtraNave() ) {
			throw new SuperposicionNavesError("La posicion ya esta ocupada.");
		}
		this.estaFueraDeArea();
	}

	/* Movimiento que se debe llevar a cabo si la funcion intentar movimiento comprueba que el movimiento
	 * por defecto provocaria un choque entre naves del mismo tipo. Intenta moverse una posicion hacia atras
	 */
	public void moverAlternativo() throws SuperposicionNavesError { 

		this.determinarPosicion( this.posicionX(), this.posicionY() +1);
		if ( this.seSuperponeConOtraNave() ) {
			throw new SuperposicionNavesError("La posicion ya esta ocupada.");
		}
		this.estaFueraDeArea();
	}

}
