package fiuba.algo3.juego.modelo.tiposDeFlota;

import java.util.Random;

import fiuba.algo3.juego.modelo.ArmaAbandonada;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.TanqueEnergia;


public class GenerarFlotaZeta extends GenerarFlota {

	int planoAncho;
	int planoAltura;


	public GenerarFlotaZeta(int posEnY, Plano unPlano) {
		this.posEnY = posEnY;
		this.plano = unPlano;
		this.planoAncho = this.plano.devolverAncho();
		this.planoAltura = this.plano.devolverAltura();
	}

	@Override
	/* Genera una flota zeta, muchos items en posiciones al azar
	 * Hay chances de que no genere ningun item */
	public void generar() {

		Random generadorRandom = new Random();

		int itemsACrear = generadorRandom.nextInt(7) - 3;
		while (itemsACrear > 0) {

			this.posEnX = generadorRandom.nextInt(this.planoAncho - 60) + 30;
			this.posEnY = generadorRandom.nextInt(this.planoAltura - 60) + 30;
		
			Punto itemPos = new Punto(this.posEnX, this.posEnY);
			new TanqueEnergia (itemPos, this.plano);	

			itemsACrear--;
		}

		itemsACrear = generadorRandom.nextInt(7) - 3;
		while (itemsACrear > 0) {

			this.posEnX = generadorRandom.nextInt(this.planoAncho - 60) + 30;
			this.posEnY = generadorRandom.nextInt(this.planoAltura - 60) + 30;
		
			Punto itemPos = new Punto(this.posEnX, this.posEnY);
			new ArmaAbandonada (itemPos, this.plano);	

			itemsACrear--;
		}
	}

}
