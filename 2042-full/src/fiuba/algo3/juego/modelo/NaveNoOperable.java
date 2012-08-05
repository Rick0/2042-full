package fiuba.algo3.juego.modelo;

import java.util.Iterator;
import java.util.Random;
import fiuba.algo3.juego.modelo.excepciones.ItemNoDisponibleError;
import fiuba.algo3.juego.modelo.excepciones.NaveNoDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


/*Todas las naves que no pueden ser utilizadas por el jugador deben 
 * heredar de esta clase abstracta
 */
public abstract class NaveNoOperable extends Nave {

	private static final long serialVersionUID = 3275367912248566223L;
	boolean fueraDelPlano;
	int puntos;
	public boolean tengoQueHuir;
	final int chanceParaItems = 1000;
	int chanceTanqueVida;
	int chanceTanqueArma;
//	int chanceTanqueSuper;
	int cantAMover = 1;


	public NaveNoOperable() {
		super();
		this.tengoQueHuir = false;
	}

	public void huir() {
		tengoQueHuir = true;
	}

	/* Dependiendo del tipo de nave, esta disparara armas diferentes */
	public abstract void disparar();

	/* Metodo para cambiar la posicion de la nave */
	public abstract void mover() throws SuperposicionNavesError;

	/* Este metodo debe ser llamado cuando, por alguna razon, el movimiento que realiza
	 * la nave por defecto no puede ser llevado a cabo
	 */
	public abstract void moverAlternativo() throws SuperposicionNavesError;

	/*Cada nave de tipo no operable debe realizar ciertas acciones
	 * en cada turno, y para eso se llama a este metodo.
	 */
	public void vivir() {

		if (!estaDestruida) {

			if (tengoQueHuir) {
				this.retirarse();
				this.intentarChocar(this.plano.devolverAlgo42());
			}
			else {
				this.intentarMover();
				this.intentarChocar(this.plano.devolverAlgo42());
				this.pasaUnTiempo();
				this.disparar();
			}
		}
	}

	/* La nave intenta moverse en una posicion diferente valida del plano. 
	 * Para eso, primero realiza un movimiento por defecto (Implementado 
	 * en la funcion mover). Si al moverse levanta una excepcion por 
	 * superponerse con otra nave, uso la funcion moverAlternativo para 
	 * realizar otro tipo de movimiento. Si sigue levantando error, entonces
	 * lo deja en su lugar
	 */
	public void intentarMover() {

		Punto puntoOriginal = this.devolverPunto();
		try {
			this.mover();
		} catch (SuperposicionNavesError e) {
			/*Si llego a la conclusion de que, moviendolo, causo que se superponga con
			 * otra nave, lo devuelvo a la posicion original y realizo algun movimiento alternativo
			 */
			this.cambiarPosicion(puntoOriginal);
			try {
				this.moverAlternativo();
			} catch (SuperposicionNavesError e2){ 
				 /* Si con el movimiento alternativo sigue superponiendose con otra nave,
				  * no le dejo moverse: Lo dejo en la posicion original 
				  */
				 this.cambiarPosicion(puntoOriginal);
			}
		}

		this.estaFueraDelPlano();
	}

	/* Devuelve true si la posicion de una nave se superpone con alguna otra de la lista */
	public boolean seSuperponeConOtraNave() {

		Nave nave;
		Iterator<NaveNoOperable> i = this.plano.listaNaves.iterator();
		while ( i.hasNext() ) {
			nave = i.next();
			if ( nave.coincidePosicionCon(rectangulo) && (nave != this)) {
				return true;
			}
		}
		return false;
	}

