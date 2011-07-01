package fiuba.algo3.juego.controlador.tiposDeFlota;

import java.util.Random;
import fiuba.algo3.juego.modelo.Plano;


public class GenerarFlotaUpsilon extends GenerarFlota {


	public GenerarFlotaUpsilon(int posEnY, Plano unPlano) {
		this.posEnY = posEnY;
		this.plano = unPlano;
	}

	@Override
	/* Genera una flota upsilon, 3 naves al azar, solo neutrales */
	public void generar() {

		Random generadorRandom = new Random();
		this.posEnX = generadorRandom.nextInt(180) + 20;
		int navesACrear = 3;

		while (navesACrear > 0) {

			this.generarNaveNeutralAlAzar();
			this.posEnX = posEnX + generadorRandom.nextInt(100) + 60;
			navesACrear--;
		}
	}

}
