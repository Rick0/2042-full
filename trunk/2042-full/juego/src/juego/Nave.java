package juego;

public abstract class Nave extends ObjetoUbicable {
	
	/*Clase de la cual heredan todas las naves del juego*/
	
	boolean destruida;
	boolean operable;
	int energia;
	
	public void destruirse {
	"Lleva a cabo las acciones correspondientes si debe destruirse"
		if ( this.devolverCantidadEnergia>0 ){
			"NaveNoDestruidaError new signal: 'La nave aun tiene energia en su tanque'"
		}
		else {
			destruida = true;
			plano.agregarNaveEliminada( this );
		}
	}
	
	public int devolverCantidadEnergia() {
	"devuelve la cantidad de energia actual con la que cuenta la nave"

	return energia;
	
	}
	
	public boolean estadoActualDestruida() {
	"Devuelve True si la nave está destruida".

	return destruida;
	}
	
	
}
