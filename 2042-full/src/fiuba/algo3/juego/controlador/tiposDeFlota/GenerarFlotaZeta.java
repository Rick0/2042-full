package fiuba.algo3.juego.controlador.tiposDeFlota;

import java.util.Random;
import fiuba.algo3.juego.modelo.Caza;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class GenerarFlotaZeta extends GenerarFlota {


	public GenerarFlotaZeta(int posEnY, Plano unPlano) {
		this.posEnY = posEnY;
		this.plano = unPlano;
	}

	@Override
	/* Genera una flota zeta, muchas pero muchas cazas en fila */
	public void generar() {

		Random generadorRandom = new Random();
		this.posEnX = 2;
		int planoAncho = this.plano.devolverAncho();

		while (posEnX < planoAncho) {

			Punto posNave = new Punto(this.posEnX, this.posEnY);
			Caza unCaza = null;
			try {
				unCaza = new Caza(posNave, this.plano);
			} catch (SuperposicionNavesError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NaveDestruidaError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.posEnX = posEnX + (int)(unCaza.devolverAncho()) + generadorRandom.nextInt(123) + 1;
		}
	}

}
