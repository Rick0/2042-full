package juego;

public class Rectangulo {
		/*Clase rectangulo, crea figuras rectangulares con un determinado ancho
		 * y altura, ademas de una posicion en el espacio.
		 */
		
		int ancho;
		int altura;
		double posicionX;
		double posicionY;
		
		public Rectangulo(int alturaR, int anchoR, double x, double y ) {
		/*Constructor del rectangulo, recibe su posicion en el espacio y sus dimensiones*/
			posicionX=x;
			posicionY=y;
			ancho=anchoR;
			altura=alturaR;
			
		}
		
		public double devolverPosicionY() {
			/*Devuelve la posicion en Y del rectangulo*/
				return posicionY;
				
			}
		
		public double devolverPosicionX() {
			/*Devuelve la posicion en X del rectangulo*/
				return posicionX;
				
			}
		
		public int devolverAltura() {
			/*Devuelve la altura del rectangulo*/
				return altura;
				
		}

		public int devolverAncho() {
			/*Devuelve el ancho del rectangulo*/
				return ancho;
				
		}
		
		public void determinarPosicion(double posx, double posy){
		
			posicionX = posx;
			posicionY = posy;
		}
		
		public boolean coincideConPosicionDe(Rectangulo otroRectangulo){
			
			/*Compara dos rectangulos y devuelve true si se superponen
			 * (Es decir, si tienen en comun al menos un punto) o false en caso contrario.
			 */
			
			double otroXComienzo, otroXFinal, XComienzo, XFinal, otroYComienzo, otroYFinal, YComienzo, YFinal;
		
			otroXComienzo = otroRectangulo.posicionX;
			otroXFinal = otroXComienzo + otroRectangulo.ancho;
			XComienzo = posicionX;
			XFinal = XComienzo + ancho;
		
		
			if(XComienzo <= otroXComienzo){
				if (!(otroXComienzo<=XFinal)){
					return false;
				}
			}
			if (XComienzo >= otroXComienzo){
				if(!(XComienzo<=otroXFinal)){
					return false;
				}
			}
		
			otroYComienzo = otroRectangulo.posicionY;
			otroYFinal = otroYComienzo + otroRectangulo.altura;
			YComienzo = posicionY;
			YFinal = YComienzo + altura;
			if(YComienzo <= otroYComienzo){
				if (!(otroYComienzo<=YFinal)){
					return false;
				}
			}
			if (YComienzo >= otroYComienzo){
				if (!(YComienzo<=otroYFinal)){
					return false;
				}
			}
			return true;
	
			}
		
}