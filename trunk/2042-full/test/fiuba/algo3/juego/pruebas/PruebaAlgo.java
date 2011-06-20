package fiuba.algo3.juego.pruebas;

import junit.framework.TestCase;

import org.junit.Test;

import fiuba.algo3.juego.modelo.Algo42;
import fiuba.algo3.juego.modelo.Avioneta;
import fiuba.algo3.juego.modelo.Nave;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.AreaInvalidaError;
import fiuba.algo3.juego.modelo.excepciones.ArmaNoDisponibleError;
import fiuba.algo3.juego.modelo.excepciones.NaveARastrearError;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class PruebaAlgo extends TestCase{

	@Test
	public void testPruebaMovimientoInvalido() throws AreaInvalidaError {
	/*Prueba que el algo42 no pueda moverse a zonas invalidas*/
		
		/*Necesito una instancia de plano, ya que un algo42 es una instancia de ObjetoUbicable.*/
		Plano plano=new Plano(100,100);
		Punto punto= new Punto(0,96);
		Algo42 algo=new Algo42(punto,plano);


		/*Intentar mover la nave hacia arriba deberia devolver un error del tipo AreaInvalida*/
		  try {
			    algo.moverArriba();
			    fail("Deberia haber lanzado una excepcion");

			  }
		  catch (AreaInvalidaError f) {
			    // si sale por aqui es que la prueba ha ido bien
			  }

		  /*Muestro como podria capturar el error del tipo AreaInvalida: simplemente no muevo la nave.*/
		  Punto puntoOriginal= algo.devolverPunto();
		  try{
			algo.moverArriba();
		  }
		  catch (AreaInvalidaError e) {
			  algo.cambiarPosicion(puntoOriginal);
		  }
		  
		  assertEquals(algo.devolverPunto().getX(),0.0);
		  assertEquals(algo.devolverPunto().getY(),96.0);

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
		Plano plano = new Plano(200,200);
		Punto punto= new Punto (50,50);
		Algo42 algo = new Algo42(punto,plano);
		
		Punto posicionInicial = algo.devolverPunto();
		algo.moverAbajo();
		assertEquals(posicionInicial.getY()-2,algo.devolverPunto().getY());
		
		posicionInicial = algo.devolverPunto();
		algo.moverArriba();
		assertEquals(posicionInicial.getY()+2 , algo.devolverPunto().getY());
		
		posicionInicial = algo.devolverPunto();
		algo.moverDerecha();
		assertEquals(posicionInicial.getX() +2, algo.devolverPunto().getX());
		
		posicionInicial = algo.devolverPunto();
		algo.moverDerecha();
		assertEquals( posicionInicial.getX()+2 , algo.devolverPunto().getX());
	}
		
	@Test
	public void testLanzamientoCohetes() throws AreaInvalidaError {
			//Lanzar un cohete deberia levantar error porque un algo42, por defecto, no tiene ni cohetes ni torpedos."
	Plano plano = new Plano(100,100);
	Punto punto= new Punto(50,50);
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
		Punto punto= new Punto(50,50);
		Algo42 algo = new Algo42(punto,plano);
		
		algo.dispararLaser();
		assertEquals(plano.devolverListaArmas().size() , 1);
	}
	
	public void testLanzamientoTorpedoRastreador() throws AreaInvalidaError,SuperposicionNavesError,
	NaveARastrearError,ArmaNoDisponibleError, NaveDestruidaError {
		Plano plano = new Plano(100,100);
		Punto posicionAlgo= new Punto(50,50);
		Punto posicionAvioneta= new Punto(50,50);
		Algo42 algo = new Algo42(posicionAlgo,plano);
		Nave avioneta = new Avioneta(posicionAvioneta,plano);
		
		try {
		    algo.dispararTorpedoRastreadorHacia( avioneta );
		    fail("Deberia haber lanzado una excepcion");
		}
		catch (ArmaNoDisponibleError error) {
			    // si sale por aqui es que la prueba ha ido bien
		}
		  
		try {
		    algo.dispararTorpedoRastreadorHacia( algo );
		    fail("Deberia haber lanzado una excepcion, no se puede disparar a si mismo");
		}
		catch (NaveARastrearError error) {
		   // si sale por aqui es que la prueba ha ido bien
		}
		
		
		//Le doy cohetes.
		algo.aumentarArmas(4, 0);
		//Ahora si deberia poder disparar."
		try {
			algo.dispararTorpedoRastreadorHacia( avioneta );
			//Si esto anda es porque no lanzo excepcion y ademas dispara
		} catch (ArmaNoDisponibleError error) {}
		
		assertEquals( plano.devolverListaArmas().size(),1);
	}
	
	@Test
	public void testPosicionIniciialInvalida() {
		Plano plano = new Plano(100,100);
		Algo42 algo = null;
		try {
			Punto punto=new Punto(-1,50);
			algo = new Algo42(punto,plano);
			fail("No debe poder crearse fuera de los limites del mapa");
		} catch (AreaInvalidaError error) {}
		try {
			Punto punto=new Punto(101,50);
			algo = new Algo42(punto,plano);
			fail("No debe poder crearse fuera de los limites del mapa");
		} catch (AreaInvalidaError error) {}
		try {
			Punto punto= new Punto(50,-1);
			algo = new Algo42(punto,plano);
			fail("No debe poder crearse fuera de los limites del mapa");
		} catch (AreaInvalidaError error) {}
		try {
			Punto punto= new Punto(50,101);
			algo = new Algo42(punto,plano);
			fail("No debe poder crearse fuera de los limites del mapa");
		} catch (AreaInvalidaError error) {}
		
		try {
			Punto punto= new Punto(50,50);
			algo = new Algo42(punto,plano);
		} catch (AreaInvalidaError error) {}
		
		
		
		assertEquals("Una vez verificado lo anterior debe poder crearse en una posicion valida",algo.devolverEnergia(), 100);
		
	}
}