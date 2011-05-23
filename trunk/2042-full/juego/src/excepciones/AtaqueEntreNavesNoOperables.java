package excepciones;

public class AtaqueEntreNavesNoOperables extends Exception{

	/**
	 * Esta excepcion deberia ser lanzada si una municion
	 * originada por una nave no operable intentara provocar
	 * danios en otra del mismo tipo
	 */
	private static final long serialVersionUID = -5010346577065053655L;
	
	public AtaqueEntreNavesNoOperables( String cadena ){

		super( cadena ); // constructor param. de Exception

		}
}
