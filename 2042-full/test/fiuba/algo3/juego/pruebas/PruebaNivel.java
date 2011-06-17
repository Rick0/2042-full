package fiuba.algo3.juego.pruebas;

import junit.framework.TestCase;

import org.junit.Test;

import fiuba.algo3.juego.modelo.Caza;
import fiuba.algo3.juego.modelo.Nivel;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


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
			Punto punto= new Punto(posx,posy);
			Caza caza = new Caza( punto , plano );
			caza.modificarEnergia( -20 ); //Destruyo el caza
		}
		assertTrue(plano.devolverListaNavesEliminades().size() > 1);
		nivel.sumarPuntajeTurno( plano.devolverListaNavesEliminades() );
		//Deberia haber avanzado un nivel
		assertEquals( nivel.devolverNumeroNivel() , 2 );
	}

}