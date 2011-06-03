package juego;
import juego.excepciones.AreaInvalidaError;
import juego.excepciones.ArmaNoDisponibleError;
import juego.excepciones.NaveARastrearError;


/* El Algo42 es la nave principal del juego
 * Es la unica nave que puede ser manejada por el jugador
 */	
public class Algo42 extends Nave {

	int torpedos;
	int cohetes;


	/* Crea una nueva instancia de algo42, con ubicacion(posicion_X,posicion_Y),
	 * en el plano de juego que recibe por parametro
	 */
	public Algo42(int posicion_X,int posicion_Y,Plano planoJuego) throws AreaInvalidaError {

		plano= planoJuego;
		energia= 100;
		torpedos=0;
		cohetes=0;
		destruida=false;
		operable=true;

		if ( ( (posicion_X<(planoJuego.ancho)) & (posicion_Y<(planoJuego.altura)) )  & ((posicion_X>=0) & (posicion_Y>=0) ) ) {
			planoJuego.introducirAlgo(this);
			rectangulo = new Rectangulo (5,3,posicion_X,posicion_Y);
		}
		else{
			throw new AreaInvalidaError("La nave debe ser creada en una posicion valida dentro del area de juego.");
		}
	}

	public Algo42() throws AreaInvalidaError{	}

	/* Quita la mitad de la energia de Algo42, perop siempre usando valores enteros */
	public void quitarMitadEnergia() {	
		energia = (int)(energia/2);
	}

	public void setCohetes(int cantidad) {
		cohetes = cantidad;
	}

	public void dispararCohete() throws ArmaNoDisponibleError {
		if ( cohetes <=0 ) {
			throw new ArmaNoDisponibleError("No hay cohetes que lanzar.");
		}
		new Cohete( this.posicionX(), this.posicionY(), true, this.plano);
		cohetes = (cohetes - 1);
	}
	
	public void dispararLaser() {
		new Laser( this.posicionX(), this.posicionY(), true, this.plano);
	}

	public void dispararTorpedoHacia(Nave unaNave) throws ArmaNoDisponibleError, NaveARastrearError {
		if (unaNave == this ) { 
			throw new NaveARastrearError("La nave rastreada no puede ser la misma algo");
		}
		if ( torpedos <=0 ) {
			throw new ArmaNoDisponibleError("No hay torpedos que lanzar.");
		}
		TorpedoRastreador T = new TorpedoRastreador(this.posicionX(), this.posicionY(), true, this.plano );
		T.determinarNaveRastreada( unaNave);
		torpedos =( torpedos - 1);
	}

	/* Aumenta las cantidades de torpedos y cohetes recibidos por parametro */
	public void aumentarArmas( int cantidadTorpedos, int cantidadCohetes ) {
		this.torpedos = (this.torpedos + cantidadTorpedos);
		this.cohetes = (this.cohetes + cantidadCohetes);
	}

	/* Recibe una cierta cantidad de puntos y los suma a la energia de la nave */
	public void ModificarEnergia (int puntosModificar) {
		energia = energia+puntosModificar;
		if ( energia <= 0 ) {
			destruida = true;
		}
	}

	/* La nave Algo42 se mueve un lugar hacia abajo */
	public void moverAbajo () throws AreaInvalidaError {	
		if((rectangulo.devolverPosicionY())<=0){
			throw new AreaInvalidaError("La nave ya no puede moverse mas hacia abajo");
		}
		this.determinarPosicion((rectangulo.devolverPosicionX()),(rectangulo.devolverPosicionY()) - 1);
	}

	/* La nave Algo42 se mueve un lugar hacia arriba */
	public void moverArriba () throws AreaInvalidaError {	
		if((rectangulo.devolverPosicionY() + (rectangulo.devolverAltura()))>plano.devolverAltura()){
			throw new AreaInvalidaError("La nave ya no puede moverse mas hacia arriba");
		}
		this.determinarPosicion((rectangulo.devolverPosicionX()),(rectangulo.devolverPosicionY()) + 1);
	}

	/* La nave Algo42 se mueve un lugar hacia la derecha */
	public void moverDerecha () throws AreaInvalidaError {
		if((rectangulo.devolverPosicionX() + (rectangulo.devolverAncho()))>plano.devolverAncho()){
			throw new AreaInvalidaError("La nave ya no puede moverse mas hacia la derecha");
		}
		this.determinarPosicion((rectangulo.devolverPosicionX())+1,(rectangulo.devolverPosicionY()));
	}

	/* La nave Algo42 se mueve un lugar hacia la izquierda */
	public void moverIzquierda () throws AreaInvalidaError {
		if((rectangulo.devolverPosicionX())<=0){
			throw new AreaInvalidaError("La nave ya no puede moverse mas hacia la izquierda");
		}
		this.determinarPosicion((rectangulo.devolverPosicionX())-1,(rectangulo.devolverPosicionY()));
	}

	public void recibirChoque() {
		this.ModificarEnergia(-30);
	}

	public int getCohetes() {		
		return cohetes;
	}

}
