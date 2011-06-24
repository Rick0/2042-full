package fiuba.algo3.juego.modelo;

import java.io.Serializable;
import fiuba.algo3.juego.modelo.excepciones.AreaInvalidaError;
import fiuba.algo3.juego.modelo.excepciones.ArmaNoDisponibleError;
import fiuba.algo3.juego.modelo.excepciones.NaveARastrearError;


/* El Algo42 es la nave principal del juego
 * Es la unica nave que puede ser manejada por el jugador
 */	
public class Algo42 extends Nave implements Serializable {

	private static final long serialVersionUID = -3316879072392921990L;
	int movPixel = 5;
	int torpedos;
	int cohetes;
	static int velocidadDisparoCohete = 20;
	int velocidadDisparoCoheteCont;
	static int velocidadDisparoTorpedo = 20;
	int velocidadDisparoTorpedoCont;


	/* Crea una nueva instancia de algo42, con ubicacion(determinada por un punto),
	 * en el plano de juego que recibe por parametro
	 */
	public Algo42(Punto punto,Plano planoJuego) throws AreaInvalidaError {

		velocidadDisparo = 25;
		velocidadDisparoCont = velocidadDisparo;
		velocidadDisparoCoheteCont = velocidadDisparoCohete;
		velocidadDisparoTorpedoCont = velocidadDisparoTorpedo;
		plano = planoJuego;
		energia = 100;
		torpedos = 0;
		cohetes = 0;
		estaDestruida = false;
		esOperable = true;

		if ( ( (punto.getX()<(planoJuego.ancho)) & (punto.getY()<(planoJuego.altura)) )  & ((punto.getY()>=0) & (punto.getX()>=0) ) ) {
			planoJuego.introducirAlgo42(this);
			rectangulo = new Rectangulo (64,64,punto);
		}
		else{
			throw new AreaInvalidaError("La nave debe ser creada en una posicion valida dentro del area de juego.");
		}
	}

	/* Crea una instancia de laser en la posicion del algo42 */
	public void dispararLaser() {
		if (velocidadDisparoCont == velocidadDisparo) {

			int ancho = rectangulo.devolverAncho();
			int altura = rectangulo.devolverAltura();
			Punto posLaser = new Punto(this.devolverPunto().getX()+(ancho/2)-9, this.devolverPunto().getY()+altura);
			new Laser(posLaser, true, this.plano);
			velocidadDisparoCont = 0;
		}
	}

	/* Dispara un cohete en la posicion del algo42 */
	public void dispararCohete() throws ArmaNoDisponibleError {

		if (velocidadDisparoCoheteCont == velocidadDisparoCohete) {

			if ( cohetes <= 0 ) {
				throw new ArmaNoDisponibleError("No hay cohetes que lanzar.");
			}

			int ancho = rectangulo.devolverAncho();
			int altura = rectangulo.devolverAltura();
			Punto posCohete = new Punto(this.devolverPunto().getX()+(ancho/2)-9, this.devolverPunto().getY()+altura);
			new Cohete(posCohete, true, this.plano);
			cohetes = (cohetes - 1);
			velocidadDisparoCoheteCont = 0;
		}
	}

	/* Crea una instancia de torpedoRastreador; recibe una nave por parametro,
	 * esa nave sera el objetivo del torpedo
	 */
	public void dispararTorpedoRastreadorHacia(Nave unaNave) throws ArmaNoDisponibleError, NaveARastrearError {

		if (velocidadDisparoTorpedoCont == velocidadDisparoTorpedo) {

			if (unaNave == this) { 
				throw new NaveARastrearError("La nave rastreada no puede ser la misma algo");
			}
			if (torpedos <= 0) {
				throw new ArmaNoDisponibleError("No hay torpedos que lanzar.");
			}

			int ancho = rectangulo.devolverAncho();
			int altura = rectangulo.devolverAltura();
			Punto posTorpedo = new Punto(this.devolverPunto().getX()+(ancho/2)-9, this.devolverPunto().getY()+altura);
			TorpedoRastreador unTorpedo = new TorpedoRastreador(posTorpedo, true, this.plano);
			unTorpedo.determinarNaveRastreada(unaNave);
			torpedos = (torpedos - 1);
			velocidadDisparoTorpedoCont = 0;
		}
	}

	/* Aumenta las cantidades de torpedos y cohetes recibidos por parametro */
	public void aumentarArmas(int cantidadTorpedos, int cantidadCohetes) {
		this.torpedos = (this.torpedos + cantidadTorpedos);
		this.cohetes = (this.cohetes + cantidadCohetes);
	}

