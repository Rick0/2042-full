package juego;

public abstract class Arma {
	
	float danio;
	boolean usada;
	boolean origenAlgo42;
	
	public void InicializarOrigenAlgo42( boolean verdaderoOFalso) {
	
	/*true indica que quien lanzó el arma fue una instancia de Algo42,
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
	
	public boolean intentarAtacar(Nave unaNave) throws AlgoSeAtacaASiMismo, AtaqueEntreNavesNoOperables {
		//Ataca a la nave que recibe por parametro. Devuelve true si el ataque fue efectivo, false en caso contrario
		if (( unaNave.operable ) && ( origenAlgo42 )) {
				throw new AlgoSeAtacaASiMismoError;
		}
		if ((unaNave.operable == false ) && ( origenAlgo42 == false )) {
				throw new AtaqueEntreNavesNoOperables;
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
		if (( this.devolverPosicionX() < 0) || ( this.devolverPosicionX() > x )) {
			usada = true; 
			this.plano.agregarArmaUsada( this );
		}
		if ((this.devolverPosicionY() < 0) || (this.devolverPosicionY() > y )) {
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
			this.determinarPosicion( this.devolverPosicionX, (this.devolverPosicionY + 2 ));
		} else {
			this.determinarPosicion( this.devolverPosicionX, (this.devolverPosicionY - 2 ));
		}
	}
	
}
