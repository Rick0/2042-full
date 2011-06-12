package fiuba.algo3.juego.pruebas;

import junit.framework.TestCase;

import org.junit.Test;

import fiuba.algo3.juego.modelo.Bombardero;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class PruebaBombardero extends TestCase{
	
	@Test
	public void testMoverBombardero() throws SuperposicionNavesError, NaveDestruidaError{
	/*Crea una instancia de bombardero y prueba su movimiento*/

		Plano plano=new Plano(100,100);
		Bombardero bombardero = new Bombardero ( 5,92,plano);
		bombardero.mover();
		assertEquals( bombardero.posicionX() , 5.5);
		assertEquals(bombardero.posicionY(), 91.5);
		/*Lo muevo 19 veces mas para que empiece el movimiento en 
		 * zig zag hacia la izquierda*/
		int n=0;
		while(n<19){
			n=n+1;
			bombardero.mover();
		}
		assertEquals( bombardero.posicionX() , 15.0);
		assertEquals(bombardero.posicionY(), 82.0);
		bombardero.mover();
		/*Pruebo que siga bajando pero moviendose a la izquierda*/
		assertEquals(bombardero.posicionY(), 81.5);
		assertEquals( bombardero.posicionX() , 14.5);
		bombardero.mover();
		/*Pruebo que siga a la izquierda*/
		assertEquals(bombardero.posicionY(), 81.0);
		assertEquals( bombardero.posicionX() , 14.0);
	}
}
