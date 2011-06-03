package juego.excepciones;

public class ItemUsadoError  extends Exception{

	/**
	 * Esta excepcion deberia ser lanzada si se intentara
	 * agregar al area de juego un item usado
	 */
	private static final long serialVersionUID = 1116643400278747226L;

	public ItemUsadoError ( String cadena ){

		super( cadena ); // constructor param. de Exception

		}
}
