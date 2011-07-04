package fiuba.algo3.juego.modelo;

import java.io.Serializable;
import fiuba.algo3.juego.modelo.excepciones.ArmaUsadaError;


public class Laser extends Arma implements Serializable{

	private static final long serialVersionUID = -7667054506616914306L;


	public Laser (Punto punto, boolean origenAlgo,Plano plano ) {

		this.danio = -10;
		this.fueUsado = false;
		this.rectangulo = new Rectangulo(41, 9, punto);
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
