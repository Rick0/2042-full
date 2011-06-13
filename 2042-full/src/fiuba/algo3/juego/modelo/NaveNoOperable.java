package fiuba.algo3.juego.modelo;

import java.util.Iterator;

import fiuba.algo3.juego.modelo.excepciones.NaveNoDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;



/*Todas las naves que no pueden ser utilizadas por el jugador deben 
 * heredar de esta clase abstracta
 */
public abstract class NaveNoOperable extends Nave {

	boolean fueraDeJuego;
	int puntos;


	/* La nave se mueve y lleva a cabo su funcion: Si esta en la posicion de algo42 lo choca.
	 * Si lanza un arma, la agrega a la lista de Armas
	 */
	abstract void intentarAccionSobre(Algo42 algo42);

	/* Metodo para cambiar la posicion de la nave */
	public abstract void mover() throws SuperposicionNavesError;

	/* Este metodo debe ser llamado cuando, por alguna razon, el movimiento que realiza
	 * la nave por defecto no puede ser llevado a cabo
	 */
	public abstract void moverAlternativo() throws SuperposicionNavesError;

	/*Cada nave de tipo no operable debe realizar ciertas acciones
	 * en cada turno, y para eso se llama a este metodo.
	 * */
	public void vivir(){
		this.intentarMovimiento();
		this.intentarAccionSobre(plano.algo42());
	}


	/* La nave intenta moverse en una posicion diferente valida del plano. 
	 * Para eso, primero realiza un movimiento por defecto (Implementado 
	 * en la funcion mover). Si al moverse levanta una excepcion por 
	 * superponerse con otra nave, uso la funcion moverAlternativo para 
	 * realizar otro tipo de movimiento. Si sigue levantando error, entonces
	 * lo deja en su lugar
	 */
	public void intentarMovimiento() {

		Punto puntoOriginal=this.devolverPunto();
		try {
			this.mover();
		} catch (SuperposicionNavesError e) {
			/*Si llego a la conclusion de que, moviendolo, causo que se superponga con
			 * otra nave, lo devuelvo a la posicion original y realizo algun movimiento alternativo
			 */
			this.determinarPosicion(puntoOriginal);
			try {
				this.moverAlternativo();
			} catch (SuperposicionNavesError e2){ 
				 /* Si con el movimiento alternativo sigue superponiendose con otra nave,
				  * no le dejo moverse: Lo dejo en la posicion original 
				  */
				 this.determinarPosicion(puntoOriginal);
			}

		this.estaFueraDeArea();
		}
	}

	/* Lleva a cabo las acciones correspondientes si debe destruirse */
	public void destruirse() throws NaveNoDestruidaError {
		
		if ( (this.devolverCantidadEnergia())>0 ) {
			throw new NaveNoDestruidaError("La nave aun tiene energia en su tanque");
		}
		else {
			destruida = true;
			plano.agregarNaveEliminada(this);
		}
	}

	/* Recibe una cierta cantidad de puntos y los suma a la energÃ­a de la nave */
	public void modificarEnergia(int puntosModificar) {

		energia = energia+puntosModificar;
		if(energia <= 0) {
			try {
				this.destruirse();
			} catch ( NaveNoDestruidaError error ) {
				//La nave no sera destruida
			}
		}
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
	public boolean estaFueraDeArea() {

		if ( (this.devolverPunto().getX() > plano.devolverAncho()) || (this.devolverPunto().getX() <0 ) || (this.devolverPunto().getY() > plano.devolverAltura()) || (devolverPunto().getY() < 0) ) {
			this.fueraDeJuego = true;
			return true;
		}
		this.fueraDeJuego = false;
		return false;
	}

	/* Recibe una nave Algo42 y la choca. Como consecuencia,
	 * Algo42 pierde puntos 30 de su tanque de energia.
	 * Ademas, la nave es destruida
	 */
	public boolean intentarChocar(Algo42 algo42) {

		if (algo42.coincidePosicionCon(this.rectangulo)) {
			algo42.recibirChoque();
			destruida = true;
			return true;
		}
		return false;
	}

	public int devolverPuntosPorEliminacion() {
		return puntos;
	}

	/* Con este metodo, las naves no operables empiezan a retroceder en linea recta 
	 * (Es decir, a ir hacia arriba, ya que las naves no operables siempre se mueven
	 * de arriba a abajo) hasta salir del area de juego
	 */
	public void retirarse() {
		Punto nuevaPosicion=new Punto(this.devolverPunto().getX(),this.devolverPunto().getY()+1);
		this.determinarPosicion(nuevaPosicion);
	}

	/* Devuelve el estado fueraDeJuego , que es un valor booleano.
	 * Devuelve true si la nave fue evaluada con la funcion estaFueraDeArea y dio
	 * positivo, lo cual significa que la nave esta ocupando un area que no le corresponde
	 */
	public boolean estadoActualFueraDeJuego() {
		return fueraDeJuego;
	}

}
