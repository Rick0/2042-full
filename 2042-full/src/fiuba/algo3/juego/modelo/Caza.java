package fiuba.algo3.juego.modelo;

import java.io.Serializable;
import java.util.Random;
import fiuba.algo3.juego.modelo.excepciones.ItemNoDisponibleError;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class Caza extends NaveNoOperable implements Serializable{

	private static final long serialVersionUID = 6097375138795523283L;


	/* Inicializa una instancia de Caza */
	public Caza(Punto punto, Plano plano) throws SuperposicionNavesError, NaveDestruidaError {

		super();
		puntos = 50;
		energia = 10;
		esOperable = false;
		rectangulo = new Rectangulo(33, 33, punto);
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
	/* Los cazas disparan tanto torpedos simples como torpedos adaptables.
	 * Hay mas frecuencia de que dispara el torpedo simple
	 */
	public void disparar() {

		if (velocidadDisparoCont == velocidadDisparo) {

			Random generadorRandom = new Random();
			// Genera un entero que va desde 0 hasta nextInt-1, o sea 3
			// Hay 75% de que dispare torpedo simple, y 25% para el torpedo adaptable
			int i = generadorRandom.nextInt(4);

			if (i == 0) {
				this.dispararTorpedoAdaptable();
			} else {
				this.dispararTorpedo();
			}
			velocidadDisparoCont = 0;
		}
	}

	@Override
	/* Recibe una cierta cantidad de puntos y los suma a la energ�a de la nave. Ademas,
	/* si la energia es menor a 0, el bombardero deja un paquete de armas en el escenario de juego
	 */
	public void modificarEnergia( int cantidad ) {

		energia = (energia + cantidad);
		if (energia <= 0) {
			try {
				this.destruirse();
			} catch (Exception error) { 
				// Esto no puede suceder
			}

			try {
				this.dejarTanque();
			} catch (ItemNoDisponibleError error) { 
				// Esto no puede suceder
				return;
			}
		}
	}

	/* Los cazas se mueve hacia abajo */
	public void mover() throws SuperposicionNavesError {

		Punto nuevoPunto = new Punto(this.devolverPunto().getX(), this.devolverPunto().getY()-1);
		this.cambiarPosicion(nuevoPunto);
		if (this.seSuperponeConOtraNave() ) {
			throw new SuperposicionNavesError("La posicion ya esta ocupada.");
		}
		this.estaFueraDelPlano();
	}

	/* Movimiento que se debe llevar a cabo si la funcion intentar movimiento comprueba que el movimiento
	 * por defecto provocaria un choque entre naves del mismo tipo. Intenta moverse una
	 * posicion hacia atras. Si no puede, se queda quieto
	 */
	public void moverAlternativo() throws SuperposicionNavesError {

		Punto nuevoPunto = new Punto(this.devolverPunto().getX(), this.devolverPunto().getY()+1);
		this.cambiarPosicion(nuevoPunto);
		if (this.seSuperponeConOtraNave() ) {
			throw new SuperposicionNavesError("La posicion ya esta ocupada.");
		}
		this.estaFueraDelPlano();
	}

}
