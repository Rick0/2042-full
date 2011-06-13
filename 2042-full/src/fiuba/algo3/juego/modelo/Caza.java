package fiuba.algo3.juego.modelo;

import fiuba.algo3.juego.modelo.excepciones.ItemNoDisponibleError;
import fiuba.algo3.juego.modelo.excepciones.ItemUsadoError;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class Caza extends NaveNoOperable {

	int numero, pasosAvanzados;


	/* Inicializa una instancia de Caza */
	public Caza(Punto punto, Plano plano) throws SuperposicionNavesError, NaveDestruidaError{

		numero = 0;
		puntos = 50;
		energia = 10;
		pasosAvanzados = 0;
		operable = false;
		rectangulo = new Rectangulo(4, 4, punto);
		destruida = false;
		fueraDeJuego = false;
		this.determinarPlano(plano);

		if (this.seSuperponeConOtraNave()) {
			throw new SuperposicionNavesError("La posici�n esta ocupada");
		}
		plano.agregarNave(this);
	}

	@Override
	/* Si la nave esta en la posicion de algo42 lo choca. Lanza
	 * un torpedo y lo agrega a la lista de Armas
	 */
	public void intentarAccionSobre(Algo42 algo42) {
			this.intentarChocar(algo42);
			new Laser(this.devolverPunto(), false, this.plano);
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
		if (! this.destruida) {
			throw new ItemNoDisponibleError("El caza aun no esta destruido, no puede dejar armas.");
		}
		itemDejado = new TanqueEnergia(this.devolverPunto());
		return itemDejado;
	}

	/* Crea una instancia de TorpedoSimple en la posicion actual de la nave */
	public void dispararTorpedo() {
		new TorpedoSimple( this.devolverPunto(), false, this.plano);
	}

	/* Crea una instancia de TorpedoAdaptable en la posicion actual de la nave */
	public void dispararTorpedoAdaptable() {
		new TorpedoAdaptable( this.devolverPunto(), false, this.plano);
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
		this.determinarPosicion(nuevoPunto);
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
		this.determinarPosicion(punto);
		pasosAvanzados = (pasosAvanzados + 1); 
		if (this.seSuperponeConOtraNave() ) {
			throw new SuperposicionNavesError("La posicion ya esta ocupada.");
		}
		this.estaFueraDeArea();
	}

}
