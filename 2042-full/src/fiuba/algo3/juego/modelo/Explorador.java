package fiuba.algo3.juego.modelo;

import java.io.Serializable;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class Explorador extends NaveNoOperable implements Serializable{

	private static final long serialVersionUID = -6855917678225418172L;
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
		esOperable = false;
		rectangulo = new Rectangulo(50, 45, punto);
		estaDestruida = false;
		fueraDelPlano = false;
		this.determinarPlano(plano);
		this.determinarRadio(radioDeGiro);

		if (this.seSuperponeConOtraNave()) {
			throw new SuperposicionNavesError("La posicion esta ocupada");
		}
		this.cambiarPosicion(punto);
		plano.agregarNave(this);
		plano.agregarObjetoNuevo(this);
	}
	
	
	@Override
	/* El explorador no tiene armas */
	public void disparar() {	}

	/* El explorador se mueve en circulos */
	public void mover() throws SuperposicionNavesError { 
		double pi = Math.PI;
		double  num=((1.000)/12);
		anguloActual = (anguloActual +num);
		puntosHaciaAbajo = ( puntosHaciaAbajo + 0.5);
		Punto nuevoPunto= new Punto((radio*( (Math.cos( pi * anguloActual)))) + centroInicialX,(centroInicialY - (radio * Math.sin( pi * anguloActual )) - puntosHaciaAbajo) );
		this.cambiarPosicion(nuevoPunto );
		if ( this.seSuperponeConOtraNave() ) {
			throw new SuperposicionNavesError("La posicion ya esta ocupada.");
		}
		this.estaFueraDelPlano();
	}

	/* Movimiento que se debe llevar a cabo si la funcion 'intentar movimiento' comprueba que el movimiento
	 * por defecto provocaria un choque entre naves del tipo no operable.
	 */
	
	public void moverAlternativo() throws SuperposicionNavesError {

		Punto posicion= this.devolverPunto();
		Punto nuevoPunto= new Punto(posicion.getX()+2,posicion.getY());
		this.cambiarPosicion(nuevoPunto);
		if (this.seSuperponeConOtraNave() ) {
			nuevoPunto= new Punto(posicion.getX()-2,posicion.getY());
			this.cambiarPosicion(nuevoPunto);
			if (this.seSuperponeConOtraNave() ){
				throw new SuperposicionNavesError("La posicion ya esta ocupada.");
			}
		}
		this.estaFueraDelPlano();
	}

	/* Determina el radio de giro del explorador */
	private void determinarRadio(int r) {
		radio = r;
	}

	/* Dada la mayor complejidad del movimiento del explorador, este metodo ubicar
	 * no solo ubica a la nave en su lugar, sino que tambien guarda cual sera el centro de la 
	 * circunferencia (esta debera tener su centro trasladado a la ubicacion x,y)
	 */
	public void cambiarPosicion(Punto punto) {

		this.rectangulo.cambiarPosicion(punto);
		if (!inicializado) {
			centroInicialX = ( (this.devolverPunto().getX()) - radio);
			centroInicialY = (this.devolverPunto().getY());
			inicializado = true;
		}
	}

}
