package excepciones;

public class ArmaNoUsadaError extends Exception{

	/**
	 * Cuando intenta agregarse un arma cuyo estado es no usada
	 * a la lista de armas usadas del plano, deberia lanzarse
	 * esta excepcion.
	 */
	
	private static final long serialVersionUID = -3875256095026846790L;

	public ArmaNoUsadaError( String cadena ){

		super( cadena ); // constructor param. de Exception

		}
}
