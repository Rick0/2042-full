package fiuba.algo3.juego.modelo;

/* Todos los objetos que se pueden ubicar en el escenario de juego 
 * heredan de esta clase: Todos deben tener una instancia de rectangulo
 * que los representa en el espacio y que les da un tamanio y una ubicacion
 */
public abstract class ObjetoUbicable {

	Rectangulo rectangulo;
	Plano plano;
	
	/* Ubica el objeto en la posicion determinada, lo cual implica
	 * cambiar el punto que determina su posicion */
	public void determinarPosicion(Punto punto) {
		rectangulo.determinarPosicion(punto);
	}

	/* Devuelve la el punto que determina la posicion del objeto */
	public Punto devolverPunto() {
		return rectangulo.devolverPuntoIzquierdoInferior();
	}

	/* Toda instancia de esta clase contiene un rectangulo que representa su figura 
	 * Este metodo devuelve true si la posicion del objeto ubicable se superpone en 
	 * al menos un punto con el rectangulo que recibe por parametro
	 */
	public boolean coincidePosicionCon( Rectangulo figuraRectangulo) {
		return (this.rectangulo.coincideConPosicionDe( figuraRectangulo ));
	}

	/* Toda instancia de objeto ubicable esta contenida en un plano. Para permitir la interaccion entre objetos
	 * contenidos en el mismo plano, toda instancia de ObjetoUbicable tiene una referencia al plano que lo contiene.
	 * Este metodo sirve para determinar el plano del objeto
	 */
	public void determinarPlano(Plano planoDelObjeto) {
		this.plano = planoDelObjeto;
	}

}