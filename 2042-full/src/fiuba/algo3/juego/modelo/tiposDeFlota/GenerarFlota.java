package fiuba.algo3.juego.modelo.tiposDeFlota;

import java.util.Random;
import fiuba.algo3.juego.modelo.Avioneta;
import fiuba.algo3.juego.modelo.Bombardero;
import fiuba.algo3.juego.modelo.Caza;
import fiuba.algo3.juego.modelo.Civil;
import fiuba.algo3.juego.modelo.Explorador;
import fiuba.algo3.juego.modelo.Helicoptero;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public abstract class GenerarFlota {

	int posEnY;
	int posEnX;
	Plano plano;
	static int radioNormal = 35;


	public abstract void generar();

	/* Genera una nave al azar, tanto enemiga como neutral */
	protected void generarNaveAlAzar() {

		Random generadorRandom = new Random();
		int tipoNave = generadorRandom.nextInt(6);
		this.generarUnaNave(tipoNave);
	}

	/* Genera una nave al azar, solo naves enemigas */
	protected void generarNaveEnemigaAlAzar() {
		Random generadorRandom = new Random();
		int tipoNave = generadorRandom.nextInt(4);
		this.generarUnaNave(tipoNave);
	}

	/* Genera una nave al azar, solo naves neutrales */
	protected void generarNaveNeutralAlAzar() {
		Random generadorRandom = new Random();
		int tipoNave = generadorRandom.nextInt(2);
		tipoNave += 4;
		this.generarUnaNave(tipoNave);
	}

	/* Genera la nave */
	protected void generarUnaNave(int tipoNave) {

		switch (tipoNave) {

			case 0: {
				Punto posNave = new Punto(this.posEnX, (this.posEnY)-60);
				try {
					new Explorador(posNave, radioNormal, this.plano);
				} catch (SuperposicionNavesError e) {
					// TODO Auto-generated catch block
				} catch (NaveDestruidaError e) {
					// TODO Auto-generated catch block
				}
				break;
			}

			case 1: {
				Punto posNave = new Punto(this.posEnX, this.posEnY);
				try {
					new Bombardero(posNave, this.plano);
				} catch (SuperposicionNavesError e) {
					// TODO Auto-generated catch block
				} catch (NaveDestruidaError e) {
					// TODO Auto-generated catch block
				}
				break;
			}

			case 2: {
				Punto posNave = new Punto(this.posEnX, this.posEnY);
				try {
					new Caza(posNave, this.plano);
				} catch (SuperposicionNavesError e) {
					// TODO Auto-generated catch block
				} catch (NaveDestruidaError e) {
					// TODO Auto-generated catch block
				}
				break;
			}

			case 3: {
				Punto posNave = new Punto(this.posEnX, this.posEnY);
				try {
					new Avioneta(posNave, this.plano);
				} catch (SuperposicionNavesError e) {
					// TODO Auto-generated catch block
				} catch (NaveDestruidaError e) {
					// TODO Auto-generated catch block
				}
				break;
			}

			case 4: {
				Punto posNave = new Punto(this.posEnX, this.posEnY);
				try {
					new Helicoptero(posNave, this.plano);
				} catch (SuperposicionNavesError e) {
					// TODO Auto-generated catch block
				} catch (NaveDestruidaError e) {
					// TODO Auto-generated catch block
				}
				break;
			}

			case 5: {
				Punto posNave = new Punto(this.posEnX, this.posEnY);
				try {
					new Civil(posNave, this.plano);
				} catch (SuperposicionNavesError e) {
					// TODO Auto-generated catch block
				} catch (NaveDestruidaError e) {
					// TODO Auto-generated catch block
				}
				break;
			}
		}
	}

}
