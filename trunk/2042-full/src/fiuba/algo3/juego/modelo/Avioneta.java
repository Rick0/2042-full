package fiuba.algo3.juego.modelo;

import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class Avioneta extends NaveNoOperable {

	public int puntosAtras, puntosAdelante;


	/* Inicializa una instancia de Avioneta */
	public Avioneta(Punto punto, Plano plano) throws SuperposicionNavesError, NaveDestruidaError {
		
		this.puntos = 20;
		energia = 20;
		puntosAdelante= 0;
		puntosAtras = 0;
		esOperable = false;
		rectangulo = new Rectangulo(60, 55, punto);
		estaDestruida = false;
		fueraDeJuego = false;
		this.determinarPlano(plano);

		if (this.seSuperponeConOtraNave()) {
			throw new SuperposicionNavesError("La posici�n esta ocupada");
		}
		plano.agregarNave(this);
	}

	public void dispararLaser() {
		new Laser(this.devolverPunto(), false, this.plano);
	}

	/* Metodo para el movimiento de la avioneta. Se mueve 60 puntos hacia adelante, luego 60 hacia atras,
	 * y asi sucesivamente. Es la nave mas rapida, por lo cual se mueve dos lugares cada vez que
	 * este metodo es invocado
	 */
	public void mover() throws SuperposicionNavesError {

		if ( (this.puntosAdelante <= 60) ) {
			this.puntosAdelante = (this.puntosAdelante + 2);
			Punto nuevaPosicion= new Punto(devolverPunto().getX(),devolverPunto().getY()-2);
			this.cambiarPosicion(nuevaPosicion);
		}
		else {
			this.puntosAtras = (this.puntosAtras + 2);
			Punto nuevaPosicion= new Punto(devolverPunto().getX(),devolverPunto().getY()+2);
			this.cambiarPosicion(nuevaPosicion);
			if (this.puntosAtras >= 60) {
				this.puntosAtras = 0;
				this.puntosAdelante =0;
			}
		}

		if (this.seSuperponeConOtraNave()) {
			throw new SuperposicionNavesError("La posicion ya esta ocupada.");
		}
		this.estaFueraDeArea();
	}

	/* Este metodo debe ser llamado cuando, por alguna razon, el movimiento que realiza
	 * la nave por defecto no puede ser llevado a cabo
	 */
	public void moverAlternativo() throws SuperposicionNavesError {

		if (this.puntosAdelante < 62) {
			this.puntosAtras = 0;
			this.puntosAdelante = 0;
			// Hago que empiece a moverse hacia adelante desde 0,
			//pero antes lo hago moverse hacia atras, si es que puedo.
			Punto nuevaPosicion= new Punto(devolverPunto().getX(),devolverPunto().getY()+2);
			this.cambiarPosicion(nuevaPosicion);
		}
		else {
			this.puntosAtras = 0;
			Punto nuevaPosicion= new Punto(devolverPunto().getX(),devolverPunto().getY()-2);
			this.cambiarPosicion(nuevaPosicion);
		}

		if ( this.seSuperponeConOtraNave() ) {
			throw new SuperposicionNavesError("La posicion ya esta ocupada");
		}
		this.estaFueraDeArea();
	}
	
	@Override
	/* Si la nave esta en la posicion de algo42 lo choca.
	 * Lanza un laser y lo agrega a la lista de Armas
	 */
	void intentarAccionSobre(Algo42 algo42) {
		this.intentarChocar(algo42);
		this.dispararLaser();
	}

}
