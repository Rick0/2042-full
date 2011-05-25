package juego;

import java.util.Iterator;

import excepciones.NaveNoDestruidaError;

public abstract class NaveNoOperable extends Nave{
/*Todas las naves que no pueden ser utilizadas por el jugador deben 
 * heredar de esta clase abstracta.*/
	
	boolean fueraDeJuego;
	int puntos;
	
	abstract void IntentarAccionSobre(Algo42 algo42);
	/*La nave se mueve y lleva a cabo su funcion: Si esta en la posicion de algo42 lo choca. Si
lanza un arma, la agrega a la lista de Armas.*/
	
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
}
