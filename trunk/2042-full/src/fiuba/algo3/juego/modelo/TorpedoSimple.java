package fiuba.algo3.juego.modelo;

import fiuba.algo3.juego.modelo.excepciones.ArmaUsadaError;

public class TorpedoSimple extends Arma {

	public TorpedoSimple (Punto punto, boolean origenAlgo,Plano plano ) {

		this.danio = -20;
		this.fueUsada = false;
		this.rectangulo = (new Rectangulo(3, 3, punto));
		this.determinarPlano(plano);

		try {
			this.plano.agregarArma(this);
		}
		catch (ArmaUsadaError e) {
			System.out.println("Se produjo un error irrecuperable");
		}

		this.InicializarOrigenAlgo42(origenAlgo);

	}

}
