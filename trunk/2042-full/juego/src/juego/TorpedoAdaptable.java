package juego;

import juego.excepciones.AlgoSeAtacaASiMismoError;
import juego.excepciones.ArmaUsadaError;
import juego.excepciones.AtaqueEntreNavesNoOperables;

public class TorpedoAdaptable extends Arma {
	
	public TorpedoAdaptable (double d, double f, boolean origenAlgo,Plano plano ) {
		
		this.danio = 0;
		this.usada = false;
		this.rectangulo = (new Rectangulo(4 , 2, d, f ));
		this.InicializarOrigenAlgo42(origenAlgo);
		this.determinarPlano(plano);
		try {
			this.plano.agregarArma( this );
		} catch (ArmaUsadaError e) {
			
			System.out.println("Se produjo un error irrecuperable");
		}
		
	}
	
	public boolean intentarAtacar(Nave unaNave) throws AlgoSeAtacaASiMismoError, AtaqueEntreNavesNoOperables {
		//Ataca a la nave que recibe por parametro. Devuelve true si el ataque fue efectivo, false en caso contrario
		if (( unaNave.operable ) && ( origenAlgo42 )) {
				throw new AlgoSeAtacaASiMismoError("Una nave algo42 no puede atacarse a si misma");
		}
		if ((unaNave.operable == false ) && ( origenAlgo42 == false )) {
				throw new AtaqueEntreNavesNoOperables("Una nave no operable no puede atacar a otra del mismo tipo");
		}
		
		if (unaNave.coincidePosicionCon( this.rectangulo ) && (this.usada == false)) {
				unaNave.modificarEnergia( danio );
				this.usada = true;
				return true;
		}
		return false;
	}

}
