package excepciones;

public class ArmaUsadaError extends Exception{



		/**
		 * Esta excepcion deberia ser lanzada al
		 * intentar agregar a la lista de armas utilizables del plano
		 * un arma cuyo estado sea usada.
		 */

		private static final long serialVersionUID = -2177586861943553809L;

		public ArmaUsadaError( String cadena ){

			super( cadena ); // constructor param. de Exception

			}
}