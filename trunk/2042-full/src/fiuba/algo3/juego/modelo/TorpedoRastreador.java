package fiuba.algo3.juego.modelo;

import java.io.Serializable;
import fiuba.algo3.juego.modelo.excepciones.ArmaUsadaError;


public class TorpedoRastreador extends Arma implements Serializable{

	private static final long serialVersionUID = 2021359934861160328L;
	private Nave naveRastreada;
	private int tiempoDeVida;
	static int cantidadAMover = 2;


	public TorpedoRastreador (Punto punto, boolean origenAlgo, Plano plano) {

		this.danio = -20;
		this.fueUsado = false;
		this.rectangulo = new Rectangulo(42, 19, punto);
		this.inicializarOrigenAlgo42(origenAlgo);
		this.determinarPlano(plano);
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
		if (this.origenAlgo42 == true)
			this.naveRastreada = this.plano.devolverListaNaves().get(0);
		else {
			int n = this.plano.devolverListaNavesAI().size();
			if (n == 0)
				this.naveRastreada = this.plano.devolverAlgo42();
			else
				this.naveRastreada = this.plano.devolverListaNavesAI().get(n-1);
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

	public void setNaveRastreada( Nave unaNave) {
		this.naveRastreada = unaNave;
	}
	
}
