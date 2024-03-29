package fiuba.algo3.juego.test;

import java.util.List;
import junit.framework.TestCase;
import org.junit.Test;
import fiuba.algo3.juego.modelo.Arma;
import fiuba.algo3.juego.modelo.Caza;
import fiuba.algo3.juego.modelo.Item;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.ItemNoDisponibleError;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class CazaYFlotaTest extends TestCase {
	
	@Test
	/* Prueba que un caza no puede dejar un tanque de energia,
	 * cuando esta eliminado, prueba que las coloca en la posicion correcta. */
	public void testDejarArmas() throws SuperposicionNavesError, ItemNoDisponibleError, NaveDestruidaError{

		Plano plano = new Plano( 100 , 100 );
		Punto punto= new Punto(50,50);
		Caza caza = new Caza( punto , plano);
		
		/*Un caza es inicializado con 10 puntos de energia. Hasta que no sean iguales o menores que cero,
		no puede dejar un item.*/
		try {
			caza.dejarTanqueVida();
			fail("NO ha muerto, no puede dejar un item");
		} catch (ItemNoDisponibleError error) {}

		//Modifico su energia y ahora si deberia crearlo, y en la posicion del caza"
		caza.modificarEnergia( -10 );
		Item item = caza.dejarTanqueVida();
		
		assertEquals(item.devolverPunto().getX() , 50.0 );
		assertEquals(item.devolverPunto().getY() , 50.0 );
	}
	
	@Test
	/* Prueba el lanzamiento de torpedos simples */
	public void testLanzamientoTorpedo() throws SuperposicionNavesError, NaveDestruidaError { 

		Plano plano = new Plano( 100 , 100 );
		Punto posicion= new Punto( 90, 90 );
		Caza caza = new Caza( posicion, plano );
		
		//La responsabilidad del caza es crear un laser y ubicarlo, por lo tanto, solo eso voy a probar en esta prueba unitaria.
		caza.dispararTorpedo();
		List<Arma> torpedo = plano.devolverListaArmas(); //Se que solo hay una
		assertEquals(torpedo.get(0).devolverPunto().getY() , 90.0 );
		assertEquals((int)torpedo.get(0).devolverPunto().getX() , (int)(caza.devolverPunto().getX() + caza.devolverAncho()/2 - caza.devolverAncho() /10) );
	}
	
	@Test
	/* Prueba los movimientos de los cazas. */
	public void testMoverCaza() throws SuperposicionNavesError, NaveDestruidaError {

		double pos = 75.0;
		Plano plano = new Plano( 100 , 100 );
		Punto punto= new Punto(70,75);
		Caza caza = new Caza(punto, plano ); 
		for ( int i = 1 ; i < 20 ; i++ ) {	
			assertEquals(caza.devolverPunto().getY() , pos );
			caza.mover();
			pos = ( pos - 1 );
		}
	}
	
	@Test
	/* Prueba los movimientos de los cazas. */
	public void testMoverAlternativoCaza() throws SuperposicionNavesError, NaveDestruidaError {

		double pos = 75.0;
		Plano plano = new Plano( 100 , 100 );
		Punto punto= new Punto(70,75);
		Caza caza = new Caza(punto , plano ); 
		for ( int i = 1 ; i < 20 ; i++ ) {	
			assertEquals(caza.devolverPunto().getY() , pos );
			caza.moverAlternativo();
			pos = ( pos + 1 );
		}
	}

}
