package fiuba.algo3.juego.modelo;

import java.io.Serializable;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.NaveNoDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class Helicoptero extends NaveNoOperable implements Serializable{

	private static final long serialVersionUID = -288158403144849238L;


	/* Instancia un Helicoptero */
	public Helicoptero(Punto punto, Plano plano) throws SuperposicionNavesError, NaveDestruidaError {
		
		puntos = -(200/4);
		energia = 20;
		esOperable = false;
		rectangulo = new Rectangulo(30, 24, punto);
		estaDestruida = false;
		fueraDelPlano = false;
		this.determinarPlano(plano);
		chanceTanqueVida = 0;
		chanceTanqueArma = 1;
		this.cantAMover = 2;
		
		if (this.seSuperponeConOtraNave()) {
			throw new SuperposicionNavesError("La posicion esta ocupada");
		}
		plano.agregarNaveAliada(this);
		plano.agregarObjetoNuevo(this);
	}

	/* El helicoptero, como nave neutral, no huye si alguna nave guia se destruye */
	public void vivir() {

		if (!estaDestruida) {

			this.intentarMover();
		//	this.intentarChocar(this.plano.devolverAlgo42());
			this.pasaUnTiempo();
			this.disparar();
		}
	}

	/* El helicoptero se mueve hacia abajo */
	public void mover() throws SuperposicionNavesError { 
		Punto punto = new Punto (this.devolverPunto().getX(),this.devolverPunto().getY() - this.cantAMover);
		this.cambiarPosicion( punto );
		if ( this.seSuperponeConOtraNave() )
			throw new SuperposicionNavesError("La posicion ya esta ocupada.");
		this.estaFueraDelPlano();
	}

	/* Movimiento que se debe llevar a cabo si la funcion intentar movimiento comprueba que el movimiento
	 * por defecto provocaria un choque entre naves del mismo tipo. Intenta moverse una posicion hacia atras
	 */
	public void moverAlternativo() throws SuperposicionNavesError { 

		Punto punto = new Punto (this.devolverPunto().getX(),this.devolverPunto().getY() + (this.cantAMover/2) );
		this.cambiarPosicion( punto);
		if ( this.seSuperponeConOtraNave() )
			throw new SuperposicionNavesError("La posicion ya esta ocupada.");
		this.estaFueraDelPlano();
	}

	@Override
	/* El helicoptero tiene orden de no disparar */
	public void disparar() {	}

	@Override
	/* Lleva a cabo las acciones correspondientes si debe destruirse */
	public void destruirse() throws NaveNoDestruidaError {

		if (this.devolverEnergia() > 0)
			throw new NaveNoDestruidaError("La nave aun tiene energia en su tanque");
		else {
			estaDestruida = true;
			plano.agregarNaveAliadaEliminada(this);
			new NaveExplosion(this.devolverPunto(), this.plano);
		}
	}
	
}
