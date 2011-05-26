package juego;

import excepciones.ArmaUsadaError;

public class TorpedoSimple extends Arma {
	public TorpedoSimple (int x, int y, boolean origenAlgo,Plano plano ) {
		this.danio = -20;
		this.usada = false;
		this.rectangulo = (new Rectangulo(3, 3, x, y ));
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
