package juego.excepciones;

public class SuperposicionNavesError extends Exception {

	/**
	 * Esta excepcion deberia ser lanzada al crear naves
	 * en el plano en posiciones validas pero ya ocupadas
	 * por otras naves.
	 */
	private static final long serialVersionUID = 2122004940302396293L;

	public SuperposicionNavesError( String cadena ){

	super( cadena ); // constructor param. de Exception

	}

}
