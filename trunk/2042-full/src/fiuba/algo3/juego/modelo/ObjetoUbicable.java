package fiuba.algo3.juego.modelo;

import java.io.Serializable;

import fiuba.algo3.titiritero.ObjetoVivo;
import fiuba.algo3.titiritero.Posicionable;


/* Todos los objetos que se pueden ubicar en el escenario de juego 
 * heredan de esta clase: Todos deben tener una instancia de rectangulo
 * que los representa en el espacio y que les da un tamanio y una ubicacion
 */
public abstract class ObjetoUbicable implements ObjetoVivo, Posicionable, Serializable{

	private static final long serialVersionUID = 8478744294538118063L;
	Rectangulo rectangulo;
	Plano plano;


	/* Ubica el objeto en la posicion determinada, lo cual implica
	 * cambiar el punto que determina su posicion */
	public void cambiarPosicion(Punto punto) {
		rectangulo.cambiarPosicion(punto);
	}

	/* Devuelve la el punto que determina la posicion del objeto */
	public Punto devolverPunto() {
		return rectangulo.devolverPuntoIzquierdoInferior();
	}

	/* Toda instancia de esta clase contiene un rectangulo que representa su figura 
	 * Este metodo devuelve true si la posicion del objeto ubicable se superpone en 
	 * al menos un punto con el rectangulo que recibe por parametro
	 */
	public boolean coincidePosicionCon(Rectangulo figuraRectangulo) {
		return (this.rectangulo.coincideConPosicionDe( figuraRectangulo ));
	}

	/* Devuelve el plano en la cual se encuentra el objeto ubicable */
	public Plano devolverPlano() {
		return this.plano;
	}

	/* Toda instancia de objeto ubicable esta contenida en un plano. Para permitir la interaccion entre objetos
	 * contenidos en el mismo plano, toda instancia de ObjetoUbicable tiene una referencia al plano que lo contiene.
	 * Este metodo sirve para determinar el plano del objeto
	 */
	public void determinarPlano(Plano planoDelObjeto) {
		this.plano = planoDelObjeto;
	}
	
	/*Devuelve la altura del objeto ubicable*/
	public int devolverAltura() {
		return rectangulo.altura;
	}

	/*Devuelve el ancho del objeto ubicable*/
	public int devolverAncho() {
		return rectangulo.ancho;
	}

	/* Metodo implementado para la interface Posicionable */
	public int getY() {
		return (int)(plano.devolverAltura()-devolverPunto().getY()-this.rectangulo.devolverAltura());
	}

	/* Metodo implementado para la interface Posicionable */
	public int getX() {
		return (int)(devolverPunto().getX());
	}

}