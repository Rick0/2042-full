package fiuba.algo3.juego.test;

import org.junit.Test;
import junit.framework.TestCase;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.Rectangulo;


public class RectanguloTest extends TestCase{

	@Test
	public void testSuperposicionRectangulos() {

		Punto rectangulo1ExtremoIzqInferior=new Punto(30,30);
		Punto rectangulo2ExtremoIzqInferior=new Punto(20,20);

		Rectangulo rectangulo1 = new Rectangulo(10,10,rectangulo1ExtremoIzqInferior);
		Rectangulo rectangulo2 = new Rectangulo (10,10,rectangulo2ExtremoIzqInferior);

		assertEquals(rectangulo1.coincideConPosicionDe(rectangulo2),true);
		
		Punto nuevoPuntoRectangulo1= new Punto(30,30);
		rectangulo2.cambiarPosicion(nuevoPuntoRectangulo1);
		assertEquals(rectangulo1.coincideConPosicionDe(rectangulo2),true);
		
		Punto nuevoPuntoRectangulo2= new Punto(47, 50);
		rectangulo2.cambiarPosicion( nuevoPuntoRectangulo2 );
		assertEquals(rectangulo1.coincideConPosicionDe(rectangulo2),false);
		
		Punto nPuntoRectangulo2= new Punto(90, 30);
		rectangulo2.cambiarPosicion(nPuntoRectangulo2);
		assertEquals(rectangulo1.coincideConPosicionDe(rectangulo2),false);
		
		Punto otroPuntoRectangulo2= new Punto(30, 90);
		rectangulo2.cambiarPosicion(otroPuntoRectangulo2);
		assertEquals(rectangulo1.coincideConPosicionDe(rectangulo2),false);
	}

}
