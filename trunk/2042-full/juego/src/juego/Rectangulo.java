package juego;

public class Rectangulo {
		/*Clase rectangulo, crea figuras rectangulares con un determinado ancho
		 * y altura, ademas de una posicion en el espacio.
		 */
		
		int ancho;
		int altura;
		int posicionX;
		int posicionY;
		
		void inicializar( int alturaR, int anchoR, int posicion_X, int posicion_Y ) {
			
			posicionX=posicion_X;
			posicionY=posicion_Y;
			ancho=anchoR;
			altura=alturaR;
			
		}
		
		boolean coincideConPosicionDe(Rectangulo otroRectangulo){
			
			int otroXComienzo, otroXFinal, XComienzo, XFinal, otroYComienzo, otroYFinal, YComienzo, YFinal;
		
			otroXComienzo = otroRectangulo.posicionX;
			otroXFinal = otroXComienzo + otroRectangulo.ancho;
			XComienzo = otroRectangulo.posicionX;
			XFinal = XComienzo + ancho;
		
		
			if(XComienzo <= otroXComienzo){
				if (otroXComienzo > XFinal){
					return false;
				}
			}
			if (XComienzo >= otroXComienzo){
				if(XComienzo > otroXFinal){
					return false;
				}
			}
		
			otroYComienzo = otroRectangulo.posicionY;
			otroYFinal = otroYComienzo + otroRectangulo.altura;
			YComienzo = posicionY;
			YFinal = YComienzo + altura;
			if(YComienzo <= otroYComienzo){
				if (otroYComienzo > YFinal){
					return false;
				}
			}
			if (YComienzo >= otroYComienzo){
				if (YComienzo > otroYFinal){
					return false;
				}
			}
			return true;
	
			}
		
}