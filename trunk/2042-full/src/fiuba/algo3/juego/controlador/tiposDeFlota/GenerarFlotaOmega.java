package fiuba.algo3.juego.controlador.tiposDeFlota;

import java.util.Random;
import fiuba.algo3.juego.modelo.Plano;


public class GenerarFlotaOmega extends GenerarFlota {


	public GenerarFlotaOmega(int posEnY, Plano unPlano) {
		this.posEnY = posEnY;
		this.plano = unPlano;
	}

	@Override
	/* Genera una flota omega, 4 naves enemigas al azar */
	public void generar() {

		Random generadorRandom = new Random();
		this.posEnX = generadorRandom.nextInt(30) + 60;
		int navesACrear = 4;

		while (navesACrear > 0) {

			this.generarNaveEnemigaAlAzar();
			this.posEnX = posEnX + 80 + generadorRandom.nextInt(40);
			navesACrear--;
		}
	}

}
