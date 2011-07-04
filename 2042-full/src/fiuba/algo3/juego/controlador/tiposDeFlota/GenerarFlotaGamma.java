package fiuba.algo3.juego.controlador.tiposDeFlota;

import java.util.Random;
import fiuba.algo3.juego.modelo.Avioneta;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class GenerarFlotaGamma extends GenerarFlota {


	public GenerarFlotaGamma(int posEnY, Plano unPlano) {
		this.posEnY = posEnY;
		this.plano = unPlano;
	}

	@Override
	/* Genera una flota gamma, compuesto integramente por avionetas */
	public void generar() {

		Random generadorRandom = new Random();
		int navesACrear = generadorRandom.nextInt(3) + 3;
		posEnX = generadorRandom.nextInt(20) + 10;

		while (navesACrear > 0) {

			Punto posNave = new Punto(posEnX, this.posEnY);
			try {
				new Avioneta(posNave, this.plano);
			} catch (SuperposicionNavesError e) {
				// TODO Auto-generated catch block
			} catch (NaveDestruidaError e) {
				// TODO Auto-generated catch block
			}

			posEnX = posEnX + 80 + generadorRandom.nextInt(20);
			navesACrear--;		
		}
	}

}
