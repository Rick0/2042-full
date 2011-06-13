package fiuba.algo3.juego.modelo;

/* Clase rectangulo, crea figuras rectangulares con un determinado ancho
 * y altura, ademas de una posicion en el espacio.
 * La posicion esta dada por su punto extremo izquierdo inferior.
 */
public class Rectangulo {

	int ancho;
	int altura;
	Punto puntoIzquierdoInferior;


	/* Constructor del rectangulo, recibe su posicion en el espacio y sus dimensiones */
	public Rectangulo(int alturaR, int anchoR, Punto punto ) {
		puntoIzquierdoInferior= punto;
		ancho=anchoR;
		altura=alturaR;
	}

	/* Devuelve el punto que determina la posicion del rectangulo */
	public Punto devolverPuntoIzquierdoInferior() {
		return puntoIzquierdoInferior;
	}

	/* Devuelve la altura del rectangulo */
	public int devolverAltura() {
		return altura;	
	}

	/* Devuelve el ancho del rectangulo */
	public int devolverAncho() {
		return ancho;
	}

	/*Recibe un punto y ese pasa a ser el punto izquierdo inferior
	 * del rectangulo, que determina su posicion
	 */
	public void determinarPosicion(Punto punto) {
		puntoIzquierdoInferior=punto;
	}

	/* Compara dos rectangulos y devuelve true si se superponen
	 * (es decir, si tienen en comun al menos un punto)
	 * o false en caso contrario.
	 */
	public boolean coincideConPosicionDe(Rectangulo otroRectangulo) {

		double otroXComienzo, otroXFinal, XComienzo, XFinal, otroYComienzo, otroYFinal, YComienzo, YFinal;
	
		otroXComienzo = otroRectangulo.devolverPuntoIzquierdoInferior().getX();
		otroXFinal = otroXComienzo + otroRectangulo.ancho;
		XComienzo = puntoIzquierdoInferior.getX();
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

		otroYComienzo = otroRectangulo.devolverPuntoIzquierdoInferior().getY();
		otroYFinal = otroYComienzo + otroRectangulo.altura;
		YComienzo = puntoIzquierdoInferior.getY();
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