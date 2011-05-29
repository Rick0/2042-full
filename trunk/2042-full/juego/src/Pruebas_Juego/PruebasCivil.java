package Pruebas_Juego;

import org.junit.*;
import junit.framework.TestCase;
import juego.*;
import excepciones.*;

public class PruebasCivil extends TestCase {
	
	@Test
	public void testMoverAlternativoAvionCivil() throws SuperposicionNavesError {	
	/*Pruebo que al llamar a la funcion mover alternativo, realice el movimiento esperado en las naves civiles,
	es decir, el movimiento hacia atras*/
	
		int pos = 35, i = 0;
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
	public void testMoverAvionCivil() throws SuperposicionNavesError {	
		/*Pruebo que al llamar a la funcion mover, realice el movimiento esperado en las naves civiles,
		es decir, el movimiento hacia adelante.*/
		
			int pos = 35, i = 0;
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
