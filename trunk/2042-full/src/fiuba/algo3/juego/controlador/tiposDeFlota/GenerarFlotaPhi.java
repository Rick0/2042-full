package fiuba.algo3.juego.controlador.tiposDeFlota;

import java.util.Random;
import fiuba.algo3.juego.modelo.Caza;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class GenerarFlotaPhi extends GenerarFlota {

	int cazaAncho;

	public GenerarFlotaPhi(int posEnY, Plano unPlano) {
		this.posEnY = posEnY;
		this.plano = unPlano;
		this.cazaAncho = 50;
	}

	@Override
	/* Genera una flota phi, muchas pero muchas cazas una al lado de otra */
	public void generar() {

		Random generadorRandom = new Random();
		this.posEnX = generadorRandom.nextInt(8) + 2;
		int planoAncho = this.plano.devolverAncho();

		while (posEnX < planoAncho) {

			Punto posNave = new Punto(this.posEnX, this.posEnY);
			Caza unCaza = null;
			try {
				unCaza = new Caza(posNave, this.plano);
				this.cazaAncho = unCaza.devolverAncho();
			} catch (SuperposicionNavesError e) {
				// TODO Auto-generated catch block
			} catch (NaveDestruidaError e) {
				// TODO Auto-generated catch block
			}
			this.posEnX = posEnX + cazaAncho + generadorRandom.nextInt(123) + 1;
		}
	}

}
