package fiuba.algo3.juego.pruebas;

import junit.framework.TestCase;

import org.junit.Test;

import fiuba.algo3.juego.modelo.Algo42;
import fiuba.algo3.juego.modelo.Avioneta;
import fiuba.algo3.juego.modelo.Bombardero;
import fiuba.algo3.juego.modelo.Civil;
import fiuba.algo3.juego.modelo.Helicoptero;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.excepciones.AreaInvalidaError;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class PruebaNaveNoOperable extends TestCase {
	

	@Test
	public void testChocar() throws AreaInvalidaError, SuperposicionNavesError, NaveDestruidaError{
	/*Prueba colocar avionetas en distintas posiciones y chocar una instancia de algo42.
	Si el choque se dio, deberia devolver true, o false en caso contrario*/
	
	Plano plano= new Plano ( 100 , 100 ); 
	
	Avioneta avioneta= new Avioneta (85 , 85 , plano);
	Algo42 algo= new Algo42(85 , 79 , plano);
	/*Un algo42 tiene altura 5. O sea: va de 79 a 84. Aun no chocan.*/
	assertEquals(avioneta.intentarChocar(algo),false);
	avioneta.mover();
	/*Ahora si se produce el choque*/
	assertEquals(avioneta.intentarChocar(algo),true);
	}
	
	@Test
	public void testEstaFueraDeArea() throws SuperposicionNavesError, NaveDestruidaError{
	/*Prueba el metodo esta fuera de area con una avioneta*/

		Plano plano=new Plano ( 80,80);
		Avioneta avioneta= new Avioneta (10 , 10 , plano);
		/*Muevo 5 veces la avioneta; Va de 10 a 0, por lo cual aun esta en el plano del juego*/
		for (int i=0; i<5; i++) {
			avioneta.mover();
			assertEquals(avioneta.estadoActualFueraDeJuego(),false);
			assertEquals(avioneta.estaFueraDeArea() , false);
		}
		/*La muevo una vez mas y ya esta fuera de juego*/
		avioneta.mover();
		assertEquals(avioneta.estadoActualFueraDeJuego(),true);
		assertEquals(avioneta.estaFueraDeArea() ,true);

	}
	
	@Test
	public void testIntentarMoverBombardero() throws SuperposicionNavesError, NaveDestruidaError{
	/*Voy a probar el uso de la funcion Intentar mover con un bombardero.*/

		Plano plano=new Plano (100,100);
		Bombardero bombardero1 = new Bombardero( 50 ,92, plano);
		for (int i=0; i<20; i++) { 
			bombardero1.intentarMovimiento();
		}
		assertEquals(bombardero1.posicionX(),60.0);
		assertEquals(bombardero1.posicionY(),82.0);
		/*ahora deberia empezar a moverse a la izquierda*/
		bombardero1.intentarMovimiento();
		assertEquals(bombardero1.posicionX(),59.5);
		assertEquals(bombardero1.posicionY(),81.5);
		/*Creo otro bombardero para que este por chocarse con el. 
		 *Tener en cuenta dimensiones del bombardero= 7x7*/

		Bombardero bombardero2= new Bombardero(52.0,81.5, plano);
		bombardero2.intentarMovimiento();
		/*Segun esto, el bombardero2 deberia haber cambiado su direccion a la izquierda.*/
		assertEquals(bombardero2.posicionX(),51.5);
		assertEquals(bombardero2.posicionY(),81.0);
		/*Ahora voy a crear una avioneta (dimensiones 3x6)justo debajo del bombardero2 y voy a mover 
		 * a bombardero1, provocando que no pueda moverse.
		 */
		bombardero2.intentarMovimiento();
		/*Segun esto, el bombardero2 deberia haber cambiado su direccion a la izquierda.*/
		assertEquals(bombardero2.posicionX(),51.0);
		assertEquals(bombardero2.posicionY(),80.5);
		@SuppressWarnings("unused")
		/*Suprimi un warning, porque este metodo no necesita a la 
		 * avioneta para nada, por eso el compilador indica warning
		 */
		Avioneta avioneta=new Avioneta( 51 , 74 , plano);
		bombardero2.intentarMovimiento();
		assertEquals(bombardero2.posicionX(),51.0);
		assertEquals(bombardero2.posicionY(),80.5);

	}
	
	@SuppressWarnings("unused")
	@Test
	public void testIntentarMoverCivil() throws SuperposicionNavesError, NaveDestruidaError {
	/*Voy a probar el uso de la funcion Intentar mover utilizando una nave civil.
	Pruebo las alternativas de movimiento del avion civil, dejandole el paso libre y tambien poniendo obstaculos
	en su camino, para probar que realiza los movimientos correctos*/
		
		
		Plano plano = new Plano( 100 , 100 );
		Helicoptero helicoptero = new Helicoptero( 30 , 20 , plano );
		Civil civil = new Civil( 30 , 26 , plano );
		civil.intentarMovimiento(); //La primera vez que lo mueva, deberia poder moverse hacia adelante normalmente
		assertEquals((int)civil.posicionY() , 25 ); //Se castea para que no den diferentes 25 y 25.0000000000001
		civil.intentarMovimiento();
		assertEquals( (int) civil.posicionY() , 26 ); //Ahora si, la otra nave esta obstaculizando el camino, por eso se va hacia atras.
		civil.intentarMovimiento();
		assertEquals((int)civil.posicionY() , 25 ); //Recordar que una nave civil tiene altura 5. Ocupa desde 25 hasta 30
		/*Ahora voy a crear otra nave, una avioneta, que se ubicara detras de civil. Con esto quiero probar
		que si todos los caminos estan totalmente obstaculizados, entonces la nave no se mueve.*/
		Avioneta avioneta = new Avioneta( 30 , 31 , plano ); 
		civil.intentarMovimiento();
		assertEquals((int)civil.posicionY() , 25 );
	}
	
	@Test
	public void testRetirarse() throws SuperposicionNavesError, NaveDestruidaError{
	//Prueba el movimiento para retirarse en una avioneta

		Plano plano = new Plano( 80 , 80 );
		Avioneta avioneta = new Avioneta( 10 , 10  , plano );
		int posicion = 10;
		for ( int i= 1 ; i<5 ; i++) {  
			avioneta.retirarse();
			posicion = ( posicion + 1 );
			assertEquals((int)avioneta.posicionY(), posicion );
		}
	}
}