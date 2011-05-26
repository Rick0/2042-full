package juego;

import excepciones.*;

public class Helicoptero extends NaveNoOperable {

	
	public Helicoptero(int posicionX, int posicionY, Plano plano) throws SuperposicionNavesError {
		
		puntos = -200;
		energia = 1;
		operable = false;
		rectangulo = new Rectangulo(4, 4, posicionX, posicionY);
		destruida = false;
		fueraDeJuego = false;
		
		this.determinarPlano(plano);
		
		if (this.seSuperponeConOtraNave()) {
			throw new SuperposicionNavesError("La posición esta ocupada");
		}
		plano.agregarNave(this);
	}
	
	@Override
	public void IntentarAccionSobre(Algo42 algo42) {
		//Si la nave esta en la posicion de algo42 lo choca.
		this.intentarChocar(algo42);

	}
	
	public void mover() throws SuperposicionNavesError { 
	//El helicoptero se mueve hacia abajo.
		this.determinarPosicion( this.posicionX(), this.posicionY() -1);
		if ( this.seSuperponeConOtraNave() ) {
			throw new SuperposicionNavesError("La posicion ya esta ocupada.");
		}
		this.estaFueraDeArea();
	}
	
	public void moverAlternativo() throws SuperposicionNavesError { 
		/*"Movimiento que se debe llevar a cabo si la funcion intentar movimiento comprueba que el movimiento
	por defecto provocaria un choque entre naves del mismo tipo. Intenta moverse una
	posicion hacia atras. .*/
			this.determinarPosicion( this.posicionX(), this.posicionY() +1);
			if ( this.seSuperponeConOtraNave() ) {
				throw new SuperposicionNavesError("La posicion ya esta ocupada.");
			}
			this.estaFueraDeArea();
		}
}
