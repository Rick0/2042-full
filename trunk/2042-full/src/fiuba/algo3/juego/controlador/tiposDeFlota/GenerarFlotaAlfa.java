package fiuba.algo3.juego.controlador.tiposDeFlota;

import java.util.Random;
import fiuba.algo3.juego.modelo.Bombardero;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class GenerarFlotaAlfa extends GenerarFlota {


	public GenerarFlotaAlfa(int posEnY, Plano unPlano) {
		this.posEnY = posEnY;
		this.plano = unPlano;
	}

	@Override
	/* Genera una flota alfa, compuesto integramente por bombarderos, los aviones enemigos mas poderosos */
	public void generar() {

		Random generadorRandom = new Random();
		int navesACrear = 5;
		posEnX = generadorRandom.nextInt(20) + 10;

		while (navesACrear > 0) {

			Punto posNave = new Punto(posEnX, this.posEnY);
			try {
				new Bombardero(posNave, this.plano);
			} catch (SuperposicionNavesError e) {
				// TODO Auto-generated catch block
			} catch (NaveDestruidaError e) {
				// TODO Auto-generated catch block
			}

			posEnX = posEnX + 100 + generadorRandom.nextInt(20);
			navesACrear--;		
		}
	}

}
