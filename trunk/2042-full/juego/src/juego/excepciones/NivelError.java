package juego.excepciones;

public class NivelError extends Exception {

	/**
	 * Esta excepcion deberia ser lanzada si intentara
	 * llamarse al metodo para avanzar un nivel, siendo
	 * que los puntos del nivel actual aun no son los necesarios
	 */
	private static final long serialVersionUID = 1L;

	public NivelError( String cadena ){

	super( cadena ); // constructor param. de Exception

	}

}
