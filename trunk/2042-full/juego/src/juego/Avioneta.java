package juego;

import excepciones.*;

public class Avioneta extends NaveNoOperable {
	public int puntosAtras, puntosAdelante;
	
	public Avioneta(int posicionX, int posicionY, Plano plano) throws SuperposicionNavesError{
		//"Inicializa una instancia de Avioneta"

		this.puntos = 20;
		energia = 20;
		puntosAdelante= 0;
		puntosAtras = 0;
		operable = false;
		rectangulo = new Rectangulo(6, 3, posicionX, posicionY);
		destruida = false;
		fueraDeJuego = false;
		this.determinarPlano(plano);
		if (this.seSuperponeConOtraNave()) {
			throw new SuperposicionNavesError("La posición esta ocupada");
		}
		plano.agregarNave(this);
	}
	
	public void dispararLaser() {
		new Laser(this.posicionX(), this.posicionY(), false, this.plano);
	}
	
	public void mover() throws SuperposicionNavesError {
		/*Metodo para el movimiento de la avioneta. Se mueve 60 puntos hacia adelante, luego 60 hacia atras,
		y asi sucesivamente. Es la nave mas rapida, por lo cual se mueve dos lugares cada vez que
		este metodo es invocado"*/
		
		if ( (this.puntosAdelante <= 60) ) {
				this.puntosAdelante = (this.puntosAdelante + 2);
				this.determinarPosicion(this.posicionX(), (this.posicionY() - 2) );
		} else {
				this.puntosAtras = (this.puntosAtras + 2);
				this.determinarPosicion( this.posicionX() , (this.posicionY() + 2) );
				if (this.puntosAtras >= 60) {
					this.puntosAtras = 0;
					this.puntosAdelante =0;
				}
		}
		if (this.seSuperponeConOtraNave()) {
			throw new SuperposicionNavesError("La posicion ya esta ocupada.");
		}
		this.estaFueraDeArea();
	}
	
	public void moverAternativo() throws SuperposicionNavesError {
		/*Este metodo debe ser llamado cuando, por alguna razon, el movimiento que realiza
		la nave por defecto no puede ser llevado a cabo.*/
			
			if (this.puntosAdelante < 62) {
					this.puntosAtras = 0;
					this.puntosAdelante = 0; //Hago que empiece a moverse hacia adelante desde 0,
					//pero antes lo hago moverse hacia atras, si es que puedo.
					this.determinarPosicion(this.posicionX(), (this.posicionY() + 2 ) );
			}else {
					this.puntosAtras = 0;
					this.determinarPosicion( this.posicionX(),(this.posicionY() - 2));
			}
			if ( this.seSuperponeConOtraNave() ) {
				throw new SuperposicionNavesError("La posicion ya esta ocupada");
			}
			this.estaFueraDeArea();
	}
	
	@Override
	void IntentarAccionSobre(Algo42 algo42) {
		//Si la nave esta en la posicion de algo42 lo choca. Lanza
		//un laser y lo agrega a la lista de Armas."
			
			this.intentarChocar(algo42);
			this.dispararLaser();
	}

}
