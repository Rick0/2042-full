package juego;

import excepciones.*;

public class Civil extends NaveNoOperable {

	@Override
	void IntentarAccionSobre(Algo42 algo42) {
		// TODO Auto-generated method stub

	}
	
	private void mover() throws SuperposicionNavesError { 
	//La nave civil se mueve hacia abajo.

		this.determinarPosicion( this.posicionX() , this.posicionY() - 1);
		if ( this.seSuperponeConOtraNave() ) {
			throw new SuperposicionNavesError("La posicion ya esta ocupada.");
		}
		this.estaFueraDeArea();
	}
	
	private void moverAlternativo() throws SuperposicionNavesError {
	/*Movimiento que se debe llevar a cabo si la funcion intentar movimiento comprueba que el movimiento
	por defecto provocaria un choque entre naves del mismo tipo. Intenta moverse una
	posicion hacia atras. Si no puede, se queda quieto.*/

		this.determinarPosicion( this.posicionX(), (this.posicionY() + 1));
		if (this.seSuperponeConOtraNave() ) {
			
			throw new SuperposicionNavesError("La posicion ya esta ocupada.");
		}
		this.estaFueraDeArea();
	}
	
}