	/* Decide si la nave esta fuera de area, es decir si con su movimiento ya llego a una posicion fuera del plano.
	 * En ese caso, cambia su estado fuera de juego a true 
	 */
	public boolean estaFueraDelPlano() {

		if ( (this.devolverPunto().getX() > plano.devolverAncho()) || (this.devolverPunto().getX() < 0 ) || (this.devolverPunto().getY() > plano.devolverAltura()) || (devolverPunto().getY() < 0) ) {
			this.fueraDelPlano = true;
			try {
				this.plano.agregarNaveEliminada(this);
			} catch (NaveNoDestruidaError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		this.fueraDelPlano = false;
		return false;
	}

	/* Si la nave choca con Algo42:
	 * Algo42 pierde energia, y la nave es destruida
	 */
	public boolean intentarChocar(Algo42 algo42) {

		if (algo42.coincidePosicionCon(this.rectangulo)) {
			algo42.chocarCon(this);
			this.chocarCon(algo42);
			return true;
		}
		return false;
	}

	public int devolverPuntosPorEliminacion() {
		return this.puntos;
	}

	/* Con este metodo, las naves no operables empiezan a retroceder en linea recta 
	 * (Es decir, a ir hacia arriba, ya que las naves no operables siempre se mueven
	 * de arriba a abajo) hasta salir del area de juego
	 */
	public void retirarse() {
		Punto nuevaPosicion = new Punto(this.devolverPunto().getX(),this.devolverPunto().getY()+2);
		this.cambiarPosicion(nuevaPosicion);
		this.estaFueraDelPlano();
	}

	/* Devuelve el estado fueraDeJuego , que es un valor booleano.
	 * Devuelve true si la nave fue evaluada con la funcion estaFueraDeArea y dio
	 * positivo, lo cual significa que la nave esta ocupando un area que no le corresponde
	 */
	public boolean fueraDelPlano() {
		return fueraDelPlano;
	}

	/* La nave dispara un laser */
	public void dispararLaser() {
		new Laser(this.devolverPuntoParaArma(), false, this.plano);
	}

	/* La nave dispara un cohete */
	public void dispararCohete() {
		new Cohete(this.devolverPuntoParaArma(), false, this.plano);
	}

	/* La nave dispara un torpedo simple */
	public void dispararTorpedo() {
		new TorpedoSimple(this.devolverPuntoParaArma(), false, this.plano);
	}

	/* La nave dispara un torpedo adaptable */
	public void dispararTorpedoAdaptable() {
		new TorpedoAdaptable(this.devolverPuntoParaArma(), false, this.plano);
	}

	/* La nave dispara un torpedo rastreador */
	public void dispararTorpedoRastreador() {
		new TorpedoRastreador(this.devolverPuntoParaArma(), false, this.plano);
	}

	/* La nave dispara un torpedo rastreador V2 */
	public void dispararTorpedoRastreadorV2() {
	
		Random generadorRandom = new Random();
		int ancho = rectangulo.devolverAncho();
		int altura = rectangulo.devolverAltura();
		int cantTorpedosDisp = generadorRandom.nextInt(2) + 3;
		int i = 0;
		
		while (i < cantTorpedosDisp) {
			int x = generadorRandom.nextInt(ancho);
			int y = generadorRandom.nextInt(altura);
			
			Punto posTorpedo = new Punto(this.devolverPunto().getX() + x, this.devolverPunto().getY() + y);
			new TorpedoRastreadorV2(posTorpedo, false, this.plano);
			i++;
		}
	}

	/* Devuelve el punto en donde se va a crear el arma disparada */
	private Punto devolverPuntoParaArma() {
		int posEnX = (int)((this.devolverPunto().getX() + (this.devolverAncho() / 2)) - (this.devolverAncho() / 10));
		int posEnY = (int)(this.devolverPunto().getY());
		Punto puntoArma = new Punto(posEnX, posEnY);
		return puntoArma;
	}

	/* Una nave no operable choca con Algo42 
	 * El resultado es que la nave no operable se destruye
	 */
	public void chocarCon(Algo42 algo42) {

		this.modificarEnergia( -(this.devolverEnergia()) );
	}

	/* Me indica si la nave esta huyendo o no */
	public boolean estaHuyendo() {
		return tengoQueHuir;
	}

	/* Crea una instancia de ArmaAbandonada y la devuelve */ 
	public Item dejarTanqueArma() throws ItemNoDisponibleError {

		if (!this.estaDestruida)
			throw new ItemNoDisponibleError("La nave aun no esta destruida, no puede dejar armas");
		Item itemDejado = new ArmaAbandonada(this.devolverPunto(), this.plano);		
		return itemDejado;
	}
	
	/* Crea una instancia de TanqueEnergia y la devuelve */
	public Item dejarTanqueVida() throws ItemNoDisponibleError {
		
		if (!this.estaDestruida)
			throw new ItemNoDisponibleError("La nave aun no esta destruida, no puede dejar armas.");
		Item itemDejado = new TanqueEnergia(this.devolverPunto(), this.plano);
		return itemDejado;
	}

	@Override
	/* Lleva a cabo las acciones correspondientes si debe destruirse */
	public void destruirse() throws NaveNoDestruidaError {

		if (this.devolverEnergia() > 0)
			throw new NaveNoDestruidaError("La nave aun tiene energia en su tanque");
		else {
			estaDestruida = true;
			plano.agregarNaveEliminada(this);
			new NaveExplosion(this.devolverPunto(), this.plano);
			
			Random generadorRandom = new Random();
			int numeroTanqueVida = generadorRandom.nextInt(this.chanceParaItems);
			int numeroTanqueArma = generadorRandom.nextInt(this.chanceParaItems);
			
			if (numeroTanqueVida < this.chanceTanqueVida) {
				try {
					this.dejarTanqueVida();
				} catch (ItemNoDisponibleError error) { 
					// Esto no puede suceder
					return;
				}
			}
			if (numeroTanqueArma < this.chanceTanqueArma) {
				try {
					this.dejarTanqueArma();
				} catch (ItemNoDisponibleError error) { 
					// Esto no puede suceder
					return;
				}
			}
		}
	}
	
}
