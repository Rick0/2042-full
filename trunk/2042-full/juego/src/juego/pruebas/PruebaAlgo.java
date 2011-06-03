package juego.pruebas;

import org.junit.Test;

import juego.*;
import juego.excepciones.*;
import junit.framework.*;


public class PruebaAlgo extends TestCase{

	@Test
	public void testPruebaMovimientoInvalido() throws AreaInvalidaError {
	/*Prueba que el algo42 no pueda moverse a zonas invalidas*/
		
		/*Necesito una instancia de plano, ya que un algo42 es una instancia de ObjetoUbicable.*/
		Plano plano=new Plano(100,100);
		Algo42 algo=new Algo42(0,96,plano);


		/*Intentar mover la nave hacia arriba deberia devolver un error del tipo AreaInvalida*/
		  try {
			    algo.moverArriba();
			    fail("Deberia haber lanzado una excepcion");

			  }
		  catch (AreaInvalidaError f) {
			    // si sale por aqui es que la prueba ha ido bien
			  }

		  /*Muestro como podria capturar el error del tipo AreaInvalida: simplemente no muevo la nave.*/
		  try{
			algo.moverArriba();
		  }
		  catch (AreaInvalidaError e) {
			  algo.determinarPosicion(0,96);
		  }
		  
		  assertEquals(algo.posicionX(),0.0);
		  assertEquals(algo.posicionY(),96.0);

		  /*Intentar mover la nave hacia  la izquierda tambien deberia devolver un error del tipo AreaInvalida*/
		  try{
			    algo.moverIzquierda();
			    fail("Deberia haber lanzado una excepcion");
		  } 
		  catch (AreaInvalidaError m){
			    // si sale por aqui es que la prueba ha ido bien
		  }

	}
	
	@Test
	public void testMoverAlgo() throws AreaInvalidaError {
		/*Prueba que el algo42 pueda moverse a zonas validas*/
		
		/*Necesito una instancia de plano, ya que un algo42 es una instancia de ObjetoUbicable.*/
		Plano plano = new Plano(100,100);
		Algo42 algo = new Algo42(50,50,plano);
		double posicionInicial;
		
		posicionInicial = algo.posicionY();
		algo.moverAbajo();
		assert(posicionInicial > algo.posicionY());
		
		posicionInicial = algo.posicionY();
		algo.moverArriba();
		assert(posicionInicial < algo.posicionY());
		
		posicionInicial = algo.posicionX();
		algo.moverDerecha();
		assert(posicionInicial < algo.posicionX());
		
		posicionInicial = algo.posicionX();
		algo.moverDerecha();
		assert(posicionInicial > algo.posicionX());
	}
		
	@Test
	public void testLanzamientoCohetes() throws AreaInvalidaError {
			//Lanzar un cohete deberia levantar error porque un algo42, por defecto, no tiene ni cohetes ni torpedos."
	Plano plano = new Plano(100,100);
	Algo42 algo = new Algo42(50,50,plano);
	
	algo.setCohetes(0);
	try {
	    algo.dispararCohete();
	    fail("Deberia haber lanzado una excepcion");
	  }
	  catch (ArmaNoDisponibleError error) {
		    // si sale por aqui es que la prueba ha ido bien
	  }
	
	 //Le doy cohetes.
	  algo.aumentarArmas(0, 4);
	//Ahora si deberia poder disparar."
	 try {
		 algo.dispararCohete();
		 //Si esto anda es porque no lanzo excepcion y ademas dispara
	 } catch (ArmaNoDisponibleError error) {}
	 assertEquals(algo.getCohetes(),3);
	}
		
	public void testLanzamientoLaser() throws AreaInvalidaError {
		Plano plano = new Plano(100,100);
		Algo42 algo = new Algo42(50,50,plano);
		
		algo.dispararLaser();
		assertEquals(plano.devolverListaArmas().size() , 1);
	}
	
	public void testLanzamientoTorpedoRastreador() throws AreaInvalidaError,SuperposicionNavesError,
	NaveARastrearError,ArmaNoDisponibleError, NaveDestruidaError {
		Plano plano = new Plano(100,100);
		Algo42 algo = new Algo42(50,50,plano);
		Nave avioneta = new Avioneta(60,70,plano);
		
		try {
		    algo.dispararTorpedoHacia( avioneta );
		    fail("Deberia haber lanzado una excepcion");
		}
		catch (ArmaNoDisponibleError error) {
			    // si sale por aqui es que la prueba ha ido bien
		}
		  
		try {
		    algo.dispararTorpedoHacia( algo );
		    fail("Deberia haber lanzado una excepcion, no se puede disparar a si mismo");
		}
		catch (NaveARastrearError error) {
		   // si sale por aqui es que la prueba ha ido bien
		}
		
		
		//Le doy cohetes.
		algo.aumentarArmas(4, 0);
		//Ahora si deberia poder disparar."
		try {
			algo.dispararTorpedoHacia( avioneta );
			//Si esto anda es porque no lanzo excepcion y ademas dispara
		} catch (ArmaNoDisponibleError error) {}
		
		assertEquals( plano.devolverListaArmas().size(),1);
	}
	
	@Test
	public void testPosicionIniciialInvalida() {
		Plano plano = new Plano(100,100);
		Algo42 algo = null;
		try {
			algo = new Algo42(-1,50,plano);
			fail("No debe poder crearse fuera de los limites del mapa");
		} catch (AreaInvalidaError error) {}
		try {
			algo = new Algo42(101,50,plano);
			fail("No debe poder crearse fuera de los limites del mapa");
		} catch (AreaInvalidaError error) {}
		try {
			algo = new Algo42(50,-1,plano);
			fail("No debe poder crearse fuera de los limites del mapa");
		} catch (AreaInvalidaError error) {}
		try {
			algo = new Algo42(50,101,plano);
			fail("No debe poder crearse fuera de los limites del mapa");
		} catch (AreaInvalidaError error) {}
		
		try {
			algo = new Algo42(50,50,plano);
		} catch (AreaInvalidaError error) {}
		
		
		
		assertEquals("Una vez verificado lo anterior debe poder crearse en una posicion valida",algo.devolverCantidadEnergia(), 100);
		
	}
}