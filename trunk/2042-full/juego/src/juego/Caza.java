package juego;


import excepciones.*;

public class Caza extends NaveNoOperable {
	int numero, pasosAvanzados;
	
	public Caza(int posicionX, int posicionY, Plano plano) throws SuperposicionNavesError{
		//"Inicializa una instancia de Caza"

		numero = 0;
		puntos = 50;
		energia = 10;
		pasosAvanzados = 0;
		operable = false;
		rectangulo = new Rectangulo(4, 4, posicionX, posicionY);
		destruida = false;
		fueraDeJuego = false;
		this.determinarPlano(plano);
		if (this.seSuperponeConOtraNave()) {
			throw new SuperposicionNavesError("La posición esta ocupada");
		}
		plano.agregarNave(this);
	} 
	
	
	@Override
	public void IntentarAccionSobre(Algo42 algo42) {
		//Si la nave esta en la posicion de algo42 lo choca. Lanza
		//un torpedo y lo agrega a la lista de Armas."
			this.intentarChocar(algo42);
			new Laser(this.posicionX(),this.posicionY(), false, this.plano);
	}
	
	public boolean avanzo3Pasos() {
	//Devuelve true si la nave avanzo 3 pasos o mas.
	if (pasosAvanzados >= 3) {
		return true;
	}
	return false;
	}
	
	public Item dejarTanque() throws ItemNoDisponibleError {
		//Crea una instancia de TanqueEnergia y la devuelve.
		Item itemDejado;
		if (! this.destruida){
			throw new ItemNoDisponibleError("El caza aun no esta destruido, no puede dejar armas.");
		}
		itemDejado = new TanqueEnergia(this.posicionX(), this.posicionY());
		return itemDejado;
	}
	
	public void dispararTorpedo() {
	//Crea una instancia de TorpedoSimple en la posicion actual de la nave
	
		new TorpedoSimple( this.posicionX(),this.posicionY(), false, this.plano);
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
				itemDejado = this.dejarTanque();
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
	
	public void mover() throws SuperposicionNavesError {
		//Los cazas se mueve hacia abajo.
			this.determinarPosicion(this.posicionX(),this.posicionY() - 1);
			pasosAvanzados = (pasosAvanzados + 1); 
			if (this.seSuperponeConOtraNave() ) {
				throw new SuperposicionNavesError("La posicion ya esta ocupada.");
			}
			this.estaFueraDeArea();
	}
	
	public void moverAlternativo() throws SuperposicionNavesError {
		/*"Movimiento que se debe llevar a cabo si la funcion intentar movimiento comprueba que el movimiento
	por defecto provocaria un choque entre naves del mismo tipo. Intenta moverse una
	posicion hacia atras. Si no puede, se queda quieto.*/
			this.determinarPosicion(this.posicionX(),this.posicionY() + 1);
			pasosAvanzados = (pasosAvanzados + 1); 
			if (this.seSuperponeConOtraNave() ) {
				throw new SuperposicionNavesError("La posicion ya esta ocupada.");
			}
			this.estaFueraDeArea();
	}
}
