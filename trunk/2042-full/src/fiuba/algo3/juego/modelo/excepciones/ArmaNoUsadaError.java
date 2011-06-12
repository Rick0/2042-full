package fiuba.algo3.juego.modelo.excepciones;


/**
 * Cuando intenta agregarse un arma cuyo estado es no usada
 * a la lista de armas usadas del plano, deberia lanzarse
 * esta excepcion.
 */
public class ArmaNoUsadaError extends Exception{

	private static final long serialVersionUID = -3875256095026846790L;


	public ArmaNoUsadaError( String cadena ) {
		 // constructor param. de Exception
		super( cadena );
	}

}
