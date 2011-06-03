package juego;
import juego.excepciones.ArmaUsadaError;

public class TorpedoRastreador extends Arma {

	private Nave naveRastreada;


	public TorpedoRastreador (double d, double f, boolean origenAlgo,Plano plano ) {

		this.danio = -20;
		this.usada = false;
		this.rectangulo = (new Rectangulo(2 , 2, d, f ));
		this.determinarPlano(plano);

		try {
			this.plano.agregarArma( this );
		} catch (ArmaUsadaError e)
		{
			System.out.println("Se produjo un error irrecuperable");
		}

		this.InicializarOrigenAlgo42(origenAlgo);
	}

	/* Guarda la nave a la cual rasteara el torpedo. 
	 * Cuando se utilice el metodo mover, el torpedo se movera hacia esa nave
	 */
	public void determinarNaveRastreada(Nave unaNave) {
		this.naveRastreada = unaNave;
	}

	/* El torpedo rastreador se mueve de tal forma que se acerque a la nave que persigue */ 
	public void mover() {

		if (this.naveRastreada.posicionX() < this.posicionX() ) {
			this.determinarPosicion( (this.posicionX() - 1) , this.posicionY() );
		}
		else if ( (this.naveRastreada.posicionX() > this.posicionX() )) {		
			this.determinarPosicion( (this.posicionX() + 1) , this.posicionY() );
		}

		if ( (this.naveRastreada.posicionY() < this.posicionY() )) {
			this.determinarPosicion( (this.posicionX()) , (this.posicionY() - 1) );
		}
		else if ( (this.naveRastreada.posicionY() > this.posicionY() )) {
			this.determinarPosicion( (this.posicionX()) , this.posicionY() +1 );
		}

	}

}
