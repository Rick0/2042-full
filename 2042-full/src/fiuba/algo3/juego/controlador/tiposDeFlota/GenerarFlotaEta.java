package fiuba.algo3.juego.controlador.tiposDeFlota;

import fiuba.algo3.juego.modelo.Plano;


public class GenerarFlotaEta extends GenerarFlota {


	public GenerarFlotaEta(int posEnY, Plano unPlano) {
		this.posEnY = posEnY;
		this.plano = unPlano;
	}

	@Override
	/* Genera una flota eta, una sola nave enemiga en el centro del plano */
	public void generar() {

		this.posEnX = (int)((this.plano.devolverAncho()) / 2) - 25;
		this.generarNaveEnemigaAlAzar();
	}

}
