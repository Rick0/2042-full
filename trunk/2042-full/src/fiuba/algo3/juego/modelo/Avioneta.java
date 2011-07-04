package fiuba.algo3.juego.modelo;

import java.io.Serializable;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class Avioneta extends NaveNoOperable implements Serializable {

	private static final long serialVersionUID = 4601764551109182837L;
	public int puntosAtrasCont, puntosAdelanteCont;
	static int puntosAdelante = 400;
	static int puntosAtras = 300;


	/* Inicializa una instancia de Avioneta */
	public Avioneta(Punto punto, Plano plano) throws SuperposicionNavesError, NaveDestruidaError {

		super();
		this.puntos = 20;
		energia = 20;
		puntosAdelanteCont = 0;
		puntosAtrasCont = 0;
		esOperable = false;
		rectangulo = new Rectangulo(48, 61, punto);
		estaDestruida = false;
		fueraDelPlano = false;
		this.determinarPlano(plano);

		if (this.seSuperponeConOtraNave()) {
			throw new SuperposicionNavesError("La posicion esta ocupada");
		}
		plano.agregarNave(this);
		plano.agregarObjetoNuevo(this);
	}

	/* Metodo para el movimiento de la avioneta. Se mueve 60 puntos hacia adelante, luego 60 hacia atras,
	 * y asi sucesivamente. Es la nave mas rapida, por lo cual se mueve dos lugares cada vez que
	 * este metodo es invocado
	 */
	public void mover() throws SuperposicionNavesError {

		if ( (this.puntosAdelanteCont <= puntosAdelante) ) {
			this.puntosAdelanteCont = (this.puntosAdelanteCont + 2);
			Punto nuevaPosicion= new Punto(devolverPunto().getX(),devolverPunto().getY()-2);
			this.cambiarPosicion(nuevaPosicion);
		}
		else {
			this.puntosAtrasCont = (this.puntosAtrasCont + 2);
			Punto nuevaPosicion= new Punto(devolverPunto().getX(),devolverPunto().getY()+2);
			this.cambiarPosicion(nuevaPosicion);
			if (this.puntosAtrasCont >= puntosAtras) {
				this.puntosAtrasCont = 0;
				this.puntosAdelanteCont =0;
			}
		}

		if (this.seSuperponeConOtraNave()) {
			throw new SuperposicionNavesError("La posicion ya esta ocupada.");
		}
		this.estaFueraDelPlano();
	}

	/* Este metodo debe ser llamado cuando, por alguna razon, el movimiento que realiza
	 * la nave por defecto no puede ser llevado a cabo
	 */
	public void moverAlternativo() throws SuperposicionNavesError {

		if (this.puntosAdelanteCont < (puntosAdelante+2)) {
			this.puntosAtrasCont = 0;
			this.puntosAdelanteCont = 0;
			// Hago que empiece a moverse hacia adelante desde 0,
			// pero antes lo hago moverse hacia atras, si es que puedo.
			Punto nuevaPosicion= new Punto(devolverPunto().getX(),devolverPunto().getY()+2);
			this.cambiarPosicion(nuevaPosicion);
		}
		else {
			this.puntosAtrasCont = 0;
			Punto nuevaPosicion= new Punto(devolverPunto().getX(),devolverPunto().getY()-2);
			this.cambiarPosicion(nuevaPosicion);
		}

		if ( this.seSuperponeConOtraNave() ) {
			throw new SuperposicionNavesError("La posicion ya esta ocupada");
		}

		this.estaFueraDelPlano();
	}
	
	@Override
	/* La avioneta dispara lasers */
	public void disparar() {

		if (velocidadDisparoCont == velocidadDisparo) {
			this.dispararLaser();
			velocidadDisparoCont = 0;
		}
	}

	public int devolverPuntosAdelante() {
		return puntosAdelante;
	}

	public int devolverPuntosAtras() {
		return puntosAtras;
	}

}
