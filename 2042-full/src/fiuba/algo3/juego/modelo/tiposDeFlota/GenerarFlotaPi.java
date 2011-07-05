package fiuba.algo3.juego.modelo.tiposDeFlota;

import java.util.Random;
import fiuba.algo3.juego.modelo.Explorador;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class GenerarFlotaPi extends GenerarFlota {

	int anchoExplorador;
	int alturaExplorador;
	static int radio = (int)(radioNormal * 2.2);


	public GenerarFlotaPi(int posEnY, Plano unPlano) {
		this.posEnY = posEnY;
		this.plano = unPlano;
	}

	@Override
	/* Genera una flota pi, compuesto por 4 explorador girando en conjunto */
	public void generar() {

		Random generadorRandom = new Random();
		posEnX = generadorRandom.nextInt(this.plano.devolverAncho() - 400) + 200;
		int posEnYAux = this.posEnY - 70;
		int cuartoDeGiro = 30;
		int navesACrear = 4;

		while (navesACrear > 0) {

			Punto posNave = new Punto(this.posEnX, posEnYAux);
			Explorador unExplorador = null;
			try {
				unExplorador = new Explorador(posNave, radio, this.plano);
			} catch (SuperposicionNavesError e) {
				// TODO Auto-generated catch block
			} catch (NaveDestruidaError e) {
				// TODO Auto-generated catch block
			}

			if (unExplorador != null) {

				for (int i = 0; i < (cuartoDeGiro*navesACrear); i++) { 
					unExplorador.intentarMover();
				}
			}
			navesACrear--;
		}
	}

}
