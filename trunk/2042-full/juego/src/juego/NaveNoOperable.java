package juego;

import java.util.Iterator;

import juego.excepciones.NaveNoDestruidaError;
import juego.excepciones.SuperposicionNavesError;


public abstract class NaveNoOperable extends Nave{
/*Todas las naves que no pueden ser utilizadas por el jugador deben 
 * heredar de esta clase abstracta.*/
	
	boolean fueraDeJuego;
	int puntos;
	
	abstract void IntentarAccionSobre(Algo42 algo42);
	/*La nave se mueve y lleva a cabo su funcion: Si esta en la posicion de algo42 lo choca. Si
	lanza un arma, la agrega a la lista de Armas.*/
	
	public abstract void mover() throws SuperposicionNavesError;
	/*Metodo para cambiar la posicion de la nave.*/
	
	public abstract void moverAlternativo() throws SuperposicionNavesError;
	/*"Este metodo debe ser llamado cuando, por alguna razon, el movimiento que realiza
	la nave por defecto no puede ser llevado a cabo.*/
	
	public void intentarMovimiento(){
	/*La nave intenta moverse en una posicion diferente valida del plano. 
	 * Para eso, primero realiza un movimiento por defecto (Implementado 
	 * en la funcion mover). Si al moverse levanta una excepcion por 
	 * superponerse con otra nave, uso la funcion moverAlternativo para 
	 * realizar otro tipo de movimiento. Si sigue levantando error, entonces
	 * lo deja en su lugar"
	 */
		
		double posx=this.posicionX();
		double posy=this.posicionY();	
		try{
			this.mover();
		}catch (SuperposicionNavesError e) {
				/*Si llego a la conclusion de que, moviendolo, causo que se superponga con
				 * otra nave, lo devuelvo a la posicion original y realizo algun movimiento
				 *  alternativo.
				 */
				this.determinarPosicion(posx, posy);
				try{
					this.moverAlternativo();
				} catch (SuperposicionNavesError e2){ 
					 /*Si con el movimiento alternativo sigue superponiendose con otra nave,
					 no le dejo moverse: Lo dejo en la posicion original*/
					 this.determinarPosicion(posx ,posy);
				}

		this.estaFueraDeArea();
		}
	}
		
	
	public void destruirse() throws NaveNoDestruidaError {
		/*Lleva a cabo las acciones correspondientes si debe destruirse*/
			if ( (this.devolverCantidadEnergia())>0 ){
				throw new NaveNoDestruidaError("La nave aun tiene energia en su tanque");
			}
			else {
				destruida = true;
				plano.agregarNaveEliminada( this );
			}
		}
	
	public void modificarEnergia(int puntosModificar) {
		/*Recibe una cierta cantidad de puntos y los suma a la energÃ­a de la nave.*/

			energia = energia+puntosModificar;
			if(energia <= 0) {
				try {
					this.destruirse();
				} catch ( NaveNoDestruidaError error ) {
					//La nave no sera destruida
				}
			}
		}
	public boolean seSuperponeConOtraNave() {
		//Devuelve true si la posicion de una nave se superpone con alguna otra de la lista.
		Nave nave;
		Iterator<Nave> i = this.plano.listaNaves.iterator();
		while ( i.hasNext() ) {
			nave = i.next();
			if ( nave.coincidePosicionCon(rectangulo) && (nave != this)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean estaFueraDeArea() {
		 /*Decide si la nave esta fuera de area,
		es decir si con su movimiento ya llego a una posicion fuera del plano. En ese caso,
		cambia su estado fuera de juego a true*/

			if ( (rectangulo.devolverPosicionX() > 100) || (rectangulo.devolverPosicionX() <0 ) || (rectangulo.devolverPosicionY() > 100) || (rectangulo.devolverPosicionY() < 0) ) {
				this.fueraDeJuego = true;
				return true;
			}
			this.fueraDeJuego = false;
			return false;
	}
	
	public boolean intentarChocar( Algo42 algo42) {
	//Recibe una nave Algo42 y la choca; Como consecuencia, la Algo42 pierde puntos 30 de su tanque de energia. Ademas, la nave es destruida."
	
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
	
	public void retirarse() {
	/*Con este metodo, las naves no operables empiezan a retroceder en linea recta 
	(Es decir, a ir hacia arriba, ya que las naves no operables siempre se mueven
	de arriba a abajo) hasta salir del area de juego.*/
	this.determinarPosicion(this.posicionX() , this.posicionY() +1);
	}
	
	public boolean estadoActualFueraDeJuego(){
	/*Devuelve el estado fueraDeJuego , que es un valor booleano.
	Devuelve true si la nave fue evaluada con la funcion estaFueraDeArea y dio
	positivo, lo cual significa que la nave esta ocupando un area que no le
	corresponde.*/
		return fueraDeJuego;
	}
	
}
