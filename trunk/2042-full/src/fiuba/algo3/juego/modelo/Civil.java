package fiuba.algo3.juego.modelo;

import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class Civil extends NaveNoOperable {

	/* Inicializa una instancia de Civil */
	public Civil(Punto punto, Plano plano) throws SuperposicionNavesError, NaveDestruidaError {

		puntos = -300;
		energia = 1;
		esOperable = false;
		rectangulo = new Rectangulo(50, 50, punto);
		estaDestruida = false;
		fueraDeJuego = false;
		this.determinarPlano(plano);
		
		if (this.seSuperponeConOtraNave()) {
			throw new SuperposicionNavesError("La posicion esta ocupada");
		}
		plano.agregarNave(this);
	}

	/* La nave civil se mueve hacia abajo */
	public void mover() throws SuperposicionNavesError { 

		Punto nuevoPunto= new Punto(this.devolverPunto().getX(),this.devolverPunto().getY()-1);
		this.cambiarPosicion( nuevoPunto );
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

		Punto nuevoPunto= new Punto(this.devolverPunto().getX(),this.devolverPunto().getY()+1);
		this.cambiarPosicion( nuevoPunto );
		if (this.seSuperponeConOtraNave() ) {
			throw new SuperposicionNavesError("La posicion ya esta ocupada.");
		}
		this.estaFueraDeArea();
	}
	
	@Override
	/* El avion civil no tiene armas */
	public void disparar() {	}

}