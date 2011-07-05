package fiuba.algo3.juego.modelo;

import java.io.Serializable;
import java.util.Random;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;
import fiuba.algo3.titiritero.ObjetoVivo;


public class FlotaCazas extends ObjetoUbicable implements Serializable, ObjetoVivo{

	private static final long serialVersionUID = -4108150044663064255L;
	int cantidadCazas;
	int cantidadFormacion;
	int posEnX;
	int posEnY;
	static int offSet = 3;
	static int lapsoEntreCreacion = 33 + offSet;
	int lapsoEntreCreacionCont;
	int cazaAncho;


	/* Crea una flota de cazas en formacion V */
	public FlotaCazas(int cantidadFormacion, Plano planoDeJuego) {

		this.cantidadCazas = 0;
		this.cantidadFormacion = cantidadFormacion;
		this.plano = planoDeJuego;
		this.lapsoEntreCreacionCont = lapsoEntreCreacion;
		this.cazaAncho = 33;

		Random generadorRandom = new Random();
		posEnX = generadorRandom.nextInt(this.plano.devolverAncho() - 400) + 200;
		posEnY = this.plano.devolverAltura() - 20;
	}

	public void vivir() {

		this.pasaUnTiempo();
		this.crearCaza();

		if (this.cantidadCazas >= this.cantidadFormacion)
			this.plano.agregarObjetoDestruido(this);
	}

	/* En cada instante, se actualiza el contador del tiempo de crear cazas */
	private void pasaUnTiempo() {

		if (lapsoEntreCreacionCont < lapsoEntreCreacion) {
			lapsoEntreCreacionCont++;
		}
	}

	/* Crea cazas para que adquieran la formacion V */
	private void crearCaza() {

		if (lapsoEntreCreacionCont == lapsoEntreCreacion) {
		
			if (this.cantidadCazas == 0) {

				Punto posNave = new Punto(this.posEnX, this.posEnY);
				Caza unCaza = null;
				try {
					unCaza = new Caza(posNave, this.plano);
					this.cazaAncho  = unCaza.devolverAncho();
				} catch (SuperposicionNavesError e) {
					// TODO Auto-generated catch block
				} catch (NaveDestruidaError e) {
					// TODO Auto-generated catch block
				}
			}
			else {

				Punto posNave = new Punto((this.posEnX - this.cazaAncho*cantidadCazas), this.posEnY);
				Caza unCaza = null;
				try {
					unCaza = new Caza(posNave, this.plano);
					this.cazaAncho  = unCaza.devolverAncho();
				} catch (SuperposicionNavesError e) {
					// TODO Auto-generated catch block
				} catch (NaveDestruidaError e) {
					// TODO Auto-generated catch block
				}

				posNave = new Punto((this.posEnX + this.cazaAncho*cantidadCazas), this.posEnY);
				unCaza = null;
				try {
					unCaza = new Caza(posNave, this.plano);
					this.cazaAncho  = unCaza.devolverAncho();
				} catch (SuperposicionNavesError e) {
					// TODO Auto-generated catch block
				} catch (NaveDestruidaError e) {
					// TODO Auto-generated catch block
				}
			}

			this.cantidadCazas++;
			this.lapsoEntreCreacionCont = 0;
		}
	}

	@Override
	public int getX() {
		return 0;
	}

	@Override
	public int getY() {
		return 0;
	}

}
