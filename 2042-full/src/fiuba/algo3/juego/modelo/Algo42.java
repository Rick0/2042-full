package fiuba.algo3.juego.modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;
import fiuba.algo3.juego.modelo.excepciones.AreaInvalidaError;
import fiuba.algo3.juego.modelo.excepciones.ArmaNoDisponibleError;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;


/* El Algo42 es la nave principal del juego
 * Es la unica nave que puede ser manejada por el jugador
 */	
public class Algo42 extends Nave implements Serializable {

	private static final long serialVersionUID = -3316879072392921990L;
	static int cantAMover = 5;
	static int cantAMoverSuperMode = 5;
	int cohetes;
	int cohetesV2;
	int torpedos;
	int torpedosV2;
	static final int velocidadDisparoCohete = 15;
	int velocidadDisparoCoheteCont;
	static final int velocidadDisparoCoheteV2 = 50;
	int velocidadDisparoCoheteV2Cont;
	static final int velocidadDisparoTorpedo = 18;
	int velocidadDisparoTorpedoCont;
	static final int velocidadDisparoTorpedoV2 = 25;
	int velocidadDisparoTorpedoV2Cont;
	static final int laserV2offSet = 18;
	
	public static final int energiaMaxima = 123;
	static final int energiaInicial = 100;
	boolean puedeDisparar;
	int superMode;	// 0 es false, 1 es true
	static int tiempoSuperMode = 500;
	int tiempoSuperModeCont;
	static int afterImageDelay = 6;
	int afterImageDelayCont;
	boolean modoAutomatico;
	int efectoAutomaticoMover;
	int efectoAutomaticoDisparar;
	static final int automaticoMoverDelay = 0;
	int automaticoMoverDelayCont;
	static final int autoRecuperarseDelay = 10;
	int autoRecuperarseCont;
	

	/* Crea una nueva instancia de algo42, con ubicacion(determinada por un punto),
	 * en el plano de juego que recibe por parametro
	 */
	public Algo42(Punto punto, Plano planoJuego) throws AreaInvalidaError {

		velocidadDisparo = 12;
		velocidadDisparoCont = velocidadDisparo;
		velocidadDisparoCoheteCont = velocidadDisparoCohete;
		velocidadDisparoCoheteV2Cont = velocidadDisparoCoheteV2;
		velocidadDisparoTorpedoCont = velocidadDisparoTorpedo;
		velocidadDisparoTorpedoV2Cont = velocidadDisparoTorpedoV2;
		plano = planoJuego;
		energia = energiaInicial;
		cohetes = 0;
		cohetesV2 = 0;
		torpedos = 0;
		torpedosV2 = 0;
		estaDestruida = false;
		esOperable = true;
		puedeDisparar = true;
		superMode = 0;
		tiempoSuperModeCont = tiempoSuperMode;
		modoAutomatico = false;
		efectoAutomaticoMover = 0;
		efectoAutomaticoDisparar = 0;
		automaticoMoverDelayCont = automaticoMoverDelay;
		autoRecuperarseCont = autoRecuperarseDelay;

		if ( ( (punto.getX()<(planoJuego.ancho)) & (punto.getY()<(planoJuego.altura)) )  & ((punto.getY()>=0) & (punto.getX()>=0) ) ) {
			planoJuego.introducirAlgo42(this);
			rectangulo = new Rectangulo (62,52,punto);
		}
		else{
			throw new AreaInvalidaError("La nave debe ser creada en una posicion valida dentro del area de juego.");
		}
	}

