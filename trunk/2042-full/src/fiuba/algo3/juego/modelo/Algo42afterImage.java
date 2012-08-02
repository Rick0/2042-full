package fiuba.algo3.juego.modelo;

import java.io.Serializable;

import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.NaveNoDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


/* After-image de la nave Algo42 */
public class Algo42afterImage extends NaveNoOperable implements Serializable {

	private static final long serialVersionUID = -3109680188616011654L;
	int tiempoDeVida = 121;


	public Algo42afterImage(Punto punto, Plano unPlano) throws NaveDestruidaError {
		
		super();
		this.energia = 1;
		this.estaDestruida = false;
		this.esOperable = true;
		
		this.rectangulo = new Rectangulo(62, 52, punto);
		this.determinarPlano(unPlano);
		
		this.plano.agregarNaveAI(this);
		this.plano.agregarObjetoNuevo(this);
	}

	@Override
	public void vivir() {

		if (!this.estaDestruida) {

			this.pasaUnTiempo();
			
			if (tiempoDeVida <= 0) {

				this.energia = 0;
				try {
					this.destruirse();
				} catch (NaveNoDestruidaError e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/* En cada instante, se disminuye el tiempo de vida */
	public void pasaUnTiempo() {

		tiempoDeVida = tiempoDeVida - 1;
	}

	@Override
	public void modificarEnergia (int energiaAModificar) {

		energia = energia + energiaAModificar - 1;

		if (energia <= 0) {
			
			try {
				this.destruirse();
			} catch (NaveNoDestruidaError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void disparar() {
	}

	@Override
	public void mover() throws SuperposicionNavesError {
	}

	@Override
	public void moverAlternativo() throws SuperposicionNavesError {
	}

	@Override
	/* Lleva a cabo las acciones correspondientes si debe destruirse */
	public void destruirse() throws NaveNoDestruidaError {

		if ( (this.devolverEnergia()) > 0 ) {
			throw new NaveNoDestruidaError("La nave aun tiene energia en su tanque");
		}
		else {
			estaDestruida = true;
			plano.agregarNaveAIEliminada(this);
		}
	}
	
}
