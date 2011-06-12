package fiuba.algo3.juego.modelo;

/* Clase rectangulo, crea figuras rectangulares con un determinado ancho
 * y altura, ademas de una posicion en el espacio.
 */
public class Rectangulo {

	int ancho;
	int altura;
	double posicionX;
	double posicionY;


	/* Constructor del rectangulo, recibe su posicion en el espacio y sus dimensiones */
	public Rectangulo(int alturaR, int anchoR, double x, double y ) {
		posicionX=x;
		posicionY=y;
		ancho=anchoR;
		altura=alturaR;
	}

	/* Devuelve la posicion en Y del rectangulo */
	public double devolverPosicionY() {
		return posicionY;
	}

	/* Devuelve la posicion en X del rectangulo */
	public double devolverPosicionX() {
		return posicionX;
	}

	/* Devuelve la altura del rectangulo */
	public int devolverAltura() {
		return altura;	
	}

	/* Devuelve el ancho del rectangulo */
	public int devolverAncho() {
		return ancho;
	}

	public void determinarPosicion(double posx, double posy) {
		posicionX = posx;
		posicionY = posy;
	}

	/* Compara dos rectangulos y devuelve true si se superponen
	 * (es decir, si tienen en comun al menos un punto)
	 * o false en caso contrario.
	 */
	public boolean coincideConPosicionDe(Rectangulo otroRectangulo) {

		double otroXComienzo, otroXFinal, XComienzo, XFinal, otroYComienzo, otroYFinal, YComienzo, YFinal;
	
		otroXComienzo = otroRectangulo.posicionX;
		otroXFinal = otroXComienzo + otroRectangulo.ancho;
		XComienzo = posicionX;
		XFinal = XComienzo + ancho;


		if(XComienzo <= otroXComienzo) {
			if (!(otroXComienzo <= XFinal)) {
				return false;
			}
		}
		if (XComienzo >= otroXComienzo) {
			if(!(XComienzo <= otroXFinal)) {
				return false;
			}
		}

		otroYComienzo = otroRectangulo.posicionY;
		otroYFinal = otroYComienzo + otroRectangulo.altura;
		YComienzo = posicionY;
		YFinal = YComienzo + altura;
		if(YComienzo <= otroYComienzo) {
			if (!(otroYComienzo <= YFinal)) {
				return false;
			}
		}
		if (YComienzo >= otroYComienzo) {
			if (!(YComienzo <= otroYFinal)) {
				return false;
			}
		}
		return true;

	}

}