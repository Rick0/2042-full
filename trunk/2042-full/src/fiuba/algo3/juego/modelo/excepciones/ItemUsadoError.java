package fiuba.algo3.juego.modelo.excepciones;


/**
 * Esta excepcion deberia ser lanzada si se intentara
 * agregar al area de juego un item usado
 */
public class ItemUsadoError  extends Exception{

	private static final long serialVersionUID = 1116643400278747226L;


	public ItemUsadoError ( String cadena ) {
		// constructor param. de Exception
		super( cadena );
	}

}
