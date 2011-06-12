package fiuba.algo3.juego.modelo.excepciones;


/**
 * Este error deberia ser lanzado si un arma originada por algo42
 * intentara atacar a una instancia de Algo42.
 */
public class AlgoSeAtacaASiMismoError extends Exception {

	private static final long serialVersionUID = 1L;


	public AlgoSeAtacaASiMismoError( String cadena ) {
		// constructor param. de Exception
		super( cadena );
	}

}
