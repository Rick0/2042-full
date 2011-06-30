package fiuba.algo3.juego.test;

import junit.framework.TestCase;

import org.junit.Test;

import fiuba.algo3.juego.modelo.Avioneta;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.TorpedoRastreador;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class PruebaTorpedoRastreador extends TestCase{
	
	@Test
	public void testMover() throws SuperposicionNavesError, NaveDestruidaError{
	/*Prueba el movimiento de un torpedo rastreador. Un torpedo de este tipo
	deberia ir acercandose a su objetivo.*/

		Plano plano= new Plano( 100 ,100);

		/*Pruebas de lanzamiento de torpedos. Para probarlos, necesito un objetivo.*/
		Punto posicionAvioneta= new Punto(70,70);
		Punto posicionTorpedo= new Punto(60,65);
		Avioneta avioneta=new Avioneta(posicionAvioneta, plano);
		
		TorpedoRastreador torpedo= new TorpedoRastreador (posicionTorpedo, true , plano);
		torpedo.determinarNaveRastreada( avioneta );
		torpedo.mover();
		/*Deberia haberse acercado un poco a la avioneta.*/
		
		assertEquals((torpedo.devolverPunto().getX()),61.0);
		assertEquals((torpedo.devolverPunto().getY()),66.0);
		
		for (int i=0; i<10; i++) {
			torpedo.mover();
		}

		/*Ahora deberia estar en la misma posicion de la avioneta.*/
		assertEquals((torpedo.devolverPunto().getY()),70.0);
		assertEquals((torpedo.devolverPunto().getX()),70.0);
	}
}
