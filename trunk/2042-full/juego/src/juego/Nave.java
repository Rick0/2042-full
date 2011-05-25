package juego;

import excepciones.NaveNoDestruidaError;

public abstract class Nave extends ObjetoUbicable {
	
	/*Clase de la cual heredan todas las naves del juego*/
	
	boolean destruida;
	boolean operable;
	int energia;
	
	public abstract void modificarEnergia(int danio) throws NaveNoDestruidaError;
	
	public int devolverCantidadEnergia() {
	/*devuelve la cantidad de energia actual con la que cuenta la nave*/

	return energia;
	
	}
	
	public boolean estadoActualDestruida() {
	/*Devuelve True si la nave esta destruida*/

	return destruida;
	}
	
	public boolean operable(){
	/*Devuelve true si se trata de una nave operable; false en caso contrario*/
		return operable;
	}
		
}