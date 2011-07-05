package fiuba.algo3.juego.modelo.tiposDeFlota;

import java.util.Random;
import fiuba.algo3.juego.modelo.FlotaCazas;
import fiuba.algo3.juego.modelo.Plano;


public class GenerarFlotaDelta extends GenerarFlota {


	public GenerarFlotaDelta(int posEnY, Plano unPlano) {
		this.posEnY = posEnY;
		this.plano = unPlano;
	}

	@Override
	/* Genera una flota delta, compuesto integramente por cazas, que se mueven conjuntamente en forma de V */
	public void generar() {

		Random generadorRandom = new Random();
		FlotaCazas unaFlota = new FlotaCazas(generadorRandom.nextInt(3) + 3, this.plano);
		this.plano.agregarObjetoNuevo(unaFlota);
	}

}
