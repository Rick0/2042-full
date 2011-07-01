package fiuba.algo3.juego.controlador.tiposDeFlota;

import java.util.Random;
import fiuba.algo3.juego.modelo.Avioneta;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class GenerarFlotaGamma implements GenerarFlota {

	int posEnY;
	Plano plano;


	public GenerarFlotaGamma(int posEnY, Plano unPlano) {
		this.posEnY = posEnY;
		this.plano = unPlano;
	}

	@Override
	/* Genera una flota gamma, compuesto integramente por avionetas */
	public void generar() {

		// hay q poner el movarriba mov abajo a mas de 60, a: (int)(plano.getLargo()*0,9)
		// y q se puedan irse del plano
		Random generadorRandom = new Random();
		int navesACrear = generadorRandom.nextInt(2);
		navesACrear = navesACrear + 3;
		int posRandom = generadorRandom.nextInt(20);
		int posEnX = posRandom + 5;

		while (navesACrear > 0) {

			Punto posNave = new Punto(posEnX, this.posEnY);
			try {
				new Avioneta(posNave, this.plano);
			} catch (SuperposicionNavesError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NaveDestruidaError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			posEnX = posEnX + 115;
			navesACrear--;		
		}
	}

}
