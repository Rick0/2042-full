package fiuba.algo3.juego.controlador.tiposDeFlota;

import java.util.Random;
import fiuba.algo3.juego.modelo.*;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class GenerarFlotaOmega implements GenerarFlota {

	int posEnY;
	Plano plano;


	public GenerarFlotaOmega(int posEnY, Plano unPlano) {
		this.posEnY = posEnY;
		this.plano = unPlano;
	}

	@Override
	/* Genera una flota omega, 4 naves enemigas */
	public void generar() {

		Random generadorRandom = new Random();
	//	int navesACrear = 4;
		int posRandom = generadorRandom.nextInt(30);
		int posEnX = posRandom + 70;

		Punto posNave1 = new Punto(posEnX, (this.posEnY)-60);
		try {
			new Explorador(posNave1, 35, this.plano);
		} catch (SuperposicionNavesError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NaveDestruidaError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		posEnX = posEnX + 120;

		Punto posNave2 = new Punto(posEnX, (this.posEnY));
		try {
			new Bombardero(posNave2, this.plano);
		} catch (SuperposicionNavesError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NaveDestruidaError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		posEnX = posEnX + 120;

		Punto posNave3 = new Punto(posEnX, (this.posEnY));
		try {
			new Caza(posNave3, this.plano);
		} catch (SuperposicionNavesError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NaveDestruidaError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		posEnX = posEnX + 120;

		Punto posNave4 = new Punto(posEnX, (this.posEnY));
		try {
			new Avioneta(posNave4, this.plano);
		} catch (SuperposicionNavesError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NaveDestruidaError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//	navesACrear--;		
	}

}
