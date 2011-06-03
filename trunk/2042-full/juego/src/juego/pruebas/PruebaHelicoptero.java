package juego.pruebas;

import junit.framework.TestCase;
import juego.*;
import juego.excepciones.*;

public class PruebaHelicoptero extends TestCase {
	
	public void testMoverHelicoptero() throws SuperposicionNavesError, NaveDestruidaError{
	/*Prueba los movimientos de los helicopteros.*/

		Plano plano = new Plano (100,100);
		double pos=95;
		Helicoptero helicoptero= new Helicoptero (10, 95, plano);
		
		for (int i=0; i<20; i++) {
			helicoptero.mover();
			pos=pos-1;
			assertEquals(helicoptero.posicionY(),pos);
			assertEquals(helicoptero.posicionX(), 10.0);
		}
	}
	
	public void testMoverAlternativo() throws SuperposicionNavesError, NaveDestruidaError{
	/*Pruebo que al llamar a la funcion mover alternativo, realice el movimiento esperado en los helicopteros,
	es decir, el movimiento hacia atras*/

		Plano plano=new Plano(100 , 100);
		Helicoptero helicoptero= new Helicoptero (30 , 65 , plano);
		double pos=65;
		for (int i=0; i<20; i++) {
				assertEquals((helicoptero.posicionY()), pos);
				helicoptero.moverAlternativo();
				pos=pos+1;
		}
	}
}