	/* Este metodo se llama cuando se destruye el Algo42
	 * Este metodo lo resetea, dejandolo con sus valores iniciales
	 */
	private void resetearAlgo42() {

		velocidadDisparoCont = velocidadDisparo;
		velocidadDisparoCoheteCont = velocidadDisparoCohete;
		velocidadDisparoTorpedoCont = velocidadDisparoTorpedo;
		energia = 100;
		torpedos = 0;
		cohetes = 0;
		estaDestruida = false;

		Punto posInicial = new Punto(((this.plano.devolverAncho())/2)-32, (this.plano.devolverAltura()/6));
		rectangulo.cambiarPosicion(posInicial);
	}

	@Override
	/* Recibe una cierta cantidad de puntos y los suma a la energia de la nave */
	public void modificarEnergia (int energiaAModificar) {

		energia = energia + energiaAModificar;
		if ( energia <= 0 ) {

			this.estaDestruida = true;
			new NaveExplosion(this.devolverPunto(), this.plano);

			this.resetearAlgo42();
			this.plano.perderUnaVida();
		}
	}

	/* La nave Algo42 se mueve un lugar hacia abajo */
	public void moverAbajo () throws AreaInvalidaError {	
		if((this.devolverPunto().getY())<=0){
			throw new AreaInvalidaError("La nave ya no puede moverse mas hacia abajo");
		}
		Punto nuevoPunto= new Punto(this.devolverPunto().getX(), (this.devolverPunto().getY() - movPixel));
		this.cambiarPosicion(nuevoPunto);
	}

	/* La nave Algo42 se mueve un lugar hacia arriba */
	public void moverArriba () throws AreaInvalidaError {	
		if((this.devolverPunto().getY() + (rectangulo.devolverAltura()))>plano.devolverAltura()){
			throw new AreaInvalidaError("La nave ya no puede moverse mas hacia arriba");
		}
		Punto nuevaPosicion=new Punto(this.devolverPunto().getX(), (this.devolverPunto().getY() + movPixel));
		this.cambiarPosicion(nuevaPosicion);
	}

	/* La nave Algo42 se mueve un lugar hacia la derecha */
	public void moverDerecha () throws AreaInvalidaError {
		if((this.devolverPunto().getX() + (rectangulo.devolverAncho()))>plano.devolverAncho()){
			throw new AreaInvalidaError("La nave ya no puede moverse mas hacia la derecha");
		}
		Punto nuevaPosicion=new Punto((this.devolverPunto().getX()) + movPixel, (this.devolverPunto().getY()));
		this.cambiarPosicion(nuevaPosicion);
	}

	/* La nave Algo42 se mueve un lugar hacia la izquierda */
	public void moverIzquierda () throws AreaInvalidaError {
		if((this.devolverPunto().getX())<=0){
			throw new AreaInvalidaError("La nave ya no puede moverse mas hacia la izquierda");
		}
		Punto nuevaPosicion=new Punto((this.devolverPunto().getX()) - movPixel,(this.devolverPunto().getY()));
		this.cambiarPosicion(nuevaPosicion);
	}

	public void chocarCon(NaveNoOperable unaNave) {
		this.modificarEnergia(-30);
	}

	public void chocarCon(TanqueEnergia unTanque) {
		this.modificarEnergia(unTanque.getEnergia());
	}

	public void chocarCon(ArmaAbandonada unTanque) {
		this.aumentarArmas(unTanque.getNumeroTorpedos(), unTanque.getNumeroCohetes());
	}

	public int getCohetes() {		
		return cohetes;
	}

	public void setCohetes(int cantidad) {
		cohetes = cantidad;
	}

	public int getTorpedos() {		
		return torpedos;
	}

	public void setToperdos(int cantidad) {
		torpedos = cantidad;
	}

	public int getMovPixel() {
		return movPixel;
	}

	@Override
	public void vivir() {
		this.pasaUnTiempo();
	}

	@Override
	/* En cada instante, se actualiza el contador de velocidad de disparo de las 3 armas */
	public void pasaUnTiempo() {

		if (velocidadDisparoCont < velocidadDisparo) {
			velocidadDisparoCont++;
		}
		if (velocidadDisparoCoheteCont < velocidadDisparoCohete) {
			velocidadDisparoCoheteCont++;
		}
		if (velocidadDisparoTorpedoCont < velocidadDisparoTorpedo) {
			velocidadDisparoTorpedoCont++;
		}
	}

}
