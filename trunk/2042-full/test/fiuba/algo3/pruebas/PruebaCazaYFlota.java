package juego.pruebas;

import java.util.List;

import org.junit.Test;

import juego.*;
import juego.excepciones.ItemNoDisponibleError;
import juego.excepciones.NaveDestruidaError;
import juego.excepciones.SuperposicionNavesError;
import junit.framework.TestCase;

public class PruebaCazaYFlota extends TestCase {
	
	@Test
	public void testDejarArmas() throws SuperposicionNavesError, ItemNoDisponibleError, NaveDestruidaError{
	/*Prueba que un caza no puede dejar un tanque de energia,
	cuando esta eliminado, prueba que las coloca en la posicion correcta.*/

		
		
		Plano plano = new Plano( 100 , 100 );
		Caza caza = new Caza( 50 , 50 , plano);
		
		/*Un caza es inicializado con 10 puntos de energia. Hasta que no sean iguales o menores que cero,
		no puede dejar un item.*/
		try {
			caza.dejarTanque();
			fail("NO ha muerto, no puede dejar un item");
		} catch (ItemNoDisponibleError error) {}
		
		
		//Modifico su energia y ahora si deberia crearlo, y en la posicion del caza"
		
		caza.modificarEnergia( -10 );
		Item item = caza.dejarTanque();
		
		assertEquals((int)item.posicionX() , 50 );
		assertEquals((int)item.posicionY() , 50 );
	}
	
	@Test
	public void testLanzamientoTorpedo() throws SuperposicionNavesError, NaveDestruidaError { 
	//Prueba el lanzamiento de torpedos simples"

	
		Plano plano = new Plano( 100 , 100 );
		Caza caza = new Caza( 90 , 90 , plano );
		
		//La responsabilidad del caza es crear un laser y ubicarlo, por lo tanto, solo eso voy a probar en esta prueba unitaria.
		caza.dispararTorpedo();
		List<Arma> torpedo = plano.devolverListaArmas(); //Se que s�lo hay una
		assertEquals((int)torpedo.get(0).posicionY() , 90 );
		assertEquals((int)torpedo.get(0).posicionX() , 90 );
	}
	
	@Test
	public void testMoverCaza() throws SuperposicionNavesError, NaveDestruidaError {
	//Prueba los movimientos de los cazas.
		
		int pos = 75;
		Plano plano = new Plano( 100 , 100 );
		Caza caza = new Caza( 70 , 75 , plano ); 
		for ( int i = 1 ; i < 20 ; i++ ) {	
			assertEquals((int)caza.posicionY() , pos );
			caza.mover();
			pos = ( pos - 1 );
		}
	}
	
	@Test
	public void testMoverAlternativoCaza() throws SuperposicionNavesError, NaveDestruidaError {
	//Prueba los movimientos de los cazas.
		
		int pos = 75;
		Plano plano = new Plano( 100 , 100 );
		Caza caza = new Caza( 70 , 75 , plano ); 
		for ( int i = 1 ; i < 20 ; i++ ) {	
			assertEquals((int)caza.posicionY() , pos );
			caza.moverAlternativo();
			pos = ( pos + 1 );
		}
	}
	
}
