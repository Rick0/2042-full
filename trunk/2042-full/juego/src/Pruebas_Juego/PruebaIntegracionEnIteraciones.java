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
	
	@Test
	public void testInteraccionAlgoYCazaItemsYArmas() throws AreaInvalidaError, SuperposicionNavesError {
	/*Prueba el uso tanto de los torpedos simples como de los tanques de energia,
	con una instancia de caza y una nave algo 42.*/

		
		
		Plano plano = new Plano( 100 , 100 );
		Algo42 algo = new Algo42( 50 , 5 , plano );
		Caza caza = new Caza( 50 , 80 , plano );
		Item item = null;
		
		caza.dispararTorpedo();
		Arma torpedo = plano.devolverListaArmas().get(0);
		algo.dispararLaser();
		Arma laser = plano.devolverListaArmas().get(1);
		while ( algo.devolverCantidadEnergia() == 100 ) {
			try {
				torpedo.intentarAtacar(algo);
			} catch (AtaqueEntreNavesNoOperables error) {
				//NO puede pasar, hay una sola nave no operable!
			} catch (AlgoSeAtacaASiMismoError error) {
				//Los objetos son distintos
			}
			torpedo.mover();
		}
		assertEquals( algo.devolverCantidadEnergia() , 80 );
		while ( !caza.estadoActualDestruida() ) {
			laser.mover();
			try {
				laser.intentarAtacar(caza);
			} catch (AtaqueEntreNavesNoOperables error) {
				//NO puede pasar, hay una sola nave no operable!
			} catch (AlgoSeAtacaASiMismoError error) {
				//Los objetos son distintos
			}
		}
		try {
			item = caza.dejarTanque();
		} catch (ItemNoDisponibleError error) {
			//Esto sucede si el caza no ha muerto, pero yo me aseguré de que si lo hizo
		}
	
		//Muevo la nave hacia arriba 76 veces para alcanzar el tanque
		for ( int i = 1 ; i < 76 ; i++ ) { 
			algo.moverArriba();
			item.intentarEfectoEn( algo );
		}
		assertEquals( algo.devolverCantidadEnergia() , 120 );
	}
	
	@Test
	public void testInteraccionArmasNavesNoOperables() throws AlgoSeAtacaASiMismoError, SuperposicionNavesError {
	/*Prueba interacciones entre naves no operables y las armas que ellas mismas lanzan.
	Prueba que las naves no operables no pueden atacarse entre ellas*/

		
		
		Plano plano = new Plano( 100 , 100 );
		
		Avioneta avioneta = new Avioneta( 50 , 50 , plano );
		avioneta.dispararLaser();
		Arma laser = plano.devolverListaArmas().get(0);
		//Una avioneta es una nave no operable y por lo tanto, no puede atacarse a si misma
		try {
			laser.intentarAtacar(avioneta);
			fail("No puede atacarse a si misma");
		} catch (AtaqueEntreNavesNoOperables error) {
			//Es correcto que salga por aca
		}
		
		
		Bombardero bombardero = new Bombardero( 50 , 58 , plano );
		Cohete cohete = bombardero.dispararCohete();
		//Posicion actual del cohete: 60,60. Altura: 4. Al llegar a la posicion correcta, compruebo que levanta error.
		cohete.mover(); //Posicion En Y= 56. Avioneta ocupa de 50 hacia arriba.
		cohete.mover(); //Posicion En Y= 54.
		cohete.mover(); //Posicion En Y= 52.
		cohete.mover(); //Posicion En Y= 50.
		assertEquals((int) cohete.posicionY() , 50 );
		try {
			cohete.intentarAtacar( avioneta );
			fail("Naves no operables no pueden atacarse entre si"); 
		} catch ( AtaqueEntreNavesNoOperables error) {
			//Debe salir por aca
		}
	}
	
	@Test
	public void testUsoMunicionesLasers() throws AlgoSeAtacaASiMismoError, AtaqueEntreNavesNoOperables, AreaInvalidaError {

	//Prueba el uso de municiones. Una avioneta le dispara a un algo42 y visceversa 
		
		
		Plano plano = new Plano( 100 , 100 );
		Avioneta avioneta = null;
		try {
			avioneta = new Avioneta( 50 , 30 , plano );
		} catch (SuperposicionNavesError e) {
			// No puede pasar, se crea primera
			e.printStackTrace();
		}
		Algo42 algo = new Algo42( 50 , 22 , plano );
		algo.dispararLaser();
		Arma laserA = plano.devolverListaArmas().get(0);
		avioneta.dispararLaser();
		Arma laserB = plano.devolverListaArmas().get(1);
		assertFalse ( laserB.intentarAtacar( algo ) );
		/*Esto crea dos instancias de laser: una con origen Algo42 (que por lo tanto se mueve hacia arriba)
		y otra con origen nave enemiga, que se mueve hacia abajo.*/
		laserB.mover();
		assertEquals( (int)laserB.posicionY() , 28 );// "El algo42 llega hasta 27, y el laser esta entre 33 y 28."
		assertFalse( laserB.intentarAtacar(algo) );
		laserA.mover();
		assertEquals( (int)laserA.posicionY() , 24 ); //La avioneta llega hasta 30, y el laser de 24 a 29
		assertFalse( laserA.intentarAtacar(avioneta) );
		laserB.mover();
		assertEquals( (int)laserB.posicionY() , 26 ); //l algo42 llega hasta 27, y el laser esta entre 33 y 26.
		assertTrue( laserB.intentarAtacar(algo) );
		laserA.mover();
		assertEquals( (int)laserA.posicionY() , 26); //La avioneta llega hasta 30, y el laser de 26 a 31
		assertTrue( laserA.intentarAtacar(avioneta) );
	}
}
