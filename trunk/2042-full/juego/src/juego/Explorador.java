package juego;

import excepciones.*;

public class Explorador extends NaveNoOperable {

	float anguloActual;
	int puntosHaciaAbajo,radio,centroInicialX,centroInicialY;
	boolean inicializado;
	
	public Explorador(int posicionX, int posicionY, int radioDeGiro, Plano plano) throws SuperposicionNavesError {
		//"Inicializa una instancia de Bombardero"

		puntos = 50;
		energia = 30;
		inicializado = false;
		puntosHaciaAbajo = 0;
		anguloActual = 0;
		operable = false;
		rectangulo = new Rectangulo(5, 2, posicionX, posicionY);
		destruida = false;
		fueraDeJuego = false;
		this.determinarPlano(plano);
		this.determinarRadio( radioDeGiro );
		this.determinarPosicion( posicionX,posicionY);
		if (this.seSuperponeConOtraNave()) {
			throw new SuperposicionNavesError("La posición esta ocupada");
		}
		plano.agregarNave(this);
	}
	
	
	@Override
	public void IntentarAccionSobre(Algo42 algo42) {
		//Si la nave esta en la posicion de algo42 lo choca.
			this.intentarChocar(algo42);

	}
	
	public void mover() throws SuperposicionNavesError { 
	//El explorador se mueve en circulos.
		double pi = Math.PI;
		anguloActual = (anguloActual +(1/12));
		puntosHaciaAbajo = (int)( puntosHaciaAbajo + 0.5);
		this.determinarPosicion( (int)(radio*( Math.cos( pi * anguloActual) + centroInicialX )), (int)(centroInicialY - (radio * Math.sin( pi * anguloActual )) - puntosHaciaAbajo) );
		if ( this.seSuperponeConOtraNave() ) {
			throw new SuperposicionNavesError("La posicion ya esta ocupada.");
		}
		this.estaFueraDeArea();
	}
	public void moverAlternativo() throws SuperposicionNavesError {
		
		/*Movimiento que se debe llevar a cabo si la funcion intentar movimiento comprueba que el movimiento
		por defecto provocaria un choque entre naves del tipo no operable. Este metodo hace que el explorador
		retroceda en su angulo de giro y se mueva hacia arriba (en lugar de hacia abajo, que es el movimiento normal del explorador.)*/
			double pi = Math.PI;
			anguloActual = (int)(anguloActual - (1/12));
			puntosHaciaAbajo = (int)(puntosHaciaAbajo - 0.5);
			this.determinarPosicion((int) (radio*( Math.cos( pi *anguloActual ) ) + centroInicialX),(int) (centroInicialY - (radio * Math.sin( pi*anguloActual ) ) - puntosHaciaAbajo) );
			if (this.seSuperponeConOtraNave() ) {
				throw new SuperposicionNavesError("La posicion ya esta ocupada.");
			}
			this.estaFueraDeArea();
	}
	
	private void determinarRadio(int r) {
		//Determina el radio de giro del explorador.

			radio = r;
	}
	
	public void determinarPosicion(int posX, int posY) {
	/*Dada la mayor complejidad del movimiento del explorador, este metodo ubicar
	no solo ubica a la nave en su lugar, sino que tambien guarda cual sera el centro de la 
	circunferencia (esta debera tener su centro trasladado a la ubicacion x,y)*/

	this.rectangulo.determinarPosicion(posX, posY);
	if (!inicializado) {
		centroInicialX = ( posX - radio);
		centroInicialY = posY;
		inicializado = true;
	}
	}
}
