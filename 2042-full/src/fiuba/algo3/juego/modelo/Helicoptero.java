package fiuba.algo3.juego.modelo;

import java.io.Serializable;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class Helicoptero extends NaveNoOperable implements Serializable{

	private static final long serialVersionUID = -288158403144849238L;


	/* Instancia un Helicoptero */
	public Helicoptero(Punto punto, Plano plano) throws SuperposicionNavesError, NaveDestruidaError {
		
		puntos = -200;
		energia = 1;
		esOperable = false;
		rectangulo = new Rectangulo(30, 24, punto);
		estaDestruida = false;
		fueraDelPlano = false;
		this.determinarPlano(plano);
		
		if (this.seSuperponeConOtraNave()) {
			throw new SuperposicionNavesError("La posicion esta ocupada");
		}
		plano.agregarNave(this);
		plano.agregarObjetoNuevo(this);
	}

	@Override
	/* El helicoptero tiene orden de no disparar */
	public void disparar() {	}

	/* El helicoptero se mueve hacia abajo */
	public void mover() throws SuperposicionNavesError { 
		Punto punto= new Punto (this.devolverPunto().getX(),this.devolverPunto().getY() -1);
		this.cambiarPosicion( punto );
		if ( this.seSuperponeConOtraNave() ) {
			throw new SuperposicionNavesError("La posicion ya esta ocupada.");
		}
		this.estaFueraDelPlano();
	}

	/* Movimiento que se debe llevar a cabo si la funcion intentar movimiento comprueba que el movimiento
	 * por defecto provocaria un choque entre naves del mismo tipo. Intenta moverse una posicion hacia atras
	 */
	public void moverAlternativo() throws SuperposicionNavesError { 

		Punto punto= new Punto (this.devolverPunto().getX(),this.devolverPunto().getY() +1);
		this.cambiarPosicion( punto);
		if ( this.seSuperponeConOtraNave() ) {
			throw new SuperposicionNavesError("La posicion ya esta ocupada.");
		}
		this.estaFueraDelPlano();
	}

}
