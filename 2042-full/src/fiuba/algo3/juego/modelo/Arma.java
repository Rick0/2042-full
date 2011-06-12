package fiuba.algo3.juego.modelo;
import java.util.Iterator;

import fiuba.algo3.juego.modelo.excepciones.AlgoSeAtacaASiMismoError;
import fiuba.algo3.juego.modelo.excepciones.ArmaNoUsadaError;
import fiuba.algo3.juego.modelo.excepciones.AtaqueEntreNavesNoOperables;



public abstract class Arma extends ObjetoUbicable {
	
	int danio;
	boolean usada;
	boolean origenAlgo42;


	/* True indica que quien lanzo el arma fue una instancia de Algo42,
	 * False en caso contrario
	 */
	public void InicializarOrigenAlgo42( boolean verdaderoOFalso) {
		this.origenAlgo42 = verdaderoOFalso;
	}
	
	public void vivir() throws AlgoSeAtacaASiMismoError, AtaqueEntreNavesNoOperables, ArmaNoUsadaError{
		
		this.intentarMovimiento();
		if(origenAlgo42){
			Iterator<NaveNoOperable> iteradorNave = plano.listaNaves.iterator();

			while(iteradorNave.hasNext()) {
			    NaveNoOperable elemento = iteradorNave.next(); 
			    this.intentarAtacar(elemento);
			} 
		} else {
			this.intentarAtacar(plano.algo42);
		}
		if(usada){
			plano.agregarArmaUsada(this);
		}
	}
	
	/* Devuelve true si el arma fue usada */
	public boolean estadoUsado() {
		return (this.usada);
	}

	/* Ataca a la nave que recibe por parametro. Devuelve true si el ataque fue efectivo, false en caso contrario */
	public boolean intentarAtacar(Nave unaNave) throws AlgoSeAtacaASiMismoError, AtaqueEntreNavesNoOperables {
		
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

	/* El arma cambia su posicion en el plano.
	 * Si el arma sale de los limites del plano, cambia su estado a usada
	 */
	public void intentarMovimiento() {

		float x,y;
		x = (this.plano).devolverAncho();
		y = (this.plano).devolverAltura();

		if (( (this.posicionX()) < 0) || (( this.posicionX() )> x )) {
			this.usada = true;
			try {
				this.plano.agregarArmaUsada( this );
			} catch (ArmaNoUsadaError error) {
				System.out.println("El Juego ha sufrido una falla irrecuperable");
			}
		}
		if (((this.posicionY()) < 0) || ((this.posicionY()) > y )) {
			this.usada = true;
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

		if (this.origenAlgo42) {
			this.determinarPosicion( this.posicionX(), (this.posicionY() + 2 ));
		} else {
			this.determinarPosicion( this.posicionX(), (this.posicionY() - 2 ));
		}
	}

	/*true indica que quien lanzo el arma fue Algo42,
	 * false en caso contrario
	 */
	public boolean origenAlgo42() {
		return this.origenAlgo42;
	}

}
