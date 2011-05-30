
package Pruebas_Juego;

import juego.Rectangulo;
import junit.framework.*;

public class PruebaRectangulo extends TestCase{

	public void testSuperposicionRectangulos(){

		Rectangulo rectangulo1 = new Rectangulo(10,10,30,30);
		Rectangulo rectangulo2 = new Rectangulo (10,10,20,20);

		assertEquals(rectangulo1.coincideConPosicionDe(rectangulo2),true);
		rectangulo2.determinarPosicion(30,30);
		assertEquals(rectangulo1.coincideConPosicionDe(rectangulo2),true);
		rectangulo2.determinarPosicion(47, 50);
		assertEquals(rectangulo1.coincideConPosicionDe(rectangulo2),false);
		rectangulo2.determinarPosicion(90, 30);
		assertEquals(rectangulo1.coincideConPosicionDe(rectangulo2),false);
		rectangulo2.determinarPosicion(30, 90);
		assertEquals(rectangulo1.coincideConPosicionDe(rectangulo2),false);

	}
}