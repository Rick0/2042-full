package fiuba.algo3.juego.test;

import junit.framework.TestCase;
import org.junit.Test;
import fiuba.algo3.juego.modelo.Civil;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class CivilTest extends TestCase {
	
	@Test
	/* Pruebo que al llamar a la funcion mover alternativo,
	 * realice el movimiento esperado en las naves civiles,
	 * es decir, el movimiento hacia atras */
	public void testMoverAlternativoAvionCivil() throws SuperposicionNavesError, NaveDestruidaError {	

		double pos = 35, i = 0;
		Plano plano= new Plano( 100, 100);
		Punto punto= new Punto(70,35);
		Civil avion = new Civil( punto, plano);
		while ( i < 20) {	
			i = (i + 1);	
			assertEquals( avion.devolverPunto().getY() , pos );
			avion.moverAlternativo();
			pos = (pos + 1);
		}
	}
	
	@Test
	/* Pruebo que al llamar a la funcion mover, realice el movimiento esperado en las naves civiles,
	 * es decir, el movimiento hacia adelante */
	public void testMoverAvionCivil() throws SuperposicionNavesError, NaveDestruidaError {	

		double pos = 35.0, i = 0;
		Plano plano= new Plano( 100, 100);
		Punto punto= new Punto(70,35);
		Civil avion = new Civil( punto, plano);
		while ( i < 20) {	
			i = (i + 1);	
			assertEquals( avion.devolverPunto().getY() , pos );
			avion.mover();
			pos = (pos - 1);
		}
	}

}
