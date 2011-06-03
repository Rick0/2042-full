package juego.excepciones;


/**
 * Esta excepcion deberia ser lanzada si una nave no destruida
 * utilizara metodos para dejar items en el plano
 */
public class ItemNoDisponibleError extends Exception{

	private static final long serialVersionUID = 7727191581807132864L;


	public ItemNoDisponibleError ( String cadena ) {
		// constructor param. de Exception
		super( cadena );
	}

}
