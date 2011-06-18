package fiuba.algo3.juego.modelo;

import fiuba.algo3.juego.modelo.excepciones.ArmaUsadaError;


public class TorpedoAdaptable extends Arma {
	
	public TorpedoAdaptable (Punto punto, boolean origenAlgo,Plano plano ) {
		
		this.danio = 0;
		this.fueUsado = false;
		this.rectangulo = (new Rectangulo(4 , 2, punto));
		this.InicializarOrigenAlgo42(origenAlgo);
		this.determinarPlano(plano);

		try {
			this.plano.agregarArma( this );
		} catch (ArmaUsadaError e) {
			System.out.println("Se produjo un error irrecuperable");
		}

	}

	@Override
	/* El danio del torpedo adaptable depende de la energia de Algo42 a la hora de chocar */
	public int getDanio() {
		return (int)((this.plano.getAlgo42().devolverCantidadEnergia()) / 2);
	}

}