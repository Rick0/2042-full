package juego;

import java.util.ArrayList;
import java.util.Iterator;


public class Nivel {
	/*Clase nivel. Se encarga de los puntos del nivel actual, los puntos acumulados y de pasar el nivel*/
	int puntosNivelActual;
	int puntosTotales;
	int numeroNivel;
	
	public Nivel(){
		numeroNivel = 1;
		puntosNivelActual = 0;
		puntosTotales = 0;
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
	
	public void sumarPuntajeTurno(ArrayList<NaveNoOperable> arrayList) {
	/*Recibe la lista de enemigos que elimino el algo42 durante un turno
	y los agrega tanto al puntaje total como al puntaje del nivel actual.
	Si el puntaje actual es mayor a 1000, avanza un nivel*/
		NaveNoOperable naveAuxiliar;
		Iterator<NaveNoOperable> nave = arrayList.iterator();
		while (nave.hasNext()) {
			naveAuxiliar = nave.next();
			if (naveAuxiliar.estadoActualDestruida() ) {
				puntosNivelActual = (puntosNivelActual + naveAuxiliar.devolverPuntosPorEliminacion() );
				puntosTotales = ( puntosTotales + naveAuxiliar.devolverPuntosPorEliminacion() );
			}
		}
	if ( puntosNivelActual >= 1000 ) { 
		this.avanzarNivel();
	}
	}

	public boolean avanzarNivel() {
		//"Avanza un nivel"
			if (puntosNivelActual >=1000) {
				puntosNivelActual = 0;
				numeroNivel = (numeroNivel + 1);
				return true;
			}
			return false;
		
	}
	
	public boolean actuarCon(ArrayList<NaveNoOperable> listaEliminados) {
	//La accion del nivel consiste en sumar su puntaje e intentar avanzar, si es que se puede"

		this.sumarPuntajeTurno( listaEliminados );
		return this.avanzarNivel();
	}
}