	public Algo42()  {

		velocidadDisparo = 12;
		velocidadDisparoCont = velocidadDisparo;
		velocidadDisparoCoheteCont = velocidadDisparoCohete;
		velocidadDisparoCoheteV2Cont = velocidadDisparoCoheteV2;
		velocidadDisparoTorpedoCont = velocidadDisparoTorpedo;
		velocidadDisparoTorpedoV2Cont = velocidadDisparoTorpedoV2;
		plano = null;
		energia = energiaInicial;
		cohetes = 0;
		cohetesV2 = 0;
		torpedos = 0;
		torpedosV2 = 0;
		estaDestruida = false;
		esOperable = true;
		puedeDisparar = true;
		superMode = 0;
		tiempoSuperModeCont = tiempoSuperMode;
		modoAutomatico = false;
		efectoAutomaticoMover = 0;
		efectoAutomaticoDisparar = 0;
		automaticoMoverDelayCont = automaticoMoverDelay;
		autoRecuperarseCont = autoRecuperarseDelay;
		
		rectangulo = null;
	}

	/* Crea una instancia de laser en la posicion del algo42 */
	public void dispararLaser() {

		if (this.superMode == 0)
			this.dispararLaserV1();
		else
			this.dispararLaserV2();
		
		this.efectoAutomaticoDisparar = 1;
	}
	
	private void dispararLaserV1() {

		if ((velocidadDisparoCont == velocidadDisparo) && puedeDisparar) {

			int ancho = rectangulo.devolverAncho();
			int altura = rectangulo.devolverAltura();
			Punto posLaser = new Punto(this.devolverPunto().getX()+(ancho/2)-6, this.devolverPunto().getY()+altura);
			new Laser(posLaser, true, this.plano);
			velocidadDisparoCont = 0;
		}
	}
	
	private void dispararLaserV2() {

		if ((velocidadDisparoCont == velocidadDisparo) && puedeDisparar) {

			int ancho = rectangulo.devolverAncho();
			int altura = rectangulo.devolverAltura();
			Punto posLaser1 = new Punto(this.devolverPunto().getX()+(ancho/2)-6+laserV2offSet, this.devolverPunto().getY()+altura);
			Punto posLaser2 = new Punto(this.devolverPunto().getX()+(ancho/2)-6-laserV2offSet, this.devolverPunto().getY()+altura);
			new Laser(posLaser1, true, this.plano);
			new Laser(posLaser2, true, this.plano);
			velocidadDisparoCont = 0;
		}
	}

	/* Crea una instancia de cohete en la posicion del algo42 */
	public void dispararCohete() throws ArmaNoDisponibleError {

		if (this.superMode == 0)
			this.dispararCoheteV1();
		else
			this.dispararCoheteV2();
			
		if (cohetes <= 0  ||  cohetesV2 <= 0)
			this.efectoAutomaticoDisparar = 0;
		else
			this.efectoAutomaticoDisparar = 2;
	}

	public void dispararCoheteV1() throws ArmaNoDisponibleError {

		if ((velocidadDisparoCoheteCont == velocidadDisparoCohete) && puedeDisparar) {

			if ( cohetes <= 0 )
				throw new ArmaNoDisponibleError("No hay cohetes que lanzar.");

			int ancho = rectangulo.devolverAncho();
			int altura = rectangulo.devolverAltura();
			Punto posCohete = new Punto(this.devolverPunto().getX()+(ancho/2)-6, this.devolverPunto().getY()+altura);
			new Cohete(posCohete, true, this.plano);
			cohetes = (cohetes - 1);
			velocidadDisparoCoheteCont = 0;
		}
	}

	public void dispararCoheteV2() throws ArmaNoDisponibleError {

		if ((velocidadDisparoCoheteV2Cont == velocidadDisparoCoheteV2) && puedeDisparar) {

			if ( cohetesV2 <= 0 )
				throw new ArmaNoDisponibleError("No hay cohetesV2 que lanzar.");

			int ancho = rectangulo.devolverAncho();
			int altura = rectangulo.devolverAltura();
			Punto posCohete = new Punto(this.devolverPunto().getX()+(ancho/2)-6, this.devolverPunto().getY()+altura);
			new CoheteV2(posCohete, true, this.plano);
			cohetesV2 = (cohetesV2 - 1);
			velocidadDisparoCoheteV2Cont = 0;
		}
	}

