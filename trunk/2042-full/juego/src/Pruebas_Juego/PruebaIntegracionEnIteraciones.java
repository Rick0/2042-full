package Pruebas_Juego;

import org.junit.Test;

import juego.*;
import excepciones.*;
import junit.framework.*;

public class PruebaIntegracionEnIteraciones extends TestCase {
	
	@Test
	public void testChoqueAlgoAvioneta() throws SuperposicionNavesError, AreaInvalidaError {

	/*Prueba que realiza un choque entre una instancia de algo42 y una avioneta
	Los objetos ubicables necesitan un plano donde moverse.*/
	Plano plano = new Plano( 100 , 100 );
	
		Avioneta avioneta = new Avioneta( 50 , 30 , plano );
		Algo42 algo = new Algo42( 50 , 20 , plano );
		
		avioneta.mover(); //Avioneta posicion Y: 28
		assertEquals((int) avioneta.posicionY() , 28 );
		algo.moverArriba();  //algo posicion Y: 21
		assertEquals((int) algo.posicionY() , 21 );
		//El algo tiene altura 5, 21+26<28, aun no chocan.
		assertFalse( avioneta.intentarChocar( algo ) );
		assertEquals( algo.devolverCantidadEnergia() , 100 );
		avioneta.mover(); //Avioneta posicion Y: 26
		assertEquals( (int)avioneta.posicionY() , 26 );
		algo.moverArriba();  //algo posicion Y: 22
		assertEquals((int) algo.posicionY() , 22 );
		//El algo tiene altura 5, 22+5=27, ya deberian haber chocado
		assertTrue( avioneta.intentarChocar( algo ) );
		//Pruebo que la cantidad de energia del algo42 se haya reducido en 30 puntos
		assertEquals( algo.devolverCantidadEnergia() , 70 );
		//Pruebo que la avioneta haya quedado destruida luego del choque
		assertTrue( avioneta.estadoActualDestruida() );
	}
	
	@Test
	public void testInteraccionAlgo42ArmasPropias() throws AreaInvalidaError, AtaqueEntreNavesNoOperables {
	/*Prueba interacciones entre naves algo42 y las armas que ellas mismas lanzan.
	Prueba que las naves algo42 no pueden atacarse a si mismas.*/

		
		
		Plano plano = new Plano( 100 , 100 );
		Algo42 algo = new Algo42( 50 , 6 , plano );
		algo.dispararLaser();
		algo.moverArriba();
		Arma laser = plano.devolverListaArmas().get(0);
		try {
			laser.intentarAtacar( algo );
			fail("Se auto ataca, esto no puede pasar");
		} catch (AlgoSeAtacaASiMismoError error) {}
		
	}
	
}
