package fiuba.algo3.juego.test;

import junit.framework.TestCase;

import org.junit.Test;

import fiuba.algo3.juego.modelo.Algo42;
import fiuba.algo3.juego.modelo.Avioneta;
//import fiuba.algo3.juego.modelo.Bombardero;
import fiuba.algo3.juego.modelo.Civil;
import fiuba.algo3.juego.modelo.Helicoptero;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.AreaInvalidaError;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class NaveNoOperableTest extends TestCase {
	

	@Test
	/* Prueba colocar avionetas en distintas posiciones y chocar una instancia de algo42.
	 * Si el choque se dio, deberia devolver true, o false en caso contrario */
	public void testChocar() throws AreaInvalidaError, SuperposicionNavesError, NaveDestruidaError{

		Plano plano= new Plano ( 1000 , 1000 ); 
		
		Punto posicionAvioneta= new Punto(85,135);
		Punto posicionAlgo= new Punto(85,70);
		Avioneta avioneta= new Avioneta (posicionAvioneta , plano);
		Algo42 algo= new Algo42(posicionAlgo , plano);
		assertFalse(avioneta.intentarChocar(algo));
		
		for ( int i = 0 ; i < 15 ; i++) {
			algo.moverArriba();
		}
	
		/*Ahora si se produce el choque*/
		assertTrue(avioneta.intentarChocar(algo));
	}
	
	@Test
	/* Prueba el metodo esta fuera de area con una avioneta */
	public void testEstaFueraDeArea() throws SuperposicionNavesError, NaveDestruidaError{

		Plano plano=new Plano ( 80,80);
		Punto posicion= new Punto(10,10);
		Avioneta avioneta= new Avioneta (posicion, plano);

		/*Muevo 5 veces la avioneta; Va de 10 a 0, por lo cual aun esta en el plano del juego*/
		for (int i=0; i<5; i++) {
			avioneta.mover();
			assertEquals(avioneta.fueraDelPlano(),false);
			assertEquals(avioneta.estaFueraDelPlano() , false);
		}

		/*La muevo una vez mas y ya esta fuera de juego*/
		avioneta.mover();
		assertEquals(avioneta.fueraDelPlano(),true);
		assertEquals(avioneta.estaFueraDelPlano() ,true);
	}

	
	@SuppressWarnings("unused")
	@Test
	/* Voy a probar el uso de la funcion Intentar mover utilizando una nave civil.
	 * Pruebo las alternativas de movimiento del avion civil, dejandole el paso libre y tambien poniendo obstaculos
	 * en su camino, para probar que realiza los movimientos correctos */
	public void testIntentarMoverCivil() throws SuperposicionNavesError, NaveDestruidaError {

		Plano plano = new Plano( 1000 , 1000 );
		Punto posicionHelicoptero = new Punto(5,126);
		Helicoptero helicoptero = new Helicoptero( posicionHelicoptero, plano );
		Punto posicionCivil = new Punto(5,180);
		Civil civil = new Civil( posicionCivil , plano );
		civil.intentarMover(); //La primera vez que lo mueva, deberia poder moverse hacia adelante normalmente
		assertEquals(civil.devolverPunto().getY() , 179.0 ); 
		civil.intentarMover();
		assertEquals(  civil.devolverPunto().getY() , 178.0 ); //Ahora si, la otra nave esta obstaculizando el camino, por eso se va hacia atras.
		civil.intentarMover();
		assertEquals(civil.devolverPunto().getY() , 177.0 ); //Recordar que una nave civil tiene altura 5. Ocupa desde 25 hasta 30

		/* Ahora voy a crear otra nave, una avioneta, que se ubicara detras de civil. Con esto quiero probar
		 * que si todos los caminos estan totalmente obstaculizados, entonces la nave no se mueve */
		Punto punto = new Punto(5,228);
		Avioneta avioneta = new Avioneta(punto, plano ); 
		civil.intentarMover();
		assertEquals(civil.devolverPunto().getY() , 176.0 );
	}
	
	@Test
	/* Prueba el movimiento para retirarse en una avioneta */
	public void testRetirarse() throws SuperposicionNavesError, NaveDestruidaError{

		Plano plano = new Plano( 80 , 80 );
		Punto punto= new Punto(10,10);
		Avioneta avioneta = new Avioneta( punto , plano );
		double posicion = 10.0;

		for ( int i= 1 ; i<5 ; i++) {  
			avioneta.retirarse();
			posicion = ( posicion + 2.0 );
			assertEquals(avioneta.devolverPunto().getY(), posicion );
		}
	}

}
