package fiuba.algo3.juego.modelo;


/* Clase abstracta de la cual heredaran los items del juego */
public abstract class Item extends ObjetoUbicable {

	boolean usado;


	/* Recibe una nave Algo42 (la nave manejada por el usuario)
	 * e intenta aplicar el efecto del item sobre ella.
	 * Para eso, el algo42 debe estar en la misma posicion que el item
	 */
	public abstract void intentarEfectoEn(Algo42 algo);

	/*Cada item debe realizar algun efecto en algo42,
	* dependiendo de que tipo de item sea*/
	public void vivir(){

		this.intentarEfectoEn(plano.algo42());
		if(usado==true){
			plano.agregarItemUsado(this);
		}
	}


	/* Devuelve true si el item ya fue usado, false en caso contrario */
	boolean usado () {
		return usado;
	}

	/* Modifica el estado a no usado */
	public void noUsado() {
		usado = false;
	}

}