package excepciones;


public class NaveNoDestruidaError extends Exception {


	/**
	 * Esta excepcion debe ser lanzada si una nave que aun cuenta con
	 * energia intentara destruirse
	 */

	private static final long serialVersionUID = -8234822292376457087L;

	public NaveNoDestruidaError( String cadena ){

	super( cadena ); // constructor param. de Exception

	}

}

