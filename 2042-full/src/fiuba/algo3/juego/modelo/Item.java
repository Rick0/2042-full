package fiuba.algo3.juego.modelo;

import java.io.Serializable;
import java.util.Random;


/* Clase abstracta de la cual heredaran los items del juego */
public abstract class Item extends ObjetoUbicable implements Serializable {

	private static final long serialVersionUID = -4828893549103315270L;
	boolean fueUsado;
	boolean sinTiempoDeVida;
	int tiempoDeVida = 555;
	int puntos;
	int cantAMover;
	int movDelay;
	int movDelayCont;

	
	public Item() {
		Random generadorRandom = new Random();
		cantAMover = generadorRandom.nextInt(3) + 1;
		movDelay = generadorRandom.nextInt(5) + 2;
		movDelayCont = 0;
		
		fueUsado = false;
		sinTiempoDeVida = false;
	}
	
	/* Recibe una nave Algo42 (la nave manejada por el usuario)
	 * e intenta aplicar el efecto del item sobre ella.
	 * Para eso, el Algo42 debe estar en la misma posicion que el item
	 */
	public abstract void intentarChocar(Algo42 algo42);

	/* Cada item debe realizar algun efecto en algo42,
	 * dependiendo de que tipo de item sea */
	public void vivir() {

		if (!fueUsado) {
			this.pasaUnTiempo();
			this.intentarChocar(plano.devolverAlgo42());
			this.mover();
		}
	}

	/* Devuelve true si el item ya fue usado, false en caso contrario */
	boolean fueUsado() {
		return fueUsado;
	}
	
	/* Devuelve true si el item ya no tiene mas tiempo de vida, false en caso contrario */
	boolean sinTiempoDeVida() {
		return sinTiempoDeVida;
	}

	/* Modifica el estado a no usado */
	public void noUsado() {
		fueUsado = false;
	}
	
	/* El item choca con el avion Algo42 */
	public void chocarCon(Algo42 algo42) {
		this.fueUsado = true;
		plano.agregarItemUsado(this);
	}

	/* Pasa un tiempo, y el tiempo de vida del item decrementa
	 * Cuando llega a 0, este desaparece del plano
	 */
	public void pasaUnTiempo() {

		if (tiempoDeVida <= 0) {
			this.sinTiempoDeVida = true;
			plano.agregarItemUsado(this);
		}
		else
			tiempoDeVida--;
		
		if (this.movDelayCont < this.movDelay)
			this.movDelayCont++;
	}

	public int devolverPuntosPorUtilizacion() {
		return this.puntos;
	}
	
	private void mover() {
		
		if (this.movDelayCont >= this.movDelay) {
			Punto nuevoPunto = new Punto(this.devolverPunto().getX(),this.devolverPunto().getY() - this.cantAMover);
			this.cambiarPosicion( nuevoPunto );
			this.movDelayCont = 0;
		}
	}

}