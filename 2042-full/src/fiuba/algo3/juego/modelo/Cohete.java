package fiuba.algo3.juego.modelo;

import java.io.Serializable;
import fiuba.algo3.juego.modelo.excepciones.ArmaUsadaError;


public class Cohete extends Arma implements Serializable {

	private static final long serialVersionUID = -6522154567380001117L;


	public Cohete (Punto punto, boolean origenAlgo, Plano plano) {

		this.danio = -30;
		this.fueUsado = false;
		this.rectangulo = new Rectangulo(20, 16, punto);
		this.inicializarOrigenAlgo42(origenAlgo);
		this.determinarPlano(plano);

		try {
			this.plano.agregarArma(this);
			this.plano.agregarObjetoNuevo(this);
		} catch (ArmaUsadaError e) {
			System.out.println("Se produjo un error irrecuperable");
		}
	}

}
