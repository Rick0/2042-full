package fiuba.algo3.juego.test;

import org.junit.Test;

import fiuba.algo3.juego.modelo.Bombardero;
import fiuba.algo3.juego.modelo.Helicoptero;
import fiuba.algo3.juego.modelo.NaveGuia;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;
import junit.framework.TestCase;

public class NaveGuiaTest extends TestCase{
	
	@Test
	/* Prueba los movimientos de las naves guias*/
	public void testMoverGuia() throws SuperposicionNavesError, NaveDestruidaError{

		Plano plano = new Plano (100,100);
		double pos = 95.0;
		Punto punto = new Punto(10,95);
		NaveGuia guia = new NaveGuia (punto, plano);
		for (int i=0; i<20; i++) {
			guia.mover();
			pos=pos-1;
			assertEquals(guia.devolverPunto().getY(),pos);
			assertEquals(guia.devolverPunto().getX(), 10.0);
		}
	}
	
	/*Prueba que al ser eliminada, la nave guia le dice a las naves en 
	 * el plano que deben huir
	 */
	public void testMensajeHuir() throws Throwable, NaveDestruidaError{
		Plano plano= new Plano(10000,10000);
		Punto posicion= new Punto(50,50);
		NaveGuia guia= new NaveGuia(posicion,plano);
		
		// Voy a ir creando algunas naves para corroborar que huyen
		
		Punto posicionHelicoptero = new Punto(200,150);
		Helicoptero helicoptero = new Helicoptero(posicionHelicoptero,plano);
		Punto posicionBombardero = new Punto(500,500);
		Bombardero bombardero = new Bombardero(posicionBombardero,plano);
		assertFalse(bombardero.tengoQueHuir);
		assertFalse(helicoptero.tengoQueHuir);
		guia.modificarEnergia(-2000);
		assertTrue(bombardero.tengoQueHuir);
		assertTrue(helicoptero.tengoQueHuir);
	}

}
