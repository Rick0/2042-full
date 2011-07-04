package fiuba.algo3.juego.modelo.tiposDeFlota;

import java.util.Random;
import fiuba.algo3.juego.modelo.Avioneta;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class GenerarFlotaChi extends GenerarFlota {

	int alturaAvioneta;


	public GenerarFlotaChi(int posEnY, Plano unPlano) {
		this.posEnY = posEnY;
		this.plano = unPlano;
		this.alturaAvioneta = 55;
	}

	@Override
	/* Genera una flota chi, compuesto por 3 avionetas en fila, uno atras de otro */
	public void generar() {

		Random generadorRandom = new Random();
		int navesACrear = 3;
		posEnX = generadorRandom.nextInt(this.plano.devolverAncho() - 40) + 20;
		int posEnYAux = this.posEnY;

		while (navesACrear > 0) {

			Punto posNave = new Punto(this.posEnX, posEnYAux);
			Avioneta unaAvioneta = null;
			try {
				unaAvioneta = new Avioneta(posNave, this.plano);
				this.alturaAvioneta = unaAvioneta.devolverAltura();
			} catch (SuperposicionNavesError e) {
				// TODO Auto-generated catch block
			} catch (NaveDestruidaError e) {
				// TODO Auto-generated catch block
			}

			posEnYAux = posEnYAux - this.alturaAvioneta - 10;
			navesACrear--;		
		}
	}

}
