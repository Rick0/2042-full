package juego.excepciones;


/**
 * Este error deberia lanzarse al intentar mover el algo42
 * fuera del area de juego
 */
public class AreaInvalidaError extends Exception {

	private static final long serialVersionUID = -8201025231792397306L;


	public AreaInvalidaError( String cadena ) {
		// constructor param. de Exception
		super( cadena );
	}

}
