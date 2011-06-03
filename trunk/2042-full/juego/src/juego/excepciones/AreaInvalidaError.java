package juego.excepciones;

public class AreaInvalidaError extends Exception {

	/**
	 * Este error deberia lanzarse al intentar mover el algo42
	 * fuera del area de juego
	 */
	private static final long serialVersionUID = -8201025231792397306L;
	
	public AreaInvalidaError( String cadena ){

		super( cadena ); // constructor param. de Exception

		}
}
