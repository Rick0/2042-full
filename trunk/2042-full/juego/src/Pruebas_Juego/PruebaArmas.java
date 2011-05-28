package Pruebas_Juego;

import junit.framework.*;
import org.junit.Test;
import juego.*;
import excepciones.*;

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
	
	
}
