package excepciones;

public class ArmaNoDisponibleError extends Exception  {

	/**
	 * Cuando una nave de tipo algo42 intenta lanzar un tipo de arma
	 * y no dispone de municiones, deberia lanzarse este error.
	 */
	private static final long serialVersionUID = 8721263262722091917L;

	public ArmaNoDisponibleError( String cadena ){

	super( cadena ); // constructor param. de Exception

	}
}