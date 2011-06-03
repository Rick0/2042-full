package juego.excepciones;

public class GuiaNoDestruidaError extends Exception{

	/**
	 * Esta excepcion deberia ser lanzada si se intentara
	 * retirar una flota de naves enemigas cuya nave guia no 
	 * fue destruida.
	 */
	private static final long serialVersionUID = -4184381327759529933L;

	public GuiaNoDestruidaError( String cadena ){

		super( cadena ); // constructor param. de Exception

		}
}
