package fiuba.algo3.juego.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;


/* Clase nivel. Se encarga de los puntos del nivel actual, los puntos acumulados y de pasar el nivel */
public class Nivel implements Serializable{

	private static final long serialVersionUID = 47192090769295819L;
	int puntosNivelActual;
	int puntosTotales;
	int numeroNivel;


	public Nivel() {
		numeroNivel = 1;
		puntosNivelActual = 0;
		puntosTotales = 0;
	}

	public int devolverNumeroNivel() {
		return numeroNivel;
	}

	public int devolverPuntosActuales() {
		return puntosNivelActual;
	}

	public int devolverPuntuacionTotal() {
		return puntosTotales;
	}

	/* Recibe la lista de enemigos que elimino el algo42 durante un turno
	 * y los agrega tanto al puntaje total como al puntaje del nivel actual.
	 * Si el puntaje actual es mayor a 1000, avanza un nivel
	 */
	public void sumarPuntajeTurno(ArrayList<NaveNoOperable> arrayList) {

		NaveNoOperable naveAuxiliar;
		Iterator<NaveNoOperable> nave = arrayList.iterator();

		while (nave.hasNext()) {
			naveAuxiliar = nave.next();
			if (naveAuxiliar.estadoActualDestruida() ) {
				puntosNivelActual = ( puntosNivelActual + naveAuxiliar.devolverPuntosPorEliminacion() );
				puntosTotales = ( puntosTotales + naveAuxiliar.devolverPuntosPorEliminacion() );
			}
		}
	}

	/* Avanza un nivel si se tiene mas de 1000 puntos actuales
	 * Retrocede un nivel si se tiene menos de -1000 puntos actuales
	 */
	public void avanzarNivel() {

		if (puntosNivelActual >= 1000) {
			puntosNivelActual = 0;
			numeroNivel++;
		}
		else if (puntosNivelActual <= -1000) {
			puntosNivelActual = 0;
			numeroNivel--;
		}
	}

	/* La accion del nivel consiste en sumar su puntaje e intentar avanzar, si es que se puede */
	public void actuarCon(ArrayList<NaveNoOperable> listaEliminados) {
		this.sumarPuntajeTurno( listaEliminados );
		this.avanzarNivel();
	}

}
