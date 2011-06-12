package fiuba.algo3.juego.pruebas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import fiuba.algo3.juego.modelo.Algo42;
import fiuba.algo3.juego.modelo.Arma;
import fiuba.algo3.juego.modelo.Avioneta;
import fiuba.algo3.juego.modelo.Bombardero;
import fiuba.algo3.juego.modelo.Caza;
import fiuba.algo3.juego.modelo.Civil;
import fiuba.algo3.juego.modelo.Cohete;
import fiuba.algo3.juego.modelo.Explorador;
import fiuba.algo3.juego.modelo.Guia;
import fiuba.algo3.juego.modelo.Guia1;
import fiuba.algo3.juego.modelo.Helicoptero;
import fiuba.algo3.juego.modelo.Item;
import fiuba.algo3.juego.modelo.NaveNoOperable;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.excepciones.AlgoSeAtacaASiMismoError;
import fiuba.algo3.juego.modelo.excepciones.AreaInvalidaError;
import fiuba.algo3.juego.modelo.excepciones.ArmaNoDisponibleError;
import fiuba.algo3.juego.modelo.excepciones.ArmaNoUsadaError;
import fiuba.algo3.juego.modelo.excepciones.AtaqueEntreNavesNoOperables;
import fiuba.algo3.juego.modelo.excepciones.ItemNoDisponibleError;
import fiuba.algo3.juego.modelo.excepciones.NaveARastrearError;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class PruebaIntegracionEnIteraciones extends TestCase {
	
	/*Prueba bastantes aspectos del juego: la creacion de niveles, manejo del area del juego,
	algunas funciones basicas del algo 42 y de las naves enemigas, y de la nave que las guia*/
	
	@Test
	public void testEventos() throws AreaInvalidaError, SuperposicionNavesError, NaveDestruidaError, AlgoSeAtacaASiMismoError, AtaqueEntreNavesNoOperables, ArmaNoUsadaError{
		
		Plano plano=new Plano(150,150);
		//Verifico que el nivel se inicializo con los valores correctos
		assertEquals(plano.devolverNivel(),1 );
		Algo42 algo= new Algo42 (50, 83 ,plano);
		//La idea seria que el algo 42 se inicialice en Y=0 o algun numero menor a 10 (ser ubicados
		//inicialmente en alturas grandes seria mas propio de naves enemigas) pero lo voy
		//a inicializar en 83 para no tener que mover tantas veces y acelerar las pruebas
		List<NaveNoOperable> flota = new ArrayList<NaveNoOperable>();
		int n=0;
		int m=10;
		//Creo 5 naves y las ubico.
		while (n<5){
			n=n+1;
			flota.add(new Civil(m,5,plano));
			m=m+10;
		}
		//Hago que mi lista de avionetas sea la flota que dirigira la nave guia.
		Guia guia= new Guia1(flota , 50 , 90 , plano);
		
		//Comienzan las pruebas de escena actual
		algo.dispararLaser();
		plano.revisarEventos();
		//Las armas se mueven dos posiciones por turno; Y el laser tiene una altura de 5 puntos
		//(o sea que esta de Y=88 a Y=83). Algo42 esta de 82 a 87.
		//Ademas la parte inferior de la nave enemiga esta en Y=89; Pero la nave enemiga se mueve.
		//cuando el algo42 dispare, va a crear un arma en su posicion, que se va a mover. En el turno siguiente,.
		//va a disparar de nuevo. En el primer turno, la nave enemiga deberia tener 10 de sus 20 puntos
		//totales, y en el siguiente deberia estar destruida.
		assertEquals(guia.posicionY(),89.0 );
		//No deberia haber cambios en la nave algo42, porque no se movio
		//En cambio si deberia haber cambios en la nave guia"
		assertEquals(algo.devolverCantidadEnergia(),100);
		assertEquals(guia.devolverCantidadEnergia(),0);
		assertTrue(guia.estadoActualDestruida());
		assertEquals(plano.devolverNivel(),2);
	}
	
	/*Prueba que realiza un choque entre una instancia de algo42 y una avioneta
	Los objetos ubicables necesitan un plano donde moverse.*/
	@Test
	public void testChoqueAlgoAvioneta() throws SuperposicionNavesError, AreaInvalidaError, NaveDestruidaError {

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
	public void testInteraccionAlgoYCazaItemsYArmas() throws AreaInvalidaError, SuperposicionNavesError, NaveDestruidaError {
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
			//Esto sucede si el caza no ha muerto, pero yo me asegur� de que si lo hizo
		}
	
		//Muevo la nave hacia arriba 76 veces para alcanzar el tanque
		for ( int i = 1 ; i < 76 ; i++ ) { 
			algo.moverArriba();
			item.intentarEfectoEn( algo );
		}
		assertEquals( algo.devolverCantidadEnergia() , 120 );
	}
	
	@Test
	public void testInteraccionArmasNavesNoOperables() throws AlgoSeAtacaASiMismoError, SuperposicionNavesError, NaveDestruidaError {
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
	public void testUsoMunicionesLasers() throws AlgoSeAtacaASiMismoError, AtaqueEntreNavesNoOperables, AreaInvalidaError, NaveDestruidaError {

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
	
	@Test
	public void testUsoYEfectoMunicionesCohetesTorpedos() throws ArmaNoDisponibleError, NaveARastrearError, 
	AreaInvalidaError, SuperposicionNavesError, AlgoSeAtacaASiMismoError, AtaqueEntreNavesNoOperables, NaveDestruidaError {

	/*Prueba el uso de armas y sus efectos en naves. Primero crea una instancia de algo42, que destruye un bombardero.
	Toma sus armas. Prueba un cohete con una instancia de helicoptero, y luego un torpedo
	con una instancia de avion civil.*/
		
		
		
		Plano plano = new Plano( 100 , 100 );
		Algo42 algo = new Algo42( 50 , 10 , plano); 
		Bombardero bombardero = new Bombardero( 50 , 30 , plano );
		//Intentar dejar el arma ahora devuelve error
		try {
			bombardero.dejarArma();
			fail("No puede dejarlporque no murio");
		} catch (ItemNoDisponibleError error) {
			//Esto es correcto
		}

		ArrayList<Arma> listaLaser = new ArrayList<Arma>(); 
		for ( int i=0 ; i < 5 ; i++ ) {
			algo.dispararLaser();
			listaLaser.add( plano.devolverListaArmas().get(i) );
		}
		//Lanzo 5 veces el laser porque el bombardero tiene 50 puntos de energia.
		Arma armaAuxiliar = null;
		while ( !bombardero.estadoActualDestruida() ) {
			Iterator<Arma> iterador = listaLaser.iterator();
			while ( iterador.hasNext() ) {
				armaAuxiliar = iterador.next();
				armaAuxiliar.mover();
				armaAuxiliar.intentarAtacar(bombardero);
			}
		}
		Item item = null;
		try {
			item = bombardero.dejarArma();
		} catch (ItemNoDisponibleError error) {
			//No puede pasar por la condicion de salida del while
		}
		//Intentar tirar el cohete antes de tenerlo deberia levantar un error
		try {
			algo.dispararCohete();
			fail("No puedo tirar cohetes si no los tengo");
		} catch (ArmaNoDisponibleError error) {}
		//Me muevo 20 veces para tomar el arma."
		for (int i=0 ; i < 20 ; i++ ) {
			algo.moverArriba();
			item.intentarEfectoEn(algo);
		}
		Helicoptero helicoptero = new Helicoptero( 50 , 60 , plano );
		algo.dispararCohete();
		Arma cohete = plano.devolverListaArmas().get(5);
		while ( !helicoptero.estadoActualDestruida() ) {
			cohete.mover();
			helicoptero.mover();
			try {
				cohete.intentarAtacar(helicoptero);
			} catch (AlgoSeAtacaASiMismoError error) {
				//No puede ocurrir
			}
			
		}
		Civil avion = new Civil( 20 , 90 , plano );
		algo.dispararTorpedoHacia(avion);
		Arma TorpedoRastreador = plano.devolverListaArmas().get(6);
		while ( !avion.estadoActualDestruida() ) {
			TorpedoRastreador.mover();
			TorpedoRastreador.intentarAtacar(avion);
			avion.mover();
		}
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testVivirAvioneta() throws SuperposicionNavesError, NaveDestruidaError, AreaInvalidaError {
		/*Prueba que al llamar al vivir de la avioneta la misma se mueva.
		*Adem�s se verifica que los disparos sean creados
		*/
		
		double posYInicial, posYFinal;
		
		Plano plano = new Plano( 100 , 100);
		//Se crea un algo porque es requerimiento del plano
		Algo42 algo42 = new Algo42( 50 , 50 , plano );
		Avioneta nave = new Avioneta( 10 , 10 , plano );
		posYInicial = nave.posicionY();
		for ( int i = 0 ; i < 9 ; i++ ) {
			nave.vivir();
		}
		posYFinal = nave.posicionY();
		assertFalse ("La nave se mueve en eje Y", posYInicial == posYFinal );
		assertTrue ("La nave crea los disparos", plano.devolverListaArmas().size() == 9 );
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testVivirExplorador() throws SuperposicionNavesError, NaveDestruidaError, AreaInvalidaError {
		/*Prueba que al llamar al vivir del explorador el mismo se mueva.
		*Adem�s se verifica que no sean creados disparos
		*/
		
		double posYInicial, posYFinal, posXInicial, posXFinal;
		
		Plano plano = new Plano( 100 , 100);
		//Se crea un algo porque es requerimiento del plano
		Algo42 algo42 = new Algo42( 50 , 50 , plano );
		Explorador nave = new Explorador( 5 , 5 , 10 , plano);
		posXInicial = nave.posicionX();
		posYInicial = nave.posicionY();
		
		for ( int i = 0 ; i < 9 ; i++ ) {
			nave.vivir();
		}
		posXFinal = nave.posicionX();
		posYFinal = nave.posicionY();
		assertFalse ("La nave se mueve en eje X", posYInicial == posYFinal );
		assertFalse ("La nave se mueve en eje Y", posXInicial == posXFinal );
		assertTrue ("La nave crea los disparos", plano.devolverListaArmas().size() == 0 );
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testVivirBombardero() throws SuperposicionNavesError, NaveDestruidaError, AreaInvalidaError {
		/*Prueba que al llamar al vivir del Bombardero el mismo se mueva.
		*Adem�s se verifica que sean creados disparos
		*/
		
		double posYInicial, posYFinal, posXInicial, posXFinal;
		
		Plano plano = new Plano( 100 , 100);
		//Se crea un algo porque es requerimiento del plano
		Algo42 algo42 = new Algo42( 50 , 50 , plano );
		Bombardero nave = new Bombardero( 10 , 10 , plano );
		posXInicial = nave.posicionX();
		posYInicial = nave.posicionY();
		
		for ( int i = 0 ; i < 9 ; i++ ) {
			nave.vivir();
		}
		posXFinal = nave.posicionX();
		posYFinal = nave.posicionY();
		assertFalse ("La nave se mueve en eje Y", posYInicial == posYFinal );
		assertFalse ("La nave se mueve en eje X", posXInicial == posXFinal );
		assertTrue ("La nave crea los disparos", plano.devolverListaArmas().size() == 9 );
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testVivirAvionCivil() throws SuperposicionNavesError, NaveDestruidaError, AreaInvalidaError {
		/*Prueba que al llamar al vivir del avion civil la misma se mueva.
		*Adem�s se verifica que los disparos sean creados
		*/
		
		double posYInicial, posYFinal, posXInicial, posXFinal;
		
		Plano plano = new Plano( 100 , 100);
		//Se crea un algo porque es requerimiento del plano
		Algo42 algo42 = new Algo42( 50 , 50 , plano );
		Civil nave = new Civil( 10 , 10 , plano );
		posXInicial = nave.posicionX();
		posYInicial = nave.posicionY();
		for ( int i = 0 ; i < 9 ; i++ ) {
			nave.vivir();
		}
		posXFinal = nave.posicionX();
		posYFinal = nave.posicionY();
		
		assertFalse ("La nave se mueve en eje Y", posYInicial == posYFinal );
		assertTrue ("La nave no mueve en eje X", posXInicial == posXFinal );
		assertTrue ("La nave No crea disparos", plano.devolverListaArmas().size() == 0 );
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testVivirCaza() throws SuperposicionNavesError, NaveDestruidaError, AreaInvalidaError {
		/*Prueba que al llamar al vivir del Caza la misma se mueva.
		*Adem�s se verifica que los disparos sean creados
		*/
		
		double posYInicial, posYFinal, posXInicial, posXFinal;
		
		Plano plano = new Plano( 100 , 100);
		//Se crea un algo porque es requerimiento del plano
		Algo42 algo42 = new Algo42( 50 , 50 , plano );
		Caza nave = new Caza( 10 , 10 , plano );
		posXInicial = nave.posicionX();
		posYInicial = nave.posicionY();
		for ( int i = 0 ; i < 9 ; i++ ) {
			nave.vivir();
		}
		posXFinal = nave.posicionX();
		posYFinal = nave.posicionY();
		
		assertFalse ("La nave se mueve en eje Y", posYInicial == posYFinal );
		assertTrue ("La nave no mueve en eje X", posXInicial == posXFinal );
		assertTrue ("La nave crea disparos", plano.devolverListaArmas().size() == 9 );
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testVivirHelicoptero() throws SuperposicionNavesError, NaveDestruidaError, AreaInvalidaError {
		/*Prueba que al llamar al vivir del Caza la misma se mueva.
		*Adem�s se verifica que los disparos sean creados
		*/
		
		double posYInicial, posYFinal, posXInicial, posXFinal;
		
		Plano plano = new Plano( 100 , 100);
		//Se crea un algo porque es requerimiento del plano
		Algo42 algo42 = new Algo42( 50 , 50 , plano );
		Helicoptero nave = new Helicoptero(10 , 10 , plano);
		
		posXInicial = nave.posicionX();
		posYInicial = nave.posicionY();
		for ( int i = 0 ; i < 7 ; i++ ) {
			nave.vivir();
		}
		posXFinal = nave.posicionX();
		posYFinal = nave.posicionY();
		
		assertFalse ("La nave se mueve en eje Y", posYInicial == posYFinal );
		assertTrue ("La nave No se mueve en eje X", posXInicial == posXFinal );
		assertTrue ("La nave crea disparos", plano.devolverListaArmas().size() == 0 );
	}
	
	
	@Test
	public void testInteraccionAvionetaYAlgo42() throws SuperposicionNavesError, NaveDestruidaError, AreaInvalidaError, AlgoSeAtacaASiMismoError, AtaqueEntreNavesNoOperables, ArmaNoUsadaError {
		/*Prueba que al llamar al vivir de la avioneta la misma se mueva.
		*Adem�s se verifica que los disparos sean creados
		*/
		
		int energiaInicial, energiaFinal;
		
		Plano plano = new Plano( 100 , 100);
		//Se crea un algo porque es requerimiento del plano
		Algo42 algo42 = new Algo42( 10 , 50 , plano );
		Avioneta nave = new Avioneta( 10 , 10 , plano );
		
		energiaInicial = algo42.devolverCantidadEnergia();
		nave.vivir();//Se mueve y adem�s crea un disparo
		Arma disparo = plano.devolverListaArmas().get(0);
		for (int i = 0 ; i < 30 ; i++ ) {
			disparo.vivir();
		}
		algo42.recibirChoque();
		energiaFinal = algo42.devolverCantidadEnergia();
		
		assertTrue ("La energia de algo42 debe bajar", energiaInicial > energiaFinal );
	}
}
