package juego;
import juego.NaveNoOperable;
import juego.excepciones.*;


public class Bombardero extends NaveNoOperable {

	double haciaDer, haciaIzq;


	/* Inicializa una instancia de Bombardero */
	public Bombardero(double x, double y, Plano plano) throws SuperposicionNavesError, NaveDestruidaError {

		this.puntos = 30;
		energia = 50;
		haciaDer = 0;
		haciaIzq = 0;
		operable = false;
		rectangulo = new Rectangulo(7, 7, x, y);
		destruida = false;
		fueraDeJuego = false;
		this.determinarPlano(plano);

		if (this.seSuperponeConOtraNave()) {
			throw new SuperposicionNavesError("La posici�n esta ocupada");
		}
		plano.agregarNave(this);
	}

	/* Crea una instancia de ArmaAbandonada y la devuelve */ 
	public Item dejarArma() throws ItemNoDisponibleError {
		
		if (!this.destruida) {
			throw new ItemNoDisponibleError("El bombardero aun no esta destruido, no puede dejar armas");
		}
		Item item = new ArmaAbandonada(this.posicionX(), this.posicionY() );
		return item;
	}

	@Override
	/* Recibe una cierta cantidad de puntos y los suma a la energ�a de la nave. Ademas,
	 * si la energia es menor a 0, el bombardero deja un paquete de armas en el escenario de juego
	 */
	public void modificarEnergia( int cantidad ) {

		Item itemDejado;

		energia = (energia + cantidad);
		if (energia <= 0) {
			try {
				this.destruirse();
			} catch (Exception error) { 
				// esto no puede suceder
			}
			try {
				itemDejado = this.dejarArma();
			} catch (ItemNoDisponibleError error) { 
				// esto no puede suceder
				return;
			}
			try {
				plano.agregarItem( itemDejado );
			} catch (ItemUsadoError error) {
				itemDejado.noUsado();
			}
		}
	}
	
	@Override
	/* Si la nave esta en la posicion de algo42 lo choca. Lanza
	 * una instancia de arma aleatoria (laser, cohete o torpedo)
	 */
	public void IntentarAccionSobre(Algo42 algo42) {
		this.intentarChocar(algo42);
		this.lanzarArmaAleatoriaHacia(algo42);
	}

	/* El bombardero se mueve en Zig Zag; Primero se mueve 0.5 puntos hacia abajo y hacia la derecha.
	 * Cuando ya bajo 10 puntos, empieza a ir 0.5 puntos hacia abajo y hacia la izquierda, hasta bajar
	 * 10 puntos mas, y asi sucesivamente
	 */
	public void mover() throws SuperposicionNavesError {

		if ( haciaDer <10 ) {
			this.determinarPosicion( this.posicionX() + 0.5 ,this.posicionY() - 0.5);
			haciaDer = (haciaDer + 0.5);
		}
		else {
			if (haciaIzq  <= 10) {
				this.determinarPosicion(this.posicionX() - 0.5,this.posicionY() -0.5);
				haciaIzq = (haciaIzq +0.5);
			}
			else {
				haciaIzq = 0;
				haciaDer = 0;
			}
		}
		if ( this.seSuperponeConOtraNave() ) {
			throw new SuperposicionNavesError("La posicion ya esta ocupada.");
		}

		this.estaFueraDeArea();
	}

	/* Movimiento que se debe llevar a cabo si la funcion intentar movimiento comprueba que el movimiento
	 * por defecto provocaria un choque entre naves del mismo tipo. Intenta empezar el movimiento en zig zag
	 * en el sentido opuesto. Si no puede, se queda quieto
	 */
	public void moverAlternativo() throws SuperposicionNavesError {

		if ( haciaDer <=10 ) {
			// Se estaba moviendo hacia la derecha. Lo envio a la izquierda
			this.determinarPosicion(this.posicionX() -0.5, this.posicionY() -0.5);
			haciaDer = 10.5;
			haciaIzq = 0.5;
		}
		else {
			 // Se estaba moviendo hacia la izquierda. Lo envio a la derecha
			this.determinarPosicion( this.posicionX() +0.5,this.posicionY() -0.5);
			haciaDer = 0.5;
			haciaIzq = 10.5;
		}

		if ( this.seSuperponeConOtraNave() ) {
			throw new SuperposicionNavesError ("La posicion ya esta ocupada");
		}
		this.estaFueraDeArea();
	}
	
	public Cohete dispararCohete() {
		return new Cohete( this.posicionX(), this.posicionY(), false, this.plano);
	}
	
	public Laser dispararLaser() {
		return new Laser( this.posicionX(), this.posicionY(), false, this.plano);
	}
	
	private TorpedoRastreador dispararTorpedoHacia(Algo42 unAlgo42) {
		TorpedoRastreador T =new TorpedoRastreador( this.posicionX(), this.posicionY(), false, this.plano);
		T.determinarNaveRastreada(unAlgo42);
		return T;
	}

	/*El bombardero tiene tres tipos de armas distintas para lanzar. Esta funcion crea un arma
	 * aleatoria: Busca un numero aleatorio del 1 al 3, si el numero es 1 lanza un laser, si es dos, un cohete
	 * y si es 3, un torpedo rastreador
	 */
	public Arma lanzarArmaAleatoriaHacia(Algo42 algo42) {

		int r = new Double(Math.random() * 3).intValue();

		if (r == 1) {
			return this.dispararLaser();
		}
		else if (r == 2) {
			return this.dispararCohete();
		}
		else {
			return this.dispararTorpedoHacia( this.plano.algo42 );
		}
	}
		
}
