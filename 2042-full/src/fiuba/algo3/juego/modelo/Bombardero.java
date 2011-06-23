package fiuba.algo3.juego.modelo;

import java.io.Serializable;
import java.util.Random;
import fiuba.algo3.juego.modelo.excepciones.ItemNoDisponibleError;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class Bombardero extends NaveNoOperable implements Serializable {

	private static final long serialVersionUID = -7505650596285382873L;
	double haciaDer, haciaIzq;


	/* Inicializa una instancia de Bombardero */
	public Bombardero(Punto punto, Plano plano) throws SuperposicionNavesError, NaveDestruidaError {

		super();
		puntos = 30;
		energia = 50;
		haciaDer = 0;
		haciaIzq = 0;
		esOperable = false;
		rectangulo = new Rectangulo(65, 65,punto);
		estaDestruida = false;
		fueraDelPlano = false;
		this.determinarPlano(plano);

		if (this.seSuperponeConOtraNave()) {
			throw new SuperposicionNavesError("La posicion esta ocupada");
		}
		plano.agregarNave(this);
		plano.agregarObjetoNuevo(this);
	}

	/* Crea una instancia de ArmaAbandonada y la devuelve */ 
	public Item dejarArma() throws ItemNoDisponibleError {

		if (!this.estaDestruida) {
			throw new ItemNoDisponibleError("El bombardero aun no esta destruido, no puede dejar armas");
		}
		Item itemDejado = new ArmaAbandonada(this.devolverPunto(),this.plano);		

		return itemDejado;
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
				// esto no puede suceder
			}

			try {
				this.dejarArma();
			} catch (ItemNoDisponibleError error) { 
				// esto no puede suceder
				return;
			}
		}
	}
	
	/* El bombardero se mueve en Zig Zag; Primero se mueve 0.5 puntos hacia abajo y hacia la derecha.
	 * Cuando ya bajo 10 puntos, empieza a ir 0.5 puntos hacia abajo y hacia la izquierda, hasta bajar
	 * 10 puntos mas, y asi sucesivamente
	 */
	public void mover() throws SuperposicionNavesError {

		if ( haciaDer < 10 ) {
			Punto nuevoPunto = new Punto(this.devolverPunto().getX()+0.5,this.devolverPunto().getY()-0.5);
			this.cambiarPosicion(nuevoPunto);
			haciaDer = (haciaDer + 0.5);
		}
		else {
			if (haciaIzq  <= 10) {
				Punto nuevoPunto=new Punto(this.devolverPunto().getX()-0.5,this.devolverPunto().getY()-0.5);
				this.cambiarPosicion(nuevoPunto);
				haciaIzq = (haciaIzq + 0.5);
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

		if ( haciaDer <=10 ) {
			// Se estaba moviendo hacia la derecha. Lo envio a la izquierda
			Punto nuevoPunto=new Punto(this.devolverPunto().getX()-0.5,this.devolverPunto().getY()-0.5);
			this.cambiarPosicion(nuevoPunto);
			haciaDer = 10.5;
			haciaIzq = 0.5;
		}
		else {
			 // Se estaba moviendo hacia la izquierda. Lo envio a la derecha
			Punto nuevoPunto=new Punto(this.devolverPunto().getX()+0.5,this.devolverPunto().getY()-0.5);
			this.cambiarPosicion(nuevoPunto);
			haciaDer = 0.5;
			haciaIzq = 10.5;
		}

		if ( this.seSuperponeConOtraNave() ) {
			throw new SuperposicionNavesError ("La posicion ya esta ocupada");
		}
		this.estaFueraDelPlano();
	}

	/*El bombardero tiene tres tipos de armas distintas para lanzar. Esta funcion crea un arma
	 * aleatoria: Busca un numero aleatorio del 1 al 3, si el numero es 1 lanza un laser, si es dos, un cohete
	 * y si es 3, un torpedo rastreador
	 */
	public void disparar() {

		if (velocidadDisparoCont == velocidadDisparo) {

			Random generadorRandom = new Random();
			// Genera un entero que va desde 0 hasta nextInt-1, o sea 3
			// Hay 50% de que dispare laser, y 25% para cada una de las otras dos armas
			int i = generadorRandom.nextInt(4);

			if (i == 1) {
				this.dispararTorpedoRastreadorHacia(this.plano.getAlgo42());
			} else if (i == 2) {
				this.dispararCohete();
			} else {
				this.dispararLaser();
			}
			velocidadDisparoCont = 0;
		}
	}

}