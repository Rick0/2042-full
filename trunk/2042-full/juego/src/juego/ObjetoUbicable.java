package juego;

public abstract class ObjetoUbicable {
	
	/*Todos los objetos que se pueden ubicar en el escenario de juego 
	 * heredan de esta clase: Todos deben tener una instancia de rectangulo
	 * que los representa en el espacio y que les da un tamanioo 
	 * y una ubicacion.*/
	
	Rectangulo rectangulo;
	Plano plano;
	
	public void determinarPosicion(double posx,double posy){
		/*Ubica el objeto en la posicion determinada*/
		rectangulo.determinarPosicion(posx,posy);
	}

	public double posicionX(){
		/*Devuelve la posicion en X del objeto*/
		return rectangulo.devolverPosicionX();
	}
	
	public double posicionY(){
		/*Devuelve la posicion en Y del objeto*/
		return rectangulo.devolverPosicionY();
	}
	
	public boolean coincidePosicionCon( Rectangulo figuraRectangulo) {
		
	/*Toda instancia de esta clase contiene un rectangulo que representa su figura 
	Este metodo devuelve true si la posicion del objeto ubicable se superpone en 
	al menos un punto con el rectangulo que recibe por parametro.*/
	
	return ( this.rectangulo.coincideConPosicionDe( figuraRectangulo ));
	}
	
	public void determinarPlano(Plano planoDelObjeto) {
		/*Toda instancia de objeto ubicable esta contenida en un plano. Para permitir la interaccion entre objetos
		contenidos en el mismo plano, toda instancia de ObjetoUbicable tiene una referencia al plano que lo contiene.
		Este metodo sirve para determinar el plano del objeto.*/

		this.plano = planoDelObjeto;
	}
}
