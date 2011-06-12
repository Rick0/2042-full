package fiuba.algo3.juego.modelo.excepciones;


/**
 * Cuando una nave de tipo algo42 intenta lanzar un tipo de arma
 * y no dispone de municiones, deberia lanzarse este error.
 */
public class ArmaNoDisponibleError extends Exception  {

	private static final long serialVersionUID = 8721263262722091917L;


	public ArmaNoDisponibleError( String cadena ) {
		// constructor param. de Exception
		super( cadena ); 
	}

}
