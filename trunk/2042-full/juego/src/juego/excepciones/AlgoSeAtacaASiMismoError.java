package juego.excepciones;

public class AlgoSeAtacaASiMismoError extends Exception {
	
	/**
	 * Este error deberia ser lanzado si un arma originada por algo42
	 * intentara atacar a una instancia de Algo42.
	 */
	private static final long serialVersionUID = 1L;

	public AlgoSeAtacaASiMismoError( String cadena ){

		super( cadena ); // constructor param. de Exception

		}
}
