package fiuba.algo3.juego.modelo;
import fiuba.algo3.juego.modelo.excepciones.AreaInvalidaError;
import fiuba.algo3.juego.modelo.excepciones.ArmaNoDisponibleError;
import fiuba.algo3.juego.modelo.excepciones.NaveARastrearError;


/* El Algo42 es la nave principal del juego
 * Es la unica nave que puede ser manejada por el jugador
 */	
public class Algo42 extends Nave {

	int torpedos;
	int cohetes;


	/* Crea una nueva instancia de algo42, con ubicacion(determinada por un punto),
	 * en el plano de juego que recibe por parametro
	 */
	public Algo42(Punto punto,Plano planoJuego) throws AreaInvalidaError {

		plano= planoJuego;
		energia= 100;
		torpedos=0;
		cohetes=0;
		destruida=false;
		operable=true;

		if ( ( (punto.getX()<(planoJuego.ancho)) & (punto.getY()<(planoJuego.altura)) )  & ((punto.getY()>=0) & (punto.getX()>=0) ) ) {
			planoJuego.introducirAlgo(this);
			rectangulo = new Rectangulo (5,3,punto);
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

	/*Dispara un cohete en la posicion del algo42*/
	public void dispararCohete() throws ArmaNoDisponibleError {
		if ( cohetes <=0 ) {
			throw new ArmaNoDisponibleError("No hay cohetes que lanzar.");
		}
		new Cohete( this.devolverPunto(), true, this.plano);
		cohetes = (cohetes - 1);
	}
	
	/*Crea una instancia de laser en la posicion del algo42*/
	public void dispararLaser() {
		new Laser( this.devolverPunto(), true, this.plano);
	}

	/*Crea una instancia de torpedoRastreador; recibe una nave por parametro,
	 * esa nave sera el objetivo del torpedo
	 */
	public void dispararTorpedoHacia(Nave unaNave) throws ArmaNoDisponibleError, NaveARastrearError {
		if (unaNave == this ) { 
			throw new NaveARastrearError("La nave rastreada no puede ser la misma algo");
		}
		if ( torpedos <=0 ) {
			throw new ArmaNoDisponibleError("No hay torpedos que lanzar.");
		}
		TorpedoRastreador T = new TorpedoRastreador(this.devolverPunto(), true, this.plano );
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
		if((this.devolverPunto().getY())<=0){
			throw new AreaInvalidaError("La nave ya no puede moverse mas hacia abajo");
		}
		Punto nuevoPunto= new Punto(this.devolverPunto().getX(),(this.devolverPunto().getY()-1));
		this.determinarPosicion(nuevoPunto);
	}

	/* La nave Algo42 se mueve un lugar hacia arriba */
	public void moverArriba () throws AreaInvalidaError {	
		if((this.devolverPunto().getY() + (rectangulo.devolverAltura()))>plano.devolverAltura()){
			throw new AreaInvalidaError("La nave ya no puede moverse mas hacia arriba");
		}
		Punto nuevaPosicion=new Punto(this.devolverPunto().getX(),(this.devolverPunto().getY()+ 1));
		this.determinarPosicion(nuevaPosicion);
	}

	/* La nave Algo42 se mueve un lugar hacia la derecha */
	public void moverDerecha () throws AreaInvalidaError {
		if((this.devolverPunto().getX() + (rectangulo.devolverAncho()))>plano.devolverAncho()){
			throw new AreaInvalidaError("La nave ya no puede moverse mas hacia la derecha");
		}
		Punto nuevaPosicion=new Punto(this.devolverPunto().getX() +1,(this.devolverPunto().getY()));
		this.determinarPosicion(nuevaPosicion);
	}

	/* La nave Algo42 se mueve un lugar hacia la izquierda */
	public void moverIzquierda () throws AreaInvalidaError {
		if((this.devolverPunto().getX())<=0){
			throw new AreaInvalidaError("La nave ya no puede moverse mas hacia la izquierda");
		}
		Punto nuevaPosicion=new Punto(this.devolverPunto().getX() - 1,(this.devolverPunto().getY()));
		this.determinarPosicion(nuevaPosicion);
	}

	public void recibirChoque() {
		this.ModificarEnergia(-30);
	}

	public int getCohetes() {		
		return cohetes;
	}

	@Override
	public void vivir() {
		// TODO Auto-generated method stub
		
	}

}
