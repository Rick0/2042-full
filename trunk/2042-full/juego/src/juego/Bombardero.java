package juego;

import excepciones.*;
import juego.NaveNoOperable;

public class Bombardero extends NaveNoOperable {
	int haciaDer, haciaIzq;
	
	public Bombardero(int posicionX, int posicionY, Plano plano) throws SuperposicionNavesError{
		//"Inicializa una instancia de Bombardero"

		this.puntos = 30;
		energia = 50;
		haciaDer = 0;
		haciaIzq = 0;
		operable = false;
		rectangulo = new Rectangulo(7, 7, posicionX, posicionY);
		destruida = false;
		fueraDeJuego = false;
		this.determinarPlano(plano);
		if (this.seSuperponeConOtraNave()) {
			throw new SuperposicionNavesError("La posición esta ocupada");
		}
		plano.agregarNave(this);
	}
	
	public Item dejarArma() throws ItemNoDisponibleError {
		//Crea una instancia de ArmaAbandonada y la devuelve. 
		if (!this.destruida) {
			throw new ItemNoDisponibleError("El bombardero aun no esta destruido, no puede dejar armas");
		}
		Item item = new ArmaAbandonada(this.posicionX(), this.posicionY() );
		return item;
	}
	
	@Override
	public void modificarEnergia( int cantidad ) {
		//Recibe una cierta cantidad de puntos y los suma a la energía de la nave. Ademas,
		//si la energia es menor a 0, el bombardero deja un paquete de armas en el escenario de juego"
		
		Item itemDejado;

		energia = (energia + cantidad);
		if (energia <= 0) {
			try {
				this.destruirse();
			} catch (Exception error) { 
				//esto no puede suceder
			}
			try {
				itemDejado = this.dejarArma();
			} catch (ItemNoDisponibleError error) { 
				//esto no puede suceder
				return;
			}
			try {
				plano.agregarItem( itemDejado );
			} catch (ItemUsadoError error) {
				itemDejado.noUsado();
			}
		}
	}
	@Override
	public void IntentarAccionSobre(Algo42 algo42) {
		// TODO Auto-generated method stub
		
	}
	public void mover() throws SuperposicionNavesError {
		/*El bombardero se mueve en Zig Zag; Primero se mueve 0.5 puntos hacia abajo y hacia la derecha.
		Cuando ya bajo 10 puntos, empieza a ir 0.5 puntos hacia abajo y hacia la izquierda, hasta bajar
		10 puntos mas, y asi sucesivamente.*/

			if ( haciaDer <=10 ) {
				this.determinarPosicion( this.posicionX() + 1 ,this.posicionY() - 1);
				haciaDer = (haciaDer + 1);
			} else {
				if (haciaIzq  <= 10) {
					this.determinarPosicion(this.posicionX() - 1,this.posicionY() -1);
					haciaIzq = (haciaIzq +1);
				} else {
					haciaIzq = 0;
					haciaDer = 0;
				}
			}
			if ( this.seSuperponeConOtraNave() ) {
				throw new SuperposicionNavesError("La posicion ya esta ocupada.");
			}

			this.estaFueraDeArea();
		}

}
