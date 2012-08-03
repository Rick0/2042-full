package fiuba.algo3.juego.test;

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
import fiuba.algo3.juego.modelo.NaveGuia;
import fiuba.algo3.juego.modelo.Explorador;
import fiuba.algo3.juego.modelo.Helicoptero;
import fiuba.algo3.juego.modelo.Item;
import fiuba.algo3.juego.modelo.NaveNoOperable;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.AlgoSeAtacaASiMismoError;
import fiuba.algo3.juego.modelo.excepciones.AreaInvalidaError;
import fiuba.algo3.juego.modelo.excepciones.ArmaNoDisponibleError;
import fiuba.algo3.juego.modelo.excepciones.ArmaNoUsadaError;
import fiuba.algo3.juego.modelo.excepciones.AtaqueEntreNavesNoOperables;
import fiuba.algo3.juego.modelo.excepciones.ItemNoDisponibleError;
import fiuba.algo3.juego.modelo.excepciones.NaveARastrearError;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class PruebaDeIntegracionEnIteracionesTest extends TestCase {

	@Test
	/* Prueba bastantes aspectos del juego: la creacion de niveles, manejo del area del juego,
	 * algunas funciones basicas del algo 42 y de las naves enemigas, y de la nave que las guia */
	public void testEventos() throws AreaInvalidaError, SuperposicionNavesError, NaveDestruidaError, AlgoSeAtacaASiMismoError, AtaqueEntreNavesNoOperables, ArmaNoUsadaError{
		
		Plano plano=new Plano(1000,1000);
		//Verifico que el nivel se inicializo con los valores correctos
		assertEquals(plano.devolverNumeroDeNivel(),1 );
		Punto posAlgo= new Punto(50,83);
		Algo42 algo= new Algo42 (posAlgo ,plano);

		//La idea seria que el algo 42 se inicialice en Y=0 o algun numero menor a 10 (ser ubicados
		//inicialmente en alturas grandes seria mas propio de naves enemigas) pero lo voy
		//a inicializar en 83 para no tener que mover tantas veces y acelerar las pruebas
		List<NaveNoOperable> flota = new ArrayList<NaveNoOperable>();
		int n=0;
		int m=10;

		//Creo 5 naves y las ubico.
		while (n<5){
			n=n+1;
			flota.add(new Civil((new Punto(m,5)),plano));
			m=m+60;
		}

		//Hago que mi lista de avionetas sea la flota que dirigira la nave guia.
		Punto posicionGuia= new Punto(50,90);
		NaveGuia guia= new NaveGuia(posicionGuia , plano);
		
		//Comienzan las pruebas de escena actual
		algo.modificarVelocidadDisparoCont(algo.devolverVelocidadDisparo());
		int t=0;
		// Hago ocho disparos para llegar a eliminar a la nave guia.
		while(t<9){
			algo.dispararLaser();
			t=t+1;
		}
		plano.revisarEventos();
		guia.mover();
		
		//No deberia haber cambios en la nave algo42, porque no se movio
		//En cambio si deberia haber cambios en la nave guia"
		assertEquals(algo.devolverEnergia(),70); //100 es la inicial, pero fue impactado
		assertEquals(guia.devolverEnergia(),0);
		assertTrue(guia.estadoActualDestruida());
		assertTrue(plano.devolverNivel().devolverPuntosActuales()==100);
	}
	
	@Test
	/*Prueba la interaccion entre naves no operables en un plano
	 * y una nave guia, y el modo en el cual las naves no operables
	 * deben cambiar su movimiento al ser eliminada una nave guia.
	 */
	public void testInteraccionGuiaNaves() throws SuperposicionNavesError, NaveDestruidaError{
		Plano plano= new Plano(10000,10000);
		Punto posicion= new Punto(50,50);
		NaveGuia guia= new NaveGuia(posicion,plano);
		Punto puntoAlgo= new Punto(1900,1900);
		try {
			@SuppressWarnings("unused")
			Algo42 algo= new Algo42(puntoAlgo,plano);
		} catch (AreaInvalidaError e) {
			// No deberia pasar
		}
		
		// Voy a ir creando algunas naves para corroborar que huyen
		
		Punto posicionHelicoptero = new Punto(200,150);
		Helicoptero helicoptero = new Helicoptero(posicionHelicoptero,plano);
		Punto posicionBombardero = new Punto(500,500);
		Bombardero bombardero= new Bombardero(posicionBombardero,plano);
		bombardero.vivir();
		helicoptero.vivir();
		// Pruebo que las naves no estan huyendo, es decir, avanzan hacia adelante.
		assertEquals(bombardero.devolverPunto().getY(),499.5);
		assertEquals(helicoptero.devolverPunto().getY(),148.0);
		// Elimino la nave guia
		guia.modificarEnergia(-2000);
		assertTrue(guia.estadoActualDestruida());
		// Ahora pruebo que el bombardero esta huyendo
		// Pero el helicoptero no porque no es una nave enemiga
		bombardero.vivir();
		helicoptero.vivir();
		assertEquals(bombardero.devolverPunto().getY(),501.5);
	}
	
	@Test
	/*Prueba que realiza un choque entre una instancia de algo42 y una avioneta
	Los objetos ubicables necesitan un plano donde moverse.*/
	public void testChoqueAlgoAvioneta() throws SuperposicionNavesError, AreaInvalidaError, NaveDestruidaError {

		Plano plano = new Plano( 1000 , 1000 );
		Punto posicionAvioneta= new Punto(50,85);
		Punto posicionAlgo= new Punto(50,4);
		Avioneta avioneta = new Avioneta( posicionAvioneta , plano );
		Algo42 algo = new Algo42( posicionAlgo, plano );
		
		avioneta.mover(); //Avioneta posicion Y: 85
		assertEquals((int) avioneta.devolverPunto().getY() , 83 );
		algo.moverArriba();  //algo posicion Y: (4+movPixel)
		assertEquals((int) algo.devolverPunto().getY() , (int)posicionAlgo.getY()+algo.devolverCantidadAMover());
		//El algo tiene altura 64, 6+64=70<83, aun no chocan.
		assertFalse( avioneta.intentarChocar( algo ) );
		assertEquals( algo.devolverEnergia() , 100 );
		avioneta.mover(); //Avioneta posicion Y: 81
		avioneta.mover(); //Avioneta posicion Y: 79
		avioneta.mover(); //Avioneta posicion Y: 77
		avioneta.mover(); //Avioneta posicion Y: 75
		assertEquals( (int)avioneta.devolverPunto().getY() , 75 );
		algo.moverArriba(); // Algo posicion Y= 8, llega a 72
		algo.moverArriba(); // posicion Y= 10, llega a 74
		assertEquals((int) algo.devolverPunto().getY() , (int)posicionAlgo.getY()+3*(algo.devolverCantidadAMover()) );
		//Ahora si se van a chocar.
		algo.moverArriba();
		assertTrue( avioneta.intentarChocar( algo ) );
		//Pruebo que la cantidad de energia del algo42 se haya reducido en 30 puntos
		assertEquals( algo.devolverEnergia() , 70 );
		//Pruebo que la avioneta haya quedado destruida luego del choque
		assertTrue( avioneta.estadoActualDestruida() );
	}
	
	@Test
	/* Prueba interacciones entre naves algo42 y las armas que ellas mismas lanzan.
	 * Prueba que las naves algo42 no pueden atacarse a si mismas */
	public void testInteraccionAlgo42ArmasPropias() throws AreaInvalidaError, AtaqueEntreNavesNoOperables {

		Plano plano = new Plano( 100 , 100 );
		Punto punto= new Punto(50,6);
		Algo42 algo = new Algo42(punto, plano );
		algo.modificarVelocidadDisparoCont(algo.devolverVelocidadDisparo());
		algo.dispararLaser();
		algo.moverArriba();
		Arma laser = plano.devolverListaArmas().get(0);

		try {
			laser.intentarChocar( algo );
			fail("Se auto ataca, esto no puede pasar");
		} catch (AlgoSeAtacaASiMismoError error) {}
	}


	@Test
	/* Prueba interacciones entre naves no operables y las armas que ellas mismas lanzan.
	 * Prueba que las naves no operables no pueden atacarse entre ellas */
	public void testInteraccionArmasNavesNoOperables() throws AlgoSeAtacaASiMismoError, SuperposicionNavesError, NaveDestruidaError {

		Plano plano = new Plano( 1000 , 1000 );
		Punto punto= new Punto(50,50);
		Avioneta avioneta = new Avioneta( punto , plano );
		avioneta.dispararLaser();
		Arma laser = plano.devolverListaArmas().get(0);

		//Una avioneta es una nave no operable y por lo tanto, no puede atacarse a si misma
		try {
			laser.intentarChocar(avioneta);
			fail("No puede atacarse a si misma");
		} catch (AtaqueEntreNavesNoOperables error) {
			//Es correcto que salga por aca
		}

		Punto posicionBombardero= new Punto(50,120);
		Bombardero bombardero = new Bombardero( posicionBombardero, plano );
		bombardero.dispararCohete();
		Arma cohete = plano.devolverListaArmas().get(1);
		//Posicion actual del cohete: 50,120. Altura: 4. Al llegar a la posicion correcta, compruebo que levanta error.
		cohete.mover(); //Posicion En Y= 114. Avioneta ocupa de 50 hacia arriba.
		assertEquals((int)cohete.devolverPunto().getY() , (120 - cohete.devolverCantidadAMover()) );

		try {
			cohete.intentarChocar( avioneta );
			fail("Naves no operables no pueden atacarse entre si"); 
		} catch ( AtaqueEntreNavesNoOperables error) {
			//Debe salir por aca
		}
	}
	
	@Test
	/* Prueba el uso de municiones. Una avioneta le dispara a un algo42 y visceversa */
	public void testUsoMunicionesLasers() throws AlgoSeAtacaASiMismoError, AtaqueEntreNavesNoOperables, AreaInvalidaError, NaveDestruidaError {

		Plano plano = new Plano(1000, 1000);
		Avioneta avioneta = null;
		try {
			Punto punto = new Punto(50,122);
			avioneta = new Avioneta( punto, plano );
		} catch (SuperposicionNavesError e) {
			// No puede pasar, se crea primera
			e.printStackTrace();
		}

		Punto posicionAlgo = new Punto(50,50);
		Algo42 algo = new Algo42( posicionAlgo , plano );
		algo.modificarVelocidadDisparoCont(algo.devolverVelocidadDisparo());
		algo.dispararLaser();
		avioneta.modificarVelocidadDisparoCont(avioneta.devolverVelocidadDisparo());
		avioneta.dispararLaser();
		Arma laserA = plano.devolverListaArmas().get(0);
		int posicionLaserA = (int)laserA.devolverPunto().getY() - algo.devolverAltura();
		Arma laserB = plano.devolverListaArmas().get(1);
		int posicionLaserB = (int)laserB.devolverPunto().getY();
		assertFalse ( laserB.intentarChocar( algo ) );

		/* Esto crea dos instancias de laser: una con origen Algo42 (que por lo tanto se mueve hacia arriba)
		 * y otra con origen nave enemiga, que se mueve hacia abajo. */
		assertFalse( laserB.intentarChocar(algo) );
		algo.moverArriba();
		laserB.mover();	posicionLaserB -= laserB.devolverCantidadAMover();
		assertEquals( (int)laserB.devolverPunto().getY() , posicionLaserB );
		assertTrue( laserB.intentarChocar(algo) );
		laserA.mover();	posicionLaserA += algo.devolverCantidadAMover();
		laserA.mover();	posicionLaserA += algo.devolverCantidadAMover();
		assertEquals( (int)laserA.devolverPunto().getY() - algo.devolverAltura(), posicionLaserA ); 
		assertTrue( laserA.intentarChocar(avioneta) );
		laserB.mover();	posicionLaserB -= laserB.devolverCantidadAMover();
		assertEquals( (int)laserB.devolverPunto().getY() , posicionLaserB ); 
		for ( int i = 1 ; i < 11; i++ ) {
			laserB.mover();	posicionLaserB -= laserB.devolverCantidadAMover();
			assertEquals( (int)laserB.devolverPunto().getY() , posicionLaserB);
		}

		assertEquals( (int)laserB.devolverPunto().getY() , posicionLaserB );
		assertEquals ( (int)algo.devolverPunto().getY(), (int)posicionAlgo.getY()+algo.devolverCantidadAMover());
		//LaserB y algo estan ocupando mismas posiciones
		assertTrue( laserB.intentarChocar(algo) );
		for (int i = 1; i<10; i++) {
			laserA.mover();	posicionLaserA += algo.devolverCantidadAMover();
			assertEquals((int)( laserA.devolverPunto().getY()-algo.devolverAltura()), posicionLaserA);
		}
		assertTrue( laserA.intentarChocar(avioneta) );
	}

	@Test
	/* Prueba el uso de armas y sus efectos en naves. Primero crea una instancia de algo42, que destruye un bombardero.
	 * Toma sus armas. Prueba un cohete con una instancia de helicoptero, y luego un torpedo con una instancia de avion civil */
	public void testUsoYEfectoMunicionesCohetesTorpedos() throws ArmaNoDisponibleError, NaveARastrearError,	AreaInvalidaError, SuperposicionNavesError, AlgoSeAtacaASiMismoError, AtaqueEntreNavesNoOperables, NaveDestruidaError {

		Plano plano = new Plano( 1000 , 1000 );
		Punto puntoAlgo = new Punto(50,10);
		Punto puntoBombardero = new Punto(50,90);
		Algo42 algo = new Algo42(puntoAlgo , plano); 
		Bombardero bombardero = new Bombardero( puntoBombardero , plano );

		//Intentar dejar el arma ahora devuelve error
		try {
			bombardero.dejarArma();
			fail("No puede dejar porque no murio");
		} catch (ItemNoDisponibleError error) {
			//Esto es correcto
		}

		//Lanzo 5 veces el laser porque el bombardero tiene 50 puntos de energia.
		ArrayList<Arma> listaLaser = new ArrayList<Arma>(); 
		for ( int i=0 ; i < 5 ; i++ ) {
			algo.modificarVelocidadDisparoCont(algo.devolverVelocidadDisparo());
			algo.dispararLaser();
			listaLaser.add( plano.devolverListaArmas().get(i) );
		}

		Arma armaAuxiliar = null;
		while ( !bombardero.estadoActualDestruida() ) {
			Iterator<Arma> iterador = listaLaser.iterator();
			while ( iterador.hasNext() ) {
				armaAuxiliar = iterador.next();
				armaAuxiliar.mover();
				armaAuxiliar.intentarChocar(bombardero);
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
			item.intentarChocar(algo);
		}

		Punto posicionHelicoptero= new Punto(50,200);
		Helicoptero helicoptero = new Helicoptero(posicionHelicoptero , plano );
		algo.dispararCohete();
		Arma cohete = plano.devolverListaArmas().get(5);
		while ( !helicoptero.estadoActualDestruida() ) {
			cohete.mover();
			helicoptero.mover();
			try {
				cohete.intentarChocar(helicoptero);
			} catch (AlgoSeAtacaASiMismoError error) {
				//No puede ocurrir
			}
		}
	}
	
	@SuppressWarnings("unused")
	@Test
	/*Prueba que al llamar al vivir de la avioneta la misma se mueva.
	 *Ademas se verifica que los disparos sean creados */
	public void testVivirAvioneta() throws SuperposicionNavesError, NaveDestruidaError, AreaInvalidaError {

		double posYInicial, posYFinal;
		Plano plano = new Plano( 100 , 100);
		Punto puntoAlgo= new Punto(50,50);
		Punto puntoAvioneta= new Punto(10,10);

		//Se crea un algo porque es requerimiento del plano
		Algo42 algo42 = new Algo42( puntoAlgo, plano );
		Avioneta nave = new Avioneta( puntoAvioneta, plano );
		posYInicial = nave.devolverPunto().getY();

		for ( int i = 0 ; i < 9 ; i++ ) {
			nave.dispararLaser();
			nave.mover();
		}
		posYFinal = nave.devolverPunto().getY();
		assertFalse ("La nave se mueve en eje Y", posYInicial == posYFinal );
		assertTrue ("La nave crea los disparos", plano.devolverListaArmas().size() == 9 );
	}
	
	@SuppressWarnings("unused")
	@Test
	/* Prueba que al llamar al vivir del explorador el mismo se mueva.
	 * Adem�s se verifica que no sean creados disparos */
	public void testVivirExplorador() throws SuperposicionNavesError, NaveDestruidaError, AreaInvalidaError {

		double posYInicial, posYFinal, posXInicial, posXFinal;
		Plano plano = new Plano( 100 , 100);

		//Se crea un algo porque es requerimiento del plano
		Punto puntoAlgo= new Punto(50,50);
		Punto puntoExplorador= new Punto(5,5);
		Algo42 algo42 = new Algo42(puntoAlgo, plano );
		Explorador nave = new Explorador( puntoExplorador , 10 , plano);
		posXInicial = nave.devolverPunto().getX();
		posYInicial = nave.devolverPunto().getY();
		
		for ( int i = 0 ; i < 9 ; i++ ) {
			nave.vivir();
		}
		posXFinal = nave.devolverPunto().getX();
		posYFinal = nave.devolverPunto().getY();
		assertFalse ("La nave se mueve en eje X", posYInicial == posYFinal );
		assertFalse ("La nave se mueve en eje Y", posXInicial == posXFinal );
		assertTrue ("La nave crea los disparos", plano.devolverListaArmas().size() == 0 );
	}
	
	@SuppressWarnings("unused")
	@Test
	/* Prueba que al llamar al vivir del Bombardero el mismo se mueva.
	 * Ademas se verifica que sean creados disparos */
	public void testVivirBombardero() throws SuperposicionNavesError, NaveDestruidaError, AreaInvalidaError {

		double posYInicial, posYFinal, posXInicial, posXFinal;
		Plano plano = new Plano( 100 , 100);

		//Se crea un algo porque es requerimiento del plano
		Punto puntoAlgo= new Punto(50,50);
		Punto puntoBombardero= new Punto(10,10);
		Algo42 algo42 = new Algo42(puntoAlgo, plano );
		Bombardero nave = new Bombardero( puntoBombardero, plano );
		posXInicial = nave.devolverPunto().getX();
		posYInicial = nave.devolverPunto().getY();
		
		for ( int i = 0 ; i < 9 ; i++ ) {
			nave.mover();
			nave.dispararLaser();
		}
		posXFinal = nave.devolverPunto().getX();
		posYFinal = nave.devolverPunto().getY();
		assertFalse ("La nave se mueve en eje Y", posYInicial == posYFinal );
		assertFalse ("La nave se mueve en eje X", posXInicial == posXFinal );
		assertTrue ("La nave crea los disparos", plano.devolverListaArmas().size() == 9 );
	}
	
	@SuppressWarnings("unused")
	@Test
	/* Prueba que al llamar al vivir del avion civil la misma se mueva.
	 * Ademas se verifica que los disparos sean creados */
	public void testVivirAvionCivil() throws SuperposicionNavesError, NaveDestruidaError, AreaInvalidaError {

		double posYInicial, posYFinal, posXInicial, posXFinal;
		Plano plano = new Plano( 100 , 100);

		//Se crea un algo porque es requerimiento del plano
		Punto puntoAlgo= new Punto(50,50);
		Punto puntoCivil= new Punto(10,10);
		Algo42 algo42 = new Algo42( puntoAlgo, plano );
		Civil nave = new Civil( puntoCivil, plano );
		posXInicial = nave.devolverPunto().getX();
		posYInicial = nave.devolverPunto().getY();
		for ( int i = 0 ; i < 9 ; i++ ) {
			nave.vivir();
		}
		posXFinal = nave.devolverPunto().getX();
		posYFinal = nave.devolverPunto().getY();
		
		assertFalse ("La nave se mueve en eje Y", posYInicial == posYFinal );
		assertTrue ("La nave no mueve en eje X", posXInicial == posXFinal );
		assertTrue ("La nave No crea disparos", plano.devolverListaArmas().size() == 0 );
	}
	
	@SuppressWarnings("unused")
	@Test
	/* Prueba que al llamar al vivir del Caza la misma se mueva.
	 * Ademas se verifica que los disparos sean creados */
	public void testVivirCaza() throws SuperposicionNavesError, NaveDestruidaError, AreaInvalidaError {

		double posYInicial, posYFinal, posXInicial, posXFinal;
		Plano plano = new Plano( 100 , 100);

		//Se crea un algo porque es requerimiento del plano
		Punto puntoAlgo= new Punto(50,50);
		Punto puntoCaza= new Punto(10,10);
		Algo42 algo42 = new Algo42( puntoAlgo, plano );
		Caza nave = new Caza( puntoCaza, plano );
		posXInicial = nave.devolverPunto().getX();
		posYInicial = nave.devolverPunto().getY();
		for ( int i = 0 ; i < 9 ; i++ ) {
			nave.mover();
			nave.dispararTorpedo();
		}
		posXFinal = nave.devolverPunto().getX();
		posYFinal = nave.devolverPunto().getY();
		
		assertFalse ("La nave se mueve en eje Y", posYInicial == posYFinal );
		assertTrue ("La nave no mueve en eje X", posXInicial == posXFinal );
		assertTrue ("La nave crea disparos", plano.devolverListaArmas().size() == 9 );
	}
	
	@SuppressWarnings("unused")
	@Test
	/* Prueba que al llamar al vivir del Caza la misma se mueva.
	 * Ademas se verifica que los disparos sean creados */
	public void testVivirHelicoptero() throws SuperposicionNavesError, NaveDestruidaError, AreaInvalidaError {

		double posYInicial, posYFinal, posXInicial, posXFinal;
		Plano plano = new Plano( 100 , 100);

		//Se crea un algo porque es requerimiento del plano
		Punto puntoAlgo= new Punto(50,50);
		Punto puntoHelicoptero= new Punto(10,10);
		Algo42 algo42 = new Algo42( puntoAlgo , plano );
		Helicoptero nave = new Helicoptero(puntoHelicoptero , plano);
		
		posXInicial = nave.devolverPunto().getX();
		posYInicial = nave.devolverPunto().getY();
		for ( int i = 0 ; i < 7 ; i++ ) {
			nave.vivir();
		}
		posXFinal = nave.devolverPunto().getX();
		posYFinal = nave.devolverPunto().getY();
		
		assertFalse ("La nave se mueve en eje Y", posYInicial == posYFinal );
		assertTrue ("La nave No se mueve en eje X", posXInicial == posXFinal );
		assertTrue ("La nave crea disparos", plano.devolverListaArmas().size() == 0 );
	}

	@Test
	/* Prueba que al llamar al vivir de la avioneta la misma se mueva.
	 * Ademas se verifica que los disparos sean creados */
	public void testInteraccionAvionetaYAlgo42() throws SuperposicionNavesError, NaveDestruidaError, AreaInvalidaError, AlgoSeAtacaASiMismoError, AtaqueEntreNavesNoOperables, ArmaNoUsadaError {

		int energiaInicial, energiaFinal;
		Plano plano = new Plano( 100 , 100);

		//Se crea un algo porque es requerimiento del plano
		Punto puntoAlgo= new Punto(10,50);
		Punto puntoAvioneta= new Punto(10,10);
		Algo42 algo42 = new Algo42(puntoAlgo, plano );
		Avioneta nave = new Avioneta( puntoAvioneta , plano );

		energiaInicial = algo42.devolverEnergia();
		nave.vivir();//Se mueve y ademas crea un disparo
		Arma disparo = plano.devolverListaArmas().get(0);
		for (int i = 0 ; i < 30 ; i++ ) {
			disparo.vivir();
		}
		algo42.chocarCon(nave);
		energiaFinal = algo42.devolverEnergia();
		
		assertTrue ("La energia de algo42 debe bajar", energiaInicial > energiaFinal );
	}

}
