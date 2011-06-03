package juego.pruebas;

import junit.framework.*;
import org.junit.Test;
import juego.*;
import juego.excepciones.*;

public class PruebaArmas extends TestCase {

	@Test
	public void testAtaqueInvalidoArmaAlgo42() throws AtaqueEntreNavesNoOperables, AreaInvalidaError{
		/*"Prueba que un arma que reconoce que su origen es una algo42, no puede atacar a una nave
		de tipo algo42"*/

		Plano plano = new Plano(100,100);
		Algo42 algo = new Algo42(50,50,plano); 
			
		Cohete cohete = new Cohete(50 ,50, true, plano);

		try {
			cohete.intentarAtacar( algo );
			fail("Algo no puede atacarse a si mismo");
		} catch ( AlgoSeAtacaASiMismoError error) {
			//Se espera que falle
		}
	}
	
	@Test
	public void testAtaqueInvalidoArmaNaveNoOperable() throws
	AreaInvalidaError, AlgoSeAtacaASiMismoError, SuperposicionNavesError, NaveDestruidaError {
	/*Prueba que un arma que reconoce que su origen no es una algo42, no puede atacar a una nave
	de tipo no operable (en este caso, una nave civil)*/

		Plano plano = new Plano(100,100);
		Cohete cohete = new Cohete(50 ,50, false, plano);
		NaveNoOperable civil = new Civil(50,50, plano);
		try {
			cohete.intentarAtacar( civil );
			fail("Deber�a fallar porque entre enemigos no se atacan");
		} catch (AtaqueEntreNavesNoOperables error) {
			//Se espera que falle
		}
	}
	
	@Test
	public void testMovimiento() {
		//Prueba el movimiento de un arma

		Plano plano = new Plano(100,100);
		Arma laser = new Laser( 50, 90, false, plano);
		
		int posicion = 90;
		
		for (int i=1; i == 30; i ++) {
			laser.intentarMovimiento();
			posicion = (posicion - 2);
			
			assertTrue("La posicion en X no cambia", laser.posicionX() == 50);
						
			assertTrue("La posicion en Y debe decrementarse en 2 unidades", laser.posicionY() == posicion );
		}
				
	}
	
	@Test
	public void testMovimientoFueraDeArea() {
	//Prueba que un arma, al salir del plano, pasa su estado a usado.

		Plano plano = new Plano( 100, 100);
		Laser laser = new Laser(50, 80, false, plano);
		
		for (int i=0; i<42; i++) {
			laser.intentarMovimiento();

		}
		assertEquals(laser.estadoUsado(),true);
		}

}
