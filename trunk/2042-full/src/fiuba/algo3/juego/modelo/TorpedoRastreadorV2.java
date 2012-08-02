package fiuba.algo3.juego.modelo;

import java.io.Serializable;
import java.util.Random;
import fiuba.algo3.juego.modelo.excepciones.ArmaUsadaError;


public class TorpedoRastreadorV2 extends Arma implements Serializable{

	private static final long serialVersionUID = 4568264682174031546L;
	private Nave naveRastreada;
	private int tiempoDeVida;
	private int cantidadAMover;


	public TorpedoRastreadorV2 (Punto punto, boolean origenAlgo, Plano plano) {

		this.fueUsado = false;
		this.rectangulo = new Rectangulo(42, 19, punto);
		this.inicializarOrigenAlgo42(origenAlgo);
		this.determinarPlano(plano);
		Random generadorRandom = new Random();
		int i = generadorRandom.nextInt(7) + 2;
		this.cantidadAMover = i;
		this.danio = (-50)/i;
		this.determinarNaveRastreada();
		
		if (this.origenAlgo42)
			this.tiempoDeVida = 444;
		else
			this.tiempoDeVida = 333;

		try {
			this.plano.agregarArma(this);
			this.plano.agregarObjetoNuevo(this);
		} catch (ArmaUsadaError e) {
			System.out.println("Se produjo un error irrecuperable");
		}
	}

	/* Guarda la nave a la cual rasteara el torpedo. 
	 * Cuando se utilice el metodo mover, el torpedo se movera hacia esa nave
	 */
	public void determinarNaveRastreada() {
	
		Random generadorRandom = new Random();
		int i;
		if (this.origenAlgo42 == true) {
		
			int n = this.plano.devolverCantidadNaves();
			if (n == 1)
				i = 0;
			else
				i = generadorRandom.nextInt(n);
			naveRastreada = this.plano.devolverListaNaves().get(i);
		}
		else {
		
			int n = this.plano.devolverListaNavesAI().size();
			if (n == 0)
				this.naveRastreada = this.plano.devolverAlgo42();
			else {
				if (n == 1)
					i = 0;
				else
					i = generadorRandom.nextInt(n);
				this.naveRastreada = this.plano.devolverListaNavesAI().get(i);
			}
		}
	}

	/* El torpedo rastreador se mueve de tal forma que se acerque a la nave que persigue */ 
	public void mover() {

		if (this.naveRastreada.devolverPunto().getX() < this.devolverPunto().getX() ) {
			Punto nuevaPosicionX = new Punto(this.devolverPunto().getX() - cantidadAMover, this.devolverPunto().getY());
			this.cambiarPosicion( nuevaPosicionX );
		}
		else if ( (this.naveRastreada.devolverPunto().getX() > this.devolverPunto().getX() )) {	
			Punto nuevaPosicionX = new Punto(this.devolverPunto().getX() + cantidadAMover, this.devolverPunto().getY());
			this.cambiarPosicion( nuevaPosicionX );
		}

		if ( (this.naveRastreada.devolverPunto().getY() < this.devolverPunto().getY() )) {
			Punto nuevaPosicionY = new Punto(this.devolverPunto().getX(), this.devolverPunto().getY() - cantidadAMover);
			this.cambiarPosicion( nuevaPosicionY );
		}
		else if ( (this.naveRastreada.devolverPunto().getY() > this.devolverPunto().getY() )) {
			Punto nuevaPosicionY = new Punto(this.devolverPunto().getX(), this.devolverPunto().getY() + cantidadAMover);
			this.cambiarPosicion( nuevaPosicionY );
		}
	}

	/* Pasa un tiempo, y el tiempo de vida del torpedo decrementa
	 * Cuando llega a 0, este desaparece del plano
	 */
	public void pasaUnTiempo() {

		if (tiempoDeVida <= 0) {
			this.fueUsado = true;
			this.plano.listaArmasUsadas.add(this);
		}
		else {
			tiempoDeVida--;
		}
	}

	@Override
	public void vivir() {

		if (!fueUsado) {

			this.pasaUnTiempo();

			if (origenAlgo42) {
				
				if (naveRastreada.estadoActualDestruida() || ((NaveNoOperable)naveRastreada).fueraDelPlano()) {

					if (this.plano.hayNavesEnemigas()) {
						this.determinarNaveRastreada();
						this.intentarMover();
					}
					else {
						Punto nuevaPosicion = new Punto(this.devolverPunto().getX(), this.devolverPunto().getY() + (cantidadAMover/2));	
						this.cambiarPosicion(nuevaPosicion);
					}
				}
				else
					this.intentarMover();
			}
			else {
				
				boolean naveRastreadaEsta = this.plano.devolverListaNavesAI().contains(naveRastreada);
				
				if (!naveRastreadaEsta)
					this.determinarNaveRastreada();
				this.intentarMover();
			}

			this.intentarChocar();
		}
	}

	public int devolverCantidadAMover() {
		return cantidadAMover;
	}

}