	/* Crea una instancia de torpedoRastreador */
	public void dispararTorpedoRastreador() throws ArmaNoDisponibleError {

		if (this.superMode == 0)
			this.dispararTorpedoRastreadorV1();
		else
			this.dispararTorpedoRastreadorV2();
			
		if (torpedos <= 0  ||  torpedosV2 <= 0)
			this.efectoAutomaticoDisparar = 0;
		else
			this.efectoAutomaticoDisparar = 3;
	}

	private void dispararTorpedoRastreadorV1() throws ArmaNoDisponibleError {

		if ((velocidadDisparoTorpedoCont == velocidadDisparoTorpedo) && puedeDisparar) {

			if (torpedos <= 0)
				throw new ArmaNoDisponibleError("No hay torpedos que lanzar.");

			int ancho = rectangulo.devolverAncho();
			int altura = rectangulo.devolverAltura();
			Punto posTorpedo = new Punto(this.devolverPunto().getX()+(ancho/2)-9, this.devolverPunto().getY()+altura);
			new TorpedoRastreador(posTorpedo, true, this.plano);
			torpedos = (torpedos - 1);
			velocidadDisparoTorpedoCont = 0;
		}
	}

	private void dispararTorpedoRastreadorV2() throws ArmaNoDisponibleError {

		if ((velocidadDisparoTorpedoV2Cont == velocidadDisparoTorpedoV2) && puedeDisparar) {

			if (torpedosV2 <= 0)
				throw new ArmaNoDisponibleError("No hay torpedosV2 que lanzar.");
			
			Random generadorRandom = new Random();
			int ancho = rectangulo.devolverAncho();
			int altura = rectangulo.devolverAltura();
			int cantTorpedosDisp = generadorRandom.nextInt(5) + 3;
			int i = 0;
			
			while (i < cantTorpedosDisp  &&  torpedosV2 > 0) {
				int x = generadorRandom.nextInt(ancho);
				int y = generadorRandom.nextInt(altura);
				
				Punto posTorpedo = new Punto(this.devolverPunto().getX() + x, this.devolverPunto().getY() + y);
				new TorpedoRastreadorV2(posTorpedo, true, this.plano);
				torpedosV2 = (torpedosV2 - 1);
				i++;
			}
			
			velocidadDisparoTorpedoV2Cont = 0;
		}
	}

	/* Aumenta las cantidades de las armas recibidas por parametro */
	public void aumentarArmas(int cantidadTorpedos, int cantidadCohetes, int cantidadTorpedosV2, int cantidadCohetesV2) {
		this.torpedos = this.torpedos + cantidadTorpedos;
		this.cohetes  = this.cohetes  + cantidadCohetes;
		this.torpedosV2 = this.torpedosV2 + cantidadTorpedosV2;
		this.cohetesV2  = this.cohetesV2  + cantidadCohetesV2;
	}

	/* Este metodo se llama cuando se destruye el Algo42
	 * Este metodo lo resetea, dejandolo con sus valores iniciales
	 */
	private void resetearAlgo42() {

		velocidadDisparoCont = velocidadDisparo;
		velocidadDisparoCoheteCont = velocidadDisparoCohete;
		velocidadDisparoCoheteV2Cont = velocidadDisparoCoheteV2;
		velocidadDisparoTorpedoCont = velocidadDisparoTorpedo;
		velocidadDisparoTorpedoV2Cont = velocidadDisparoTorpedoV2;
		energia = energiaInicial;
		cohetes = 0;
		cohetesV2 = 0;
		torpedos = 0;
		torpedosV2 = 0;
		estaDestruida = false;
		superMode = 0;
		tiempoSuperModeCont = tiempoSuperMode;
		modoAutomatico = false;
		efectoAutomaticoMover = 0;
		efectoAutomaticoDisparar = 0;
		automaticoMoverDelayCont = automaticoMoverDelay;
		autoRecuperarseCont = autoRecuperarseDelay;

		Punto posInicial = new Punto((((this.plano.devolverAncho())/2) - (this.devolverAncho()/2)), (this.plano.devolverAltura()/6));
		rectangulo.cambiarPosicion(posInicial);
	}

