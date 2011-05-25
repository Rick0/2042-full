package juego;

import excepciones.NaveNoDestruidaError;

public abstract class NaveNoOperable extends Nave{
/*Todas las naves que no pueden ser utilizadas por el jugador deben 
 * heredar de esta clase abstracta.*/
	
	boolean fueraDeJuego;
	boolean puntos;
	
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
}
