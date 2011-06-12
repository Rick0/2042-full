package fiuba.algo3.juego.pruebas;

import junit.framework.TestCase;

import org.junit.Test;

import fiuba.algo3.juego.modelo.Civil;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class PruebaCivil extends TestCase {
	
	@Test
	public void testMoverAlternativoAvionCivil() throws SuperposicionNavesError, NaveDestruidaError {	
	/*Pruebo que al llamar a la funcion mover alternativo, realice el movimiento esperado en las naves civiles,
	es decir, el movimiento hacia atras*/
	
		double pos = 35, i = 0;
		Plano plano= new Plano( 100, 100);
		Civil avion = new Civil( 70, 35, plano);
		while ( i < 20) {	
			i = (i + 1);	
			assertEquals( avion.posicionY() , pos );
			avion.moverAlternativo();
			pos = (pos + 1);
		}
	}
	
	@Test
	public void testMoverAvionCivil() throws SuperposicionNavesError, NaveDestruidaError {	
		/*Pruebo que al llamar a la funcion mover, realice el movimiento esperado en las naves civiles,
		es decir, el movimiento hacia adelante.*/
		
			double pos = 35, i = 0;
			Plano plano= new Plano( 100, 100);
			Civil avion = new Civil( 70, 35, plano);
			while ( i < 20) {	
				i = (i + 1);	
				assertEquals( avion.posicionY() , pos );
				avion.mover();
				pos = (pos - 1);
			}
	}

}
