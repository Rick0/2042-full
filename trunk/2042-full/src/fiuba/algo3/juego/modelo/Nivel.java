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
	final int puntosParaUnNivel = 1500;


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

	/* Recibe la lista de enemigos que elimino Algo42 durante un turno, y agrega puntaje */
	public void sumarPuntajeNaves(ArrayList<NaveNoOperable> listaNavesEliminadas) {

		NaveNoOperable unaNave;
		Iterator<NaveNoOperable> iteradorNave = listaNavesEliminadas.iterator();

		while (iteradorNave.hasNext()) {
			unaNave = iteradorNave.next();
			if (unaNave.estadoActualDestruida() ) {
				puntosNivelActual = ( puntosNivelActual + unaNave.devolverPuntosPorEliminacion() );
				puntosTotales = ( puntosTotales + unaNave.devolverPuntosPorEliminacion() );
			}
		}
	}

	/* Recibe la lista de aliados que se eliminaron durante un turno, y agrega puntaje */
	public void sumarPuntajeNavesAliadas(ArrayList<NaveNoOperable> listaNavesAliadasEliminadas) {

		NaveNoOperable unaNave;
		Iterator<NaveNoOperable> iteradorNavesAliadas = listaNavesAliadasEliminadas.iterator();

		while (iteradorNavesAliadas.hasNext()) {
			unaNave = iteradorNavesAliadas.next();
			if (unaNave.estadoActualDestruida() ) {
				puntosNivelActual = ( puntosNivelActual + unaNave.devolverPuntosPorEliminacion() );
				puntosTotales = ( puntosTotales + unaNave.devolverPuntosPorEliminacion() );
			}
		}
	}
	
	/* Recibe la lista de items que eso Algo42 durante un turno, y agrega puntaje */
	public void sumarPuntajeItems(ArrayList<Item> listaItemsUsados) {

		Item unItem;
		Iterator<Item> iteradorItem = listaItemsUsados.iterator();

		while (iteradorItem.hasNext()) {
			unItem = iteradorItem.next();
			if (unItem.fueUsado() ) {
				puntosNivelActual = ( puntosNivelActual + unItem.devolverPuntosPorUtilizacion() );
				puntosTotales = ( puntosTotales + unItem.devolverPuntosPorUtilizacion() );
			}
		}
	}

	/* Avanza un nivel si se tiene mas de 1500 puntos actuales
	 * Retrocede un nivel si se tiene menos de -1500 puntos actuales
	 */
	public void avanzarNivel() {

		if (puntosNivelActual >= this.puntosParaUnNivel) {
			puntosNivelActual = 0;
			numeroNivel++;
		}
		else if (puntosNivelActual < (this.puntosParaUnNivel * -1)) {
			puntosNivelActual = 0;
			numeroNivel--;
		}
	}

	/* La accion del nivel consiste en sumar su puntaje e intentar avanzar, si es que se puede */
	public void actuarCon(ArrayList<NaveNoOperable> listaNavesEliminadas, ArrayList<NaveNoOperable> listaNavesAliadasEliminadas, ArrayList<Item> listaItemsUsados) {
		this.sumarPuntajeNaves(listaNavesEliminadas);
		this.sumarPuntajeNavesAliadas(listaNavesAliadasEliminadas);
		this.sumarPuntajeItems(listaItemsUsados);
		this.avanzarNivel();
	}

}