	@Override
	/* Recibe una cierta cantidad de puntos y los suma a la energia de la nave */
	public void modificarEnergia (int energiaAModificar) {

		energia = energia + energiaAModificar;

		if (energia > energiaMaxima) {
			energia = energiaMaxima;
		}

		if (energia <= 0) {
		
			this.estaDestruida = true;
			new NaveExplosion(this.devolverPunto(), this.plano);

			this.resetearAlgo42();
			this.plano.perderUnaVida();
		}
	}

	/* La nave Algo42 se mueve un lugar hacia abajo */
	public void moverAbajo () {	
		if ((this.devolverPunto().getY()) <= 0)
			this.efectoAutomaticoMover = 0;
		else {
			Punto nuevoPunto = new Punto(this.devolverPunto().getX(), (this.devolverPunto().getY() - this.cantAMoverse() ));
			this.cambiarPosicion(nuevoPunto);
		}
	}

	/* La nave Algo42 se mueve un lugar hacia arriba */
	public void moverArriba () {	
		if ((this.devolverPunto().getY() + (rectangulo.devolverAltura())) > plano.devolverAltura())
			this.efectoAutomaticoMover = 0;
		else {
			Punto nuevaPosicion = new Punto(this.devolverPunto().getX(), (this.devolverPunto().getY() + this.cantAMoverse() ));
			this.cambiarPosicion(nuevaPosicion);
		}
	}

	/* La nave Algo42 se mueve un lugar hacia la derecha */
	public void moverDerecha () {
		if ((this.devolverPunto().getX() + (rectangulo.devolverAncho())) > plano.devolverAncho())
			this.efectoAutomaticoMover = 0;
		else {
			Punto nuevaPosicion = new Punto((this.devolverPunto().getX()) + this.cantAMoverse(), (this.devolverPunto().getY()));
			this.cambiarPosicion(nuevaPosicion);
		}
	}

	/* La nave Algo42 se mueve un lugar hacia la izquierda */
	public void moverIzquierda () {
		if ((this.devolverPunto().getX()) <= 0)
			this.efectoAutomaticoMover = 0;
		else {
			Punto nuevaPosicion = new Punto((this.devolverPunto().getX()) - this.cantAMoverse(), (this.devolverPunto().getY()));
			this.cambiarPosicion(nuevaPosicion);
		}
	}

	public void chocarCon(NaveNoOperable unaNave) {
		this.modificarEnergia(-30);
	}

	public void chocarCon(TanqueEnergia unTanque) {
		this.modificarEnergia(unTanque.getEnergia());
	}

	public void chocarCon(ArmaAbandonada unTanque) {
		this.aumentarArmas(unTanque.getNumeroTorpedos(), unTanque.getNumeroCohetes(), unTanque.getNumeroTorpedosV2(), unTanque.getNumeroCohetesV2() );
	}

	public int getCohetes() {		
		return cohetes;
	}

	public void setCohetes(int cantidad) {
		cohetes = cantidad;
	}

	public int getCohetesV2() {		
		return cohetesV2;
	}

	public void setCohetesV2(int cantidad) {
		cohetesV2 = cantidad;
	}

	public int getTorpedos() {		
		return torpedos;
	}

	public void setToperdos(int cantidad) {
		torpedos = cantidad;
	}

	public int getTorpedosV2() {		
		return torpedosV2;
	}

	public void setToperdosV2(int cantidad) {
		torpedosV2 = cantidad;
	}

	public int devolverCantidadAMover() {
		return cantAMover;
	}

