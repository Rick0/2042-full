package Pruebas_Juego;


import java.util.List;
import org.junit.*;
import junit.framework.TestCase;
import juego.*;
import excepciones.*;

public class PruebaAvioneta extends TestCase{

	@Test
	public void testMoverAvioneta() throws SuperposicionNavesError {
	/*Creara una instancia de avioneta, la hara bajar 60 pasos asegurandose de que la posicion
	en cada punto sea la correcta, y luego se movera una vez mas para corroborar que el movimiento
	hacia arriba se lleve a cabo.*/

		
		
		Plano plano = new Plano(100, 100);
		Avioneta avioneta = new Avioneta(50, 95, plano);
		
		int n = 0, posY = 95;
		while ( n < 31 ) {
			n++;
			avioneta.mover();
			posY = (posY - 2);
			assertTrue("La Avioneta no se mueve en el eje Y", avioneta.posicionY() == posY );
		}
		avioneta.mover();
		posY = (posY + 2);
		assertTrue(avioneta.posicionY() == posY);
	}
	
	@Test
	public void testLanzamientoLaser() throws SuperposicionNavesError {
	//Prueba el lanzamiento de armas laser.

	
	Plano plano = new Plano(100, 100);
	Avioneta avioneta = new Avioneta(20, 25, plano);
	//La responsabilidad de la avioneta es crear un laser y ubicarlo, por lo tanto, solo eso voy a probar en esta prueba unitaria.
	avioneta.dispararLaser();
	List<Arma> arma = plano.devolverListaArmas();

	assertEquals( arma.get(0).posicionX(), 20 );
	assertEquals( arma.get(0).posicionY(), 25 );
	}
}
