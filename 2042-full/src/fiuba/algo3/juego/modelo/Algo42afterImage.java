package fiuba.algo3.juego.modelo;


/* After-image de la nave Algo42 */
public class Algo42afterImage extends ObjetoUbicable {

	private static final long serialVersionUID = -3109680188616011654L;
	int tiempoDeVida = 41;
	boolean desaparecio;


	public Algo42afterImage(Punto punto, Plano unPlano) {

		this.rectangulo = new Rectangulo(62, 52, punto);
		this.determinarPlano(unPlano);
		this.plano.agregarObjetoNuevo(this);
		this.desaparecio = false;
	}

	@Override
	public void vivir() {

		if (!this.desaparecio) {

			this.pasaUnTiempo();
			if (tiempoDeVida == 0) {
				this.desaparecio = true;
				plano.agregarObjetoDestruido(this);
			}
		}
	}

	/* En cada instante, se disminuye el tiempo de vida */
	public void pasaUnTiempo() {

		tiempoDeVida = tiempoDeVida - 1;
	}

}
