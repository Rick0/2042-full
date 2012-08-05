package fiuba.algo3.juego.modelo;

import java.io.Serializable;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.NaveNoDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class Civil extends NaveNoOperable implements Serializable{

	private static final long serialVersionUID = -2718411834744356332L;


	/* Inicializa una instancia de Civil */
	public Civil(Punto punto, Plano plano) throws SuperposicionNavesError, NaveDestruidaError {

		puntos = -(300/4);
		energia = 30;
		esOperable = false;
		rectangulo = new Rectangulo(48, 48, punto);
		estaDestruida = false;
		fueraDelPlano = false;
		this.determinarPlano(plano);
		chanceTanqueVida = 1;
		chanceTanqueArma = 0;
		
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
			this.intentarChocar(this.plano.devolverAlgo42());
			this.pasaUnTiempo();
			this.disparar();
		}
	}

	/* La nave civil se mueve hacia abajo */
	public void mover() throws SuperposicionNavesError { 

		Punto nuevoPunto = new Punto(this.devolverPunto().getX(),this.devolverPunto().getY() - this.cantAMover);
		this.cambiarPosicion( nuevoPunto );
		if ( this.seSuperponeConOtraNave() )
			throw new SuperposicionNavesError("La posicion ya esta ocupada.");
		this.estaFueraDelPlano();
	}

	/* Movimiento que se debe llevar a cabo si la funcion intentar movimiento comprueba que el movimiento
	 * por defecto provocaria un choque entre naves del mismo tipo. Intenta moverse una
	 * posicion hacia atras. Si no puede, se queda quieto
	 */
	public void moverAlternativo() throws SuperposicionNavesError {

		Punto nuevoPunto= new Punto(this.devolverPunto().getX(),this.devolverPunto().getY() + this.cantAMover);
		this.cambiarPosicion( nuevoPunto );
		if (this.seSuperponeConOtraNave() )
			throw new SuperposicionNavesError("La posicion ya esta ocupada.");
		this.estaFueraDelPlano();
	}
	
	@Override
	/* El avion civil no tiene armas */
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
