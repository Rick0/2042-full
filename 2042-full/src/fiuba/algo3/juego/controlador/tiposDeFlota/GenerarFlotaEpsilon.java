package fiuba.algo3.juego.controlador.tiposDeFlota;

import java.util.Random;
import fiuba.algo3.juego.modelo.Plano;


public class GenerarFlotaEpsilon extends GenerarFlota {


	public GenerarFlotaEpsilon(int posEnY, Plano unPlano) {
		this.posEnY = posEnY;
		this.plano = unPlano;
	}

	@Override
	/* Genera una flota epsilon, 4 naves al azar, tanto enemigas como neutrales */
	public void generar() {

		Random generadorRandom = new Random();
		this.posEnX = generadorRandom.nextInt(40) + 60;
		int navesACrear = 4;

		while (navesACrear > 0) {

			this.generarNaveAlAzar();
			this.posEnX = posEnX + 60 + generadorRandom.nextInt(60);
			navesACrear--;
		}
	}

}
