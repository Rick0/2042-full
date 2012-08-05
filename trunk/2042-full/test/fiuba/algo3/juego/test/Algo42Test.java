package fiuba.algo3.juego.test;

import junit.framework.TestCase;
import org.junit.Test;
import fiuba.algo3.juego.modelo.Algo42;
import fiuba.algo3.juego.modelo.Caza;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.AreaInvalidaError;
import fiuba.algo3.juego.modelo.excepciones.ArmaNoDisponibleError;
import fiuba.algo3.juego.modelo.excepciones.NaveARastrearError;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class Algo42Test extends TestCase{

	@Test
	/* Prueba que el algo42 no pueda moverse a zonas invalidas */
	public void testPruebaMovimientoInvalido() throws AreaInvalidaError {

		/*Necesito una instancia de plano, ya que un algo42 es una instancia de ObjetoUbicable.*/
		Plano plano = new Plano(100,100);
		Punto punto = new Punto(0,96);
		Algo42 algo = new Algo42(punto,plano);

		assertEquals(algo.devolverPunto().getX(),0.0);
		assertEquals(algo.devolverPunto().getY(),96.0);
	}


	@Test
	/* Prueba que el algo42 pueda moverse a zonas validas */
	public void testMoverAlgo() throws AreaInvalidaError {

		/*Necesito una instancia de plano, ya que un algo42 es una instancia de ObjetoUbicable.*/
		Plano plano = new Plano(200,200);
		Punto punto= new Punto (50,50);
		Algo42 algo = new Algo42(punto,plano);
		
		Punto posicionInicial = algo.devolverPunto();
		algo.moverAbajo();
		assertEquals(posicionInicial.getY()-algo.devolverCantidadAMover(),algo.devolverPunto().getY());
		
		posicionInicial = algo.devolverPunto();
		algo.moverArriba();
		assertEquals(posicionInicial.getY()+algo.devolverCantidadAMover(), algo.devolverPunto().getY());
		
		posicionInicial = algo.devolverPunto();
		algo.moverDerecha();
		assertEquals(posicionInicial.getX() +algo.devolverCantidadAMover(), algo.devolverPunto().getX());
		
		posicionInicial = algo.devolverPunto();
		algo.moverDerecha();
		assertEquals( posicionInicial.getX()+algo.devolverCantidadAMover() , algo.devolverPunto().getX());
	}
		
	@Test
	/* Lanzar un cohete deberia levantar error porque un algo42, por defecto, no tiene ni cohetes ni torpedos */
	public void testLanzamientoCohetes() throws AreaInvalidaError {

		Plano plano = new Plano(100,100);
		Punto punto = new Punto(50,50);
		Algo42 algo = new Algo42(punto,plano);
		
		algo.setCohetes(0);
		try {
			algo.dispararCohete();
			fail("Deberia haber lanzado una excepcion");
		  }
		  catch (ArmaNoDisponibleError error) {
				// si sale por aqui es que la prueba ha ido bien
		  }
		
		 //Le doy cohetes.
		  algo.aumentarArmas(0, 4, 0, 0);
		//Ahora si deberia poder disparar."
		 try {
			 algo.dispararCohete();
			 //Si esto anda es porque no lanzo excepcion y ademas dispara
		 } catch (ArmaNoDisponibleError error) {}
		 assertEquals(algo.getCohetes(),3);
		}
			
		public void testLanzamientoLaser() throws AreaInvalidaError {
			Plano plano = new Plano(100,100);
			Punto punto= new Punto(50,50);
			Algo42 algo = new Algo42(punto,plano);
			algo.modificarVelocidadDisparoCont(algo.devolverVelocidadDisparo());
			algo.dispararLaser();
			assertEquals(plano.devolverListaArmas().size() , 1);
	}

	@Test
	public void testLanzamientoTorpedoRastreador() throws AreaInvalidaError,SuperposicionNavesError,NaveARastrearError,ArmaNoDisponibleError, NaveDestruidaError {

		Plano plano = new Plano(100,100);
		Punto posicionAlgo = new Punto(50,50);
		Algo42 algo = new Algo42(posicionAlgo,plano);
		new Caza(new Punto(80,80),plano);

		try {
		    algo.dispararTorpedoRastreador();
		    fail("Deberia haber lanzado una excepcion");
		}
		catch (ArmaNoDisponibleError error) {
			// si sale por aqui es que la prueba ha ido bien
		}

		// Le doy cohetes.
		algo.aumentarArmas(4, 0, 0, 0);
		// Ahora si deberia poder disparar."
		try {
			algo.dispararTorpedoRastreador();
			//Si esto anda es porque no lanzo excepcion y ademas dispara
		} catch (ArmaNoDisponibleError error) {}
		
		assertEquals( plano.devolverListaArmas().size(), 1);
	}
	
	@Test
	public void testPosicionIniciialInvalida() {

		Plano plano = new Plano(100,100);
		Algo42 algo = null;

		try {
			Punto punto = new Punto(-1,50);
			algo = new Algo42(punto,plano);
			fail("No debe poder crearse fuera de los limites del mapa");
		} catch (AreaInvalidaError error) {}

		try {
			Punto punto = new Punto(101,50);
			algo = new Algo42(punto,plano);
			fail("No debe poder crearse fuera de los limites del mapa");
		} catch (AreaInvalidaError error) {}

		try {
			Punto punto = new Punto(50,-1);
			algo = new Algo42(punto,plano);
			fail("No debe poder crearse fuera de los limites del mapa");
		} catch (AreaInvalidaError error) {}

		try {
			Punto punto = new Punto(50,101);
			algo = new Algo42(punto,plano);
			fail("No debe poder crearse fuera de los limites del mapa");
		} catch (AreaInvalidaError error) {}
		
		try {
			Punto punto = new Punto(50,50);
			algo = new Algo42(punto,plano);
		} catch (AreaInvalidaError error) {}

		assertEquals("Una vez verificado lo anterior debe poder crearse en una posicion valida",algo.devolverEnergia(), 100);
	}

}
