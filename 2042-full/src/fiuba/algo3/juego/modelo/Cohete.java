package fiuba.algo3.juego.modelo;

import fiuba.algo3.juego.modelo.excepciones.ArmaUsadaError;


public class Cohete extends Arma {

	public Cohete (Punto punto, boolean origenAlgo, Plano plano) {

		this.danio = -30;
		this.fueUsado = false;
		this.rectangulo = (new Rectangulo(4 , 2, punto));
		this.InicializarOrigenAlgo42(origenAlgo);
		this.determinarPlano(plano);
	
		try {
			this.plano.agregarArma(this);
			this.plano.agregarObjetoNuevo(this);
		} catch (ArmaUsadaError e) {
			System.out.println("Se produjo un error irrecuperable");
		}
	}

}
