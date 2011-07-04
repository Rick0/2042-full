package fiuba.algo3.juego.test;

import java.util.List;
import junit.framework.TestCase;
import org.junit.Test;
import fiuba.algo3.juego.modelo.Arma;
import fiuba.algo3.juego.modelo.Avioneta;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class AvionetaTest extends TestCase{

	@Test
	/* Creara una instancia de avioneta, la hara bajar 60 pasos asegurandose de que la posicion
	 * en cada punto sea la correcta, y luego se movera una vez mas para corroborar que el movimiento
	 * hacia arriba se lleve a cabo.
	 */
	public void testMoverAvioneta() throws SuperposicionNavesError, NaveDestruidaError {

		Plano plano = new Plano(500, 500);
		Punto punto= new Punto(50,450);
		Avioneta avioneta = new Avioneta(punto, plano);
		
		int n = 0, posY = 450;
		while ( n < ((avioneta.devolverPuntosAdelante())/2)+1 ) {
			n++;
			avioneta.mover();
			posY = (posY - 2);
			assertTrue("La Avioneta se mueve en el eje Y", avioneta.devolverPunto().getY() == posY );
		}
		avioneta.mover();
		posY = (posY + 2);
		assertTrue(avioneta.devolverPunto().getY() == posY);
	}

	@Test
	/* Prueba el movimiento alternativo de la avioneta */
	public void testMoverAvionetaAlternativo() throws SuperposicionNavesError, NaveDestruidaError {

		Plano plano = new Plano(500, 500);
		Punto punto= new Punto(50,450);
		Avioneta avioneta = new Avioneta(punto, plano);
		double n = 0, posY = 450;
		
		while ( n < 10 ) {
			
			n = (n + 1);
			avioneta.mover();
			posY = (posY - 2);
			assertEquals( avioneta.devolverPunto().getY() , posY );
		}

		assertEquals( avioneta.devolverPunto().getY() , 430.0 );
		avioneta.moverAlternativo();
		assertEquals( "La avioneta debe subir dos casillas", avioneta.devolverPunto().getY(), 432.0);
		//Pero al llamar a mover, deberia bajar de nuevo.
		avioneta.mover();
		assertEquals( avioneta.devolverPunto().getY() , 430.0 );
		posY = avioneta.devolverPunto().getY();

		for (int i=1; i < ((avioneta.devolverPuntosAdelante())/2)+1; i++) {
			avioneta.mover();
			posY = (posY - 2);
			assertEquals( avioneta.devolverPunto().getY(), posY );
		}
		assertTrue( posY == avioneta.devolverPunto().getY() );

		//Ahora deberia empezar a subir al llamar a mover
		avioneta.mover();
		assertTrue( posY+2 == avioneta.devolverPunto().getY() );
	}

	@Test
	/* Prueba el lanzamiento de armas laser */
	public void testLanzamientoLaser() throws SuperposicionNavesError, NaveDestruidaError {

		Plano plano = new Plano(100, 100);
		Punto punto=new Punto(20,25);
		Avioneta avioneta = new Avioneta(punto, plano);
		//La responsabilidad de la avioneta es crear un laser y ubicarlo, por lo tanto, solo eso voy a probar en esta prueba unitaria.
		avioneta.dispararLaser();
		List<Arma> arma = plano.devolverListaArmas();

		assertEquals( arma.get(0).devolverPunto().getX(), (avioneta.devolverPunto().getX() + avioneta.devolverAncho()/2 - avioneta.devolverAncho() /10) );
		assertEquals( arma.get(0).devolverPunto().getY(), 25.0 );
	}

}
