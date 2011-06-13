package fiuba.algo3.juego.modelo;

import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class Explorador extends NaveNoOperable {

	double anguloActual;
	double puntosHaciaAbajo,radio,centroInicialX,centroInicialY;
	boolean inicializado;


	/* Inicializa una instancia de Bombardero */
	public Explorador(Punto punto, int radioDeGiro, Plano plano) throws SuperposicionNavesError, NaveDestruidaError {

		puntos = 50;
		energia = 30;
		inicializado = false;
		puntosHaciaAbajo = 0;
		anguloActual = 0;
		operable = false;
		rectangulo = new Rectangulo(5, 2, punto);
		destruida = false;
		fueraDeJuego = false;
		this.determinarPlano(plano);
		this.determinarRadio( radioDeGiro );

		if (this.seSuperponeConOtraNave()) {
			throw new SuperposicionNavesError("La posicion esta ocupada");
		}
		this.determinarPosicion(punto);
		plano.agregarNave(this);
	}
	
	
	@Override
	/* Si la nave esta en la posicion de algo42 lo choca */
	public void intentarAccionSobre(Algo42 algo42) {
		this.intentarChocar(algo42);
	}

	/* El explorador se mueve en circulos */
	public void mover() throws SuperposicionNavesError { 
		double pi = Math.PI;
		double  num=((1.000)/12);
		anguloActual = (anguloActual +num);
		puntosHaciaAbajo = ( puntosHaciaAbajo + 0.5);
		Punto nuevoPunto= new Punto((radio*( (Math.cos( pi * anguloActual)))) + centroInicialX,(centroInicialY - (radio * Math.sin( pi * anguloActual )) - puntosHaciaAbajo) );
		this.determinarPosicion(nuevoPunto );
		if ( this.seSuperponeConOtraNave() ) {
			throw new SuperposicionNavesError("La posicion ya esta ocupada.");
		}
		this.estaFueraDeArea();
	}

	/* Movimiento que se debe llevar a cabo si la funcion 'intentar movimiento' comprueba que el movimiento
	 * por defecto provocaria un choque entre naves del tipo no operable. Este metodo hace que el explorador
	 * retroceda en su angulo de giro y se mueva hacia arriba (en lugar de hacia abajo, que es el movimiento normal del explorador)
	 */
	public void moverAlternativo() throws SuperposicionNavesError {

		double pi = Math.PI;
		double  num=((1.000)/12);
		anguloActual = (anguloActual - num);
		puntosHaciaAbajo = (puntosHaciaAbajo - 0.5);
		Punto nuevoPunto= new Punto( (radio*( Math.cos( pi *anguloActual ) ) + centroInicialX),(centroInicialY - (radio * Math.sin( pi*anguloActual ) ) - puntosHaciaAbajo));
		this.determinarPosicion(nuevoPunto);
		if (this.seSuperponeConOtraNave() ) {
			throw new SuperposicionNavesError("La posicion ya esta ocupada.");
		}
		this.estaFueraDeArea();
	}

	/* Determina el radio de giro del explorador */
	private void determinarRadio(int r) {
		radio = r;
	}

	/* Dada la mayor complejidad del movimiento del explorador, este metodo ubicar
	 * no solo ubica a la nave en su lugar, sino que tambien guarda cual sera el centro de la 
	 * circunferencia (esta debera tener su centro trasladado a la ubicacion x,y)
	 */
	public void determinarPosicion(Punto punto) {

		this.rectangulo.determinarPosicion(punto);
		if (!inicializado) {
			centroInicialX = ( (this.devolverPunto().getX()) - radio);
			centroInicialY = (this.devolverPunto().getY());
			inicializado = true;
		}
	}

}
