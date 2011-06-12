package fiuba.algo3.juego.modelo.excepciones;


/**
 * Esta excepcion debe ser lanzada si una nave que aun cuenta con
 * energia intentara destruirse
 */
public class NaveNoDestruidaError extends Exception {

	private static final long serialVersionUID = -8234822292376457087L;

	public NaveNoDestruidaError( String cadena ) {
		// constructor param. de Exception
		super( cadena );
	}

}
