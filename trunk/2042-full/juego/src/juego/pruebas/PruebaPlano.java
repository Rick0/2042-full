package juego.pruebas;


import org.junit.*;
import junit.framework.TestCase;
import juego.*;
import juego.excepciones.*;

public class PruebaPlano extends TestCase{
	
	@Test
	public void testAgregarArmaInvalida() throws AlgoSeAtacaASiMismoError, AtaqueEntreNavesNoOperables, SuperposicionNavesError, NaveDestruidaError{
	/*Prueba que no puede insertarse un arma usada a la lista de armas, ni tampoco un arma no usada a la lista de armas usadas*/

		Plano plano= new Plano ( 100 , 100);
		Laser arma= new Laser(50,50,true,plano);
		try {
			plano.agregarArmaUsada(arma);
			fail("El arma no fue usada");
		} catch ( ArmaNoUsadaError error) {
			//Se espera que falle
		}
		Avioneta avioneta=new Avioneta(  50 , 50  , plano);
		/*Instancie una avioneta solo para poder usar el arma*/
		arma.intentarAtacar(avioneta);
		try {
			plano.agregarArma(arma);
			fail("El arma fue usada");
		} catch ( ArmaUsadaError error2) {
			//Se espera que falle
		}
	}
	
	public void testAgregarNaveInvalida() throws SuperposicionNavesError, NaveDestruidaError{
	/*Prueba que no puede insertarse un nave destruida a la lista de naves,
	 *  ni tampoco un arma en juego a la lista de naves destruidas*/

		Plano plano= new Plano(100,100);
		Avioneta avioneta=new Avioneta ( 50 , 50 , plano);
		try {
			plano.agregarNaveEliminada(avioneta);
			fail("La nave no fue destruida");
		} catch ( NaveNoDestruidaError error) {
			//Se espera que falle
		}
		avioneta.modificarEnergia(-100);
		try {
			plano.agregarNave(avioneta);
			fail("La nave esta destruida, no puede agregarse al plano");
		} catch ( NaveDestruidaError error) {
			//Se espera que falle
		}
	}
}
