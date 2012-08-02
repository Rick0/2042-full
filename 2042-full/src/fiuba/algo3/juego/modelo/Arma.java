package fiuba.algo3.juego.modelo;

import java.io.Serializable;
import java.util.Iterator;
import fiuba.algo3.juego.modelo.excepciones.AlgoSeAtacaASiMismoError;
import fiuba.algo3.juego.modelo.excepciones.ArmaNoUsadaError;
import fiuba.algo3.juego.modelo.excepciones.AtaqueEntreNavesNoOperables;


public abstract class Arma extends ObjetoUbicable implements Serializable {

	private static final long serialVersionUID = 5055567818102322794L;
	int danio;
	boolean fueUsado;
	boolean origenAlgo42;
	static int cantidadAMover = 5;


	/* True indica que quien lanzo el arma fue una instancia de Algo42,
	 * False en caso contrario
	 */
	public void inicializarOrigenAlgo42(boolean verdaderoOFalso) {
		this.origenAlgo42 = verdaderoOFalso;
	}

	public void vivir() {

		if (!fueUsado) {
			this.intentarMover();
			this.intentarChocar();
		}
	}

	/* Devuelve true si el arma fue usada */
	public boolean fueUsado() {
		return this.fueUsado;
	}

	/* Dependiendo de que avion disparo el arma, esta buscara aviones para colisionar */
	public void intentarChocar() {

		if (origenAlgo42) {
			Iterator<NaveNoOperable> iteradorNave = plano.listaNaves.iterator();
			while(iteradorNave.hasNext()) {
			    NaveNoOperable elemento = iteradorNave.next(); 
			    try {
					this.intentarChocar(elemento);
				} catch (AlgoSeAtacaASiMismoError e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (AtaqueEntreNavesNoOperables e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else {
			Iterator<NaveNoOperable> iteradorNaveAI = plano.listaNavesAI.iterator();
			while(iteradorNaveAI.hasNext()) {
				NaveNoOperable elemento = iteradorNaveAI.next(); 
				try {
					this.intentarChocar(elemento);
				} catch (AlgoSeAtacaASiMismoError e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (AtaqueEntreNavesNoOperables e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				this.intentarChocar(this.plano.devolverAlgo42());
			} catch (AlgoSeAtacaASiMismoError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AtaqueEntreNavesNoOperables e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/* Ataca a la nave que recibe por parametro. Devuelve true si el ataque fue efectivo, false en caso contrario */
	public boolean intentarChocar(Nave unaNave) throws AlgoSeAtacaASiMismoError, AtaqueEntreNavesNoOperables {
		
		if ( unaNave.esOperable &&  origenAlgo42) {
			throw new AlgoSeAtacaASiMismoError("Una nave algo42 no puede atacarse a si misma");
		}
		if (!unaNave.esOperable && !origenAlgo42) {
			throw new AtaqueEntreNavesNoOperables("Una nave no operable no puede atacar a otra del mismo tipo");
		}

		if (unaNave.coincidePosicionCon(this.rectangulo) && !unaNave.estadoActualDestruida()) {
			unaNave.chocarCon(this);
			this.chocarCon(unaNave);
			return true;
		}
		return false;
	}

	/* El arma cambia su posicion en el plano.
	 * Si el arma sale de los limites del plano, cambia su estado a usada
	 */
	public void intentarMover() {

		float x,y;
		x = this.plano.devolverAncho();
		y = this.plano.devolverAltura();

		if (((this.devolverPunto().getX()) < 0) || (( this.devolverPunto().getX() )> x )) {
			this.fueUsado = true;
			try {
				this.plano.agregarArmaUsada( this );
			} catch (ArmaNoUsadaError error) {
				System.out.println("El Juego ha sufrido una falla irrecuperable");
			}
		}
		if (((this.devolverPunto().getY()) < 0) || ((this.devolverPunto().getY()) > y )) {
			this.fueUsado = true;
			try {
				this.plano.agregarArmaUsada( this );
			} catch (ArmaNoUsadaError error) {
				System.out.println("El Juego ha sufrido una falla irrecuperable");
			}
		}

		this.mover();
	}

	/* El arma cambia su posicion */
	public void mover() {

		Punto nuevaPosicion;
		if (this.origenAlgo42) {
			nuevaPosicion = new Punto(this.devolverPunto().getX(),(this.devolverPunto().getY()) + cantidadAMover);
		} else {
			nuevaPosicion = new Punto(this.devolverPunto().getX(),(this.devolverPunto().getY()) - cantidadAMover);
		}
		this.cambiarPosicion(nuevaPosicion);
	}

	/* True indica que quien lanzo el arma fue Algo42,
	 * false en caso contrario
	 */
	public boolean origenAlgo42() {
		return this.origenAlgo42;
	}

	/* Devuelve el danio que inflige el arma */
	public int getDanio() {
		return this.danio;
	}

	/* El arma choca con una nave */
	public void chocarCon(Nave unaNave) {
		this.fueUsado = true;
		try {
			plano.agregarArmaUsada(this);
		} catch (ArmaNoUsadaError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* El arma pasa a estar usado */
	public void pasaAEstarUsado() {
		this.fueUsado = true;
	}

	/* Devuelve la cantidad a mover del arma */
	public int devolverCantidadAMover() {
		return cantidadAMover;
	}

}
