package fiuba.algo3.juego.modelo;

import java.io.Serializable;
import fiuba.algo3.juego.modelo.excepciones.ArmaUsadaError;


public class TorpedoRastreador extends Arma implements Serializable{

	private static final long serialVersionUID = 2021359934861160328L;
	private Nave naveRastreada;
	private int tiempoDeVida = 333;
	static int cantidadAMover = 2;


	public TorpedoRastreador (Punto punto, boolean origenAlgo, Plano plano) {

		this.danio = -20;
		this.fueUsado = false;
		this.rectangulo = new Rectangulo(42, 19, punto);
		this.inicializarOrigenAlgo42(origenAlgo);
		this.determinarPlano(plano);

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
	public void determinarNaveRastreada(Nave unaNave) {
		this.naveRastreada = unaNave;
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

			boolean naveRastreadaFueraDePlano;
			if (naveRastreada.esOperable())
				naveRastreadaFueraDePlano = false;
			else {
				if (((NaveNoOperable)naveRastreada).fueraDelPlano())
					naveRastreadaFueraDePlano = true;
				else
					naveRastreadaFueraDePlano = false;
			}

			if (naveRastreada.estadoActualDestruida() || naveRastreadaFueraDePlano) {

				if (this.plano.hayNavesEnemigas()) {
					naveRastreada = this.plano.devolverListaNaves().get(0);
					this.intentarMover();
				}
				else {
					Punto nuevaPosicion = null;
					if (origenAlgo42)
						nuevaPosicion = new Punto(this.devolverPunto().getX(),this.devolverPunto().getY() + cantidadAMover);	
					else
						nuevaPosicion = new Punto(this.devolverPunto().getX(),this.devolverPunto().getY() - cantidadAMover);
					this.cambiarPosicion(nuevaPosicion);
				}
			}
			else {
				this.intentarMover();
			}

			this.intentarChocar();
		}
	}

	public int devolverCantidadAMover() {
		return cantidadAMover;
	}

}
