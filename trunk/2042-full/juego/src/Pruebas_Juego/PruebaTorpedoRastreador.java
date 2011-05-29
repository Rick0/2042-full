package Pruebas_Juego;

import org.junit.Test;

import excepciones.SuperposicionNavesError;

import junit.framework.TestCase;
import juego.*;

public class PruebaTorpedoRastreador extends TestCase{
	
	@Test
	public void testMover() throws SuperposicionNavesError{
	/*Prueba el movimiento de un torpedo rastreador. Un torpedo de este tipo
	deberia ir acercandose a su objetivo.*/

		Plano plano= new Plano( 100 ,100);

		/*Pruebas de lanzamiento de torpedos. Para probarlos, necesito un objetivo.*/
		Avioneta avioneta=new Avioneta( 70, 70 , plano);
		
		TorpedoRastreador torpedo= new TorpedoRastreador (60 , 65 , true , plano);
		torpedo.determinarNaveRastreada( avioneta );
		torpedo.mover();
		/*Deberia haberse acercado un poco a la avioneta.*/
		
		assertEquals((torpedo.posicionX()),61);
		assertEquals((torpedo.posicionY()),66);
		
		for (int i=0; i<10; i++) {
			torpedo.mover();
		}

		/*Ahora deberia estar en la misma posicion de la avioneta.*/
		assertEquals((torpedo.posicionY()),70);
		assertEquals((torpedo.posicionX()),70);
	}
}