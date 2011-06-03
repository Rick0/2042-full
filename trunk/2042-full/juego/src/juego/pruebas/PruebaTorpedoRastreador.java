package juego.pruebas;

import org.junit.Test;


import junit.framework.TestCase;
import juego.*;
import juego.excepciones.NaveDestruidaError;
import juego.excepciones.SuperposicionNavesError;

public class PruebaTorpedoRastreador extends TestCase{
	
	@Test
	public void testMover() throws SuperposicionNavesError, NaveDestruidaError{
	/*Prueba el movimiento de un torpedo rastreador. Un torpedo de este tipo
	deberia ir acercandose a su objetivo.*/

		Plano plano= new Plano( 100 ,100);

		/*Pruebas de lanzamiento de torpedos. Para probarlos, necesito un objetivo.*/
		Avioneta avioneta=new Avioneta( 70, 70 , plano);
		
		TorpedoRastreador torpedo= new TorpedoRastreador (60 , 65 , true , plano);
		torpedo.determinarNaveRastreada( avioneta );
		torpedo.mover();
		/*Deberia haberse acercado un poco a la avioneta.*/
		
		assertEquals((torpedo.posicionX()),61.0);
		assertEquals((torpedo.posicionY()),66.0);
		
		for (int i=0; i<10; i++) {
			torpedo.mover();
		}

		/*Ahora deberia estar en la misma posicion de la avioneta.*/
		assertEquals((torpedo.posicionY()),70.0);
		assertEquals((torpedo.posicionX()),70.0);
	}
}
