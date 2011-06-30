package fiuba.algo3.juego.test;

import junit.framework.TestCase;

import org.junit.Test;

import fiuba.algo3.juego.modelo.Avioneta;
import fiuba.algo3.juego.modelo.Laser;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.AlgoSeAtacaASiMismoError;
import fiuba.algo3.juego.modelo.excepciones.ArmaNoUsadaError;
import fiuba.algo3.juego.modelo.excepciones.ArmaUsadaError;
import fiuba.algo3.juego.modelo.excepciones.AtaqueEntreNavesNoOperables;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.NaveNoDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class PlanoTest extends TestCase{
	
	@Test
	public void testAgregarArmaInvalida() throws AlgoSeAtacaASiMismoError, AtaqueEntreNavesNoOperables, SuperposicionNavesError, NaveDestruidaError{
	/*Prueba que no puede insertarse un arma usada a la lista de armas, ni tampoco un arma no usada a la lista de armas usadas*/

		Plano plano= new Plano ( 100 , 100);
		Punto punto= new Punto(50,50);
		Laser arma= new Laser(punto,true,plano);
		try {
			plano.agregarArmaUsada(arma);
			fail("El arma no fue usada");
		} catch ( ArmaNoUsadaError error) {
			//Se espera que falle
		}
		Punto puntoAvioneta= new Punto(50,50);
		Avioneta avioneta=new Avioneta( puntoAvioneta , plano);
		/*Instancie una avioneta solo para poder usar el arma*/
		arma.intentarChocar(avioneta);
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

		Punto punto= new Punto(50,50);
		Plano plano= new Plano(100,100);
		Avioneta avioneta=new Avioneta ( punto, plano);
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
