package juego;

import excepciones.AreaInvalidaError;

public class Algo42 extends Nave {
/*El algo42 es la nave principal del juego,,
Es la unica nave que puede ser manejada por el jugador.*/
	
	int torpedos;
	int cohetes;
	
	public Algo42(int posicion_X,int posicion_Y,Plano planoJuego) throws AreaInvalidaError{
	/*Crea una nueva instancia de algo42, con ubicación(posicion_X,posicion_Y),
	 * en el plano de juego que recibe por parametro
	 */
			
	plano= planoJuego;
	energia= 100;
	torpedos=0;
	cohetes=0;
	destruida=false;
	operable=true;
	if (( (posicion_X<(planoJuego.ancho)) & (posicion_Y<(planoJuego.altura)) )  & ((posicion_X>=0) & (posicion_Y>=0) ) ){
		planoJuego.introducirAlgo(this);
		rectangulo= new Rectangulo (5,3,posicion_X,posicion_Y);
	}
	else{
		throw new AreaInvalidaError("La nave debe ser creada en una posicion valida dentro del area de juego.");
		}
	}
	
	public void ModificarEnergia (int puntosModificar) {
	
	/*Recibe una cierta cantidad de puntos y los suma a la energia de la nave.*/
		
		energia = energia+puntosModificar;
		if ( energia <= 0 ) {
			destruida = true;
		}
	}
	
	public void moverAbajo () throws AreaInvalidaError {
		
		/*La nave Algo42 se mueve un lugar hacia abajo.*/
			
		if((rectangulo.devolverPosicionY())<=0){
			throw new AreaInvalidaError("La nave ya no puede moverse mas hacia abajo");
		}
		this.determinarPosicion((rectangulo.devolverPosicionX()),(rectangulo.devolverPosicionY()) - 1);
	}
	
	public void moverArriba () throws AreaInvalidaError {
		
		/*La nave Algo42 se mueve un lugar hacia arriba.*/
			
		if((rectangulo.devolverPosicionY() + (rectangulo.devolverAltura()))>plano.devolverAltura()){
			throw new AreaInvalidaError("La nave ya no puede moverse mas hacia arriba");
		}
		this.determinarPosicion((rectangulo.devolverPosicionX()),(rectangulo.devolverPosicionY()) + 1);
	}
	
	public void moverDerecha () throws AreaInvalidaError {
		
		/*La nave Algo42 se mueve un lugar hacia la derecha.*/
			
		if((rectangulo.devolverPosicionX() + (rectangulo.devolverAncho()))>plano.devolverAncho()){
			throw new AreaInvalidaError("La nave ya no puede moverse mas hacia la derecha");
		}
		this.determinarPosicion((rectangulo.devolverPosicionX())+1,(rectangulo.devolverPosicionY()));
	}

	public void moverIzquierda () throws AreaInvalidaError {
		
		/*La nave Algo42 se mueve un lugar hacia la izquierda.*/
			
		if((rectangulo.devolverPosicionX())<=0){
			throw new AreaInvalidaError("La nave ya no puede moverse mas hacia la izquierda");
		}
		this.determinarPosicion((rectangulo.devolverPosicionX())-1,(rectangulo.devolverPosicionY()));
	}

	
	public void modificarEnergia(int danio){
	}
}
