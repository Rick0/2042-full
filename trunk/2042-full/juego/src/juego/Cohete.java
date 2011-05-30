package juego;

//import java.lang.Object;
import excepciones.*;

public class Cohete extends Arma {
	public Cohete (double d, double f, boolean origenAlgo,Plano plano ) {
		this.danio = -30;
		this.usada = false;
		this.rectangulo = (new Rectangulo(4 , 2, d, f ));
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
