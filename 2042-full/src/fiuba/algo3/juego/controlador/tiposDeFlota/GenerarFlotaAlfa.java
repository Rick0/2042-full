package fiuba.algo3.juego.controlador.tiposDeFlota;

import java.util.Random;
import fiuba.algo3.juego.modelo.Bombardero;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class GenerarFlotaAlfa extends GenerarFlota {


	public GenerarFlotaAlfa(int posEnY, Plano unPlano) {
		this.posEnY = posEnY;
		this.plano = unPlano;
	}

	@Override
	/* Genera una flota alfa, compuesto integramente por bombarderos, los aviones enemigos mas poderosos */
	public void generar() {

		Random generadorRandom = new Random();
		int navesACrear = 5;
		int posRandom = generadorRandom.nextInt(20);
		posEnX = posRandom + 5;
	//	ArrayList<NaveNoOperable> lista= new ArrayList<NaveNoOperable>();

		while (navesACrear > 0) {

			Punto posNave = new Punto(posEnX, this.posEnY);
			try {
				/*Bombardero b = */new Bombardero(posNave, this.plano);
		//		lista.add(b);
			} catch (SuperposicionNavesError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NaveDestruidaError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			posEnX = posEnX + 110;
			navesACrear--;		
		}
	}

}
