package fiuba.algo3.juego.controlador;

import java.util.ArrayList;
import java.util.Random;
import fiuba.algo3.juego.modelo.*;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;
import fiuba.algo3.titiritero.ObjetoVivo;


/* Clase encargada de generar las flotas del juego
 */
public class GeneradorFlota implements ObjetoVivo {

	Plano plano;
	int cantidadNaves;
	static int velocidadSpawnear = 250;
	int velocidadSpawnearCont;
	boolean tengoQueGenerar;
	int posEnY;
	boolean necesitoJefe;
	ArrayList<NaveNoOperable> lista;


	public GeneradorFlota(Plano planoJuego) {

		plano = planoJuego;
		velocidadSpawnearCont = velocidadSpawnear;
		tengoQueGenerar = true;
		posEnY = (this.plano.devolverAltura()-10);
		necesitoJefe = false;

		this.actualizarCantidadNaves();
	}

	/* Vivir del generadorFlota */
	public void vivir() {

		this.pasaUnTiempo();
		this.actualizarCantidadNaves();


	/*	if (necesitoJefe && (velocidadSpawnearCont == velocidadSpawnear)) {
			this.generarJefe(lista);
			this.velocidadSpawnearCont = 0;
			necesitoJefe = false;
		}
	*/	if ((tengoQueGenerar) && (velocidadSpawnearCont == velocidadSpawnear) && (cantidadNaves < 11)) {
			/*lista = */this.generarFlotaGamma();
			this.velocidadSpawnearCont = 0;
			necesitoJefe = true;
		}
	}

/*	private Guia1 generarJefe(ArrayList<NaveNoOperable> lista) {
		Punto posNave = new Punto(150, this.posEnY-200);
		Guia1 guia = null;
		try {
			guia = new Guia1(lista, posNave,this.plano);
		} catch (NaveDestruidaError e) {
			System.out.print("entre");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return guia;		
	}*/
	
	/* Genera una flota alfa, compuesto integramente por bombarderos, los aviones enemigos mas poderosos */
	public void generarFlotaAlfa() {

		Random generadorRandom = new Random();
		int navesACrear = 5;
		int posRandom = generadorRandom.nextInt(20);
		int posEnX = posRandom + 5;
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

	/* Genera una flota beta, compuesto integramente por exploradores */
	public void generarFlotaBeta() {

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

	/* Genera una flota delta, compuesto integramente por cazas, que se mueven conjuntamente en forma de V */
	public void generarFlotaDelta() {

		// usar flota cazas

	}

	/* Genera una flota gamma, compuesto integramente por avionetas */
	public void generarFlotaGamma() {

		// hay q poner el movarriba mov abajo a mas de 60, a: (int)(plano.getLargo()*0,9)
		// y q se puedan irse del plano
		Random generadorRandom = new Random();
		int navesACrear = generadorRandom.nextInt(2);
		navesACrear = navesACrear + 3;
		int posRandom = generadorRandom.nextInt(20);
		int posEnX = posRandom + 5;

		while (navesACrear > 0) {

			Punto posNave = new Punto(posEnX, this.posEnY);
			try {
				new Avioneta(posNave, this.plano);
			} catch (SuperposicionNavesError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NaveDestruidaError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			posEnX = posEnX + 115;
			navesACrear--;		
		}
	}

	/* Genera una flota omega, 4 naves enemigas al azar */
	public void generarFlotaOmega() {

		// place holder

	}

	/* Genera una flota epsilon, 4 naves al azar, tanto enemigas como neutrales */
	public void generarFlotaEpsilon() {

		// place holder

	}

	/* En cada instante, se actualiza el contador de velocidad de generar flota */
	public void pasaUnTiempo() {

		if (velocidadSpawnearCont < velocidadSpawnear) {
			velocidadSpawnearCont++;
		}
	}

	/* Devuelve true si la lista de naves no operables esta vacia, false caso contrario */
	public boolean noHayNavesEnemigas() {

		if (this.plano.devolverListaNaves().size() == 0) {
			return true;
		}
		return false;
	}

	/* Actualiza la cantidad de naves no operables en el plano, y lo guarda en la variable 'cantidadNaves' */
	public void actualizarCantidadNaves() {

		this.cantidadNaves = this.plano.devolverListaNaves().size();
	}

}
