package fiuba.algo3.juego.modelo.excepciones;


/**
 * Esta excepcion deberia ser lanzado si se intentara
 * agregar a la lista de items que deben ser eliminados
 * del juego un item no utilizado
 */
public class ItemNoUsadoError extends RuntimeException{

	private static final long serialVersionUID = -5766929275667747485L;


	public ItemNoUsadoError( ) {
		// constructor param. de Exception
		super();
	}

}
