package juego;

public abstract class ObjetoUbicable {
	
	/*Todos los objetos que se pueden ubicar en el escenario de juego 
	 * heredan de esta clase: Todos deben tener una instancia de rectangulo
	 * que los representa en el espacio y que les da un tamanioo 
	 * y una ubicacion.*/
	
	Rectangulo rectangulo;
	Plano plano;
	
	public void determinarPosicion(int posicionX,int posicionY){
		/*Ubica el objeto en la posicion determinada*/
		rectangulo.determinarPosicion(posicionX,posicionY);
	}

}
