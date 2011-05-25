package juego;

public class Nivel {
	/*Clase nivel. Se encarga de los puntos del nivel actual, los puntos acumulados y de pasar el nivel*/
	int puntosNivelActual;
	int puntosTotales;
	int numeroNivel;
	
	public Nivel(){
		numeroNivel =1;
		puntosNivelActual =0;
		puntosTotales =0;
	}
	
	public int devolverNumeroNivel(){

		return numeroNivel;
	}
	
	public int devolverPuntosActuales(){
		return puntosNivelActual;
	}
	
	public int devolverPuntuacionTotal(){
		return puntosTotales;
	}
}