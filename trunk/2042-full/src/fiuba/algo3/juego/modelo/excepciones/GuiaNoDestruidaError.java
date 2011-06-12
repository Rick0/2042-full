package fiuba.algo3.juego.modelo.excepciones;


/**
 * Esta excepcion deberia ser lanzada si se intentara
 * retirar una flota de naves enemigas cuya nave guia no 
 * fue destruida.
 */
public class GuiaNoDestruidaError extends Exception{

	private static final long serialVersionUID = -4184381327759529933L;


	public GuiaNoDestruidaError( String cadena ) {
		// constructor param. de Exception
		super( cadena );
	}

}
