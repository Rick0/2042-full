package juego;

public class Algo42 extends Nave {
/*El algo42 es la nave principal del juego,,
Es la unica nave que puede ser manejada por el jugador.*/
	
	int torpedos;
	int cohetes;
	
	Algo42(int posicion_X,int posicion_Y,Plano planoJuego){
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
		/*Lanzar excepcion*/
		}
	}
}
