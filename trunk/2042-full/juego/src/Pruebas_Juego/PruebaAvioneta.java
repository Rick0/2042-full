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
			assertTrue("La Avioneta se mueve en el eje Y", avioneta.posicionY() == posY );
		}
		avioneta.mover();
		posY = (posY + 2);
		assertTrue(avioneta.posicionY() == posY);
	}
	
	@Test
	public void testMoverAvionetaAlternativo() throws SuperposicionNavesError {
	//Prueba el movimiento alternativo de la avioneta
		
		Plano plano = new Plano(100, 100);
		Avioneta avioneta = new Avioneta(50, 95, plano);
		
		int n = 0, posY = 95;
		
		while ( n < 10 ) {
			
			n = (n + 1);
			avioneta.mover();
			posY = (posY - 2);
			assertEquals( avioneta.posicionY() , posY );
			
		}
		assertEquals( avioneta.posicionY() , 75 );
		avioneta.moverAlternativo();
		assertEquals( "La avioneta debe subir dos casillas", avioneta.posicionY(), 77);
		//Pero al llamar a mover, deberia bajar de nuevo.
		avioneta.mover();
		assertEquals( avioneta.posicionY() , 75 );
		posY = 75;
		for (int i=1; i < 31; i++) {
			avioneta.mover();
			posY = (posY - 2);
			assertEquals( avioneta.posicionY() , posY );
		}
		assertTrue( posY == 15 );
		//Ahora deberia empezar a subir al llamar a mover
		avioneta.mover();
		posY = avioneta.posicionY();
		assertTrue( posY == 17 );
		avioneta.moverAlternativo();
		posY = avioneta.posicionY();
		assertTrue( posY == 15); //Ahora el mover alternativo deberia ir hacia atras.
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
