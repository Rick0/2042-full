package fiuba.algo3.juego.controlador.tiposDeFlota;

import java.util.Random;
import fiuba.algo3.juego.modelo.Plano;


public class GenerarFlotaNu extends GenerarFlota {


	public GenerarFlotaNu(int posEnY, Plano unPlano) {
		this.posEnY = posEnY;
		this.plano = unPlano;
	}

	@Override
	/* Genera una flota nu, de 1 a 4 naves enemigas al azar, empezando por el lado derecho del plano */
	public void generar() {

		Random generadorRandom = new Random();
		this.posEnX = this.plano.devolverAncho() - generadorRandom.nextInt(60) - 30;
		int navesACrear = generadorRandom.nextInt(4) + 1;

		while (navesACrear > 0) {

			this.generarNaveEnemigaAlAzar();
			this.posEnX = posEnX - 60 - generadorRandom.nextInt(80);
			navesACrear--;
		}
	}

}
