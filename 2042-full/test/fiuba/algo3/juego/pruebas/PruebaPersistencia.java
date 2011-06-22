package fiuba.algo3.juego.pruebas;

import junit.framework.TestCase;

import org.junit.Test;

import fiuba.algo3.juego.modelo.Algo42;
import fiuba.algo3.juego.modelo.Explorador;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.AreaInvalidaError;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class PruebaPersistencia extends TestCase {
	
	@Test
	@SuppressWarnings("unused")
	public void testPersistir() throws SuperposicionNavesError, NaveDestruidaError {
		
		String archivo = "plano.dat";
		Plano plano = new Plano(1000, 1000);
		try {
			Algo42 algo = new Algo42(new Punto(500,500), plano);
		} catch (AreaInvalidaError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 1; i < 5; i++) {
			Explorador nave = new Explorador(new Punto(i*100,110), 30, plano);
		}
		
		plano.persistir(archivo);
		Plano planoRestaurado = Plano.restaurar(archivo);
		
		assertTrue(plano == planoRestaurado);
	}
}
