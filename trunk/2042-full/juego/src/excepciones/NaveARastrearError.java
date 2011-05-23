package excepciones;

public class NaveARastrearError extends Exception{


	/**
	 * Este metodo deberia ser lanzado si una instancia de algo42
	 * se eligiera a si misma como objetivo del torpedo
	 * rastreador lanzado
	 */
	private static final long serialVersionUID = -7360116221834796073L;

	public NaveARastrearError( String cadena ){

		super( cadena ); // constructor param. de Exception

		}
}