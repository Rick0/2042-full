package fiuba.algo3.juego.modelo.excepciones;


/**
 * Esta excepcion deberia ser lanzada si intentara
 * llamarse al metodo para avanzar un nivel, siendo
 * que los puntos del nivel actual aun no son los necesarios
 */
public class NivelError extends Exception {

	private static final long serialVersionUID = 1L;

	public NivelError( String cadena ) {
		// constructor param. de Exception
		super( cadena ); 
	}

}
