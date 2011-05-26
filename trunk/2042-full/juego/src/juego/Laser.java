package juego;

import excepciones.*;

public class Laser extends Arma {
	public Laser (int x, int y, boolean origenAlgo,Plano plano ) {
		this.danio = -10;
		this.usada = false;
		this.rectangulo = (new Rectangulo(5 , 1, x, y ));
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
