package juego.excepciones;


/**
 * Esta excepcion deberia lanzarse si intentara agregarse
 * al plano de juego una nave cuyo estado sea destruida
 */
public class NaveDestruidaError extends Exception{

	private static final long serialVersionUID = 6943773446071233513L;


	public NaveDestruidaError ( String cadena ){
		// constructor param. de Exception
		super( cadena );
	}

}
