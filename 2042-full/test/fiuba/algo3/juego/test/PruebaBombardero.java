package fiuba.algo3.juego.test;

import junit.framework.TestCase;

import org.junit.Test;

import fiuba.algo3.juego.modelo.Bombardero;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class PruebaBombardero extends TestCase{
	
	@Test
	public void testMoverBombardero() throws SuperposicionNavesError, NaveDestruidaError{
	/*Crea una instancia de bombardero y prueba su movimiento*/

		Plano plano=new Plano(100,100);
		Punto punto= new Punto(5,92);
		Bombardero bombardero = new Bombardero (punto,plano);
		bombardero.mover();
		assertEquals( bombardero.devolverPunto().getX() , 5.5);
		assertEquals(bombardero.devolverPunto().getY(), 91.5);
		/*Lo muevo 19 veces mas para que empiece el movimiento en 
		 * zig zag hacia la izquierda*/
		int n=0;
		while(n<19){
			n=n+1;
			bombardero.mover();
		}
		assertEquals( bombardero.devolverPunto().getX() , 15.0);
		assertEquals(bombardero.devolverPunto().getY(), 82.0);
		bombardero.mover();
		/*Pruebo que siga bajando pero moviendose a la izquierda*/
		assertEquals(bombardero.devolverPunto().getY(), 81.5);
		assertEquals( bombardero.devolverPunto().getX() , 14.5);
		bombardero.mover();
		/*Pruebo que siga a la izquierda*/
		assertEquals(bombardero.devolverPunto().getY(), 81.0);
		assertEquals( bombardero.devolverPunto().getX() , 14.0);
	}
}
