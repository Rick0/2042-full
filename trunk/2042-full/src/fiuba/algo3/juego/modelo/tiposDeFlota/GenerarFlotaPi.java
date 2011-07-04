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
	static int radio = (radioNormal * 2);


	public GenerarFlotaPi(int posEnY, Plano unPlano) {
		this.posEnY = posEnY;
		this.plano = unPlano;
		this.anchoExplorador = 50;
		this.alturaExplorador = 45;
	}

	@Override
	/* Genera una flota pi, compuesto por 4 explorador girando en conjunto */
	public void generar() {

		Random generadorRandom = new Random();
		posEnX = generadorRandom.nextInt(this.plano.devolverAncho() - 200) + 100;
		int posEnYAux = this.posEnY - 60;
		int offSet = 5;

		Punto posNave = new Punto(this.posEnX, posEnYAux);
		Explorador unExplorador = null;
		try {
			unExplorador = new Explorador(posNave, radio, this.plano);
			this.anchoExplorador = unExplorador.devolverAncho();
			this.alturaExplorador = unExplorador.devolverAltura();
		} catch (SuperposicionNavesError e) {
			// TODO Auto-generated catch block
		} catch (NaveDestruidaError e) {
			// TODO Auto-generated catch block
		}

		this.posEnX -= (this.anchoExplorador - offSet) * 2;
		posEnYAux -= (this.alturaExplorador - offSet) * 2;
		
		Punto posNave2 = new Punto(this.posEnX, posEnYAux);
		Explorador unExplorador2 = null;
		try {
			unExplorador2 = new Explorador(posNave2, radio, this.plano);
			this.anchoExplorador = unExplorador2.devolverAncho();
			this.alturaExplorador = unExplorador2.devolverAltura();
		} catch (SuperposicionNavesError e) {
			// TODO Auto-generated catch block
		} catch (NaveDestruidaError e) {
			// TODO Auto-generated catch block
		}

		this.posEnX += (this.anchoExplorador + offSet) * 4;
		
		Punto posNave3 = new Punto(this.posEnX, posEnYAux);
		Explorador unExplorador3 = null;
		try {
			unExplorador3 = new Explorador(posNave3, radio, this.plano);
			this.anchoExplorador = unExplorador3.devolverAncho();
			this.alturaExplorador = unExplorador3.devolverAltura();
		} catch (SuperposicionNavesError e) {
			// TODO Auto-generated catch block
		} catch (NaveDestruidaError e) {
			// TODO Auto-generated catch block
		}

		this.posEnX -= (this.anchoExplorador - offSet) * 2;
		posEnYAux -= (this.alturaExplorador - offSet) * 2;
		
		Punto posNave4 = new Punto(this.posEnX, posEnYAux);
		Explorador unExplorador4 = null;
		try {
			unExplorador4 = new Explorador(posNave4, radio, this.plano);
			this.anchoExplorador = unExplorador4.devolverAncho();
			this.alturaExplorador = unExplorador4.devolverAltura();
		} catch (SuperposicionNavesError e) {
			// TODO Auto-generated catch block
		} catch (NaveDestruidaError e) {
			// TODO Auto-generated catch block
		}
	}

}
