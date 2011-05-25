package juego;
import excepciones.*;

public abstract class Arma extends ObjetoUbicable {
	
	int danio;
	boolean usada;
	boolean origenAlgo42;
	
	public void InicializarOrigenAlgo42( boolean verdaderoOFalso) {
	
	/*true indica que quien lanzo el arma fue una instancia de Algo42,
	false en caso contrario.*/
		this.origenAlgo42 = verdaderoOFalso;
	}
	
	public void actuar() {
		/*actuar
		
		self intentarMovimiento .
		(origenAlgo42 )ifTrue: [
			plano listaNaves do: [:cadaNave|
					self intentarAtacar: cadaNave .
					].
			] ifFalse: [
					self intentarAtacar: (plano algo42 ).].
		(usada)ifTrue:[plano agregarArmaUsada: self.].*/
		this.intentarMovimiento();
		/*recorrer la lista*/
		
		if ( this.usada ) {
			this.plano.agregarArmaUsada(this);
		}
	}
	
	public boolean estadoUsado() {
	//Devuelve true si el arma fue usada

		return this.usada;
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
	
	public void intentarMovimiento() {
		
		//"El arma cambia su posicion en el plano.
		//Si el arma sale de los limites del plano, cambia su estado a usada"
		float x,y;
		x = this.plano.ancho;
		y = this.plano.altura;
		if (( this.posicionX() < 0) || ( this.posicionX() > x )) {
			usada = true; 
			this.plano.agregarArmaUsada( this );
		}
		if ((this.posicionY() < 0) || (this.posicionY() > y )) {
			usada = true;
			this.plano.agregarArmaUsada( this );
		}
		if (! this.usada ) {
			this.mover();
		}
		
	}
	
	public void mover() {
		
		//El arma cambia su posicion
		if ( this.origenAlgo42 ) {
			this.determinarPosicion( this.posicionX(), (this.posicionY() + 2 ));
		} else {
			this.determinarPosicion( this.posicionX(), (this.posicionY() - 2 ));
		}
	}
	
}
