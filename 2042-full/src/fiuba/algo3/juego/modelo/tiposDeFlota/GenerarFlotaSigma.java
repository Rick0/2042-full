package fiuba.algo3.juego.modelo.tiposDeFlota;

import java.util.Random;
import fiuba.algo3.juego.modelo.NaveGuia;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class GenerarFlotaSigma extends GenerarFlota {


	public GenerarFlotaSigma(int posEnY, Plano unPlano) {
		this.posEnY = posEnY;
		this.plano = unPlano;
	}

	@Override
	/* Genera una flota sigma, compuesto por una nave guia solamente */
	public void generar() {

		Random generadorRandom = new Random();
		this.posEnX = generadorRandom.nextInt(this.plano.devolverAncho() - 300) + 150;

		Punto posNave = new Punto(this.posEnX, this.posEnY - 60);
		try {
			new NaveGuia(posNave, this.plano);
		} catch (SuperposicionNavesError e) {
			// TODO Auto-generated catch block
		} catch (NaveDestruidaError e) {
			// TODO Auto-generated catch block
		}
	}

}