	@Override
	public void vivir() {
		this.pasaUnTiempo();
		this.efectoSuperMode();
		try {
			this.efectoModoAutomatico();
		} catch (AreaInvalidaError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ArmaNoDisponibleError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void persistir() {

		try {
			ObjectOutputStream oos = new ObjectOutputStream(
			new FileOutputStream( "algo42.dat" ));
			oos.writeObject(this);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Algo42 restaurar() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("algo42.dat"));
			Algo42 algo = (Algo42) ois.readObject();
			ois.close();
			return algo;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
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
		if (velocidadDisparoCoheteV2Cont < velocidadDisparoCoheteV2) {
			velocidadDisparoCoheteV2Cont++;
		}
		if (velocidadDisparoTorpedoCont < velocidadDisparoTorpedo) {
			velocidadDisparoTorpedoCont++;
		}
		if (velocidadDisparoTorpedoV2Cont < velocidadDisparoTorpedoV2) {
			velocidadDisparoTorpedoV2Cont++;
		}
	}

	public void estadoPuedeDisparar(boolean estado) {
		this.puedeDisparar = estado;
	}
	
	private void autoRecuperarse() {
	
		if (autoRecuperarseCont >= autoRecuperarseDelay) {
			this.energia++;
			autoRecuperarseCont = 0;
		}
		else
			autoRecuperarseCont++;
	}

	private int cantAMoverse() {
		return (cantAMover + cantAMoverSuperMode * superMode);
	}
	
	public void entrarASuperMode() {
		this.superMode = 1;
		this.afterImageDelayCont = afterImageDelay;
	}
	
	private void salirDeSuperMode() {
		this.superMode = 0;
		this.tiempoSuperModeCont = tiempoSuperMode;
	}
	
	public int estadoSuperMode() {
		return this.superMode;
	}
	
	public int tiempoSuperModeRestante() {
		return this.tiempoSuperModeCont;
	}
	
	private void efectoModoAutomatico() throws AreaInvalidaError, ArmaNoDisponibleError {
	
		if (this.modoAutomatico == true) {
		
			if (automaticoMoverDelayCont >= automaticoMoverDelay) {
				switch (this.efectoAutomaticoMover) {
					case 1: {
						this.moverAbajo();
						break;
					}
					case 2: {
						this.moverArriba();
						break;
					}
					case 3: {
						this.moverDerecha();
						break;
					}
					case 4: {
						this.moverIzquierda();
						break;
					}
					default: {
						break;
					}
				}
				automaticoMoverDelayCont = 0;
			}
			else
				automaticoMoverDelayCont++;
			
			switch (this.efectoAutomaticoDisparar) {
				case 1: {
					this.dispararLaser();
					break;
				}
				case 2: {
			//		this.dispararCohete();
					break;
				}
				case 3: {
			//		this.dispararTorpedoRastreador();
					break;
				}
				default: {
					break;
				}
			}
		}
	}
	
	private void efectoSuperMode() {
		if (this.superMode == 1) {
			
			if (this.energia < Algo42.energiaMaxima)
				this.autoRecuperarse();
			if (this.tiempoSuperModeCont > 0)
				this.tiempoSuperModeCont--;
			if (this.tiempoSuperModeCont <= 0)
				this.salirDeSuperMode();
			if (afterImageDelayCont >= afterImageDelay) {
				try {
					new Algo42afterImage(this.devolverPunto(), this.plano);
				} catch (NaveDestruidaError e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				afterImageDelayCont = 0;
			}
			else
				afterImageDelayCont++;
		}
	}
	
	public void cambiarModoAutomatico() {
		if (this.modoAutomatico)
			this.modoAutomatico = false;
		else
			this.modoAutomatico = true;
	}
	
	public void cambiarEfectoAutomaticoMover(int direccion) {
		this.efectoAutomaticoMover = direccion;
	}
	
	public boolean estadoModoAutomatico() {
		return this.modoAutomatico;
	}
	
}
