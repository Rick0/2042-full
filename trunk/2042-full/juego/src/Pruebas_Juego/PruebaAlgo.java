package Pruebas_Juego;

import org.junit.Test;

import juego.*;
import excepciones.AreaInvalidaError;
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
		  
		  assertEquals(algo.posicionX(),0);
		  assertEquals(algo.posicionY(),96);

		  /*Intentar mover la nave hacia  la izquierda tambien deberia devolver un error del tipo AreaInvalida*/
		  try{
			    algo.moverIzquierda();
			    fail("Deberia haber lanzado una excepcion");
		  } 
		  catch (AreaInvalidaError m){
			    // si sale por aqui es que la prueba ha ido bien
		  }

	}
}