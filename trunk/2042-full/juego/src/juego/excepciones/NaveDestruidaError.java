package juego.excepciones;

public class NaveDestruidaError extends Exception{

	/**
	 * Esta excepcion deberia lanzarse si intentara agregarse
	 * al plano de juego una nave cuyo estado sea destruida
	 */
	private static final long serialVersionUID = 6943773446071233513L;

	public NaveDestruidaError ( String cadena ){

		super( cadena ); // constructor param. de Exception

		}
}
