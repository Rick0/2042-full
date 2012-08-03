package fiuba.algo3.juego.test;

import junit.framework.TestCase;
import org.junit.Test;
import fiuba.algo3.juego.modelo.Helicoptero;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class HelicopteroTest extends TestCase {
	
	@Test
	/* Prueba los movimientos de los helicopteros */
	public void testMoverHelicoptero() throws SuperposicionNavesError, NaveDestruidaError{

		Plano plano = new Plano (100,100);
		double pos = 95.0;
		Punto punto = new Punto(10,95);
		Helicoptero helicoptero = new Helicoptero (punto, plano);
		
		for (int i=0; i<20; i++) {
			helicoptero.mover();
			pos = pos - 2;
			assertEquals(helicoptero.devolverPunto().getY(), pos);
			assertEquals(helicoptero.devolverPunto().getX(), 10.0);
		}
	}

	@Test
	/* Pruebo que al llamar a la funcion mover alternativo, realice el movimiento esperado en los helicopteros,
	 * es decir, el movimiento hacia atras */
	public void testMoverAlternativo() throws SuperposicionNavesError, NaveDestruidaError{

		Plano plano = new Plano(100 , 100);
		Punto posicion = new Punto(30,65);
		Helicoptero helicoptero = new Helicoptero (posicion , plano);
		double pos=65;

		for (int i=0; i<20; i++) {
			assertEquals((helicoptero.devolverPunto().getY()), pos);
			helicoptero.moverAlternativo();
			pos=pos+1;
		}
	}

}
