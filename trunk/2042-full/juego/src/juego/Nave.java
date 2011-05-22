package juego;
import excepciones.NaveNoDestruidaError;

public abstract class Nave extends ObjetoUbicable {
	
	/*Clase de la cual heredan todas las naves del juego*/
	
	boolean destruida;
	boolean operable;
	int energia;
	
	public int devolverCantidadEnergia() {
	/*devuelve la cantidad de energia actual con la que cuenta la nave*/

	return energia;
	
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
	
	public boolean estadoActualDestruida() {
	/*Devuelve True si la nave esta destruida*/

	return destruida;
	}
	
	
}
