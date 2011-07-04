package fiuba.algo3.juego.modelo.tiposDeFlota;

import fiuba.algo3.juego.modelo.Plano;


public class GenerarFlotaTheta extends GenerarFlota {


	public GenerarFlotaTheta(int posEnY, Plano unPlano) {
		this.posEnY = posEnY;
		this.plano = unPlano;
	}

	@Override
	/* Genera una flota theta, una sola nave en el centro del plano, tanto enemiga como neutral */
	public void generar() {

		this.posEnX = (int)((this.plano.devolverAncho()) / 2) - 25;
		this.generarNaveAlAzar();
	}

}
