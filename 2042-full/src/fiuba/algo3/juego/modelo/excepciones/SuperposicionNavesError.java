package fiuba.algo3.juego.modelo.excepciones;


/**
 * Esta excepcion deberia ser lanzada al crear naves
 * en el plano en posiciones validas pero ya ocupadas
 * por otras naves.
 */
public class SuperposicionNavesError extends Exception {

	private static final long serialVersionUID = 2122004940302396293L;

	public SuperposicionNavesError( String cadena ) {
		 // constructor param. de Exception
		super( cadena );
	}

}
