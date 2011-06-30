package fiuba.algo3.juego.test;

import junit.framework.TestCase;

import org.junit.Test;

import fiuba.algo3.juego.modelo.Explorador;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class ExploradorTest extends TestCase{
	
	@Test
	public void testMoverExplorador() throws SuperposicionNavesError, NaveDestruidaError {
	/*El explorador se mueve en forma de helice, intentado recorrer toda el area del juego:
	Por lo tanto, para adaptarse al area de juego de 100x100, sera inicializado con radio=35;
	De esta manera cubrira casi todo el area en sus giros. Su movimiento
	no es puramente circular: Es como una helice. Cada vez que gira, tambien avanza 0.5 hacia abajo
	en Y- Segun esto, al completar media vuelta -es decir, 1/2 pi- habra avanzado 6 veces; ya
	que en cada paso, baja 1/12, habra bajado 6/12=1/2. Como ademas baja 0.5 en Y cada vez,
	estara en Y= 62 (100-35-3), y 15 (50-35) en X*/
	
	
		//"Creo y ubico la nave en la posicion 50,100"
		int n = 0;
		Plano plano = new Plano( 100 , 100 );
		Punto posicionExplorador= new Punto(50,100);
		Explorador explorador = new Explorador( posicionExplorador , 35 , plano); 
		
		while ( n < 6 ) {
			explorador.mover();
			n = ( n + 1 );
		}
		assertEquals( (int)(explorador.devolverPunto().getY()) , 62 );
		assertEquals( (int)(explorador.devolverPunto().getX()), 15);
	}
	
	@Test
	public void testMoverAlternativo() throws SuperposicionNavesError, NaveDestruidaError { 
	/*Prueba el movimiento alternativo de una nave exploradora.
	El mover alternativo basicamente hace el mismo giro en posicion contraria.
	Por lo tanto, estas pruebas haran el giro alternativo desde el principio. Dara media vuelta de helice hacia atras.
	*/
		
		Plano plano = new Plano( 200 , 100 );
		Punto punto=new Punto(50,70);
		Explorador explorador = new Explorador( punto, 35 , plano );
		int n = 0;
		while ( n < 6 ) {
			explorador.moverAlternativo();
			n = ( n + 1 );
		}
		assertEquals( (int)explorador.devolverPunto().getX() , 15 );
		assertEquals( (int)explorador.devolverPunto().getY() , 108 );
	}
	
}
