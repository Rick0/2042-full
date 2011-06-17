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
	
	public void vivir(){
		
		this.intentarMover();
		if(origenAlgo42){
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
		} else {
			try {
				this.intentarChocar(plano.algo42);
			} catch (AlgoSeAtacaASiMismoError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AtaqueEntreNavesNoOperables e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(usada){
			try {
				plano.agregarArmaUsada(this);
			} catch (ArmaNoUsadaError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/* Devuelve true si el arma fue usada */
	public boolean estadoUsado() {
		return (this.usada);
	}

	/* Ataca a la nave que recibe por parametro. Devuelve true si el ataque fue efectivo, false en caso contrario */
	public boolean intentarChocar(Nave unaNave) throws AlgoSeAtacaASiMismoError, AtaqueEntreNavesNoOperables {
		
		if (( unaNave.esOperable ) && ( origenAlgo42 )) {
				throw new AlgoSeAtacaASiMismoError("Una nave algo42 no puede atacarse a si misma");
		}
		if ((unaNave.esOperable == false ) && ( origenAlgo42 == false )) {
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
	public void intentarMover() {

		float x,y;
		x = (this.plano).devolverAncho();
		y = (this.plano).devolverAltura();

		if (( (this.devolverPunto().getX()) < 0) || (( this.devolverPunto().getX() )> x )) {
			this.usada = true;
			try {
				this.plano.agregarArmaUsada( this );
			} catch (ArmaNoUsadaError error) {
				System.out.println("El Juego ha sufrido una falla irrecuperable");
			}
		}
		if (((this.devolverPunto().getY()) < 0) || ((this.devolverPunto().getY()) > y )) {
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

		Punto nuevaPosicion;
		if (this.origenAlgo42) {
			nuevaPosicion=new Punto(this.devolverPunto().getX(),(this.devolverPunto().getY()) + 2);
		} else {
			nuevaPosicion=new Punto(this.devolverPunto().getX(),(this.devolverPunto().getY()) - 2);
		}
		this.cambiarPosicion(nuevaPosicion);
	}

	/*true indica que quien lanzo el arma fue Algo42,
	 * false en caso contrario
	 */
	public boolean origenAlgo42() {
		return this.origenAlgo42;
	}

}
