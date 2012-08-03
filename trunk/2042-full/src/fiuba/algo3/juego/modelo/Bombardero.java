package fiuba.algo3.juego.modelo;

import java.io.Serializable;
import java.util.Random;
import fiuba.algo3.juego.modelo.excepciones.ItemNoDisponibleError;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class Bombardero extends NaveNoOperable implements Serializable {

	private static final long serialVersionUID = -7505650596285382873L;
	double haciaDer, haciaIzq;
	static int cantidadMoverLateral = 20;
	static double cantidadPaso = 0.5;


	/* Inicializa una instancia de Bombardero */
	public Bombardero(Punto punto, Plano plano) throws SuperposicionNavesError, NaveDestruidaError {

		super();
		puntos = 30;
		energia = 50;
		haciaDer = 0;
		haciaIzq = 0;
		esOperable = false;
		rectangulo = new Rectangulo(44, 60,punto);
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
	/* Recibe una cierta cantidad de puntos y los suma a la energia de la nave. Ademas,
	 * si la energia es menor a 0, el bombardero deja un paquete de armas en el escenario de juego
	 */
	public void modificarEnergia(int cantidad) {

		energia = (energia + cantidad);
		if (energia <= 0) {
			try {
				this.destruirse();
			} catch (Exception error) { 
				// Esto no puede suceder
			}

			try {
				this.dejarArma();
			} catch (ItemNoDisponibleError error) { 
				// Esto no puede suceder
				return;
			}
		}
	}
	
	/* El bombardero se mueve en Zig Zag; Primero se mueve 0.5 puntos hacia abajo y hacia la derecha.
	 * Cuando ya bajo 10 puntos, empieza a ir 0.5 puntos hacia abajo y hacia la izquierda, hasta bajar
	 * 10 puntos mas, y asi sucesivamente
	 */
	public void mover() throws SuperposicionNavesError {

		if (haciaDer < cantidadMoverLateral) {
			Punto nuevoPunto = new Punto(this.devolverPunto().getX() + cantidadPaso, this.devolverPunto().getY() - cantidadPaso);
			this.cambiarPosicion(nuevoPunto);
			haciaDer = (haciaDer + cantidadPaso);
		}
		else {
			if (haciaIzq  <= cantidadMoverLateral) {
				Punto nuevoPunto = new Punto(this.devolverPunto().getX() - cantidadPaso, this.devolverPunto().getY() - cantidadPaso);
				this.cambiarPosicion(nuevoPunto);
				haciaIzq = (haciaIzq + cantidadPaso);
			}
			else {
				haciaIzq = 0;
				haciaDer = 0;
			}
		}
		if ( this.seSuperponeConOtraNave() ) {
			throw new SuperposicionNavesError("La posicion ya esta ocupada.");
		}

		this.estaFueraDelPlano();
	}

	/* Movimiento que se debe llevar a cabo si la funcion intentar movimiento comprueba que el movimiento
	 * por defecto provocaria un choque entre naves del mismo tipo. Intenta empezar el movimiento en zig zag
	 * en el sentido opuesto. Si no puede, se queda quieto
	 */
	public void moverAlternativo() throws SuperposicionNavesError {

		if ( haciaDer <= cantidadMoverLateral ) {
			// Se estaba moviendo hacia la derecha. Lo envio a la izquierda
			Punto nuevoPunto = new Punto(this.devolverPunto().getX() - cantidadPaso,this.devolverPunto().getY() - cantidadPaso);
			this.cambiarPosicion(nuevoPunto);
			haciaDer = cantidadMoverLateral + cantidadPaso;
			haciaIzq = cantidadPaso;
		}
		else {
			// Se estaba moviendo hacia la izquierda. Lo envio a la derecha
			Punto nuevoPunto = new Punto(this.devolverPunto().getX() + cantidadPaso,this.devolverPunto().getY() - cantidadPaso);
			this.cambiarPosicion(nuevoPunto);
			haciaDer = cantidadPaso;
			haciaIzq = cantidadMoverLateral + cantidadPaso;
		}

		if ( this.seSuperponeConOtraNave() ) {
			throw new SuperposicionNavesError ("La posicion ya esta ocupada");
		}
		this.estaFueraDelPlano();
	}

	/* El bombardero tiene tres tipos de armas distintas para lanzar. Esta funcion crea un arma aleatoria */
	public void disparar() {

		if (velocidadDisparoCont == velocidadDisparo) {

			Random generadorRandom = new Random();
			// Genera un entero que va desde 0 hasta nextInt-1
			// Torpedo rastreador tiene el menor % de ser disparado
			int i = generadorRandom.nextInt(9);

			if (i <= 2) {
				this.dispararCohete();
			} else if (i == 3) {
				this.dispararTorpedoRastreador();
			} else {
				this.dispararLaser();
			}
			velocidadDisparoCont = 0;
		}
	}

	/* Devuelve la cantidad de movientos laterales maximo */
	public int devolverCantidadMoverLateral() {
		return cantidadMoverLateral;
	}

}
