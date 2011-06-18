package fiuba.algo3.juego.modelo;

import fiuba.algo3.juego.modelo.excepciones.ItemNoDisponibleError;
import fiuba.algo3.juego.modelo.excepciones.ItemUsadoError;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class Caza extends NaveNoOperable {

	int numero, pasosAvanzados;


	/* Inicializa una instancia de Caza */
	public Caza(Punto punto, Plano plano) throws SuperposicionNavesError, NaveDestruidaError {

		numero = 0;
		puntos = 50;
		energia = 10;
		pasosAvanzados = 0;
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

	@Override
	/* Los cazas disparan tanto torpedos simples como torpedos adaptables.
	 * Hay mas frecuencia de que dispara el torpedo simple
	 */
	public void disparar() {
		int r = new Double(Math.random() * 3).intValue();

		if (r == 1) {
			this.dispararTorpedoAdaptable();
		}
		else {
			this.dispararTorpedo();
		}
	}

	/* Devuelve true si la nave avanzo 3 pasos o mas */
	public boolean avanzo3Pasos() {
		if (pasosAvanzados >= 3) {
			return true;
		}
		return false;
	}

	/* Crea una instancia de TanqueEnergia y la devuelve */
	public Item dejarTanque() throws ItemNoDisponibleError {
		
		Item itemDejado;
		if (! this.estaDestruida) {
			throw new ItemNoDisponibleError("El caza aun no esta destruido, no puede dejar armas.");
		}
		itemDejado = new TanqueEnergia(this.devolverPunto(), this.plano);
		return itemDejado;
	}

	@Override
	/* Recibe una cierta cantidad de puntos y los suma a la energ�a de la nave. Ademas,
	/* si la energia es menor a 0, el bombardero deja un paquete de armas en el escenario de juego
	 */
	public void modificarEnergia( int cantidad ) {
		
		Item itemDejado;

		energia = (energia + cantidad);
		if (energia <= 0) {
			try {
				this.destruirse();
			} catch (Exception error) { 
				//esto no puede suceder
			}
			try {
				itemDejado = this.dejarTanque();
			} catch (ItemNoDisponibleError error) { 
				//esto no puede suceder
				return;
			}
			try {
				plano.agregarItem( itemDejado );
			} catch (ItemUsadoError error) {
				itemDejado.noUsado();
			}
		}
	}

	/* Los cazas se mueve hacia abajo */
	public void mover() throws SuperposicionNavesError {

		Punto nuevoPunto= new Punto(this.devolverPunto().getX(),this.devolverPunto().getY()-1);
		this.cambiarPosicion(nuevoPunto);
		pasosAvanzados = (pasosAvanzados + 1); 
		if (this.seSuperponeConOtraNave() ) {
			throw new SuperposicionNavesError("La posicion ya esta ocupada.");
		}
		this.estaFueraDeArea();
	}

	/* Movimiento que se debe llevar a cabo si la funcion intentar movimiento comprueba que el movimiento
	 * por defecto provocaria un choque entre naves del mismo tipo. Intenta moverse una
	 * posicion hacia atras. Si no puede, se queda quieto
	 */
	public void moverAlternativo() throws SuperposicionNavesError {

		Punto punto= new Punto(this.devolverPunto().getX(),this.devolverPunto().getY()+1);
		this.cambiarPosicion(punto);
		pasosAvanzados = (pasosAvanzados + 1); 
		if (this.seSuperponeConOtraNave() ) {
			throw new SuperposicionNavesError("La posicion ya esta ocupada.");
		}
		this.estaFueraDeArea();
	}

}
