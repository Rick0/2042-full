package Pruebas_Juego;

import org.junit.*;

import excepciones.NaveDestruidaError;
import excepciones.SuperposicionNavesError;
import junit.framework.TestCase;
import juego.*;


public class PruebaNivel extends TestCase {
	
	@Test
	public void testAvanzarInvalido() {
	//Prueba que si no se sumaron los puntos necesarios, no se puede avanzar de nivel.
	
	Nivel nivel = new Nivel();
	assertEquals( nivel.devolverPuntosActuales() , 0 );
	//No puede avanzar porque tiene 0 puntos.
	assertTrue ( !nivel.avanzarNivel() ); 
	
	}
	
	@Test
	public void testAvanzarNiveles() throws SuperposicionNavesError, NaveDestruidaError {
	/*Crea un plano y una lista de naves, a las cuales les modificara la energia para que pasen a estar destruidas.
	Las naves seran 20 cazas. La destruccion de un caza da 50 puntos; Eliminar 20 da los 1000 puntos necesarios
	para avanzar de nivel.*/

		
		Nivel nivel = new Nivel();
		Plano plano = new Plano( 200 , 200 );
		int posx = 0;
		int posy = 0;
		assertEquals( nivel.devolverNumeroNivel() , 1 );
		int i = 0;
		while ( i < 21 ) {
			i++;
			posx = ( posx + 10 );
			posy = ( posy + 10 );
			Caza caza = new Caza( posx , posy , plano );
			caza.modificarEnergia( -20 ); //Destruyo el caza
		}
		assertTrue(plano.devolverListaNavesEliminades().size() > 1);
		nivel.sumarPuntajeTurno( plano.devolverListaNavesEliminades() );
		//Deberia haber avanzado un nivel
		assertEquals( nivel.devolverNumeroNivel() , 2 );
	}

}
