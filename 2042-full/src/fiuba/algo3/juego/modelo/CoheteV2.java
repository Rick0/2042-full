package fiuba.algo3.juego.modelo;

import java.io.Serializable;
import java.util.Iterator;
import fiuba.algo3.juego.modelo.excepciones.AlgoSeAtacaASiMismoError;
import fiuba.algo3.juego.modelo.excepciones.ArmaNoUsadaError;
import fiuba.algo3.juego.modelo.excepciones.ArmaUsadaError;
import fiuba.algo3.juego.modelo.excepciones.AtaqueEntreNavesNoOperables;


public class CoheteV2 extends Arma implements Serializable {

	private static final long serialVersionUID = -6522154567380001117L;
	final int explosionTamanio = 200;

	
	public CoheteV2 (Punto punto, boolean origenAlgo, Plano plano) {

		this.cantidadAMover = 4;
		this.danio = -50;
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
	
	@Override
	public boolean intentarChocar(Nave unaNave) throws AlgoSeAtacaASiMismoError, AtaqueEntreNavesNoOperables {
		
		if ( unaNave.esOperable &&  origenAlgo42)
			throw new AlgoSeAtacaASiMismoError("Una nave algo42 no puede atacarse a si misma");
		if (!unaNave.esOperable && !origenAlgo42)
			throw new AtaqueEntreNavesNoOperables("Una nave no operable no puede atacar a otra del mismo tipo");

		if (unaNave.coincidePosicionCon(this.rectangulo) && !unaNave.estadoActualDestruida()) {
			this.chocarCon(unaNave);
			return true;
		}
		return false;
	}
	
	@Override
	public void chocarCon(Nave naveChocada) {
	
		double xExplosion = this.devolverPunto().getX() + 20/2 - explosionTamanio/2;
		double yExplosion = this.devolverPunto().getY() + 16/2 - explosionTamanio/2;
		Punto puntoExplosion = new Punto(xExplosion, yExplosion);
		this.rectangulo = new Rectangulo(explosionTamanio, explosionTamanio, puntoExplosion);
		
		Iterator<NaveNoOperable> iteradorNave = this.plano.listaNaves.iterator();
		while(iteradorNave.hasNext()) {
			NaveNoOperable unaNave = iteradorNave.next();
			if (unaNave.coincidePosicionCon(this.rectangulo) && !unaNave.estadoActualDestruida())
				unaNave.chocarCon(this);
		}

		this.fueUsado = true;
		try {
			plano.agregarArmaUsada(this);
		} catch (ArmaNoUsadaError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new GranExplosion(puntoExplosion, explosionTamanio, this.plano);
	}

}
