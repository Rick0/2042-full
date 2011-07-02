package fiuba.algo3.juego.modelo;

import java.io.Serializable;
import fiuba.algo3.juego.modelo.excepciones.ArmaUsadaError;

public class TorpedoSimple extends Arma implements Serializable{

	private static final long serialVersionUID = -23138257791129L;


	public TorpedoSimple (Punto punto, boolean origenAlgo, Plano plano) {

		this.danio = -20;
		this.fueUsado = false;
		this.rectangulo = (new Rectangulo(3, 3, punto));
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
