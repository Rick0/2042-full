package juego.excepciones;

public class ItemNoDisponibleError extends Exception{


	/**
	 * Esta excepcion deberia ser lanzada si una nave no destruida
	 * utilizara metodos para dejar items en el plano
	 */
	private static final long serialVersionUID = 7727191581807132864L;

	public ItemNoDisponibleError ( String cadena ){

		super( cadena ); // constructor param. de Exception

		}
}
