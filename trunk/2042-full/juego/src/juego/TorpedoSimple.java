package juego;

import excepciones.ArmaUsadaError;

public class TorpedoSimple extends Arma {
	public TorpedoSimple (double d, double f, boolean origenAlgo,Plano plano ) {
		this.danio = -20;
		this.usada = false;
		this.rectangulo = (new Rectangulo(3, 3, d, f ));
		this.determinarPlano(plano);
		try {
			this.plano.agregarArma( this );
		} catch (ArmaUsadaError e)
		{
			System.out.println("Se produjo un error irrecuperable");
		}
		this.InicializarOrigenAlgo42(origenAlgo);
		
	}

}
