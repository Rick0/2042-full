package juego;

public abstract class Item extends ObjetoUbicable {
	/*Clase abstracta de la cual heredaran los items del juego*/
	
	boolean usado;

	public abstract void intentarEfectoEn(Algo42 algo);
	/*Recibe una nave Algo42 (la nave manejada por el usuario) e intenta aplicar el efecto del item sobre ella:
	Para eso, el algo42 debe estar en la misma posicion que el item*/
	
	boolean usado (){
		/*Devuelve true si el item ya fue usado, false en caso contrario*/

		return usado;
	}

	public void noUsado() {
		// Modifica el estado a no usado
		usado = false;
	}
	
}
