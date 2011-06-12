package fiuba.algo3.juego.modelo.excepciones;


/**
 * Este metodo deberia ser lanzado si una instancia de algo42
 * se eligiera a si misma como objetivo del torpedo
 * rastreador lanzado
 */
public class NaveARastrearError extends Exception{

	private static final long serialVersionUID = -7360116221834796073L;


	public NaveARastrearError( String cadena ) {
		// constructor param. de Exception
		super( cadena );
	}

}
