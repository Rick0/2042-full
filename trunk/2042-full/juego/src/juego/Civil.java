package juego;

import juego.excepciones.*;

public class Civil extends NaveNoOperable {
	
	public Civil(int posicionX, int posicionY, Plano plano) throws SuperposicionNavesError, NaveDestruidaError {
		//"Inicializa una instancia de Bombardero"

		puntos = -300;
		energia = 1;
		operable = false;
		rectangulo = new Rectangulo(5, 2, posicionX, posicionY);
		destruida = false;
		fueraDeJuego = false;
		
		this.determinarPlano(plano);
		
		if (this.seSuperponeConOtraNave()) {
			throw new SuperposicionNavesError("La posici�n esta ocupada");
		}
		plano.agregarNave(this);
	}
	
	public void mover() throws SuperposicionNavesError { 
	//La nave civil se mueve hacia abajo.

		this.determinarPosicion( this.posicionX() , this.posicionY() - 1);
		if ( this.seSuperponeConOtraNave() ) {
			throw new SuperposicionNavesError("La posicion ya esta ocupada.");
		}
		this.estaFueraDeArea();
	}
	
	public void moverAlternativo() throws SuperposicionNavesError {
	/*Movimiento que se debe llevar a cabo si la funcion intentar movimiento comprueba que el movimiento
	por defecto provocaria un choque entre naves del mismo tipo. Intenta moverse una
	posicion hacia atras. Si no puede, se queda quieto.*/

		this.determinarPosicion( this.posicionX(), (this.posicionY() + 1));
		if (this.seSuperponeConOtraNave() ) {
			
			throw new SuperposicionNavesError("La posicion ya esta ocupada.");
		}
		this.estaFueraDeArea();
	}
	
	@Override
	public void IntentarAccionSobre(Algo42 algo42) {
	//Si la nave esta en la posicion de algo42 lo choca.
		this.intentarChocar(algo42);
	}
}
