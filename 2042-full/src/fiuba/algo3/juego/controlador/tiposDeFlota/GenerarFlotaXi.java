package fiuba.algo3.juego.controlador.tiposDeFlota;

import java.util.Random;
import fiuba.algo3.juego.modelo.Bombardero;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class GenerarFlotaXi extends GenerarFlota {

	int alturaBombardero;


	public GenerarFlotaXi(int posEnY, Plano unPlano) {
		this.posEnY = posEnY;
		this.plano = unPlano;
		this.alturaBombardero = 65;
	}

	@Override
	/* Genera una flota xi, compuesto por 3 bombarderos en fila, uno atras de otro */
	public void generar() {

		Random generadorRandom = new Random();
		int navesACrear = 3;
		posEnX = generadorRandom.nextInt(this.plano.devolverAncho() - 40) + 20;
		int posEnYAux = this.posEnY;

		while (navesACrear > 0) {

			Punto posNave = new Punto(this.posEnX, posEnYAux);
			Bombardero unBombardero = null;
			try {
				unBombardero = new Bombardero(posNave, this.plano);
				this.alturaBombardero = unBombardero.devolverAltura();
			} catch (SuperposicionNavesError e) {
			} catch (NaveDestruidaError e) {
			}

			posEnYAux = posEnYAux - this.alturaBombardero - 5;
			navesACrear--;		
		}
	}

}
