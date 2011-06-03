package juego.excepciones;


/**
 * Esta excepcion deberia ser lanzada al
 * intentar agregar a la lista de armas utilizables del plano
 * un arma cuyo estado sea usada.
 */
public class ArmaUsadaError extends Exception{

	private static final long serialVersionUID = -2177586861943553809L;


	public ArmaUsadaError( String cadena ) {
		// constructor param. de Exception
		super( cadena );
	}

}
