package fiuba.algo3.juego.controlador.tiposDeFlota;

import java.util.Random;
import fiuba.algo3.juego.modelo.Explorador;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class GenerarFlotaBeta implements GenerarFlota {

	int posEnY;
	Plano plano;


	public GenerarFlotaBeta(int posEnY, Plano unPlano) {
		this.posEnY = posEnY;
		this.plano = unPlano;
	}

	@Override
	/* Genera una flota beta, compuesto integramente por exploradores */
	public void generar() {

		Random generadorRandom = new Random();
		int navesACrear = 3;
		int posRandom = generadorRandom.nextInt(50);
		int posEnX = posRandom + 70;

		while (navesACrear > 0) {

			Punto posNave = new Punto(posEnX, (this.posEnY-60));
			try {
				new Explorador(posNave, 35, this.plano);
			} catch (SuperposicionNavesError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NaveDestruidaError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			posEnX = posEnX + 150;
			navesACrear--;		
		}
	}

}
