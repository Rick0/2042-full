package fiuba.algo3.juego.modelo;

import fiuba.algo3.juego.modelo.excepciones.ArmaUsadaError;


public class TorpedoRastreador extends Arma {

	private Nave naveRastreada;
	private int tiempoDeVida = 500;


	public TorpedoRastreador (Punto punto, boolean origenAlgo, Plano plano) {

		this.danio = -20;
		this.fueUsado = false;
		this.rectangulo = (new Rectangulo(2 , 2, punto ));
		this.InicializarOrigenAlgo42(origenAlgo);
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
			Punto nuevaPosicionX = new Punto(this.devolverPunto().getX() - 1, this.devolverPunto().getY());
			this.cambiarPosicion( nuevaPosicionX );
		}
		else if ( (this.naveRastreada.devolverPunto().getX() > this.devolverPunto().getX() )) {	
			Punto nuevaPosicionX = new Punto(this.devolverPunto().getX() + 1, this.devolverPunto().getY());
			this.cambiarPosicion( nuevaPosicionX );
		}

		if ( (this.naveRastreada.devolverPunto().getY() < this.devolverPunto().getY() )) {
			Punto nuevaPosicionY = new Punto(this.devolverPunto().getX(), this.devolverPunto().getY() -1 );
			this.cambiarPosicion( nuevaPosicionY );
		}
		else if ( (this.naveRastreada.devolverPunto().getY() > this.devolverPunto().getY() )) {
			Punto nuevaPosicionY = new Punto(this.devolverPunto().getX(), this.devolverPunto().getY() +1 );
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
			this.intentarMover();
			this.intentarChocar();
		}
	}

}
