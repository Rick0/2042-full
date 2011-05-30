package Pruebas_Juego;

import org.junit.Test;

import excepciones.AreaInvalidaError;
import excepciones.SuperposicionNavesError;
import juego.*;
import junit.framework.TestCase;

public class PruebaNaveNoOperable extends TestCase {
	
	@Test
	public void testChocar() throws AreaInvalidaError, SuperposicionNavesError{
	/*Prueba colocar avionetas en distintas posiciones y chocar una instancia de algo42.
	Si el choque se dio, deberia devolver true, o false en caso contrario*/
	
	Plano plano= new Plano ( 100 , 100 ); 
	
	Avioneta avioneta= new Avioneta (85 , 85 , plano);
	Algo42 algo= new Algo42(85 , 79 , plano);
	/*Un algo42 tiene altura 5. O sea: va de 79 a 84. Aun no chocan.*/
	assertEquals(avioneta.intentarChocar(algo),false);
	avioneta.mover();
	/*Ahora si se produce el choque*/
	assertEquals(avioneta.intentarChocar(algo),true);
	}
	
	@Test
	public void testEstaFueraDeArea() throws SuperposicionNavesError{
	/*Prueba el metodo esta fuera de area con una avioneta*/

		Plano plano=new Plano ( 80,80);
		Avioneta avioneta= new Avioneta (10 , 10 , plano);
		/*Muevo 5 veces la avioneta; Va de 10 a 0, por lo cual aun esta en el plano del juego*/
		for (int i=0; i<5; i++) {
			avioneta.mover();
			assertEquals(avioneta.estadoActualFueraDeJuego(),false);
			assertEquals(avioneta.estaFueraDeArea() , false);
		}
		/*La muevo una vez mas y ya esta fuera de juego*/
		avioneta.mover();
		assertEquals(avioneta.estadoActualFueraDeJuego(),true);
		assertEquals(avioneta.estaFueraDeArea() ,true);

	}
	
	@Test
	public void testIntentarMoverBombardero() throws SuperposicionNavesError{
	/*Voy a probar el uso de la funcion Intentar mover con un bombardero.*/

		Plano plano=new Plano (100,100);
		Bombardero bombardero1 = new Bombardero( 50 ,92, plano);
		for (int i=0; i<20; i++) { 
			bombardero1.intentarMovimiento();
		}
		assertEquals(bombardero1.posicionX(),60.0);
		assertEquals(bombardero1.posicionY(),82.0);
		/*ahora deberia empezar a moverse a la izquierda*/
		bombardero1.intentarMovimiento();
		assertEquals(bombardero1.posicionX(),59.5);
		assertEquals(bombardero1.posicionY(),81.5);
		/*Creo otro bombardero para que este por chocarse con el. 
		 *Tener en cuenta dimensiones del bombardero= 7x7*/

		Bombardero bombardero2= new Bombardero(52.5,81.5, plano);
		bombardero2.intentarMovimiento();
		bombardero1.intentarMovimiento();
		/*Segun esto, el bombardero2 deberia haber cambiado su direccion a la izquierda.*/
		assertEquals(bombardero2.posicionX(),52);
		assertEquals(bombardero2.posicionY(),81);
		/*Ahora voy a crear una avioneta (dimensiones 3x6)justo debajo del bombardero2 y voy a mover 
		 * a bombardero1, provocando que no pueda moverse.
		 */
		@SuppressWarnings("unused")
		/*Suprimi un warning, porque este metodo no necesita a la 
		 * avioneta para nada, por eso el compilador indica warning
		 */
		Avioneta avioneta=new Avioneta( 52 , 75 , plano);
		bombardero2.intentarMovimiento();
		assertEquals(bombardero2.posicionX(),52);
		assertEquals(bombardero2.posicionY(),81);

	}
}